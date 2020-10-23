package thread;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        var queue = new LinkedList<Integer>();
        var t1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                synchronized (queue) {
                    queue.add(i);
                    queue.notify();
                }

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        });
        var t2 = new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    synchronized (queue) {
                        while (queue.isEmpty()) {
                            queue.wait();
                        }
                        queue.remove();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        });

        t1.start();
        t2.start();

        t1.interrupt();
        t2.interrupt();
    }
}
