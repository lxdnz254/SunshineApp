package com.lxdnz.nz.sunshine.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class CompassView extends View {

    private float degreesNorth = 0;
    private float degreesSouth = 180;

    public CompassView(Context context) {
        super(context);
    }

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CompassView(Context context, AttributeSet attrs, int defaultStyle) {
        super(context, attrs, defaultStyle);
    }

    @Override
    protected void onMeasure(int wMeasureSpec, int hMeasureSpec) {
        int myHeight = 100;
        int myWidth = 100;

        int hSpecMode = MeasureSpec.getMode(hMeasureSpec);
        int hSpecSize = MeasureSpec.getSize(hMeasureSpec);

        if(hSpecMode == MeasureSpec.EXACTLY)
            myHeight = hSpecSize;
        else if(hSpecMode == MeasureSpec.AT_MOST) {
            // Wrap Content
        }

        int wSpecMode = MeasureSpec.getMode(wMeasureSpec);
        int wSpecSize = MeasureSpec.getSize(wMeasureSpec);

        if(wSpecMode == MeasureSpec.EXACTLY)
            myWidth = wSpecSize;
        else if(wSpecMode == MeasureSpec.AT_MOST) {
            // Wrap Content
        }

        setMeasuredDimension(myWidth, myHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        float minOfXY = Math.min(centerX, centerY);

        int borderStrokeWidth = 4;
        Paint border = new Paint(Paint.ANTI_ALIAS_FLAG);
        border.setColor(getResources().getColor(R.color.sunshine_light_blue));
        border.setStrokeWidth(borderStrokeWidth);
        border.setStyle(Paint.Style.STROKE);

        Paint fillBlue = new Paint();
        fillBlue.setColor(getResources().getColor(R.color.sunshine_blue));
        fillBlue.setStyle(Paint.Style.FILL);

        float borderInner = 0.8f;
        Paint fillWhite = new Paint();
        fillWhite.setColor(Color.WHITE);
        fillWhite.setStyle(Paint.Style.FILL);

        float needleLength = 0.75f;
        int needleStrokeWidth = 8;
        Paint needleNorth = new Paint();
        needleNorth.setColor(Color.RED);
        needleNorth.setStyle(Paint.Style.FILL);
        needleNorth.setStrokeWidth(needleStrokeWidth);

        Paint needleSouth = new Paint();
        needleSouth.setColor(getResources().getColor(R.color.grey));
        needleSouth.setStyle(Paint.Style.FILL);
        needleSouth.setStrokeWidth(needleStrokeWidth);

        // Draw outer border.
        canvas.drawCircle(centerX, centerY, minOfXY, fillBlue);
        canvas.drawCircle(centerX, centerY, minOfXY - (borderStrokeWidth / 2), border);

        // Draw inner border.
        canvas.drawCircle(centerX, centerY, minOfXY * borderInner, fillWhite);
        canvas.drawCircle(centerX, centerY, minOfXY * borderInner, border);

        // Draw needle.
        canvas.drawLine(centerX, centerY,
                (float)(centerX + (centerX * needleLength * Math.sin(Math.toRadians(degreesSouth)))),
                (float)(centerY - (centerY * needleLength * Math.cos(Math.toRadians(degreesSouth)))),
                needleSouth);
        canvas.drawLine(centerX, centerY,
                (float)(centerX + (centerX * needleLength * Math.sin(Math.toRadians(degreesNorth)))),
                (float)(centerY - (centerY * needleLength * Math.cos(Math.toRadians(degreesNorth)))),
                needleNorth);

        invalidate();
    }

    public void setDegrees(float degrees) {
        degreesNorth = degrees;
        degreesSouth = degrees + 180;
    }
}
