package jadie.ticketorder.domain;

import org.springframework.util.ObjectUtils;

import java.util.List;

import static jadie.ticketorder.exception.OrderExceptionMessages.EMPTY_ORDER_LINES;
import static jadie.ticketorder.exception.OrderExceptionMessages.NOT_CHANGE_SHIPPING_INFO;

public class Order {
    private OrderState state;
    private ShippingInfo shippingInfo;
    private List<OrderLine> orderLines;

    public Order(List<OrderLine> orderLines) {
        setOrderLines(orderLines);
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
    }

    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
        if (ObjectUtils.isEmpty(orderLines)) {
            throw new IllegalStateException(EMPTY_ORDER_LINES);
        }
    }

    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        if (!state.isShippingChangeable()) {
            throw new IllegalStateException(NOT_CHANGE_SHIPPING_INFO);
        }
        this.shippingInfo = newShippingInfo;
    }

    public void changeShipped() {
        this.state = OrderState.SHIPPED;
    }
}