package libusbcamera.jadeh.uvccamera;

import com.example.jade.JadeLog;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * 作者：Create on 2019/7/17 14:10  by  jadehh
 * 邮箱：
 * 描述：TODO
 * 最近修改：2019/7/17 14:10 modify by jadehh
 */
public class OpencvTest {
    /**
     * Read image.
     *
     * @param imagePath the image path
     */
    public void readImage(String imagePath){
        Mat img = Imgcodecs.imread(imagePath);
        Size size = img.size();
        for (int i=0;i<size.width;i++){
            for (int j=0;j<size.height;j++){
                double channles[] = img.get(i,j);
                JadeLog.printArray(channles);
            }
        }
    }
}
