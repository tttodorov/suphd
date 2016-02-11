package com;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "help", eager = true)
public class HelpBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String moveToLoginPage() {
		return "login";
	}

	public String moveToSelectRolePage() {
		return "selectRole";
	}

}