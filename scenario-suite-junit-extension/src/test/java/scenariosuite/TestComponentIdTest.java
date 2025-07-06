package scenariosuite;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestComponentIdTest {
    @Test
    void parse() {
        assertThat(TestComponentId.parse("A")).isEqualTo(TestComponentId.of("A"));
        assertThat(TestComponentId.parse("A_1")).isEqualTo(TestComponentId.of("A", 1));
        assertThat(TestComponentId.parse("A_1_b")).isEqualTo(TestComponentId.of("A", 1, "b"));

        assertThat(TestComponentId.parse("_1")).isEqualTo(TestComponentId.of(1));
        assertThat(TestComponentId.parse("_1_a")).isEqualTo(TestComponentId.of(1, "a"));
    }

    @Test
    void to_string() {
        assertThat(TestComponentId.Part.of("A").toString()).isEqualTo("A");
        assertThat(TestComponentId.Part.of(1).toString()).isEqualTo("1");

        assertThat(TestComponentId.of("A")).hasToString("A");
        assertThat(TestComponentId.of("A", 1)).hasToString("A.1");
        assertThat(TestComponentId.of("A", 1, "b")).hasToString("A.1.b");
    }
}
