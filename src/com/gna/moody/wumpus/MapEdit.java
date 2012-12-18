package com.gna.moody.wumpus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

public class MapEdit extends View
{
	int lakouves_x[] ;
	int lakouves_y[] ;
	static World w ;
	private Cell[][] singlesquare = null ;
	private boolean drawPit,drawWumpus ,drawTreasure ;
	private Paint paint ;
	private Paint paintforText ;
	private int xss ;
	private int yss ;
	private int x , y , l ,a ;
	public static int size =8 ;
	Handler handler = new Handler() {
        // @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case 0:
                invalidate();
                break;
            }
        }
	};
	public MapEdit(Context context,int size)
	{
		super(context);
		this.size = size ;
		paint = new Paint();
	    this.paint.setARGB(255, 0, 0, 0);
	    this.paint.setAntiAlias(true);
	    this.paint.setStyle(Style.STROKE);
	    this.paint.setStrokeWidth(5);
	    
	    paintforText = new Paint();
        
        this.paintforText.setColor(Color.RED);
        this.paintforText.setTextSize(40);
	    l = this.getWidth();
        a = (this.getHeight()-250);
        x = size ;
        y = size ;
	    xss = (int)(l / x);
	    yss = (int)(a / y);
	    lakouves_x = new int[size];
	    lakouves_y = new int[size];
	    singlesquare = new Cell[x][y];
	    
	    for (int z = 0; z < y; z++) {
	        for (int i = 0; i < x; i++) {
	        	 singlesquare[z][i] = new WumpusEmpty(xss * i, z * yss);
	        }
	    }
	}
	@Override
	protected void onDraw(Canvas canvas)
	{
		int max_height = this.getHeight()-250;
        for (int i = 0; i < singlesquare.length; i++) {
            for (int j = 0; j < singlesquare[0].length; j++) {
                singlesquare[i][j].draw(canvas, getResources(), j, i, (this
                        .getWidth() + 3)
                        / singlesquare.length, (max_height)
                        / singlesquare[0].length);
            }
        }
        String s = "Drawing : ";
        if(drawPit)
        {
        	s+= "Pit";
        }
        else if(drawWumpus)
        {
        	s+= "Wumpus";
        }
        else if(drawTreasure)
        {
        	s+= "Treasure";
        }
        else
        {
        	s+= "Nothing";
        }
        canvas.drawText(s,120,120,paintforText);
        super.onDraw(canvas);
        
	}
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		float touch_x = event.getX();
		float touch_y = event.getY();
		int x_aux = (int) (touch_x / (this.getWidth() / x));
        int y_aux = (int) (touch_y / ((this.getHeight()-250) / y));
        if(touch_y < this.getHeight()-250){
        drawpit(x_aux, y_aux);
        }
        else
        {
        	yaw();
        	
        	
        	
        }
        
        
        
		
		return super.onTouchEvent(event);
	}
	private void yaw()
	{
		
		
		w = new World(size-1 , size -1 ,lakouves_x,lakouves_y,2,size,size-2,size-2);
    	
    	
    	
    	Intent startWumpusSolo = new Intent("android.intent.action.WUMPUSPLAYER");
    	
    	((Activity)getContext()).startActivity(startWumpusSolo);
		
	}
	private void drawpit(int x_aux, int y_aux)
	{
		Cell cel = null ;
		//xss * i, z * yss
		cel = new WumpusPit(yss*y_aux,xss*x_aux);
		singlesquare[y_aux][x_aux] = cel;
		lakouves_x[y_aux] ++;
		lakouves_y[x_aux] ++;
		handler.sendMessage(Message.obtain(handler, 0));
	}
	public static World getWorld()
	{
		
		return w;
	}
	
	
}
