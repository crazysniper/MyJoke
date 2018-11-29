package application.recyclerviewdemo.model;

import java.io.Serializable;

/**
 * Created by Gao on 2018/11/29.
 */

public class Student implements Serializable{
    private int id;
    private String name;

    public Student(int id, String name) {
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
}
