package scenariosuite;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class ScenarioExtension implements TestWatcher, ExecutionCondition, BeforeAllCallback {
    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(ScenarioExtension.class);

    private static class FailureState {
        private boolean failed = false;

        public boolean isFailed() {
            return failed;
        }

        public void setFailed(boolean failed) {
            this.failed = failed;
        }
    }

    @Override
    public void beforeAll(ExtensionContext context) {
        final ExtensionContext.Store store = context.getStore(NAMESPACE);
        final FailureState failureState = store.getOrComputeIfAbsent("failure", k -> new FailureState(), FailureState.class);
        failureState.setFailed(false);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        final ExtensionContext.Store store = context.getStore(NAMESPACE);
        final FailureState failureState = store.get("failure", FailureState.class);
        failureState.setFailed(true);
    }

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        final ExtensionContext.Store store = context.getStore(NAMESPACE);
        final FailureState failureState = store.get("failure", FailureState.class);

        if (failureState != null && failureState.isFailed()) {
            return ConditionEvaluationResult.disabled("Previous step failed");
        } else {
            return ConditionEvaluationResult.enabled("Test passed");
        }
    }
}
