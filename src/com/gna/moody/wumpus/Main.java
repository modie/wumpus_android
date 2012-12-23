package com.gna.moody.wumpus;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main extends Activity 
{
	char c[] ;
	
	private String filename = "map.txt";
	private String filepath = "Wympus";
	File myInternalFile;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button bt2 = (Button)findViewById(R.id.button2);
		Button bt1 =(Button)findViewById(R.id.button1);
		Button bt3 =(Button) findViewById(R.id.button3);
		Button bt4 =(Button) findViewById(R.id.button4);
		bt4.setEnabled(false);
		bt1.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				Intent openWumpus = new Intent("android.intent.action.WUMPUSAGENT");
				startActivity(openWumpus);
			}
		});
		bt2.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent openSolo = new Intent("android.intent.action.WUMPUSPLAYER");
				startActivity(openSolo);
				
			}
		});
		bt3.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent openText = new Intent("android.intent.action.TEXT");
				startActivity(openText);
				
			}
		});
		bt4.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent openMapEdit = new Intent("android.intent.action.WorldEdit");
				startActivity(openMapEdit);
				
			}
		});
		ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
		File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
		myInternalFile = new File(directory , filename);
		InputStream inputStream = getResources().openRawResource(R.raw.hello);
		
		Scanner input = new Scanner(inputStream);
		try
		{
			
			Log.e("line", "0");
			PrintStream ps = new PrintStream(myInternalFile);
			String line = input.nextLine();
			int counter_lines = 0;
			do
			{
				ps.println(line);
				line = input.nextLine();
				counter_lines ++ ;
			}
			while(input.hasNextLine());
			ps.close();
			
			input.close();
			Log.e("counter","= "+counter_lines);
			Log.e("wtf","File should have been created ");	
			
		}catch(Exception e)
		{
			e.toString();
		}
		
	}
	
	private String readTxt()
	{
		String hello_data = "" ;
		InputStream inputStream = getResources().openRawResource(R.raw.hello);
		Scanner input = new Scanner(inputStream);
		int i;
		
		try {
			String line = input.nextLine();
			
			while (input.hasNextLine())
			{
				
				hello_data += line ;
				line = input.nextLine();
			}
			inputStream.close();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		 
		return hello_data;
	}
	
	

}
