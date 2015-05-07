package fionaApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Selection {
	
	/**
	 * 这个方法用作从文件中读取行然后将行中字符串元素放入一个字符串List
	 * @param filepath输入文件路径
	 * @return 字符串LIst
	 * @throws IOException 
	 */
	public List<String> getStringList(String filepath) throws IOException {
		FileReader file = new FileReader(filepath);
		BufferedReader filein = new BufferedReader(file);
		String line = null;
		List<String> lineList = new ArrayList<String>();
		while ((line = filein.readLine()) != null) {
			   lineList.add(line);
			  }
		filein.close();
		return lineList;		
	}
	
	
	
	/**
	 * 主方法
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Selection ams = new Selection();
		List<String> amsArrayGeneList = ams.getStringList("D:/PeakAnno/annoSum/amsZp2SumGeneIDset.bed");
		List<String> amsSeqGeneList = ams.getStringList("D:/PeakAnno/annoSum/amsZp3SumGeneIDset.bed");
		int sum = 0;
		for (int i = 0; i < amsArrayGeneList.size(); i++) {
			for (int j = 0; j < amsSeqGeneList.size(); j++) {
				if (amsSeqGeneList.get(j).contains(amsArrayGeneList.get(i))) {
					sum++;
					break;
				}
			}			
		}
		System.out.println(sum);
		
	}

}
