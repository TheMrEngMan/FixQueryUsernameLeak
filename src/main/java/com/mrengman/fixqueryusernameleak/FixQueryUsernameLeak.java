package com.mrengman.fixqueryusernameleak;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FixQueryUsernameLeak extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register the listener to do the thing
        Bukkit.getPluginManager().registerEvents(new QueryListener(), this);
    }

}
