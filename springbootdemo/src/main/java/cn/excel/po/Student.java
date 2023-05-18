package cn.excel.po;
import java.util.Objects;
/**
 * @Author 张帅虎
 * @Data 2022/9/6
 */
/**
 * 通过表格中的属性映射成一个java类
 */
public class Student{
    private int id;
    private String name;
    private int age;
    private String sex;
    private String habit;
    private Integer departmentid;
    private Integer courseid;


    public Student(){}

    public Student(int id, String name, int age, String sex, String habit, Integer departmentid, Integer courseid) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.habit = habit;
        this.departmentid = departmentid;
        this.courseid = courseid;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    public Integer getDepartmentid() {
        return departmentid;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", habit='" + habit + '\'' +
                ", departmentid=" + departmentid +
                ", courseid=" + courseid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && age == student.age && Objects.equals(name, student.name) && Objects.equals(sex, student.sex) && Objects.equals(habit, student.habit) && departmentid.equals(student.departmentid) && courseid.equals(student.courseid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, sex, habit, departmentid, courseid);
    }
}
