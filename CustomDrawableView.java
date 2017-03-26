package com.example.android.bounce;

/**
 * Created by User on 3/24/2017.
 */

import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;

        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.drawable.ShapeDrawable;
        import android.graphics.drawable.shapes.OvalShape;
        import android.graphics.drawable.shapes.RectShape;
        import android.view.View;

import org.w3c.dom.Text;

/**
 * Created by sagarsaxena on 3/24/17.
 */

public class CustomDrawableView extends View {
    private ShapeDrawable mDrawable;
    private ShapeDrawable mDrawable2;
    private TextDrawable tDrawable;
    private TextDrawable tDrawable2;
    private TextDrawable tDrawableGoal;
    private TextDrawable tDrawableSign;
    public int wid1;
    public int wid;
    public int len;
    public int x = 612;
    public int y = 1060;
    public int h = 50;
    public int w = 50;
    public CustomDrawableView(Context context){
        super(context);
        tDrawableSign= new TextDrawable("Goal");
        tDrawableSign.changeTextSize(35f);
        tDrawableGoal = new TextDrawable("5");
        tDrawable = new TextDrawable("0");
        tDrawable2 = new TextDrawable("30");
        mDrawable2 = new ShapeDrawable(new RectShape());
        mDrawable2.getPaint().setColor(Color.parseColor("#303F9F"));
        mDrawable2.setBounds(0,0,3000,3000);
        mDrawable = new ShapeDrawable(new OvalShape());
        mDrawable.getPaint().setColor(Color.parseColor("#FFFFFF"));
        mDrawable.setBounds(x, y, x + w, y + h);
    }
    public CustomDrawableView(Context context, AttributeSet att) {
        super(context,att);
        tDrawableSign= new TextDrawable("Goal");
        tDrawableSign.changeTextSize(35f);
        tDrawableGoal = new TextDrawable("5");
        tDrawable = new TextDrawable("0");
        tDrawable2 = new TextDrawable("30");
        mDrawable2 = new ShapeDrawable(new RectShape());
        mDrawable2.getPaint().setColor(Color.parseColor("#303F9F"));
        mDrawable2.setBounds(0,0,wid,len);
        mDrawable = new ShapeDrawable(new OvalShape());
        mDrawable.getPaint().setColor(Color.parseColor("#FFFFFF"));
        mDrawable.setBounds(x, y, x + w, y + h);
    }
    public void changeBounds(int a, int b, int c, int d){
        x = a-25;
        y = b-100;
        h = c;
        w = d;
        mDrawable.setBounds(x, y, x + w, y + h);
    }
    public void changeText(String a, int x)
    {
        if(x==0)
            tDrawable.changeText(a);
        else if (x==1){
            tDrawable2.changeText(a);
        }
        else if (x==2)
            tDrawableGoal.changeText(a);
    }
    public void retSize(int x, int y){
        wid=x;
        len=y;
    }

    public void changeColor(int color){
        mDrawable2.getPaint().setColor(color);
    }
    protected void onDraw(Canvas canvas) {
        mDrawable2.draw(canvas);
        mDrawable.draw(canvas);

        tDrawableSign.drawIt(canvas,(wid/2)+1,200);
        tDrawable.drawIt(canvas,wid-120,150);
        tDrawable2.drawIt(canvas,100,150);
        tDrawableGoal.drawIt(canvas,wid/2,150);
    }

}