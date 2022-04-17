package cc.honghuan.jucdemo.aqs;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author honghuan.Liu
 * @date 2022/4/16 23:33
 */
public class Test {

    static AtomicInteger count = new AtomicInteger(0);
    // static HonghuanLock lock = new HonghuanLock();
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        for (int i = 0; i < 2000; i++) {

            new Thread(() -> {

                for (int k = 0; k < 10; k++) {
                    lock.lock();
                    count.incrementAndGet();
                    lock.unlock();
                }


            }).start();


        }

        System.out.println(count);

    }
}
