package bupt.cs.shop.buyer.vo.goods.pages;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageTemplateVo implements Serializable {

    private String type;

    private Object pageData;



}
