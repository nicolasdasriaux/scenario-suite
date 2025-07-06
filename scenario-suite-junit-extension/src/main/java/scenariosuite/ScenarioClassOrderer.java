package scenariosuite;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.ClassOrdererContext;

import java.util.Comparator;

public class ScenarioClassOrderer implements ClassOrderer {
    @Override
    public void orderClasses(ClassOrdererContext context) {
        context.getClassDescriptors().sort(Comparator.comparing(classDescriptor -> {
            final Class<?> testClass = classDescriptor.getTestClass();
            return TestComponentId.parse(testClass.getSimpleName());
        }));
    }
}
