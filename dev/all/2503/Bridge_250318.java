//브리지 패턴

// 1. Implementor (구현부 인터페이스)
interface OSImplementor
        mentor {
    void runOS();
}

// 2. ConcreteImplementor (구체적인 구현 클래스)
class AndroidOS implements OSImplementor {
    @Override
    public void runOS() {
        System.out.println("Android OS 실행");
    }
}

class IOS implements OSImplementor {
    @Override
    public void runOS() {
        System.out.println("iOS 실행");
    }
}

// 3. Abstraction (추상 계층)
abstract class Device {
    protected OSImplementor osImplementor; // Bridge 역할

    protected Device(OSImplementor osImplementor) {
        this.osImplementor = osImplementor;
    }

    abstract void operate();
}

// 4. RefinedAbstraction (구체적인 기능 계층)
class Phone extends Device {
    public Phone(OSImplementor osImplementor) {
        super(osImplementor);
    }

    @Override
    void operate() {
        System.out.println("스마트폰에서 ");
        osImplementor.runOS();
    }
}

class Tablet extends Device {
    public Tablet(OSImplementor osImplementor) {
        super(osImplementor);
    }

    @Override
    void operate() {
        System.out.print("태블릿에서 ");
        osImplementor.runOS();
    }
}

// 5. 클라이언트 코드
public class BridgePatternExample {
    public static void main(String[] args) {
        Device androidPhone = new Phone(new AndroidOS());
        androidPhone.operate();

        Device iosTable = new Tablet(new IOS());
        iosTable.operate();
    }
}