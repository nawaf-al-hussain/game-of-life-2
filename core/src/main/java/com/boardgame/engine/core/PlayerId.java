package com.boardgame.engine.core;

import java.util.Objects;
import java.util.UUID;

/**
 * Immutable identifier for a player.
 * Can be extended for different game types.
 */
public final class PlayerId {
    private final String value;
    
    private PlayerId(String value) {
        this.value = value;
    }
    
    public static PlayerId create() {
        return new PlayerId(UUID.randomUUID().toString());
    }
    
    public static PlayerId fromString(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("PlayerId cannot be null or blank");
        }
        return new PlayerId(value);
    }
    
    public String getValue() {
        return value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerId playerId = (PlayerId) o;
        return Objects.equals(value, playerId.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    @Override
    public String toString() {
        return "PlayerId{" + value + '}';
    }
}