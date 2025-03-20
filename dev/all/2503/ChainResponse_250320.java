// 1. Handler (핸들러 인터페이스)
abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String request);
}

// 2. ConcreteHandler (구체적인 핸들러)

// 프론트데스크 (기본적인 고객 지원 처리)
class FrontDeskSupport extends SupportHandler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("기본 문의")) {
            System.out.println("📞 프론트데스크에서 문의 처리: " + request);
        } else {
            System.out.println("➡️ 프론트데스크에서 다음 부서로 요청 전달...");
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            }
        }
    }
}

// 계정팀 (계정 관련 문제 처리)
class AccountSupport extends SupportHandler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("계정 문제")) {
            System.out.println("🔑 계정팀에서 계정 문제 해결: " + request);
        } else {
            System.out.println("➡️ 계정팀에서 다음 부서로 요청 전달...");
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            }
        }
    }
}

// 기술 지원팀 (기술 문제 처리)
class TechSupport extends SupportHandler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("기술 문제")) {
            System.out.println("🖥️ 기술 지원팀에서 기술 문제 해결: " + request);
        } else {
            System.out.println("➡️ 기술 지원팀에서 다음 부서로 요청 전달...");
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            }
        }
    }
}

// 관리자 (최종 처리)
class ManagerSupport extends SupportHandler {
    @Override
    public void handleRequest(String request) {
        System.out.println("👨‍💼 관리자가 최종적으로 처리: " + request);
    }
}

// 3. 클라이언트 코드
public class ChainOfResponsibilityExample {
    public static void main(String[] args) {
        // 핸들러 체인 구성
        SupportHandler frontDesk = new FrontDeskSupport();
        SupportHandler accountTeam = new AccountSupport();
        SupportHandler techTeam = new TechSupport();
        SupportHandler manager = new ManagerSupport();

        // 체인 연결
        frontDesk.setNextHandler(accountTeam);
        accountTeam.setNextHandler(techTeam);
        techTeam.setNextHandler(manager);

        // 요청 처리
        System.out.println("=== 기본 문의 요청 ===");
        frontDesk.handleRequest("기본 문의");

        System.out.println("\n=== 계정 문제 요청 ===");
        frontDesk.handleRequest("계정 문제");

        System.out.println("\n=== 기술 문제 요청 ===");
        frontDesk.handleRequest("기술 문제");

        System.out.println("\n=== 알 수 없는 요청 ===");
        frontDesk.handleRequest("환불 요청");
    }
}
