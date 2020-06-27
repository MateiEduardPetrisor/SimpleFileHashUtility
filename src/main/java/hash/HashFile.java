package hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.concurrent.Task;

public class HashFile extends Task<String> {

	private File file;
	private String hashValue;
	private String hashAlgoritm;
	private static Logger log = LoggerFactory.getLogger(HashFile.class);

	public File getFilePath() {
		return this.file;
	}

	public String getHashValue() {
		return this.hashValue;
	}

	public HashFile(File filePath, String hashAlgoritm) {
		this.file = filePath;
		this.hashAlgoritm = hashAlgoritm;
	}

	public String getHashAlgoritm() {
		return this.hashAlgoritm;
	}

	public void computeHash() throws NoSuchAlgorithmException, IOException {
		long start = System.currentTimeMillis();
		InputStream inputStream = new FileInputStream(this.getFilePath());
		MessageDigest digest = MessageDigest.getInstance(this.getHashAlgoritm());
		byte[] block = new byte[4096];
		int length = 0;
		while ((length = inputStream.read(block)) > 0) {
			digest.update(block, 0, length);
		}
		this.hashValue = convertBytesToString(digest.digest());
		long end = System.currentTimeMillis() - start;
		log.info(this.file.getAbsolutePath() + " " + this.hashAlgoritm + " = " + this.hashValue
				+ " Time Spent On Hashing = " + TimeUnit.MILLISECONDS.toSeconds(end));
		inputStream.close();
	}

	private String convertBytesToString(byte[] bytes) {
		return Hex.toHexString(bytes);
	}

	@Override
	public String toString() {
		return this.getHashValue();
	}

	@Override
	protected String call() {
		try {
			this.computeHash();
		} catch (NoSuchAlgorithmException | IOException e) {
			log.info(e.getMessage());
		}
		return this.toString();
	}
}