package hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import org.bouncycastle.util.encoders.Hex;
import javafx.concurrent.Task;

public class HashFile extends Task<String> implements InterfaceHash {

	private File file;
	private String hashValue;
	private String hashAlgoritm;
	private final String fileNameForWritingHash = "FileHashes.txt";

	public File getFilePath() {
		return this.file;
	}

	public String getHashValue() {
		return this.hashValue;
	}

	public String getFileNameForWritingHash() {
		return this.fileNameForWritingHash;
	}

	public HashFile(File filePath, String hashAlgoritm) {
		this.file = filePath;
		this.hashAlgoritm = hashAlgoritm;
	}

	public String getHashAlgoritm() {
		return this.hashAlgoritm;
	}

	@Override
	public void computeHash() throws Exception {
		InputStream inputStream = new FileInputStream(this.getFilePath());
		MessageDigest digest = MessageDigest.getInstance(this.getHashAlgoritm());
		byte[] block = new byte[4096];
		int length;
		while ((length = inputStream.read(block)) > 0) {
			digest.update(block, 0, length);
		}
		this.hashValue = convertBytesToString(digest.digest());
		inputStream.close();
	}

	private String convertBytesToString(byte[] bytes) {
		return Hex.toHexString(bytes);
	}

	@Override
	public void writeHashToFile() throws IOException {
		File file = new File(this.getFileNameForWritingHash());
		FileWriter fileWriter = new FileWriter(file, true);
		String lineToWrite = this.getFilePath() + " " + this.getHashAlgoritm() + " " + this.getHashValue()
				+ System.lineSeparator();
		fileWriter.write(lineToWrite);
		fileWriter.close();
	}

	@Override
	public String toString() {
		return this.getHashValue();
	}

	@Override
	protected String call() throws Exception {
		this.computeHash();
		this.writeHashToFile();
		return this.toString();
	}
}