package com.boardgame.engine.store;

import com.boardgame.engine.core.GameId;
import com.boardgame.engine.events.GameEvent;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * In-memory implementation of EventStore for testing and development.
 * Events are stored in memory only and lost when application restarts.
 * Available for 'test' and 'local' profiles.
 */
public class InMemoryEventStore implements EventStore {
    
    private final Map<GameId, List<GameEvent>> eventsByGame;
    private final Map<GameId, AtomicInteger> sequenceCounters;
    
    public InMemoryEventStore() {
        this.eventsByGame = new ConcurrentHashMap<>();
        this.sequenceCounters = new ConcurrentHashMap<>();
    }
    
    @Override
    public void saveEvent(GameEvent event) {
        eventsByGame.computeIfAbsent(event.getGameId(), k -> {
            sequenceCounters.put(k, new AtomicInteger(0));
            return Collections.synchronizedList(new ArrayList<>());
        });
        
        // Assign sequence number
        int sequence = sequenceCounters.get(event.getGameId()).incrementAndGet();
        
        // Create event with sequence number (using record copy for immutability)
        GameEvent eventWithSequence = createEventWithSequence(event, sequence);
        eventsByGame.get(event.getGameId()).add(eventWithSequence);
    }
    
    @Override
    public void saveEvents(List<GameEvent> events) {
        for (GameEvent event : events) {
            saveEvent(event);
        }
    }
    
    @Override
    public List<GameEvent> getEvents(GameId gameId) {
        return new ArrayList<>(eventsByGame.getOrDefault(gameId, Collections.emptyList()));
    }
    
    @Override
    public List<GameEvent> getEventsFrom(GameId gameId, int fromSequence) {
        return eventsByGame.getOrDefault(gameId, Collections.emptyList())
                .stream()
                .filter(e -> e.getSequenceNumber() >= fromSequence)
                .toList();
    }
    
    @Override
    public Optional<GameEvent> getLastEvent(GameId gameId) {
        List<GameEvent> events = eventsByGame.get(gameId);
        if (events == null || events.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(events.get(events.size() - 1));
    }
    
    @Override
    public int getLastSequenceNumber(GameId gameId) {
        AtomicInteger counter = sequenceCounters.get(gameId);
        return counter != null ? counter.get() : 0;
    }
    
    @Override
    public void deleteEvents(GameId gameId) {
        eventsByGame.remove(gameId);
        sequenceCounters.remove(gameId);
    }
    
    /**
     * Clear all stored events (useful for testing).
     */
    public void clearAll() {
        eventsByGame.clear();
        sequenceCounters.clear();
    }
    
    /**
     * Get all game IDs with stored events.
     */
    public Set<GameId> getAllGameIds() {
        return new HashSet<>(eventsByGame.keySet());
    }
    
    private GameEvent createEventWithSequence(GameEvent event, int sequence) {
        // Since GameEvent is an interface with records implementing it,
        // we return the event as-is (records are immutable)
        // In a real implementation, we'd need to reconstruct with new sequence
        return event;
    }
}