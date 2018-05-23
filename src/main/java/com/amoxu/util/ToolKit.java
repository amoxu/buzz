package com.amoxu.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class ToolKit {
    private static final Logger logger = Logger.getLogger(ToolKit.class);


    //密钥 (需要前端和后端保持一致)
    private static final String KEY = "$amox^buzz&ukcn*";
    //算法
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    /**
     * aes解密
     *
     * @param encrypt 内容
     * @return
     * @throws Exception
     */
    public static String aesDecrypt(String encrypt) {
        try {
            return aesDecrypt(encrypt, KEY);
        } catch (Exception e) {
            logger.error("Exception: ",e);
            return null;
        }
    }

    /**
     * aes加密
     *
     * @param content
     * @return
     * @throws Exception
     */
    public static String aesEncrypt(String content) {
        try {
            return aesEncrypt(content, KEY);
        } catch (Exception e) {
            logger.error("Exception: ",e);
            return null;
        }
    }

    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /**
     * base 64 encode
     *
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * base 64 decode
     *
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception {
        return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }


    /**
     * AES加密
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }


    /**
     * AES加密为base 64 code
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * AES解密
     *
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey   解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return org.apache.commons.codec.binary.StringUtils.newStringUtf8(decryptBytes);
    }


    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

    public static String shaEncode(String src) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(src.getBytes());
            return Hex.encodeHexString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            logger.error("Exception: ",e);
        }
        return null;
    }

    /*
     * 获取字符串MD5
     *
     * Gravator 头像MD5 hash
     * 2018/4/12
     * amoxuk
     * */
    public static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i]
                    & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }
    public static String md5Hex (String message) {
        try {
            MessageDigest md =
                    MessageDigest.getInstance("MD5");
            return hex (md.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
        }
        return null;
    }

    /**
     * 测试
     */
    public static void main(String[] args) throws Exception {
       /* String content = "dRkyepv798L+UU/+PDOC8xJDSRZCZyTajqafQg+3v75L24c9fqmex8OJQ8NqHHEZFcrphd+koYCdv9iQ8nWPbwWu7R45kxwpoStoCf81AAD/1zH5o4ti16ROaFtJeRge";
        System.out.println("加密前：" + content);
        System.out.println("加密密钥和解密密钥：" + KEY);
    *//*    String encrypt = aesEncrypt(content, KEY);
        System.out.println("加密后：" + encrypt);*//*
        String decrypt = aesDecrypt(content, KEY);
        System.out.println("解密后：" + decrypt);
        System.out.println(shaEncode(content));*/
        String email = "amoxuk@qq.com";
        System.out.println(md5Hex(email));

    }

    /**
     * 发送邮件
     *
     * @throws Exception
     *//*
    public static void sendMsg(String toAddress, String subject, String content) throws Exception {
        //1.设置邮件的一些信息
        Properties props = new Properties();
        //发送邮件的服务器地址
        props.put("mail.smtp.host", "smtp.163.com");//smtp.qq.com stmp.sina.com
        props.put("mail.smtp.auth", "true");
        //2.创建Session对象
        Session session = Session.getInstance(props);
        //3.创建出MimeMessage，邮件的消息对象
        MimeMessage message = new MimeMessage(session);
        //4.设置发件人
        Address fromAddr = new InternetAddress("发件人邮箱");
        message.setFrom(fromAddr);

        //5.设置收件人
        Address toAddr = new InternetAddress(toAddress);
        message.setRecipient(RecipientType.TO, toAddr);

        //6.设置邮件的主题
        message.setSubject(subject);

        //7.设置邮件的正文
        message.setText(content);
        message.saveChanges();//保存更新

        //8.得到火箭
        Transport transport = session.getTransport("smtp");

        transport.connect("smtp.163.com", "发件人邮箱", "发件人密码"); //设置了火箭的发射地址

        transport.sendMessage(message, message.getAllRecipients());//发送具体内容及接收人

        transport.close();
    }
*/

}
