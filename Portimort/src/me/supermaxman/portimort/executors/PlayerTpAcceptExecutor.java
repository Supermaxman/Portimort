package me.supermaxman.portimort.executors;


import java.util.ArrayList;

import me.supermaxman.portimort.Portimort;

import org.bukkit.entity.Player;

public class PlayerTpAcceptExecutor extends BaseExecutor {
    public static final ArrayList<String> tphere = new ArrayList<String>();
    
    @Override
    protected void run(Player player, String[] args) {

    	if(Portimort.tprequests.containsKey(player.getName())) {
    		Player from = player.getServer().getPlayerExact(Portimort.tprequests.get(player.getName()));
    		if(from==null) {
        		player.sendMessage(Portimort.pxe+"Player may have logged off.");//error, player not found
    			Portimort.tprequests.remove(player.getName());
    		}else {
    			if(tphere.contains(player.getName())) {
    				tphere.remove(player.getName());
        			PlayerTpExecutor.tp(player, from);
        			Portimort.tprequests.remove(player.getName());
    			}else {
        			PlayerTpExecutor.tp(from, player);
        			Portimort.tprequests.remove(player.getName());
    			}

    		}
    		
    	}else {
    		player.sendMessage(Portimort.px+"No requests have been submitted.");//error, player not found
    	}
    }

    public PlayerTpAcceptExecutor(Portimort pi) {
        super(pi);
    }
}
