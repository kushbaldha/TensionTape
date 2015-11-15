package kush.tensiontape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by Kush on 11/6/2015.
 */
public class Person extends GameObject {
    int need;
    String text;
    int number;
    Rect rect1,rect2,rect3,rect4;

    public Person(int playernumber) {
        Random r = new Random();
        need = r.nextInt(11) + 1;
        number = playernumber;
        rect1 = new Rect(0, 0, 350, 350);
        rect2 = new Rect(730,0,1080,350);
        rect3 = new Rect(0,1570,350,1920);
        rect4 = new Rect(730,1570,1080,1920);
    }

    public void update() {

    }

    public void draw(Canvas canvas) {
        Paint selectPaint = new Paint();
        selectPaint.setColor(Color.TRANSPARENT);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);
        text = "I need " + need + " inches of tape!";
        if (number == 1) {
            canvas.drawRect(rect1, selectPaint);
            canvas.drawText(text, 25, 350, paint);
        } else if (number == 2) {
            canvas.drawRect(rect2, selectPaint);
            canvas.drawText(text, 605, 350, paint);
        }
        else if (number == 3){
            canvas.drawRect(rect3, selectPaint);
            canvas.drawText(text, 25, 1595, paint);
        }
        else if (number == 4){
            canvas.drawRect(rect4, selectPaint);
            canvas.drawText(text, 605, 1595, paint);
        }

    }

    public int getNeed() {
        return need;
    }

    public Rect getRect(int num)
    {
        switch(num) {
            case 0:
                return rect1;
            case 1:
                return rect2;
            case 2:
                return rect3;
            case 3:
                return rect4;
            default:
                return rect1;
        }
    }
}
