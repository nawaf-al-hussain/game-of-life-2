package com.boardgame.engine.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameIdTest {

    @Test
    void testCreateGeneratesUniqueIds() {
        GameId id1 = GameId.create();
        GameId id2 = GameId.create();
        assertNotEquals(id1, id2);
    }

    @Test
    void testFromString() {
        String idValue = "test-game-123";
        GameId id = GameId.fromString(idValue);
        assertEquals(idValue, id.getValue());
    }

    @Test
    void testFromStringNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> GameId.fromString(null));
        assertThrows(IllegalArgumentException.class, () -> GameId.fromString(""));
        assertThrows(IllegalArgumentException.class, () -> GameId.fromString("   "));
    }
}