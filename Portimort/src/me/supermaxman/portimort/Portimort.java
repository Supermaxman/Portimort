package me.supermaxman.portimort;

import me.supermaxman.portimort.executors.PlayerTpAcceptExecutor;
import me.supermaxman.portimort.executors.PlayerTpDenyExecutor;
import me.supermaxman.portimort.executors.PlayerTpExecutor;
import me.supermaxman.portimort.executors.PlayerTpHereExecutor;
import me.supermaxman.portimort.executors.PlayerTpHereOverrideExecutor;
import me.supermaxman.portimort.executors.PlayerTpLightningExecutor;
import me.supermaxman.portimort.executors.PlayerTpLocationExecutor;
import me.supermaxman.portimort.executors.PlayerTpOverrideExecutor;
import me.supermaxman.portimort.executors.PlayerTpToggleExecutor;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Logger;

public class Portimort extends JavaPlugin {

    //Required
    public static Logger log;
    public static FileConfiguration conf;

    public static Permission permission = null;
    public static Chat chat = null;
    public static Portimort pi;
    public static final HashMap<String, Integer> tpstatus = new HashMap<String, Integer>();
    
    public static final HashMap<String, String> tprequests = new HashMap<String, String>();
	public static String px = ChatColor.AQUA+"[Portimort]: ";
	public static String pxe = ChatColor.AQUA+"[Portimort]: "+ChatColor.RED+"Error, ";
    
    @Override
    public void onDisable() {
        log.info("Disabled.");
        Utils.savecfg();
    }
    
    @Override
    public void onEnable() {
        conf = getConfig();
        pi = this;
        log = getLogger();
        conf = getConfig();
        if (!setupPermissions()) {
            log.severe("Vault fail!");
            this.setEnabled(false);
            return;
        }
        log.info("All systems go! Version:" + this.getDescription().getVersion());
        getCommand("tp").setExecutor(new PlayerTpExecutor(this));
        getCommand("teleport").setExecutor(new PlayerTpExecutor(this));
        
        getCommand("tptoggle").setExecutor(new PlayerTpToggleExecutor(this));    
        getCommand("tpt").setExecutor(new PlayerTpToggleExecutor(this));    
        getCommand("tpaccept").setExecutor(new PlayerTpAcceptExecutor(this));    
        getCommand("tpdeny").setExecutor(new PlayerTpDenyExecutor(this));    
        getCommand("tpo").setExecutor(new PlayerTpOverrideExecutor(this));    
        getCommand("tpl").setExecutor(new PlayerTpLightningExecutor(this));    
        getCommand("tploc").setExecutor(new PlayerTpLocationExecutor(this));    
        
        
        getCommand("tph").setExecutor(new PlayerTpHereExecutor(this));    
        getCommand("tphere").setExecutor(new PlayerTpHereExecutor(this));    
        getCommand("tpoh").setExecutor(new PlayerTpHereOverrideExecutor(this));    
        getCommand("tpohere").setExecutor(new PlayerTpHereOverrideExecutor(this));    
        Utils.setupCfg();
        
    }
    
    
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
            return true;
        }
        return false;
    }
    
    
    
    
    
    
    
}
