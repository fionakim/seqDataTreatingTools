package fionaApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MinusZp4 {
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
	 * @param List12
	 * @param filepath
	 * @return
	 * @throws IOException
	 */
	public void getListFile(ArrayList<String> List12, String filepath)
			throws IOException {

		File newfile = new File(filepath);
		if (!newfile.exists()) {
			newfile.createNewFile();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(newfile));
		for (int i = 0; i < List12.size(); i++) {
			bw.write(List12.get(i));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	public ArrayList<String> minusList(ArrayList<String> minuendlist,
			ArrayList<String> subtractorlist) {
		ArrayList<String> resultList = new ArrayList<String>();
		for (int i = 0; i < minuendlist.size(); i++) {
			if (!subtractorlist.contains(minuendlist.get(i))) {
				resultList.add(minuendlist.get(i));
			}
		}
		return resultList;
	}

	/**
	 * 主方法
	 * 
	 * @param args
	 * @throws IOException
	 */
//	public static void main(String[] args) throws IOException {
//		MinusZp4 obj = new MinusZp4();
//		ArrayList<String> targetList = obj
//				.getStringList("D:/test/tdr/tdr123sum_microarrayOverlapFile.txt");
//
//		ArrayList<String> zp4List = obj
//				.getStringList("D:/test/tdr/tdrZp4geneID.txt");
//
//		System.out.println("now it begins to minus zp4!");
//
//		ArrayList<String> differenceList = obj.minusList(targetList, zp4List);
//
//		String differenceListFilepath = "D:/test/tdr/tdr123sum_microarrayOverlap-zp4File.txt";
//
//		obj.getListFile(differenceList, differenceListFilepath);
//
//		System.out.println("you got the file in the path: "
//				+ differenceListFilepath);
//
//	}

}
