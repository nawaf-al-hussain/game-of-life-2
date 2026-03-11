package com.boardgame.engine.core;

import java.util.Objects;
import java.util.UUID;

/**
 * Immutable identifier for a game instance.
 * Value object - no behavior, just identity.
 */
public final class GameId {
    private final String value;
    
    private GameId(String value) {
        this.value = value;
    }
    
    public static GameId create() {
        return new GameId(UUID.randomUUID().toString());
    }
    
    public static GameId fromString(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("GameId cannot be null or blank");
        }
        return new GameId(value);
    }
    
    public String getValue() {
        return value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameId gameId = (GameId) o;
        return Objects.equals(value, gameId.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    @Override
    public String toString() {
        return "GameId{" + value + '}';
    }
}