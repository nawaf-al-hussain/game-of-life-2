package com.boardgame.engine.economy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

class MoneyTest {

    @Test
    void testCreateMoney() {
        Money m = Money.of(100);
        assertEquals(new BigDecimal("100.00"), m.getAmount());
        assertEquals(Money.CurrencyType.DOLLAR, m.getCurrency());
    }

    @Test
    void testZeroMoney() {
        Money m = Money.zero();
        assertEquals(BigDecimal.ZERO.setScale(2), m.getAmount());
    }

    @Test
    void testAdd() {
        Money m1 = Money.of(50);
        Money m2 = Money.of(75);
        Money result = m1.add(m2);
        assertEquals(new BigDecimal("125.00"), result.getAmount());
    }

    @Test
    void testSubtract() {
        Money m1 = Money.of(100);
        Money m2 = Money.of(30);
        Money result = m1.subtract(m2);
        assertEquals(new BigDecimal("70.00"), result.getAmount());
    }

    @Test
    void testSubtractThrowsOnInsufficientFunds() {
        Money m1 = Money.of(30);
        Money m2 = Money.of(100);
        assertThrows(IllegalArgumentException.class, () -> m1.subtract(m2));
    }

    @Test
    void testMultiply() {
        Money m = Money.of(50);
        Money result = m.multiply(3);
        assertEquals(new BigDecimal("150.00"), result.getAmount());
    }

    @Test
    void testPercentage() {
        Money m = Money.of(100);
        Money result = m.percentage(25);
        assertEquals(new BigDecimal("25.00"), result.getAmount());
    }

    @Test
    void testComparison() {
        Money m1 = Money.of(100);
        Money m2 = Money.of(50);
        Money m3 = Money.of(100);

        assertTrue(m1.isGreaterThan(m2));
        assertTrue(m2.isLessThan(m1));
        assertFalse(m1.isGreaterThan(m3));
    }

    @Test
    void testToString() {
        Money m = Money.of(1234);
        assertEquals("$1,234.00", m.toString());
    }
}