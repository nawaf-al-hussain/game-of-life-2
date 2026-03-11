package com.boardgame.engine.core;

import java.util.Objects;
import java.util.UUID;

public final class CommandId {
    private final String value;
    
    private CommandId(String value) {
        this.value = value;
    }
    
    public static CommandId create() {
        return new CommandId(UUID.randomUUID().toString());
    }
    
    public static CommandId fromString(String value) {
        return new CommandId(value);
    }
    
    public String getValue() {
        return value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandId commandId = (CommandId) o;
        return Objects.equals(value, commandId.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}