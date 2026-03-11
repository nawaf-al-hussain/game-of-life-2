package com.boardgame.engine.components;

import java.util.Objects;
import java.util.Random;

/**
 * Immutable value object representing a dice roll.
 */
public final class DiceRoll {
    private final int die1;
    private final int die2;
    private final int total;
    private final boolean isDouble;
    
    private DiceRoll(int die1, int die2) {
        if (die1 < 1 || die1 > 6 || die2 < 1 || die2 > 6) {
            throw new IllegalArgumentException("Dice values must be between 1 and 6");
        }
        this.die1 = die1;
        this.die2 = die2;
        this.total = die1 + die2;
        this.isDouble = die1 == die2;
    }
    
    /**
     * Roll two dice randomly.
     */
    public static DiceRoll roll() {
        Random random = new Random();
        return new DiceRoll(random.nextInt(6) + 1, random.nextInt(6) + 1);
    }
    
    /**
     * Roll with a specific random seed for testing.
     */
    public static DiceRoll roll(Random random) {
        return new DiceRoll(random.nextInt(6) + 1, random.nextInt(6) + 1);
    }
    
    /**
     * Create from specific values (for testing or replay).
     */
    public static DiceRoll of(int die1, int die2) {
        return new DiceRoll(die1, die2);
    }
    
    public int getDie1() {
        return die1;
    }
    
    public int getDie2() {
        return die2;
    }
    
    public int getTotal() {
        return total;
    }
    
    public boolean isDouble() {
        return isDouble;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiceRoll diceRoll = (DiceRoll) o;
        return die1 == diceRoll.die1 && die2 == diceRoll.die2;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(die1, die2);
    }
    
    @Override
    public String toString() {
        return "DiceRoll{" + die1 + " + " + die2 + " = " + total + (isDouble ? " (double!)" : "") + '}';
    }
}