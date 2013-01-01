package me.supermaxman.portimort.executors;


import java.util.ArrayList;

import me.supermaxman.portimort.Portimort;
import me.supermaxman.portimort.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerTpRequestsExecutor extends BaseExecutor {
    @Override
    protected void run(Player player, String[] args) {
    	if(args.length==0) {
        	Utils.checkList(player);
        	ArrayList<String> list = Portimort.tprequests.get(player.getName());
    		player.sendMessage(Portimort.px+"Teleport Requests:");//you cant tp
        	if(list.isEmpty())return;
        	
        	for(String s:Portimort.tprequests.get(player.getName())) {
        		player.sendMessage(ChatColor.GREEN+s);//names
        	}
    	}else {
    		if(args[0].equalsIgnoreCase("clear")||args[0].equalsIgnoreCase("clr")) {
    	        Utils.checkList(player);
    	        Portimort.tprequests.remove(player.getName());
    	        PlayerTpAcceptExecutor.tphere.remove(player.getName());
    	        for(ArrayList<String> s:Portimort.tprequests.values()) {
    	        	s.remove(player.getName().toLowerCase());
    	        }
        		player.sendMessage(Portimort.px+"Teleport Requests Cleared.");//you cant tp
    		}else {
        		player.sendMessage(Portimort.pxe+"Unknown Command.");//you cant tp
    		}
    	}

    }

    public PlayerTpRequestsExecutor(Portimort pi) {
        super(pi);
    }
}
