package bupt.cs.shop.buyer.pojo.page;

import lombok.Data;

@Data
public class TemplateDetail {

    private Long id;

    private Long templateId;

    private String templateType;

    private String templateData;

    private Integer status;

    private Long createTime;
}
