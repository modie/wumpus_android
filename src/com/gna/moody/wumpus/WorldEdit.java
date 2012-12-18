package com.gna.moody.wumpus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WorldEdit extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapedit);
		Button btsmall = (Button)findViewById(R.id.btSmall);
		btsmall.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent startMapEditor = new Intent("android.intent.action.MapEditor");
				startActivity(startMapEditor);
				
			}
		});
	}
	
}
