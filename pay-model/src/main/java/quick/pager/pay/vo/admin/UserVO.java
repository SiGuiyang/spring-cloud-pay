package quick.pager.pay.vo.admin;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class UserVO implements Serializable {
    private static final long serialVersionUID = -1270305099243184875L;


    /**
     * 主键Id
     */
    private Long userId;
    /**
     * 角色Id
     */
    private Long roleId;
    /**
     * 状态
     */
    private Integer serverStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除标志<br />
     * true 删除<br />
     * false 未删除<br />
     */
    private Boolean deleteStatus;
    /**
     * 用户名
     */
    private String username;
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
    /**
     * 角色名称
     */
    private String roleName;
}
