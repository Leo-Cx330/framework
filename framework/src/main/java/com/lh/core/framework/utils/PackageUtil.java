package com.lh.core.framework.utils;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: framework
 * @Package: com.lh.core.framework.utils
 * @ClassName: PackageUtil
 * @Description: java类作用描述
 * @Author: lihao
 * @CreateDate: 2018/10/24 5:41 PM
 * @UpdateDate: 2018/10/24 5:41 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PackageUtil {



    public static List<String> scanPackage(String packageName){
        List<String> classz=new ArrayList<>();
        URL url = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".","/"));
        if(url==null){
            return null;
        }
        String pathFile = url.getFile();
        File file = new File(pathFile);
        String fileList[] = file.list();
        for (String path : fileList) {
            File eachFile = new File(pathFile + path);
            if (eachFile.isDirectory()) {
                scanPackage(packageName + "." + eachFile.getName());
            } else {
                String name = eachFile.getName();
                classz.add(packageName + "." +name.substring(0,name.lastIndexOf(".")));
            }
        }
        return classz;
    }


}
