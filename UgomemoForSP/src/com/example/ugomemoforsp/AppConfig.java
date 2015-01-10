package com.example.ugomemoforsp;

import java.text.DecimalFormat;

import android.os.Environment;

public class AppConfig {
	
	public static final String APPNAME = "/Painted/"; 
	
	public static String getDirPath() {
		return Environment.getExternalStorageDirectory()+"/"+APPNAME+"/";
	}
	
	public static String getFilePath(int imageNumber) {
		DecimalFormat form=new DecimalFormat("0000");
		return getDirPath()+"img"+form.format(imageNumber)+".png";
	}
	

}
