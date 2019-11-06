package com.example.ic10;

import java.util.HashMap;
import java.util.Map;

public class User {
    String name;
    int age;

    public User() {

    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Map toHashMap() {
        Map<String, Object> userMap = new HashMap<>();

        userMap.put("name", this.name);
        userMap.put("age", this.age);

        return userMap;
    }

    public User (Map userMap) {

//        User user = new User();
////
////        user.setName((String) userMap.get("name"));
////        user.setAge((int) (long) userMap.get("age"));
////
////        return user;

        this.name = (String) userMap.get("name");
        this.age = (int)(long) userMap.get("age");
    }
}
