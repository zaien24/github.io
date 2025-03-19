// 1. Component (기본 인터페이스)
interface Coffee {
    String getDescription();
    double cost();
}

// 2. ConcreteComponent (구체적인 컴포넌트 - 기본 커피)
class BasicCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Basic Coffee";
    }

    @Override
    public double cost() {
        return 5.0;
    }
}

// 3. Decorator (추상 데코레이터)
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public double cost() {
        return coffee.cost();
    }
}

// 4. ConcreteDecorator (구체적인 데코레이터 - 우유 추가)
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    @Override
    public double cost() {
        return coffee.cost() + 1.5;
    }
}

// 5. ConcreteDecorator (구체적인 데코레이터 - 설탕 추가)
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public double cost() {
        return coffee.cost() + 0.5;
    }
}

// 6. 클라이언트 코드
public class DecoratorPatternExample {
    public static void main(String[] args) {
        // 기본 커피 주문
        Coffee basicCoffee = new BasicCoffee();
        System.out.println(basicCoffee.getDescription() + " -> $" + basicCoffee.cost());

        // 우유 추가
        Coffee milkCoffee = new MilkDecorator(basicCoffee);
        System.out.println(milkCoffee.getDescription() + " -> $" + milkCoffee.cost());

        // 설탕 추가
        Coffee sugarMilkCoffee = new SugarDecorator(milkCoffee);
        System.out.println(sugarMilkCoffee.getDescription() + " -> $" + sugarMilkCoffee.cost());
    }
}
