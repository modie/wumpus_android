package com.gna.moody.wumpus;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class Wumpus extends Activity{
	WumpusGame wg;
	WumpusAgent wa ;
	World w ;
	int map[][];
	Thread ourThread = null ;
	Point p ,pnew;
	Thread t ;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
		wg = new WumpusGame(this,false);
		setContentView(wg);
		
		pnew = new Point();
		p = new Point();
		map =wg.getMap() ;
		w = wg.getWorld();
		WumpusGame.needsToStop = false ;
		wa = new WumpusAgent(w.getSize());
		t= new Thread(){
			public void run(){
				while(true){
					try{
						
						//this is where i call ai
						//maybe something like WumpusAgent(wg); :D
						//wg.makeMove();
						
						
						//p = wg.makeMove();  // p wg.makeMove(wa.decide(p.x,p.y));
						p = wa.getLocation() ;
						if(p.x!=w.getPlayerX()){
							p.x = w.getPlayerX();
							
						}
						if(p.y!= w.getPlayerY()){
							p.y = w.getPlayerY();
						}
						
						wa.setRoom(p.x, p.y, map[p.x][p.y]);
						wa.setVisited(p.x, p.y);
						wa.incrNumberOfVisits(p.x, p.y);
						
						
						wa.setLocation(p);
						
						wa.updateField();
						wa.initializeValues();
						wa.AboutPosition();
						wa.FindPits();
						//then doing moves 
						if(wa.possibleMoves()==2)
						{
							wg.moveUp();
							pnew = new Point(p.x-1,p.y);
							
						}
						else if(wa.possibleMoves()==1)
						{
							wg.moveRight();
							pnew = new Point(p.x,p.y+1);
						}
						else if(wa.possibleMoves()==0)
						{
							wg.moveDown();
							pnew = new Point(p.x+1,p.y);
						}
						else if(wa.possibleMoves()==-1)
						{
							
							wg.moveLeft();
							pnew = new Point(p.x,p.y-1);
						}
						else if(wa.possibleMoves()==10)
						{
							Log.e("wtf", "wtf why is it 10 ?");
						}
						else
						{
							
							
							WumpusRoom up ,down,left,right;
							up=down=left=right=null ;
							if(p.x-1 >= 0)
							{
								up = wa.getRoom(p.x-1, p.y);
							}
							if(p.x + 1 < w.getSize())
							{
								down = wa.getRoom(p.x +1 ,p.y);
							}
							if(p.y + 1 < w.getSize())
							{
								right = wa.getRoom(p.x,p.y+1);
							}
							if(p.y - 1 >=0)
							{
								left = wa.getRoom(p.x, p.y-1);
							}
							
							if(up!=null && !up.isPit())
							{
								wg.moveUp();
								pnew = new Point(p.x-1,p.y);
							}
							else if(left!= null && (!left.isPit() ||!left.isMaybepit()||
									!left.isWumpus()) )
								//FIXME for Wumpus and maybePit 
							{
								wg.moveLeft();
								pnew = new Point(p.x,p.y-1);
							}
							else if(right!=null && !right.isPit())
							{
								wg.moveRight();
								pnew = new Point(p.x,p.y+1);
							}
							else if(down!=null && !down.isPit())
							{
								wg.moveDown();
								pnew = new Point(p.x+1,p.y);
							}
							
							Log.e("wtf","trully a wtf moment");
						}
						wa.setLocation(pnew);
						wa.initializeVars();
						
						
						
						
						Thread.sleep(1000);
					}catch(InterruptedException e){
						
					}
				}
			}
		};
		t.start();
		
	}
	@SuppressWarnings("deprecation")
	@Override
	protected void onPause() {
		System.exit(0);
		super.onPause();
		
		
	}
	
	
}
