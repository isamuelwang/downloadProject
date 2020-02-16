package com.owwang.yunzhang.util;

/**
 * @Classname UrlUtil
 * @Description TODO
 * @Date 2020-02-15
 * @Created by WANG
 */

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class UrlUtil {
    public static void main(String[] args) {
        String result="";//访问返回结果
        BufferedReader read=null;//读取访问结果
        try {
            //创建url
            URL realurl=new URL("http://wfs.sxsrsc.com:8000/rwt/DUXIU/http/NFXXPLUUPNXGTZUTMF3HTLUDN7XB/n/a0bbca363532a0b455e566a6e0204372MC257915859263/img1/B80187C5E0E4A949C82781ADDB33DF0C66DF3FCDC2F21CE83F3908A406309252E2D63A22E0A1BE98E3E0A96D744F93A293137DEC92B90B775B0E6B5359A38DA6514CBDA1BBA97AD95E81B1088CF7D31AD4B12801A17E25944040554FDE552755D37C857D05D007A87748EACA090799CEC78F/nf1/qw/14235871/4E85AAD54ECF4921ACF2F17D3CD0BB7D/000001?zoom=0");
            //打开连接
            URLConnection connection=realurl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Cache-Control", "max-age=0");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Cookie", "UM_distinctid=170492fc84d13d-0839844cd9beda-6701b35-1fa400-170492fc84e4c7; CNZZDATA2088844=cnzz_eid%3D2106450902-1581771919-http%253A%252F%252Fwww.xue338.com%252F%26ntime%3D1581771919; CNZZDATA2044458=cnzz_eid%3D987421655-1581773560-http%253A%252F%252Fwfs.sxsrsc.com%253A8000%252F%26ntime%3D1581773560");
            connection.setRequestProperty("Host", "wfs.sxsrsc.com:8000");
            connection.setRequestProperty("Referer", "http://wfs.sxsrsc.com:8000/rwt/DUXIU/https/MJYX843PMS4YR4LWF3SX85B/bookDetail.jsp?dxNumber=000016353959&d=4ED17DFE25F1043CA5AB7C9FE5C5DFE7&fenlei=1112030209&sw=%E5%8C%97%E4%BA%AC");
            connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段，获取到cookies等
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;//循环读取
            while ((line = read.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
            JSONObject obj = new JSONObject().fromObject(result);//将json字符串转换为json对象
            // 格式化输出格式
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(read!=null){//关闭流
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}