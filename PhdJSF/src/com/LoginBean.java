package com;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "login", eager = true)
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String moveToHelpPage() {
		return "help";
	}

}