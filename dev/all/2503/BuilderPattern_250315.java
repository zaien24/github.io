// Builder pattern
// Product 클래스 ( 빌더 패턴 적용)
class Product {
    private final String name;
    private final int price;
    private final String category;

    // private 생성자 (Builder 내부에서만 생성 가능)
    private Product(Builder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.category = builder.category;
    }

    // Builder 클래스 정의
    public static class Builder {
        private String name;
        private int price;
        private String category;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPrice(int price) {
            this.price = price;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + ", category='" + category + "'}";
    }
}

// 빌더 패턴 사용
public class BuilderPatternExample {
    public static void main(String[] args) {
        Product product = new Product.Builder()
                .setName("Laptop")
                .setPrice(1500)
                .setCategory("Electronics")
                .build();
    }
}
