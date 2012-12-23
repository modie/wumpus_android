package com.gna.moody.wumpus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import android.content.Context;
import android.content.ContextWrapper;

public class World {
	int monster_x ;
	int monster_y ;
	int lakouves_x[] ;
	int lakouves_y[] ;
	int treasurex ;
	int treasurey ;
	int player_pos_x =0;
	int player_pos_y =0;
	int looking_up = 2 ;
	int looking_down = 0 ;
	public int getMonster_x()
	{
		return monster_x;
	}
	public void setMonster_x(int monster_x)
	{
		this.monster_x = monster_x;
	}
	public int getMonster_y()
	{
		return monster_y;
	}
	public void setMonster_y(int monster_y)
	{
		this.monster_y = monster_y;
	}
	public int[] getLakouves_x()
	{
		return lakouves_x;
	}
	public void setLakouves_x(int[] lakouves_x)
	{
		this.lakouves_x = lakouves_x;
	}
	public int[] getLakouves_y()
	{
		return lakouves_y;
	}
	public void setLakouves_y(int[] lakouves_y)
	{
		this.lakouves_y = lakouves_y;
	}
	public int getTreasurex()
	{
		return treasurex;
	}
	public void setTreasurex(int treasurex)
	{
		this.treasurex = treasurex;
	}
	public int getTreasurey()
	{
		return treasurey;
	}
	public void setTreasurey(int treasurey)
	{
		this.treasurey = treasurey;
	}
	public void setSize(int size)
	{
		this.size = size;
	}
	public void setMap(int[][] map)
	{
		this.map = map;
	}
	int looking_aristera = -1 ;
	int looking_deksia = 1 ;
	int size ;
	int map[][] ;
	int looking ; // 2 gia panw , 1 gia deksia , -1 gia aristera , 0 gia katw .
	 
	// x = size - 1 -x ; :D :D :D :D
	World(int mx , int my , int lkx[],int lky[],int looking,int size,int treasurex,int treasurey)
	{
		this.looking = looking ;
		monster_x = mx ;
		monster_y = my ;
		this.treasurex = treasurex ;
		this.treasurey = treasurey ;
		lakouves_x = lkx ;
		lakouves_y = lky ;
		this.size = size ;
		player_pos_x = size- 1;
		map = new int[size][size];
		
		EnterValues();
	}
	World(int size)
	{
		this.looking =2 ;
		this.size = size ;
		this.player_pos_x = size -1 ;
		this.map = new int[size][size];
		EnterValues();
	}
	World(Context context)
	{
		try{
		ReadFromFile(context);
		}catch(Exception e)
		{
			System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
		}
		for(int i =0;i<lakouves_x.length;i++)
		{
			if(lakouves_x[i]!=-1){
			System.out.println("PIT : x= "+lakouves_x[i]+" y= "+lakouves_y[i]);
			}
		}
		System.out.println("Le monster at x= "+monster_x+" y= "+monster_y);
		System.out.println("Le treasure at x= "+treasurex+" y= "+treasurey);
		this.looking = 2 ;
		this.player_pos_x = size -1 ;
		map = new int[size][size];
		System.out.println("Size is "+size);
		System.out.println("Error inc");
		EnterValues();
		System.out.println("Finally");
		
	}
	public void setMonster(int monster_x , int monster_y)
	{
		this.monster_x = monster_x;
		this.monster_y = monster_y;
		
	}
	public void setTreasure(int treasurex , int treasurey)
	{
		this.treasurex = treasurex ;
		this.treasurey = treasurey ;
	}
	public void setPits(int lkx[],int lky[])
	{
		lakouves_x = lkx ;
		lakouves_y = lky ;
	}
	public void Movement(int x , int y)
	{
		player_pos_x = x ;
		player_pos_y = y ;
	}
	public void EnterValues(){ 
		//enter monster 
		map[treasurex][treasurey] = 6 ;
		map[monster_x][monster_y]= 3 ;
		//enter values around monster S=1 
		if(!((monster_x+1)>=size)){
			map[monster_x+1][monster_y]=1 ;
		}
		if((monster_x-1 >= 0)){
			map[monster_x-1][monster_y]=1 ;
		}
		if(monster_y-1 >= 0){
			map[monster_x][monster_y-1]=1 ;
		}
		if(!((monster_y+1)>=size))
		{
			map[monster_x][monster_y+1]=1 ;
		}
				//enter pits , L == 4;
		//enter pits , L == 4;
		for(int i =0 ;i<lakouves_x.length ; i++)
		{
			
			if(lakouves_x[i]!=-1){
				int lx = lakouves_x[i];
				int ly = lakouves_y[i];
				
				map[lx][ly]=4 ;
			}
		}
		for(int i = 0 ; i < lakouves_x.length ; i++)
		{
			if(lakouves_x[i]!=-1){
				int lx = lakouves_x[i];
				int ly = lakouves_y[i];
				if(!(lx+1>=size))
				{
					if(map[lx+1][ly]==1){
						map[lx+1][ly]=5;
					}else{
					map[lx+1][ly]=2 ;
					}
				}
				if(lx -1 >=0){
				if(map[lx-1][ly]==1){
					map[lx-1][ly]=5;
				}else{
				map[lx-1][ly]=2 ;
				}
				}
				if(!(ly+1>=size))
				{
					if(map[lx][ly+1]==1){
						map[lx][ly+1]=5;
					}else{
					map[lx][ly+1]=2 ;
					}
				}
				if(ly-1 >= 0){
				if(map[lx][ly-1]==1){
					map[lx][ly-1]=5 ;
				}else{
				map[lx][ly-1]=2 ;
				}
				}
			}
		}
		//map[i][y] = 5 //blood and aura ; 
		
		
		
		
	}
	public int getSize()
	{
		return size ;
	}
	public int[][] getMap()
	{
		return map;
	}
	public int getPlayerX()
	{
		return player_pos_x;
	}
	public int getPlayerY()
	{
		return player_pos_y;
	}
	public void setPlayerY(int y)
	{
		player_pos_y = y ;
	}
	public void setPlayerX(int x)
	{
		player_pos_x = x ;
	}
	public int getLooking()
	{
		return looking ;
	}
	public void setLooking(int i)
	{
		looking = i ;
	}
	public int getId(int x, int y)
	{
		return map[x][y];
	}
	private void ReadFromFile(Context context)
	{
		initializePits();
		int counter = 0;
		String filename = "map.txt";
		String filepath = "Wympus";
		File myInternalFile;
		
		ContextWrapper contextWrapper = new ContextWrapper(context.getApplicationContext());
		File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
		myInternalFile = new File(directory , filename);
		FileInputStream fis;
		
		try{
			fis = new FileInputStream(myInternalFile);
			Scanner scanner = new Scanner(fis);
			
			
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				System.out.println(line);
				if(line.substring(0,4).equals("size"))
				{
					this.size = Integer.parseInt(line.substring(7));
					
					System.out.println("Found le size and is "+size);
				}
				else
				if(line.substring(0,3).equals("pit"))
				{
					lakouves_x[counter] = Integer.parseInt(line.substring(4, 5));
					lakouves_y[counter] = Integer.parseInt(line.substring(6, 7));
					counter ++ ;
					
					System.out.println("Found new pit");
				}
				else
				if(line.substring(0,6).equals("wumpus"))
				{
					System.out.println("Wumpus found");
					monster_x = Integer.parseInt(line.substring(7, 8));
					monster_y = Integer.parseInt(line.substring(9));
				}
				else 
				if(line.substring(0,8).equals("treasure"))
				{
					System.out.println("Treasure found");
					treasurex = Integer.parseInt(line.substring(9, 10));
					treasurey = Integer.parseInt(line.substring(11));
				}
				
				
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("So he read");
		
		
	}
	private void initializePits()
	{
		lakouves_x = new int[20];
		lakouves_y = new int[20];
		for(int i = 0 ; i < lakouves_x.length;i++)
		{
			lakouves_x[i] = -1 ;
			lakouves_y[i] = -1 ;
		}
		
	}
	
}