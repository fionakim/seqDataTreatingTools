package fionaApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PeakAnno {
		
	public static void main(String[] args) throws IOException {		
		PeakAnno obj = new PeakAnno();
		String bedFile = "D:/PeakAnno/macs2/untri/noCtrlp0.01/amsZp2Macs2NoCtrl_p01_peaks.bed";		
		String gff3File="D:/PeakAnno/TAIR10_GFF3.gff";		
		String peakAnnoFile ="D:/PeakAnno/macs2/untri/noCtrlp0.01/amsZp2Macs2NoCtrl_p01_peaksAnnoJava.bed";
		obj.getPeakAnnoFile(bedFile,gff3File,peakAnnoFile);	
		System.out.println("Now you got the annotation file at: " + peakAnnoFile);
	}

	private void getPeakAnnoFile(String bedFile, String gff3File,
			String peakAnnoFile) throws IOException {
	     
		FileReader bedFilefr = new FileReader(bedFile);
		BufferedReader bedFilebr = new BufferedReader(bedFilefr);
		
		
		FileWriter fWriter = new FileWriter(peakAnnoFile);
		BufferedWriter fwb= new BufferedWriter(fWriter);
		String head ="chr\tpeak start\tpeak end\tpeak length\tpeak name\tabs summit position\tabs summit pileup height\t-log10(Pvalue) of summit\tfeature attributes\tfeature type\tfeature start\tfeature end" ;
		fwb.write(head);
		fwb.newLine();
		
		String bedFileLine = null;
		int lineNo=0;
		int annoLineNo=0;
		
		while ((bedFileLine=bedFilebr.readLine()) !=null) {
			lineNo ++;
			System.out.println("Now it's the " + lineNo +"th peak's turn, we are rewriting its item info!");
			String[] bedFileLineString = bedFileLine.split("\t");			
			int peakStart = Integer.parseInt(bedFileLineString[1]);
			int peakEnd = Integer.parseInt(bedFileLineString[2]);
			ArrayList<Integer>peakRegion = getIntervalList(peakStart,peakEnd);
			
			
			FileReader gff3Filefr = new FileReader(gff3File);
			BufferedReader gff3Filebr= new BufferedReader(gff3Filefr);
			String gff3Line = null;
			int gfflineNo =0;
			while ((gff3Line=gff3Filebr.readLine()) != null) {
				gfflineNo ++;
				String[] gff3LineString = gff3Line.split("\t");
//				System.out.println("we now start to compare the "+gfflineNo+"th feature!");
				int featureStart=Integer.parseInt(gff3LineString[3]);
				int featureEnd = Integer.parseInt(gff3LineString[4]);
				ArrayList<Integer> featureRegion = getIntervalList(featureStart, featureEnd);
				String resultItem = null;
				
				if ((gff3LineString[0].contains(bedFileLineString[0])) && (OverlapList(peakRegion,featureRegion))) {
					System.out.println("we find the item matched the " + lineNo+"th peak item");
					resultItem = bedFileLineString[0] + "\t" + bedFileLineString[1] + "\t" + bedFileLineString[2] + "\t" + bedFileLineString[3] +"\t"+ bedFileLineString[9] + "\t"
				+ bedFileLineString[4] + "\t" + bedFileLineString[5] + "\t" + bedFileLineString[6] + "\t" +gff3LineString[8] + "\t" +gff3LineString[2] +"\t" + gff3LineString[3] +"\t" +gff3LineString[4];
					fwb.write(resultItem);
					fwb.newLine();
					annoLineNo++;
				}
			}
			gff3Filebr.close();			
		}	
		System.out.println("we successfully annotate " +annoLineNo +"peaks againt total "  +lineNo + "peaks!" );
		fwb.close();
		bedFilebr.close();
	}

	private boolean OverlapList(ArrayList<Integer> peakRegion,
			ArrayList<Integer> featureRegion) {
		boolean b = false;
		for (int i = 0; i < peakRegion.size(); i++) {
			if (featureRegion.contains(peakRegion.get(i))) {
				b = true;
				break;
			}else {
				continue;
			}
		}
		return b;
	}



	private  ArrayList<Integer> getIntervalList(int start, int end) {
		ArrayList<Integer> IntervalList = new ArrayList<Integer>();
		for (int i = start; i < end+1; i++) {
			IntervalList.add(i);
		}
		return IntervalList;
	}
}
