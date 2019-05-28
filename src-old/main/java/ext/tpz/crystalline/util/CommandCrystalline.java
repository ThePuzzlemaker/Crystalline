package ext.tpz.crystalline.util;

import com.google.common.collect.Lists;
import ext.tpz.crystalline.api.crystal.CrystalRegistry;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.insanity.InsanityUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.server.permission.PermissionAPI;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class CommandCrystalline extends CommandBase {

    public final List<String> aliases;

    public CommandCrystalline() {
        aliases = Lists.newArrayList(Reference.MODID, "crystalline", "cl");
    }

    @Override
    @Nonnull
    public String getName() {
        return "crystalline";
    }

    @Override
    @Nonnull
    public String getUsage(@Nonnull ICommandSender iCommandSender) {
        return "crystalline insanity\n" +
                TextFormatting.RED + "               set <insanity>\n" +
                TextFormatting.RED + "           crystal\n" +
                TextFormatting.RED + "               dump\n";
    }

    @Override
    @Nonnull
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public void execute(@Nonnull MinecraftServer minecraftServer, @Nonnull ICommandSender iCommandSender, @Nonnull String[] args) throws CommandException {
        if (args.length < 2) {
            iCommandSender.sendMessage(new TextComponentString(TextFormatting.RED + "Usage: " + getUsage(iCommandSender)));
            return;
        }
        String base = args[0];
        if (base.equalsIgnoreCase("insanity")) {
            if (args[1].equalsIgnoreCase("set")) {
                if (args.length < 3) {
                    iCommandSender.sendMessage(new TextComponentString(TextFormatting.RED + "Usage: " + getUsage(iCommandSender)));
                    return;
                }
                int insanity;
                try {
                    insanity = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    iCommandSender.sendMessage(new TextComponentString(TextFormatting.RED + "Error parsing insanity!"));
                    return;
                }
                if (insanity < 0 || insanity > 101) {
                    iCommandSender.sendMessage(new TextComponentString(TextFormatting.RED + "Insanity must be from 0-101, 101 being permanent!"));
                    return;
                }
                EntityPlayerMP playerMp = getCommandSenderAsPlayer(iCommandSender);
                InsanityUtils.setInsanity(playerMp.getEntityWorld(), playerMp, insanity);
                iCommandSender.sendMessage(new TextComponentString("Set insanity to " + insanity + "."));
            }
        } else if (base.equalsIgnoreCase("crystal")) {
            if (args[1].equalsIgnoreCase("dump")) {
                String list = "";
                for (ICrystal c : CrystalRegistry.getRegistry()) {
                    list += c.getRegistryName().toString() + ", ";
                }
                iCommandSender.sendMessage(new TextComponentString(list));
            }
        } else {
            iCommandSender.sendMessage(new TextComponentString(TextFormatting.RED + "Usage: " + getUsage(iCommandSender)));
        }

    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        try {
            return PermissionAPI.hasPermission(getCommandSenderAsPlayer(sender), "crystalline.crystalline");
        } catch (PlayerNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Nonnull
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 0) {
            return Lists.newArrayList("insanity", "crystal");
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("insanity")) {
                return Lists.newArrayList("set");
            }
            if (args[0].equalsIgnoreCase("crystal")) {
                return Lists.newArrayList("dump");
            }
        }
        return Lists.newArrayList();
    }
}
