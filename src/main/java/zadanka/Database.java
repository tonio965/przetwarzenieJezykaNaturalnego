package zadanka;

public class Database {
	String url;
	String login;
	String pw;
	
	public Database() {
		this.url = "jdbc:postgresql://localhost:5432/myDb";
		this.login= "postgres";
		this.pw="dupa12345";
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	

}
