package lab07;

import java.io.Serializable;

public class Sale implements Serializable {
    private static final long serialVersionUID = 1L;
    private String productId;
    private int quantity;
    
    public Sale() {
        super();
    }
    
    public Sale(String productId, int quantity) {
        super();
        this.productId = productId; // Corregido: antes decía "produc"
        this.quantity = quantity;
    }
    
    public String getProductId() {
        return productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}