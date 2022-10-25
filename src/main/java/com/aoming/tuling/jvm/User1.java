package com.aoming.tuling.jvm;

/**
 * TODO
 *
 * @author ao921
 * @version 1.0
 * @date 2022/3/29 16:44
 */
public class User1 {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sout(){
        System.out.println("=======自己的加载器加载类调用方法======");
    }

    public void sout1(){
        System.out.println("=======另外一个User1版本：自己的加载器加载类调用方法=======");
    }

}

