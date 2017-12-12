package hash;

import java.io.IOException;

public interface InterfaceHash {
	public void computeHash() throws Exception;

	public void writeHashToFile() throws IOException;
}