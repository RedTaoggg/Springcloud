package com.hongtao.springcloud.service;

import com.hongtao.springcloud.domain.User;
import com.hongtao.springcloud.hystrix.MyFallback;
import com.hongtao.springcloud.hystrix.MyFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @FeignClient 用于标记当前接口是一个Feign的声明式服务接口
 * Spring会为这个接口生成动态代理对象
 * 属性：
 *      name:用于指定注册中心的某个服务的名字
 *      fallback:指定一个自定义的异常熔断类，当使用声明式接口中的方法调用远程服务时
 *                      如果出现了异常则自动执行fallback所指定的这个类中的对应的方法来执行服务的降级
 *       fallbackFactory: 指定一个自定义的异常熔断器类，这个熔断器可以获取异常信息
 */
@FeignClient(name = "07-EUREKA-FEGIN-PROVIDE",fallbackFactory = MyFallbackFactory.class)
public interface TestService {
    /**
     * 定义抽象方法，并使用RequestMapping标记这个方法用于访问服务提供者，
     * 其中RequestMapping的参数/test应该与当前接口所标记的服务名中的服务中的
     * 某个请求路径相同
     * @return
     */
    @RequestMapping("/test")
    String test();

    /**
     *  如果本次服务提供者的语法拥有参数那么声明式服务接口的方法的参数必须要指定@RequestParam
     *  否则无法传递参数到服务的提供者
     * @param name
     * @param age
     * @return
     */
    @RequestMapping("/testParams01")
    String testParams01(@RequestParam String name, @RequestParam Integer age);
    @RequestMapping("/testParams02")
    String testParams02(@RequestBody User user);// 提供者的消费者都需要加@RequestBody

    /**
     * 如果服务提供者返回的json数据符合Json对象格式那么我们就可以使用一个实体类或者一个Map集合来接收响应数据
     * @return
     */
    @RequestMapping("/testReturn")
    User testReturn();

    /**
     * 如果服务提供者返回的Json数据符合Json对象数组，那么我们就可以使用一个List来接收响应数据
     * 如果这个Json数据中的元素符合Json对象格式那么集合的泛型可以是一个实体或一个Map集合
     * Spring就会根据我们的实际数据类型将Json转换成返回值
     * @return
     */
    @RequestMapping("/testReturnList")
    List<User> testReturnList();
}
