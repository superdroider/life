package com.lxj022.lifeassistant.data;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 算法 对称加密，密码学中的高级加密标准 2005年成为有效标准
 */
public class AES {
    static Cipher cipher;
    /*
     * AES/CBC/NoPadding 要求 
     * 密钥必须是16位的；Initialization vector (IV) 必须是16位 
     * 待加密内容的长度必须是16的倍数，如果不是16的倍数，就会出如下异常： 
     * javax.crypto.IllegalBlockSizeException: Input length not multiple of 16 bytes 
     *  
     *  由于固定了位数，所以对于被加密数据有中文的, 加、解密不完整 
     *   
     *  可 以看到，在原始数据长度为16的整数n倍时，假如原始数据长度等于16*n，则使用NoPadding时加密后数据长度等于16*n， 
     *  其它情况下加密数据长 度等于16*(n+1)。在不足16的整数倍的情况下，假如原始数据长度等于16*n+m[其中m小于16]， 
     *  除了NoPadding填充之外的任何方 式，加密数据长度都等于16*(n+1). 
     */
    static final String CIPHER_ALGORITHM_CBC_NoPadding = "AES/CBC/NoPadding";

    static byte[] getIV() {
        String iv = "2345tqIv_shiqing"; //IV length: must be 16 bytes long
        return iv.getBytes();
    }

    /**
     * 使用AES 算法 解密，默认模式 AES/CBC/NoPadding  参见上面对于这种mode的数据限制
     */
    public static String decrypt(String str){
        try {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC_NoPadding);
            SecretKeySpec mSecretKeySpec = new SecretKeySpec("2345android_key_".getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, mSecretKeySpec, new IvParameterSpec(getIV()));//使用解密模式初始化 密钥
            byte[] decrypt = cipher.doFinal(calculate(str));
            String result = new String(decrypt, "utf-8");
            WriteStringToFile("/mnt/sdcard/weather.json", new String(decrypt, "utf-8"));
            System.out.println("method4-解密后：" + new String(decrypt, "utf-8"));
            return result;
        } catch (Exception e) {
            Log.e("tag", "exception: " + e.getMessage());
        }
        return null;
    }

    public static void WriteStringToFile(String filePath, String str) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
            }
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(str);// 往文件里写入字符串
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static byte[] calculate(String paramString) {
        Object localObject = null;
        if (paramString == null) {
        }
        while (paramString.length() < 2) {
            return (byte[]) localObject;
        }
        int j = paramString.length() / 2;
        byte[] arrayOfByte = new byte[j];
        int i = 0;
        for (; ; ) {
            localObject = arrayOfByte;
            if (i >= j) {
                break;
            }
            arrayOfByte[i] = ((byte) Integer.parseInt(paramString.substring(i * 2, i * 2 + 2), 16));
            i += 1;
        }
        return arrayOfByte;
    }

}  