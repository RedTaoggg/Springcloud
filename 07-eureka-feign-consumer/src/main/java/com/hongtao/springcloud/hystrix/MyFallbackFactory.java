package com.hongtao.springcloud.hystrix;

import com.hongtao.springcloud.domain.User;
import com.hongtao.springcloud.service.TestService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义异常熔断类，并实现Hystrix的降级回调的父接口
 * 注意：
 *          这个父接口的泛型很重要，这个泛型决定当前类要为哪个声明式接口提供者异常熔断
 */
@Component
public class MyFallbackFactory implements FallbackFactory<TestService> {
    /**
     * create 方法来自父接口，用于返回一个父接口中泛型的对象
     *      作用于当这个泛型对象出现异常以后将只用create返回的这个对象来进行降级
     * @param throwable
     * @return
     */
    @Override
    public TestService create(Throwable throwable) {
        //使用匿名内部类来创建TestService声明式服务接口的熔断对象
        return new TestService() {
            @Override
            public String test() {
                System.out.println("1:"+throwable.getMessage());
                System.out.println("2:"+throwable.getClass());
                System.out.println("3:"+throwable.getCause());
                return "test方法被熔断了---";
            }

            @Override
            public String testParams01(String name, Integer age) {
                return null;
            }

            @Override
            public String testParams02(User user) {
                return null;
            }

            @Override
            public User testReturn() {
                return null;
            }

            @Override
            public List<User> testReturnList() {
                return null;
            }
        };
    }
}
