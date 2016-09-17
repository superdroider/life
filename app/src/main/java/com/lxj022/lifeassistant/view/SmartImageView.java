package com.lxj022.lifeassistant.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.lxj022.lifeassistant.R;
import com.lxj022.lifeassistant.data.HttpRequest;

/**
 * @author Superdroid
 * @time 2016/9/5 21:33
 * @des
 */
public class SmartImageView extends ImageView {
    public SmartImageView(Context context) {
        super(context);
    }

    public SmartImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置网络图片地址
     * @param url
     */
    public void setNetImage(String url) {
        new LoadImageTask().execute(url);
    }

    class LoadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            SmartImageView.this.setImageResource(R.mipmap.ic_launcher);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            return HttpRequest.loadBitmap(strings[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) {
                SmartImageView.this.setImageResource(R.mipmap.ic_launcher);
                return;
            }
            SmartImageView.this.setImageBitmap(bitmap);
        }
    }
}
