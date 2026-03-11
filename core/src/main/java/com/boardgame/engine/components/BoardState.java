package com.boardgame.engine.components;

import com.boardgame.engine.core.PlayerId;
import java.util.List;
import java.util.Map;

/**
 * Immutable snapshot of the board state.
 */
public interface BoardState {
    int getSize();
    Map<Integer, Tile> getTiles();
    Map<PlayerId, Integer> getPlayerPositions();
    List<Tile> getSpecialTiles(TileType type);
    
    enum TileType {
        START, NORMAL, PAYDAY, CAREER, CAREER_CHANGE, COLLEGE_CHOICE,
        MARRIAGE, BABY, BUY_HOUSE, BUY_CAR, TAX, ACTION, 
        INSURANCE, STOCK_MARKET, RETIREMENT, ATTRIBUTE,
        FORK_CHOICE, INVESTMENT, LOAN, NIGHT_SCHOOL, GRADUATION, 
        PET, VACATION, BUCKET_LIST, LIFE_EVENT, SPIN_SPACE,
        HOUSE_SELL, LOAN_REPAY, RETIREMENT_SPIN, BONUS_COLLECTION,
        ALL_PLAYERS_SPIN, LIFE_SUMMARY
    }
}