package zadanie6;

import java.util.ArrayList;
import java.util.List;

public class SingleFile {
	
	private List<String> loadedWords;
	private List<String> tokens;
	private List<String> tags;
	private List<String> lemmatized;
	
	public SingleFile(List<String> loadedWords, List<String> tokens) {
		super();
		this.loadedWords = loadedWords;
		this.tokens = tokens;
	}

	public SingleFile() {
		super();
		loadedWords= new ArrayList<>();
		tokens= new ArrayList<>();
		tags= new ArrayList<>();
		lemmatized= new ArrayList<>();
	}
	
	public void addLemma(String lemma) {
		lemmatized.add(lemma);
	}
	
	public List<String> getLemmatized() {
		return lemmatized;
	}

	public void setLemmatized(List<String> lemmatized) {
		this.lemmatized = lemmatized;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	public void addTag(String tag) {
		tags.add(tag);
	}

	public void loadWord(String s) {
		loadedWords.add(s);
	}

	public List<String> getLoadedWords() {
		return loadedWords;
	}

	public void setLoadedWords(List<String> loadedWords) {
		this.loadedWords = loadedWords;
	}

	public List<String> getTokens() {
		return tokens;
	}

	public void setTokens(List<String> tokens) {
		this.tokens = tokens;
	}
	
	
	
	

}
