// 로그 핸들러 인터페이스
abstract class Logger {
    protected Logger nextLogger; // 다음 핸들러

    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(String level, String message) {
        if (canHandle(level)) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level, message); // 다음 핸들러로 전달
        }
    }

    protected abstract boolean canHandle(String level);
    protected abstract void write(String message);
}

// 구체적인 핸들러 (DEBUG 레벨)
class DebugLogger extends Logger {
    protected boolean canHandle(String level) {
        return level.equalsIgnoreCase("DEBUG");
    }

    protected void write(String message) {
        System.out.println("[DEBUG] " + message);
    }
}

// 구체적인 핸들러 (INFO 레벨)
class InfoLogger extends Logger {
    protected boolean canHandle(String level) {
        return level.equalsIgnoreCase("INFO");
    }

    protected void write(String message) {
        System.out.println("[INFO] " + message);
    }
}

// 구체적인 핸들러 (ERROR 레벨)
class ErrorLogger extends Logger {
    protected boolean canHandle(String level) {
        return level.equalsIgnoreCase("ERROR");
    }

    protected void write(String message) {
        System.out.println("[ERROR] " + message);
    }
}

// 클라이언트 코드
public class ChainOfResponsibilityExample {
    public static void main(String[] args) {
        Logger debugLogger = new DebugLogger();
        Logger infoLogger = new InfoLogger();
        Logger errorLogger = new ErrorLogger();

        debugLogger.setNextLogger(infoLogger);
        infoLogger.setNextLogger(errorLogger);

        // 로그 메시지 처리
        debugLogger.logMessage("DEBUG", "Debugging application...");
        debugLogger.logMessage("INFO", "Application is running.");
        debugLogger.logMessage("ERROR", "An error occurred!");
    }
}
