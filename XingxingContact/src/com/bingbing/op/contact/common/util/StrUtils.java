package com.bingbing.op.contact.common.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * String process utilities.
 * 
 */
public class StrUtils
{
    private static char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    /**
     * Checks whether the given string is null or has zero length.
     * 
     * @param str String to test.
     * @return true when the given string is null or zero length, or false.
     */
    public static boolean isEmpty(String str)
    {
        return (str == null) || (str.length() == 0);
    }

    /**
     * Retrieves either the original string or an empty one if the argument is null.
     * 
     * @param value Original string.
     * @return Not null string;
     */
    public static String notNullStr(String value)
    {
        return (value == null) ? "" : value;
    }

    /**
     * Converts given string to an integer value in a safe way.
     * 
     * @param str string to convert
     * @param dft default value in case the string cannot be converted
     * @return integer value represented in the string, or the default value
     */
    public static int toInt(String str, int dft)
    {
        if (isEmpty(str)) { return dft; }

        int ret = dft;
        try
        {
            ret = Integer.parseInt(str);
        }
        catch (NumberFormatException ex)
        {
            ret = dft;
        }

        return ret;
    }

    /**
     * Converts given string to a long value in a safe way.
     * 
     * @param str string to convert
     * @param dft default value in case the string cannot be converted
     * @return long value represented in the string, or the default value
     */
    public static long toLong(String str, long dft)
    {
        if (isEmpty(str)) { return dft; }

        long ret = dft;
        try
        {
            ret = Long.parseLong(str);
        }
        catch (NumberFormatException ex)
        {
            ret = dft;
        }

        return ret;
    }

    /**
     * Converts given string to a byte value in a safe way.
     * 
     * @param str string to convert
     * @param dft default value in case the string cannot be converted
     * @return long value represented in the string, or the default value
     */
    public static byte toByte(String str, byte dft)
    {
        return (byte)toInt(str, dft);
    }

    /**
     * Converts given string to a float value in a safe way.
     * 
     * @param str string to convert
     * @param dft default value in case the string cannot be converted
     * @return float value represented in the string, or the default value
     */
    public static float toFloat(String str, float dft)
    {
        if (isEmpty(str)) { return dft; }

        float ret = dft;
        try
        {
            ret = Float.parseFloat(str);
        }
        catch (NumberFormatException ex)
        {
            ret = dft;
        }

        return ret;
    }

    /**
     * Converts the given string to a boolean value in a safe way. True can be represented as: "1",
     * "yes", "true", "ok" or a number > 0. False can be represented as: "0", "no", "false".
     * 
     * @param str string to convert
     * @param dft default value in case the string cannot be converted
     * @return boolean value represented in the string, or the default value
     */
    public static boolean toBoolean(String str, boolean dft)
    {
        if (isEmpty(str)) { return dft; }

        boolean ret = dft;

        if (str.equals("1"))
        {
            ret = true;
        }
        else if (str.equals("0"))
        {
            ret = false;
        }
        else
        {
            int value = toInt(str, 0);
            if (value > 0)
            {
                ret = true;
            }
            else
            {
                String uStr = str.toUpperCase();
                if (uStr.equals("TRUE") || uStr.equals("YES") || uStr.equals("OK"))
                {
                    ret = true;
                }
                else if (uStr.equals("FALSE") || uStr.equals("NO"))
                {
                    ret = false;
                }
            }
        }

        return ret;
    }

    /**
     * Private construtors means that the class is just a utilities and it is not allowed to extend.
     */
    private StrUtils()
    {
        // empty here
    }

    /**
     * Checks whether the given string is email or no.
     * 
     * @param str String to test.
     * @return true when the given string is email , or false.
     */
    public static boolean isEmail(String str)
    {
        Pattern EMAIL_ADDRESS = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "("
                + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");
        Pattern p = EMAIL_ADDRESS;
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static String bytes2HexString(byte[] b, String separator)
    {
        if (b != null)
        {
            StringBuffer sb = new StringBuffer(b.length * 2);
            for (int i = 0; i < b.length; i++)
            {
                // look up high nibble char
                sb.append(hexChars[(b[i] & 0xf0) >>> 4]); // fill left with zero
                                                          // bits
                // look up low nibble char
                sb.append(hexChars[b[i] & 0x0f]);

                if (!isEmpty(separator) && (i < b.length - 1))
                {
                    sb.append(separator);
                }
            }
            return sb.toString();
        }
        else
        {
            return "";
        }
    }

    /**
     * Extracts the value part of the token starting with the specified pattern. Tokens are
     * delimited with sSep. Case is ignored.
     * 
     * Example: GetValueStartingWith("a=15;b=-23;c=3", "b=", ";", "0") ==> "-23".
     * 
     * Warning! GetValueStartingWith("stt=5;t=abc", "t=", ";", "0") ==> "5" !!!
     * 
     * @param sStr string String to search in.
     * @param sPtt string Pattern the value starts with.
     * @param sSep string Tokens separator.
     * @param sDft string Default value in case pattern is not found.
     * @return Value part of the token.
     */
    public static String getValueStartingWith(final String sStr, final String sPtt, final String sSep, final String sDft)
    {
        if (isEmpty(sStr)) { return sDft; }

        String ret = sDft;
        try
        {
            int pos = sStr.indexOf(sPtt);
            if (pos < 0)
            {
                pos = sStr.toUpperCase().indexOf(sPtt.toUpperCase());
            }
            if (pos >= 0)
            {
                pos += sPtt.length();

                int end = sStr.indexOf(sSep, pos);
                if (end >= pos)
                {
                    ret = sStr.substring(pos, end);
                }
                else
                {
                    ret = sStr.substring(pos);
                }
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }

        return ret;
    }

    public static String getPinyinFromChinese(String src)
    {
        return makeStringByStringSet(getPinyin(src));
    }

    /**
     * 字符串集合转换字符串(逗号分隔)
     * 
     * @param stringSet
     * @return
     */
    public static String makeStringByStringSet(Set<String> stringSet)
    {
        StringBuilder str = new StringBuilder();
        int i = 0;
        for (String s : stringSet)
        {
            if (i == stringSet.size() - 1)
            {
                str.append(s);
            }
            else
            {
                str.append(s + ",");
            }
            i++;
        }
        return str.toString().toLowerCase();
    }

    public static boolean isPinyin(String src)
    {
        if (src != null && !src.trim().equalsIgnoreCase(""))
        {
            char[] srcChar;
            srcChar = src.toCharArray();
            char c = srcChar[0];
            return String.valueOf(c).matches("[\\u4E00-\\u9FA5]+");
        }
        return false;
    }

    /**
     * 获取拼音集合
     * 
     * @param src
     * @return Set<String>
     */
    public static Set<String> getPinyin(String src)
    {
        if (src != null && !src.trim().equalsIgnoreCase(""))
        {
            char[] srcChar;
            srcChar = src.toCharArray();
            // 汉语拼音格式输出类
            HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();

            // 输出设置，大小写，音标方式等
            hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

            String[][] temp = new String[src.length()][];
            for (int i = 0; i < srcChar.length; i++)
            {
                char c = srcChar[i];
                // 是中文或者a-z或者A-Z转换拼音(我的需求，是保留中文或者a-z或者A-Z)
                if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+"))
                {
                    try
                    {
                        temp[i] = PinyinHelper.toHanyuPinyinStringArray(srcChar[i], hanYuPinOutputFormat);
                    }
                    catch (BadHanyuPinyinOutputFormatCombination e)
                    {
                        e.printStackTrace();
                    }
                }
                else if (((int)c >= 65 && (int)c <= 90) || ((int)c >= 97 && (int)c <= 122))
                {
                    temp[i] = new String[] { String.valueOf(srcChar[i]) };
                }
                else
                {
                    temp[i] = new String[] { "" };
                }
            }
            String[] pingyinArray = Exchange(temp);
            Set<String> pinyinSet = new HashSet<String>();
            for (int i = 0; i < pingyinArray.length; i++)
            {
                pinyinSet.add(pingyinArray[i]);
            }
            return pinyinSet;
        }
        return null;
    }

    /**
     * 递归
     * 
     * @param strJaggedArray
     * @return
     */
    public static String[] Exchange(String[][] strJaggedArray)
    {
        String[][] temp = DoExchange(strJaggedArray);
        return temp[0];
    }

    /**
     * 递归
     * 
     * @param strJaggedArray
     * @return
     */
    private static String[][] DoExchange(String[][] strJaggedArray)
    {
        int len = strJaggedArray.length;
        if (len >= 2)
        {
            int len1 = strJaggedArray[0].length;
            int len2 = strJaggedArray[1].length;
            int newlen = len1 * len2;
            String[] temp = new String[newlen];
            int Index = 0;
            for (int i = 0; i < len1; i++)
            {
                for (int j = 0; j < len2; j++)
                {
                    temp[Index] = strJaggedArray[0][i] + strJaggedArray[1][j];
                    Index++;
                }
            }
            String[][] newArray = new String[len - 1][];
            for (int i = 2; i < len; i++)
            {
                newArray[i - 1] = strJaggedArray[i];
            }
            newArray[0] = temp;
            return DoExchange(newArray);
        }
        else
        {
            return strJaggedArray;
        }
    }

}
