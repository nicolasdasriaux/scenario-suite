package scenariosuite.example;

import scenariosuite.Scenario;
import scenariosuite.ScenarioGroup;
import scenariosuite.ScenarioSuite;
import scenariosuite.Step;

@ScenarioSuite("Scenario based tests")
public class SimpleScenarioSuiteTest {
    @Scenario("Scenario 1")
    class _1 {
        @Step("Start")
        void a_1() {
        }

        @Step("Process")
        void a_2() {
        }

        @Step("Stop")
        void b() {
        }
    }

    @Scenario("Scenario 2")
    class _2 {
        @Step("Start")
        void a() {
        }

        @Step("Process")
        void b() {
        }
    }

    @ScenarioGroup("Scenarios 3")
    class _3 {
        @Scenario("Scenario 3.1")
        class _3_1 {
            @Step("Process A")
            void a() {
            }

            @Step("Process B")
            void b() {
            }
        }

        @Scenario("Scenario 3.2")
        class _3_2 {
            @Step("Process A")
            void a() {
            }
        }
    }
}
