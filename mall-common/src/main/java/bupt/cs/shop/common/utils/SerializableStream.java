package bupt.cs.shop.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

/**
 * 序列化的input stream
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerializableStream {
    private String base64;

    public SerializableStream(InputStream inputStream) {
        this.base64 = Base64DecodeMultipartFile.inputStreamToStream(inputStream);
    }

}
 
