package zadanka;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import com.google.common.collect.HashBiMap;

import opennlp.tools.tokenize.SimpleTokenizer;

public class ps2zad1 {
	
	Set<String> wordsSet; //im using set because it stores only distinct values
	String[] distinctWords;
	String[] changedWords;
	
	public ps2zad1() {
		wordsSet = new HashSet<String>();
		
	}
	
	
	public void createDict() throws IOException {
		File f = new File("/Users/tomaszkoltun/eclipse-workspace/0.0.1/src/main/resources/przyklad.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter("slownik.txt"));
		StringBuilder sb = new StringBuilder();
		@SuppressWarnings("deprecation")
		SimpleTokenizer tokenizer = new SimpleTokenizer();
		try {
			Scanner sc = new Scanner(f);
		    while (sc.hasNextLine()) {
		    	String line = sc.nextLine();
		    	sb.append(line).append(" ");
		    }
		    sc.close();
		    String s = sb.toString();
		    String [] tokenized = tokenizer.tokenize(s); //selects words to array using this weird library
		    for(String word : tokenized) {
		    	String myword = word.replaceAll("[^a-zA-Z]", "").toLowerCase(); //im deleting non letters characters and change to lowercase
		    	if(myword.length()>0) {
		    		wordsSet.add(myword); //add it to my set
		    	}
		    	
		    }
		    distinctWords = new String[wordsSet.size()];
		    wordsSet.toArray(distinctWords);
		    System.out.println("separated words");
		    for(String distinctWord: distinctWords) {
		    	writer.write(distinctWord); //write distinct words to file
		    	writer.write("\n");
		    	System.out.println(distinctWord);
		    }
		    writer.close();
		    
		} catch (FileNotFoundException e) {
			System.out.println("wysypalo sie przy wycinaniu slow");
			e.printStackTrace();
		}
		
	}
	

	public void do20percentChanges() throws IOException {
		changedWords = distinctWords;
		int size = distinctWords.length;
		int amountOfWordsToChange = (int) (size*0.2);
		List<Integer> selectedNumbers = new ArrayList<>();
		int commitedChanges = 0;
		
		while(commitedChanges < amountOfWordsToChange) {
			boolean alreadyChanged = false;
			int selectedWord = generateRandomNumber(size); //select a word index
			for(int number : selectedNumbers) { //check if was already changed 
				if(number==selectedWord) {  //if so mark it as already changed and skip an iteration
					alreadyChanged=true;
					break;
				}
			}
			if(alreadyChanged)
				continue; //if was already changed i skip current iteration
			
			//generate how to change the word
			selectedNumbers.add(selectedWord);
			String toChange = changedWords[selectedWord]; //word to change
			int howManyChanges = generateRandomNumber(3) + 1; //how many changes in the word
			for(int x=0; x<howManyChanges; x++) {
				
				int selectedChange = generateRandomNumber(3); //which type of change to do
				switch(selectedChange) {

				case 0: //delete a radom letter
					toChange=deleteAletter(toChange);
					break;
				case 1: //add a letter
					toChange=addAletter(toChange);
					break;
				case 2: //change a letter
					toChange=changeAletter(toChange);
					break;
			
				}
				
			}
			changedWords[selectedWord]= toChange; //assign changed word to its place in the array
			commitedChanges++;
		}
		saveChangedWords(changedWords, "przyklad_z_bledami.txt");
		
	}
	
	private void saveChangedWords(String[] changedWords2, String path) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(path));
		System.out.println("changed words");
	    for(String distinctWord: changedWords2) {
	    	writer.write(distinctWord); //write distinct words to file
	    	writer.write("\n");
	    	System.out.println(distinctWord);
	    }
	    writer.close();
	}
	
	public void correctErrors() throws IOException {
		List<String> dict= readFromFile("/Users/tomaszkoltun/eclipse-workspace/0.0.1/slownik.txt");
		List<String> errors= readFromFile("/Users/tomaszkoltun/eclipse-workspace/0.0.1/przyklad_z_bledami.txt");
		List<String> correctedErrors = new ArrayList<>();
		//search for the word in errors list which is not present in dict list
		boolean exists = false;
		for(int i = 0 ; i< errors.size()-1; i++) {
			List<Integer> distances = new ArrayList<>(); //distances list to change to word from dict on a certain index
			for(int j = 0; j< dict.size()-1; j++) {
				distances.add(levenshteinDistance(errors.get(i), dict.get(j))); //add distance to list
			}
			//traverse the distances list to find the smallest one and assign the closest one as the good soluton
			int smallestDistance=Integer.MAX_VALUE;
			int indexOfSmallestDistance=Integer.MAX_VALUE;
			for(int x =0 ; x<distances.size()-1 ; x++) {
				if(distances.get(x)<smallestDistance) {
					smallestDistance=distances.get(x);
					indexOfSmallestDistance=x; //smallest distance index found
				}
			}
			correctedErrors.add(dict.get(indexOfSmallestDistance));		
		}
		String[] CorrectedArray = correctedErrors.toArray(new String[0]);
		saveChangedWords(CorrectedArray, "przyklad_poprawiony.txt");
	}
	
	public int countDiffs(String path1, String path2) {
		List<String> dict= readFromFile(path1);
		List<String> correctedFromErrors= readFromFile(path2);
		int diffs=0;
		for(int i=0; i<dict.size()-1; i++) {
			if(!dict.get(i).equals(correctedFromErrors.get(i)))
				diffs++;
		}
		return diffs;
	}
	
	
	
	List<String> readFromFile(String path) {
		File f = new File(path);
		List<String> words = new ArrayList<>();
		try {
			Scanner sc = new Scanner(f);
		    while (sc.hasNextLine()) {
		    	String line = sc.nextLine();
		    	words.add(line);
		    }
		    sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return words;
	}
	
	public int levenshteinDistance (CharSequence lhs, CharSequence rhs) {                          
	    int len0 = lhs.length() + 1;                                                     
	    int len1 = rhs.length() + 1;                                                     
	                                                                                    
	    // the array of distances                                                       
	    int[] cost = new int[len0];                                                     
	    int[] newcost = new int[len0];                                                  
	                                                                                    
	    // initial cost of skipping prefix in String s0                                 
	    for (int i = 0; i < len0; i++) cost[i] = i;                                     
	                                                                                    
	    // dynamically computing the array of distances                                  
	                                                                                    
	    // transformation cost for each letter in s1                                    
	    for (int j = 1; j < len1; j++) {                                                
	        // initial cost of skipping prefix in String s1                             
	        newcost[0] = j;                                                             
	                                                                                    
	        // transformation cost for each letter in s0                                
	        for(int i = 1; i < len0; i++) {                                             
	            // matching current letters in both strings                             
	            int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;             
	                                                                                    
	            // computing cost for each transformation                               
	            int cost_replace = cost[i - 1] + match;                                 
	            int cost_insert  = cost[i] + 1;                                         
	            int cost_delete  = newcost[i - 1] + 1;                                  
	                                                                                    
	            // keep minimum cost                                                    
	            newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
	        }                                                                           
	                                                                                    
	        // swap cost/newcost arrays                                                 
	        int[] swap = cost; cost = newcost; newcost = swap;                          
	    }                                                                               
	                                                                                    
	    // the distance is the cost for transforming all letters in both strings        
	    return cost[len0 - 1];                                                          
	}


	private String changeAletter(String toChange) { //change a random letter in a string
		String alphabet = "qwertyuiopasdfghjklzxcvbnm";
		int letterToChange = generateRandomNumber(toChange.length());
		int randomLetter = generateRandomNumber(26); //from alphabet
		while(toChange.charAt(letterToChange)==alphabet.charAt(randomLetter)) { //to be sure it wont change to the same letter eg b to b
			randomLetter = generateRandomNumber(26);
		}
		StringBuilder sb = new StringBuilder(toChange);
		sb.setCharAt(letterToChange, alphabet.charAt(randomLetter));
		
		return sb.toString();
	}


	private String addAletter(String toChange) {
		String alphabet = "qwertyuiopasdfghjklzxcvbnm";
		int indexToAdd = generateRandomNumber(toChange.length());
		int randomLetter = generateRandomNumber(26);
		StringBuilder sb = new StringBuilder(toChange);
		sb.insert(indexToAdd, alphabet.charAt(randomLetter));
		return sb.toString();
	}


	private String deleteAletter(String toChange) {
		StringBuilder sb = new StringBuilder(toChange);
		System.out.println("len:"+toChange.length());
		if(toChange.length()>0) {
			int indexToDelete = generateRandomNumber(toChange.length());
			sb.deleteCharAt(indexToDelete);
		}
		return sb.toString();
	}


	private int generateRandomNumber(int upperbound) {
		Random rand = new Random();
		int number = rand.nextInt(upperbound);
		if(number<0)
			number =number*(-1);
//		System.out.println("returned number "+ number);
	    return number;
	}
}
