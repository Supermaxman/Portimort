package me.supermaxman.portimort.executors;


import me.supermaxman.portimort.Portimort;
import me.supermaxman.portimort.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerTpToggleExecutor extends BaseExecutor {
    @Override
    protected void run(Player player, String[] args) {
    	//perms
    	if(args.length==0) {
    		if(Portimort.tpstatus.containsKey(player.getName())) {
    			int i = Portimort.tpstatus.get(player.getName());
    			i++;
    			if(i>3) {
    				i=1;
    			}
    			Portimort.tpstatus.put(player.getName(), i);
    			player.sendMessage(Portimort.px+"Your Teleport Status has been set to "+ChatColor.GOLD+Utils.intToString(i));//explain what it was set as
    		}else {
    			Portimort.tpstatus.put(player.getName(), 2);//default set to allow, but since command, set to deny
    			player.sendMessage(Portimort.px+"Your Teleport Status has been set to "+ChatColor.GOLD+Utils.intToString(2));//explain what it was set as
    		}
    		
    	    Utils.savePlayercfg(player.getName());
    		
    		
    		return;
    	}else {
    		String s = args[0];
    		Integer i = Utils.stringToInt(s);
    		if(i==null) {
    			player.sendMessage(Portimort.pxe+"Incorrect Teleport Status, The three are Allow, Deny, and Ask.");//explain what it was set as
    			return;
    		}
    		Portimort.tpstatus.put(player.getName(), i);
			player.sendMessage(Portimort.px+"Your Teleport Status has been set to "+ChatColor.GOLD+Utils.intToString(i));//explain what it was set as
    	}
    	
    	
    }

    public PlayerTpToggleExecutor(Portimort pi) {
        super(pi);
    }
}
