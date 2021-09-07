package bupt.cs.shop.buyer.pojo.common.slider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationSource {

    private Long id;

    private String name;

    private String resource;

    private String type;

    private Boolean deleteFlag;

}
