package com.boardgame.engine.store;

import com.boardgame.engine.core.GameId;
import com.boardgame.engine.events.GameEvent;
import java.util.List;
import java.util.Optional;

/**
 * Interface for storing and retrieving game events.
 * Implementations can use PostgreSQL, Redis, or other storage.
 */
public interface EventStore {
    
    /**
     * Save a single event.
     */
    void saveEvent(GameEvent event);
    
    /**
     * Save multiple events atomically.
     */
    void saveEvents(List<GameEvent> events);
    
    /**
     * Get all events for a game.
     */
    List<GameEvent> getEvents(GameId gameId);
    
    /**
     * Get events from a specific sequence number onwards.
     */
    List<GameEvent> getEventsFrom(GameId gameId, int fromSequence);
    
    /**
     * Get the last event for a game.
     */
    Optional<GameEvent> getLastEvent(GameId gameId);
    
    /**
     * Get the highest sequence number for a game.
     */
    int getLastSequenceNumber(GameId gameId);
    
    /**
     * Delete all events for a game.
     */
    void deleteEvents(GameId gameId);
}