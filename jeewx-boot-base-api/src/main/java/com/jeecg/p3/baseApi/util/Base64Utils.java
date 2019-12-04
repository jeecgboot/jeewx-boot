package com.jeecg.p3.baseApi.util;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * @author zhuzhe
 * @date 2018/10/17 17:21
 * @email zhe.zhu1@outlook.com
 */
public class Base64Utils {

    public static MultipartFile base64ToMultipart(String base64) {
        try {
            String[] baseStr = base64.split(",");

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            b = decoder.decodeBuffer(baseStr[1]);

            for(int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new Base64DecodeMultipartFile(b, baseStr[0]);
        } catch (IOException e) {
            return null;
        }
    }
}
