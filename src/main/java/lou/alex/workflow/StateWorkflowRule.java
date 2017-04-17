package lou.alex.workflow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

public class StateWorkflowRule<E extends StellaEvent> implements WorkflowRule {

    private final TradeState state;
    private final Class<E> eventType;
    private final TradeState targetState;
    private final Collection<Function<TradeContext, ValidationResult>> validators = new ArrayList<>();
    private final Collection<Consumer<TradeContext>> actions = new ArrayList<>();

    public StateWorkflowRule(TradeState state, Class<E> eventType, TradeState targetState) {
        this.state = state;
        this.eventType = eventType;
        this.targetState = targetState;
    }

    public StateWorkflowRule<E> withValidators(Function<TradeContext, ValidationResult> ... vldtrs) {
        validators.addAll(Arrays.asList(vldtrs));
        return this;
    }

    public StateWorkflowRule<E> withActions(Consumer<TradeContext> ... acts) {
        actions.addAll(Arrays.asList(acts));
        return this;
    }


    @Override
    public boolean accepts(TradeContext trade) {
        return state.equals(trade.getCurrentState()) && eventType.isInstance(trade.getEvent());
    }

    @Override
    public RuleDecision apply(TradeContext trade) {
        if (!accepts(trade)) {
            return new RuleNotApplicapable();
        }

        for (Function<TradeContext, ValidationResult> validator: validators) {
            ValidationResult result = validator.apply(trade);
            if (result instanceof ValidationFailure) {
                return new RuleValidationFailure((ValidationFailure) result);
            }
        }
        return new RuleAdopted(trade, targetState, actions);
    }
}
