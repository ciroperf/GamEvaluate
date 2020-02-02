package gamevaluate.bean;

public class GeneralUser {

	private String email;
	private String username;
	private String password;
	private int role;
	private boolean banned;

	public GeneralUser(String email, String username, String password, int role, boolean banned) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
		this.banned = banned;
	}
	
	public GeneralUser() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (o == null)
			return false;
		if (o.getClass() != this.getClass())
			return false;
		
		GeneralUser g = (GeneralUser)o;
		
		return g.getUsername().equals(this.username);
		
	}

	@Override
	public String toString() {
		String s =  "GeneralUser [email=" + email + ", username=" + username + ", password=" + password + ", role=";
	

			
			if (role == 2)
				s.concat("utente");
			if (role == 3)
				s.concat("moderatore");
			if (role == 4)
				s.concat("amministratore");
			
			if (role != 4)
				s.concat(", banned=" + banned + "]");
			else
				s.concat("]");
				
			return s;

	}

}
