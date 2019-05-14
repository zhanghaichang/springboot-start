package com.dwring.springboot.model;

import java.util.List;
import lombok.Data;

@Data
public class UserGroup {

	UserInfo user;
	
	List<Group> groups;
}
