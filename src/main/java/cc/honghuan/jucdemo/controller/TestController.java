package cc.honghuan.jucdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

/**
 * @author honghuan.Liu
 * @date 2022/5/13 08:22
 */
@RestController
public class TestController {

    ExecutorService threadPool1 = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));




    @GetMapping("/test2")
    public void test2() throws Exception {
        Future<String> submit = threadPool1.submit(() -> {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String s = myTask2();
            return s;
        });

        System.out.println(submit.get());
    }

    private String myTask2() throws ExecutionException, InterruptedException {

        Future<String> submit1 = threadPool1.submit(() -> {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "child";
        });
        return submit1.get();
    }


    @GetMapping("/test1")
    public void test1() throws Exception {
        Future<String> submit = threadPool1.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myTask1();
            return "parent";
        });

        System.out.println(submit.get());
    }

    private void myTask1() throws ExecutionException, InterruptedException {
        Future<String> submit = threadPool1.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": child 1");
            return "child 1";
        });

        Future<String> submit2 = threadPool1.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": child 2");
            return "child 2";
        });

    }
}
