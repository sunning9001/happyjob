package com.happy.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.happy.controller.base.BaseController;
import com.happy.service.user.data.UpImgData;
import com.happy.service.user.data.UpImgMsg;
import com.happy.util.pubConst.Const;
import com.happy.util.sms.SmsUtil;
import com.happy.util.wxUtil.WxAppParamsEnum;
import com.happy.util.wxUtil.WxModelConst;

/**
 * 
 * @TODO: 工具类
 */
public class Util {
    
    private static final Logger logger = LoggerFactory.getLogger(Util.class);
    

    public static boolean isEmpty(Object obj) {
        if (obj != null) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isEmpty(String str) {
        if (str != null && !("".equals(str)) && !("undefined").equals(str)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isEmptyList(List<?> list) {
        if (list != null && !(list.isEmpty())) {
            return false;
        } else {
            return true;
        }
    }

    
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath);
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        if (!Util.isEmpty(tempList) && tempList.length > 0) {
            for (int i = 0; i < tempList.length; i++) {
                if (path.endsWith(File.separator)) {
                    temp = new File(path + tempList[i]);
                } else {
                    temp = new File(path + File.separator + tempList[i]);
                }
                if (temp.isFile()) {
                    temp.delete();
                }
                if (temp.isDirectory()) {
                    delAllFile(path + "/" + tempList[i]);
                    delFolder(path + "/" + tempList[i]);
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * @TODO:     _转驼峰命名
     */
    public static String convUnderline2Camel(String field) {
        // 先全部小写
        String field1 = field.toLowerCase();
        if (field1.contains("_")) {
            String[] str = field1.split("_");
            StringBuffer sb = new StringBuffer();
            sb.append(str[0]);
            for (int i = 1; i < str.length; i++) {
                String s = str[i];
                sb.append(Character.toUpperCase(s.charAt(0))).append(
                        s.substring(1));
            }
            return sb.toString();
        } else {
            return field1;
        }
    }

    /**
     * @TODO:获取本机访问地址
     * 
     */
    public static String getIp() {
        String ip = "";
        try {
            InetAddress inet = InetAddress.getLocalHost();
            ip = inet.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return ip;
    }


    // 发送请求并接受返回的数据
    public static String sendPost(String baseUrl, String params) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(baseUrl);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

 // 发送请求并接受返回文件流
    public static String sendPostReFile(String baseUrl, String params,String leftPath) {
        PrintWriter out = null;
        InputStream in = null;
        BufferedReader reader = null;
        String result = "";
        try {
            URL realUrl = new URL(baseUrl);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(params);
            // flush输出流的缓冲
            out.flush();
            String contentType = conn.getContentType();
            if(contentType != null && contentType.matches(Const.HP_RESPONSE_CONTENT_TYPE_FORMAT)) { // 图片流
                in = conn.getInputStream();
                return BaseController.uploadFileInput(in, leftPath);
            }
            // 普通数据流
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "utf-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if(reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    /**
     * 
     * @TODO:base64还原字符串并根据前后位数截取
     */
    public static String BASE64DecoderStr(String str, int fnum, int lnum) {
        byte[] bt = Base64.decodeBase64(str);
        str = new String(bt);
        str = str.substring(fnum);
        str = str.substring(0, str.length() - lnum);
        return str;
    }


    /**
     * @TODO:判断手机号是否合法
     */
    public static boolean checkPhone(String phone) {
        String regex = "^1[34578]\\d{9}$";
        if (Util.isEmpty(phone)) {
            return false;
        } else if (phone.length() != 11) {
            return false;
        } else if (!phone.matches(regex)) {
            return false;
        }
        return true;
    }


    // 获取登录地址
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }


    /**
             *    获取uuid
     */
    public static String getUuidRd() {

        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * @TODO: 判断两个DATE格式日期是否同一天 （返回天数）
     * @param d1
     * @param d2
     * @return -1 参数有空 -2 不是同一年 其余返回后者键前者日期的差
     */
    public static boolean dateInSameDay(Date d1, Date d2) {
        if (Util.isEmpty(d1) || Util.isEmpty(d2)) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d1);
        int year1 = calendar.get(Calendar.YEAR);
        int days1 = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTime(d2);
        int year2 = calendar.get(Calendar.YEAR);
        int days2 = calendar.get(Calendar.DAY_OF_YEAR);
        if (year1 == year2 && days1 == days2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @TODO: 判断两个DATE日期查几天
     * @param d1
     * @param d2
     */
    public static int dateInSameDay2(Date date1, Date date2) {
        if (Util.isEmpty(date1) || Util.isEmpty(date2)) {
            return -1;
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) // 同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) // 闰年
                {
                    timeDistance += 366;
                } else // 不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else // 不同年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }

    /**
     * @TODO: 判断两个DATE格式日期是否同一天 （返回天数）
     * @param d1
     * @param d2
     * @return -1 参数有空 -2 不是同一年 其余返回后者键前者日期的差
     */
    public static boolean dateInSameMouth(Date d1, Date d2) {
        if (Util.isEmpty(d1) || Util.isEmpty(d2)) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d1);
        int year1 = calendar.get(Calendar.YEAR);
        int mouth1 = calendar.get(Calendar.MONTH);
        calendar.setTime(d2);
        int year2 = calendar.get(Calendar.YEAR);
        int mouth2 = calendar.get(Calendar.MONTH);
        if (year1 == year2 && mouth1 == mouth2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @TODO: 判断两个DATE格式日期是否同一年
     * @param d1
     * @param d2
     */
    public static boolean dateInSameYear(Date d1, Date d2) {
        if (Util.isEmpty(d1) || Util.isEmpty(d2)) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d1);
        int year1 = calendar.get(Calendar.YEAR);
        calendar.setTime(d2);
        int year2 = calendar.get(Calendar.YEAR);
        if (year1 == year2) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * @TODO:获取现在时间
     * 
     * @return返回长时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        Date currentTime = getCurrentDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * @TODO:获取现在时间
     * 
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDateShort() {
        Date currentTime = getCurrentDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     * 
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = getCurrentDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }



    /**
     * @TODO: 给定格式、时间字符串获取DATE格式时间
     * @param date
     */
    public static Date getDateFromStr(String date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date dateRes = null;
        if (!isEmpty(date)) {
            try {
                dateRes = format.parse(date);
            } catch (ParseException e1) {
                dateRes = null;
                e1.printStackTrace();
            }
        }
        return dateRes;
    }

    /**
     * @TODO: 给定时间字符串获取Time格式时间
     * @param Time
     */
    public static Time getTimeFromStr(String time) {
        if (!isEmpty(time) && time.matches(Const.TIME_MATCH_REGEX)) {
            try {
                return Time.valueOf(time);
            } catch (Exception e1) {
                logger.info(time, "时间TIME格式字符串转换失败===========");
            }
        }
        return null;
    }


    // 类型转换字符串转换为基本类型的包装类型
    public static Object typeChange(String str, Class<?> toClass) {
        Object obj = null;
        if(Util.isEmpty(str)) {
            return null;
        }
        try {
            Method method = toClass.getMethod("valueOf", String.class);
            obj = method.invoke(toClass, str);
        } catch (Exception e) {
            logger.error("类型转换出现异常",e);
        }
        return obj;
    }


    // 时间格式转换为字符串格式
    public static String dateFormat(Date date, String format) {
        SimpleDateFormat f = new SimpleDateFormat(format);
        String str = null;
        try {
            str = f.format(date);
        } catch (Exception e) {
            logger.error("日期格式转换出现异常",e);
        }

        return str;
    }

    // 获取当前项目地址
    public static String getBaseUrl(HttpServletRequest request) {
        String address = null;
        String serverName = request.getServerName();
        if ("localhost".equals(serverName) || isboolIp(serverName)) {
            address = request.getScheme() + "://" + serverName + ":"
                    + request.getLocalPort() + request.getContextPath() + "/";
        } else {
            address = request.getScheme() + "://" + serverName + "/";
        }
        System.out.println(address);
        return address;
    }

    /** * 判断是否为合法IP * @return the ip */
    public static boolean isboolIp(String ipAddress) {
        String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }

    /**
    *
    * @TODO:     北京时间
    */
    public static Date getCurrentDate() {
        Calendar cal = Calendar.getInstance(
            TimeZone.getTimeZone("Asia/Urumqi"), Locale.CHINA);
        return cal.getTime();
    }
    /**
     *
     * @TODO:     时间对应的秒数
     */
    public static long getDateSecond(Date d) {
        if(d == null ) {
            return 0;
        }
        return d.getTime()/1000;
    }

    // 获取北京时间
    public static Date getCurrentDate(String formatStr) {
        String webUrl3 = "http://www.taobao.com";
        String str = getWebsiteDatetime(webUrl3);
        DateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    // 获取网络时间
    private static String getWebsiteDatetime(String webUrl) {
        try {
            URL url = new URL(webUrl);// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象
            uc.connect();// 发出连接
            long ld = uc.getDate();// 读取网站日期时间
            Date date = new Date(ld);// 转换为标准时间对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                    Locale.CHINA);// 输出北京时间
            return sdf.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @TODO:获取当前日期是星期几
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }



   


    /**
     * @TODO:生成制定规则随机字符串
     * @param length
     */
    public static String getRandomStringByLength(int length, String type) {
        String str1 = "abcdefghijklmnopqrstuvwxyz";
        String str2 = "0123456789";
        String base = str1 + str2;
        if ("az".equals(type)) { // 全小写字母
            base = str1;
        } else if ("AZ".equals(type)) { // 全大写字母
            base = str1.toUpperCase();
        } else if ("0".equals(type)) { // 全数字
            base = str2;
        } else if ("aA".equals(type)) { // 大小写字母
            base = str1 + str2;
        } else if ("total".equals(type)) { // 大小写字母
            base = str1 + str1.toUpperCase() + str2;
        }

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    /**
     * @TODO: 保留两位小数
     */
    public static double getTwoDecimal(double d) {
        BigDecimal bg = new BigDecimal(d);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
        return f1;
    }

    /**
     * @TODO: 获取当前时间后面几天的时间
     * @param days
     */
    public static Date getNextTime(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * @TODO:发送POST请求
     * 
     * @param url : 目的地址
     * @param parameters:请求参数，Map类型。
     */
    public static String sendPost(String url, Map<String, String> parameters) {
        String result = "";// 返回的结果
        BufferedReader in = null;// 读取响应输入流
        PrintWriter out = null;
        StringBuffer sb = new StringBuffer();// 处理请求参数
        String params = "";// 编码之后的参数
        try {
            // 编码请求参数
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name)
                            .append("=")
                            .append(java.net.URLEncoder.encode(
                                    parameters.get(name), "UTF-8"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name)
                            .append("=")
                            .append(java.net.URLEncoder.encode(
                                    parameters.get(name), "UTF-8")).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            // 创建URL对象
            java.net.URL connURL = new java.net.URL(url);
            // 打开URL连接
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
                    .openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 设置POST方式
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式
            in = new BufferedReader(new InputStreamReader(
                    httpConn.getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    /** 获取两个数之间的随机数 */
    public static int getRdNum(int start, int end) {
        Random random = new Random();
        int num = random.nextInt(end);
        return num;
    }


    /**
     * @TODO 普通MD5
     */
    public static String MD5(String input) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        char[] charArray = input.toCharArray();
        byte[] byteArray = new byte[charArray.length];
 
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
 
     
     
     
    /**
     * @TODO 加盐MD5
     * saltBase 16位以内,
     */
        public static String generateMD5(String password,String saltBase) {
//            Random r = new Random();
            StringBuilder sb = new StringBuilder(16);
//            sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
            sb.append(saltBase);
            int len = sb.length();
            if (len < 16) {
                for (int i = 0; i < 16 - len; i++) {
                    sb.append("0");
                }
            }
            String salt = sb.toString();
            password = md5Hex(password + salt);
            char[] cs = new char[48];
            for (int i = 0; i < 48; i += 3) {
                cs[i] = password.charAt(i / 3 * 2);
                char c = salt.charAt(i / 3);
                cs[i + 1] = c;
                cs[i + 2] = password.charAt(i / 3 * 2 + 1);
            }
            return new String(cs);
        }
        /**
         * @TODO 校验加盐后是否和原文一致
         */
        public static boolean verify(String password, String md5) {
            char[] cs1 = new char[32];
            char[] cs2 = new char[16];
            for (int i = 0; i < 48; i += 3) {
                cs1[i / 3 * 2] = md5.charAt(i);
                cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
                cs2[i / 3] = md5.charAt(i + 1);
            }
            String salt = new String(cs2);
            return md5Hex(password + salt).equals(new String(cs1));
        }
        /**
         * @TODO 获取十六进制字符串形式的MD5摘要
         */
        private static String md5Hex(String src) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                byte[] bs = md5.digest(src.getBytes());
                return new String(new Hex().encode(bs));
            } catch (Exception e) {
                return null;
            }
        }


    
    //默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;
 
    /**
     * @TODO: 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double doubleAdd(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    /**
     * @TODO: 提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double doubleSub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    } 
    /**
     * @TODO: 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double doubleMul(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
 
    /**
     * @TODO: 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到小数点以后10位，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double doubleDiv(double v1,double v2){
        return doubleDiv(v1,v2,DEF_DIV_SCALE);
    }
 
    /**
     * @TODO: 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double doubleDiv(double v1,double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
 
    /**
     * @TODO: 提供精确的小数位四舍五入处理。
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double doubleRound(double v,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    /**
     * @TODO: 提供精确的小数位向下取值处理。
     * @param v 需要向下取值的数字
     * @param scale 小数点后保留几位
     * @return 的结果
     */
    public static double doubleRoundDown(double v,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one,scale,BigDecimal.ROUND_FLOOR).doubleValue();
    }
    
    
    /**
     * @TODO: get请求
     */
    public static String sendRequestGet(String url) {
        URL realUrl;
        StringBuffer bs = new StringBuffer();
        InputStream is = null;
        try {
            realUrl = new URL(url);
            HttpURLConnection urlcon = (HttpURLConnection) realUrl
                    .openConnection();
            urlcon.connect();
            is = urlcon.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"));
            String l = null;
            while ((l = buffer.readLine()) != null) {
                bs.append(l);
            }
            logger.info("sendRequestGetPlus 返回信息=={}", bs.toString());
        } catch (MalformedURLException e) {
            logger.error("sendRequestGetPlus 请求异常", e);
        } catch (IOException e) {
            logger.error("sendRequestGetPlus 请求异常", e);
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("sendRequestGetPlus 请求关闭异常", e);
                }
            }
        }

        return bs.toString();
    }
    /**
     * Description:  判断操作系统(设备)
     */
    public static String getEquipment(String User_Agent) {
        String result= "";
        if (User_Agent.contains("Android")) {
            result = "Android";
        } else if (User_Agent.contains("iPhone")) {
            result = "iPhone";
        } else if (User_Agent.contains("iPad")) {
            result = "iPad";
        } else if (User_Agent.contains("Windows")) {
            result = "Windows";
        } else if (User_Agent.contains("Linux")) {
            result = "Linux";
        }else {
            result = "unknown";
        }
        return result;
    };
    /**
     * @TODO:   判断浏览器
     */
    public static String getBrowser(String user_agent) {
        String result= "";
        if(user_agent.indexOf("SogouMobileBrowser") != -1){//    "SogouMobileBrowser":"搜狗手机浏览器",
            result = "SogouMobileBrowser";
        }else if(user_agent.indexOf("UCBrowser") != -1) {//    "UCBrowser":"UC浏览器",
            result = "UCBrowser";
        }else if(user_agent.indexOf("UCWEB") != -1) {//    "UCWEB":"UC浏览器",
            result = "UCWEB";
        }else if(user_agent.indexOf("Opera") != -1) {//    "Opera":"Opera浏览器",
            result = "Opera";
        }else if(user_agent.indexOf("QQBrowser") != -1) {//    "QQBrowser":"QQ浏览器",
            result = "QQBrowser";
        }else if(user_agent.indexOf("TencentTraveler") != -1) {//    "TencentTraveler":"QQ浏览器",
            result = "TencentTraveler";
        }else if(user_agent.indexOf("MetaSr") != -1) {//    "MetaSr":"搜狗浏览器",
            result = "MetaSr";
        }else if(user_agent.indexOf("360SE") != -1) {//    "360SE":"360浏览器",
            result = "360SE";
        }else if(user_agent.indexOf("The world") != -1) {//    "The world":"世界之窗浏览器",
            result = "The world";
        }else if(user_agent.indexOf("Maxthon") != -1) {//    "Maxthon":"遨游浏览器",
            result = "Maxthon";
        }else if(user_agent.indexOf("MicroMessenger") != -1) {//    "MicroMessenger":"微信浏览器",
            result = "MicroMessenger";
        }else if(user_agent.indexOf("Chrome") != -1) {//    "Chrome":"谷歌浏览器",
            result = "Chrome";
        }else if(user_agent.indexOf("Mozilla") != -1){//Mozilla 火狐
            result = "Mozilla";
        }else{
            result = "unknown";
        }
        return result;
    };
    
    /**
     *
     * @TODO:     urlEncode字符串编码
     */
    public static String urlEncodeStr(String str,String enc) {
        
        if(!Util.isEmpty(str)) {
            try {
               return URLEncoder.encode(str, enc);
           } catch (UnsupportedEncodingException e) {
                logger.error("urlEncodeStr编码异常==",e);
           }
        }
        return null;
    }
    /**
     *
     * @TODO:     urlEncode字符串解码
     */
    public static String urlDecodeStr(String str,String enc) {
        if(!Util.isEmpty(str)) {
            try {
               return URLDecoder.decode(str, enc);
           } catch (UnsupportedEncodingException e) {
                logger.error("urlDecodeStr解码异常==",e);
           }
        }
        return null;
    }
    private static final String URL_REGEX ="(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]"; 
    
    /**
     *
     * @TODO:     url验证合法性
     */
    public static boolean checkUrl(String url) {
        
        if(!Util.isEmpty(url)) {
            return url.matches(URL_REGEX);
        }
        return false;
    }
    
    /**
     * @TODO 手机短信验证码发送，成功后返回验证码
     */
    public static String sendPhoneCode(String phoneNo,int codeNum) {
        String msgCode = Util.getRandomStringByLength(codeNum, "0"); // 四位数字
        String content = SmsUtil.MSG_MODEL.replace("${msgCode}", msgCode);
        if(SmsUtil.sendSms(content, phoneNo)) { // 发送成功
            return msgCode;
        }
        logger.info("短信验证码发送失败！");
        return null;
    }
    
    /**
    *
    * @TODO:     获取微信access_token
    * @param appid
    * @param secret
    * @return
    */
   public static String getAccessToken(String appid,String secret) {
       String url = WxModelConst.WX_PUBLIC_ACCESSTOKEN_URL.replace("${appid}", appid).replace("${secret}", secret);
       String accessStr = Util.sendRequestGet(url);
       JSONObject accessJson = JSONObject.parseObject(accessStr);
       if (accessJson.containsKey("access_token")) {
           return accessJson.getString("access_token");
       }else {
           return null;
       }
   }
   /**
    * @TODO:  获取小程序码接口B，适用于需要的码数量极多的业务场景。
    */
   public static UpImgMsg getWXACodeUnlimit(String accessToken,String scene,String page,int width) {
       
       String url = WxModelConst.WX_APPLETS_GETWXACODEUNLIMIT_URL.replace("${access_token}", accessToken);
       JSONObject json = new JSONObject();
       json.put("scene", scene);
       json.put("page", page);
       json.put("width", width);
       String result = Util.sendPostReFile(url, json.toString(), Const.HP_UP_IMG_USER_PATH);
       UpImgMsg msg = new UpImgMsg();
       if(Util.isEmpty(result)) {
           msg.setErrorCode(1);
           msg.setMessage("图片存储失败");
           return msg;
       }
       if(result.startsWith("http")) {
           UpImgData data = new UpImgData();
           data.setImgUrl(result);
           msg.setData(data);
           return msg;
       }
       JSONObject jsonResult = JSONObject.parseObject(result);
       msg.setErrorCode(jsonResult.getIntValue("errcode"));
       msg.setMessage(jsonResult.getString("errmsg"));
       return msg;
   }
   /**
    * @TODO:  获取小程序码接口A，适用于需要的码数量极多的业务场景。
    */
   public static UpImgMsg getWXAQRCode(String accessToken,String path,int width) {
       
       String url = WxModelConst.WX_APPLETS_CREATEWXAQRCODE_URL.replace("${access_token}", accessToken);
       JSONObject json = new JSONObject();
       json.put("path", path);
       json.put("width", width);
       String result = Util.sendPostReFile(url, json.toString(), Const.HP_UP_IMG_USER_PATH);
       UpImgMsg msg = new UpImgMsg();
       if(Util.isEmpty(result)) {
           msg.setErrorCode(1);
           msg.setMessage("图片存储失败");
           return msg;
       }
       if(result.startsWith("http")) {
           UpImgData data = new UpImgData();
           data.setImgUrl(result);
           msg.setData(data);
           return msg;
       }
       JSONObject jsonResult = JSONObject.parseObject(result);
       msg.setErrorCode(jsonResult.getIntValue("errcode"));
       msg.setMessage(jsonResult.getString("errmsg"));
       return msg;
   }
   /**
    * @TODO:  获取小程序码接口C，适用于需要的码数量极多的业务场景。
    */
   public static UpImgMsg getWXACode(String accessToken,String path,int width) {
       
       String url = WxModelConst.WX_APPLETS_GETWXACODE_URL.replace("${access_token}", accessToken);
       JSONObject json = new JSONObject();
       json.put("path", path);
       json.put("width", width);
       String result = Util.sendPostReFile(url, json.toString(), Const.HP_UP_IMG_USER_PATH);
       UpImgMsg msg = new UpImgMsg();
       if(Util.isEmpty(result)) {
           msg.setErrorCode(1);
           msg.setMessage("图片存储失败");
           return msg;
       }
       if(result.startsWith("http")) {
           UpImgData data = new UpImgData();
           data.setImgUrl(result);
           msg.setData(data);
           return msg;
       }
       JSONObject jsonResult = JSONObject.parseObject(result);
       msg.setErrorCode(jsonResult.getIntValue("errcode"));
       msg.setMessage(jsonResult.getString("errmsg"));
       return msg;
   }
    
   /**
    *
    * @TODO:     解密微信加密信息
    * @CreateTime:  2019年1月17日下午11:10:13 
    * @CreateAuthor: chenwei
    * @param encryptedData
    * @param iv
    * @param sessionKey
    * @return
    */
   public static JSONObject decodeWxData(String encryptedData,String iv,String sessionKey) {
       encryptedData = Util.urlDecodeStr(encryptedData, Const.CODE_TYPE_STR);
       iv = Util.urlDecodeStr(iv, Const.CODE_TYPE_STR);
       sessionKey = Util.urlDecodeStr(sessionKey, Const.CODE_TYPE_STR);
       
       encryptedData = encryptedData.replace(' ', '+');
       iv = iv.replace(' ', '+');
       sessionKey = sessionKey.replace(' ', '+');
       System.out.println(encryptedData);
       System.out.println(iv);
       System.out.println(sessionKey);
       
     // 被加密的数据
        byte[] dataByte = Base64.decodeBase64(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decodeBase64(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decodeBase64(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                logger.info("解密返回参数==result=={}",result);
                return JSONObject.parseObject(result);
            }
        }catch (NoSuchProviderException e) {
            logger.error("here is exception", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("here is exception", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("here is exception", e);
        } catch (NoSuchPaddingException e) {
            logger.error("here is exception", e);
        } catch (InvalidKeyException e) {
            logger.error("here is exception", e);
        } catch (InvalidAlgorithmParameterException e) {
            logger.error("here is exception", e);
        } catch (InvalidParameterSpecException e) {
            logger.error("here is exception", e);
        } catch (IllegalBlockSizeException e) {
            logger.error("here is exception", e);
        } catch (BadPaddingException e) {
            logger.error("here is exception", e);
        }
        return null;
   }
   /**
    *
    * @TODO:     生成拼团成功提醒消息
    * @CreateTime:  2019年1月19日下午8:13:41 
    * @param posName
    * @param openId
    * @param remark
    * @return
    */
   public static String createPositionMsg(String posName,String openId,String remark,String form_id) {
       
       JSONObject json = new JSONObject();
       json.put(WxAppParamsEnum.WxMsgKeyModel.TEMP_TOUSER.getKeyName(), openId);
       json.put(WxAppParamsEnum.WxMsgKeyModel.TEMP_TEMPLATE_ID.getKeyName(), WxAppParamsEnum.WxMsgModel.TEMP_POSITION.getModelId());
       json.put(WxAppParamsEnum.WxMsgKeyModel.TEMP_PAGE.getKeyName(), "/pages/index/index");
       json.put(WxAppParamsEnum.WxMsgKeyModel.TEMP_FORM_ID.getKeyName(), form_id);
       JSONObject data = new JSONObject();
       JSONObject item1 = new JSONObject();
       item1.put("value", remark);
       data.put("keyword1", item1);
       JSONObject item2 = new JSONObject();
       item2.put("value", posName);
       data.put("keyword2", item2);
       json.put(WxAppParamsEnum.WxMsgKeyModel.TEMP_DATA.getKeyName(), data);
       return json.toString();
   }
   /**
    *
    * @TODO:     身份认证成功提醒消息
    * @CreateTime:  2019年1月19日下午8:13:41 
    * @param posName
    * @param openId
    * @param remark
    * @return
    */
   public static String createApproveMsg(String openId,String remark,String form_id) {
       
       JSONObject json = new JSONObject();
       json.put(WxAppParamsEnum.WxMsgKeyModel.TEMP_TOUSER.getKeyName(), openId);
       json.put(WxAppParamsEnum.WxMsgKeyModel.TEMP_TEMPLATE_ID.getKeyName(), WxAppParamsEnum.WxMsgModel.TEMP_APPROVE.getModelId());
       json.put(WxAppParamsEnum.WxMsgKeyModel.TEMP_PAGE.getKeyName(), "/pages/check-iphone/index");
       json.put(WxAppParamsEnum.WxMsgKeyModel.TEMP_FORM_ID.getKeyName(), form_id);
       JSONObject data = new JSONObject();
       JSONObject item1 = new JSONObject();
       item1.put("value", remark);
       data.put("keyword1", item1);
       JSONObject item2 = new JSONObject();
       item2.put("value", Util.dateFormat(Util.getCurrentDate(), Const.DATE_FORMAT_STR_7));
       data.put("keyword2", item2);
       json.put(WxAppParamsEnum.WxMsgKeyModel.TEMP_DATA.getKeyName(), data);
       return json.toString();
   }
   /**
    *
    * @TODO:     根据身份证号获取出生年月日
    * @CreateTime:  2019年1月20日下午2:25:48 
    * @param number
    * @return
    */
   public static Long getBornIDcard(String number){
       if(Util.isEmpty(number)) {
           return null;
       }
       String birth = number.substring(6, 14);
       String year = birth.substring(0, 4);
       String month = birth.substring(4, 6);
       String day = birth.substring(6, 8);
       
       String birthday = year+"-"+month+"-"+day;
       logger.info("出生年月=={}",birthday);
       return (Long)Util.typeChange(year, Long.class);
   }

   
   /**
    *
    * @TODO:     描述一下这个方法是干什么的
    * @CreateTime:  2019年1月20日下午2:51:31 
    * @param addr
    * @param city
    * @return
    */
   public static JSONObject getAddtrGdDetail(String addr,String city) {
       String api = "https://restapi.amap.com/v3/geocode/geo?parameters";
       String key = "b6a7f5b5e8bb76a100f3964056810a53";
       
       SortedMap<String, String> signMap = new TreeMap<String, String>();
       addr = Util.urlEncodeStr(addr, Const.CODE_TYPE_STR);
       city = Util.urlEncodeStr(city, Const.CODE_TYPE_STR);
       signMap.put("address",addr );
       signMap.put("key", key);
       if(!Util.isEmpty(city)) {
           signMap.put("city", city);
       }
       String signStr = Util.createGdSign(signMap, "ede61604ff69e93875eb1aedc9e87c36");
       String url = "key="+key+"&address="+addr;
       if(!Util.isEmpty(city)) {
           url += "&city="+city;
       }
       
       url += "&sig="+signStr;
       logger.info("参数信息=={}",url);
       String result = Util.sendPost(api, url);
       logger.info("地理编码接口返回信息==={}",result);
       return JSONObject.parseObject(result);
   }
   /**
   *
   * @TODO:     根据高德地址编码接口返回信息获取location
   * @CreateTime:  2019年1月20日下午2:51:31 
   * @return
   */
   public static String getLocationFromGd(JSONObject gdResult) {
       if(gdResult !=null && gdResult.getIntValue("status")==1 && gdResult.getIntValue("infocode")==10000) {
           JSONArray geocodes = gdResult.getJSONArray("geocodes");
           if(geocodes !=null && geocodes.size()>0) {
               JSONObject jsonItem = geocodes.getJSONObject(0);
               return jsonItem.getString("location");
           }
       }
       return null;
   }
   
   public static String createGdSign(SortedMap<String, String> packageParams, String API_KEY) {
   StringBuffer sb = new StringBuffer();
   Set<Entry<String, String>> es = packageParams.entrySet();
   Iterator<Entry<String, String>> it = es.iterator();
   while (it.hasNext()) {
       Entry<String, String> entry = it.next();
       String k = (String) entry.getKey();
       String v = (String) entry.getValue();
       if ((v != null) && (!("".equals(v))) && (!("sign".equals(k)))
               && (!("key".equals(k)))) {
           if(!Util.isEmpty(sb.toString())) {
               sb.append("&");
           }
           sb.append(k + "=" + v);
       }
   }
   sb.append(API_KEY);
   System.out.println(sb);
   String sign = Util.MD5(sb.toString());
   return sign;
}
}
