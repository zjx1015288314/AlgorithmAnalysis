package com.zjx.HashCode测试;

/**
 * 重写hashcode  equals
 */
public class User {
    private String name;
    private int age;

    @Override
    public String toString() {
        return this.name + " " + this.age;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  //是否引用同一对象
        if(o == null) return false; //非空性
        if(getClass() != o.getClass()) return false;
        User user = (User) o;

        if(equalsName(this.name,user.name) && age == user.age){ return true;}
        return false;
    }


    public boolean equalsName(String s1, String s2){
        return s1 != null ? s1.equals(s2) : s2 == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }
}
