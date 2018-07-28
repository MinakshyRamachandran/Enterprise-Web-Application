import java.io.*;

public class User implements Serializable{

	String firstName;
	String lastName;
	String userId;
	String password;
	String userType;
	String repassword;

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserType() {
		return userType;
	}

	public void setrePassword(String repassword) {
		this.repassword = repassword;
	}

	public String getrePassword() {
		return repassword;
	}


}
