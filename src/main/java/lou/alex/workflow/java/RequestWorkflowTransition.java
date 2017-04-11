package lou.alex.workflow.java;

public class RequestWorkflowTransition {
    private final TradeVersion prev;
    private final TradeVersion current;
    private final String productType;

    public RequestWorkflowTransition(TradeVersion prev, TradeVersion current, String productType) {
        this.prev = prev;
        this.current = current;
        this.productType = productType;
    }

    public TradeVersion getPrev() {
        return prev;
    }

    public TradeVersion getCurrent() {
        return current;
    }

    public String getProductType() {
        return productType;
    }
}
