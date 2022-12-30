package com.imooc.java.escape;

import java.util.Optional;

/**
 * <h1>学会 Optional，规避NPE</h1>
 * <h2>不要用 isPresent()当做 if 判空用法，无意义</h2>
 * <h2>orElse(), orElseGet(), map()的妙用</>
 * <h2>最好将 get(), ifPresent()当做Optional的私有方法，不去直接使用</>
 * <h2>Optional对象不能作为类的field，没有实现序列化接口</h2>
 * <h2>Optional用于简单对象；在领域模型（业务对象）中应小心使用</h2>
 */
public class OptionalUsage {

    public static class User {
        private String name;

        User() {
        }
        User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private static void isUserNull() {
        User user = null;
        if (user != null) {
            System.out.println("User is not NULL.");
        } else {
            System.out.println("User NULL!");
        }

        Optional<User> optional = Optional.empty();
        // 这种用法与JDK1.8之前的 if 判空一样，使用新API的意义不大
        if(optional.isPresent()) {
            System.out.println("User is not NULL.");
        } else {
            System.out.println("User NULL!");
        }
    }

    private static User anonymous() {
        return new User();
    }

    public static void main(String[] args) {
        // isPresent：使用if判空，没有意义的使用方法
        isUserNull();

        User user = null;
        Optional<User> optionalUser = Optional.ofNullable(user);
        // orElse: 存在即返回；NULL则提供默认值
        optionalUser.orElse(new User());
        // orElseGet：存在即返回；NULL则由函数去产生
        optionalUser.orElseGet(() -> anonymous());
        // orElseThrow：存在即返回；NULL则抛出异常
        optionalUser.orElseThrow(RuntimeException::new);

        // ifPresent：存在才做相应处理；NULL则忽略
        optionalUser.ifPresent(u -> System.out.println(u.getName()));

        // map：对Optional中的对象执行某种操作，且返回一个Optional 对象
        optionalUser.map(u -> u.getName()).orElse("somebody");
        // map可以无限级联操作，因为返回的是个Optional
        User user2 = new User("Tom");
        Optional<User> optionalUser2 = Optional.ofNullable(user2);
        int name_len = optionalUser2.map(u -> u.getName()).map(name -> name.length()).map(len -> len+1).orElse(0);
        System.out.println(name_len);
    }
}
