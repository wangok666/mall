package bupt.cs.shop.buyer.pojo.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {

    private Long id;

    private Long categoryId;

    private String content;

    private Integer sort;

    private String title;

    private String type;

    private Boolean openStatus;

    private Boolean deleteFlag;

    private String createBy;

    private Date createTime;

    private Date updateTime;
}