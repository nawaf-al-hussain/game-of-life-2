package com.boardgame.engine.components;

/**
 * Represents a tile on the game board.
 */
public interface Tile {
    int getPosition();
    String getName();
    void setName(String name);
    String getDescription();
    void setDescription(String description);
    BoardState.TileType getType();
    void setType(BoardState.TileType type);
    boolean isOccupied();
    void setOccupied(boolean occupied);
}