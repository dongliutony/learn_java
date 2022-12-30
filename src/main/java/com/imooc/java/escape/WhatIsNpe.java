package com.imooc.java.escape;

import java.util.List;

/**
 * <h1>理解什么是空指针异常</h1>
 * <h2>best practice</h2>
 * <h3>1. 创建了对象，检查做了初始化</h3>
 * <h3>2. func不要 return null；如果一定需要，写出comment</h3>
 * <h3>3. 对于外部传值(如 db、网络请求等)，除非明确明确说明非null，一定要及时判空</h3>
 */
public class WhatIsNpe {

    public static class User {

        private String name;
        private String[] address;

        public void print() {
            System.out.println("This is User class.");
        }

        public String readbook() {
            System.out.println("User is reading...");
            return null;
        }
    }

    /**
     * <h2>自定义运行时异常</h2>
     */
    public static class CustomException extends RuntimeException {

    }

    public static void main(String[] args) {

        // 1. call a method on a null object
//        User user = null;
//        user.print();

        // 2. access fields of a null object
//        User user = null;
//        System.out.println(user.name);

        // 3. access(or get length) an array element with a null array
//        User user = new User();
//        System.out.println(user.address.length);

        // 4. throw out a null Exception. Use "null" as a throwable object
//        CustomException e = null;
//        throw e;

        // 5. caller uses a null as a return value of a method
        User user = new User();
        System.out.println(user.readbook().contains("hello"));

    }
}
