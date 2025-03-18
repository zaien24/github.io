// 1. USB (새로운 노트북이 기대하는 인터페이스)
interface USB {
    void connectWithUsbCable();
}

// 2. 기존 USB-C 충전기 (변경 불가능한 코드)
class UsbTypeCCharger {
    public void chargeWithUsbC() {
        System.out.println("USB-C 충전기 사용 중...");
    }
}

// 3. USB-A → USB-C 변환 어댑터 (Adapter)
class UsbToUsbCAdapter implements USB {
    private UsbTypeCCharger usbCCharger;

    public UsbToUsbCAdapter(UsbTypeCCharger usbCCharger) {
        this.usbCCharger = usbCCharger;
    }

    @Override
    public void connectWithUsbCable() {
        System.out.println("USB-A 포트를 USB-C로 변환...");
        usbCCharger.chargeWithUsbC();
    }
}

// 4. 새로운 노트북 (USB-A 포트만 지원)
class NewLaptop {
    private USB usbPort;

    public NewLaptop(USB usbPort) {
        this.usbPort = usbPort;
    }

    public void charge() {
        System.out.println("새로운 노트북 (USB-A 포트) 충전 시작...");
        usbPort.connectWithUsbCable();
    }
}

// 5. 실행 코드
public class AdapterPatternExample {
    public static void main(String[] args) {
        // 기존 USB-C 충전기
        UsbTypeCCharger usbCCharger = new UsbTypeCCharger();

        // USB-C 충전기를 USB-A 포트에서 사용할 수 있도록 변환하는 어댑터
        USB adapter = new UsbToUsbCAdapter(usbCCharger);

        // 새로운 노트북에서 USB-A 포트로 충전기 사용
        NewLaptop newLaptop = new NewLaptop(adapter);
        newLaptop.charge();
    }
}
