package application.recyclerviewdemo.util;

import java.util.ArrayList;
import java.util.List;

import application.recyclerviewdemo.model.Student;

/**
 * Created by Gao on 2018/11/29.
 */

public class DataUtil {

    public static List<Student> getDataList() {
        List<Student> stuList = new ArrayList<>();

        Student student = null;
        for (int index = 0; index < 30; index++) {
            student = new Student(index, "name=" + index);
            stuList.add(student);
            student = null;
        }

        return stuList;
    }
}
