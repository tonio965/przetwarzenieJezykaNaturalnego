package zadanie5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import opennlp.tools.util.Sequence;

public class SentenceCounter {

	public static void main(String[] args) throws IOException {
		countSentences();
		countTokens();
		meanAmountOfTokensInSentence();
		countPartsOfSpeech();
	}
	
	//zad1
	public static void countSentences() throws FileNotFoundException {
		System.out.println("Zad1");
		InputStream modelIn = new FileInputStream("C:/Users/tonio/Documents/GitHub/przetwarzenieJezykaNaturalnego/src/main/resources/en-sent.bin");
		File f = new File("C:/Users/tonio/Documents/GitHub/przetwarzenieJezykaNaturalnego/src/main/resources/zad5.txt");
		StringBuilder sb = new StringBuilder();
		try {
			SentenceModel model = new SentenceModel(modelIn);
			SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
			Scanner sc = new Scanner(f);
		    while (sc.hasNextLine()) {
		    	String line = sc.nextLine();
		    	sb.append(line).append(" ");
		    }
		    String fromFile = sb.toString();
		    String sentences[] = sentenceDetector.sentDetect(fromFile);
		    System.out.println("amount of sentences:"+sentences.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//zad2
	
	public static void countTokens() throws FileNotFoundException {
		System.out.println("Zad2");
		File f = new File("C:/Users/tonio/Documents/GitHub/przetwarzenieJezykaNaturalnego/src/main/resources/zad5.txt");
		StringBuilder sb = new StringBuilder();
		try {
			Scanner sc = new Scanner(f);
		    while (sc.hasNextLine()) {
		    	String line = sc.nextLine();
		    	sb.append(line).append(" ");
		    }
		    String fromFile = sb.toString();
		    SimpleTokenizer tokenizer = new SimpleTokenizer();
		    String [] tokens = tokenizer.tokenize(fromFile);
		    System.out.println("amount of tokens: "+tokens.length);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//zad 3 
	public static void meanAmountOfTokensInSentence() throws FileNotFoundException {
		System.out.println("Zad3");
		InputStream modelIn = new FileInputStream("C:/Users/tonio/Documents/GitHub/przetwarzenieJezykaNaturalnego/src/main/resources/en-sent.bin");
		File f = new File("C:/Users/tonio/Documents/GitHub/przetwarzenieJezykaNaturalnego/src/main/resources/zad5.txt");
		StringBuilder sb = new StringBuilder();
		SimpleTokenizer tokenizer = new SimpleTokenizer();
		try {
			SentenceModel model = new SentenceModel(modelIn);
			SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
			Scanner sc = new Scanner(f);
		    while (sc.hasNextLine()) {
		    	String line = sc.nextLine();
		    	sb.append(line).append(" ");
		    }
		    String fromFile = sb.toString();
		    String sentences[] = sentenceDetector.sentDetect(fromFile);
		    int [] amountOfTokens = new int[sentences.length];
		    
		    for(int i=0; i<sentences.length;i++) {
		    	String [] tokens = tokenizer.tokenize(sentences[i]);
		    	amountOfTokens[i]=tokens.length;
		    }
		    int sum=0;
		    for(int i=0; i<sentences.length;i++) {
		    	sum+=amountOfTokens[i];
		    }
		    int mean = sum/amountOfTokens.length;
		    System.out.println("mean amount of tokens in a sentence: "+mean);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//zad4
	public static void countPartsOfSpeech() throws IOException {
		
		System.out.println("zad4 and zad5");
		InputStream modelIn = new FileInputStream("C:/Users/tonio/Documents/GitHub/przetwarzenieJezykaNaturalnego/src/main/resources/en-pos-maxent.bin");
		InputStream tokenmodelIn = new FileInputStream("C:/Users/tonio/Documents/GitHub/przetwarzenieJezykaNaturalnego/src/main/resources/en-sent.bin");
		POSModel model = new POSModel(modelIn);
		POSTaggerME tagger = new POSTaggerME(model);
		File f = new File("C:/Users/tonio/Documents/GitHub/przetwarzenieJezykaNaturalnego/src/main/resources/zad5.txt");
	    SimpleTokenizer tokenizer = new SimpleTokenizer();
		Scanner sc = new Scanner(f);
		
		
		StringBuilder sb = new StringBuilder();
	    while (sc.hasNextLine()) {
	    	String line = sc.nextLine();
	    	sb.append(line).append(" ");
	    }
	    String fromFile = sb.toString();
	    String [] tokens = tokenizer.tokenize(fromFile);
	    
	    //now tokens can be tagged
	    String tags[] = tagger.tag(tokens); //parts of speech to count
	    int nounsCounter =0;
	    int verbsCounter =0;
	    int adjectivesCounter =0;
	    int adverbsCounter =0;
	    List<String> nouns = new ArrayList<>();
	    List<String> nounsTags = new ArrayList<>();
	    int counter =0;
	    for(String tag: tags) {
	    	
	    	//a lot of tags checked because there are diff forms of part of speech acorging to docs:
	    	//https://dpdearing.com/posts/2011/12/opennlp-part-of-speech-pos-tags-penn-english-treebank/
	    	
	    	if(tag.equals("NN") || tag.equals("NNS") || tag.equals("NNP") || tag.equals("NNPS")) {
	    	
	    		nounsCounter++;
	    		nouns.add(tokens[counter]);
	    		nounsTags.add(tag);
	    	}
	    		
	    	
	    	
	    	if(tag.equals("VB") || tag.equals("VBD") || tag.equals("VBG") || tag.equals("VBN") || tag.equals("VBP") || tag.equals("VBZ"))
	    		verbsCounter++;
	    	
	    	if(tag.equals("JJ") || tag.equals("JJR") || tag.equals("JJS"))
	    		adjectivesCounter++;
	    	
	    	if(tag.equals("RB") || tag.equals("RBR") || tag.equals("RBS"))
	    		adverbsCounter++;
	    	
	    	counter++;
	    }
	    System.out.println("nouns: "+nounsCounter+" verbs: "+verbsCounter+" adjectives: "+adjectivesCounter+" adverbs: "+adverbsCounter);
	    
	    
//	    Sequence topSequences[] = tagger.topKSequences(tokens);
//	    for(Sequence q : topSequences) {
//	    	System.out.println(q);
//	    }
	    
	    
	    
	    ///zad5 
		InputStream is = new FileInputStream("C:/Users/tonio/Documents/GitHub/przetwarzenieJezykaNaturalnego/src/main/resources/en-lemmatizer.dict");
		DictionaryLemmatizer lemmatizer = new DictionaryLemmatizer(is);
		String [] nounsArray = new String[nouns.size()];
		String [] tagsArray = new String[nouns.size()];
		nounsArray = nouns.toArray(nounsArray);
		tagsArray = nounsTags.toArray(tagsArray);
		String[] lemmatized = lemmatizer.lemmatize(nounsArray, tagsArray);
		List asList = Arrays.asList(lemmatized);
		Set<String> mySet = new HashSet<String>(asList);
		//create list of items with occurencies
		List<FrequentItemToZad5> items = new ArrayList<>();
		for(String s: mySet){
			FrequentItemToZad5 item = new FrequentItemToZad5(Collections.frequency(asList,s), s);
			items.add(item);
		}
		items.sort(new ItemComparator());
		System.out.println("the most occuring nouns");
		for(int i=0; i<6; i++) {
			System.out.println(items.get(items.size()-i-1).getValue()+" "+items.get(items.size()-i-1).getOccurences() );
		}
		
		
		//zad6
		System.out.println("Zad6");
		for(int i=0; i<3; i++) {
			String noun = items.get(items.size()-i-1).getValue();
		    String [] tokenedWords = tokenizer.tokenize(fromFile);
		    String tagsoftokened[] = tagger.tag(tokenedWords); 
		    List<String> adjsForNoun = new ArrayList<>();
		    for(int j=0;j<tokenedWords.length; j++) {
		    	//if word matches the noun like a string and if its a noun - check if previous tokens are adjs if so add to list
		    	if(tokenedWords[j].contains(noun)) {
		    		
			    	if(tagsoftokened[j].equals("NN") || tagsoftokened[j].equals("NNS") || tagsoftokened[j].equals("NNP") || tagsoftokened[j].equals("NNPS")) {
				    	
			    		boolean isAdj=true;
			    		int index=j-1;
			    		while (isAdj) {
			    			if(tagsoftokened[index].equals("JJ") || tagsoftokened[index].equals("JJR") || tagsoftokened[index].equals("JJS")) {
			    				adjsForNoun.add(tokenedWords[index]);
			    				index-=1;
			    			}
			    			else {
			    				isAdj=false;
			    			}

			    		}

			    	}
		    		

		    	}
		    }
		    StringBuilder adjs = new StringBuilder();
		    for(String adj : adjsForNoun) {
		    	adjs.append(adj).append(" ");
		    }
		    System.out.println("noun: "+noun);
		    System.out.println("adjectives to it: "+adjs.toString());
		}
	}
	

	
	

}
