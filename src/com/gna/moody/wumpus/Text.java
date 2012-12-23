package com.gna.moody.wumpus;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Text extends Activity {
	FileOutputStream fos ;
	String FILENAME = "InternalString";
	private String filename = "map.txt";
	private String filepath = "Wympus";
	EditText helloTxt;
	File myInternalFile ;
    @Override
	public void onCreate(Bundle savedInstanceState) 
    {
    	ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
    	File directory = contextWrapper.getDir(filepath,Context.MODE_PRIVATE);
    	myInternalFile = new File(directory,filename);
    	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		        
		helloTxt = (EditText)findViewById(R.id.txtData);
		Button btnSave = (Button)findViewById(R.id.btnSave);
		Button btnClear = (Button)findViewById(R.id.btnClearScreen);
		helloTxt.setText(readTxt());
		btnSave.setOnClickListener( new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				try
				{
					FileOutputStream fos = new FileOutputStream(myInternalFile);
				    fos.write(helloTxt.getText().toString().getBytes());
				    fos.close();
					
				} catch (Exception e)
				{
					
					e.printStackTrace();
				}
				
			}
		});
		btnClear.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				
				
				helloTxt.setText(readTxt());
			}
		});
		try
		{
			fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.close();
		} 
		catch (FileNotFoundException e)
		{
			
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			
			e.printStackTrace();
		}
	}
    
	private String readTxt()
	{
		
		
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int i;
		
		try {
			FileInputStream fis = new FileInputStream(myInternalFile);
			i = fis.read();
			
			while (i != -1)
			{
				byteArrayOutputStream.write(i);
				i = fis.read();
			}
			fis.close();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		 
		return byteArrayOutputStream.toString();
		
		//return myData ;
		
	}
}
