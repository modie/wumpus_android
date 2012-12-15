package com.gna.moody.wumpus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WumpusMain extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button bt2 = (Button)findViewById(R.id.button2);
		Button bt1 =(Button)findViewById(R.id.button1);
		Button bt3 =(Button) findViewById(R.id.button3);
		Button bt4 =(Button) findViewById(R.id.button4);
		bt1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
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
				Intent openMapEdit = new Intent();
				startActivity(openMapEdit);
				
			}
		});
	}
	
	

}
