package com.imooc.java.escape;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <h1>数值计算和时间计算</h1>
 */
public class NumberAndTime {

    /**
     * <h2>Scale 需要与小数位匹配</h2>
     */
    public static void scaleProblem() {

        BigDecimal bigDecimal = new BigDecimal("12.345");
        // 异常：精度丢失
//        BigDecimal result = bigDecimal.setScale(2);
        // 正常
//        BigDecimal result = bigDecimal.setScale(6);
        // 舍入操作
        BigDecimal result = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        System.out.println(result);
    }

    /**
     * <h2>无法整除的情况</h2>
     */
    public static void divideProblem() {
        // 会抛出异常
//        System.out.println(new BigDecimal(30).divide(new BigDecimal(7)));
        // 指定精度，it works
        System.out.println(new BigDecimal(30).divide(new BigDecimal(7), 2, RoundingMode.HALF_UP));
    }

    /**
     * <h2>精度问题导致比较结果与预期不一致</h2>
     */
    public static void equalProblem() {
        BigDecimal bd1 = new BigDecimal("0");
        BigDecimal bd2 = new BigDecimal("0.0");
        System.out.println(bd1.equals(bd2));  // 返回 false
        System.out.println(bd1.compareTo(bd2) == 0);  // 返回 true
    }

    /**
     * <h2>SimpleDateFormat可以解析>=的时间精度；不能解析小的(更短的，信息不足)</h2>
     */
    public static void dateFormatPrecision() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time2 = "2020-03-12 10:00:00"; // 可以解析
        String time1 = "2020-03"; // 不能解析

        System.out.println(sdf.parse(time2));
        System.out.println(sdf.parse(time1));
    }

    /**
     * <h2>SimpleDateFormat 是线程不安全的</h2>
     * <h3>解决方法 1：将SimpleDateFormat定义为局部变量，局部变量保存在线程自己的栈中。最简单、常用</h3>
     * <h3>解决方法 2：使用 threadLocal，独立维护线程自己的数据。次选</h3>
     * <h3>解决方法 3：使用 Synchronized，用锁保证安全，性能开销大。少用</h3>
     */
    public static void threadSafety() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10, 100, 1, TimeUnit.MINUTES,
                new LinkedBlockingDeque<>(1000)
        );

        while(true) {
            threadPoolExecutor.execute(() -> {
                String dateString = "2020-10-11 10:00:00";
                try{
                    Date parseDate = sdf.parse(dateString);
                    String dateString2 = sdf.format(parseDate);
                    System.out.println(dateString.equals(dateString2));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) throws Exception {
//        scaleProblem();
//        divideProblem();
//        equalProblem();
//        dateFormatPrecision();
        threadSafety();
    }
}
