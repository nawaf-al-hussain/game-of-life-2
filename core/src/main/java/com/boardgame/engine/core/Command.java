package com.boardgame.engine.core;

import java.time.Instant;

/**
 * A command represents a player's intention to perform an action.
 * Commands are validated and may produce events.
 */
public interface Command {
    CommandId getId();
    GameId getGameId();
    PlayerId getPlayerId();
    CommandType getType();
    Instant getTimestamp();
    
    // Optional payload for command data
    default Object getPayload() {
        return null;
    }
    
    // Optional additional payload
    default Object getAdditionalPayload() {
        return null;
    }
    
    // Validation occurs before processing
    default boolean requiresCurrentPlayer() {
        return true;
    }
    
    enum CommandType {
        ROLL_DICE,
        CHOOSE_CAREER,
        BUY_ASSET,
        SELL_ASSET,
        END_TURN,
        DRAW_CARD,
        GET_MARRIED,
        HAVE_CHILD,
        FILE_TAXES,
        TAKE_LOAN,
        PAY_LOAN,
        BUY_INSURANCE,
        INVEST,
        PROMOTE,
        DIVORCE,
        RETIRE,
        CHOOSE_PATH,
        RETIRE_CHOICE,
        BUCKET_LIST
    }
}