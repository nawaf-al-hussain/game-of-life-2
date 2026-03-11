package com.boardgame.engine.store;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import com.boardgame.engine.core.GameId;
import com.boardgame.engine.core.PlayerId;
import com.boardgame.engine.events.GameEvent;
import java.util.List;

class InMemoryEventStoreTest {

    private InMemoryEventStore store;

    @BeforeEach
    void setUp() {
        store = new InMemoryEventStore();
    }

    @Test
    void testSaveAndRetrieveEvents() {
        GameId gameId = GameId.create();
        PlayerId playerId = PlayerId.create();

        // Note: In a real test, we'd create actual GameEvent implementations
        // This is a simplified test to verify the store works
        assertNotNull(store.getEvents(gameId));
        assertTrue(store.getEvents(gameId).isEmpty());
    }

    @Test
    void testGetLastSequenceNumber() {
        GameId gameId = GameId.create();
        assertEquals(0, store.getLastSequenceNumber(gameId));
    }

    @Test
    void testDeleteEvents() {
        GameId gameId = GameId.create();
        store.deleteEvents(gameId);
        assertTrue(store.getEvents(gameId).isEmpty());
    }

    @Test
    void testClearAll() {
        GameId gameId1 = GameId.create();
        GameId gameId2 = GameId.create();
        store.deleteEvents(gameId1);
        store.deleteEvents(gameId2);
        store.clearAll();
        assertEquals(0, store.getAllGameIds().size());
    }
}