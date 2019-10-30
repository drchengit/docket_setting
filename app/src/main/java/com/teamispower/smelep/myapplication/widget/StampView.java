package com.teamispower.smelep.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.teamispower.smelep.myapplication.utils.ArmUtils;

import java.text.DecimalFormat;

/**
 * @author DrChen
 * @Date 2019/10/25 0025.
 * qq:1414355045
 */
public class StampView extends View {
    private Paint paint2;
    private Path mPath;
    private int effect, offset_x, offset_y;
    /*绘制区域,不包括锯齿*/
    private RectF rectF;
    /*锯齿的半径*/
    private float blade_radius;

    public StampView(Context context) {
        this(context, null);
    }

    public StampView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public StampView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        effect = ArmUtils.dip2px(context, 11);
        offset_x = 0;
        offset_y = ArmUtils.dip2px(context, 2);
        blade_radius = ArmUtils.dip2px(context,5);
        mPath = new Path();
        rectF = new RectF();
        paint2 = new Paint();
        paint2.setAntiAlias(true);
        // 设定颜色
        paint2.setColor(Color.WHITE);
        // 设定阴影(柔边(Effect),Offset X 轴位移,Offset Y 轴位移, 阴影颜色)
        paint2.setShadowLayer(effect, offset_x, offset_y, Color.parseColor("#33000000"));
        //硬件加速要关，不然没有阴影
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        rectF.left = Math.abs(effect - offset_x);
        rectF.top = Math.abs(effect - offset_y);
        rectF.right = w - Math.abs(offset_x + effect);
        rectF.bottom = h - Math.abs(offset_x + effect);

        measurePath();

    }

    /**
     * 确定绘制的背景（包括锯齿）path
     */
    private void measurePath() {
        mPath.reset();
        /*top锯齿*/
        //真实的背景包括锯齿
      final float true_top = rectF.top-blade_radius;

        mPath.moveTo(rectF.left,true_top);//起点
        float current_x = rectF.left;
        //当前函数段x,绘制锯齿只截取一段半圆一直向后绘制
        float current_angle = 180;//上面的锯齿半圆是 180到0度往下凹的半圆
        //上一个锯齿的x
        float last_x = rectF.left;
        //他们加起来就是path 的真实路径
        while (current_x<=rectF.right){//绘制到头了
            current_x =last_x+ blade_radius * (float) Math.cos(Math.toRadians(current_angle))+blade_radius;
            float endY = blade_radius * (float) Math.sin(Math.toRadians(current_angle));

            mPath.lineTo(current_x,endY);

            current_angle--;
            if(current_angle<=0){
                current_angle = 180;
                last_x = current_x;
            }

        }
        final float true_bottom = rectF.bottom+blade_radius;
        /*right*/
        mPath.lineTo(rectF.right,true_bottom);
        /*bottom*/
          current_x = rectF.right;
        // 只截取一段半圆一直向前绘制
        current_angle = 360;//下面的锯齿半圆是 360到180度往上凸的半圆
        //上一个锯齿的x
       last_x =rectF.right;
//        Log.e("angle",current_angle+" :" +current_x);
        while (current_x>=rectF.left){//绘制到头了

            current_x =last_x + blade_radius * (float) Math.cos(Math.toRadians(current_angle))-blade_radius;
            float endY = blade_radius * (float) Math.sin(Math.toRadians(current_angle));

            mPath.lineTo(current_x,rectF.bottom+endY);

//            Log.e("angle",current_angle+" :" +current_x);
            current_angle--;
            if(current_angle<=180){
                current_angle = 360;
                last_x = current_x;
            }

        }
        /*left*/
        mPath.lineTo(rectF.left,true_top);
        mPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawPath(mPath,paint2);
    }
}
