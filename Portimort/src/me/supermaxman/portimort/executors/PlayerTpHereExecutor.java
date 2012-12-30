package me.supermaxman.portimort.executors;


import me.supermaxman.portimort.Portimort;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerTpHereExecutor extends BaseExecutor {
    @Override
    protected void run(Player player, String[] args) {
		if ((Portimort.permission.has(player,"tp.teleport"))){
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
    	if(!Portimort.tpstatus.containsKey(too.getName())) {
    		Portimort.tpstatus.put(too.getName(), 1);//default is allow
    	}
    	int i = Portimort.tpstatus.get(too.getName());
    	if(i==1) {
    		tpreq(player, too);
    	}else if(i==2){
    		player.sendMessage(Portimort.pxe+"Teleport Denied.");//you cant tp
    	}else if(i==3){
    		tpreq(player, too);
    	}else {
    		Portimort.tpstatus.put(too.getName(), 1);//default is allow
    		tpreq(player, too);

    	}
    	
		}else {
			player.sendMessage(Portimort.pxe+"Insufficient Permissions.");//no perms
		}
    }

    public PlayerTpHereExecutor(Portimort pi) {
        super(pi);
    }
    
    public static void tpreq(Player player, Player from) {
    	
    	Portimort.tprequests.put(from.getName(),player.getName());
    	PlayerTpAcceptExecutor.tphere.add(from.getName());
		from.sendMessage(Portimort.px+"You were requested to teleport to "+player.getDisplayName()+ChatColor.AQUA+".");//player requested
		from.sendMessage(Portimort.px+"Type /tpaccept or /tpdeny.");//player choice
		player.sendMessage(Portimort.px+"You requested to teleport "+from.getDisplayName()+ChatColor.AQUA+" to you.");//you requested
    }
    
}
