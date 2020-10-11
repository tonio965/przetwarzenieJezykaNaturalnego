package zadanka;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ps2zad2 {
	private URL url;
	private Scanner sc;
	private String websiteContent;
	private String regex;
	private List<String> times;
	
	public ps2zad2() throws IOException {
		url= new URL("https://bieganie.pl/?show=1&cat=11&id=10910");
		sc = new Scanner(url.openStream());
		websiteContent = new String();
		regex = "^.*([0-9]:[0-9][0-9]:[0-9][0-9]).*";
		times = new ArrayList<>();
	}
	
	public List<String> getWebsiteContent() {
	    StringBuffer sb = new StringBuffer();
	    Pattern p = Pattern.compile(regex);
	    while(sc.hasNext()) {
	       String next = sc.next(); 
	       Matcher m = p.matcher(next);  
	       boolean b = m.matches();
	       if(b) {
	    	   times.add(next.replaceAll("[^:0-9]", ""));
	       }

	    }
	    return times;
	}

}
