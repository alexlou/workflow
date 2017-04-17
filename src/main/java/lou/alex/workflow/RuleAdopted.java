package lou.alex.workflow;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * Created by Alex on 4/17/2017.
 */
public class RuleAdopted implements RuleDecision {

    private final TradeContext trade;
    private final TradeState targetState;
    private final Collection<Consumer<TradeContext>> actions;

    public RuleAdopted(TradeContext trade, TradeState targetState, Collection<Consumer<TradeContext>> actions) {
        this.trade = trade;
        this.targetState = targetState;
        this.actions = actions;
    }

    @Override
    public void perform() {
        for (Consumer<TradeContext> action : actions) {
            action.accept(trade);
        }
    }

    @Override
    public TradeState nextState() {
        return targetState;
    }

    @Override
    public boolean accepted() {
        return true;
    }
}
