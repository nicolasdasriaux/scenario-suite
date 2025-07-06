package scenariosuite;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(ScenarioExtension.class)
@TestMethodOrder(ScenarioMethodOrderer.class)
@DisplayNameGeneration(ScenarioDisplayNameGenerator.class)
@Nested
public @interface Scenario {
    String value();
}
