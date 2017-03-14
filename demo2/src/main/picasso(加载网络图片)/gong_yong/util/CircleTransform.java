package gong_yong.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

import com.squareup.picasso.Transformation;

/**
 * 创建者：wanglei
 * <p>时间：16/6/7  16:59
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class CircleTransform implements Transformation {
    private boolean roundRectOrCircle;
    private int radius;

    /**
     * @param roundRectOrCircle true 矩形圆角  false 圆形
     * @param radius            弧度 30就有一点效果了
     */
    public CircleTransform(boolean roundRectOrCircle, int radius) {
        this.roundRectOrCircle = roundRectOrCircle;
        this.radius = radius;
    }

    /**
     * http://blog.csdn.net/lmj623565791/article/details/24555655
     * <p>主要靠：paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));这行代码,SRC_IN这种模式，
     * 两个绘制的效果叠加后取交集展现后图，第一个绘制的是个圆形，第二个绘制的是个Bitmap，于是交集为圆形，
     * 展现的是BItmap，就实现了圆形图片效果。圆角，其实就是先绘制圆角矩形，
     * 是不是很简单，以后别人再说实现圆角，你就把这一行代码给他就行了。
     *
     * @param source Bitmap
     * @return Bitmap
     */
    @Override
    public Bitmap transform(Bitmap source) {
        int min = Math.min(source.getWidth(), source.getHeight());
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);

        /**
         * 首先绘制圆形或圆角矩形
         */
        if (roundRectOrCircle) {
            /**
             * left 左边起始点
             * top  上面起始点
             * right    当前的宽
             * bottom   当前的高
             */
            RectF rectF = new RectF(0, 0, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
        } else {
            /**
             * cx：圆心的x坐标。
             * cy：圆心的y坐标。
             * radius：圆的半径。
             * paint：绘制时所使用的画笔。
             */
            canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        }

        /**
         * 使用SRC_IN
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        /**
         * 绘制图片
         */
        canvas.drawBitmap(source, 0, 0, paint);
        paint.setAntiAlias(true);
        source.recycle();
        return target;
    }

//    @Override
//    public Bitmap transform(Bitmap source) {
//        int size = Math.min(source.getWidth(), source.getHeight());
//
//        int x = (source.getWidth() - size) / 2;
//        int y = (source.getHeight() - size) / 2;
//
//        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
//        if (squaredBitmap != source) {
//            source.recycle();
//        }
//
//        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
//
//        Canvas canvas = new Canvas(bitmap);
//        Paint paint = new Paint();
////        paint.setColor(context.getResources().getColor(R.color.lucency));
//        BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
//        paint.setShader(shader);
//        paint.setAntiAlias(true);
//
//        float r = size / 2f;
//        canvas.drawCircle(r, r, r, paint);
//
//        squaredBitmap.recycle();
//        return bitmap;
//    }


    @Override
    public String key() {
        return "circle";
    }
}