package zadanka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ps3zad123 {
	
	public void executeExc1(String combinedWords) throws SQLException {
		
		  Database db = new Database();
		  Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		  PreparedStatement ps = conn.prepareStatement("SELECT paper_title, abstract, ts_rank(to_tsvector('mycfg', abstract), query) FROM article, to_tsquery('mycfg','"+combinedWords+"') query WHERE to_tsvector('mycfg', abstract) @@ query ORDER BY ts_rank DESC LIMIT 10;");
	      ResultSet rs=ps.executeQuery();  
	      while(rs.next()){
	    	 System.out.println("title: "+ rs.getString(1));

	    	 System.out.println("abstract: "+rs.getString(2));

	    	 System.out.println("rank: "+rs.getDouble(3));
	    	 System.out.println("////////////////////////");
	    	 System.out.println("    ");
	      }  
	      ps.close();

	}
	
	public void executeExc2(String combinedWords) throws SQLException {
		
		  Database db = new Database();
		  Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		  PreparedStatement ps = conn.prepareStatement("SELECT paper_title, keywords, abstract, session, (ts_rank(to_tsvector('mycfg', abstract), query) +"
					+ "ts_rank(to_tsvector('mycfg', paper_title), query) + ts_rank(to_tsvector('mycfg', keywords), query) +"
					+ "ts_rank(to_tsvector('mycfg', session), query)) AS rank FROM article, to_tsquery('mycfg','"+combinedWords+"') "
					+ "query WHERE to_tsvector('mycfg', paper_title) @@ query OR to_tsvector('mycfg', keywords) @@ query "
					+ "OR to_tsvector('mycfg', abstract) @@ query OR to_tsvector('mycfg', session) @@ query ORDER BY rank DESC LIMIT 10;");
	      ResultSet rs=ps.executeQuery();  
	      while(rs.next()){
	    	 System.out.println("title: "+ rs.getString(1));

	    	 System.out.println("abstract: "+rs.getString(2));

	    	 System.out.println("rank: "+rs.getString(3));
	    	 System.out.println("////////////////////////");
	    	 System.out.println("    ");
	      }  
	      ps.close();

	}

}


