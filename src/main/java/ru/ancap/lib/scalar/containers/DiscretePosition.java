package ru.ancap.lib.scalar.containers;

import java.util.HashMap;
import java.util.Map;

public interface DiscretePosition {

    long coordinate(Axis axis);

    static DiscretePositionBuilder builder() {
        return new DiscretePositionBuilder.Scalar();
    }

    class Scalar implements DiscretePosition {

        private final Map<Axis, Long> coordinate;

        public Scalar(Map<Axis, Long> map) {
            this.coordinate = map;
        }

        @Override
        public long coordinate(Axis axis) {
            return this.coordinate.getOrDefault(axis, 0L);
        }
    }

    interface DiscretePositionBuilder {

        DiscretePositionBuilder add(Axis axis, long coordinate);
        DiscretePosition build();

        class Scalar implements DiscretePositionBuilder {

            private final Map<Axis, Long> map;

            public Scalar() {
                this.map = new HashMap<>();
            }

            @Override
            public DiscretePositionBuilder add(Axis axis, long coordinate) {
                this.map.put(axis, coordinate);
                return this;
            }

            @Override
            public DiscretePosition build() {
                return new DiscretePosition.Scalar(Map.copyOf(map));
            }
        }

    }
}
