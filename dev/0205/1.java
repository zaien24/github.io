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

/////////////
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

//////
// Prototype 인터페이스
// 게임 캐릭터(게임 개발)
// 사용 이유
// 게임에서는 비슷한 능력을 가진 캐릭터나 몬스터를 빠르게 생성해야 하는 경우가 많음.
// 객체를 새로 생성하는 것보다 기존 캐릭터를 복제하는 것이 성능상 유리함.
// 동일한 능력을 가진 캐릭터를 여러 개 만들 때 유용함.
interface GameCharacter extends Cloneable {
    GameCharacter clone();
}

// Concreate Prototype (캐릭터)
class Warrior implements GameCharacter {
    private String name;
    private int power;

    public Warrior(String name, int power) {
        this.name = name;
        this.power = power;
    }

    @Override
    public GameCharacter clone() {
        return new Warrior(this.name, this.power);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }
}

// 캐릭터 관리 클래스 (PrototypeRegistry)
class CharacterRegistry {
    private Map<String, GameCharacter> characters = new HashMap<>();

    public void addCharacter(String key, GameCharacter character) {
        characters.put(key, character);
    }

    public GameCharacter getCharacter(String key) {
        return characters.get(key).clone();
    }
}

// 클라이언트 코드
public class GamePrototypeExample {
    public static void main(String[] args) {
        // 캐릭터 등록
        CharacterRegistry registry = new CharacterRegistry();
        registry.addCharacter("Warrior", new Warrior("Knight", 100));

        // 기존 캐릭터 복제
        Warrior clonedWarrior = (Warrior) registry.getCharacter("Warrior");
        clonedWarrior.setName("Dark Knight");

        System.out.println("Original: " + registry.getCharacter("Warrior").getName());
        System.out.println("Cloned: " + clonedWarrior.getName());
    }
}

//✅ 이점: 새로운 캐릭터를 추가할 때 new 키워드를 사용하지 않고 복제하여 성능을 최적화할 수 있음.



----
class DeepPrototype implements Cloneable {
    private String name;
    private NestedObject nestedObject;

    public DeepPrototype(String name, String nestedData) {
        this.name = name;
        this.nestedObject = new NestedObject(nestedData);
    }

    @Override
    public DeepPrototype clone() {
        return new DeepPrototype(this.name, this.nestedObject.getData());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNestedData(String data) {
        this.nestedObject.setData(data);
    }

    public String getName() {
        return name;
    }

    public String getNestedData() {
        return nestedObject.getData();
    }

    static class NestedObject {
        private String data;

        public NestedObject(String data) {
            this.data = data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }
}

// 클라이언트 코드
public class DeepPrototypeExample {
    public static void main(String[] args) {
        DeepPrototype original = new DeepPrototype("Original", "Nested Data");
        DeepPrototype cloned = original.clone();

        // 원본 수정
        original.setNestedData("Changed Nested Data");

        System.out.println("Original Nested Data: " + original.getNestedData());
        System.out.println("Cloned Nested Data: " + cloned.getNestedData());
    }
}
