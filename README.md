# Scenario Suite for JUnit 5

**Scenario Suite for JUnit 5** is a JUnit extension for **scenario-based testing**.

A **scenario suite** (a class annotated with `@ScenarioSuite`)
consists of **scenarios** (nested classes annotated with `@Scenario`)
composed of **steps** (methods annotated with `@Step`).

Scenarios can also be organized into potentially hierarchical **scenario groups**
(nested classes annotated with `@ScenarioGroup`).

The steps within a scenario are executed in an order conventionally defined by their method names.
Scenarios are also executed in a similar order based o class name.

If a **step fails**, the **subsequent steps** in that scenario are **skipped**.

Text labels are provided as parameters for each of the mentioned annotations
allowing legible display in IDEs such as IntelliJ

## Writing a scenario suite

```java
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

        @Step("Stop") // Sucesss
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

            @Step("Process B")// Success
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
```

The result in the IDE such as IntelliJ will look like the following.

![](success.png)

## Skipping steps after failure

Steps after a failure in a scenario will be skipped.

```java
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

        @Step("Stop") // Sucesss
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
```

The result in the IDE such as IntelliJ will look like the following.

![](skip-after-failure.png)
