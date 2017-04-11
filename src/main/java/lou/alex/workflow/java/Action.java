package lou.alex.workflow.java;

public interface Action {
    void perform(WorkflowTransitionResult context);
}
