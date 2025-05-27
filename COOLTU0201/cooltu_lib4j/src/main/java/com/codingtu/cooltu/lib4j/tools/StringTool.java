package com.codingtu.cooltu.lib4j.tools;

import com.codingtu.cooltu.lib4j.es.Es;

import java.util.ArrayList;
import java.util.List;

public class StringTool {
    /**************************************************
     *
     * 判断字符串是否为空。包括null和"","   "等
     *
     **************************************************/
    public static boolean isBlank(String text) {
        return text == null || text.trim().length() <= 0;
    }

    /**************************************************
     *
     * 判断字符串是否有值，不包括"","  "等
     *
     **************************************************/
    public static boolean isNotBlank(String text) {
        return !isBlank(text);
    }

    /**************************************************
     *
     * 格式化数字。比如12，格式化成4位的0012
     *
     * @param number 需要格式化的数字，整数型
     * @param digits 位数
     * @return 格式化的数字
     *
     **************************************************/
    public static String formatNumber(Object number, int digits) {
        StringBuilder sb = new StringBuilder();
        sb.append(number);
        int rest = digits - sb.length();
        if (rest > 0) {
            for (int i = 0; i < rest; i++) {
                sb.insert(0, "0");
            }
        }
        return sb.toString();
    }

    /**************************************************
     *
     * 将double类型进行格式化，精确到小数点后几位，并且四舍五入
     * bit为格式化的位数
     * trim为是否去掉最后一位的0
     * 例如：12.3956，bit=2，trim=true。结果为12.4
     *      12.3956，bit=2，trim=false。结果为12.40
     *
     **************************************************/
//    public static String parseNumber(double num, int bit, boolean trim) {
//        String type = null;
//        if (trim) {
//            type = "#." + repeatString(bit, "#");
//        } else {
//            type = "0." + repeatString(bit, "0");
//        }
//        return new DecimalFormat(type).format(num);
//    }
    public static String doubleToString(Double num, int bit, boolean trim) {
        if (bit < 0) {
            throw new RuntimeException("bit必须大于等于0");
        }

        if (num == null
                || num == 0
                || Double.isInfinite(num)
                || !Double.isFinite(num)
                || Double.isNaN(num)) {
            if (bit == 0 || trim) {
                return "0";
            } else {
                return "0." + StringTool.repeatString(bit, "0");
            }
        }

        boolean isNegative = num < 0;
        if (isNegative) {
            num = Math.abs(num);
        }
        String absStr = absDoubleToString(num, bit, trim);
        if (isNegative) {
            return "-" + absStr;
        }
        return absStr;
    }

    private static String absDoubleToString(Double num, int bit, boolean trim) {
        String numStr = String.valueOf(num);
        int dotIndex = numStr.indexOf(".");
        if (dotIndex < 0) {
            if (bit == 0 || trim) {
                return numStr;
            } else {
                return numStr + "." + StringTool.repeatString(bit, "0");
            }
        }

        String zhengshuStr = numStr.substring(0, dotIndex);
        String xiaoshuStr = numStr.substring(dotIndex + 1);

        int xiaoshuLen = xiaoshuStr.length();
        if (xiaoshuLen < bit) {
            if (trim) {
                return String.valueOf(num);
            } else {
                return String.valueOf(num) + StringTool.repeatString(bit - xiaoshuLen, "0");
            }
        } else if (xiaoshuLen == bit) {
            return String.valueOf(num);
        }

        if (bit == 0) {
            int zhengshu = Integer.parseInt(zhengshuStr);
            if (Integer.parseInt(xiaoshuStr.substring(0, 1)) >= 5) {
                zhengshu += 1;
            }
            return String.valueOf(zhengshu);
        }

        int xiaoshu1 = Integer.parseInt(xiaoshuStr.substring(0, bit));
        int xiaoshu2 = Integer.parseInt(xiaoshuStr.substring(bit, bit + 1));

        xiaoshuStr = String.valueOf(xiaoshu1);
        if (xiaoshu2 >= 5) {
            xiaoshu1 += 1;
        }
        String newXiaoshuStr = String.valueOf(xiaoshu1);
        if (xiaoshuStr.length() == newXiaoshuStr.length()) {
            xiaoshuStr = newXiaoshuStr;
        } else {
            xiaoshuStr = newXiaoshuStr.substring(1);
            zhengshuStr = String.valueOf(Integer.parseInt(zhengshuStr) + 1);
        }

        if (trim) {
            xiaoshuStr = StringTool.trimRight(xiaoshuStr, '0');
            if (StringTool.isBlank(xiaoshuStr)) {
                return zhengshuStr;
            } else {
                return zhengshuStr + "." + xiaoshuStr;
            }
        } else {
            return zhengshuStr + "." + xiaoshuStr;
        }
    }

    /**************************************************
     *
     * 重复拼接字符串
     *
     * @param times 重复几次
     * @param str 重复的字符串
     * @return 拼接的字符串
     *
     **************************************************/
    public static String repeatString(int times, String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**************************************************
     *
     * object转换成string
     *
     **************************************************/
    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        } else if (obj instanceof String) {
            return (String) obj;
        } else {
            return String.valueOf(obj);
        }
    }


    /**************************************************
     *
     * 判断是否为url
     *
     **************************************************/
    public static boolean isUrl(String str) {
        if (isBlank(str))
            return false;
        str = str.trim();
        return str.matches("^(http|https)://.+");
    }

    /**************************************************
     *
     * 判断给定的字符串，是否在给定的字符串数组中
     *
     **************************************************/
    @Deprecated
    public static boolean inRange(String str, String... strs) {
        return Es.strs(strs).has(str);
    }


    /**************************************************
     *
     * 切去后缀
     *
     **************************************************/
    public static String cutSuffix(String str, String suffix) {
        return str.substring(0, str.length() - suffix.length());
    }

    public static String cutSuffix(Class aClass, String suffix) {
        return cutSuffix(aClass.getSimpleName(), suffix);
    }

    /**************************************************
     *
     * 获取子字符串
     *
     **************************************************/
    public static List<String> getSubs(String oriStr, String startStr, String left, String right) {
        ArrayList<String> strs = new ArrayList<>();
        int fromIndex = 0;
        int endIndex;
        while (true) {
            if (StringTool.isNotBlank(startStr)) {
                fromIndex = oriStr.indexOf(startStr, fromIndex);
                if (fromIndex >= 0) {
                    fromIndex += startStr.length();
                } else {
                    break;
                }
            }


            fromIndex = oriStr.indexOf(left, fromIndex);
            if (fromIndex < 0) {
                break;
            }

            fromIndex += left.length();

            endIndex = oriStr.indexOf(right, fromIndex);
            if (endIndex < 0) {
                break;
            }

            strs.add(oriStr.substring(fromIndex, endIndex));

            fromIndex = endIndex + right.length();

        }
        return strs;

    }

    //严格模式
    public static String getSub(String oriStr, String startStr, String left, String right) {
        int startIndex = 0;
        if (isNotBlank(startStr)) {
            startIndex = oriStr.indexOf(startStr);
            if (startIndex < 0) {
                return null;
            }
            startIndex += startStr.length();
        }

        startIndex = oriStr.indexOf(left, startIndex);
        if (startIndex < 0) {
            return null;
        }
        startIndex += left.length();

        int endIndex = oriStr.length();
        if (isNotBlank(right)) {
            endIndex = oriStr.indexOf(right, startIndex);
            if (endIndex < 0) {
                return null;
            }
        }
        return oriStr.substring(startIndex, endIndex);
    }

    //获取子字符串，但是会判断首尾位置
    public static String getSub(String str, int startIndex, int endIndex) {
        int length = str.length();
        if (startIndex > endIndex) {
            int temp = startIndex;
            startIndex = endIndex;
            endIndex = temp;
        }

        if (startIndex < 0 || endIndex >= length) {
            return null;
        }

        return str.substring(startIndex, endIndex);
    }


    //修改字符串的角标，让角标在字符串的范围内
    private static int getIndexInStr(int length, int num) {
        if (num < 0) {
            return 0;
        }
        if (num >= length) {
            return length;
        }
        return num;
    }

    /**************************************************
     *
     * 获取子字符串
     * startIndex 为起点位置
     * bit 为截取几位
     *
     **************************************************/

    public static String getSubFromStart(String str, int startIndex, int bit) {
        int endIndex = startIndex + bit;
        return getSub(str, startIndex, endIndex);
    }

    /**************************************************
     *
     * 获取子字符串
     * endIndex 为终点位置
     * bit 为截取几位
     *
     **************************************************/
    public static String getSubFromEnd(String str, int endIndex, int bit) {
        int startIndex = endIndex - bit;
        return getSub(str, startIndex, endIndex);
    }

    public static String getSubFromEnd(String str, int bit) {
        return getSubFromEnd(str, str.length(), bit);
    }

    /**************************************************
     *
     * 获取子字符串
     *
     **************************************************/
    public static String getSubToEnd(String str, int bit) {
        return getSubToEnd(str, str.length(), bit);
    }

    public static String getSubToEnd(String str, int endIndex, int bit) {
        if (endIndex <= bit) {
            return null;
        }
        return getSub(str, 0, endIndex - bit);
    }


    /**************************************************
     *
     *
     *
     **************************************************/
    public static boolean contains(String str, char c) {
        return contains(false, str, c);
    }

    public static boolean contains(boolean isIgnoreCase, String str, char c) {
        if (StringTool.isBlank(str)) {
            return false;
        }
        char cNew = c;
        if (isIgnoreCase) {
            if (CharTool.isLower(c)) {
                cNew = ConvertTool.toUpper(c);
            } else if (CharTool.isUpper(c)) {
                cNew = ConvertTool.toLower(c);
            } else {
                isIgnoreCase = false;
            }
        }

        for (int i = 0; i < str.length(); i++) {
            if (isIgnoreCase) {
                if (c == str.charAt(i) || cNew == str.charAt(i)) {
                    return true;
                }
            } else {
                if (c == str.charAt(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    public static String getColorfulStr(String str, String color) {
        return "<font color='" + color + "'>" + str + "</font>";
    }

    public static String getColorfulStr(char str, String color) {
        return "<font color='" + color + "'>" + str + "</font>";
    }


    @Deprecated
    public static String getColorfulStr1(String str, String keyword, String defaultColor, String lightColor) {
        if (isBlank(str)) {
            return null;
        }

        if (isBlank(keyword)) {
            return getColorfulStr(str, defaultColor);
        }

        for (int i = 0; i < keyword.length(); i++) {
            char key = keyword.charAt(i);
            if (!contains(str, key)) {
                return null;
            }
        }
        return getColorfulStr(str, keyword, defaultColor, lightColor);
    }

    public static String getColorfulStrStrictMode(String str, String keyword,
                                                  String defaultColor, String lightColor) {
        return getColorfulStrStrictMode(false, str, keyword, defaultColor, lightColor);
    }

    public static String getColorfulStrStrictMode(boolean isIgnoreCase, String str, String keyword, String defaultColor, String lightColor) {
        if (isBlank(str)) {
            return null;
        }

        if (isBlank(keyword)) {
            return getColorfulStr(str, defaultColor);
        }

        for (int i = 0; i < keyword.length(); i++) {
            char key = keyword.charAt(i);
            if (!contains(isIgnoreCase, str, key)) {
                return null;
            }
        }
        return getColorfulStr(isIgnoreCase, str, keyword, defaultColor, lightColor);
    }

    private static String getColorfulStr(String str, String keyword, String defaultColor, String lightColor) {
        return getColorfulStr(false, str, keyword, defaultColor, lightColor);
    }

    private static String getColorfulStr(boolean isIgnoreCase, String str, String keyword, String defaultColor, String lightColor) {
        StringBuilder sb = new StringBuilder();

        int length = str.length();
        char c = str.charAt(0);
        boolean isContains = false;
        if (contains(isIgnoreCase, keyword, c)) {
            isContains = true;
        }
        if (length == 1) {
            return isContains ? getColorfulStr(str, lightColor) : getColorfulStr(str, defaultColor);
        }
        StringBuilder sub = new StringBuilder();
        sub.append(c);
        for (int i = 1; i < length; i++) {
            c = str.charAt(i);
            if (contains(isIgnoreCase, keyword, c)) {
                if (!isContains) {
                    //上一个不包含
                    String colorStr = getColorfulStr(sub.toString(), defaultColor);
                    sb.append(colorStr);
                    sub = new StringBuilder();
                }
                sub.append(c);
                isContains = true;
            } else {
                if (isContains) {
                    String colorStr = getColorfulStr(sub.toString(), lightColor);
                    sb.append(colorStr);
                    sub = new StringBuilder();
                }
                sub.append(c);
                isContains = false;
            }
        }
        if (isContains) {
            String colorStr = StringTool.getColorfulStr(sub.toString(), lightColor);
            sb.append(colorStr);
        } else {
            String colorStr = StringTool.getColorfulStr(sub.toString(), defaultColor);
            sb.append(colorStr);
        }

        return sb.toString();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        if ((len & 1) == 1) {
            s = "0" + s;
            ++len;
        }

        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }

        return data;
    }

    public static boolean isInteger(String str) {
        if (isBlank(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!CharTool.isNumber(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**************************************************
     *
     * trim
     *
     **************************************************/
    public static String trim(String str, char x) {
        if (str == null) {
            return null;
        }

        int left = getLeftIndex(str, x);
        int right = getRightIndex(str, x);

        if (left >= right) {
            return null;
        } else {
            return str.substring(left, right);
        }
    }

    public static String trimLeft(String str, char x) {
        if (str == null) {
            return null;
        }
        int index = getLeftIndex(str, x);

        if (index == str.length()) {
            return null;
        } else {
            return str.substring(index);
        }
    }

    public static String trimRight(String str, char x) {
        if (str == null) {
            return null;
        }

        int index = getRightIndex(str, x);
        if (index == 0) {
            return null;
        } else {
            return str.substring(0, index);
        }
    }

    private static int getLeftIndex(String str, char x) {
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != x) {
                break;
            }
            index++;
        }
        return index;
    }

    private static int getRightIndex(String str, char x) {
        int index = str.length();
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (c != x) {
                break;
            }
            index--;
        }
        return index;
    }
}
