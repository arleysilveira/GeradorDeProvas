package br.com.rh.util;
/*import java.io.ByteArrayInputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
  

import org.w3c.dom.Document;  
import org.w3c.tidy.Tidy;  
  
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;  
  
public class Html2Pdf {  
  
   public static void convert(String input, OutputStream out) throws DocumentException{  
        convert(new ByteArrayInputStream(input.getBytes()), out);  
   }  
     
   public static void convert(InputStream input, OutputStream out) throws DocumentException{  
       Tidy tidy = new Tidy();             
       Document doc = tidy.parseDOM(input, null);  
        ITextRenderer renderer = new ITextRenderer();  
        renderer.setDocument(doc, null);  
        renderer.layout();         
        renderer.createPDF(out);                
   }     
  
} */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import org.w3c.dom.Document;
import org.w3c.tidy.Configuration;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.SAXException;

import com.lowagie.text.DocumentException;

public class Html2Pdf {	
	
	public static void convert(String input, OutputStream out) throws DocumentException, SAXException, IOException{
        convert(new ByteArrayInputStream(input.getBytes()), out);
	}
	
	public static void convert(InputStream input, OutputStream out) throws DocumentException, SAXException, IOException{
		Tidy tidy = new Tidy();    
    	tidy.setCharEncoding(Configuration.UTF8);  
    	Document doc = tidy.parseDOM(input, null);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(doc, null);
        renderer.layout();       
        renderer.createPDF(out);        		
	}	

}
