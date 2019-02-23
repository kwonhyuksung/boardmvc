package com.kitri.util;

import java.io.File;
import java.io.IOException;

import com.oreilly.servlet.multipart.FileRenamePolicy;

// 이미지 파일의 이름을 바꾸는 정책을 오버라이드. 지금의 코드는 날짜로 바꾸는 것.
public class DateFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File f) {
	    if (createNewFile(f)) {
	      return f;
	    }
	    String name = f.getName();	    
	    String ext = null;

	    int dot = name.lastIndexOf(".");
	    if (dot != -1) {	      
	      ext = name.substring(dot);  // includes "."
	    }
	    else {	      
	      ext = "";
	    }
	    
	    String newName = System.nanoTime() + ext; // 12937812908370912730.jpg 정도? 10억 분의 1초?
	    f = new File(f.getParent(), newName);	    

	    return f;
	  }

	  private boolean createNewFile(File f) {
	    try {
	      return f.createNewFile();
	    }
	    catch (IOException ignored) {
	      return false;
	    }
	  }
	
}
