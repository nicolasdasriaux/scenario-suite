package scenariosuite;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

public class ScenarioDisplayNameGenerator extends DisplayNameGenerator.Standard {
    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {
        final Optional<String> optionalScenarioTest = Optional.ofNullable(testClass.getAnnotation(ScenarioSuite.class))
                .map(ScenarioSuite::value);

        return optionalScenarioTest.
                orElseGet(() -> super.generateDisplayNameForClass(testClass));
    }

    @Override
    public String generateDisplayNameForNestedClass(List<Class<?>> enclosingInstanceTypes, Class<?> nestedClass) {
        final Optional<String> optionalScenario = Optional.ofNullable(nestedClass.getAnnotation(Scenario.class))
                .map(Scenario::value);

        final Optional<String> optionalScenarioGroup = Optional.ofNullable(nestedClass.getAnnotation(ScenarioGroup.class))
                .map(ScenarioGroup::value);

        String stepId = TestComponentId.parse(nestedClass.getSimpleName()).toString();

        final String step = optionalScenario
                .or(() -> optionalScenarioGroup)
                .orElseGet(() -> super.generateDisplayNameForNestedClass(enclosingInstanceTypes, nestedClass));

        return "[%s] %s".formatted(stepId, step);
    }

    @Override
    public String generateDisplayNameForMethod(List<Class<?>> enclosingInstanceTypes, Class<?> testClass, Method testMethod) {
        final Optional<String> optionalStep = Optional.ofNullable(testMethod.getAnnotation(Step.class))
                .map(Step::value);

        String stepId = TestComponentId.parse(testMethod.getName()).toString();

        final String step = optionalStep
                .orElseGet(() -> super.generateDisplayNameForMethod(enclosingInstanceTypes, testClass, testMethod));

        return "[%s] %s".formatted(stepId, step);
    }
}
