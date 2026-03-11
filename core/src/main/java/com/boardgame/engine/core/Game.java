package com.boardgame.engine.core;

import com.boardgame.engine.components.Player;
import com.boardgame.engine.events.GameEvent;
import java.util.List;
import java.util.Optional;

/**
 * Main game interface - every game must implement this.
 * This is the heart of the universal engine.
 */
public interface Game {
    GameId getId();
    GameState getCurrentState();
    List<GameEvent> processCommand(Command command);
    void applyEvents(List<GameEvent> events);
    Optional<PlayerId> getWinner();
    boolean isFinished();
    List<Player> getPlayers();
    GameType getGameType();
}