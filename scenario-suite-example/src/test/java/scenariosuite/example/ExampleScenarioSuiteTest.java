package scenariosuite.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import scenariosuite.Scenario;
import scenariosuite.ScenarioGroup;
import scenariosuite.ScenarioSuite;
import scenariosuite.Step;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ScenarioSuite("Scenario based tests")
public class ExampleScenarioSuiteTest {
    @Scenario("Scenario 1")
    class _1 {
        @Step("Start") // Succès
        void a_1() {
        }

        @Step("Process") // ÉCHEC (assertion)
        void a_2() {
            assertThat(false).isTrue();
        }

        @Step("Stop") // Passé
        void b() {
        }
    }

    @Scenario("Scenario 2")
    class _2 {
        @Step("Start") // ÉCHEC (exception)
        void a() {
            throw new RuntimeException();
        }

        @Step("Process") // Passé
        void b() {
        }

        @Step("Stop") // Passé
        void c() {
        }
    }

    @ScenarioGroup("Scenarios 3")
    class _3 {
        // Base de scénario permettant de préserver de l'état entre les étapes
        class Base {
            List<String> messages;

            void reset() {
                messages = new ArrayList<>();
            }

            void sendMessage(String message) {
                messages.add(message);
            }

            List<String> sentMessages() {
                return messages;
            }

            @BeforeEach
            void beforeEach() {
                reset();
            }

            @AfterEach
            void afterEach() {
                System.out.printf("messages=%s%n", messages);
            }
        }

        @Scenario("Scenario 3.1")
        class _3_1 extends Base {
            @Step("Process A")
            void a() {
                sendMessage("A1");
                sendMessage("A2");
                sendMessage("A3");

                assertThat(sentMessages()).containsExactly("A1", "A2", "A3");
            }

            @Step("Process B")
            void b() {
                sendMessage("B1");
                sendMessage("B2");

                assertThat(sentMessages()).containsExactly("B1", "B2");
            }
        }

        @Scenario("Scenario 3.2")
        class _3_2 extends Base {
            @Step("Process A")
            void a() {
                sendMessage("A1");
                sendMessage("A2");
                sendMessage("A3");

                assertThat(sentMessages()).containsExactly("A1", "A2", "A3");
            }
        }
    }
}
