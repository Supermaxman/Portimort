package me.supermaxman.portimort.executors;


import me.supermaxman.portimort.Portimort;
import me.supermaxman.portimort.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerTpDenyExecutor extends BaseExecutor {
    @Override
    protected void run(Player player, String[] args) {
    	if(args.length==0) {
        	Utils.handleDeny(player);
    	}else {
        	Utils.handleDeny(player, args[0]);
    	}
    }

    public PlayerTpDenyExecutor(Portimort pi) {
        super(pi);
    }
}
