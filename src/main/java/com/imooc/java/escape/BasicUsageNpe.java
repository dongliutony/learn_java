package com.imooc.java.escape;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <h1>字符串、数组、集合在使用时出现空指针</h1>
 */
public class BasicUsageNpe {

    private static boolean stringEqual(String x, String y) {
        return x.equals(y);
    }

    private static class User{
        private String name;
    }

    public static void main(String[] args) {

        // 1. 字符串使用 equals 可能会NPE
//        System.out.println(stringEqual("xyz", null));
//        System.out.println(stringEqual(null, "xyz"));

        // 2. "对象数组" new 出来后，没有初始化"元素对象"
//        User[] users = new User[10]; // 只分配了数组存储空间，但里面的对象不存在
//        for (int i=0; i<10; ++i) {
//            users[i] = new User(); // 必须初始化元素，否则 NPE
//            users[i].name = "imooc-" + i;
//        }

        // 3. List 对象 addAll 传递 null， 会抛出空指针
        List<User> users = new ArrayList<>();
        User user = null;
        List<User> users2 = null;

        Optional<User> opt;

        users.add(user);
//        users.addAll(users2); // NPE
    }

}
