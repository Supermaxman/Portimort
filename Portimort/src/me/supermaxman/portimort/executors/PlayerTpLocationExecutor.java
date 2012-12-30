package me.supermaxman.portimort.executors;


import me.supermaxman.portimort.Portimort;

import org.bukkit.entity.Player;

public class PlayerTpLocationExecutor extends BaseExecutor {

    @Override
    protected void run(Player player, String[] args) {
		if ((Portimort.permission.has(player,"tp.location"))){
    	if(args.length==0) {
			player.sendMessage(Portimort.pxe+"Incorrect formatting.  Correct formatting is /tploc #,#,#.");//not right format
    		return;
    	}
    	String s = args[0];
    	
    	
    	PlayerTpExecutor.tploc(player, s);
		}else {
			player.sendMessage(Portimort.pxe+"Insufficient Permissions.");//no perms
		}
    }

    public PlayerTpLocationExecutor(Portimort pi) {
        super(pi);
    }
}
