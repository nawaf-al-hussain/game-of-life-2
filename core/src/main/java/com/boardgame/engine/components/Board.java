package com.boardgame.engine.components;

import com.boardgame.engine.core.PlayerId;
import java.util.*;

/**
 * Generic game board implementation.
 * Can be extended for specific game types.
 */
public class Board {
    private final int size;
    private final List<Tile> tiles;
    private final Map<PlayerId, Integer> playerPositions;
    
    public Board(int size) {
        this.size = size;
        this.tiles = new ArrayList<>(size);
        this.playerPositions = new HashMap<>();
        initializeTiles();
    }
    
    private void initializeTiles() {
        for (int i = 0; i < size; i++) {
            tiles.add(new BasicTile(i, "Tile " + i, "Standard tile", BoardState.TileType.START));
        }
    }
    
    public int getSize() {
        return size;
    }
    
    public Tile getTile(int position) {
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("Invalid position: " + position);
        }
        return tiles.get(position);
    }
    
    public List<Tile> getTiles() {
        return Collections.unmodifiableList(tiles);
    }
    
    public void setTile(int position, Tile tile) {
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("Invalid position: " + position);
        }
        tiles.set(position, tile);
    }
    
    public int getPlayerPosition(PlayerId playerId) {
        return playerPositions.getOrDefault(playerId, 0);
    }
    
    public void setPlayerPosition(PlayerId playerId, int position) {
        // Clear previous position
        int currentPos = getPlayerPosition(playerId);
        if (currentPos >= 0 && currentPos < size) {
            tiles.get(currentPos).setOccupied(false);
        }
        
        // Set new position
        playerPositions.put(playerId, position);
        tiles.get(position).setOccupied(true);
    }
    
    public void movePlayer(PlayerId playerId, int steps) {
        int currentPos = getPlayerPosition(playerId);
        int newPos = (currentPos + steps) % size;
        setPlayerPosition(playerId, newPos);
    }
    
    public Map<PlayerId, Integer> getPlayerPositions() {
        return Collections.unmodifiableMap(playerPositions);
    }
    
    public List<Tile> getTilesByType(BoardState.TileType type) {
        return tiles.stream()
                .filter(tile -> tile.getType() == type)
                .toList();
    }
    
    /**
     * Basic tile implementation.
     */
    private static class BasicTile implements Tile {
        private final int position;
        private String name;
        private String description;
        private BoardState.TileType type;
        private boolean occupied;
        
        public BasicTile(int position, String name, String description, BoardState.TileType type) {
            this.position = position;
            this.name = name;
            this.description = description;
            this.type = type;
            this.occupied = false;
        }
        
        @Override
        public int getPosition() { return position; }
        
        @Override
        public String getName() { return name; }
        
        @Override
        public void setName(String name) { this.name = name; }
        
        @Override
        public String getDescription() { return description; }
        
        @Override
        public void setDescription(String description) { this.description = description; }
        
        @Override
        public BoardState.TileType getType() { return type; }
        
        @Override
        public void setType(BoardState.TileType type) { this.type = type; }
        
        @Override
        public boolean isOccupied() { return occupied; }
        
        @Override
        public void setOccupied(boolean occupied) { this.occupied = occupied; }
    }
}