package cn.excel.po;
import java.util.Objects;

/**
 * 通过表格中的属性映射成一个java类
 */
public class Student{
    private int id;
    private String name;
    private int age;
    private String sex;
    private String habit;

    public Student() {
    }

    public Student(int id, String name, int age, String sex, String habit) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.habit = habit;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                age == student.age &&
                Objects.equals(name, student.name) &&
                Objects.equals(sex, student.sex) &&
                Objects.equals(habit, student.habit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, sex, habit);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", habit='" + habit + '\'' +
                '}';
    }
}
