package fionaApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MakingTogether {

	public static void main(String[] args) throws IOException {

		MakingTogether obj = new MakingTogether();		
		String folderPath= "D:/PeakAnno/annoSum/sum/tdrZp1";
		File folder = new File(folderPath);
		File[] fileArray = folder.listFiles();
		for (int i = 0; i < fileArray.length; i++) {
			if (fileArray[i].getAbsolutePath().contains("Anno.bed")) {
								
				String sourceFile = fileArray[i].getAbsolutePath();
				String newSourceFile = sourceFile.replace(".bed", "M.bed");
				
				if (sourceFile.contains("Macs14")) {
					obj.sourceFileModify14(sourceFile, newSourceFile);
				}else if (sourceFile.contains("Macs2")) {
					obj.sourceFileModify2(sourceFile, newSourceFile);
				}else if (sourceFile.contains("Sissrs") && sourceFile.contains("NoCtrl")) {
					obj.sourceFileModifySissrsNoCtrl(sourceFile, newSourceFile);
				}else {
					obj.sourceFileModifySissrs(sourceFile, newSourceFile);					
				}				
			}
		}
	}

private void sourceFileModifySissrsNoCtrl(String sourceFile,
			String newSourceFile) throws IOException {

	File sourceBedFile = new File(sourceFile);
	
	String sampleID = getSampleID(sourceFile);
	String tri_untri = ensureTri(sourceFile);
	String withctrl = ensureWithCtrl(sourceFile);
	String pValueThreshold = getpValueThreshold(sourceFile);
	String tools = getTools(sourceFile);

	FileReader source = new FileReader(sourceBedFile);
	BufferedReader sourceBr = new BufferedReader(source);
	File newfile = new File(newSourceFile);

	BufferedWriter bw = new BufferedWriter(new FileWriter(newfile));

	for (String line = sourceBr.readLine(); line != null; line = sourceBr
			.readLine()) {
		String[] array = line.split("[\t]");
		int peaklength;
		if (array[1].trim().isEmpty() || array[2].trim().isEmpty()) {
			peaklength=0;
		}else {
			peaklength = Integer.parseInt(array[2].trim())-Integer.parseInt(array[1].trim()) + 1;
		}
		 
		String newlineString = sampleID + "\t" + tri_untri + "\t"
				+ withctrl + "\t" + pValueThreshold + "\t" + tools + "\t"
				+ array[0] + "\t" + array[1] + "\t" + array[2] + "\t"
				+ peaklength + "\t" +"null" + "\t" + "null"+ "\t"
				+ "null" + "\t" + array[4] + "\t" + array[5] + "\t"
				+ array[6] + "\t" + array[7];
		bw.write(newlineString);
		bw.newLine();
	}
	bw.close();
	sourceBr.close();
//	String xlsResultFilePath = newSourceFile.replace("bed", "xls");
//	File xlsResultFile = new File(xlsResultFilePath);
//	newfile.renameTo(xlsResultFile);
		
	}

//	private void addContentToSumFile(String newSourceFile,
//			String sumFilePath) throws IOException {
//		BufferedReader contentFileBr = new BufferedReader(new FileReader(newSourceFile));
//		BufferedWriter sumFilebw = new BufferedWriter(new FileWriter(sumFilePath));
//		for (String line = contentFileBr.readLine(); line != null; line =contentFileBr
//				.readLine()) {			
//			sumFilebw.write(line);
//			sumFilebw.newLine();
//		}
//		sumFilebw.close();
//		contentFileBr.close();
//		
//	}

	private void sourceFileModifySissrs(String sourceFile, String newSourceFile) throws IOException {
	
		File sourceBedFile = new File(sourceFile);		

		String sampleID = getSampleID(sourceFile);
		String tri_untri = ensureTri(sourceFile);
		String withctrl = ensureWithCtrl(sourceFile);
		String pValueThreshold = getpValueThreshold(sourceFile);
		String tools = getTools(sourceFile);

		FileReader source = new FileReader(sourceBedFile);
		BufferedReader sourceBr = new BufferedReader(source);
		File newfile = new File(newSourceFile);

		BufferedWriter bw = new BufferedWriter(new FileWriter(newfile));

		for (String line = sourceBr.readLine(); line != null; line = sourceBr
				.readLine()) {
			String[] array = line.split("[\t]");
			int peaklength = Integer.parseInt(array[2])
					- Integer.parseInt(array[1]) + 1;
			String newlineString = sampleID + "\t" + tri_untri + "\t"
					+ withctrl + "\t" + pValueThreshold + "\t" + tools + "\t"
					+ array[0] + "\t" + array[1] + "\t" + array[2] + "\t"
					+ peaklength + "\t" +getlog(array[5]) + "\t" + array[4] + "\t"
					+ "null" + "\t" + array[6] + "\t" + array[7] + "\t"
					+ array[8] + "\t" + array[9];
			bw.write(newlineString);
			bw.newLine();
		}
		bw.close();
		sourceBr.close();
//		String xlsResultFilePath = newSourceFile.replace("bed", "xls");
//		File xlsResultFile = new File(xlsResultFilePath);
//		newfile.renameTo(xlsResultFile);
		
	}

	private String getlog(String str) {
		String logPvalue =null;
		double pValue = -Math.log10(Double.parseDouble(str));
		logPvalue = Double.toString(pValue);		
		return logPvalue;
	}

	private void sourceFileModify2(String sourceFile, String newSourceFile) throws IOException {
		
		File sourceBedFile = new File(sourceFile);		

		String sampleID = getSampleID(sourceFile);
		String tri_untri = ensureTri(sourceFile);
		String withctrl = ensureWithCtrl(sourceFile);
		String pValueThreshold = getpValueThreshold(sourceFile);
		String tools = getTools(sourceFile);

		FileReader source = new FileReader(sourceBedFile);
		BufferedReader sourceBr = new BufferedReader(source);
		File newfile = new File(newSourceFile);

		BufferedWriter bw = new BufferedWriter(new FileWriter(newfile));

		for (String line = sourceBr.readLine(); line != null; line = sourceBr
				.readLine()) {
			String[] array = line.split("[\t]");		
			String newlineString = sampleID + "\t" + tri_untri + "\t"
					+ withctrl + "\t" + pValueThreshold + "\t" + tools + "\t"
					+ array[0] + "\t" + array[1] + "\t" + array[2] + "\t"
					+ array[3] + "\t" + array[6] + "\t" + array[7] + "\t"
					+ array[8] + "\t" + array[10] + "\t" + array[11] + "\t"
					+ array[12] + "\t" + array[13];
			bw.write(newlineString);
			bw.newLine();
		}
		bw.close();
		sourceBr.close();
//		String xlsResultFilePath = newSourceFile.replace("bed", "xls");
//		File xlsResultFile = new File(xlsResultFilePath);
//		newfile.renameTo(xlsResultFile);		
		
	}

	/**
	 * 用来修整macs14的结果文件
	 * 
	 * @param sourceFile
	 * @param newSourceFile
	 * @throws IOException
	 */
	private void sourceFileModify14(String sourceFile, String newSourceFile)
			throws IOException {
		
		File sourceBedFile = new File(sourceFile);
		String sampleID = getSampleID(sourceFile);
		String tri_untri = ensureTri(sourceFile);
		String withctrl = ensureWithCtrl(sourceFile);
		String pValueThreshold = getpValueThreshold(sourceFile);
		String tools = getTools(sourceFile);

		FileReader source = new FileReader(sourceBedFile);
		BufferedReader sourceBr = new BufferedReader(source);
		File newfile = new File(newSourceFile);

		BufferedWriter bw = new BufferedWriter(new FileWriter(newfile));

		for (String line = sourceBr.readLine(); line != null; line = sourceBr
				.readLine()) {
			String[] array = line.split("[\t]");
			int peaklength = Integer.parseInt(array[2])
					- Integer.parseInt(array[1]) + 1;
			String newlineString = sampleID + "\t" + tri_untri + "\t"
					+ withctrl + "\t" + pValueThreshold + "\t" + tools + "\t"
					+ array[0] + "\t" + array[1] + "\t" + array[2] + "\t"
					+ peaklength + "\t" + array[4] + "\t" + "null" + "\t"
					+ "null" + "\t" + array[5] + "\t" + array[6] + "\t"
					+ array[7] + "\t" + array[8];
			bw.write(newlineString);
			bw.newLine();
		}
		bw.close();
		sourceBr.close();
	}

	private String getTools(String sourceFile) {
		String tools = null;
		if (sourceFile.contains("Macs14")) {
			tools = "macs14";
		} else if (sourceFile.contains("Macs2")) {
			tools = "macs2";
		} else {
			tools = "sissrs";
		}
		return tools;
	}

	private String getpValueThreshold(String sourceFile) {
		String pVal = null;
		if (sourceFile.contains("05")) {
			pVal = "0.05";
		} else if (sourceFile.contains("01")) {
			pVal = "0.01";
		} else {
			pVal = "null";
		}
		return pVal;
	}

	private String ensureWithCtrl(String sourceFile) {
		Boolean withCtrl = false;
		if (sourceFile.contains("With")) {
			withCtrl = true;
		}
		return Boolean.toString(withCtrl);
	}

	private String ensureTri(String sourceFile) {
		Boolean tri = false;
		if (sourceFile.contains("Tri")) {
			tri = true;
		}
		return Boolean.toString(tri);
	}

	private String getSampleID(String sourceFile) {
		String[] sampleList = { "amsZp1", "amsZp2", "amsZp3", "amsZp4",
				"tdrZp1", "tdrZp2", "tdrZp3", "tdrZp4" };
		int id = 0;
		for (int j = 0; j < sampleList.length; j++) {
			if (sourceFile.contains(sampleList[j])) {
				id = j + 1;
			}
		}
		return Integer.toString(id);
	}
}
