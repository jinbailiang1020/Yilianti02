package com.embracesource.yilianti.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.icu.text.Normalizer.YES;

/**
 * Created by Administrator on 2017/10/23 0023.
 */

public class FormatUtils {

    public static Boolean isInValidPwd(String pwd) {
        return pwd != null && pwd.length() < 6;
    }


    public static Boolean isInValidPhoneNumber(String mobileNum) {
        if (mobileNum == null || mobileNum.length() != 11) {
            return false;
        }
        /**
         * 手机号码:
         * 13[0-9], 14[5,7], 15[0, 1, 2, 3, 5, 6, 7, 8, 9], 17[0, 1, 6, 7, 8], 18[0-9]
         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
         * 电信号段: 133,149,153,170,173,177,180,181,189
         */
        String MOBILE = "^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$";
        /**
         * 中国移动：China Mobile
         * 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         */
        String CM = "^1(3[4-9]|4[7]|5[0-27-9]|7[08]|8[2-478])\\d{8}$";
        /**
         * 中国联通：China Unicom
         * 130,131,132,145,155,156,170,171,175,176,185,186
         */
        String CU = "^1(3[0-2]|4[5]|5[56]|7[0156]|8[56])\\d{8}$";
        /**
         * 中国电信：China Telecom
         * 133,149,153,170,173,177,180,181,189
         */
        String CT = "^1(3[3]|4[9]|53|7[037]|8[019])\\d{8}$";
        return !match(MOBILE, mobileNum) || match(CM, mobileNum) || match(CU, mobileNum) || match(CT, mobileNum);
    }

    private static boolean match(String regex, String str) {

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(str);

        return matcher.matches();

    }

}
