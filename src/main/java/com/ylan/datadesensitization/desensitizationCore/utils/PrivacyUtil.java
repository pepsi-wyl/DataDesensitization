package com.ylan.datadesensitization.desensitizationCore.utils;

/**
 * @author by pepsi-wyl
 * @date 2023-10-27 11:26
 * @description: 自定义数据脱敏工具类
 */
public class PrivacyUtil {

    /**
     * 对字符串进行脱敏操作
     * @param str            原始字符串
     * @param startInclude   左侧需要保留几位明文字段
     * @param endExclude     右侧需要保留几位明文字段
     * @param symbol         用于遮罩的字符串, 如'*'
     * @return
     */
    public static String hide(CharSequence str, int startInclude, int endExclude, char symbol) {
        if (isEmpty(str)) {
            return str(str);
        } else {
            String originalStr = str(str);
            int[] strCodePoints = originalStr.codePoints().toArray();
            int strLength = strCodePoints.length;
            if (startInclude > strLength) {
                return originalStr;
            } else {
                if (endExclude > strLength) {
                    endExclude = strLength;
                }

                if (startInclude > endExclude) {
                    return originalStr;
                } else {
                    StringBuilder stringBuilder = new StringBuilder();

                    for(int i = 0; i < strLength; ++i) {
                        if (i >= startInclude && i < endExclude) {
                            stringBuilder.append(symbol);
                        } else {
                            stringBuilder.append(new String(strCodePoints, i, 1));
                        }
                    }

                    return stringBuilder.toString();
                }
            }
        }
    }

    private static String str(CharSequence str) {
        return null == str ? null : str.toString();
    }

    private static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

}