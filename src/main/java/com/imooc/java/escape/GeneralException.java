package com.imooc.java.escape;

import com.google.common.base.Enums;

import java.util.*;

/**
 * <h1>编码中常见的异常</h1>
 * <h2>ConcurrentModificationException 并发修改异常</>
 * <h2>ClassCastException 类型转换异常</h2>
 * <h2>枚举查找异常</h2>
 */
public class GeneralException {
    private static class User{
        private String name;

        public User(){}

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class Manager extends User{}

    private static class Worker extends User{}

    private static final Map<String, StaffType> typeIndex = new HashMap<>(
            StaffType.values().length
    );
    static {
        for(StaffType value: StaffType.values()) {
            typeIndex.put(value.name(), value);
        }
    }


    /**
     * <h2>1. 并发修改异常：在遍历时，对可迭代的对象做修改操作</h2>
     */
    private static void concurrentModificationException(ArrayList<User> users) {
        // 直接使用 foreach loop 迭代过程，会触发"并发修改异常"。原因是 Java 的Fail fast机制。集合的Iterator工作在一个独立线程中，
        // 拥有Mutex互斥锁。迭代器创建后会建立一个指向原来集合的单向索引表；当原来集合内对象数量变化时，这个索引表不会改变。当索引指针往后
        // 移动时，找不到需要迭代的对象。按照 fail fast 原则，迭代器立即抛出 CME。
        for (User user: users) {
            System.out.println(user.getName());
            if (user.getName().equals("tom")) {
                users.add(new User("Hello"));
            }
        }
        // 使用普通 for loop 可以; 并且local variable i会被 reassign
//        for (int i=0; i<users.size(); ++i) {
//            User user = users.get(i);
//            System.out.println(user.getName());
//            if (user.getName().equals("tom")) {
//                users.add(new User("hello"));
//            }
//        }

        // 使用"迭代器"则没有问题
//        Iterator<User> iter = users.iterator();
//        while(iter.hasNext()) {
//            User user = iter.next();
//            System.out.println(user.getName());
//            if(user.getName().equals("tom")) {
//                iter.remove();
//            }
//        }
    }

    private static StaffType enumFind(String type) {
//        return StaffType.valueOf(type);
        // 1. 最简单、最普通实现
//        try {
//            return StaffType.valueOf(type);
//        } catch(IllegalArgumentException e) {
//            return null;
//        }
//    }

        // 2. 改进实现，增加灵活性；但是效率不高
//        for (StaffType value: StaffType.values()) {
//            if (value.name().equals(type)) {
//                return value;
//            }
//        }
//        return null;

        // 3. 静态代码初始化 map索引。只执行一次，效率高。 -> 不足：不在索引中的，会返回null。外部调用时，容易导致对方NPE
//        return typeIndex.get(type);

        // 4. 使用 Guava Enums 包，返回Optional对象
        return Enums.getIfPresent(StaffType.class, type).or(StaffType.PM);
    }

    public static void main(String[] args) {
        // 1. 并发修改异常
//        ArrayList<User> users = new ArrayList<User>(
//                Arrays.asList(new User("tom"), new User("imooc"), new User("mike"))
//        );
//        System.out.println("before: " + users.size());
//        concurrentModificationException(users);
//        System.out.println("after: " + users.size());

        // 2. 类型转换异常
//        User user1 = new Manager();
//        User user2 = new Worker();
//        Manager m1 = (Manager)user1;
//        Manager m2 = (Manager)user2;
//        System.out.println(m2 instanceof Manager);

        // 3. 枚举查找异常
        System.out.println(enumFind("QA"));
        System.out.println(enumFind("QAAA"));

    }
}
