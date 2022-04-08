package com.tuling.jvm;

/**
 * TODO
 *
 * @author ao921
 * @version 1.0
 * @date 2022/3/29 16:44
 */
public class User {
    private int id;
    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

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

    @Override
    protected void finalize() throws Throwable {
//        System.out.println(id+"：即将被回收");
    }
}
