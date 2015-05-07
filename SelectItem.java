package fionaApp;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SelectItem {
	
	/**
	 * 这个方法用作从文件中读取行然后将行中字符串元素放入一个字符串List
	 * @param filepath输入文件路径
	 * @return 字符串LIst
	 * @throws IOException 
	 */
	public ArrayList<String> getStringList(String filepath) throws IOException {
		FileReader file = new FileReader(filepath);
		BufferedReader filein = new BufferedReader(file);
		String line = null;
		ArrayList<String> lineList = new ArrayList<String>();
		while ((line = filein.readLine()) != null) {
			   lineList.add(line);
			  }
		filein.close();
		return lineList;		
	}
	
	
	/**
	 * 
	 * @param list1
	 * @param list2
	 */
	public ArrayList<String> getOverlapList(ArrayList<String> list1, ArrayList<String> list2) {
		ArrayList<String> overlapList = new ArrayList<String>();
		for (int i = 0; i <list1.size(); i++) {
			for (int j = 0; j < list2.size(); j++) {				
					if (list2.get(j).contains(list1.get(i)) ) {
						overlapList.add(list2.get(j));
						break;				
				}
			}			
		}
		return overlapList;
	}
	
	

	
	/**
	 * 
	 * @param listStrings
	 * @param filepath
	 * @return
	 * @throws IOException
	 */
	public String  getListFile(String[] listStrings, String filepath) throws IOException {
	
		File newfile = new File(filepath);
		if (!newfile.exists()) {
			newfile.createNewFile();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(newfile));
		for (int i = 0; i <listStrings.length; i++) {
			bw.write(listStrings[i]);
			bw.newLine();
		}
		bw.flush();
		bw.close();
		String resultFile =newfile.getAbsolutePath();
		return resultFile;
	}
	
	
	/**
	 * 主方法
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		SelectItem obj = new SelectItem();
		ArrayList<String>zp1List = obj.getStringList("D:/test/tdr/tdrCandidateGenefromArray.txt");
		
		ArrayList<String>zp2List = obj.getStringList("D:/test/tdr/tdr123OverlapFile.txt");
		//ArrayList<String>zp3List = obj.getStringList("D:/test/tdrZp3geneID.txt");
		
		System.out.println("now it begins to overlap!");
		
			
			 ArrayList<String> overlapList12 = obj.getOverlapList(zp1List, zp2List);
			int length = overlapList12.size();
			String[] overlapArray12 =new String[length];
		
			if (overlapArray12 !=null) {
				for (int i = 0; i < overlapArray12.length; i++) {
					overlapArray12[i] = overlapList12.get(i);
				}		
			}
			
			String overlapFile12filepath = obj.getListFile(overlapArray12, "D:/test/tdr/tdr123overlap_microarrayOverlapFile.txt");
			System.out.println("you got the file in the path: " + overlapFile12filepath);
		
		
	}
	

}
