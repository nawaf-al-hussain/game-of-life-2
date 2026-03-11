package com.boardgame.engine.economy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Immutable money value object.
 * All operations return new instances.
 * No negative money allowed (debt is tracked separately).
 */
public final class Money implements Comparable<Money> {
    private final BigDecimal amount;
    private final CurrencyType currency;
    
    private Money(BigDecimal amount, CurrencyType currency) {
        this.amount = amount.setScale(2, RoundingMode.HALF_EVEN);
        this.currency = currency;
    }
    
    public static Money of(int amount) {
        return new Money(BigDecimal.valueOf(amount), CurrencyType.DOLLAR);
    }
    
    public static Money of(double amount) {
        return new Money(BigDecimal.valueOf(amount), CurrencyType.DOLLAR);
    }
    
    public static Money of(BigDecimal amount) {
        return new Money(amount, CurrencyType.DOLLAR);
    }
    
    public static Money zero() {
        return new Money(BigDecimal.ZERO, CurrencyType.DOLLAR);
    }
    
    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot add different currencies");
        }
        return new Money(this.amount.add(other.amount), this.currency);
    }
    
    public Money subtract(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot subtract different currencies");
        }
        if (this.amount.compareTo(other.amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds: " + this + " - " + other);
        }
        return new Money(this.amount.subtract(other.amount), this.currency);
    }
    
    public Money multiply(int multiplier) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(multiplier)), this.currency);
    }
    
    public Money multiply(double multiplier) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(multiplier)), this.currency);
    }
    
    public Money percentage(double percent) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(percent / 100.0)), this.currency);
    }
    
    public boolean isGreaterThan(Money other) {
        return this.amount.compareTo(other.amount) > 0;
    }
    
    public boolean isLessThan(Money other) {
        return this.amount.compareTo(other.amount) < 0;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public CurrencyType getCurrency() {
        return currency;
    }
    
    @Override
    public int compareTo(Money other) {
        return this.amount.compareTo(other.amount);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount) && currency == money.currency;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
    
    @Override
    public String toString() {
        return currency.getSymbol() + String.format("%,.2f", amount);
    }
    
    public enum CurrencyType {
        DOLLAR("$"),
        EURO("€"),
        POUND("£"),
        YEN("¥"),
        COIN("🪙");
        
        private final String symbol;
        
        CurrencyType(String symbol) {
            this.symbol = symbol;
        }
        
        public String getSymbol() {
            return symbol;
        }
    }
}