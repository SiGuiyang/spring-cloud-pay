package quick.pager.pay.common.mapper;

import java.io.Serializable;

/**
 * Mapper映射顶级接口
 *
 * @param <T>
 */
public interface IMapper<E extends Serializable, T> {

    /**
     * 插入
     * serverStatus
     *
     * @param record 泛型对象
     */
    int insertSelective(T record);

    /**
     * 根据主键查询数据库中的一条记录
     *
     * @param key 主键
     */
    T selectByPrimaryKey(E key);

    /**
     * 根据主键更新记录
     *
     * @param record 泛型对象
     */
    int updateByPrimaryKeySelective(T record);

}
