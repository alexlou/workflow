package lou.alex.workflow;

public class RuleValidationFailure implements RuleDecision {

    private final String reasonCode;
    private final String description;

    public RuleValidationFailure(ValidationFailure validationFailure){
        this.reasonCode = validationFailure.getReasonCode();
        this.description = validationFailure.getDescription();
    }

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

    public String getReasonCode() {
        return reasonCode;
    }

    public String getDescription() {
        return description;
    }
}
