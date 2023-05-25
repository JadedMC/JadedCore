package net.jadedmc.jadedcore.commands.misc;

import net.jadedmc.jadedcore.commands.AbstractCommand;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CenterCMD extends AbstractCommand {

    public CenterCMD() {
        super("center", "jadedcore.center", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        double x = player.getLocation().getBlockX() + 0.5;
        double y = player.getLocation().getY();
        double z = player.getLocation().getBlockZ() + 0.5;
        float pitch = 0;

        float yaw;
        if(player.getLocation().getYaw() > -45 && player.getLocation().getYaw() < 45) {
            yaw = 0;
        }
        else if(player.getLocation().getYaw() > 45 && player.getLocation().getYaw() < 135) {
            yaw = 90;
        }
        else if(player.getLocation().getYaw() > 135 || player.getLocation().getYaw() < -135) {
            yaw = 180;
        }
        else {
            yaw = -90;
        }

        Location location = new Location(player.getLocation().getWorld(), x, y, z, yaw, pitch);
        player.teleport(location);
    }
}
