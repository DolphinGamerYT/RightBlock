package com.dolphln.rightblock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MainES extends JavaPlugin implements Listener {
	
	boolean started = false;
	String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.BOLD.toString() + ChatColor.DARK_AQUA.toString() + "RightBlock" + ChatColor.DARK_GRAY + "] ";
	
	@Override
	public void onEnable() {
		System.out.println("RightBlock Enabled Correctly (Made by Dolphln patreon.dolphln.com)");
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		System.out.println("RightBlock Disabled Correctly (Made by Dolphln patreon.dolphln.com)");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equals("game")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args.length <= 0 || args.length >= 2) {
					p.sendMessage(prefix + ChatColor.RED + "Error. Usa " + ChatColor.YELLOW + "/game start" + ChatColor.RED + " o " + ChatColor.YELLOW + "/game stop" + ChatColor.RED + ".");
				} else {
					if (args[0].equals("start") && !started) {
						Bukkit.broadcastMessage(prefix + ChatColor.GREEN + "El juego ha empezado. Ya no puedes colocar mas bloques");
						Bukkit.broadcastMessage(prefix + ChatColor.GOLD + "Plugin hecho por Dolphln. Puedes apoyarme en patreon.dolphln.com!");
						for (Player onlinep : Bukkit.getOnlinePlayers()) {
							onlinep.playSound(onlinep.getLocation(), "block.note_block.pling", 5, 1);
						}
						started = true;
					} else if (args[0].equals("stop") && started) {
						Bukkit.broadcastMessage(prefix + ChatColor.RED + "Juego parado por " + p.getName() + ". Ya puedes poner bloques.");
						for (Player onlinep : Bukkit.getOnlinePlayers()) {
							onlinep.playSound(onlinep.getLocation(), "block.note_block.pling", 5, 1);
						}
						started = false;
					} else {
						p.sendMessage(prefix + ChatColor.RED + "Porfavor, usa " + ChatColor.YELLOW + "/game start" + ChatColor.RED + " o " + ChatColor.YELLOW + "/game stop" + ChatColor.RED + " correctamente.");
					}
				}
				} else {
				if (started == false) {
					System.out.println("You cannot place more blocks ingame!");
					Bukkit.broadcastMessage(prefix + ChatColor.GREEN + "El juego ha empezado. Ya no puedes colocar mas bloques");
					Bukkit.broadcastMessage(prefix + ChatColor.GOLD + "Plugin hecho por Dolphln. Puedes apoyarme en patreon.dolphln.com!");
					for (Player onlinep : Bukkit.getOnlinePlayers()) {
						onlinep.playSound(onlinep.getLocation(), "block.note_block.pling", 5, 1);
					}
					started = true;
				} else {
					System.out.println("You can place blocks ingame.");
					Bukkit.broadcastMessage(prefix + ChatColor.RED + "Juego parado por CONSOLE. Ya puedes poner bloques.");
					for (Player onlinep : Bukkit.getOnlinePlayers()) {
						onlinep.playSound(onlinep.getLocation(), "block.note_block.pling", 5, 1);
					}
					started = false;
				}
				
			}
		} /*else if (cmd.getName().equals("craft")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
					if (p.getInventory().contains(Material.CRAFTING_TABLE)) {
						p.playSound(p.getLocation(), "block.note_block.pling", 5, 1);
						p.openWorkbench(null, true);
					} else {
						p.sendMessage(prefix + ChatColor.RED +  "No tienes una mesa de crafteo!");
					}
				} else {
				System.out.println("You cannot use the crafting table on console!");
			}
			
		}*/
		return false;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (started == false) {
			return;
		} else {
	        Player p = e.getPlayer();
	        Block b = e.getBlock();
	        if (b.getType() != Material.END_PORTAL_FRAME) {
	        	e.setCancelled(true);
	            p.playSound(p.getLocation(), "block.ladder.break", 5, 1);
	        }
		}
	}
}
