package cc.honghuan.jucdemo.spring;

import cc.honghuan.jucdemo.spring.bean.TestBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author honghuan.Liu
 * @date 2022/5/8 15:58
 */
@Configuration
@ComponentScan("cc.honghuan.jucdemo.spring.bean")
public class SpringTest1 {


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringTest1.class);
        TestBean bean = context.getBean(TestBean.class);
        bean.sayHello();

    }
}
