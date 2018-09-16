package quick.pager.pay.mapper.user;

import org.apache.ibatis.annotations.Param;
import quick.pager.pay.mapper.IMapper;
import quick.pager.pay.model.user.User;
import quick.pager.pay.vo.admin.UserVO;

import java.util.Date;
import java.util.List;

public interface UserMapper extends IMapper<Long, User> {

    /**
     * 根据用户名查询用户
     *
     * @param username   用户名
     * @param mobileFlag "1":表示手机号 "0": 表示用户名
     */
    User selectUserByUsernameOrMobile(@Param("username") String username, @Param("mobileFlag") String mobileFlag);

    List<UserVO> selectUser(@Param("username") String username, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    /**
     * 通过用户Id查看信息
     *
     * @param userId 用户Id
     */
    UserVO selectByUser(@Param("userId") Long userId);
}
