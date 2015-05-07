package fionaApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class DeleteRepeat {

	public static void main(String[] args) throws IOException {
		String folderPath = "D:/PeakAnno/annoSum";
		File folder = new File(folderPath);
		File[] fileArray = folder.listFiles();
		for (int i = 0; i < fileArray.length; i++) {
			if (fileArray[i].getAbsolutePath().contains(".bed")) {
				File f = fileArray[i];				
				String IDFile= f.getAbsolutePath().replace(".bed", "SumGeneIDset.bed");				
				FileWriter fw = new FileWriter(IDFile);
				BufferedWriter fbw = new BufferedWriter(fw);
				FileReader fr = new FileReader(f);		
				BufferedReader br = new BufferedReader(fr);				
				HashSet<String> astr = new HashSet<String>();
				String data = br.readLine();
				String[] dataArr = null;
				
				while (data != null) {
					dataArr = data.split("\t");
					astr.add(dataArr[12]);
					data = br.readLine();
				}
				br.close();
				Object[] ID = astr.toArray();
				for (int j = 0; j < ID.length; j++) {					
					String IDstr =ID[j].toString();
					fbw.write(IDstr);
					fbw.newLine();					
					}		
				fbw.close();						
			}
		}
	}
}
