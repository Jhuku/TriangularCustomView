package com.shuvam.customviewplayground.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.shuvam.customviewplayground.R;

/**
 * Created by Shuvam Ghosh on 8/26/2017.
 */

public class IndicatorView extends View {

    private RectF mRect;
    private Paint mRectPaint;
    private Paint mCirclePaint;
    private int noOfTabs;
    private int sectionColour;
    private Path triangle;
    private PointF leftpt;
    private PointF midpt;
    private PointF rightpt;
    private Bitmap  [] imgRes;
    private int [] resources;
    private VectorDrawableCompat [] vds;



    public IndicatorView(Context context) {
        super(context);
        init(null);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public void setResources(int [] res){
        this.resources = res;
        Log.d("Size of imgres",""+resources.length);
    }


    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void init(@Nullable AttributeSet set){

        resources = new int[noOfTabs];
        mRect = new RectF();
        mRect.left = 0;
        mRect.top = 0;
        mRect.bottom = 140;

        triangle = new Path();
        leftpt=new PointF();
        midpt=new PointF();
        rightpt=new PointF();


        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mRectPaint.setStyle(Paint.Style.FILL);
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(Color.GREEN);
        if(set == null)
        {
            return;
        }

        TypedArray ta = getContext().obtainStyledAttributes(set,R.styleable.IndicatorView);
        noOfTabs = ta.getInteger(R.styleable.IndicatorView_no_of_sections,3);
        sectionColour = ta.getColor(R.styleable.IndicatorView_set_colour,Color.YELLOW);
        mRectPaint.setColor(sectionColour);
        mCirclePaint.setColor(Color.WHITE);
        imgRes = new Bitmap[noOfTabs];
        vds = new VectorDrawableCompat[noOfTabs];
        ta.recycle();

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRect.right = getWidth();



        canvas.drawRect(mRect,mRectPaint);
        Log.d("No of tabs=",""+noOfTabs);
        Log.d("X-axis value=",""+(2/(float)noOfTabs));
        Log.d("midptx value=",""+midpt.x);

        midpt.y = 180;

        leftpt.x=midpt.x-40;
        leftpt.y=140;

        rightpt.x=midpt.x+40;
        rightpt.y=140;

        triangle.moveTo(leftpt.x, leftpt.y); // Top
        triangle.lineTo(midpt.x, midpt.y); // Bottom left
        triangle.lineTo(rightpt.x ,rightpt.y); // Bottom right
        triangle.lineTo(leftpt.x, leftpt.y); // Back to Top
        triangle.close();

        canvas.drawPath(triangle,mRectPaint);

        for(int i =0; i<resources.length; i++)
        {
            vds[i] = VectorDrawableCompat.create(getContext().getResources(), resources[i], null);
        }

        for(int i=0; i<resources.length; i++)
        {
            //imgRes[i] = BitmapFactory.decodeResource(getResources(),resources[i]); //Use for png images
            imgRes[i] = getBitmap(vds[i]);

        }
      //  canvas.drawBitmap(imgRes[0],);
        canvas.drawBitmap(imgRes[0],(getWidth()/noOfTabs)/2-(imgRes[0].getWidth()/2),70-(imgRes[0].getHeight())/2,null);
        for(int i =1; i<noOfTabs; i++) {
            canvas.drawBitmap(imgRes[i],(i / (float) noOfTabs) * getWidth()+(getWidth()/noOfTabs)/2-(imgRes[i].getWidth()/2),70-(imgRes[i].getHeight())/2,null);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        midpt.x = (getWidth()/noOfTabs)/2;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                midpt.x = ((x*noOfTabs)/getWidth())*((getWidth()/noOfTabs)) + ((getWidth()/noOfTabs)/2);
                triangle.reset();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        invalidate();
        return true;
    }

    private static Bitmap getBitmap(VectorDrawableCompat vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }


}
