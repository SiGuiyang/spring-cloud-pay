package quick.pager.pay.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import quick.pager.pay.model.IModel;

import java.util.Date;

/**
 * 用户表<br />
 * <code>t_user</code>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class User extends IModel {
    private static final long serialVersionUID = -1580571728875892888L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 状态<br />
     * 2：正常
     * 3：冻结
     * 4：删除
     */
    private Integer serverStatus;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private String gender;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * token 值
     */
    private String token;

}
