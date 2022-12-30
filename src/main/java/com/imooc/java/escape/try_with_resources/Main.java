package com.imooc.java.escape.try_with_resources;

import java.io.*;

/**
 * <h1>解决使用 try ... finally 的资源泄露隐患</h1>
 */
public class Main {

    /**
     * 传统方式实现对资源的关闭
     */
    private String traditionalTryCatch() throws IOException {

        // 1. 对单一资源的关闭
//        String line = null;
//        BufferedReader br = new BufferedReader(new FileReader(""));
//        try{
//            line = br.readLine();
//        } finally {
//            br.close();
//        }
//        return line;

        // 2. 对多个资源的关闭
        InputStream is = new FileInputStream("");
        try{
            OutputStream os = new FileOutputStream("");
            try{
                byte[] buf = new byte[100];
                int n;
                while((n=is.read(buf)) >= 0)
                    os.write(buf, 0, n);
            } finally {
                os.close();
            }
        } finally {
            is.close();
        }
        return null;
    }

    /**
     * Java7引入的 try with resources实现自动资源管理
     */
    private String tryWithResources() throws IOException {

        // 1. 单个资源的使用与关闭
//        try(BufferedReader br = new BufferedReader(new FileReader(""))) {
//            return br.readLine();
//        }

        // 2. 多个资源的使用与关闭
        try(FileInputStream is = new FileInputStream("");
            FileOutputStream os = new FileOutputStream("")
        ) {
            byte[] buf = new byte[100];
            int n = 0;
            while ((n=is.read(buf)) != -1) {
                os.write(buf, 0, n);
            }
        }
        return null;
    }

    public static void main(String[] args) throws MyException {

        // 传统方式，close() 的异常会掩盖 work() 的异常
//        AutoClose autoClose = new AutoClose();
//        try{
//            autoClose.work();
//        } finally {
//            autoClose.close();
//        }

        // try with resource 方式，符合预期
        try(AutoClose autoClose = new AutoClose()) {
            autoClose.work();
        }
    }
}
