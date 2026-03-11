package com.boardgame.engine.components;

import com.boardgame.engine.core.PlayerId;
import com.boardgame.engine.economy.Money;
import java.util.*;

/**
 * Base player class - can be extended for specific games.
 */
public class Player {
    private final PlayerId id;
    private String name;
    private Color color;
    private Money money;
    private int position;
    private boolean isActive;
    private Map<String, Object> attributes;  // For game-specific extensions
    
    protected Player(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.color = builder.color;
        this.money = builder.money;
        this.position = builder.position;
        this.isActive = builder.isActive;
        this.attributes = new HashMap<>(builder.attributes);
    }
    
    public PlayerId getId() { return id; }
    public String getName() { return name; }
    public Color getColor() { return color; }
    public Money getMoney() { return money; }
    public int getPosition() { return position; }
    public boolean isActive() { return isActive; }
    
    public void addMoney(Money amount) {
        this.money = this.money.add(amount);
    }
    
    public void deductMoney(Money amount) {
        this.money = this.money.subtract(amount);
    }
    
    public void move(int steps, int boardSize) {
        if (steps < 0) {
            throw new IllegalArgumentException("Steps cannot be negative");
        }
        this.position = (this.position + steps) % boardSize;
    }
    
    public void setPosition(int position) {
        this.position = position;
    }
    
    public void setActive(boolean active) {
        this.isActive = active;
    }
    
    public void setAttribute(String key, Object value) {
        this.attributes.put(key, value);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getAttribute(String key, Class<T> type) {
        return (T) attributes.get(key);
    }
    
    public boolean hasAttribute(String key) {
        return attributes.containsKey(key);
    }
    
    public static Builder builder(PlayerId id, String name) {
        return new Builder(id, name);
    }
    
    public static class Builder {
        private final PlayerId id;
        private final String name;
        private Color color = Color.BLUE;
        private Money money = Money.zero();
        private int position = 0;
        private boolean isActive = true;
        private Map<String, Object> attributes = new HashMap<>();
        
        private Builder(PlayerId id, String name) {
            this.id = Objects.requireNonNull(id);
            this.name = Objects.requireNonNull(name);
        }
        
        public Builder color(Color color) {
            this.color = color;
            return this;
        }
        
        public Builder money(Money money) {
            this.money = money;
            return this;
        }
        
        public Builder position(int position) {
            this.position = position;
            return this;
        }
        
        public Builder active(boolean isActive) {
            this.isActive = isActive;
            return this;
        }
        
        public Builder attribute(String key, Object value) {
            this.attributes.put(key, value);
            return this;
        }
        
        public Player build() {
            return new Player(this);
        }
    }
    
    /**
     * Enum for player colors.
     */
    public enum Color {
        RED, BLUE, GREEN, YELLOW, ORANGE, PURPLE, PINK, BLACK, WHITE
    }
}