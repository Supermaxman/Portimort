package me.supermaxman.portimort;

import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;

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
}
