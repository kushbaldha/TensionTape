package kush.tensiontape;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Kush on 11/6/2015.
 */
public class Background
{
    private Bitmap image;
    private int x,y;

    public Background(Bitmap res)
    {
        image = res;
    }
    public void update()
    {

    }
    public void draw(Canvas canvas)
    {
    canvas.drawBitmap(image,x,y, null);
    }
}
