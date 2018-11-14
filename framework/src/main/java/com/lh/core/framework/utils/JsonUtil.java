package com.lh.core.framework.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @ProjectName: framework
 * @Package: com.lh.core.framework.utils
 * @ClassName: JsonUtil
 * @Description: 将请求参数转换为jsonobject
 * @Author: lihao
 * @CreateDate: 2018/11/7 3:36 PM
 * @UpdateDate: 2018/11/7 3:36 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class JsonUtil {


    public static JSONObject getBody(HttpServletRequest request){
        String contentType = request.getContentType();
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader=null;
        try{
            InputStream inputStream = request.getInputStream();
            if(inputStream!=null){
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }else{
                stringBuilder.append("");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (contentType.equalsIgnoreCase("application/json")) {
            String jsonStr = stringBuilder.toString();
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            return jsonObject;
        }

        return  null;
    }
}
