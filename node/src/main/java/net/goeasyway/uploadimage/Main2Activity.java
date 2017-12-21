package net.goeasyway.uploadimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import net.goeasyway.uploadimage.retrofit.ImageResizer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main2Activity extends AppCompatActivity {
private  ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        testHttp(iv);
    }

    private void testHttp(final ImageView iv) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String urlString = "https://static.pexels.com/photos/295818/pexels-photo-295818.jpeg";
                HttpURLConnection urlConnection = null;
                InputStream in = null;
                ByteArrayOutputStream outStream = null;
                try {
                    URL url = new URL(urlString);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    in = urlConnection.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    outStream = new ByteArrayOutputStream();
                    while ((len = in.read(buffer)) != -1){
                        outStream.write(buffer,0,len);
                    }
                    final byte[] data = outStream.toByteArray();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() { //在主线程加载UI
                            iv.setImageBitmap(new ImageResizer().decodeSampledBitmapFromBytes(data,200,200));
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if(urlConnection !=null){
                            urlConnection.disconnect();
                        }
                        if(in != null){
                            in.close();
                        }
                        if(outStream != null){
                            outStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.main2Iv);
    }
    }

