package com.company.retail.store.discounts.model;

import java.time.LocalDateTime;

import com.company.retail.store.discounts.enums.UserTypeEnum;

/*
 * User Class to store information about user that buy 
 */
public class User {

	private final UserTypeEnum type;
    private final String userName;
    private final LocalDateTime joiningDate;
    
    /*
     * Could add more fields like name, address etc etc.
     */

    public User(UserTypeEnum type, String userName) {
        this.type = type;
        this.userName = userName;
        joiningDate = LocalDateTime.now();
    }
    
    public User(UserTypeEnum type, String userName, LocalDateTime joiningDate) {
        this.type = type;
        this.userName = userName;
        this.joiningDate = joiningDate;
    }

	public UserTypeEnum getType() {
		return type;
	}

	public String getUserName() {
		return userName;
	}

	public LocalDateTime getJoiningDate() {
		return joiningDate;
	}

    
}
