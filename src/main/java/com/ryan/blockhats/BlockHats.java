package com.ryan.blockhats;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockHats extends JavaPlugin implements CommandExecutor {
	
	@Override
	public void onEnable() {
		this.getCommand("hat").setExecutor(this);
	}
	
	@Override
	public void onDisable() {}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			ItemStack item = player.getInventory().getItemInMainHand();
			
			if (item.getType().isBlock() && item.getType() != Material.AIR) {
				ItemStack helmet = player.getInventory().getHelmet();
				player.getInventory().setHelmet(item);
				if (helmet == null) {
					player.getInventory().remove(item);
				} else {
					player.getInventory().setItemInMainHand(helmet);
				}

				player.sendMessage(ChatColor.RED + "[BlockHats]" + ChatColor.LIGHT_PURPLE + " Now wearing " + item.getType() + " as a hat!");
			} else {
				player.sendMessage(ChatColor.RED + "[BlockHats] That is not a block!");
			}
		}
		return true;
	}
}