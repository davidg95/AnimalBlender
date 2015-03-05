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
 * @version 1.0.0
 */
public class AnimalBlender extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.broadcastMessage("Cheese");
    }

    @Override
    public void onDisable() {

    }

    /**
     * The onCommand method to listen for the /blend command is entered. if it
     * is then it will take in the animal to be blended and the radius it will
     * take effect in.
     *
     * @param sender the person who sent the command.
     * @param cmd the command that was sent.
     * @param label
     * @param args any arguments that followed the command.
     * @return returns true or false whether the command was carried out or not.
     */
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
                //Check the correct command was sent
                if (cmd.getName().equalsIgnoreCase("blend")) {
                    String animal = args[0];

                    int radius;

                    Player player = (Player) sender;

                    //Check a number was entered for the radius
                    try {
                        radius = Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        player.sendMessage("You must type in a number above 0 for the radius!");
                        return false;
                    }

                    World world = player.getWorld();

                    List list;

                    list = world.getEntities();

                    int count = 0;

                    //Check which animal was entered
                    if (animal.equals("rabbits")) { //If rabbits was entered
                        player.sendMessage("Emma would murder you if you did that...");
                    } else if (animal.equals("horses")) { //If horses was entered
                        player.sendMessage("Emma would murder you if you did that...");
                    } else if (animal.equals("squid")) { //If squid was entered
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
                                        String tag = squid.getCustomName();
                                        if(tag == null){
                                            squid.remove();
                                            count++;
                                        }
                                    }
                                }
                            }
                        }
                        player.sendMessage("You have blended " + count + " squid.");
                    } else if (animal.equals("cows")) { //If cows was entered
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
                                        String tag = cow.getCustomName();
                                        if(tag == null){
                                            cow.remove();
                                            count++;
                                        }
                                    }
                                }
                            }
                        }
                        player.sendMessage("You have blended " + count + " cows.");
                    } else if (animal.equals("pigs")) { //If pigs was entered
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
                                        String tag = pig.getCustomName();
                                        if(tag == null){
                                            pig.remove();
                                            count++;
                                        }
                                    }
                                }
                            }
                        }
                        player.sendMessage("You have blended " + count + " pigs.");
                    } else if (animal.equals("chickens")) { //If checkens was entered
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
                                        String tag = chicken.getCustomName();
                                        if(tag == null){
                                            chicken.remove();
                                            count++;
                                        }
                                    }
                                }
                            }
                        }
                        player.sendMessage("You have blended " + count + " chickens.");
                    } else if (animal.equals("sheep")) {  //If sheep was entered
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
                                        String tag = sheep.getCustomName();
                                        if(tag == null){
                                            sheep.remove();
                                            count++;
                                        }
                                    }
                                }
                            }
                        }
                        player.sendMessage("You have blended " + count + " sheep.");
                    } else { //If the animal or word was not recognised
                        sender.sendMessage("You cannot blend that!");
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
