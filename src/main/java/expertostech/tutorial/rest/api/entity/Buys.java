package expertostech.tutorial.rest.api.entity;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

public class Buys {
    private Integer id;
    private Integer idProduct;
    private Integer idClient;
    private Integer quantity;
    private Double price;
    private boolean sell;
    private boolean buy;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getIdProduct() {
        return idProduct;
    }
    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public boolean isSell() {
        return sell;
    }
    public void setSell(boolean sell) {
        this.sell = sell;
    }
    public boolean isBuy() {
        return buy;
    }
    public void setBuy(boolean buy) {
        this.buy = buy;
    }
    public Integer getIdClient() {
        return idClient;
    }
    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }
}
