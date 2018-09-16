package quick.pager.pay.model.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据模型基类
 */
public class IModel implements Serializable {

    private static final long serialVersionUID = 7719141002563006081L;

    /**
     * 主键Id
     */
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
