package com.imooc.java.escape;

/**
 * <h1>自动拆箱引发的空指针问题</h1>
 * <h2>使用建议</h2>
 * <h3>1. primitive类型优于wrapper类型（节省时间、空间），如 int 优于 Integer</h3>
 * <h3>2. 对于不确定的包装器类型，一定要检验是否为null</h3>
 * <h3>3. 对于值为null的包装器类型，赋值为0</h3>
 */
public class UnboxingNpe {

    private static int add(int x, int y) {
        return x + y;
    }

    private static boolean compare(long x, long y) {
        return x >= y;
    }

    public static void main(String[] args) {

        // 1. 变量赋值自动拆箱出现的空指针
        // 分析：1) javac UnboxingNpe.java 编译成字节码  2）javap -c UnboxingNpe.class
        Long cnt = null;
        long cnt_ = cnt;

        // 2. 方法传参时自动拆箱引发的空指针问题
//        Integer left = null, right = 2;
//        System.out.println(add(left, right));

        // 3. 用于比较大小的场景
//        Long first = 10L, second = null;
//        System.out.println(compare(first, second));
    }

}
