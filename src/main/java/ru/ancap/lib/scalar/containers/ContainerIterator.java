package ru.ancap.lib.scalar.containers;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;


@RequiredArgsConstructor
public class ContainerIterator<T> implements Iterable<T> {

    private final DiscretePositionContainer space;
    private final DiscretePositionConsumer<T> consumer;

    @NotNull
    @Override
    public Iterator<T> iterator() {
        Iterator<DiscretePosition> locationIterator = this.space.locationList().iterator();
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return locationIterator.hasNext();
            }

            @Override
            public T next() {
                return consumer.forThe(locationIterator.next());
            }
        };
    }
}
