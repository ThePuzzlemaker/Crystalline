package com.teamisotope.crystalline.client;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.teamisotope.crystalline.Crystalline;
import net.minecraft.resources.IResource;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.commons.io.IOUtils;

import javax.vecmath.Vector4f;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;

// kill me now
// this class is going to be the source of 99% of this mod's errors
// please help me now
// please stop me before I become unstoppable
// why won't Forge implement a thing like this
public class CrystallineOBJHelper {

    public static OBJModel.MaterialLibrary replaceMaterials(OBJModel objModel, ResourceLocation newMaterialFile) throws Exception {
        Class<? extends OBJLoader> objLoaderClass = OBJLoader.class;
        OBJLoader objLoader = OBJLoader.INSTANCE;
        OBJModel.MaterialLibrary materialLibrary = new OBJModel.MaterialLibrary();
        Field irmField = objLoaderClass.getDeclaredField("manager");
        irmField.setAccessible(true);
        IResourceManager irm = (IResourceManager)irmField.get(objLoader);
        materialLibrary.parseMaterials(irm, newMaterialFile.getPath(), newMaterialFile);
        return materialLibrary;
    }

    public static IResourceManager getIRMFromOBJLoader() throws Exception {
        Field irmField = ObfuscationReflectionHelper.findField(OBJLoader.class, "manager");
        irmField.setAccessible(true);
        IResourceManager irm = (IResourceManager)irmField.get(OBJLoader.INSTANCE);
        return irm;
    }

    public static class CrystallineOBJParser
    {
        private static final Pattern WHITE_SPACE = Pattern.compile("\\s+");
        private static Set<String> unknownObjectCommands = new HashSet<String>();
        public OBJModel.MaterialLibrary materialLibrary = new OBJModel.MaterialLibrary();
        private IResourceManager manager;
        private InputStreamReader objStream;
        private BufferedReader objReader;
        private ResourceLocation objFrom;

        private List<String> groupList = Lists.newArrayList();
        private List<OBJModel.Vertex> vertices = Lists.newArrayList();
        private List<OBJModel.Normal> normals = Lists.newArrayList();
        private List<OBJModel.TextureCoordinate> texCoords = Lists.newArrayList();

        public CrystallineOBJParser(IResource from, IResourceManager manager) throws IOException
        {
            this.manager = manager;
            this.objFrom = from.getLocation();
            this.objStream = new InputStreamReader(from.getInputStream(), StandardCharsets.UTF_8);
            this.objReader = new BufferedReader(objStream);
        }

        public List<String> getElements()
        {
            return this.groupList;
        }

        private float[] parseFloats(String[] data) // Helper converting strings to floats
        {
            float[] ret = new float[data.length];
            for (int i = 0; i < data.length; i++)
                ret[i] = Float.parseFloat(data[i]);
            return ret;
        }

        //Partial reading of the OBJ format. Documentation taken from http://paulbourke.net/dataformats/obj/
        public OBJModel parse(OBJModel.MaterialLibrary library) throws Exception
        {
            materialLibrary = library;
            String currentLine = "";
            OBJModel.Material material = new OBJModel.Material();
            material.setName(OBJModel.Material.DEFAULT_NAME);
            int usemtlCounter = 0;
            int lineNum = 0;

            for (;;)
            {
                lineNum++;
                currentLine = objReader.readLine();
                if (currentLine == null) break;
                currentLine.trim();
                if (currentLine.isEmpty() || currentLine.startsWith("#")) continue;

                try
                {
                    String[] fields = WHITE_SPACE.split(currentLine, 2);
                    String key = fields[0];
                    String data = fields[1];
                    String[] splitData = WHITE_SPACE.split(data);

                    if (key.equalsIgnoreCase("usemtl"))
                    {
                        Field materialsField = ObfuscationReflectionHelper.findField(OBJModel.MaterialLibrary.class, "materials");
                        materialsField.setAccessible(true);
                        Map<String, OBJModel.Material> materials = (Map<String,OBJModel.Material>)materialsField.get(this.materialLibrary);
                        if (materials.containsKey(data))
                        {
                            material = materials.get(data);
                        }
                        else
                        {
                            Crystalline.LOGGER.error("OBJModel.Parser: (Model: '{}', Line: {}) material '{}' referenced but was not found", objFrom, lineNum, data);
                        }
                        usemtlCounter++;
                    }
                    else if (key.equalsIgnoreCase("v")) // Vertices: x y z [w] - w Defaults to 1.0
                    {
                        float[] coords = parseFloats(splitData);
                        Vector4f pos = new Vector4f(coords[0], coords[1], coords[2], coords.length == 4 ? coords[3] : 1.0F);
                        this.vertices.add(new OBJModel.Vertex(pos, material));
                    }
                    else if (key.equalsIgnoreCase("vn")) // Vertex normals: x y z
                    {
                        this.normals.add(new OBJModel.Normal(parseFloats(splitData)));
                    }
                    else if (key.equalsIgnoreCase("vt")) // Vertex Textures: u [v] [w] - v/w Defaults to 0
                    {
                        float[] coords = parseFloats(splitData);
                        OBJModel.TextureCoordinate texCoord = new OBJModel.TextureCoordinate(coords[0],
                                coords.length >= 2 ? coords[1] : 0.0F,
                                coords.length >= 3 ? coords[2] : 0.0F);
                        if (texCoord.u < 0.0f || texCoord.u > 1.0f || texCoord.v < 0.0f || texCoord.v > 1.0f)
                            throw new OBJModel.UVsOutOfBoundsException(this.objFrom);
                        this.texCoords.add(texCoord);
                    }
                    else if (key.equalsIgnoreCase("f")) // Face Elements: f v1[/vt1][/vn1] ...
                    {
                        if (splitData.length > 4)
                            Crystalline.LOGGER.warn("OBJModel.Parser: found a face ('f') with more than 4 vertices, only the first 4 of these vertices will be rendered!");

                        List<OBJModel.Vertex> v = Lists.newArrayListWithCapacity(splitData.length);

                        for (int i = 0; i < splitData.length; i++)
                        {
                            String[] pts = splitData[i].split("/");

                            int vert = Integer.parseInt(pts[0]);
                            Integer texture = pts.length < 2 || Strings.isNullOrEmpty(pts[1]) ? null : Integer.parseInt(pts[1]);
                            Integer normal = pts.length < 3 || Strings.isNullOrEmpty(pts[2]) ? null : Integer.parseInt(pts[2]);

                            vert = vert < 0 ? this.vertices.size() - 1 : vert - 1;
                            OBJModel.Vertex newV = new OBJModel.Vertex(new Vector4f(this.vertices.get(vert).getPos()), this.vertices.get(vert).getMaterial());

                            if (texture != null)
                                newV.setTextureCoordinate(this.texCoords.get(texture < 0 ? this.texCoords.size() - 1 : texture - 1));
                            if (normal != null)
                                newV.setNormal(this.normals.get(normal < 0 ? this.normals.size() - 1 : normal - 1));

                            v.add(newV);
                        }

                        OBJModel.Vertex[] va = v.toArray(new OBJModel.Vertex[v.size()]);

                        Field materialNameField = ObfuscationReflectionHelper.findField(OBJModel.Material.class, "name");
                        materialNameField.setAccessible(true);
                        String materialName = (String)materialNameField.get(material);
                        OBJModel.Face face = new OBJModel.Face(va, materialName);
                        if (usemtlCounter < this.vertices.size())
                        {
                            for (OBJModel.Vertex ver : face.getVertices())
                            {
                                ver.setMaterial(material);
                            }
                        }

                        if (groupList.isEmpty())
                        {
                            if (this.materialLibrary.getGroups().containsKey(OBJModel.Group.DEFAULT_NAME))
                            {
                                this.materialLibrary.getGroups().get(OBJModel.Group.DEFAULT_NAME).addFace(face);
                            }
                            else
                            {
                                OBJModel.Group def = new OBJModel.Group(OBJModel.Group.DEFAULT_NAME, null);
                                def.addFace(face);
                                this.materialLibrary.getGroups().put(OBJModel.Group.DEFAULT_NAME, def);
                            }
                        }
                        else
                        {
                            for (String s : groupList)
                            {
                                if (this.materialLibrary.getGroups().containsKey(s))
                                {
                                    this.materialLibrary.getGroups().get(s).addFace(face);
                                }
                                else
                                {
                                    OBJModel.Group e = new OBJModel.Group(s, null);
                                    e.addFace(face);
                                    this.materialLibrary.getGroups().put(s, e);
                                }
                            }
                        }
                    }
                    else if (key.equalsIgnoreCase("g") || key.equalsIgnoreCase("o"))
                    {
                        groupList.clear();
                        if (key.equalsIgnoreCase("g"))
                        {
                            String[] splitSpace = data.split(" ");
                            for (String s : splitSpace)
                                groupList.add(s);
                        }
                        else
                        {
                            groupList.add(data);
                        }
                    }
                    else
                    {
                        if (!unknownObjectCommands.contains(key))
                        {
                            unknownObjectCommands.add(key);
                            Crystalline.LOGGER.info("OBJLoader.Parser: command '{}' (model: '{}') is not currently supported, skipping. Line: {} '{}'", key, objFrom, lineNum, currentLine);
                        }
                    }
                }
                catch (RuntimeException e)
                {
                    throw new RuntimeException(String.format("OBJLoader.Parser: Exception parsing line #%d: `%s`", lineNum, currentLine), e);
                }
            }

            return new OBJModel(this.materialLibrary, this.objFrom);
        }
    }


}
