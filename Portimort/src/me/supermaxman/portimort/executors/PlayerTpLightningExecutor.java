package me.supermaxman.portimort.executors;


import me.supermaxman.portimort.Portimort;

import org.bukkit.entity.Player;

public class PlayerTpLightningExecutor extends BaseExecutor {
    @Override
    protected void run(Player player, String[] args) {
		if ((Portimort.permission.has(player,"tp.override"))){
    	//perms
    	if(args.length==0) {
    		player.sendMessage(Portimort.pxe+"No name specified.");//error, no name
    		return;
    	}
    	String s = args[0];
    	Player too = player.getServer().getPlayer(s);
    	if(too==null) {
    		player.sendMessage(Portimort.pxe+"Player not found.");//error, player not found
    		return;
    	}
    	
    	PlayerTpExecutor.tpl(player, too);
		}else {
			player.sendMessage(Portimort.pxe+"Insufficient Permissions.");//no perms
		}
    }

    public PlayerTpLightningExecutor(Portimort pi) {
        super(pi);
    }
}
