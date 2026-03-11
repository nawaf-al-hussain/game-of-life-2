package com.boardgame.engine;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.boardgame.engine.components.DiceRoll;

class DiceRollTest {

    @Test
    void testRollProducesValidValues() {
        // Roll dice multiple times to ensure values are in range
        for (int i = 0; i < 100; i++) {
            DiceRoll roll = DiceRoll.roll();
            assertTrue(roll.getDie1() >= 1 && roll.getDie1() <= 6);
            assertTrue(roll.getDie2() >= 1 && roll.getDie2() <= 6);
            assertTrue(roll.getTotal() >= 2 && roll.getTotal() <= 12);
        }
    }

    @Test
    void testDiceRollOf() {
        DiceRoll roll = DiceRoll.of(3, 4);
        assertEquals(3, roll.getDie1());
        assertEquals(4, roll.getDie2());
        assertEquals(7, roll.getTotal());
        assertFalse(roll.isDouble());
    }

    @Test
    void testDiceRollDouble() {
        DiceRoll roll = DiceRoll.of(5, 5);
        assertEquals(10, roll.getTotal());
        assertTrue(roll.isDouble());
    }

    @Test
    void testDiceRollInvalidValues() {
        assertThrows(IllegalArgumentException.class, () -> DiceRoll.of(0, 3));
        assertThrows(IllegalArgumentException.class, () -> DiceRoll.of(7, 3));
        assertThrows(IllegalArgumentException.class, () -> DiceRoll.of(3, -1));
    }

    @Test
    void testDiceRollEquality() {
        DiceRoll roll1 = DiceRoll.of(3, 4);
        DiceRoll roll2 = DiceRoll.of(3, 4);
        DiceRoll roll3 = DiceRoll.of(4, 3);

        assertEquals(roll1, roll2);
        assertNotEquals(roll1, roll3);
    }
}