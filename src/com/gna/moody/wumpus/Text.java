package com.gna.moody.wumpus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Text extends Activity {
	
    @Override
	public void onCreate(Bundle savedInstanceState) 
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		        
		EditText helloTxt = (EditText)findViewById(R.id.txtData);
		Button btnSave = (Button)findViewById(R.id.btnSave);
		helloTxt.setText(readTxt());
		btnSave.setOnClickListener( new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				
			}
		});
	}
    
	private String readTxt()
	{
	
		InputStream inputStream = getResources().openRawResource(R.raw.hello);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int i;
		
		try {
			i = inputStream.read();
			
			while (i != -1)
			{
			byteArrayOutputStream.write(i);
			i = inputStream.read();
			}
			inputStream.close();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		 
		return byteArrayOutputStream.toString();
	}
}