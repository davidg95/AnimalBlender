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
 * @version 0.4
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
        //Check to make sure the sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        } else {
            //Check the correct amount of arguments were sent
            if (args.length > 2) {
                sender.sendMessage("Too many arguments!");
                return false;
            } else if (args.length < 2) {
                sender.sendMessage("Not enough arguments!");
                return false;
            } else {
                if (cmd.getName().equalsIgnoreCase("blend")) {
                    String animal = args[0];

                    int radius;

                    Player player = (Player) sender;

                    try {
                        radius = Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        player.sendMessage("You must type in a number between 0 and 1600 for the radius!");
                        return false;
                    }
                    
                    if (radius > 1600 || radius < 0) {
                        Bukkit.broadcastMessage("Radius must be between 0 and 1600!");
                        return false;
                    }

                    World world = player.getWorld();

                    List list;

                    list = world.getEntities();

                    int count = 0;

                    if (animal.equals("rabbits")) {
                        player.sendMessage("Emma would murder you if you did that...");
                    } else if (animal.equals("horses")) {
                        player.sendMessage("Emma would murder you if you did that...");
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
                                        count++;
                                    }
                                }
                            }
                        }
                        player.sendMessage("You have blended " + count + " squid.");
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
                                        count++;
                                    }
                                }
                            }
                        }
                        player.sendMessage("You have blended " + count + " cows.");
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
                                        count++;
                                    }
                                }
                            }
                        }
                        player.sendMessage("You have blended " + count + " pigs.");
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
                                        count++;
                                    }
                                }
                            }
                        }
                        player.sendMessage("You have blended " + count + " chickens.");
                    } else if (animal.equals("sheep")) {
                        for (int i = 0; i <= (list.size() - 1); i++) {
                            if (list.get(i) instanceof Sheep) {
                                Sheep sheep = (Sheep) list.get(i);
                                Location sheepLoc = sheep.getLocation();
                                Location playerLoc = player.getLocation();

                                int sheepX = sheepLoc.getBlockX();
                                int sheepY = sheepLoc.getBlockY();

                                int playerX = playerLoc.getBlockX();
                                int playerY = playerLoc.getBlockY();

                                int xDif = sheepX - playerX;
                                int yDif = sheepY - playerY;

                                if (xDif >= -radius && xDif <= radius) {
                                    if (yDif >= -radius && yDif <= radius) {
                                        sheep.remove();
                                        count++;
                                    }
                                }
                            }
                        }
                        player.sendMessage("You have blended " + count + " sheep.");
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
