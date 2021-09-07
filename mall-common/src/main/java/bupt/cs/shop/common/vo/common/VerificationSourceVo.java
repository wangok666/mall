package bupt.cs.shop.common.vo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationSourceVo implements Serializable {

    private Long id;

    private String name;

    private String resource;

    private String type;

}