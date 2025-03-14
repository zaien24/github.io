import lombok.Buidler;
import lombok.ToString;

@Buidler
@ToString
class Product {
    private String name;
    private int price;
    private String category;
}

public class LombokBuilderExample {
    public static void main(String[] args) {
        Product product = Product.builder()
                .name("Smartphone")
                .price(1200)
                .category("Electronics")
                .build();
    }
}