package fionaApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TimesCount {
	// 此类是为了解决针对一个gene具体信息文件中的基因号出现

	/**
	 * 
	 * @param filepath
	 * @return
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

	private void getGeneCountFile(ArrayList<String> singleGeneIDList,
			ArrayList<String> sumGeneIDList, String countGeneIDFilepath)
			throws IOException {
		FileWriter fWriter = new FileWriter(countGeneIDFilepath);
		BufferedWriter fWriterBuffer = new BufferedWriter(fWriter);
		for (int i = 0; i < singleGeneIDList.size(); i++) {
			System.out.println("now it's the " + i
					+ "th gene' turn, we now begin to count its copies!");
			int geneCount = 0;
			for (int j = 0; j < sumGeneIDList.size(); j++) {
				if (singleGeneIDList.get(i).contains(sumGeneIDList.get(j))) {
					geneCount++;
				}
			}
			fWriterBuffer.write(singleGeneIDList.get(i) + "\t" + geneCount);
			fWriterBuffer.newLine();
		}

		fWriterBuffer.close();
		System.out.println("we temporarily get the gene+count info file in the path: " + countGeneIDFilepath);
	}

	private void writeTimesToFile(String countGeneIDFilepath,
			String itemFilepath, String writeFilepath) throws IOException {
		System.out.println("Now it begins to insert the count info in the item table!");

		FileWriter fWriter = new FileWriter(writeFilepath);
		BufferedWriter fWriterBuffer = new BufferedWriter(fWriter);

		FileReader countGeneIDFile = new FileReader(countGeneIDFilepath);
		BufferedReader countGeneIDfilein = new BufferedReader(countGeneIDFile);
		String countGeneIDline = null;
		int lineNumber =0;
		while ((countGeneIDline = countGeneIDfilein.readLine()) != null) {
			System.out.println("Now it's the " + lineNumber +"th gene's turn, we are rewriting its item info!");
			String[] countGeneIDlineString = countGeneIDline.split("\t");

			FileReader itemFile = new FileReader(itemFilepath);
			BufferedReader itemFilein = new BufferedReader(itemFile);
			String itemline = null;
			while ((itemline = itemFilein.readLine()) != null) {
				String[] itemlinePartString = itemline.split("\t");
				if (countGeneIDlineString[0].contains(itemlinePartString[8])) {
					// 将itemlinePartString转为动态数组
					ArrayList<String> itemlinePartList = new ArrayList<String>();
					for (int i = 0; i < itemlinePartString.length; i++) {
						itemlinePartList.add(itemlinePartString[i]);
					}
					// 将count值插到相应的基因号后
					itemlinePartList.add(9, countGeneIDlineString[1]);
					String newline="";
					for (int i = 0; i < itemlinePartList.size(); i++) {
						newline = newline + itemlinePartList.get(i);
						newline = newline + "\t";
					}
					fWriterBuffer.write(newline);
					fWriterBuffer.newLine();
				}
			}
			itemFilein.close();
			lineNumber++;
		}
		countGeneIDfilein.close();
		fWriter.close();
		System.out.println("congratulations! you have got your result file in the path:  " + writeFilepath);
	}

	/**
	 * 主方法
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		TimesCount obj = new TimesCount();
		
		String singleGeneIDFilepath = "D:/test/tdr/tdr1+2+3singleGeneID.txt";
		String sumGeneIDFilepath = "D:/test/tdr/tdr1+2+3sumGeneID.txt";
		ArrayList<String> singleGeneIDList = obj
				.getStringList(singleGeneIDFilepath);
		ArrayList<String> sumGeneIDList = obj.getStringList(sumGeneIDFilepath);
		//开始写gene+出现次数的文件
		String countGeneIDFilepath = "D:/test/tdr/tdr1+2+3geneIDCount.txt";
		obj.getGeneCountFile(singleGeneIDList, sumGeneIDList,
				countGeneIDFilepath);
		//开始插入count那一栏，并写入新文件
		String targetFilepath = "D:/test/tdr/tdr1+2+3_ItemsInfo.txt";
		String writeInFilePath = "D:/test/tdr/tdr1+2+3_Items_geneCountInfo.txt";
		obj.writeTimesToFile(countGeneIDFilepath, targetFilepath,
				writeInFilePath);

	}

}
