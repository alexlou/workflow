package lou.alex.workflow;

import java.util.HashMap;
import java.util.Map;

public class WorkflowEngine {
    private final Map<String, ProductWorkflow> workflows = new HashMap<>();

    public RuleDecision apply(TradeContext trade) {
        String productType = trade.getProductType();
        ProductWorkflow workflow = this.workflows.get(productType);
        if (workflow == null) {
            return new RuleNotApplicapable();
        }
        return workflow.apply(trade);
    }

    public WorkflowEngine accepts(ProductWorkflow workflow) {
        if (workflows.containsKey(workflow.getProductType())) {
            throw new IllegalStateException("Product Workflow Already exists: " + workflow.getProductType());
        }
        workflows.put(workflow.getProductType(), workflow);
        return this;
    }
}
