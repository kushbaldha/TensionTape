package kush.tensiontape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Kush on 11/7/2015.
 */
public class TapeInfo extends GameObject
{
    Paint paint = new Paint();
    String text;
    public TapeInfo(int feet)
    {
        paint.setColor(Color.BLACK);
        paint.setTextSize(60);
        text = feet + " inches of tape selected.";
    }
    public void update()
    {

    }
    public void draw(Canvas canvas)
    {
        canvas.drawText(text,200,1500,paint);
    }
}
