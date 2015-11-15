package kush.tensiontape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
/**
 * Created by Kush on 11/6/2015.
 */
public class Tape extends GameObject
{

    public Rect rect;
    int standx;
    int standy;
    public Tape(int x, int y)
    {
        super.x = x;
        super.y =y;
    }
    public void update(int y1)
    {
        y=y1;
    }

    public void draw(Canvas canvas)
    {
       Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);
        standx= canvas.getWidth()/2;
        standy= canvas.getHeight()/2-425;
        System.out.println(standy);
        rect = new Rect(standx-60,y,standx,standy);
        //from the left, from the top, width,height
        canvas.drawRect(rect, paint);
    }
}