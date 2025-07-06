package scenariosuite;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record TestComponentId(List<Part> parts) implements Comparable<TestComponentId> {
    @Override
    public String toString() {
        return parts.stream()
                .map(Part::toString)
                .collect(Collectors.joining("."));
    }

    @Override
    public int compareTo(TestComponentId that) {
        final List<Part> thisParts = this.parts();
        final List<Part> thatParts = that.parts();
        int minSize = Math.min(thisParts.size(), thatParts.size());

        for (int i = 0; i < minSize; i++) {
            final int comparison = thisParts.get(i).compareTo(thatParts.get(i));

            if (comparison != 0) {
                return comparison;
            }
        }

        return Integer.compare(thisParts.size(), thatParts.size());
    }

    public static TestComponentId of(List<Part> parts) {
        return new TestComponentId(parts);
    }

    public static TestComponentId of(Part... parts) {
        return TestComponentId.of(Stream.of(parts).toList());
    }

    public static TestComponentId of(Object... parts) {
        return TestComponentId.of(Stream.of(parts).
                map(Part::of)
                .toList());
    }

    public static TestComponentId parse(String s) {
        final String[] items = s.split("_", -1);

        return TestComponentId.of(Arrays.stream(items)
                .filter(item -> !item.isEmpty())
                .map(Part::parse)
                .toList());
    }

    public sealed interface Part extends Comparable<Part> {
        default int compareTo(Part that) {
            if (this instanceof StringPart(String v1) && that instanceof StringPart(String v2)) {
                return v1.compareTo(v2);
            } else if (this instanceof IntPart(int v1) && that instanceof IntPart(int v2)) {
                return Integer.compare(v1, v2);
            } else if (this instanceof StringPart && that instanceof IntPart) {
                return 1;
            } else {
                return -1;
            }
        }

        static Part of(Object part) {
            if (part instanceof String s) {
                return StringPart.of(s);
            } else if (part instanceof Integer i) {
                return IntPart.of(i);
            } else {
                throw new IllegalArgumentException(part.getClass().getName());
            }
        }

        static Part parse(String s) {
            try {
                return IntPart.of(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                return StringPart.of(s);
            }
        }

        record StringPart(String value) implements Part {
            @Override
            public String toString() {
                return value;
            }

            public static StringPart of(String value) {
                return new StringPart(value);
            }
        }

        record IntPart(int part) implements Part {
            @Override
            public String toString() {
                return Integer.toString(part);
            }

            public static IntPart of(int part) {
                return new IntPart(part);
            }
        }
    }
}
