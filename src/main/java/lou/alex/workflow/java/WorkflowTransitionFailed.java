package lou.alex.workflow.java;

import java.util.Collection;
import java.util.Collections;

public class WorkflowTransitionFailed implements WorkflowTransitionResult {
    private final RequestWorkflowTransition request;
    private final WorkflowTransitionFailureReasonCode reasonCode;
    private final String reason;

    public WorkflowTransitionFailed(RequestWorkflowTransition request, WorkflowTransitionFailureReasonCode reasonCode, String reason) {
        this.request = request;
        this.reasonCode = reasonCode;
        this.reason = reason;
    }

    public RequestWorkflowTransition getRequest() {
        return request;
    }

    public WorkflowTransitionFailureReasonCode getReasonCode() {
        return reasonCode;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public Collection<Action> getActions() {
        return Collections.emptyList();
    }
}
