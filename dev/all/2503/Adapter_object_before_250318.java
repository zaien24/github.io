// 어댑터 패턴 적용 전

// USB-C 충전기만 사용할 때
class UsbTypeCCharger {
    public void chargeWithUsbC() {
        System.out.println("USB-C 충전기 사용 중...");
    }
}

class MacBook {
    private UsbTypeCCharger usbCCharger;

    public MacBook(UsbTypeCCharger usbCCharger) {
        this.usbCCharger = usbCCharger;
    }

    public void charge() {
        System.out.println("MacBook 충전 시작...");
        usbCCharger.chargeWithUsbC();
    }
}

public class UsbCChargingExample {
    public static void main(String[] args) {
        UsbTypeCCharger usbCCharger = new UsbTypeCCharger();
        MacBook macBook = new MacBook(usbCCharger);
        macBook.charge();
    }
}
