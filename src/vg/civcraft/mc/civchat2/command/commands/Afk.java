package vg.civcraft.mc.civchat2.command.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import vg.civcraft.mc.civchat2.CivChat2;
import vg.civcraft.mc.civchat2.CivChat2Manager;
import vg.civcraft.mc.civchat2.command.CivChat2CommandHandler;
import vg.civcraft.mc.civchat2.utility.CivChat2Log;
import vg.civcraft.mc.namelayer.NameAPI;
import vg.civcraft.mc.namelayer.command.PlayerCommand;

public class Afk extends PlayerCommand{
	private CivChat2Manager chatMan = CivChat2.getCivChat2Manager();
	private CivChat2Log logger = CivChat2.getCivChat2Log();
	private CivChat2CommandHandler handler = CivChat2.getCivChat2CommandHandler();
	
	public Afk(String name) {
		super(name);
		setIdentifier("afk");
		setDescription("This command is used to toggle afk status.");
		setUsage("/afk");
		setArguments(0,0);
	}
	
	@Override
	public boolean execute(CommandSender sender, String[] args){
		if(!(sender instanceof Player)){
			//console man sending chat... 
			sender.sendMessage(ChatColor.YELLOW + "You must be a player to perform that command.");
			return true;
		}
		
		Player player = (Player) sender;
		
		if(!(args.length == 0)){
			handler.helpPlayer(this, sender); 
			return true;
		}
		
		String name = NameAPI.getCurrentName(player.getUniqueId());
		chatMan.toggleAfk(name);
		String debugMessage = "Player toggled AFK state, Player: " + name + " Current State: " + chatMan.isAfk(name);
		logger.debug(debugMessage);
		
		return true;
		
	}

	@Override
	public List<String> tabComplete(CommandSender arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}	

}