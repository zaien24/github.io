// 1. Target 인터페이스 (클라이언트가 기대하는 인터페이스)
interface Target {
    void request();
}

// 2. Adaptee 클래스 (기존 클래스)
class Adaptee {
    public void specificRequest() {
        System.out.println("Adaptee: 기존 방식으로 동작");
    }
}

// 3. Adapter 클래스 (어댑터 역할)
class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        System.out.println("Adapter : 변환 과정 수행");
        adaptee.specificRequest();
    }
}

// 4. 클라이언트 코드
public class AdapterPatternExample {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target target = new Adapter(adaptee);
        target.request();
    }
}























