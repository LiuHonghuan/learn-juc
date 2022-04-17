package cc.honghuan.jucdemo.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author honghuan.Liu
 * @date 2022/4/17 20:06
 */
public class Test {

    private int queueSize = 10;
    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(queueSize);


    public static void main(String[] args) {
        Test test = new Test();

        Consumer consumer = test.new Consumer();
        Producer producer = test.new Producer();

        consumer.start();
        producer.start();


    }


    class Producer extends Thread {

        @Override
        public void run() {
            doProducer();
        }

        private void doProducer() {
            int offset = 1;
            while (true) {
                try {
                    queue.put(offset);
                    System.out.println("线程(" + Thread.currentThread().getName() + ")向队列插入一个元素" + offset + "，队列剩余空间：" + (queueSize - queue.size()));
                     Thread.sleep(1000);
                    offset++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    class Consumer extends Thread {
        @Override
        public void run() {
            try {
                doConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void doConsumer() throws Exception {
            for (; ; ) {
                Integer offset = queue.take();
                System.out.println("线程(" + Thread.currentThread().getName() + ")消费了:" + offset + ", 队列剩余空间：" + (queueSize - queue.size()) + "个");
                Thread.sleep(1000);
            }
        }
    }
}
