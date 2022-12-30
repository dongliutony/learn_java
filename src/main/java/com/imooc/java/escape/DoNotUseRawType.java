package com.imooc.java.escape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>切记不用使用原始类型，这可能会造成灾难性的后果</h1>
 */
public class DoNotUseRawType {

    /**
     * <h2>简单使用原始类型</h2>
     */
    private static void useRawType() {
        List data = new ArrayList();
        data.add("tom");
        data.add(12);
        data.add("Hello world");

        data.forEach(System.out::println);

//        data.forEach(d -> {
//            if (((String)d).equals("Hello world")) {
//                System.out.println(data.indexOf(d));
//            }
//        });

        data.forEach(d -> {
            if(d instanceof String && d.equals("Hello world")) {
                System.out.println(data.indexOf(d));
            }
        });
    }

    /**
     * <h2>优化使用原始类型</h2>
     */
    private static void optimize1() {
        // 初步优化，告诉compiler，List 容器中可以存放任意类型的对象
        List<Object> data = new ArrayList<>();
        data.add("tom");
        data.add(12);
        data.add("Hello world");

        data.forEach(System.out::println);
        data.forEach(d -> {
            if(d instanceof String && d.equals("Hello world")) {
                System.out.println(data.indexOf(d));
            }
        });
    }

    /**
     * <h2>最终优化：指明具体类型，把类型检查工具在Compile阶段完成</h2>
     */
    private static void finalOptimize() {

        List<People> data = new ArrayList<>();
        People ppl = new People("tom", 19, "Hello world");
        data.add(ppl);
        People ppl2 = new People("mike", 1, "hh");
        data.add(ppl2);

        data.forEach(System.out::println);
        data.forEach(d -> {
            System.out.println(data.indexOf(d));
        });
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class People {
        private String name;
        private Integer age;
        private String signature;
    }

    public static void main(String[] args) {

        // 原始类型List没有为List容器指定元素的类型：抛弃了泛型中所指的具体的存储类型
//        List list = null;

//        useRawType();
//        optimize1();
        finalOptimize();
    }
}
