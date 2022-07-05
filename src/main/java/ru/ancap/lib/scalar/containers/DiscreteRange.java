package ru.ancap.lib.scalar.containers;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public interface DiscreteRange extends Iterable<DiscreteCoordinate> {

    Axis axis();
    long start();
    long end();

    static DiscreteRange of(Axis axis, long start, long end) {
        return new Scalar(axis, start, end);
    }

    @AllArgsConstructor
    class Scalar implements DiscreteRange {

        private final Axis axis;
        private final long start;
        private final long end;

        @Override
        public Axis axis() {
            return axis;
        }

        @Override
        public long start() {
            return start;
        }

        @Override
        public long end() {
            return end;
        }

        @NotNull
        @Override
        public Iterator<DiscreteCoordinate> iterator() {
            final Long[] current = {null};
            return new Iterator<>() {
                @Override
                public boolean hasNext() {
                    return end == current[0];
                }

                @Override
                public DiscreteCoordinate next() {
                    if (current[0] == null) {
                        current[0] = start;
                        return DiscreteCoordinate.of(axis, start);
                    }
                    current[0]++;
                    return DiscreteCoordinate.of(axis, current[0]);
                }
            };
        }
    }
}
