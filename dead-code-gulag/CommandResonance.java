package com.teamisotope.crystalline.common.command;

import com.google.common.collect.Lists;
import com.teamisotope.crystalline.api.crystal.CrystalRegistry;
import com.teamisotope.crystalline.api.crystal.ICrystal;
import com.teamisotope.crystalline.api.resonance.Resonance;
import com.teamisotope.crystalline.api.resonance.WorldResonance;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandGive;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class CommandResonance extends CommandBase {

    @Override
    public String getName() {
        return "resonance";
    }

    @Override
    public String getUsage(ICommandSender iCommandSender) {
        return "resonance <crystal ID>";
    }

    @Override
    public List<String> getAliases() {
        return Lists.newArrayList("resonance", "cres", "resonances");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "Usage: /" + getUsage(sender)));
            return;
        }
        String id = args[0];
        ICrystal crystal = CrystalRegistry.deserialize(id);
        if (crystal == null) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "Error parsing crystal ID!"));
            return;
        }
        Resonance resonance = WorldResonance.INSTANCE.getResonance(crystal);
        sender.sendMessage(new TextComponentString(String.format("Lower/Upper: %d/%d", resonance.getLower(), resonance.getUpper())));
        sender.sendMessage(new TextComponentString(String.format("Exact: %d", resonance.getExact())));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 4;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender.canUseCommand(this.getRequiredPermissionLevel(), this.getName());
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length < 1) {
            return getListOfStringsMatchingLastWord(args, CrystalRegistry.getRegistry().getKeys().stream().map(ResourceLocation::toString).collect(Collectors.toList()));
        } else {
            return Lists.newArrayList();
        }
    }

}
