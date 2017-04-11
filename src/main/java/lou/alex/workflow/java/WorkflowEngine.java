package lou.alex.workflow.java;

public interface WorkflowEngine {
    WorkflowTransitionResult transit(RequestWorkflowTransition transitionRequest);
}
