package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_table")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int productId;

    @Column(name = "product_name")
    String productName;

    @Column(name = "product_desc")
    String productDesc;

    @Column(name = "unit_price")
    double unitPrice;

    @Column(name = "unit_stock")
    double unitInStock;

    @Column(name = "product_avail")
    String productAvail;

    @Column(name = "product_url")
    String productUrl;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    Supplier supplier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productcategory_id")
    ProductCategory productCategory;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    List<OrderDetails> orderDetails = new ArrayList<>();

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getUnitInStock() {
        return unitInStock;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<model.OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<model.OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setUnitInStock(double unitInStock) {
        this.unitInStock = unitInStock;
    }

    public String getProductAvail() {
        return productAvail;
    }

    public void setProductAvail(String productAvail) {
        this.productAvail = productAvail;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }
}

