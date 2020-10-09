package zadanka;

import java.io.IOException;

public class Zad1Execute {
	
	public Zad1Execute() throws IOException {
	}

	public void execute() throws IOException {
		// TODO Auto-generated method stub
		ps2zad1 s = new ps2zad1();
		s.createDict();
		s.do20percentChanges();
		s.correctErrors();
		int diffs =s.countDiffs("/Users/tomaszkoltun/eclipse-workspace/0.0.1/slownik.txt", 
				"/Users/tomaszkoltun/eclipse-workspace/0.0.1/przyklad_poprawiony.txt");
		System.out.println("number of diffs: "+diffs);
	}

}
