/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.davidg95.animalblender;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.Location;

import java.util.List;
import org.bukkit.ChatColor;

/**
 * Animal Blender plugin that despawns all animals of a given type within a
 * given radius.
 *
 * @author David
 * @version 1.0.1
 */
public class AnimalBlender extends JavaPlugin {

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
                if (player.hasPermission("animalblender.blend")) {
                    //Check the correct amount of arguments were sent
                    if (args.length >= 2) {
                        try {
                            EntityType type = EntityType.valueOf(args[0].toUpperCase());
                            if (type == EntityType.PLAYER) {
                                player.sendMessage(getMessage("cant-despawn-that-entity"));
                            } else if (player.hasPermission("animalblender.blend." + type.name().toLowerCase())) {
                                int radius;
                                //Check a number was entered for the radius
                                try {
                                    radius = Integer.parseInt(args[1]);
                                    int radiusLimit = getConfig().getInt("max-radius");
                                    if (radius <= 0) {
                                        player.sendMessage(getMessage("greater-than-zero"));
                                        return true;
                                    } else if (radius >= radiusLimit && !player.hasPermission("animalblender.radiusbypass") && radiusLimit > 0) {
                                        player.sendMessage(getMessage("radius-to-large"));
                                        return true;
                                    }
                                } catch (NumberFormatException e) {
                                    player.sendMessage(getMessage("greater-than-zero"));
                                    return true;
                                }

                                List<Entity> entities = player.getWorld().getEntities();

                                int count = 0;

                                //Check which animal was entered
                                for (Entity e : entities) {
                                    if (e.getType() == type) {
                                        Location entityLocation = e.getLocation();
                                        Location playerLocation = player.getLocation();

                                        int squidX = entityLocation.getBlockX();
                                        int squidY = entityLocation.getBlockY();

                                        int playerX = playerLocation.getBlockX();
                                        int playerY = playerLocation.getBlockY();

                                        int xDif = squidX - playerX;
                                        int yDif = squidY - playerY;

                                        if (xDif >= -radius && xDif <= radius) {
                                            if (yDif >= -radius && yDif <= radius) {
                                                String tag = e.getCustomName();
                                                if (tag == null) {
                                                    e.remove();
                                                    count++;
                                                }
                                            }
                                        }
                                    }
                                }
                                player.sendMessage(getMessage("blended-entities").replace("%amount", count + "").replace("%entity", type.name().toLowerCase()));
                                return true;
                            } else {
                                sender.sendMessage(getMessage("no-permission-entity"));
                            }
                        } catch (IllegalArgumentException e) {
                            player.sendMessage(getMessage("entity-not-found"));
                            return true;
                        }
                    }
                } else {
                    sender.sendMessage(getMessage("no-permission"));
                }
            } else {
                sender.sendMessage(getMessage("player-only-command"));
                return true;
            }
        }
        return true;
    }
}
