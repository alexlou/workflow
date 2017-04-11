package lou.alex.workflow.java;

/**
 * Created by Alex on 4/11/2017.
 */
public interface ProductWorkflowRules<P> extends WorkflowRules {
    default WorkflowTransitionResult transit(RequestWorkflowTransition request) {
        return transit(request.getStatus(), request.getCurrent(), getProduct(request.getCurrent()));
    }

    default P getProduct(TradeVersion trade) {
        return null;
    }

    WorkflowTransitionResult transit(TradeStatus status, TradeVersion trade, P product);
}
