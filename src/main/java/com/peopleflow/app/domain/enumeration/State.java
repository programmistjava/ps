package com.peopleflow.app.domain.enumeration;

import java.util.Optional;

/**
 * The State enumeration.
 */
public enum State {
    ADDED("ADDED"), IN_CHECK("IN-CHECK"), APPROVED("APPROVED"), ACTIVE("ACTIVE");

    private String value;

    State(String value) {
        this.value = value;
    }

    public String getState() {
        return value;
    }

    public static Optional<State> getState(String value) {
        for (State val : State.values()) {
            if (val.value.equals(value)) {
                return Optional.of(val);
            }
        }
        return Optional.empty();
    }
}
