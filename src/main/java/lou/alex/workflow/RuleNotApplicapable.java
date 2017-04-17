package lou.alex.workflow;

/**
 * Created by Alex on 4/17/2017.
 */
public class RuleNotApplicapable implements RuleDecision {
    @Override
    public void perform() {

    }

    @Override
    public TradeState nextState() {
        return null;
    }

    @Override
    public boolean accepted() {
        return false;
    }
}
