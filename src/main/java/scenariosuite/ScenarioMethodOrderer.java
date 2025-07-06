package scenariosuite;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrdererContext;

import java.lang.reflect.Method;
import java.util.Comparator;

public class ScenarioMethodOrderer implements MethodOrderer {
    @Override
    public void orderMethods(MethodOrdererContext context) {
        context.getMethodDescriptors().sort(Comparator.comparing(methodDescriptor -> {
            final Method testMethod = methodDescriptor.getMethod();
            return TestComponentId.parse(testMethod.getName());
        }));
    }
}
