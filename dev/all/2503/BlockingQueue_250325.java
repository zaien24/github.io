//BlockingQueue 기본 구현

// 1. 메시지 정의
class ATCMessage {
    enum Type { TAKEOFF, LANDING }

    final String planeId;
    final Type type;

    ATCMessage(String planeId, Type type) {
        this.planeId = planeId;
        this.type = type;
    }
}


// 2. 메시지 큐 기반 미디에이터(Consumer 역할)
class AsyncATC implements Runnable {
    private final BlockingQueue<ATCMessage> queue;
    private boolean runwayFree = true;

    public AsyncATC(BlockingQueue<ATCMessage> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ATCMessage msg = queue.take(); // 큐에서 메시지 수신
                System.out.println("[ATC] Received " + msg.type + " from " + msg.planeId);

                switch (msg.type) {
                    case TAKEOFF:
                        if (runwayFree) {
                            System.out.println(msg.planeId + " is cleared for takeoff.");
                            runwayFree = false;
                        } else {
                            System.out.println(msg.planeId + " must wait (runway busy).");
                        }
                        break;

                    case LANDING:
                        System.out.println(msg.planeId + " has landed.");
                        runwayFree = true;
                        break;
                }
            } catch (InterruptedException e) {
                System.out.println("[ATC] Shutting down.");
                Thread.currentThread().interrupt();
            }
        }
    }
}



// 3. 비행기(Producer 역할)
class AsyncAirplane implements Runnable {
    private final String id;
    private final BlockingQueue<ATCMessage> queue;

    public AsyncAirplane(String id, BlockingQueue<ATCMessage> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println("[" + id + "] requesting takeoff...");
            queue.put(new ATCMessage(id, ATCMessage.Type.TAKEOFF));

            Thread.sleep((int)(Math.random() * 2000 + 1000)); // 비행 중...

            System.out.println("[" + id + "] requesting landing...");
            queue.put(new ATCMessage(id, ATCMessage.Type.LANDING));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


// 4. Main 실행
public class AsyncMediatorExample {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<ATCMessage> queue = new LinkedBlockingQueue<>();
        Thread atc = new Thread(new AsyncATC(queue));
        atc.start();

        Thread plane1 = new Thread(new AsyncAirplane("Flight-A101", queue));
        Thread plane2 = new Thread(new AsyncAirplane("Flight-B202", queue));

        plane1.start();
        plane2.start();

        plane1.join();
        plane2.join();

        atc.interrupt(); // 관제탑 종료
    }
}
}