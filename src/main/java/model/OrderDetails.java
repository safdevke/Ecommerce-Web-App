package model;

import javax.persistence.*;

@Entity
@Table(name = "order_details_table")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int orderId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;
}
