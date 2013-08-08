package me.supermaxman.portimort.executors;


import me.supermaxman.portimort.Portimort;
import me.supermaxman.portimort.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerTpExecutor extends BaseExecutor {
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
    	if(too==null || player.canSee(too) == false) {
    		player.sendMessage(Portimort.pxe+"Player not found.");//error, player not found
    		return;
    	}
    	if(!Portimort.tpstatus.containsKey(too.getName())) {
    		Portimort.tpstatus.put(too.getName(), 1);//default is allow
    	}
    	int i = Portimort.tpstatus.get(too.getName());
    	if(i==1) {
    		tp(player, too);
    	}else if(i==2){
    		player.sendMessage(Portimort.pxe+"Teleport Denied.");//you cant tp
    	}else if(i==3){
    		tpreq(player, too);
    	}else {
    		Portimort.tpstatus.put(too.getName(), 1);//default is allow
    		tp(player, too);
    	}
    	
		}else {
			player.sendMessage(Portimort.pxe+"Insufficient Permissions.");//no perms
		}
    }

    public PlayerTpExecutor(Portimort pi) {
        super(pi);
    }
    
    
    public static void tp(Player player, Player too) {
		player.teleport(too.getLocation());
		too.sendMessage(Portimort.px+player.getDisplayName()+ChatColor.AQUA+" teleported to you.");//player tp'd
		player.sendMessage(Portimort.px+"You teleported to "+too.getDisplayName()+ChatColor.AQUA+".");//you tp'd
    }
    
    public static void tpreq(Player player, Player too) {
    	////
    	Utils.checkList(player);
    	Utils.checkList(too);
    	
    	if(!Portimort.tprequests.get(too.getName()).contains(player.getName())) {
        	Portimort.tprequests.get(too.getName()).add(player.getName().toLowerCase());
    		too.sendMessage(Portimort.px+player.getDisplayName()+ChatColor.AQUA+" requested to teleport to you.");//player requested
    		too.sendMessage(Portimort.px+"Type /tpaccept or /tpdeny.");//player choice
    		player.sendMessage(Portimort.px+"You requested to teleport to "+too.getDisplayName()+ChatColor.AQUA+".");//you requested
    	}else {
    		player.sendMessage(Portimort.px+"You have already requested to teleport to "+too.getDisplayName()+ChatColor.AQUA+".");//you requested

    	}
    }
    public static void tpl(Player player, Player too) {
    	too.getWorld().strikeLightningEffect(too.getLocation());
		player.teleport(too.getLocation());
		player.sendMessage(Portimort.px+ChatColor.GOLD+"You teleported to "+too.getDisplayName()+ChatColor.GOLD+" with lightning.");//you tp'd
    }
    public static void tpo(Player player, Player too) {
		player.teleport(too.getLocation());
		player.sendMessage(Portimort.px+ChatColor.BLUE+"You teleported to "+too.getDisplayName()+ChatColor.BLUE+".");//you tp'd
    }
    public static void tpohere(Player player, Player too) {
		player.teleport(too.getLocation());
		too.sendMessage(Portimort.px+ChatColor.BLUE+"You teleported "+player.getDisplayName()+ChatColor.BLUE+" to you.");//you tp'd
    }
    public static void tploc(Player player, String s) {
    	String[] a = s.split(",");
    	Integer x = null;
    	Integer y = null;
    	Integer z = null;

    	if(a.length==3) {
    		try {
        		x = Integer.parseInt(a[0]);
        		y = Integer.parseInt(a[1]);
        		z = Integer.parseInt(a[2]);
    		}catch(NumberFormatException e) {
    			player.sendMessage(Portimort.pxe+"Incorrect formatting.  Correct formatting is /tpl #,#,#.");//not right format
    			return;
    		}
    		
    	}else {
			player.sendMessage(Portimort.pxe+"Incorrect formatting.  Correct formatting is /tpl #,#,#.");//not right format
    		return;
    		
    	}
    	if(x!=null&&y!=null&&z!=null) {
    		player.teleport(new Location(player.getWorld(), x, y, z));
    		player.sendMessage(Portimort.px+ChatColor.GREEN+"You teleported to "+x+","+y+","+z+ChatColor.GREEN+".");//you tp'd
    		
    	}else {
			player.sendMessage(Portimort.pxe+"Incorrect formatting.  Correct formatting is /tpl #,#,#.");//not right format
    		return;
    	}
    	
    }
}
