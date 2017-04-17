package lou.alex.workflow;

/**
 * Created by Alex on 4/17/2017.
 */
public class ValidationFailure implements ValidationResult {
    private final String reasonCode;
    private final String description;

    public ValidationFailure(String reasonCode, String description) {
        this.reasonCode = reasonCode;
        this.description = description;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public String getDescription() {
        return description;
    }
}
