package lou.alex.workflow;

import java.util.Collection;

/**
 * Created by Alex on 4/17/2017.
 */
public class ProductWorkflow {

    private final String productType;
    private final Collection<WorkflowRule> rules;

    public ProductWorkflow(String productType, Collection<WorkflowRule> rules) {
        this.productType = productType;
        this.rules = rules;
    }

    public RuleDecision apply(TradeContext trade) {
        for (WorkflowRule rule :  rules) {
            RuleDecision decision = rule.apply(trade);
            if (decision instanceof RuleNotApplicapable) {
                continue;
            }
            return decision;
        }
        return new RuleNotApplicapable();
    }

    public String getProductType() {
        return productType;
    }
}
