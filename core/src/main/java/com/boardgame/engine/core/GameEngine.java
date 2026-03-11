package com.boardgame.engine.core;

import com.boardgame.engine.components.Player;
import com.boardgame.engine.events.GameEvent;
import com.boardgame.engine.store.EventStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Central game engine that manages all games.
 * This is the entry point for the universal board game system.
 */
public class GameEngine {
    private static final Logger logger = LoggerFactory.getLogger(GameEngine.class);
    
    private final Map<GameId, Game> activeGames;
    private final Map<GameType, GameDefinition> gameDefinitions;
    private final EventStore eventStore;
    
    public GameEngine(EventStore eventStore) {
        this.activeGames = new HashMap<>();
        this.gameDefinitions = new HashMap<>();
        this.eventStore = Objects.requireNonNull(eventStore, "EventStore cannot be null");
    }
    
    /**
     * Register a game definition.
     */
    public void registerGame(GameDefinition definition) {
        Objects.requireNonNull(definition, "GameDefinition cannot be null");
        gameDefinitions.put(definition.getGameType(), definition);
        logger.info("Registered game type: {}", definition.getGameType());
    }
    
    /**
     * Check if a game type is supported.
     */
    public boolean supportsGameType(GameType type) {
        return gameDefinitions.containsKey(type);
    }
    
    /**
     * Create a new game.
     */
    public GameId createGame(GameType type, List<Player> players) {
        GameDefinition definition = gameDefinitions.get(type);
        if (definition == null) {
            throw new IllegalArgumentException("Unsupported game type: " + type);
        }
        
        if (players.size() < definition.getMinPlayers() || players.size() > definition.getMaxPlayers()) {
            throw new IllegalArgumentException(
                String.format("Player count %d not valid for %s. Min: %d, Max: %d",
                    players.size(), type, definition.getMinPlayers(), definition.getMaxPlayers())
            );
        }
        
        GameId gameId = GameId.create();
        Game game = definition.createGame(gameId, players);
        activeGames.put(gameId, game);
        
        logger.info("Created game {} of type {} with {} players", gameId, type, players.size());
        return gameId;
    }
    
    /**
     * Process a command for a game.
     */
    public List<GameEvent> processCommand(GameId gameId, Command command) {
        Game game = getGame(gameId);
        if (game == null) {
            throw new IllegalArgumentException("Game not found: " + gameId);
        }
        
        if (game.isFinished()) {
            throw new IllegalStateException("Game is already finished: " + gameId);
        }
        
        logger.debug("Processing command {} for game {}", command.getType(), gameId);
        
        List<GameEvent> events = game.processCommand(command);
        
        // Persist events
        eventStore.saveEvents(events);
        
        logger.debug("Generated {} events for game {}", events.size(), gameId);
        return events;
    }
    
    /**
     * Get a game by ID.
     */
    public Game getGame(GameId gameId) {
        return activeGames.get(gameId);
    }
    
    /**
     * Get all active games.
     */
    public Collection<Game> getActiveGames() {
        return Collections.unmodifiableCollection(activeGames.values());
    }
    
    /**
     * Replay a game from its event history.
     */
    public Game replayGame(GameId gameId, GameDefinition definition) {
        List<GameEvent> events = eventStore.getEvents(gameId);
        
        Game game = definition.createGame(gameId, new ArrayList<>());
        game.applyEvents(events);
        
        logger.info("Replayed game {} from {} events", gameId, events.size());
        return game;
    }
    
    /**
     * Remove a game from active games.
     */
    public void removeGame(GameId gameId) {
        Game removed = activeGames.remove(gameId);
        if (removed != null) {
            logger.info("Removed game {}", gameId);
        }
    }
    
    /**
     * Get available game types.
     */
    public Set<GameType> getAvailableGameTypes() {
        return Collections.unmodifiableSet(gameDefinitions.keySet());
    }
    
    /**
     * Get game definition for a type.
     */
    public Optional<GameDefinition> getGameDefinition(GameType type) {
        return Optional.ofNullable(gameDefinitions.get(type));
    }
}