import com.owwang.yunzhang.util.PrintToPdfUtil;
import com.owwang.yunzhang.util.QiNiuResult;
import com.owwang.yunzhang.util.QiNiuUtil;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Classname test
 * @Description TODO
 * @Date 2020-02-16
 * @Created by WANG
 */
public class test {
/*    @Test
    public void a(){
        //生成pdf
        PrintToPdfUtil.toPdf(picPath, pdfPath + fileName + ".pdf", booktotal);
        //记录创建时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String time = format.format(new Date());
        book.setCreatetime(time);
        //上传到七牛云
        pdfResult = QiNiuUtil.upload("picbed8", new File(pdfPath + fileName + ".pdf"));
        book.setQiniuurl("http://cos.owwang.com/" + pdfResult.getUrl());
        book.setQiniuurl("http://cos.owwang.com/" + pdfResult.getUrl());
        //上传一张封面图片
        QiNiuResult picResult = QiNiuUtil.upload("picbed8", new File(picPath + "1.jpg"));
        //写入封面图片地址
        book.setQiniupicurl("http://cos.owwang.com/" + picResult.getUrl());
    }*/
}
