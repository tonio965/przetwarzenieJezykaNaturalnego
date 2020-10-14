package zadanka;

import java.io.IOException;
import java.util.List;

public class Zad1Execute {
	
	public Zad1Execute() throws IOException {
	}

	public void execute() throws IOException {
		// TODO Auto-generated method stub
		executePs2zad1();
//		executePs2zad2();
		
	}
	
	private void executePs2zad2() throws IOException {
		ps2zad2 zad2 = new ps2zad2();
		List<String> times = zad2.getWebsiteContent();
		for(String time : times) {
			System.out.println(time);
		}
		
	}

	private void executePs2zad1() throws IOException {
		ps2zad1 s = new ps2zad1();
		s.createDict();
		s.do20percentChanges();
		s.correctErrors();
		int diffs =s.countDiffs("/Users/tomaszkoltun/eclipse-workspace/0.0.1/slownik.txt", 
				"/Users/tomaszkoltun/eclipse-workspace/0.0.1/przyklad_poprawiony.txt");
		System.out.println("number of diffs: "+diffs);
	}

}
