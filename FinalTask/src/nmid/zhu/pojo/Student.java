package nmid.zhu.pojo;

/**
 * Created by Lawrence on 2017/8/29.
 */
public class Student implements Comparable {
    private String name;
    private int age;
    private String id;
    private String major;

    public void setName(String name){
        this.name=name;
    }
    public  void setAge(int age) {
        this.age=age;
    }
    public void setId(String id) {
        this.id=id;
    }
    public void setMajor(String major) {
        this.major=major;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getId() {
        return id;
    }
    public String getMajor() {
        return major;
    }
    public Student(){}

    /**
     *
     * @param name 姓名
     * @param age 年龄
     * @param id 学号
     * @param major 专业
     */
    public Student(String name,int age,String id, String major) {
        super();
        setName(name);
        setAge(age);
        setId(id);
        setMajor(major);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Student) {
            Student aStudent = (Student)o;
            return id.compareTo(aStudent.getId());
        }
        return 0;
    }
}
