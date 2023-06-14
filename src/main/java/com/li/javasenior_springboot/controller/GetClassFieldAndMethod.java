package com.li.javasenior_springboot.controller;


import com.li.javasenior_springboot.entity.bookList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GetClassFieldAndMethod {



    @RequestMapping("/getFields")
    public List<Map<String,String>> getFields(){
        Class booklist = bookList.class;

        List<Map<String,String>> mapList = new ArrayList<>();
        Field[] fields = booklist.getDeclaredFields();
        for (Field field : fields) {
            Map<String,String> map = new HashMap<>();
            map.put("name",field.getName());

            String fieldClass = field.getDeclaringClass().getName();
            String[] classPart = fieldClass.split("\\.");
            map.put("class",classPart[classPart.length - 1]);

            map.put("type",field.getType().getName());
            map.put("Modifier",Modifier.toString(field.getModifiers()));
            mapList.add(map);

        }


        return mapList;
    }

    @RequestMapping("/getMethods")
    public List<Map<String ,Object >> getMethods(){
        Class bookListControllerClass = bookListController.class;
        List<Map<String, Object>> mapList2 = new ArrayList<>();
        Method[] methods = bookListControllerClass.getDeclaredMethods();
        for(Method method: methods){
            Map<String,Object> map = new HashMap<>();

            map.put("name",method.getName());

            String methodClass = method.getDeclaringClass().getName();
            String[] classPart1 = methodClass.split("\\.");
            map.put("class",classPart1[classPart1.length - 1]);

            map.put("returnType",method.getReturnType().getName());
            map.put("modifier",Modifier.toString(method.getModifiers()));
            Parameter[] parameters = method.getParameters();
            List<String> parameterss = new ArrayList<>();
            for (Parameter parameter : parameters) {
                String[] classPart = parameter.getType().getName().split("\\.");
                parameterss.add(classPart[classPart.length - 1]);
            }
            map.put("paramType",parameterss);

            Class[] exceptions = method.getExceptionTypes();
            List<String> exceptionn = new ArrayList<>();
            for (Class exception : exceptions) {
                String[] classPart = exception.getName().split("\\.");
                exceptionn.add(classPart[classPart.length - 1]);
            }
            map.put("exception",exceptionn);

            mapList2.add(map);
        }
        return mapList2;
    }

}
