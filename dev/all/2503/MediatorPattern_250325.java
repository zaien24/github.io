// 항공 관제 시스템(Air Traffic Control System)은
// 미디에이터 패턴을 설명할 때 자주 쓰이는 예시예요.
// 각 비행기(항공기)는 서로 직접 통신하지 않고,
// 관제탑(Mediator) 을 통해 소통합니다.
// 이렇게 하면 비행기 간의 의존성을 없애고,
// 중앙집중적으로 비행 흐름을 제어할 수 있죠.

// 테스트 코드
public class MediatorPattern {
    public static void main(String[] args) {
        ATCMediator atc = new ATC();

        Airplane plane1 = new CommercialAirplane("Flight-A101", atc);
        Airplane plane2 = new CommercialAirplane("Flight-B202", atc);

        plane1.takeOff();   // Runway is free, so takeoff is cleared
        plane2.takeOff();   // Runway is occupied, so must wait
        plane1.land();      // Plane1 lands, frees the runway
        plane2.takeOff();   // Now cleared for takeoff
    }
}

interface ATCMediator {
    void registerAirplane(Airplane airplane);
    void requestTakeOff(Airplane airplane);
    void notifyLanding(Airplane airplane);
}

// Concrete Mediator
class ATC implements ATCMediator {
    private List<Airplane> airplanes = new ArrayList<>();
    private boolean runwayFlee = true;

    @Override
    public void registerAirplane(Airplane airplane) {
        airplanes.add(airplane);
    }

    @Override
    public void requestTakeOff(Airplane airplane) {
        if (runwayFree) {
            System.out.println(airplane.getId() + " is cleared for takeoff.");
            runwayFree = false;
        } else {
            System.out.println(airplane.getId() + " is waiting for runway to be free.");
        }
    }

    @Override
    public void notifyLanding(Airplane airplane) {
        System.out.println(airplane.getId() + " has landed.");
        runwayFree = true;
    }
}

// Colleague Interface
abstract class Airplane {
    protected ATCMediator mediator;
    private String id;

    public Airplane(String id, ATCMediator mediator) {
        this.id = id;
        this.mediator = mediator;
        mediator.registerAirplane(this);
    }

    public String getId() {
        return id;
    }

    public abstract void land();
    public abstract void takeOff();
}

// Concrete Colleage
class CommercialAirplane extends Airplane {

    public CommercialAirplane(String id, ATCMediator mediator) {
        super(id, mediator);
    }

    @Override
    public void takeOff() {
        System.out.println(getId() + " requesting takeoff...");
        mediator.requestTakeOff(this);
    }

    @Override
    public void land() {
        System.out.println(getId() + " landing...");
        mediator.notifyLanding(this);
    }

}