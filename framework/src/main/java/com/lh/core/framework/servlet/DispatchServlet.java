package com.lh.core.framework.servlet;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lh.core.framework.annotation.Controller;
import com.lh.core.framework.annotation.RequestJson;
import com.lh.core.framework.annotation.RequestMapping;
import com.lh.core.framework.utils.JsonUtil;
import com.lh.core.framework.utils.PackageUtil;
import com.lh.core.framework.utils.YmlUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class DispatchServlet extends HttpServlet {


    List<String>classz=new ArrayList<>();//扫描包下所有的类
    Map<String,Method> mappingMethodMap=new ConcurrentHashMap<>();//url映射对应的方法
    Map<String,Object>mappingClassMap=new ConcurrentHashMap<>();//url映射对应的类


    /**
     * 初始化，装配接口映射地址
     */
    @Override
    public void init(){
        try {
            classz= PackageUtil.scanPackage((String) YmlUtil.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(classz==null){
            return;
        }
        for (String clazz:classz) {
            String  mappingUrl="";
            try {
                Class<?> loadClass =Class.forName(clazz);
                Controller controller = loadClass.getAnnotation(Controller.class);
                if(controller==null){
                    continue;
                }
                RequestMapping controllerUrl = loadClass.getAnnotation(RequestMapping.class);
                mappingUrl=mappingUrl+controllerUrl.value();
                Method[] methods = loadClass.getMethods();
                for (Method method:methods){
                    RequestMapping methodMapping = method.getAnnotation(RequestMapping.class);
                    if(methodMapping==null){
                        continue;
                    }
                    mappingUrl=mappingUrl+methodMapping.value();
                    mappingMethodMap.put(mappingUrl,method);
                }
                Object object = loadClass.newInstance();
                mappingClassMap.put(mappingUrl,object);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                continue;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 执行请求，返回结果
     * @param request
     * @param response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
        String uri = request.getRequestURI();
        Method method = mappingMethodMap.get(uri);
        Object object = mappingClassMap.get(uri);
        if(method==null){
            return;
        }
        JSONObject body =JsonUtil.getBody(request);
        Parameter[] parameters = method.getParameters();
        Object[] o = buildParam(parameters, body);
        try {
            Object invoke = method.invoke(object,o);
            response.getWriter().write(invoke.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 封装接口方法的入参
     * @param parameters
     * @param body
     * @return
     */
    private  Object[] buildParam( Parameter[] parameters,  JSONObject body){
        Object[] objects=new Object[parameters.length];
        for (int i=0;i< parameters.length ;i++) {
            RequestJson requestJson = parameters[i].getAnnotation(RequestJson.class);
            if(requestJson==null){
                continue;
            }
            Object object = JSON.toJavaObject(body, parameters[i].getType());
            objects[i]=object;
        }
        return objects;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }


}
