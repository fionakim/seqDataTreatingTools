package fionaApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Selection3 {

	/**
	 * 主方法
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Selection3 ams = new Selection3();
		List<String> zp1List = ams
				.getStringList("D:/PeakAnno/annoSum/amsZp1SumGeneIDset.bed");
		List<String> zp2List = ams
				.getStringList("D:/PeakAnno/annoSum/amsZp2SumGeneIDset.bed");
		List<String> zp3List = ams
				.getStringList("D:/PeakAnno/annoSum/amsZp3SumGeneIDset.bed");
		List<String> zp4List = ams
				.getStringList("D:/PeakAnno/annoSum/amsZp4SumGeneIDset.bed");
		List<String> arrayList = ams
				.getStringList("D:/PeakAnno/annoSum/amsMicroArrayID.bed");

		List<String> overLap12 = ams.getOverlap(zp1List, zp2List);
		List<String> overlap12Array = ams.getOverlap(overLap12, arrayList);
		List<String> overlap12Arrayminus4 = ams.minusList(overlap12Array,
				zp4List);
		List<String> overLap13 = ams.getOverlap(zp1List, zp3List);
		List<String> overlap13Array = ams.getOverlap(overLap13, arrayList);
		List<String> overlap13Arrayminus4 = ams.minusList(overlap13Array,
				zp4List);
		List<String> overLap23 = ams.getOverlap(zp2List, zp3List);
		List<String> overlap23Array = ams.getOverlap(overLap23, arrayList);
		List<String> overlap23Arrayminus4 = ams.minusList(overlap23Array,
				zp4List);
		List<String> overLap123 = ams.getOverlap(overLap13, overLap12);

		List<String> overlap123Minus4 = ams.minusList(overLap123, zp4List);
		List<String> overLap123Array = ams.getOverlap(overLap123, arrayList);
		List<String> overLap123ArrayMinusZp4 = ams.minusList(overLap123Array,
				zp4List);
		List<String> overlap12minus4 = ams.minusList(overLap12, zp4List);
		List<String> overlap13minus4 = ams.minusList(overLap13, zp4List);
		List<String> overlap23minus4 = ams.minusList(overLap23, zp4List);

		System.out.println("the overLap1_2's count is: " + overLap12.size());
		System.out.println("the overLap1_2_array's count is: "
				+ overlap12Array.size());
		System.out.println("the overLap1_2_array-4's count is: "
				+ overlap12Arrayminus4.size());

		System.out.println("the overLap1_3's count is: " + overLap13.size());
		System.out.println("the overLap1_3_array's count is: "
				+ overlap13Array.size());
		System.out.println("the overLap1_3_array-4's count is: "
				+ overlap13Arrayminus4.size());

		System.out.println("the overLap2_3's count is: " + overLap23.size());
		System.out.println("the overLap2_3_array's count is: "
				+ overlap23Array.size());
		System.out.println("the overLap2_3_array-4's count is: "
				+ overlap23Arrayminus4.size());

		System.out.println("the overLap1_2-4's count is: "
				+ overlap12minus4.size());
		System.out.println("the overLap1_3-4's count is: "
				+ overlap13minus4.size());
		System.out.println("the overLap2_3-4's count is: "
				+ overlap23minus4.size());
		System.out.println("the overLap1_2_3's count is: " + overLap123.size());
		System.out.println("the overLap1_2_3-4's count is: "
				+ overlap123Minus4.size());
		System.out.println("the overLap1_2_3_array's count is: "
				+ overLap123Array.size());
		System.out.println("the overLap1_2_3_array-4's count is: "
				+ overLap123ArrayMinusZp4.size());
		String overLap12filepath = "D:/PeakAnno/annoSum/amsZp1&2overlapGeneID.bed";
		String overLap12Arrayfilepath = "D:/PeakAnno/annoSum/amsZp1&2&arrayOverlapGeneID.bed";
		String overLap12Arrayminus4filepath = "D:/PeakAnno/annoSum/amsZp1&2&arrayMinus4OverlapGeneID.bed";
		String overLap13filepath = "D:/PeakAnno/annoSum/amsZp1&3overlapGeneID.bed";
		String overLap13Arrayfilepath = "D:/PeakAnno/annoSum/amsZp1&3&arrayOverlapGeneID.bed";
		String overLap13Arrayminus4filepath = "D:/PeakAnno/annoSum/amsZp1&3&arrayMinus4OverlapGeneID.bed";
		String overLap23filepath = "D:/PeakAnno/annoSum/amsZp2&3overlapGeneID.bed";
		String overLap23Arrayfilepath = "D:/PeakAnno/annoSum/amsZp2&3&arrayOverlapGeneID.bed";
		String overLap23Arrayminus4filepath = "D:/PeakAnno/annoSum/amsZp2&3&arrayMinus4OverlapGeneID.bed";
		String overLap12minus4filepath = "D:/PeakAnno/annoSum/amsZp1&2-4overlapGeneID.bed";
		String overLap13minus4filepath = "D:/PeakAnno/annoSum/amsZp1&3-4overlapGeneID.bed";
		String overLap23minus4filepath = "D:/PeakAnno/annoSum/amsZp2&3-4overlapGeneID.bed";
		String overLap123filepath = "D:/PeakAnno/annoSum/amsZp1&2&3overlapGeneID.bed";
		String overLap123Arrayfilepath = "D:/PeakAnno/annoSum/amsZp1&2&3&arrayOverlapGeneID.bed";
		String overLap123Minus4filepath = "D:/PeakAnno/annoSum/amsZp1&2&3-4overlapGeneID.bed";
		String overLap123ArrayMinus4filepath = "D:/PeakAnno/annoSum/amsZp1&2&3&array-4overlapGeneID.bed";

		ams.writeListToFile(overLap12, overLap12filepath);
		ams.writeListToFile(overlap12Array, overLap12Arrayfilepath);
		ams.writeListToFile(overlap12minus4, overLap12minus4filepath);
		ams.writeListToFile(overlap12Arrayminus4, overLap12Arrayminus4filepath);
		ams.writeListToFile(overLap13, overLap13filepath);
		ams.writeListToFile(overlap13minus4, overLap13minus4filepath);
		ams.writeListToFile(overlap13Array, overLap13Arrayfilepath);
		ams.writeListToFile(overlap13Arrayminus4, overLap13Arrayminus4filepath);
		ams.writeListToFile(overLap23, overLap23filepath);
		ams.writeListToFile(overlap23minus4, overLap23minus4filepath);
		ams.writeListToFile(overlap23Array, overLap23Arrayfilepath);
		ams.writeListToFile(overlap23Arrayminus4, overLap23Arrayminus4filepath);		
		ams.writeListToFile(overLap123, overLap123filepath);		
		ams.writeListToFile(overlap123Minus4, overLap123Minus4filepath);		
		ams.writeListToFile(overLap123Array, overLap123Arrayfilepath);
		ams.writeListToFile(overLap123ArrayMinusZp4,
				overLap123ArrayMinus4filepath);
	}

	private void writeListToFile(List<String> targetList, String filepath)
			throws IOException {
		File newfile = new File(filepath);
		if (!newfile.exists()) {
			newfile.createNewFile();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(newfile));
		for (int i = 0; i < targetList.size(); i++) {
			bw.write(targetList.get(i));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	/**
	 * 这个方法用作从文件中读取行然后将行中字符串元素放入一个字符串List
	 * 
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

	private List<String> getOverlap(List<String> zp1List, List<String> zp2List) {
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

	public List<String> minusList(List<String> minuendlist,
			List<String> subtractorlist) {
		List<String> resultList = new ArrayList<String>();
		for (int i = 0; i < minuendlist.size(); i++) {
			if (!subtractorlist.contains(minuendlist.get(i))) {
				resultList.add(minuendlist.get(i));
			}
		}
		return resultList;
	}

}
