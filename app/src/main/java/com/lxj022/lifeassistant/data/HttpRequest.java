package com.lxj022.lifeassistant.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @des 网络请求类
 */
public class HttpRequest {

    private static final String TAG = "HttpRequest";

    public static String doGet(String url) {
        Log.e(TAG, "doGet: " + url);
        HttpURLConnection conn = null;
        InputStream is = null;
        String responseData = "";
        try {
            URL requestUrl = new URL(url);//URL对象
            conn = (HttpURLConnection) requestUrl.openConnection();//使用URL打开一个http地址
            conn.setDoInput(true); //允许输入流，即允许下载
            conn.setDoOutput(true); //允许输出流，即允许上传
            conn.setUseCaches(false); //不使用缓冲
            // 设置请求方式为GET
            conn.setRequestMethod("GET");
            //设置编码方式
            conn.setRequestProperty("Charset", "utf-8");
            is = conn.getInputStream();   //获取输入流，此时才真正建立链接
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine = "";
            while ((inputLine = bufferReader.readLine()) != null) {
                responseData += inputLine;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.e(TAG, "doGet: response:" + responseData);
        return responseData;
    }

    /**
     * 根据图片地址获取图片
     * @param url
     * @return
     */
    public static Bitmap loadBitmap(String url) {
        Log.e(TAG, "doGet: " + url);
        HttpURLConnection conn = null;
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            URL requestUrl = new URL(url);//URL对象
            conn = (HttpURLConnection) requestUrl.openConnection();//使用URL打开一个http地址
            conn.setDoInput(true); //允许输入流，即允许下载
            conn.setDoOutput(true); //允许输出流，即允许上传
            conn.setUseCaches(false); //不使用缓冲
            // 设置请求方式为GET
            conn.setRequestMethod("GET");
            //设置编码方式
            conn.setRequestProperty("Charset", "utf-8");
            is = conn.getInputStream();   //获取输入流，此时才真正建立链接
            bitmap = BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }
}
