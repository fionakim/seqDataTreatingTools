package fionaApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class SafeThread {

	public static final int BUFSIZE = 1024 * 32;

	public static void main(String[] args) throws IOException {
		String folderPath = "D:/PeakAnno/annoSum";
String sumFile = "D:/PeakAnno/annoSum/tdrZp1T.bed";
		File folder = new File(folderPath);
		File[] fileArray = folder.listFiles();
		FileChannel outChannel = null;
		FileOutputStream outputStream = new FileOutputStream(sumFile);
		outChannel = outputStream.getChannel();
		for (int i = 0; i < fileArray.length; i++) {
			if (fileArray[i].getAbsolutePath().contains(".bed")) {
				getTotalLines(fileArray[i]);
				FileInputStream stream = new FileInputStream(fileArray[i]);
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
		}
	if (outChannel != null) {
		outChannel.close();
	}
		outputStream.close();

	}

	private static void getTotalLines(File file) throws IOException {
		FileReader filer = new FileReader(file);
		LineNumberReader reader = new LineNumberReader(filer);
		String strLine = reader.readLine();
		int totalLines = 0;
		while (strLine != null) {
			totalLines++;
			strLine = reader.readLine();
		}
		reader.close();
		filer.close();
		System.out.println(file.getAbsolutePath() + " : line number = "
				+ totalLines);
	}

}
