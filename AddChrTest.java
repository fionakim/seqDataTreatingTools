package fionaApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class AddChrTest{
	public String combinearraystring(String[] arr_name) {
		String newline = new String();
		for (int i = 0; i < arr_name.length; i++) {
			newline = newline.concat(arr_name[i]);
			newline = newline.concat("\t");
		}return(newline);
	}
	
	public void modifyFile(String filepath,String resultpath) throws IOException {
		
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
			
			File newBEDfile= new File(resultpath);
			BufferedWriter bw = new BufferedWriter(new FileWriter(newBEDfile));
			String str= "chr";			
			String regEx1="Pt";
			String regEx2="Mt";
			String newline =null;
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				if ((!line.startsWith(regEx1)) && (!line.startsWith(regEx2))) {
					newline = line;
				}else if (line.startsWith(regEx1)) {
					newline = line.replaceFirst(regEx1, "p");
				} else {
					newline = line.replaceFirst(regEx2, "m");
				}
				
				bw.write(str+newline);
				bw.newLine();
			}				
				bw.close();
				br.close();
	}
	
 public static void main(String[] args) throws IOException {
	 AddChrTest object= new AddChrTest();
	 object.modifyFile("G:\\bamBed\\untri\\tdrZp4.bed","G:\\bamBed\\untri\\tdrZp4Chr.bed");
}
}
