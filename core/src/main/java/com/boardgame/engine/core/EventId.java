package com.boardgame.engine.core;

import java.util.Objects;
import java.util.UUID;

/**
 * Immutable identifier for a game event.
 */
public final class EventId {
    private final String value;
    
    private EventId(String value) {
        this.value = value;
    }
    
    public static EventId create() {
        return new EventId(UUID.randomUUID().toString());
    }
    
    public static EventId fromString(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("EventId cannot be null or blank");
        }
        return new EventId(value);
    }
    
    public String getValue() {
        return value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventId eventId = (EventId) o;
        return Objects.equals(value, eventId.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    @Override
    public String toString() {
        return "EventId{" + value + '}';
    }
}