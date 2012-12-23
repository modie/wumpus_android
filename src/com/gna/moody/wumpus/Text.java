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
				// TODO Auto-generated method stub
				
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
		/*
		String myData = "";
		try
		{
			FileInputStream fis = new FileInputStream(myInternalFile);
			DataInputStream in = new DataInputStream(fis);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    String strLine;
		    while ((strLine = br.readLine()) != null) 
		    {
		    	myData = myData + strLine;
		    }
		    in.close();
		    Log.e("lines = ","0");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//TODO make it read from the file i create so i can check !!! 
		
		
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

/*
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
 
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
 
public class Text extends Activity implements OnClickListener{
 
 private String filename = "map.txt";
 private String filepath = "Wympus";
 File myInternalFile;
 File myExternalFile;
 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.yaw);
 
  ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
  File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
  myInternalFile = new File(directory , filename);
 
  Button saveToInternalStorage = 
   (Button) findViewById(R.id.saveInternalStorage);
  saveToInternalStorage.setOnClickListener(this);
 
  Button readFromInternalStorage = 
   (Button) findViewById(R.id.getInternalStorage);
  readFromInternalStorage.setOnClickListener(this);
 
  Button saveToExternalStorage = 
   (Button) findViewById(R.id.saveExternalStorage);
  saveToExternalStorage.setOnClickListener(this);
 
  Button readFromExternalStorage = 
   (Button) findViewById(R.id.getExternalStorage);
  readFromExternalStorage.setOnClickListener(this);
 
  //check if external storage is available and not read only  
  if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {  
   saveToExternalStorage.setEnabled(false);
   readFromExternalStorage.setEnabled(false);
  } 
  else {
   myExternalFile = new File(getExternalFilesDir(filepath), filename);
  }
 
 }
 
 public void onClick(View v) {
 
  EditText myInputText = (EditText) findViewById(R.id.myInputText);
  TextView responseText = (TextView) findViewById(R.id.responseText);
  String myData = "";
 
  switch (v.getId()) {
  case R.id.saveInternalStorage:
   try {
	
    FileOutputStream fos = new FileOutputStream(myInternalFile);
    fos.write(myInputText.getText().toString().getBytes());
    fos.close();
   } catch (IOException e) {
    e.printStackTrace();
   }
   myInputText.setText("");
   responseText
   .setText("MySampleFile.txt saved to Internal Storage...");
   break;
 
  case R.id.getInternalStorage:
   
    myInputText.setText(readTxt());
      
    break;
 
  case R.id.saveExternalStorage:
   try {
    FileOutputStream fos = new FileOutputStream(myExternalFile);
    fos.write(myInputText.getText().toString().getBytes());
    fos.close();
   } catch (IOException e) {
    e.printStackTrace();
   }
   myInputText.setText("");
   responseText
   .setText("MySampleFile.txt saved to External Storage...");
   break;
 
  case R.id.getExternalStorage:
   try {
    FileInputStream fis = new FileInputStream(myExternalFile);
    DataInputStream in = new DataInputStream(fis);
    BufferedReader br = 
     new BufferedReader(new InputStreamReader(in));
    String strLine;
    while ((strLine = br.readLine()) != null) {
     myData = myData + strLine;
    }
    in.close();
   } catch (IOException e) {
    e.printStackTrace();
   }
   myInputText.setText(myData);
   responseText
   .setText("MySampleFile.txt data retrieved from Internal Storage...");
   break;
 
 
  }
 }
 
 private static boolean isExternalStorageReadOnly() {  
  String extStorageState = Environment.getExternalStorageState();  
  if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {  
   return true;  
  }  
  return false;  
 }  
 
 private static boolean isExternalStorageAvailable() {  
  String extStorageState = Environment.getExternalStorageState();  
  if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {  
   return true;  
  }  
  return false;  
 }  
 

	private String readTxt()
	{
		String myData = "";
		try
		{
			FileInputStream fis = new FileInputStream(myInternalFile);
			DataInputStream in = new DataInputStream(fis);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    String strLine;
		    while ((strLine = br.readLine()) != null) 
		    {
		    	myData = myData + strLine;
		    }
		    in.close();
		    Log.e("lines = ","0");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myData;
	}
}
*/