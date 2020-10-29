package zadanka;

import java.sql.SQLException;
import java.util.Scanner;

public class Zad2Execute {
	
	public void execute() {
		
		Scanner intScanner = new Scanner(System.in);
		Scanner stringScanner = new Scanner(System.in);
		while(true) {
			System.out.println("choose excercise 1 2 3 ");
			int decision = intScanner.nextInt();
			
			switch(decision) {
			
				case 1:
					System.out.println("how many words you want to test?");
					int counter = intScanner.nextInt();
					StringBuilder combinedWords= new StringBuilder();
					for(int i=0;i<counter; i++) {
						System.out.println("provide a word: ");
						combinedWords.append(stringScanner.nextLine()).append(" & ");
					}
					combinedWords.setLength(combinedWords.length() - 3); //remove last space and |
					String changed = combinedWords.toString();
					System.out.println("string: "+changed);
					ps3zad123 run = new ps3zad123();
					try {
						run.executeExc1(changed);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				break;
				
				
				case 2:
					System.out.println("how many words you want to test?");
					int counter2 = intScanner.nextInt();
					StringBuilder combinedWords2= new StringBuilder();
					for(int i=0;i<counter2; i++) {
						System.out.println("provide a word: ");
						combinedWords2.append(stringScanner.nextLine()).append(" | ");
					}
					combinedWords2.setLength(combinedWords2.length() - 3); //remove last space and |
					String changed2= combinedWords2.toString();
					System.out.println("string: "+changed2);
					ps3zad123 run2 = new ps3zad123();
					try {
						run2.executeExc1(changed2);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				break;
				
				case 3:
					System.out.println("how many words you want to test?");
					int counter3 = intScanner.nextInt();
					StringBuilder combinedWords3= new StringBuilder();
					for(int i=0;i<counter3; i++) {
						System.out.println("provide a word: ");
						combinedWords3.append(stringScanner.nextLine()).append(" | ");
					}
					combinedWords3.setLength(combinedWords3.length() - 3); //remove last space and |
					String changed3= combinedWords3.toString();
					System.out.println("string: "+changed3);
					ps3zad123 run3 = new ps3zad123();
					try {
						run3.executeExc2(changed3);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				break;
				
			}
		}
		
	}

}
