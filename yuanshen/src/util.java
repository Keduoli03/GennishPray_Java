import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class util {
    //窗口大小
    public static final int Frame_Width=1280;
    public static final int Frame_Height=720;
    public static final int Icon_Width=200;
    public static final int Icon_Height=50;
    public static final String FrameTitle="原神抽卡模拟器";
    public static final String mulu="yuanshen/src/resources/";
    public static final String once_gold="单抽出金/";
    public static final String once_blue="单抽出蓝/";
    public static final String once_purple="单抽出紫/";
    public static final String tenth_purple="十连出紫/";
    public static final String tenth_gold="十连出金/";
    public static final String bk_image=mulu+"background.png";
    public static final String One_image=mulu+"pray_once.png";
    public static final String Ten_image=mulu+"pray_tenth.png";
    public static final String History_image=mulu+"history.png";
    public static final String Detail_image=mulu+"detail.png";
    public static final String Five_Hero=mulu+"Hero/ive/";
    public static final String result_bk_Image=mulu+"抽卡背景.png";


    //获取图标方法
    public static Icon get_icon(String n) {
        ImageIcon image = new ImageIcon(n);
        Image img = image.getImage();
        img = img.getScaledInstance(util.Icon_Width, util.Icon_Height, Image.SCALE_DEFAULT);
        image.setImage(img);
        return image;
    }
    public static BufferedImage scaleImage(BufferedImage originalImage, int newWidth, int newHeight) {
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        AffineTransform transform = new AffineTransform();
        double scaleX = (double) newWidth / originalImage.getWidth();
        double scaleY = (double) newHeight / originalImage.getHeight();
        transform.scale(scaleX, scaleY);

        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
        op.filter(originalImage, resizedImage);

        g2d.dispose();

        return resizedImage;
    }
    public static class ImageScaler {
        public static BufferedImage scaleImage(String imagePath, int newWidth, int newHeight) {
            try {
                BufferedImage originalImage = ImageIO.read(new File(imagePath));
                BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = resizedImage.createGraphics();
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

                AffineTransform transform = new AffineTransform();
                double scaleX = (double) newWidth / originalImage.getWidth();
                double scaleY = (double) newHeight / originalImage.getHeight();
                transform.scale(scaleX, scaleY);

                AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
                op.filter(originalImage, resizedImage);

                g2d.dispose();

                return resizedImage;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
