package com.wowserman;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin {
	@Override
	public void onEnable() {
		// Register our KeywordListener 
		Bukkit.getPluginManager().registerEvents(new KeywordListener(), this);
	}
}
