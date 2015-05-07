package fionaApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class HashSearch {
	
	public static void  main(String[] args) throws IOException {
		String geneIDfile = "D:/lotus/mfanwID.bed";
		List<String> geneIDList =ToolsList.getStringList(geneIDfile);
		HashMap<String, Integer> geneIDhash = new HashMap<String, Integer>();
		int code =0;
		for (int i = 0; i < geneIDList.size(); i++) {
			code++;
			String str = geneIDList.get(i);
			geneIDhash.put(str, code);
		}
		
		String targetfileString = "D:/lotus/ref_Chinese_Lotus_1.1_top_level.gff3";
		String resultFile = "D:/lotus/ChineseLotusSelected.gff3";
		FileWriter fWriter = new FileWriter(resultFile);
		BufferedWriter fWriterBuffer = new BufferedWriter(fWriter);
		FileReader file = new FileReader(targetfileString);
		BufferedReader filein = new BufferedReader(file);
		String line = null;
		int lineno = 0;
		while ((line = filein.readLine()) != null) {	
			String gene = line.split("\t")[0];
			if (geneIDhash.containsKey(gene)) {	
				lineno++;
				fWriterBuffer.write(line);
				fWriterBuffer.newLine();
			} 
		}
		fWriterBuffer.close();
		filein.close();
		System.out.println(lineno);
	}
}
