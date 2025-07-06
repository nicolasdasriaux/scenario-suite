package scenariosuite.example;

import scenariosuite.Scenario;
import scenariosuite.ScenarioGroup;
import scenariosuite.ScenarioSuite;
import scenariosuite.Step;

import static org.assertj.core.api.Assertions.*;

@ScenarioSuite("Scenario based tests")
public class SimpleFailingScenarioSuiteTest {
    @Scenario("Scenario 1")
    class _1 {
        @Step("Start")
        void a_1() {
        }

        @Step("Process")
        void a_2() {
            assertThat(false).isTrue();
        }

        @Step("Stop")
        void b() {
        }
    }

    @Scenario("Scenario 2")
    class _2 {
        @Step("Start")
        void a() {
            throw new RuntimeException();
        }

        @Step("Process")
        void b() {
        }

        @Step("Stop")
        void c() {
        }
    }
}

