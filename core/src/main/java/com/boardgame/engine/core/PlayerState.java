package com.boardgame.engine.core;

/**
 * Immutable snapshot of a player's state.
 */
public interface PlayerState {
    PlayerId getPlayerId();
    String getName();
    int getPosition();
    com.boardgame.engine.economy.Money getMoney();
    boolean isActive();
    int getTurnOrder();
}