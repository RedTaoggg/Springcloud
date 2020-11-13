package com.hongtao.springcloud.hystrix;

import com.hongtao.springcloud.domain.User;
import com.hongtao.springcloud.service.TestService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义熔断器类
 */
@Component
public class MyFallback  implements TestService {
    @Override
    public String test() {
        return "test请求容断";
    }

    @Override
    public String testParams01(String name, Integer age) {
        return "testParams01请求容断";

    }

    @Override
    public String testParams02(User user) {
        return "testParams02请求容断";

    }

    @Override
    public User testReturn() {
        return null;

    }

    @Override
    public List<User> testReturnList() {
        return null;

    }
}
