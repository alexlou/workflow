package lou.alex.workflow;

/**
 * Created by Alex on 4/17/2017.
 */
public interface RuleDecision {
    void perform();
    TradeState nextState();
    boolean accepted();
}
