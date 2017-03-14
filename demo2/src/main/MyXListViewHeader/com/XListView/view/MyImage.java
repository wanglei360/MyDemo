package com.XListView.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 创建者：wanglei
 * <p>时间：16/5/13  09:55
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class MyImage extends ImageView {
    public MyImage(Context context) {
        super(context);
    }

    public MyImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMyImageY(float v1, float myTvY, float ivInitPositionHeight) {
        float myYYYYY;
        if (v1 < myTvY) {
            myYYYYY = myTvY;
        } /*else if (v1 > ivInitPositionHeight) {
            myYYYYY = ivInitPositionHeight;
        } */else {
            myYYYYY = v1;
        }
        this.setY(myYYYYY);
    }

    public void setMyImageX(float ivXSizeinimumMValue,float ivSizeDifferentialValue,float ivXSizeSingleValue,float myIvX,float ivInitPositionHeight, float v1) {
        float v = ivInitPositionHeight - v1;
        float x = myIvX - (ivXSizeSingleValue * v) + (ivSizeDifferentialValue * v);
        float myxxx;
        if (x > myIvX) {//420
            myxxx = myIvX;
        } else if (x < ivXSizeinimumMValue) {//172
            myxxx = ivXSizeinimumMValue;
        } else {
            myxxx = x;
        }
        this.setX(myxxx);
    }

    public void setMyImageSize(int myTvHeight,float ivInitPositionHeight,float ivSizeDifferentialValue,int ivInitWidth,float v1) {

        int i = (int) (ivInitWidth - (ivSizeDifferentialValue * (ivInitPositionHeight - v1)));
        int imageSize;
        if (i < myTvHeight) {//132
            imageSize = myTvHeight;
        } else if (i > ivInitWidth) {//240
            imageSize = ivInitWidth;
        } else {
            imageSize = i;
        }
//        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) iv.getLayoutParams();
//        lp.height = imageSize;
//        lp.width = imageSize;
//      这一行和下一行会导致computeScroll方法被无限调用，因为setLayoutParams方法执行的Draw,而这个方法会调用computeScroll方法导致无限调用
//        iv.setLayoutParams(lp);
        this.setLayoutParams(new LinearLayout.LayoutParams(imageSize, imageSize));//大239小132
    }
//    private void setMyImageSize_1(float v1) {
//        float i = ivInitWidth - (ivSizeDifferentialValue * (ivInitPositionHeight - v1));
//        float i1 = i / ivInitWidth;
//        float imageSize;
//        if (i1 < myTvHeight / ivInitWidth) {
//            imageSize = ivInitWidth / myTvHeight;
//        } else if (i1 > 1) {
//            imageSize = 1;
//        } else {
//            imageSize = i1;
//        }
//        Bitmap bmp = ((BitmapDrawable) iv.getDrawable()).getBitmap();//不能用background，否则空指针异常，用src
//        Matrix matrix = new Matrix();
//        //x y坐标同时缩放
//        //后面两个参数，是哪个点放大缩小，这是在中间的意思，否则就从XY轴的0点缩放
//        matrix.setScale(imageSize, imageSize, bmp.getWidth() / 2, bmp.getHeight() / 2);
//        Bitmap createBmp = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
//        Canvas canvas = new Canvas(createBmp);
//        Paint paint = new Paint();
//        canvas.drawBitmap(bmp, matrix, paint);
//        iv.setImageBitmap(createBmp);
//    }
}
