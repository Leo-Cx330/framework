package com.lh.core.demo;

import com.lh.core.demo.bean.Demo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ProjectName: framework
 * @Package: com.lh.core.demo
 * @ClassName: XmlApplicationDemo
 * @Description: java类作用描述
 * @Author: lihao
 * @CreateDate: 2018/11/13 10:56 AM
 * @UpdateDate: 2018/11/13 10:56 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class XmlApplicationDemo {


        public static void main(String[] args) {
            ApplicationContext context =new ClassPathXmlApplicationContext("spring.xml");
            Demo demo = (Demo) context.getBean("demo");
            System.out.println(demo.getId()+":"+demo.getName());
        }

}
