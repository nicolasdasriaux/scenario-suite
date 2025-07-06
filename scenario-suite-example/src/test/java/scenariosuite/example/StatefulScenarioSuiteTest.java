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
public class StatefulScenarioSuiteTest {
    @ScenarioGroup("Scenarios 3")
    class _1 {
        // A base scenario allowing to preserve state between steps
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
        class _1_1 extends Base {
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
        class _1_2 extends Base {
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
