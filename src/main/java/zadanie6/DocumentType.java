package zadanie6;

import java.util.ArrayList;
import java.util.List;

public class DocumentType {
	
	List<SingleFile> loadedFiles;
	String type;
	
	public DocumentType() {
		loadedFiles = new ArrayList<>();
	}
	public DocumentType(List<SingleFile> loadedFiles) {
		super();
		this.loadedFiles = loadedFiles;
	}

	public List<SingleFile> getLoadedFiles() {
		return loadedFiles;
	}

	public void setLoadedFiles(List<SingleFile> loadedFiles) {
		this.loadedFiles = loadedFiles;
	}
	
	public void addFile(SingleFile sf) {
		loadedFiles.add(sf);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
