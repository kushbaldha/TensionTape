package kush.tensiontape;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by Kush on 11/6/2015.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback

{
    public static final int WIDTH= 1080;
    public static final int HEIGHT = 1920;
    private MainThread thread;
    private Background bg;
    int starty = 0;
    int startx = 0;
    int endy = 0;
    int newy1 = 0;
    int score;
    Tape tape;
    Players players;
    int distance;
    int feet;
    boolean tdraw = false;
    boolean pselect = false;
    boolean tapeInfo = false;
    TapeInfo info;
    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;
    public GamePanel(Context context)
    {

     super(context);

        //add callback to surfaceholders to intercepts events like fingerpresses
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        //getholder() is the surfaceholder or the screen
        //this is the gamePanel

        //make gamePanel focusable so it can handle events
         setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        bg = new Background(BitmapFactory.decodeResource(getResources(),R.drawable.greenbg1));
        players= new Players();
        //once surface is created, we can safely start gameloop
    thread.setRunning(true);
        thread.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);

        if(MotionEvent.ACTION_DOWN == action)
        { System.out.println( "Action was DOWN");
            startx = (int)event.getX();
            starty = (int) event.getY();
            if(!pselect) {
                tape = new Tape(startx, starty);
                tdraw = true;
            }
            else
            {
                for(int i = 0; i<4 ; i++)
                {
                    if(players.aplayers[i].getRect(i).contains(startx,starty)) {
                        players.update(feet,i);
                        break;
                    }
                }
                System.out.println("Player Selected");
            }
            System.out.println("Start is" + startx + "and" + starty);
        }
        if(MotionEvent.ACTION_MOVE == action)
        {
            System.out.println("Action was MOVE");
            newy1 = (int) event.getY();
            System.out.println(newy1);
            tape.update(newy1);
        }
        if(MotionEvent.ACTION_UP == action)
        {
            tdraw = false;
            System.out.println("Action was UP");
            endy = (int)event.getY();
            distance = endy-463;
            feet = (int)((distance/80.25)+0.5); // pixels/ pixels per inch.
            System.out.println("Feet of tape is"+ feet);
            if(pselect) {
                tapeInfo = false;
                pselect = false;
            }
            else {
                info = new TapeInfo(feet);
                pselect = true;
                tapeInfo = true;
            }
        }
        return true;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
    boolean retry = true;

        // it might take several tries to stop thread so this is needed
        // a try catch loop is created

        while(retry)
        {
            try{
                thread.setRunning(false);
                thread.join();
            }catch(InterruptedException e) {e.printStackTrace();}
            retry=false;
        }
    }

    public void update()
    {
    bg.update();
    }
    @Override
    public void draw(Canvas canvas)
    {
       final float scaleFactorX = getWidth()/(WIDTH*1.f);//make sure they are floats
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);
        if(canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            if(tdraw)
            {
                tape.draw(canvas);
                System.out.println("Tape Drawn");
            }
            if(tapeInfo)
                info.draw(canvas);
            players.draw(canvas);
            //return to savedstate. If we didn't have this, it would keep on scaling. So we do this to return it to original state
            canvas.restoreToCount(savedState);
        }
    }

}
