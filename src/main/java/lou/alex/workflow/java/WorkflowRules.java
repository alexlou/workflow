package lou.alex.workflow.java;

import java.util.Collection;

public interface WorkflowRules {
    Collection<String> getSupportedProducts();
    WorkflowTransitionResult transit(RequestWorkflowTransition request);
}
