package kush.tensiontape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Kush on 11/6/2015.
 */
public class Players extends GameObject
{
    double feet;
    Person [] aplayers = new Person[4];
    int score,input;
    Paint paint;
    Rect scoreRect,fullScore;
    int ay = 1455;
    String text;
    boolean drawText = false;
    public Players()
    {
       aplayers[0]= new Person(1);
       aplayers[1]= new Person(2);
       aplayers[2]= new Person(3);
       aplayers[3]= new Person(4);
       score = 0;

        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);

    }
    public void update(int feet,int pnumber)
    {
        //feet is how much feet the user takes out
        //pnumber is the player number the user chose
        input = aplayers[pnumber].getNeed();
        if( input == feet)
        {
            System.out.println("good job!");
            score++;
            ay -= 100;
            aplayers[pnumber] = new Person(pnumber+1);
        }
        System.out.println("SCORE IS " + score);
        if((input != feet) && (score>=1))
        {       score--;
                ay += 100;
        }
        if(score == 10)
        {
         drawText = true;
         text= "You Won!";
        }
        System.out.println("SCORE IS " + score);
    }
    public void draw(Canvas canvas)
    {
        for(Person p : aplayers )
        { p.draw(canvas);}
        Paint tpaint = new Paint();
        tpaint.setColor(Color.BLACK);
        tpaint.setTextSize(100);
        Paint spaint = new Paint();
        spaint.setColor(Color.RED);
        spaint.setStyle(Paint.Style.STROKE);
        spaint.setStrokeWidth(5);
        fullScore = new Rect(900,1450,980,450);
        scoreRect = new Rect(905,1455,980,ay);
        canvas.drawRect(scoreRect, paint);
        canvas.drawRect(fullScore, spaint);
        if(drawText)
        { canvas.drawText(text,300,960,tpaint);}

    }
}
