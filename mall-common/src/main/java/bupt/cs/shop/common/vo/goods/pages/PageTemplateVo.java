package bupt.cs.shop.common.vo.goods.pages;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageTemplateVo implements Serializable {

    private String type;

    private Object pageData;



}
