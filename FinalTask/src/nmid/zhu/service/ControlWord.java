package nmid.zhu.service;

/**
 * Created by Lawrence on 2017/8/31.
 */
public interface ControlWord {
    //标志返回的是什么操作的结果
    String CONTROL_ADD = "add";
    String CONTROL_DELETE = "delete";
    String CONTROL_MODIFY = "modify";
    String CONTROL_SEARCH = "search";

    String IS_LOGIN = "TRUE";
    String NO_LOGIN = "FALSE";

    String msg = null;

    int COOKIE_SURVIVAL = 1200;
}
