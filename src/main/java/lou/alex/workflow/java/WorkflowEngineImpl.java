package lou.alex.workflow.java;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Alex on 4/11/2017.
 */
public class WorkflowEngineImpl implements WorkflowEngine {
    private final ConcurrentMap<String, WorkflowRules> rules = new ConcurrentHashMap<>();


    @Override
    public WorkflowTransitionResult transit(RequestWorkflowTransition transitionRequest) {
        String productType = transitionRequest.getProductType();
        WorkflowRules rule = rules.get(productType);
        if (rule == null) {
            return new WorkflowTransitionFailed(transitionRequest, WorkflowTransitionFailureReasonCode.Product_Not_Supported,
                    String.format("Product %s is not supported", productType));
        }

        WorkflowTransitionResult result = rule.transit(transitionRequest);
        Collection<Action> actions = result.getActions();
        for (Action action: actions) {
            action.perform(result);
        }
        return result;
    }
}
