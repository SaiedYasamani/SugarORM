package Models;

import com.orm.SugarRecord;

/**
 * Created by Saied on 19/05/2017.
 */

public class Student extends SugarRecord {

    String name;
    String mobile;

    public Student(String name, String family) {
        this.name = name;
        this.mobile = family;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
