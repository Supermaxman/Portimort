package me.supermaxman.portimort.executors;


import java.util.ArrayList;

import me.supermaxman.portimort.Portimort;
import me.supermaxman.portimort.Utils;

import org.bukkit.entity.Player;

public class PlayerTpAcceptExecutor extends BaseExecutor {
    public static final ArrayList<String> tphere = new ArrayList<String>();
    
    @Override
    protected void run(Player player, String[] args) {
    	if(args.length==0) {
        	Utils.handleAccept(player);
    	}else {
        	Utils.handleAccept(player, args[0].toLowerCase());
    	}
    }

    public PlayerTpAcceptExecutor(Portimort pi) {
        super(pi);
    }
}
