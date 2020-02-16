package com.owwang.yunzhang.service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import com.owwang.yunzhang.util.QiNiuResult;
import com.owwang.yunzhang.util.QiNiuUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Classname DownLoadService2
 * @Description TODO
 * @Date 2020-02-13
 * @Created by WANG
 */
public class FaultDownLoadService {
    public static void main(String[]args){
        dowonload("https://book.yunzhan365.com/mlpe/bbms/files/mobile/1.jpg");
    }

    public static void dowonload(String urlStr) {
        try {
            URL url = null;
            url = new URL(urlStr);
            InputStream ips = new BufferedInputStream(url.openStream());
            //创建初始pdf文件对象
            Document doc = new Document(null, 0, 0, 0, 0);
            //写入pdf
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(doc, baos);
            //根据输入流获取图片对象
            BufferedImage img = null;
            img = ImageIO.read(ips);
            // 根据图片大小设置文档大小
            doc.setPageSize(new Rectangle(img.getWidth(), img
                    .getHeight()));
            //inputstream转为byte[]
            byte[] bytes = input2byte(ips);
            // 实例化图片
            Image image = null;
            image = Image.getInstance(bytes);
            // 添加图片到文档
            doc.open();
            doc.add(image);
            // 写入PDF文档
            PdfWriter.getInstance(doc, baos);
            doc.close();
            InputStream is2 = new ByteArrayInputStream(baos.toByteArray());
            QiNiuResult result = QiNiuUtil.upload("picbed8", is2, "11111111111.pdf");
            System.out.println(result.getUrl());
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static final byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }
}
