/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.davidg95.animalblender;

import java.util.LinkedList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.permissions.Permission;

/**
 * Animal Blender plugin that despawns all animals of a given type within a
 * given radius.
 *
 * @author David
 * @version 1.0.1
 */
public class AnimalBlender extends JavaPlugin {

    private final Permission radiusBypass = new Permission("animalblender.radiusbypass");
    private final Permission useBlend = new Permission("animalblender.blend");

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("Config file loaded");
    }

    @Override
    public void onDisable() {

    }

    public String colors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String getMessage(String message) {
        return colors(getConfig().getString("messages." + message));
    }

    /**
     * The onCommand method to listen for if the /blend command is entered. if
     * it is then it will take in the animal to be blended and the radius it
     * will take effect in.
     *
     * @param sender the person who sent the command.
     * @param cmd the command that was sent.
     * @param label the alias.
     * @param args any arguments that followed the command.
     * @return returns true or false whether the command was carried out or not.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //Check the correct command was sent
        if (cmd.getName().equalsIgnoreCase("blend")) {
            //Check to make sure the sender is a player
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission(useBlend)) {
                    //Check the correct amount of arguments were sent
                    if (args.length >= 2) {
                        try {
                            EntityType type = EntityType.valueOf(args[0].toUpperCase());
                            if (type == EntityType.PLAYER) {
                                player.sendMessage(getMessage("cant-despawn-that-entity"));
                            } else if (player.hasPermission("animalblender.blend." + type.name().toLowerCase())) {
                                //Check a number was entered for the radius
                                try {
                                    int radius = Integer.parseInt(args[1]);
                                    int radiusLimit = getConfig().getInt("max-radius");
                                    if (radius <= 0) {
                                        player.sendMessage(getMessage("greater-than-zero"));
                                    } else if (radius >= radiusLimit && !player.hasPermission(radiusBypass) && radiusLimit > 0) {
                                        player.sendMessage(getMessage("radius-to-large"));
                                    } else {
                                        List<LivingEntity> entities = player.getWorld().getLivingEntities();

                                        int count = 0;

                                        //Loop through all the living entities
                                        for (LivingEntity e : entities) {
                                            //Check it is the type the user entered
                                            if (e.getType() == type) {
                                                //Check is is within the radius
                                                if (player.getLocation().distance(e.getLocation()) <= radius) {
                                                    String tag = e.getCustomName();
                                                    if (tag == null || (args.length == 3 && args[2].equalsIgnoreCase("--ignoretag"))) {
                                                        e.setHealth(0);
                                                        count++;
                                                    }
                                                }
                                            }
                                        }
                                        player.sendMessage(getMessage("blended-entities")
                                                .replace("%amount", count + "")
                                                .replace("%entity", type.name().toLowerCase())
                                        );
                                    }
                                } catch (NumberFormatException e) {
                                    player.sendMessage(getMessage("greater-than-zero"));
                                }
                            } else {
                                player.sendMessage(getMessage("no-permission-entity"));
                            }
                        } catch (IllegalArgumentException e) {
                            player.sendMessage(getMessage("entity-not-found"));
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "/blend [type] [radius]");
                    }
                } else {
                    player.sendMessage(getMessage("no-permission"));
                }
            } else {
                sender.sendMessage(getMessage("player-only-command"));
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> toReturn = new LinkedList<>();
        if (args.length == 1) {
            for (EntityType type : EntityType.values()) {
                if (type.name().toLowerCase().startsWith(args[0].toLowerCase())) {
                    toReturn.add(type.name());
                }
            }
        }
        return toReturn;
    }
}
