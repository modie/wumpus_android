package com.gna.moody.wumpus;
import java.io.FileNotFoundException;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class WumpusGame extends View {
    private Cell[][] singlesquare = null;
    int x = 10;
    int y = 10;
    private int l;
    private int a;
    int map[][];
    Intent openWumpus ;
    Cell arxiko ;
    static boolean gotWumpus = true ;
    static boolean gotArrow = true ;
    int xss ;
    int yss ;
    int looking_up = 2 ;
	int looking_down = 0 ;
	int looking_aristera = -1 ;
	int looking_deksia = 1 ;
	static boolean needsToStop = false ;
	static int counter_pits , counter_wumpus, counter_wumpus_killed,counter_treasure;
    private Paint paint;
    int size ;
    private Paint paintforText ;
    World w ;
    Handler handler = new Handler() {
        // @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case 0:
            	//invalidate comes there ..
            	invalidate();
               
                break;
            case 1:
                Toast.makeText(getContext(), "Wumpus dead", Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(getContext(), "Wumpus dead NOT", Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(getContext(), "Treasure Found-Win", Toast.LENGTH_LONG).show();
                break;
            case 4:
            	
            	//there is nothing there
            	break;
            case 5:
            	Toast.makeText(getContext(), "Haha fell into pit ", Toast.LENGTH_LONG).show();
            	/*try
				{
					newWorld(getContext());
				} catch (FileNotFoundException e1)
				{
					e1.printStackTrace();
				}
            	*/
            	counter_pits ++ ;
            	/*
            	openWumpus = new Intent("android.intent.action.WUMPUSAGENT");
				getContext().startActivity(openWumpus);
				*/
				needsToStop = true ;
            	break;
            case 6:
            	Toast.makeText(getContext(), "Haha wumpus made love with ya", Toast.LENGTH_LONG).show();
            	counter_wumpus ++ ;
            	/*
            	try
				{
					newWorld(getContext());
				} catch (FileNotFoundException e1)
				{
					
					e1.printStackTrace();
				}
				*/
            	needsToStop = true ;
            	/*
            	openWumpus = new Intent("android.intent.action.WUMPUSAGENT");
				getContext().startActivity(openWumpus);
				*/
            	break;
            case 7 : 
            	Toast.makeText(getContext(), "U FOUND TREASURE ", Toast.LENGTH_LONG).show();
            	counter_treasure ++ ;
            	/*
            	try
				{
					newWorld(getContext());
					
				} catch (FileNotFoundException e1)
				{
					
					e1.printStackTrace();
				}
				*/
            	needsToStop = true ;
            	/*
            	openWumpus = new Intent("android.intent.action.WUMPUSAGENT");
				getContext().startActivity(openWumpus);
				*/
            	break;
            case 8:
            	Toast.makeText(getContext(), "Wumpus dead", Toast.LENGTH_SHORT).show();
            	break;
            case 9:
            	Toast.makeText(getContext(), "Wumpus not dead", Toast.LENGTH_SHORT).show();
            	break;
            case 10 :
            	try {
					wait(1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
            	break;
            case 11 :
            	Toast.makeText(getContext(), "FIRE", Toast.LENGTH_SHORT).show();
            	break;
            default:
                break;
            }
 
            super.handleMessage(msg);
        }
    };
 
    public int getGameSize() {
        return x;
    }
    public Point makeMove(){
    	Random r = new Random();
    	int choice = r.nextInt(4);
    	switch(choice){
    	case 0:move();
    	break;
    	case 1:rotateLeft();
			
    	break;
    	case 2:rotateRight();
    	
    	break;
    	
    	}
    	Point p = new Point(w.getPlayerX(),w.getPlayerY());
    	return p ;
    }
    public WumpusGame(Context context,boolean isRandom) {
        super(context);
 
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
       	
       
        newWorld();
       	try
		{
			newWorld(context);
			System.out.println("CAMEEEEEEEEEEEEEEEEE");
		} catch (FileNotFoundException e)
		{
			
			e.printStackTrace();
		}
       	/*if(isRandom)
       	{
       		newWorld();
       	}
       	*/
        
        
        /*int yaw[] = {1,2};
		int yaw1[]= {2,4};
        w = new World(4,5,yaw1,yaw,2,7);
        x = w.getSize();
        y = w.getSize();
        singlesquare = new Cell[x][y];
 
        xss = (int)(l / x);
        yss = (int)(a / y);
        
        for (int z = 0; z < y; z++) {
            for (int i = 0; i < x; i++) {
                singlesquare[z][i] = new WumpusEmpty(xss * i, z * yss);
            }
        }
		setWorld(w) ;
		*/
		
		
		//while(true){
		//	th.run();
		//}
        
		
		
    }

	
	private void newWorld(Context context) throws FileNotFoundException
	{
		 w = new World(context);
		 //World w1 = new World(w.getMonster_x(),w.getMonster_y(),w.getLakouves_x(),w.getLakouves_y(),
		 //w.getLooking(),w.getSize(),w.getTreasurex(),w.getTreasurey());
		 //(int mx , int my , int lkx[],int lky[],int looking,int size,int treasurex,int treasurey)
		 x = w.getSize();
	     y = w.getSize();
	     singlesquare = new Cell[x][y];
	 
	     xss = (int)(l / x);
	     yss = (int)(a / y);
	        
	     for (int z = 0; z < y; z++) {
	         for (int i = 0; i < x; i++) {
	            singlesquare[z][i] = new WumpusEmpty(xss * i, z * yss);
	         }
	     }
	     setWorld(w) ;
	     System.out.println("Came here but....");
		
	}
	public void newWorld(World w)
	{
		 x = w.getSize();
	     y = w.getSize();
	     singlesquare = new Cell[x][y];
	 
	     xss = (int)(l / x);
	     yss = (int)(a / y);
	        
	     for (int z = 0; z < y; z++) {
	         for (int i = 0; i < x; i++) {
	            singlesquare[z][i] = new WumpusEmpty(xss * i, z * yss);
	         }
	     }
	     setWorld(w) ;
		
	}
	public void newWorld()
	{
		/*
		 * TODO add random pits,size,treasure,wumpus for ai
		 */
		
		int yaw[] = {2,3,4};//x
		int yaw1[]= {4,2,0};//y
        w = new World(4,6,yaw,yaw1,2,7 , 6 , 6);
        x = w.getSize();
        y = w.getSize();
        singlesquare = new Cell[x][y];
 
        xss = (int)(l / x);
        yss = (int)(a / y);
        
        for (int z = 0; z < y; z++) {
            for (int i = 0; i < x; i++) {
                singlesquare[z][i] = new WumpusEmpty(xss * i, z * yss);
            }
        }
		setWorld(w) ;
		
		
	}
	public World getWorld()
	{
		return w ;
	}
	public int[][] getMap(){
		return map;
	}
	public void setMap(int map[][])
	{
		this.map = map ;
	}
	@Override
    protected void onDraw(Canvas canvas) {
		
		int max_height = this.getHeight()- 250 ;
        for (int i = 0; i < singlesquare.length; i++) {
            for (int j = 0; j < singlesquare[0].length; j++) {
            	
                singlesquare[i][j].draw(canvas, getResources(), j, i, (this
                        .getWidth() + 3)
                        / singlesquare.length, (max_height)
                        / singlesquare[0].length);
            }
        }
        //button to rotate right
        Bitmap rotate_right = BitmapFactory.decodeResource(getResources(), R.drawable.redo);
        Bitmap new_rotate_right = Bitmap.createScaledBitmap(rotate_right, 120, 120, true);
        canvas.drawBitmap(new_rotate_right, this.getWidth()-120, max_height, paint);
        //button to rotate left
        Bitmap rotate_left = BitmapFactory.decodeResource(getResources(), R.drawable.redo1);
        Bitmap new_rotate_left = Bitmap.createScaledBitmap(rotate_left, 120, 120, true);
        canvas.drawBitmap(new_rotate_left,0,max_height,paint);
        //button to move
        Bitmap btnFire = BitmapFactory.decodeResource(getResources(), R.drawable.fire);
        Bitmap newBtnFire = Bitmap.createScaledBitmap(btnFire, this.getWidth()-240, 120, true);
        Bitmap BtnMove = BitmapFactory.decodeResource(getResources(), R.drawable.move);
        Bitmap newBtnMove = Bitmap.createScaledBitmap(BtnMove, this.getWidth()-240, 120, true);
        canvas.drawBitmap(newBtnMove,120 , max_height , paint);
        canvas.drawBitmap(newBtnFire, 120, max_height+120, paint);
        //String s= " X = "+ w.getPlayerX() ;
        //String s1 = " Y = "+w.getPlayerY();
        //canvas.drawText(s, 120, 120, paintforText);
        //canvas.drawText(s1, 240, 120, paintforText);
        
        super.onDraw(canvas);
    }
	public void setWorld(World w){
		int size = w.getSize();
		map = w.getMap();
		int player_pos_x = w.getPlayerX();
		int player_pos_y = w.getPlayerY();
		int looking = w.getLooking() ;
		WumpusPit we = null ;
		//singlesquare[2][2]= new WumpusPit(2*xss , 2 *yss);
		
		for (int i = 0 ; i<size ; i++)
		{
			
			
			System.out.println();
			for(int y = 0 ; y < size ; y ++)
			{
				
				
				if (map[i][y]== 1)
				{
					singlesquare[i][y] = new WumpusBlood(i*xss , yss * y);
				}
				else if(map[i][y]==2)
				{
					singlesquare[i][y]= new WumpusAura(i*xss , yss*y);
				}
				else if(map[i][y]==3)
				{
					singlesquare[i][y]= new WumpusMonster(i*xss , yss*y);
				}
				else if(map[i][y]==4)
				{
					singlesquare[i][y] =  new WumpusPit(i* xss , yss*y);
				}
				else if(map[i][y]==5)
				{
					singlesquare[i][y] = new WumpusBloodyAura(i*xss, y*yss);
				}
				else if(map[i][y]==6)
				{
					singlesquare[i][y] = new WumpusTreasure(i*xss, y*yss);
				}
				
				
				
				
			}
			singlesquare[w.getPlayerX()][w.getPlayerY()]= new WumpusHuman(w.getPlayerX()*xss , yss*w.getPlayerY(),w.getLooking(),whatWasBeforeLanding(player_pos_x, player_pos_y));
			
	}
	
	}
	
 
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x_aux = (int) (event.getX());
        int y_aux = (int) (event.getY());
        if(y_aux >= this.getHeight()-120 && x_aux >120 && x_aux < this.getWidth()-120 
        		&& event.getAction()==event.ACTION_UP)
        {
        	shoot();
        }
        else if(x_aux >=this.getWidth()-120 && event.getAction() == event.ACTION_UP)
        {
        	rotateRight();
        }
        else if(x_aux <=120 && event.getAction() == event.ACTION_UP)
        {
        	rotateLeft();
        }
        else if(x_aux >120 && x_aux < this.getWidth()-120 && event.getAction() == event.ACTION_UP)
        {
        	move();
        }
        
        
        
        
        return true;
    }
    
	public void rotateRight()
    {
    	int l = w.getLooking();
    	int lnew =-1;
    	if(l == 0 )
    	{
    		lnew = -1 ;
    	}
    	else if(l == 2)
    	{
    		lnew = 1 ;
    	}
    	else if(l == 1)
    	{
    		lnew= 0 ;
    	}
    	else if(l == -1)
    	{
    		lnew = 2 ;
    	}
    	w.setLooking(lnew);
    	singlesquare[w.getPlayerX()][w.getPlayerY()]= new WumpusHuman(w.getPlayerX()*xss , yss*w.getPlayerY(),lnew,whatWasBeforeLanding(w.getPlayerX(),w.getPlayerY()));
    	
    	
    
    handler.sendMessage(Message.obtain(handler, 0));
    }
    public void rotateLeft()
    {
    	
    	int l = w.getLooking();
    	int lnew =-1;
    	
    	if(l == 0 )
    	{
    		lnew = 1 ;
    	}
    	else if(l == 2)
    	{
    		lnew = -1 ;
    	}
    	else if(l == 1)
    	{
    		lnew= 2 ;
    	}
    	else if(l == -1)
    	{
    		lnew = 0 ;
    	}
    	w.setLooking(lnew);
    	singlesquare[w.getPlayerX()][w.getPlayerY()]= new WumpusHuman(w.getPlayerX()*xss , yss*w.getPlayerY(),lnew,whatWasBeforeLanding(w.getPlayerX(), w.getPlayerY()));
    	
    	
    
    handler.sendMessage(Message.obtain(handler, 0));
    }
    public void move()
    {
    	int oldmap[][] = w.getMap();
    	
    	int l = w.getLooking();
    	int xprev = w.getPlayerX();
    	int yprev = w.getPlayerY();
    	int xnew = xprev ;
    	int ynew = yprev ;
    	int whatwasBeforeLanding = R.drawable.roombase;
    	  		
    	
    	
    	if (l == 2)
    	{
    		if(xprev -1 >=0 ){
    			if(w.getId(xprev-1, yprev) ==4){
    				handler.sendMessage(Message.obtain(handler,5));
    				
    			}
    			else if(w.getId(xprev-1, yprev) ==3 &&gotWumpus)
    			{
    				handler.sendMessage(Message.obtain(handler,6));
    			}
    			else if(w.getId(xprev-1, yprev) ==6)
    			{
    				handler.sendMessage(Message.obtain(handler,7));
    			}
    			else{
		    		xnew = xprev -1 ;
		    		whatwasBeforeLanding =whatWasBeforeLanding(xnew, ynew);
		    	}
    		}
    		else{
    			handler.sendMessage(Message.obtain(handler, 4));
    		}
    		ynew = yprev ;
    		
    		
    	}
    	else if( l == 1)
    	{
    		xnew = xprev ;
    		if (yprev +1 <w.getSize()){
    			if( w.getId(xnew, yprev+1) ==4 ){
    				handler.sendMessage(Message.obtain(handler,5));
    				
    			}
    			else if( w.getId(xnew, yprev+1) ==3 &&gotWumpus)
    			{
    				handler.sendMessage(Message.obtain(handler,6));
    			}
    			else if( w.getId(xnew, yprev+1) ==6)
    			{
    				handler.sendMessage(Message.obtain(handler,7));
    			}
    			else{
		    		ynew = yprev + 1 ;
		    		whatwasBeforeLanding =whatWasBeforeLanding(xnew, ynew);
    			}
    		}
    		else{
    			handler.sendMessage(Message.obtain(handler, 4));
    		}
    	}
    	else if( l == 0)
    	{
    		if(xprev+1 < w.getSize()){
    			if(w.getId(xprev+1, yprev) ==4 ){
    				handler.sendMessage(Message.obtain(handler,5));
    				
    			}
    			else if(w.getId(xprev+1, yprev) ==3 &&gotWumpus)
    			{
    				handler.sendMessage(Message.obtain(handler,6));
    			}
    			else if(w.getId(xprev+1, yprev) ==6 )
    			{
    				handler.sendMessage(Message.obtain(handler,7));
    			}
    			
    			else{
		    		xnew = xprev +1 ;
		    		whatwasBeforeLanding =whatWasBeforeLanding(xnew, ynew);
	    		}
    		}
    		else{
    			handler.sendMessage(Message.obtain(handler, 4));
    		}
    		ynew = yprev ;
    	}
    	else if(l==-1)
    	{
    		xnew = xprev ;
    		if(yprev - 1 >=0){
    			if(w.getId(xprev, yprev-1) ==4 )
    			{
    				handler.sendMessage(Message.obtain(handler,5));
    			}
    			else if(w.getId(xprev, yprev-1) ==3 &&gotWumpus)
    			{
    				handler.sendMessage(Message.obtain(handler,6));
    			}
    			else if(w.getId(xprev, yprev-1) ==6)
    			{
    				handler.sendMessage(Message.obtain(handler,7));
    			}
    			else
    			{
		    		ynew = yprev -1;
		    		whatwasBeforeLanding =whatWasBeforeLanding(xnew, ynew);
    			}
    		}else{
    			handler.sendMessage(Message.obtain(handler, 4));
    		}
    	}
    	
    	w.Movement(xnew, ynew);
    	if (oldmap[xprev][yprev]== 1)
		{
			singlesquare[xprev][yprev] = new WumpusBlood(xprev*xss , yss * yprev);
		}
		else if(oldmap[xprev][yprev]==2)
		{
			singlesquare[xprev][yprev]= new WumpusAura(xprev*xss , yss*yprev);
		}
		else if(oldmap[xprev][yprev]==3 && gotWumpus)
		{
			singlesquare[xprev][yprev]= new WumpusMonster(xprev*xss , yss*yprev);
		}
		else if(oldmap[xprev][yprev]==3 && !gotWumpus)
		{
			singlesquare[xprev][yprev] = new WumpusEmpty(xprev*xss, yprev*yss);
		}
		else if(oldmap[xprev][yprev]==4)
		{
			singlesquare[xprev][yprev] =  new WumpusPit(xprev* xss , yss*yprev);
		}
		else if(oldmap[xprev][yprev]==0)
		{
			singlesquare[xprev][yprev] = new WumpusEmpty(xprev*xss, yprev*yss);
		}
		else if(oldmap[xprev][yprev]==5)
		{
			singlesquare[xprev][yprev] = new WumpusBloodyAura(xprev*xss, yprev*yss);
		}
    	
		
    	singlesquare[w.getPlayerX()][w.getPlayerY()]= new WumpusHuman(w.getPlayerX()*xss , yss*w.getPlayerY(),l,whatwasBeforeLanding);
    	
    	handler.sendMessage(Message.obtain(handler, 0));
    }
    private void shoot()
	{
    	int l = w.getLooking();
    	int x = w.getPlayerX();
    	int y = w.getPlayerY();
    	
    	if(gotArrow)
    	{
    		handler.sendMessage(Message.obtain(handler, 11));
    		gotArrow =false ;
    		if(l==2)
			{
				if (x-1>=0)
				{
					if(w.getId(x-1, y) ==3)
					{
						handler.sendMessage(Message.obtain(handler, 8));
						gotWumpus = false ;
				    	singlesquare[x-1][y]= new WumpusEmpty((x-1)*xss, y*yss);
				    	w.setMonster(-1, -1);
//TODO
					}
				}
				
			}
    		else if(l==1)
    		{
    			if(y+1<w.getSize())
    			{
    				if(w.getId(x,y+1) ==3)
					{
    					handler.sendMessage(Message.obtain(handler, 8));
						gotWumpus = false ;
				    	singlesquare[x][y+1]= new WumpusEmpty(x*xss, (y+1)*yss);
				    	w.setMonster(-1, -1);
					}
    			}
    			
    		}
    		else if(l==-1)
    		{
    			if(y-1>=0)
    			{
    				if(w.getId(x,y-1) ==3)
					{
						handler.sendMessage(Message.obtain(handler, 8));
						gotWumpus = false ;
						singlesquare[x][y-1]= new WumpusEmpty(x*xss, (y-1)*yss);
				    	w.setMonster(-1, -1);
					}
    			}
    			
    		}
    		else if(l==0)
    		{
    			if(x+1<w.getSize())
    			{
    				if(w.getId(x+1,y) ==3)
					{
						handler.sendMessage(Message.obtain(handler, 8));
						gotWumpus = false ;
						singlesquare[x+1][y]= new WumpusEmpty((x+1)*xss, y*yss);
				    	w.setMonster(-1, -1);
					}
    			}
    		}
    		
    		
    		
    		if(gotWumpus)
    		{
    			handler.sendMessage(Message.obtain(handler, 9));
    		}
    	}
    	else
    	{
    		Log.i("soz pal", "no arrow");
    	}
    	
    	handler.sendMessage(Message.obtain(handler, 0));
		//TODO 
	}
    public int whatWasBeforeLanding(int x,int y)
    {
    	return  singlesquare[x][y].Drawable();
    }
    public void moveUp()
    {
    	if(w.getLooking()==2)
    	{
    		move();
    	}
    	else if(w.getLooking()==1)
    	{
    		rotateLeft();
    		move();
    	}
    	else if(w.getLooking()==-1)
    	{
    		rotateRight();
    		move();
    	}
    	else if(w.getLooking()==0){
    		rotateRight();
    		rotateRight();
    		move();
    	}
    }
    public void moveRight()
    {
    	if(w.getLooking()==2)
    	{
    		rotateRight();
    		move();
    	}
    	else if(w.getLooking()==1)
    	{
    		move();
    	}
    	else if(w.getLooking()==0)
    	{
    		rotateLeft();
    		move();
    	}
    	else if(w.getLooking()==-1)
    	{
    		rotateLeft();
    		rotateLeft();
    		move();
    	}
    }
    public void moveLeft()
    {
    	if(w.getLooking()==-1)
    	{
    		move();
    	}
    	else if(w.getLooking()==2)
    	{
    		rotateLeft();
    		move();
    	}
    	else if(w.getLooking()==1)
    	{
    		rotateLeft();
    		rotateLeft();
    		move();
    	}
    	else if(w.getLooking()==0)
    	{
    		rotateRight();
    		move();
    	}
    }
    public void moveDown()
    {
    	if(w.getLooking()==0)
    	{
    		move();
    	}
    	else if(w.getLooking()==1)
    	{
    		rotateRight();
    		move();
    	}
    	else if(w.getLooking()==2)
    	{
    		rotateRight();
    		rotateRight();
    		move();
    	}
    	else if(w.getLooking()==-1)
    	{
    		rotateLeft();
    		move();
    	}
    }
    public void shootUp()
    {
    	if(w.getLooking()==2)
    	{
    		shoot();
    	}
    	else if(w.getLooking()==1)
    	{
    		rotateLeft();
    		shoot();
    	}
    	else if(w.getLooking()==-1)
    	{
    		rotateRight();
    		shoot();
    	}
    	else if(w.getLooking()==0){
    		rotateRight();
    		rotateRight();
    		shoot();
    	}
    }
    public void shootRight()
    {
    	if(w.getLooking()==2)
    	{
    		rotateRight();
    		shoot();
    	}
    	else if(w.getLooking()==1)
    	{
    		shoot();
    	}
    	else if(w.getLooking()==0)
    	{
    		rotateLeft();
    		shoot();
    	}
    	else if(w.getLooking()==-1)
    	{
    		rotateLeft();
    		rotateLeft();
    		shoot();
    	}
    }
    public void shootLeft()
    {
    	if(w.getLooking()==-1)
    	{
    		shoot();
    	}
    	else if(w.getLooking()==2)
    	{
    		rotateLeft();
    		shoot();
    	}
    	else if(w.getLooking()==1)
    	{
    		rotateLeft();
    		rotateLeft();
    		shoot();
    	}
    	else if(w.getLooking()==0)
    	{
    		rotateRight();
    		shoot();
    	}
    }
    public void shootDown()
    {
    	if(w.getLooking()==0)
    	{
    		shoot();
    	}
    	else if(w.getLooking()==1)
    	{
    		rotateRight();
    		shoot();
    	}
    	else if(w.getLooking()==2)
    	{
    		rotateRight();
    		rotateRight();
    		shoot();
    	}
    	else if(w.getLooking()==-1)
    	{
    		rotateLeft();
    		shoot();
    	}
    }
	
	/*
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	        needsToStop = true ;
	    }
		System.exit(0);
		return super.onKeyDown(keyCode, event);
	}
	*/
    
   
 
    
}