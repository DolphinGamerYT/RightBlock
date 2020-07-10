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

public class Main extends JavaPlugin implements Listener {
	
	boolean started = false;
	
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
		
		if (cmd.getName().equals("start")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (p.hasPermission("rightblock.start")) {
					if (started == false) {
						p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BOLD.toString() + ChatColor.DARK_AQUA.toString() + "DOLPHlN" + ChatColor.DARK_GRAY + "] " + ChatColor.GREEN +  "Ya no puedes colorcar mas bloques. Mucha suerte!");
						started = true;
					} else {
						p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BOLD.toString() + ChatColor.DARK_AQUA.toString() + "DOLPHlN" + ChatColor.DARK_GRAY + "] " + ChatColor.GREEN +  "Ya puedes colocar bloques.");
						started = false;
					}
				} else {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BOLD.toString() + ChatColor.DARK_AQUA.toString() + "DOLPHlN" + ChatColor.DARK_GRAY + "] " + ChatColor.RED +  "No tienes permisos para ejecutar este comando. (rightblock.start)");
				}
			} else {
				if (started == false) {
					System.out.println("Ya no puedes colorcar mas bloques ingame");
					started = true;
				} else {
					System.out.println("Ya puedes colorcar bloques ingame");
					started = false;
				}
				
			}
		} else if (cmd.getName().equals("craft")) {
			Player p = (Player) sender;
			if (sender instanceof Player) {
				if (p.hasPermission("rightblock.craft")) {
					if (p.getInventory().contains(Material.CRAFTING_TABLE)) {
						p.playSound(p.getLocation(), "block.note_block.pling", 5, 1);
						p.openWorkbench(null, true);
					} else {
						p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BOLD.toString() + ChatColor.DARK_AQUA.toString() + "DOLPHlN" + ChatColor.DARK_GRAY + "] " + ChatColor.RED +  "No tienes una mesa de crafteo!");
					}
				} else {
					p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BOLD.toString() + ChatColor.DARK_AQUA.toString() + "DOLPHlN" + ChatColor.DARK_GRAY + "] " + ChatColor.RED +  "No tienes permisos para ejecutar este comando. (rightblock.craft)");
				}
			} else {
				System.out.println("No puedes usar una mesa de crafteo en la consola!");
			}
			
		}
		return false;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (started == false) {
			return;
		} else {
	        Player p = e.getPlayer();
	        Block b = e.getBlock();
	        if (!(b.getType() == Material.WATER_BUCKET) || !(b.getType() == Material.LAVA_BUCKET) || !(b.getType() == Material.END_PORTAL_FRAME)) {
	        	e.setCancelled(true);
	            p.playSound(p.getLocation(), "block.ladder.break", 5, 1);
	        }
		}
	}
}
