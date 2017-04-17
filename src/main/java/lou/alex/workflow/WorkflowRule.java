package lou.alex.workflow;

public interface WorkflowRule {
    boolean accepts(TradeContext trade);
    RuleDecision apply(TradeContext trade);
}
