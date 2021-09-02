package bupt.cs.shop.buyer.goods.vo.pages;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImageData implements Serializable {

    private String img;

    private String url;

    private String size;
}
