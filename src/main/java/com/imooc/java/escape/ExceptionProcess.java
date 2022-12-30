package com.imooc.java.escape;

/**
 * <h1>Java 异常处理：当前 method/field 无法处理的问题</h1>
 * <h2>1. 抛出异常：当前方法无法处理异常了，程序执行不下去了；必须对异常执行抛出</h2>
 * <h2>2. 捕获异常：方法抛出异常后，runtime转为寻找合适的异常处理器。如果func自己的 catch 无法正确捕获异常，就只能向上传（在程序栈中依次寻找
 * 合适的异常处理器）；直到传到 main()。如果还不行，程序会中断退出。</h2>
 */
public class ExceptionProcess {

    private static class User {}

    /**
     * <h2>抛出异常</h2>
     */
    private void throwException() {
        User user = null;
        // 对user进行一些处理...
       if (null == user) {
           throw new NullPointerException();
       }
    }

    /**
     * <h2>1. 不能捕获异常: main() 方法异常中断退出</h2>
     */
    private void cannotCatchNPE() {
        try {
            throwException();
        } catch (ClassCastException cce) {
            System.out.println(cce.getMessage());
            System.out.println(cce.getClass().getName());
        }
    }

    /**
     * <h2>2. 成功捕获异常：方法用自己的异常处理器处理</h2>
     */
    private void catchNPE() {
        try {
            throwException();
        } catch (ClassCastException cce) {
            System.out.println(cce.getMessage());
            System.out.println(cce.getClass().getName());
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
            System.out.println(npe.getClass().getName());
        }
    }

    public static void main(String[] args) {
        ExceptionProcess ep = new ExceptionProcess();
        ep.catchNPE();
        ep.cannotCatchNPE();
    }

}
