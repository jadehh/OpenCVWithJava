package libusbcamera.jadeh.uvccamera;

import android.app.Activity;
import android.os.Bundle;

import com.example.jade.JadeLog;
import com.example.jade.JadeTools;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JadeTools jadeTools = new JadeTools();
        try {
            jadeTools.copyAssertFile(this,"images","71.jpg");
        }catch (IOException e){
            JadeLog.e(this,e.getMessage());
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                OpencvTest testOpencv = new OpencvTest(MainActivity.this);
                jadeTools.deleteDirWihtFile(new File(jadeTools.ROOT_PATH+"txt"));
                for (int i=0;i<10;i++){
                    testOpencv.readImage(jadeTools.ROOT_PATH+"images/"+"71.jpg");
                }

            }
        }.start();

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    //Load Oencv库

}