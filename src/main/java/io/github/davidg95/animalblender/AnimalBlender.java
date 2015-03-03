/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.davidg95.animalblender;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.World;
import org.bukkit.Location;

import java.util.List;

/**
 * Animal Blender plugin that despawns all animals of a given type within a
 * given radius.
 *
 * @author David
 * @version 0.2
 */
public class AnimalBlender extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.broadcastMessage("Cheese");
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 2) {
            sender.sendMessage("Too many arguments!");
            return false;
        } else if (args.length < 2) {
            sender.sendMessage("Not enough arguments!");
            return false;
        } else {
            if (cmd.getName().equalsIgnoreCase("blend")) {
                //Check if it is a player
                if (!(sender instanceof Player)) {
                    sender.sendMessage("You must be a player to use this command!");
                    return true;
                } else {
                    String animal = args[0];

                    int radius = Integer.parseInt(args[1]);

                    if (radius > 16000) {
                        Bukkit.broadcastMessage("Radius must be less than 1600!");
                        return false;
                    }

                    Player player = (Player) sender;

                    World world = player.getWorld();

                    List list;

                    list = world.getEntities();

                    if (animal.equals("rabbits")) {
                        Bukkit.broadcastMessage("Emma would murder you if you did that...");
                    } else if (animal.equals("horses")) {
                        Bukkit.broadcastMessage("Emma would murder you if you did that...");
                    } else if (animal.equals("squid")) {
                        for (int i = 0; i <= (list.size() - 1); i++) {
                            if (list.get(i) instanceof Squid) {
                                Squid squid = (Squid) list.get(i);
                                Location squidLoc = squid.getLocation();
                                Location playerLoc = player.getLocation();

                                int squidX = squidLoc.getBlockX();
                                int squidY = squidLoc.getBlockY();

                                int playerX = playerLoc.getBlockX();
                                int playerY = playerLoc.getBlockY();

                                int xDif = squidX - playerX;
                                int yDif = squidY - playerY;

                                if (xDif >= -radius && xDif <= radius) {
                                    if (yDif >= -radius && yDif <= radius) {
                                        squid.remove();
                                    }
                                }
                            }
                        }
                    } else if (animal.equals("cows")) {
                        for (int i = 0; i <= (list.size() - 1); i++) {
                            if (list.get(i) instanceof Cow) {
                                Cow cow = (Cow) list.get(i);
                                Location cowLoc = cow.getLocation();
                                Location playerLoc = player.getLocation();

                                int cowX = cowLoc.getBlockX();
                                int cowY = cowLoc.getBlockY();

                                int playerX = playerLoc.getBlockX();
                                int playerY = playerLoc.getBlockY();

                                int xDif = cowX - playerX;
                                int yDif = cowY - playerY;

                                if (xDif >= -radius && xDif <= radius) {
                                    if (yDif >= -radius && yDif <= radius) {
                                        cow.remove();
                                    }
                                }
                            }
                        }
                    } else if (animal.equals("pigs")) {
                        for (int i = 0; i <= (list.size() - 1); i++) {
                            if (list.get(i) instanceof Pig) {
                                Pig pig = (Pig) list.get(i);
                                Location pigLoc = pig.getLocation();
                                Location playerLoc = player.getLocation();

                                int pigX = pigLoc.getBlockX();
                                int pigY = pigLoc.getBlockY();

                                int playerX = playerLoc.getBlockX();
                                int playerY = playerLoc.getBlockY();

                                int xDif = pigX - playerX;
                                int yDif = pigY - playerY;

                                if (xDif >= -radius && xDif <= radius) {
                                    if (yDif >= -radius && yDif <= radius) {
                                        pig.remove();
                                    }
                                }
                            }
                        }
                    } else if (animal.equals("chickens")) {
                        for (int i = 0; i <= (list.size() - 1); i++) {
                            if (list.get(i) instanceof Chicken) {
                                Chicken chicken = (Chicken) list.get(i);
                                Location chickenLoc = chicken.getLocation();
                                Location playerLoc = player.getLocation();

                                int chickenX = chickenLoc.getBlockX();
                                int chickenY = chickenLoc.getBlockY();

                                int playerX = playerLoc.getBlockX();
                                int playerY = playerLoc.getBlockY();

                                int xDif = chickenX - playerX;
                                int yDif = chickenY - playerY;

                                if (xDif >= -radius && xDif <= radius) {
                                    if (yDif >= -radius && yDif <= radius) {
                                        chicken.remove();
                                    }
                                }
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
