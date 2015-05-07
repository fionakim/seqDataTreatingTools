package fionaApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SelectOverlap {

	/**
	 * 这个方法用作从文件中读取行然后将行中字符串元素放入一个字符串List
	 * 
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
	public ArrayList<String> getOverlapList(ArrayList<String> list1,
			ArrayList<String> list2) {
		ArrayList<String> overlapList = new ArrayList<String>();
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j < list2.size(); j++) {
				if (list2.get(j).contains(list1.get(i))) {
					overlapList.add(list1.get(i));
					break;
				}
			}
		}
		return overlapList;
	}

	/**
	 * 
	 * @param overlapList
	 * @param filepath
	 * @return
	 * @throws IOException
	 */
	public String getOverListFile(ArrayList<String> overlapList, String filepath)
			throws IOException {

		File newfile = new File(filepath);
		if (!newfile.exists()) {
			newfile.createNewFile();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(newfile));
		for (int i = 0; i < overlapList.size(); i++) {
			bw.write(overlapList.get(i));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		String resultFile = newfile.getAbsolutePath();
		return resultFile;
	}

	/**
	 * 主方法
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		SelectOverlap obj = new SelectOverlap();
		//ArrayList<String> zp1List = obj
			//	.getStringList("D:/test/tdrZp1geneID.txt");

		ArrayList<String> zp2List = obj
				.getStringList("D:/test/tdrCandidateGenefromArray.txt");
		ArrayList<String> zp3List = obj
				.getStringList("D:/test/tdr123OverlapFile.txt");

		System.out.println("now it begins to overlap!");

	
		ArrayList<String> overlapList23 = obj.getOverlapList(zp2List, zp3List);

		String overlapFile23filepath = obj.getOverListFile(overlapList23,
				"D:/test/tdr123overlap_microarrayOverlapFile.txt");
		System.out.println("you got the file in the path: "
				+ overlapFile23filepath);

	}

}
