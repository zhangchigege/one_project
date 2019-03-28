package test;


import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * 图片识别
 * 验证码
 */
public class image {

    public static void main(String[] args) {
        File imageFile = new File("D:\\WorkSpace\\zxzxshow\\zxshow\\verifyCode.jpeg");
        Tesseract instance = new Tesseract();
        //将验证码图片的内容识别为字符串
        try {
            //将验证码图片的内容识别为字符串
            String result = instance.doOCR(imageFile);
            System.err.println(imageFile.getName() +" result："+  result);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }

}
