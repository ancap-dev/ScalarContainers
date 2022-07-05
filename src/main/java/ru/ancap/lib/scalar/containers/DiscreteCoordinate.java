package ru.ancap.lib.scalar.containers;

import lombok.AllArgsConstructor;

public interface DiscreteCoordinate {

    Axis axis();
    Long coordinate();

    static DiscreteCoordinate of(Axis axis, Long coordinate) {
        return new Scalar(axis, coordinate);
    }

    @AllArgsConstructor
    class Scalar implements DiscreteCoordinate {

        private final Axis axis;
        private final Long coordinate;

        @Override
        public Axis axis() {
            return axis;
        }

        @Override
        public Long coordinate() {
            return coordinate;
        }
    }

}
