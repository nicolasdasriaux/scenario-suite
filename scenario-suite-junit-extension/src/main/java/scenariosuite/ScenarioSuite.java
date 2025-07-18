package scenariosuite;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.TestClassOrder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@TestClassOrder(ScenarioClassOrderer.class)
@DisplayNameGeneration(ScenarioDisplayNameGenerator.class)
public @interface ScenarioSuite {
    String value();
}
