package ru.ancap.lib.scalar.containers;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public interface Axis {

    Axis X = new Scalar("x");
    Axis Y = new Scalar("y");
    Axis Z = new Scalar("z");

    static Axis of(String axisName) {
        return Scalar.of(axisName);
    }

    class Scalar implements Axis {

        private final static Map<String, Axis> map;

        static {
            map = new HashMap<>();
        }

        // to support Axis#instance == Axis#instance
        public static Axis of(String axisName) {
            if (map.containsKey(axisName)) {
                return map.get(axisName);
            }
            Axis newAxis = new Scalar(axisName);
            map.put(axisName, newAxis);
            return newAxis;
        }

        private final String name;

        private Scalar(String axisName) {
            this.name = axisName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (! (o instanceof Scalar scalar)) return false;
            return name.equals(scalar.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
