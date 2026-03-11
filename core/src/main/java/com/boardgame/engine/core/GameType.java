package com.boardgame.engine.core;

/**
 * Enum representing all supported game types.
 * New games can be added here.
 */
public enum GameType {
    LIFE2("The Game of Life 2", "Classic life simulation board game"),
    MONOPOLY("Monopoly", "Property trading game"),
    CLUE("Clue", "Murder mystery game"),
    CUSTOM("Custom", "User-defined game");
    
    private final String displayName;
    private final String description;
    
    GameType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
}