package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.设计模式.singleton;
//正确单例演示
public enum SingletonEnum {
    INSTANCE;
    public SingletonEnum getInstance(){
        return INSTANCE;
    }

}
//错误单例演示   无法限制反射和 序列化
class User{
    private User(){}

     static enum SingletonEnum{
        INSTANCE;

        private User user;
        private SingletonEnum(){
            user = new User();
        }
        public User getInstance(){
            return user;
        }
    }
    public static User getInstance(){
        return SingletonEnum.INSTANCE.getInstance();
    }
}
