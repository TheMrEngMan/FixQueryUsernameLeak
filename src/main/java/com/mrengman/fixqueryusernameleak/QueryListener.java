package com.mrengman.fixqueryusernameleak;

import com.destroystokyo.paper.ClientOption;
import com.destroystokyo.paper.event.server.GS4QueryEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Objects;

public class QueryListener implements Listener {

    @EventHandler()
    public void PaperServerListQEvent(GS4QueryEvent event) {

        // Get the response that would've been sent
        GS4QueryEvent.QueryResponse response = event.getResponse();

        // For each player
        ArrayList<String> processedUsernames = new ArrayList<>();
        for(String username : response.getPlayers()) {
            try {
                // Check if they allow their username to be listed, if not replace it with "Anonymous Player"
                processedUsernames.add(Objects.requireNonNull(Bukkit.getPlayer(username)).getClientOption(ClientOption.ALLOW_SERVER_LISTINGS) ? username : "Anonymous Player");
            } catch (NullPointerException e) {
                // If something goes wrong (player object is null), fail safe
                processedUsernames.add("Anonymous Player");
            }
        }

        // Construct the new response copying all original values with processed usernames list
        GS4QueryEvent.QueryResponse processedResponse = GS4QueryEvent.QueryResponse.builder()
                .currentPlayers(response.getCurrentPlayers())
                .maxPlayers(response.getMaxPlayers())
                .serverVersion(response.getServerVersion())
                .gameVersion(response.getGameVersion())
                .hostname(response.getHostname())
                .port(response.getPort())
                .map(response.getMap())
                .motd(response.getMotd())
                .plugins(response.getPlugins())
                .players(processedUsernames)
                .build();

        // Apply the processed response
        event.setResponse(processedResponse);

    }

}
