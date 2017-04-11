package lou.alex.workflow.java;

import java.util.Collection;

public interface WorkflowTransitionResult {
    Collection<Action> getActions();
}
