package zadanie6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;

public class CreateVectors {

	List<String> stopWords;
	List<DocumentType> documentTypes;
	List<SingleFile> loadedFiles;
	
	public CreateVectors() throws IOException {
		stopWords = new ArrayList<>();
		loadedFiles = new ArrayList<>();
		documentTypes = new ArrayList<>();
		loadStopWords();
		removeStopWords();
		lemmatize();
	}
	
	private void lemmatize() throws IOException {
		InputStream is;
		DictionaryLemmatizer lemmatizer;
		InputStream modelIn = new FileInputStream("C:\\Users\\tonio\\eclipse-workspace\\testowy-workspace\\przetwarzenieJezykaNaturalnego\\src\\main\\resources\\en-pos-maxent.bin");
		POSModel model = new POSModel(modelIn);
		POSTaggerME tagger = new POSTaggerME(model);
		
		
		try {
			is = new FileInputStream("C:\\Users\\tonio\\eclipse-workspace\\testowy-workspace\\przetwarzenieJezykaNaturalnego\\src\\main\\resources\\en-lemmatizer.dict");
			lemmatizer = new DictionaryLemmatizer(is);
			
			for(DocumentType dt : documentTypes) {
				
				for(int i=0; i< dt.getLoadedFiles().size(); i++) {
					List<String> words = dt.getLoadedFiles().get(i).getLoadedWords();
					String [] wordsArray = new String[words.size()];
					wordsArray = words.toArray(wordsArray);
					String tags[] = tagger.tag(wordsArray);
					String[] lemmatized = lemmatizer.lemmatize(wordsArray, tags);
					int ind =0;
					for(String tag: tags) {
						dt.getLoadedFiles().get(i).addTag(tag);
						dt.getLoadedFiles().get(i).addLemma(lemmatized[ind]);
						ind++;
					}
					
				}
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listFilesForFolder(final File folder, int index) {
		DocumentType dt = new DocumentType();
		int dtIndex = 0;
		File [] files = folder.listFiles();
		System.out.println("document type: "+dtIndex);
		int fileId = 0;
	    for (int i=0; i< files.length; i++) {
	    	File f = files[i];
	    	dt.addFile(wordToObject(f));
	    	System.out.println("file id: "+fileId);
	    	fileId++;
	    }
	    dtIndex++;
	    documentTypes.add(dt);
	}
	
	private SingleFile wordToObject(File f) {
		
		SingleFile lf = new SingleFile();
		Scanner sc = null;
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    while (sc.hasNextLine()) {
	    	String s = sc.nextLine();
	    	SimpleTokenizer tokenizer = new SimpleTokenizer();
	    	String [] tokens = tokenizer.tokenize(s);
	    	
	    	for(String token : tokens) {
	    		
		    	if(!checkIfStopWord(token))
		    		lf.loadWord(token);
	    	}

        }
	    return lf;
		
	}
	
	private boolean checkIfStopWord(String s) {
		
		for(String stopWord : stopWords) {
			if(s.toLowerCase().equals(stopWord))
				return true;
		}
		return false;
	}
	

	private void removeStopWords() {
		List<File> folders = new ArrayList<>();
		folders.add(new File("C:\\Users\\tonio\\eclipse-workspace\\testowy-workspace\\przetwarzenieJezykaNaturalnego\\src\\main\\resources\\bbc\\business_train"));
		folders.add(new File("C:\\Users\\tonio\\eclipse-workspace\\testowy-workspace\\przetwarzenieJezykaNaturalnego\\src\\main\\resources\\bbc\\entertain_train"));
		folders.add(new File("C:\\Users\\tonio\\eclipse-workspace\\testowy-workspace\\przetwarzenieJezykaNaturalnego\\src\\main\\resources\\bbc\\politics_train"));
		folders.add(new File("C:\\Users\\tonio\\eclipse-workspace\\testowy-workspace\\przetwarzenieJezykaNaturalnego\\src\\main\\resources\\bbc\\sport_train"));
		folders.add(new File("C:\\Users\\tonio\\eclipse-workspace\\testowy-workspace\\przetwarzenieJezykaNaturalnego\\src\\main\\resources\\bbc\\tech_train"));
		
		int index =0;
		for(File folder : folders) {
			listFilesForFolder(folder, index);
			index++;
		}
		
	}
	
	
	private void loadStopWords() {
		Scanner sc = null;
		try {
			sc = new Scanner(new File("C:\\Users\\tonio\\eclipse-workspace\\testowy-workspace\\przetwarzenieJezykaNaturalnego\\src\\main\\resources\\stopWords.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    while (sc.hasNextLine()) {
	    	String s = sc.nextLine();
	    	stopWords.add(s);
        }
    }
		
		
	
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
