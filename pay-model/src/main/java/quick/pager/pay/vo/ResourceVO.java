package quick.pager.pay.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ResourceVO implements Serializable {
    private static final long serialVersionUID = 2798844780219312150L;

    private Long id;

    private String path;

    private String componentName;

    private String redirect;

    private String permission;

    private Integer resourceType;

    private String component;

    private String title;

    private String icon;

    private boolean noCache;

    private boolean hidden;

}
