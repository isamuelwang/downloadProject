package com.owwang.yunzhang.util;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.org.apache.xpath.internal.operations.Minus;
import net.sf.json.JSONObject;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname downloadPicByURL
 * @Description TODO
 * @Date 2020-02-08
 * @Created by WANG
 */
public class DownloadPicUtil {
    //该方法仅测试使用方法
    private void downloadYunZhang() {
        String path1 = "D:/桌面/download/pdf/pic/";
        String path2 = "D:/桌面/download/pdf/";
        File file = new File(path1);
        File file2 = new File(path2);
        //如果路径不存在，新建
        if(!file.exists()&&!file.isDirectory()) {
            file.mkdirs();
        }
        if(!file2.exists()&&!file2.isDirectory()) {
            file2.mkdirs();
        }
        for(int i = 1;i<=200;i++){
            String url = "https://book.yunzhan365.com/mlpe/qhpr/files/mobile/"+i+".jpg";
            if(i<10){
                String s = "00"+i;
                String path = "D:/桌面/download/pdf/pic/"+s+".jpg";

                downloadPicture(url, path);
            }else if(i>=10&&i<100){
                String s2 = "0"+i;
                String path = "D:/桌面/download/pdf/pic/"+s2+".jpg";
                downloadPicture(url, path);
            }else {
                String path = "D:/桌面/download/pdf/pic/"+i+".jpg";
                downloadPicture(url, path);
            }
        }
        PrintToPdfUtil.toPdf("D:/桌面/download/pdf/pic/", "D:/桌面/download/pdf/a.pdf",200);
    }

    //链接url下载图片
    public static void downloadPicture(String urlList, String path) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        overDL("http://wfs.sxsrsc.com:8000/rwt/DUXIU/http/NFXXPLUUPNXGTZUTMF3HTLUDN7XB/n/8f416f65fe32422511842777a5f61fa2MC257951273230/img1/503341060CA9F0989764CE2D74526B8EA21CC021E488C04CA1A34DEB5D669B16A711223895A0D63ED7381B248B9F995B20CBCA8D9116BF493D02B284E24777BA526FE9E880949D6C294ED89AE5FBEF1F0F6757A457C3C21F104D90B236C9DA69E27D33F6A10FE3C00B496038BA207BEEA2B5/nf1/qw/14382299/556A374C228342009BA4BBA94F8B8ED3/000001?zoom=0",
                "http://wfs.sxsrsc.com:8000/rwt/DUXIU/http/NFXXPLUUPNXGTZUTMF3HTLUDN7XB/n/slib/book/slib/14382299/556a374c228342009ba4bba94f8b8ed3/417257f6d5a44c000a2e50e26862b888.shtml?dxbaoku=true&deptid=1828&fav=http%3A%2F%2Fwww.sslibrary.com%2Freader%2Fpdg%2Fdxpdgreader%3Fd%3D6e5fc349ffd534ecf8ed4a41f2d4f449%26enc%3De748145372ea3b9cc6af3885d1093ee5%26ssid%3D14382299%26did%3D1828%26username%3Ddx2baoku_222.161.207.50_1828&fenlei=09031103&spage=1&srh=1&t=5&username=dx2baoku_222.161.207.50_1828&view=-1",
                280,
                1,
                4,
                "D:/桌面/download/",
                "");
        /*downloadPicture2("http://wfs.sxsrsc.com:8000/rwt/DUXIU/http/NFXXPLUUPNXGTZUTMF3HTLUDN7XB/n/9e5ddb065edd9648f426122c7662528bMC257940043401/img1/809248A8EBAD32F58B0C3626BC2B5015BCE19497F11C4DD8DCB3536938A69845BB420845486F146C7CCBDD670A73286F32320BD9692A816C1ECF898E6162CDF901475AF052EF8F1694B2484966D8AA1DC0D69AF13202C223A1D22E1655EB741C4C2CBF71AA11FEFCD8CCB334F65C79DD1555/nf1/qw/14334907/006D9776C1AA46D0A684F11237B11100/!00008?zoom=0",
                "http://wfs.sxsrsc.com:8000/rwt/DUXIU/http/NFXXPLUUPNXGTZUTMF3HTLUDN7XB/n/slib/book/slib/14334907/006d9776c1aa46d0a684f11237b11100/f454657f79416382cc515bda9580375d.shtml?dxbaoku=true&deptid=1828&fav=http%3A%2F%2Fwww.sslibrary.com%2Freader%2Fpdg%2Fdxpdgreader%3Fd%3D208609f15e5b7b3594970c36f6bf4700%26enc%3D97539297aa4ab16216de5b4b0ceab809%26ssid%3D14334907%26did%3D1828%26username%3Ddx2baoku_222.161.207.50_1828&fenlei=0903030403&spage=1&srh=1&t=5&username=dx2baoku_222.161.207.50_1828&view=-1",
                "D:/桌面/download/1.jpg");*/
    }

    public static void overDL(String picUrl,String refer,int pageTotal,int fowTotal,int dirTotal,String path,String cookieStr) {
        String cookie = cookieStr;
        String SpltStr = "?zoom=0";
        int splitIndex = picUrl.indexOf(SpltStr)-6;
        String tempUrl = picUrl.substring(0,splitIndex);
        System.out.println(tempUrl);
        //下载封面(1页）
        downloadPicture2(tempUrl+"bok001",refer,path+"1.jpg",cookie);
        //下载版权页（1页）
        downloadPicture2(tempUrl+"leg001",refer,path+"2.jpg",cookie);
        //下载目录（dirTotal页）
        for(Integer i=1;i<=fowTotal;i++){
            String pageNo="";
            String strI = i.toString();
            if(i<10){
                pageNo = "fow00"+strI;
            }else if(i>=10&&i<100){
                pageNo = "fow0" + strI;
            }
            downloadPicture2(tempUrl+pageNo+SpltStr,refer,path+(i+2)+".jpg",cookie);
        }
        //下载目录（dirTotal页）
        for(Integer i=1;i<=dirTotal;i++){
            String pageNo="";
            String strI = i.toString();
            if(i<10){
                pageNo = "!0000"+strI;
            }else if(i>=10&&i<100){
                pageNo = "!000" + strI;
            }
            downloadPicture2(tempUrl+pageNo+SpltStr,refer,path+(i+fowTotal+2)+".jpg",cookie);
        }
        //下载正文（pageTotal页）
        for(Integer i=1;i<=pageTotal;i++){
            //System.out.println("==========第"+i+"页开始下载==========");
            String pageNo="";
            String strI = i.toString();
            if(i<10){
                pageNo = "00000"+strI;
            }else if(i>=10&&i<100){
                pageNo = "0000" + strI;
            }else if(i>=100&&i<1000){
                pageNo = "000"+strI;
            }
            downloadPicture2(tempUrl+pageNo+SpltStr,refer,path+(i+fowTotal+2+dirTotal)+".jpg",cookie);
            File jpg = new File(path+(i+fowTotal+2+dirTotal)+".jpg");
            int count = 0;
            while (jpg.length()<=1024*10&&count<=15){
                count++;
                System.out.println("==============快去输入验证码===============");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                downloadPicture2(tempUrl+pageNo+SpltStr,refer,path+(i+fowTotal+2+dirTotal)+".jpg",cookie);
            }
            System.out.println("==========第"+i+"页下载完成==========");
        }
        //下载尾封（1页）
        downloadPicture2(tempUrl+"cov002",refer,path+(2+fowTotal+dirTotal+pageTotal+1)+".jpg",cookie);
    }



    //链接读秀url下载图片
    public static void downloadPicture2(String urlList, String referer,String path,String cookie) {
        URL url = null;
        try {
            url = new URL(urlList);
            //打开连接
            URLConnection connection=url.openConnection();
            // 设置通用的请求属性
            //connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            //connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            //connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
            //connection.setRequestProperty("connection", "Keep-Alive");
            //connection.setRequestProperty("Cache-Control", "max-age=0");
            //connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Cookie", cookie);
            //connection.setRequestProperty("Cookie", "UM_distinctid=170492fc84d13d-0839844cd9beda-6701b35-1fa400-170492fc84e4c7; CNZZDATA2088844=cnzz_eid%3D2106450902-1581771919-http%253A%252F%252Fwww.xue338.com%252F%26ntime%3D1581809674; CNZZDATA2044458=cnzz_eid%3D987421655-1581773560-http%253A%252F%252Fwfs.sxsrsc.com%253A8000%252F%26ntime%3D1581812359UM_distinctid=170492fc84d13d-0839844cd9beda-6701b35-1fa400-170492fc84e4c7; CNZZDATA2088844=cnzz_eid%3D2106450902-1581771919-http%253A%252F%252Fwww.xue338.com%252F%26ntime%3D1581809674; CNZZDATA2044458=cnzz_eid%3D987421655-1581773560-http%253A%252F%252Fwfs.sxsrsc.com%253A8000%252F%26ntime%3D1581812359");
            //connection.setRequestProperty("Host", "wfs.sxsrsc.com:8000");
            connection.setRequestProperty("Referer", referer);
            //connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            //connection.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
            //建立连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段，获取到cookies等
            //for (String key : map.keySet()) {
                //System.out.println(key + "--->" + map.get(key));
            //}
            DataInputStream dataInputStream = new DataInputStream(connection.getInputStream());
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //将字符串以：进行分割
    public static Map<String,String> splitByColon(String str){
        Map map = new HashMap();
        String[] kvs = str.split(";");
        for(String kv : kvs){
            String[] value = kv.split("\\n\" +");
            map.put(value[0].trim(),value[1].trim());
            System.out.println(map);
        }
        return map;
    }

    //网上下载的，未经修改，无法使用
    public void dlDX(){
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
