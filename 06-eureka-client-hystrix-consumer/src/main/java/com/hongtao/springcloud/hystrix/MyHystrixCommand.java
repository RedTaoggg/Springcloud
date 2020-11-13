package com.hongtao.springcloud.hystrix;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 * 自定义异常熔断器类，并继承Hystrix异常熔断器父类（抽象类）
 */
public class MyHystrixCommand extends HystrixCommand {
    private RestTemplate restTemplate;
    private String url;
    /**
     * 定义一个有参数构造（必须的），由于父类没有无参数构造因此子类必须实现有参构造
     * @param setter
     */
    public MyHystrixCommand(Setter setter, RestTemplate restTemplate, String url) {
        super(setter);
        this.restTemplate = restTemplate;
        this.url = url;
    }

    /**
     * 这个方法不能手动调用，Spring 会自动调用这个方法来访问我们的服务提供者
     * @return
     * @throws Exception
     */
    @Override
    protected Object run() throws Exception {
        return restTemplate.getForObject(url,String.class);
    }

    /**
     *服务降级方法，当spring自动调用run方法后，如果服务出现了异常则自动调用这个服务降级方法来进行异常的熔断
     * @return
     */
    @Override
    protected Object getFallback() {
//        获取异常信息
        System.out.println("1.====="+super.getFailedExecutionException().getClass());
        System.out.println("2.====="+super.getFailedExecutionException().getMessage());
        return "自定义异常熔断器熔断了服务";
    }
}
