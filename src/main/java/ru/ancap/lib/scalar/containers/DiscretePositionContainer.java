package ru.ancap.lib.scalar.containers;

import ru.ancap.lib.iterator.nested.executor.NestedLoopExecutor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface DiscretePositionContainer {


    List<DiscretePosition> locationList();

    static DiscretePositionContainerBuilder builder() {
        return new DiscretePositionContainerBuilder.Scalar();
    }

    class Scalar implements DiscretePositionContainer {

        private final List<DiscreteRange> ranges;

        public Scalar(List<DiscreteRange> ranges) {
            this.ranges = ranges;
        }

        /**
         * @return Return's a list of all the possible positions in the container.
         */
        @Override
        public List<DiscretePosition> locationList() {
            List<DiscretePosition> locations = new ArrayList<>();
            List< Iterator<DiscreteCoordinate> > iterators = new ArrayList<>();
            for (DiscreteRange range : this.ranges) {
                iterators.add(range.iterator());
            }
            new NestedLoopExecutor<>(
                    iterators.listIterator(),
                    coordinates -> {
                        var builder = DiscretePosition.builder();
                        for (DiscreteCoordinate coordinate : coordinates) {
                            builder.add(coordinate.axis(), coordinate.coordinate());
                        }
                        locations.add(builder.build());
                    }
            ).run();
            return locations;
        }
    }

    interface DiscretePositionContainerBuilder {

        DiscretePositionContainerBuilder add(DiscreteRange range);
        DiscretePositionContainer build();

        class Scalar implements DiscretePositionContainerBuilder {

            private final List<DiscreteRange> ranges;

            public Scalar() {
                this.ranges = new ArrayList<>();
            }

            @Override
            public DiscretePositionContainerBuilder add(DiscreteRange range) {
                this.ranges.add(range);
                return this;
            }

            @Override
            public DiscretePositionContainer build() {
                return new DiscretePositionContainer.Scalar(List.copyOf(this.ranges));
            }
        }
    }
}
