package com.lh.core.framework.controller;

import com.lh.core.framework.annotation.Controller;
import com.lh.core.framework.annotation.RequestJson;
import com.lh.core.framework.annotation.RequestMapping;
import com.lh.core.framework.request.IndexRequest;

/**
 * @ProjectName: framework
 * @Package: com.lh.core.framework.controller
 * @ClassName: IndexController
 * @Description: java类作用描述
 * @Author: lihao
 * @CreateDate: 2018/11/9 3:04 PM
 * @UpdateDate: 2018/11/9 3:04 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@RequestMapping("/index")
@Controller
public class IndexController {

    @RequestMapping("/test")
    public  String test(@RequestJson IndexRequest request){
        System.out.println("request = [" + request + "]");
        return request.toString();
    }
}
