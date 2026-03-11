package com.boardgame.engine.events;

import com.boardgame.engine.core.EventId;
import com.boardgame.engine.core.GameId;
import com.boardgame.engine.core.PlayerId;
import java.time.Instant;

/**
 * Base interface for all game events.
 * Events represent facts that have occurred in the game.
 * They are immutable and serializable.
 */
public interface GameEvent {
    EventId getId();
    GameId getGameId();
    PlayerId getPlayerId();
    EventType getType();
    int getSequenceNumber();
    Instant getTimestamp();
    
    enum EventType {
        // Game lifecycle
        GAME_CREATED,
        GAME_STARTED,
        GAME_FINISHED,
        
        // Player actions
        DICE_ROLLED,
        PLAYER_MOVED,
        TURN_ENDED,
        
        // Life2 specific
        CAREER_CHOSEN,
        COLLEGE_CHOICE,
        PROMOTION_OFFERED,
        GRADUATION,
        PATH_CHOICE,
        MONEY_CHANGED,
        MARRIED,
        BABY_BORN,
        HOUSE_BOUGHT,
        CAR_BOUGHT,
        INSURANCE_BOUGHT,
        INSURANCE_OFFERED,
        INVESTMENT_MADE,
        INVESTMENT_OFFERED,
        CARD_DRAWN,
        RETIRED,
        DESCRIPTION
    }
}