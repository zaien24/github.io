// 인터프리터 패턴 설명

// 시나라오 룰 엔진

// 1. Expression 인터페이스
public interface Expression {
    boolean interpret(Customer custmer);
}

// 2. Customer 클래스 (Context)
public class Customer {
    private String grade;
    private int age;

    public Customer(String grade, int age) {
        this.grade = grade;
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public int getAge() {
        return age;
    }
}

// 3. Terminal Expressions
public class GradeExpression implements Expression {
    private String expectedGrade;

    public GradeExpression(String expectedGrade) {
        this.expectedGrade = expectedGrade;
    }

    @Override
    public boolean interpret(Customer custmer) {
        return custmer.getGrade().equalsIgnoreCase(expectedGrade);
    }
}

public class AgeExpression implements Expression {
    private int minAge;

    public AgeExpression(int minAge) {
        this.minAge = minAge;
    }

    @Override
    public boolean interpret(Customer custmer) {
        return custmer.getAge() >= minAge;
    }
}

// 4. NonTerminal Expressions
public class OrExpression implements Expression {
    private Expression expr1;
    private Expression expr2;

    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(Customer customer) {
        return expr1.interpret(customer) || expr2.interpret(customer);
    }
}

public class AndExpression implements Expression {
    private Expression expr1;
    private Expression expr2;

    public AndExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(Customer customer) {
        return expr1.interpret(customer) && expr2.interpret(customer);
    }
}

// 5. 클라이언트 : 룰 해석 예제
public class RuleInterpreterDemo {
    public static void  main(String[] args) {
        // 룰 : VIP 등급 또는 60세 이상이면 할인 대상
        Expression vipRule = new GradeExpression("Vip");
        Expression ageRule = new AgeExpression(60);
        Expression discountRule = new OrExpression(vipRule, ageRule);

        Customer c1 = new Customer("VIP", 45);
        Customer c2 = new Customer("BASIC", 70);
        Customer c3 = new Customer("BASIC", 30);

        System.out.println("고객 1 할인 대상? " + discountRule.interpret(c1)); // true
        System.out.println("고객 2 할인 대상? " + discountRule.interpret(c2)); // true
        System.out.println("고객 3 할인 대상? " + discountRule.interpret(c3)); // false


    }
}






















