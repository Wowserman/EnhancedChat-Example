package com.wowserman;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.wowserman.api.KeywordManager;
import com.wowserman.api.PopulateKeywordEvent;
import com.wowserman.api.SearchForKeywordEvent;

public class KeywordListener implements Listener {
	
	private static final long PLAYER_NAME_KEYWORD_ID = KeywordManager.createID("player-names");
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void search(SearchForKeywordEvent event) {
		for (Player player:Bukkit.getOnlinePlayers()) {
			if (event.containsCustomKeyword(player.getName())) {
				// Message contains a player's name. 
				// We need to say what the keyword is that we found.
				// We're saying what word in the message we identified as a keyword.
				event.setKeyword(player.getName());
				// We need to say what the keyword classifies as.
				// In other words, we're saying the keyword describes a Player's Name.
				event.setID(PLAYER_NAME_KEYWORD_ID); 
				// We need to say what we are talking about.
				// In this case we know we found a keyword that identifies a Player,
				// So now we're saying it's a Player named player.getName().
				event.setContext(player.getName());
			}
		}
	}

	@EventHandler
	public void populate(PopulateKeywordEvent event) {
		// Check if the Keyword's ID is classified as a Player's Username		
		if (event.equalsID(PLAYER_NAME_KEYWORD_ID )) {
			// The Keyword represents a Player's Username.
			// The Context should be a Player's name,
			// according to what we did in our SearchForKeywordEvent handler.
			// 
			// In commands of our PLAYER_NAME_KEYWORD_ID, 
			// we can use the placeholder '%context%' and it will replace to our context
			// when executed.
			// So the command would look something like this "/tpa %context%"
			// We could also just do "/tpa " + event.getContext();
			// Either one works, we're just using "/tpa %context%" as it's built into the API.
			event.addCommand("/tpa %context%");
			// Lets also add a hover-tip just to show a description of what will happen when clicked.
			event.addDescription("Click me to Teleport to %context%");
			event.addDescription("Performs:");
			event.addDescription("/tpa %context%");
		}
	}
}
