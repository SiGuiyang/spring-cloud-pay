package quick.pager.pay.channel.mapper;

import org.apache.ibatis.annotations.Param;
import quick.pager.pay.common.mapper.IMapper;
import quick.pager.pay.model.common.SystemConfig;

import java.util.List;

public interface SystemConfigMapper extends IMapper<Long, SystemConfig> {

    /**
     * 根据配置项名称查询配置项
     *
     * @param configName   配置项名称
     * @param serverStatus 配置项状态 0：禁用 1：启用
     * @param repeatType       0：不存在重复的key,1：存在重复的key
     * @param orderType    排序字段,数据库字段 id ,sequence
     */
    List<SystemConfig> selectSystemConfig(@Param("configName") String configName, @Param("serverStatus") Integer serverStatus, @Param("repeatType") Integer repeatType, @Param("orderType") String orderType);

    /**
     * 查询去重启用的配置项名称
     */
    List<String> selectDistinctConfig();
}