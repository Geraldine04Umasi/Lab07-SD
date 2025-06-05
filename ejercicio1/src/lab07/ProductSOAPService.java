package lab07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class ProductSOAPService {
    private static List<Product> products = new ArrayList<>();
    private static List<Sale> sales = new ArrayList<>();
    
    @WebMethod
    public List<Product> getProducts() {
        return products;
    }
    
    @WebMethod
    public void addProduct(Product product) {
        products.add(product);
    }
    
    @WebMethod
    public List<Sale> getSales() {
        return sales;
    }
    
    @WebMethod
    public void makeSale(Sale sale) {
        sales.add(sale);
    }
    
    @WebMethod
    public void init() {
        products.addAll(Arrays.asList(
            new Product("1", "Product A", 100.0),
            new Product("2", "Product B", 150.0),
            new Product("3", "Product C", 200.0)
        ));
    }
}