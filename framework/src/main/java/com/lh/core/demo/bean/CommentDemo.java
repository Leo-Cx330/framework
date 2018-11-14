package com.lh.core.demo.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: framework
 * @Package: com.lh.core.demo.bean
 * @ClassName: CommentDemo
 * @Description: java类作用描述
 * @Author: 李浩
 * @CreateDate: 2018/11/13 2:43 PM
 * @UpdateDate: 2018/11/13 2:43 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Data
public class CommentDemo {


    private String  conttent;

    private Integer  tag;


}
