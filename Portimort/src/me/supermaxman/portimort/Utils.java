package me.supermaxman.portimort;

import java.util.ArrayList;
import java.util.Map;

import me.supermaxman.portimort.executors.PlayerTpAcceptExecutor;
import me.supermaxman.portimort.executors.PlayerTpExecutor;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Utils {
	
	
	
	public static Integer stringToInt(String s) {
		if(s.equalsIgnoreCase("allow")) {
			return 1;
		}else if(s.equalsIgnoreCase("deny")){
			return 2;
		}else if(s.equalsIgnoreCase("ask")){
			return 3;
		}else {
			return null;
		}
		
	}
	
	
	public static String intToString(Integer i) {
		if(i==1) {
			return "Allow";
		}else if(i==2){
			return "Deny";
		}else if(i==3){
			return "Ask";
		}else {
			return null;
		}
		
	}
	
	
    public static void setupCfg() {
        if (Portimort.conf.isConfigurationSection("tppref")) {
        	 for (Map.Entry<String, Object> entry : Portimort.conf.getConfigurationSection("tppref").getValues(false).entrySet()) {
                 ConfigurationSection cs = Portimort.conf.getConfigurationSection("tppref." + entry.getKey());
                 int i = Integer.parseInt(cs.getString("tps"));
                 Portimort.tpstatus.put(entry.getKey(), i);
        	 }
        }
    }
    
    public static void savecfg() {
        
        for(String s:Portimort.tpstatus.keySet()) {
        	int i = Portimort.tpstatus.get(s);
        	Portimort.conf.set("tppref." + s + ".tps", i);

        }
        
        Portimort.pi.saveConfig();
    }
    public static void savePlayercfg(String s) {
        int i = Portimort.tpstatus.get(s);
        Portimort.conf.set("tppref." + s + ".tps", i);
        Portimort.pi.saveConfig();
    }
    
    
    public static void checkList(Player player) {
    	if(!Portimort.tprequests.containsKey(player.getName())) {
    		Portimort.tprequests.put(player.getName(),new ArrayList<String>());
    	}
    }
    
    public static void handleAccept(Player player) {
    	
    	checkList(player);
    	if(!Portimort.tprequests.get(player.getName()).isEmpty()) {
    	
    	Player from = player.getServer().getPlayerExact(Portimort.tprequests.get(player.getName()).remove(0));
    	if(from==null) {
        		player.sendMessage(Portimort.pxe+"Player may have logged off.");//error, player not found
            	
    	}else {
    		if(PlayerTpAcceptExecutor.tphere.contains(player.getName())) {
    			PlayerTpAcceptExecutor.tphere.remove(player.getName());
        		PlayerTpExecutor.tp(player, from);

    		}else {
        			PlayerTpExecutor.tp(from, player);
    		}

    	}
    		
    	}else {
    		player.sendMessage(Portimort.px+"No requests have been submitted.");//error, player not found
    	}
    	
    	
    }
   
    
    public static void handleAccept(Player player, String s) {
    	
    	checkList(player);
    	if(!Portimort.tprequests.get(player.getName()).isEmpty()) {
    		
    	if(Portimort.tprequests.get(player.getName()).remove(s)) {
        	Player from = player.getServer().getPlayerExact(s);
        	if(from==null) {
            		player.sendMessage(Portimort.pxe+"Player may have logged off.");//error, player not found
            		
        	}else {
        		if(PlayerTpAcceptExecutor.tphere.contains(player.getName())) {
        			PlayerTpAcceptExecutor.tphere.remove(player.getName());
            		PlayerTpExecutor.tp(player, from);

        		}else {
            			PlayerTpExecutor.tp(from, player);
        		}

        	}
    	}else {
    		player.sendMessage(Portimort.px+"No requests have been submitted by "+s+".");//error, player not found
    	}
    		
    	}else {
    		player.sendMessage(Portimort.px+"No requests have been submitted.");//error, player not found
    	}
    	
    	
    }
    
    
    
    
    
    public static void handleDeny(Player player) {
    	
    	checkList(player);
    	if(!Portimort.tprequests.get(player.getName()).isEmpty()) {
    	
    	Player from = player.getServer().getPlayerExact(Portimort.tprequests.get(player.getName()).remove(0));
    	if(from==null) {
    		player.sendMessage(Portimort.pxe+"Player may have logged off.");//error, player not found
		}else {
			if(PlayerTpAcceptExecutor.tphere.contains(player.getName())) {
				PlayerTpAcceptExecutor.tphere.remove(player.getName());
				player.sendMessage(Portimort.px+ChatColor.RED+"You denied the teleport request from "+from.getDisplayName()+ChatColor.RED+".");//you requested
				from.sendMessage(Portimort.px+ChatColor.RED+"Your request to teleport "+player.getDisplayName()+ChatColor.RED+" to you has been denied.");//you requested
			}else {
				player.sendMessage(Portimort.px+ChatColor.RED+"You denied the teleport request from "+from.getDisplayName()+ChatColor.RED+".");//you requested
				from.sendMessage(Portimort.px+ChatColor.RED+"Your request to teleport to "+player.getDisplayName()+ChatColor.RED+" has been denied.");//you requested
			}
		}
    		
    	}else {
    		player.sendMessage(Portimort.px+"No requests have been submitted.");//error, player not found
    	}
    	
    	
    }
    
    
    public static void handleDeny(Player player, String s) {
    	
    	checkList(player);
    	if(!Portimort.tprequests.get(player.getName()).isEmpty()) {
        	if(Portimort.tprequests.get(player.getName()).remove(s)) {
            	Player from = player.getServer().getPlayerExact(s);
            	if(from==null) {
                		player.sendMessage(Portimort.pxe+"Player may have logged off.");//error, player not found
                		
            	}else {
            		if(PlayerTpAcceptExecutor.tphere.contains(player.getName())) {
            			PlayerTpAcceptExecutor.tphere.remove(player.getName());
        				player.sendMessage(Portimort.px+ChatColor.RED+"You denied the teleport request from "+from.getDisplayName()+ChatColor.RED+".");//you requested
        				from.sendMessage(Portimort.px+ChatColor.RED+"Your request to teleport "+player.getDisplayName()+ChatColor.RED+" to you has been denied.");//you requested

            		}else {
        				player.sendMessage(Portimort.px+ChatColor.RED+"You denied the teleport request from "+from.getDisplayName()+ChatColor.RED+".");//you requested
        				from.sendMessage(Portimort.px+ChatColor.RED+"Your request to teleport to "+player.getDisplayName()+ChatColor.RED+" has been denied.");//you requested
        			}

            	}
        	}else {
        		player.sendMessage(Portimort.px+"No requests have been submitted by "+s+".");//error, player not found
        	}
    		
    	}else {
    		player.sendMessage(Portimort.px+"No requests have been submitted.");//error, player not found
    	}
    	
    	
    }
    
    
    
    
}
