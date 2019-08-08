package libusbcamera.jadeh.uvccamera;

import android.app.Activity;

import com.example.jade.JadeLog;
import com.example.jade.JadeTools;
import com.example.jade.JsonUtil;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 作者：Create on 2019/7/17 14:10  by  jadehh
 * 邮箱：
 * 描述：opencv 测试类
 * 最近修改：2019/7/17 14:10 modify by jadehh
 */
public class OpencvTest {
    private Activity activity;
    /**
     * Read image.读取图片
     *
     * @param imagePath the image path
     */
    public void readImage(String imagePath){
        Mat image = Imgcodecs.imread(imagePath);
        Mat frame_block = new Mat();
        int m_xmax = 838;
        int m_xmin = 0;
        int m_ymax = 720;
        int m_ymin = 430;
        Size size = new Size(m_xmax - m_xmin, m_ymax - m_ymin);
        Point point = new Point(m_xmin + (size.width / 2), m_ymin + (size.height / 2));
        Imgproc.getRectSubPix(image, size, point, frame_block, -1);
        Mat frame_block_gray = new Mat();
        Imgproc.cvtColor(frame_block, frame_block_gray, Imgproc.COLOR_BGR2GRAY);
        Mat frame_block_gray_gaussian = new Mat();
        Size kSize = new Size(5, 5);
        Imgproc.GaussianBlur(frame_block_gray, frame_block_gray_gaussian, kSize, 0);
        Mat img = new Mat();
        frame_block_gray_gaussian.convertTo(img, CvType.CV_32S);
        Size size2 = img.size();
        ArrayList<int []> imgList = new ArrayList<>();
        JadeTools jadeTools = new JadeTools();
        int channles[] = new int[(int)(size2.width*size.height)];
        img.get(0,0,channles);
        opencvConfig config = new opencvConfig();
        config.setImgList(channles);
        jadeTools.writeTxtToFile(JsonUtil.getInstance().getGson().toJson(config), "android_"+jadeTools.getTimeStamp()+".json");
        JadeLog.e(this,"写入完成");
    }


    public OpencvTest(Activity activity1){
        activity = activity1;
        loadOpencv();
    }
    private void loadOpencv() {
        if (!OpenCVLoader.initDebug()) {
            JadeLog.v(this, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, activity, mLoaderCallback);
        } else {
            JadeLog.v(this, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }
    //Load opencv的回调函数
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(activity) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    JadeLog.v("OpenCV", "OpenCV loaded successfully");

                    JadeLog.v("OpenCV", "OpenCV loaded successfully");
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };
}
