package fionaApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

class ToolsList {
	public static final int BUFSIZE = 1024 * 32;
	
	public static  List<String> getOverlap(List<String> zp1List, List<String> zp2List) {
		ArrayList<String> resultLiStrings = new ArrayList<String>();
		for (int i = 0; i < zp1List.size(); i++) {
			for (int j = 0; j < zp2List.size(); j++) {
				if (zp1List.get(i).contains(zp2List.get(j))) {
					resultLiStrings.add(zp1List.get(i));
					break;
				}
			}
		}
		return resultLiStrings;
	}
	
	
	public static List<String> getminuslist(List<String> zp3geneList,
			List<String> zp4geneList) {
		List<String> resultList = new ArrayList<String>();
		for (int i = 0; i < zp3geneList.size(); i++) {
			if (!zp4geneList.contains(zp3geneList.get(i))) {
				resultList.add(zp3geneList.get(i));
			}
		}
		return resultList;

	}

	public static  void minus4FromFile(String filepath, List<String> zp4geneList,
			String resultFilePath) throws IOException {
		File newfile = new File(resultFilePath);
		BufferedWriter bw = new BufferedWriter(new FileWriter(newfile));

		FileReader file = new FileReader(filepath);
		BufferedReader filein = new BufferedReader(file);
		String line = null;
		String[] lineList = null;
		while ((line = filein.readLine()) != null) {
			lineList = line.split("\t");
			zp4geneList.contains(lineList[12]);
			for (int j = 0; j < zp4geneList.size(); j++) {
				if (!lineList[12].contains(zp4geneList.get(j))) {
					bw.write(line);
					bw.newLine();
				}
			}
		}
		filein.close();
		bw.flush();
		bw.close();
	}

	public static void writeListToFile(List<String> targetList, String filepath,
			String resultFilePath) throws IOException {
		File newfile = new File(resultFilePath);
		BufferedWriter bw = new BufferedWriter(new FileWriter(newfile));
		FileReader file = new FileReader(filepath);
		BufferedReader filein = new BufferedReader(file);
		String line = null;
		String[] lineList = null;
		int num = 0;
		while ((line = filein.readLine()) != null) {
			lineList = line.split("\t");

			if (targetList.contains(lineList[12])) {
				num++;
				bw.write(line);
				bw.newLine();
			}
		}
		filein.close();
		bw.flush();
		bw.close();
		System.out.println("the line sum count is: " + num);
	}


	public static void writeTimesToFile(String countGeneIDFilepath,
			String itemFilepath, String writeFilepath) throws IOException {
		System.out
				.println("Now it begins to insert the count info in the item table!");

		FileWriter fWriter = new FileWriter(writeFilepath);
		BufferedWriter fWriterBuffer = new BufferedWriter(fWriter);

		FileReader countGeneIDFile = new FileReader(countGeneIDFilepath);
		BufferedReader countGeneIDfilein = new BufferedReader(countGeneIDFile);
		String countGeneIDline = null;
		int lineNumber = 0;
		while ((countGeneIDline = countGeneIDfilein.readLine()) != null) {
			System.out.println("Now it's the " + lineNumber
					+ "th gene's turn, we are rewriting its item info!");
			String[] countGeneIDlineString = countGeneIDline.split("\t");

			FileReader itemFile = new FileReader(itemFilepath);
			BufferedReader itemFilein = new BufferedReader(itemFile);
			String itemline = null;
			while ((itemline = itemFilein.readLine()) != null) {
				String[] itemlinePartString = itemline.split("\t");
				if (countGeneIDlineString[0].contains(itemlinePartString[12])) {
					// 将itemlinePartString转为动态数组
					ArrayList<String> itemlinePartList = new ArrayList<String>();
					for (int i = 0; i < itemlinePartString.length; i++) {
						itemlinePartList.add(itemlinePartString[i]);
					}
					// 将count值插到相应的基因号后
					itemlinePartList.add(13, countGeneIDlineString[1]);					
					String newline = "";
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
		System.out
				.println("congratulations! you have got your result file in the path:  "
						+ writeFilepath);
	}

	public static  void getGeneCount(List<String> singleGeneIDList,
			List<String> sumGeneIDList, String countGeneIDFilepath)
			throws IOException {
		FileWriter fWriter = new FileWriter(countGeneIDFilepath);
		BufferedWriter fWriterBuffer = new BufferedWriter(fWriter);
		for (int i = 0; i < singleGeneIDList.size(); i++) {
			System.out.println("now it's the " + i + "th gene' turn, we now begin to count its copies!");
			int geneCount = 0;
			for (int j = 0; j < sumGeneIDList.size(); j++) {
				if (sumGeneIDList.get(j).contains(singleGeneIDList.get(i))) {
					geneCount++;	
			}									
			}
			fWriterBuffer.write(singleGeneIDList.get(i) + "\t" + geneCount);
			fWriterBuffer.newLine();
		}
		fWriterBuffer.close();
		System.out	.println("we temporarily get the gene+count info file in the path: "
						+ countGeneIDFilepath);
	}
	
	public static List<String> getStringList(String filepath) throws IOException {
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
	public static void mergeFile(String[] filepathStrings, String sumFilepath)
			throws IOException {
		FileChannel outChannel = null;
		FileOutputStream outputStream = new FileOutputStream(sumFilepath);
		outChannel = outputStream.getChannel();
		for (int i = 0; i < filepathStrings.length; i++) {
			FileInputStream stream = new FileInputStream(filepathStrings[i]);
			FileChannel fc = stream.getChannel();
			ByteBuffer bb = ByteBuffer.allocate(BUFSIZE);
			while (fc.read(bb) != -1) {
				bb.flip();
				outChannel.write(bb);
				bb.clear();
			}
			fc.close();
			stream.close();
		}
		if (outChannel != null) {
			outChannel.close();
		}
		outputStream.close();
	}

	public static void collectItems(List<String> geneIdList,
			String itemfilepath, String resultFile) throws IOException {
		FileWriter fWriter = new FileWriter(resultFile);
		BufferedWriter fWriterBuffer = new BufferedWriter(fWriter);

		for (int i = 0; i < geneIdList.size(); i++) {
			System.out.println("Now it is the " + i + "th gene's turn!");
			FileReader file = new FileReader(itemfilepath);
			BufferedReader filein = new BufferedReader(file);
			String line = null;
			while ((line = filein.readLine()) != null) {
				String[] linePartString = line.split("\t");
				if (linePartString[12].contains(geneIdList.get(i))) {
					fWriterBuffer.write(line);
					fWriterBuffer.newLine();
				}
			}
			filein.close();
		}
		fWriterBuffer.close();
	}

	
	public static void writeListToFile(List<String> targetList,
			String resultFilePath) throws IOException {
		File newfile = new File(resultFilePath);
		BufferedWriter bw = new BufferedWriter(new FileWriter(newfile));
		for (int i = 0; i < targetList.size(); i++) {
			bw.write(targetList.get(i));
			bw.newLine();
		}
		bw.flush();
		bw.close();		
	}


	
	public static void replaceLines(String targetfileString, String resultFile) throws IOException {
		FileWriter fWriter = new FileWriter(resultFile);
		BufferedWriter fWriterBuffer = new BufferedWriter(fWriter);
		FileReader file = new FileReader(targetfileString);
		BufferedReader filein = new BufferedReader(file);
		FileWriter fWriter2 = new FileWriter("D:/lotus/hx_unigene_allID.bed");
		BufferedWriter fWriterBuffer2 = new BufferedWriter(fWriter2);
		String line = null;
		String newline = null;
		int IDno=0;
		while ((line = filein.readLine()) != null) {
			if (line.startsWith(">") ) {
				IDno++;
				newline = line.split(" ")[0];
				String geneID = line;
				fWriterBuffer2.write(geneID);
				fWriterBuffer2.newLine();
			}else {
				newline=line;
			}
			fWriterBuffer.write(newline);
			fWriterBuffer.newLine();
			
		}
		fWriterBuffer.close();
		fWriterBuffer2.close();
		filein.close();
		System.out.println(IDno);
		}
}

public class Work {
	public static void main(String[] args) throws IOException {
	
		String targetfileString  = "D:\\lotus\\rawData\\GCF_000365185.1_Chinese_Lotus_1.1_protein.faa";
		String resultFile = "D:\\lotus\\GCF_000365185.1_Chinese_Lotus_1.1_protein_modify.faa";
		ToolsList.replaceLines(targetfileString, resultFile);		
//		List<String> allID = ToolsList.getStringList("D:/lotus/hx_unigene_allIDfinal.bed");
//		List<String> nrID = ToolsList.getStringList("D:/lotus/hx_unigene_nrGeneID.bed");
//		List<String> overlapList = ToolsList.getOverlap(allID, nrID);
//		System.out.println("the size is: " + overlapList.size());
//		ToolsList.writeListToFile(overlapList, "D:/lotus/hx_overlapID.bed");
//		List<String> geneList = ToolsList.getStringList("D:/PeakAnno/annoSum/ams1+2sumGeneID(single).bed");
//		List<String> arrayList = ToolsList.getStringList("D:/PeakAnno/annoSum/amsZp3SumGeneIDset.bed");
//		List<String> overlapList = ToolsList.getOverlap(geneList, arrayList);
//		System.out.println("the size is: "
//				+ overlapList.size());
		
//		List<String> zp4GeneID  =ToolsList .getStringList("D:/PeakAnno/annoSum/amsZp4SumGeneIDset.bed");
//		List<String> resultList = ToolsList.getminuslist(overlapList, zp4GeneID);
//	ToolsList.writeListToFile(resultList, "D:/PeakAnno/annoSum/amsZp3&Array-4GeneID.bed");
//	ToolsList.writeListToFile(overlapList, "D:/PeakAnno/annoSum/ams1+2sumGeneID(single)&array-4.bed");
		
//	List<String> sumGeneID  =ToolsList .getStringList("D:/PeakAnno/annoSum/ams1+2+3sumGeneIDminusZp4.bed");
//	List<String> singleGeneID  =ToolsList .getStringList("D:/PeakAnno/annoSum/ams1+2+3sumGeneID(single)minusZp4.bed");
//	List<String> zp123summinus4GeneID = ToolsList.getminuslist(zp123sumGeneID, zp4GeneID);
//	String resultFilePath = "D:/PeakAnno/annoSum/ams1+2+3sumGeneIDminusZp4.bed";
//		String geneCountFile = "D:/PeakAnno/annoSum/ams1+2+3-4geneCount.bed";
//		ToolsList.getGeneCount(singleGeneID, sumGeneID, geneCountFile);
//		String recordWithGeneCount = "D:/PeakAnno/annoSum/amsItemFileWith1+2+3-4geneCount.bed";
//		String itemFilepath = "D:/PeakAnno/annoSum/amsZp1+2+3minus4.bed";
//		ToolsList.writeTimesToFile(geneCountFile, itemFilepath, recordWithGeneCount);		
//	List<String> geneIdList  =ToolsList .getStringList("D:/PeakAnno/annoSum/amsZp1&2&3&array-4overlapGeneID.bed");
//	String itemfilepath = "D:/PeakAnno/annoSum/amsItemFileWith1+2+3-4geneCount.bed";
//	String resultFile = "D:/PeakAnno/annoSum/amsZp1&2&3&array-4overlap_ItemFileWith1+2+3-4geneCount.bed";
//	ToolsList.collectItems(geneIdList, itemfilepath, resultFile);
//		
	}
}
	
