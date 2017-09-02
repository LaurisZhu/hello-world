package nmid.zhu.pojo;

/**
 * Created by Lawrence on 2017/8/30.
 */
public class Manager {
    private String name;
    private String password;
    private String workId;

    public void setName(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public void setWorkId(String workId) {
        this.workId=workId;
    }

    public String getWorkId() {
        return workId;
    }

    public Manager() {}
    public Manager(String workId, String name, String password) {
        super();
        setName(name);
        setPassword(password);
        setWorkId(workId);
    }
}
