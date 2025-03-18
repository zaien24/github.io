// 1. Implementor (메시지 전송 방식)
interface MessageSender {
    void sendMessage(String message);
}

// 2. ConcreteImplementor (각 메시지 전송 방식)
class EmailSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println("이메일 발송: " + message);
    }
}

class SlackSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println("Slack 메시지 발송: " + message);
    }
}

class SMSSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println("SMS 발송: " + message);
    }
}

// 3. Abstraction (알림)
abstract class Notification {
    protected MessageSender sender;

    protected Notification(MessageSender sender) {
        this.sender = sender;
    }

    abstract void notifyUser(String message);
}

// 4. RefinedAbstraction (알림 유형)
class UrgentNotification extends Notification {
    public UrgentNotification(MessageSender sender) {
        super(sender);
    }

    @Override
    void notifyUser(String message) {
        System.out.print("[긴급] ");
        sender.sendMessage(message);
    }
}

class NormalNotification extends Notification {
    public NormalNotification(MessageSender sender) {
        super(sender);
    }

    @Override
    void notifyUser(String message) {
        System.out.print("[일반] ");
        sender.sendMessage(message);
    }
}

// 5. 클라이언트 코드
public class BridgePatternMessagingExample {
    public static void main(String[] args) {
        Notification urgentEmail = new UrgentNotification(new EmailSender());
        urgentEmail.notifyUser("서버 장애 발생!");

        Notification normalSlack = new NormalNotification(new SlackSender());
        normalSlack.notifyUser("새로운 공지사항 확인하세요.");
    }
}
