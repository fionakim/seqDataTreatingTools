package fionaApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CollectItems {

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

	private void collectItems(ArrayList<String> List, String itemfilepath, String resultFile )
			throws IOException {
		FileWriter fWriter = new FileWriter(resultFile);
		BufferedWriter fWriterBuffer = new BufferedWriter(fWriter);		

		for (int i = 0; i < List.size(); i++) {
			System.out.println("Now it is the " + i +"th gene's turn!");
			FileReader file = new FileReader(itemfilepath);
			BufferedReader filein = new BufferedReader(file);
			String line = null;
			while ((line = filein.readLine()) != null) {
				String[] linePartString = line.split("\t");
				
				if (linePartString[8].contains(List.get(i))) {
					fWriterBuffer.write(line);
					fWriterBuffer.newLine();
				}
			}
			filein.close();
		}
		fWriterBuffer.close();
	}

	/**
	 * 主方法
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		CollectItems obj = new CollectItems();
		// 将基因号文件转为List
		String candidateGenefilepath = "D:/test/tdr/tdr1&2+1&3+2&3_microarrayOverlap-zp4File.txt";//可根据需要修改
		ArrayList<String> geneList = obj.getStringList(candidateGenefilepath);
		
		
		System.out.println("Now it begins to collect items!" );
		
		// 从候选item文件中筛选目标item
		String candidateItemFilepath = "D:/test/tdr/tdr1+2+3+4_ItemsInfo.txt";//可根据需要修改
		String resultFilepath = "D:/test/tdr/tdr1&2+1&3+2&3_microarrayOverlap-zp4_1+2+3+4Items.txt";//可根据需要修改
		obj.collectItems(geneList, candidateItemFilepath,resultFilepath);
		System.out.println("you have got your result file at: " + resultFilepath);
	}

}
