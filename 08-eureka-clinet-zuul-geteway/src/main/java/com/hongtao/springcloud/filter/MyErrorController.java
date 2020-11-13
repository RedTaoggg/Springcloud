package com.hongtao.springcloud.filter;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MyErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }
    @RequestMapping("/error")
    @ResponseBody
    public String error(){
        ZuulException exception = (ZuulException) RequestContext.getCurrentContext().getThrowable();

        return "全局错误页面code："+exception.nStatusCode+"--- message:"+exception.getMessage();

    }
}
