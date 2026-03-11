package com.boardgame.engine.core;

import com.boardgame.engine.components.BoardState;
import java.util.Map;

/**
 * Immutable snapshot of game state at a point in time.
 * Used for replay and recovery.
 */
public interface GameState {
    GameId getGameId();
    int getTurnNumber();
    PlayerId getCurrentPlayerId();
    Map<PlayerId, PlayerState> getPlayerStates();
    BoardState getBoardState();
    long getVersion();  // For optimistic locking
    long getTimestamp();
}