package com.boardgame.engine.core;

import com.boardgame.engine.components.Player;
import java.util.List;

/**
 * Defines how to create and configure a specific game type.
 * Each game (Life2, Monopoly, etc.) provides an implementation.
 */
public interface GameDefinition {
    
    /**
     * Create a new game instance.
     */
    Game createGame(GameId gameId, List<Player> players);
    
    /**
     * Get the game type.
     */
    GameType getGameType();
    
    /**
     * Minimum number of players required.
     */
    int getMinPlayers();
    
    /**
     * Maximum number of players allowed.
     */
    int getMaxPlayers();
    
    /**
     * Get the game name.
     */
    String getName();
    
    /**
     * Get the game description.
     */
    String getDescription();
}