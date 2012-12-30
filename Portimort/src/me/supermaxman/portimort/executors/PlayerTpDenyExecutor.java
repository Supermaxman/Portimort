package me.supermaxman.portimort.executors;


import me.supermaxman.portimort.Portimort;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerTpDenyExecutor extends BaseExecutor {
    @Override
    protected void run(Player player, String[] args) {
    	if(Portimort.tprequests.containsKey(player.getName())) {
    		Player from = player.getServer().getPlayerExact(Portimort.tprequests.get(player.getName()));
    		if(from==null) {
        		player.sendMessage(Portimort.pxe+"Player may have logged off.");//error, player not found
    			Portimort.tprequests.remove(player.getName());
    		}else {
    			if(PlayerTpAcceptExecutor.tphere.contains(player.getName())) {
    				PlayerTpAcceptExecutor.tphere.remove(player.getName());
    				Portimort.tprequests.remove(player.getName());
    				player.sendMessage(Portimort.px+ChatColor.RED+"You denied the teleport request from "+from.getDisplayName()+ChatColor.RED+".");//you requested
    				from.sendMessage(Portimort.px+ChatColor.RED+"Your request to teleport "+player.getDisplayName()+ChatColor.RED+" to you has been denied.");//you requested
    			}else {
    				Portimort.tprequests.remove(player.getName());
    				player.sendMessage(Portimort.px+ChatColor.RED+"You denied the teleport request from "+from.getDisplayName()+ChatColor.RED+".");//you requested
    				from.sendMessage(Portimort.px+ChatColor.RED+"Your request to teleport to "+player.getDisplayName()+ChatColor.RED+" has been denied.");//you requested
    			}
    		}
    		
    	}else {
    		player.sendMessage(Portimort.px+"No requests have been submitted.");//error, player not found
    	}
    }

    public PlayerTpDenyExecutor(Portimort pi) {
        super(pi);
    }
}
