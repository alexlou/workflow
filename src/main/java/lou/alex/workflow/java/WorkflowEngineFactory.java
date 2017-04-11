package lou.alex.workflow.java;

/**
 * Created by Alex on 4/11/2017.
 */
public class WorkflowEngineFactory {
    public WorkflowEngine create() {
        WorkflowEngineImpl engine = new WorkflowEngineImpl();

        return engine;
    }
}
