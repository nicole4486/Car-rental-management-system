package entity;

import java.io.Serializable;

public class Staff implements Serializable{
	 private String username;
	    private String password;
	    private String name;

	    public Staff() {
	    }

	    public Staff(String username, String password, String name) {
	        this.username = username;
	        this.password = password;
	        this.name = name;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }
}
