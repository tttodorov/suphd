package com.user;

import java.io.IOException;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.LogBean;
import com.facade.UserFacade;
import com.model.User;

@SessionScoped
@ManagedBean(name = "auth", eager = true)
public class AuthBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	private User user;

	@EJB
	private UserFacade userFacade;

	private String ldapUrl = "";
	private String ldapDnSearch = "";
	private String ldapDnSearchSecret = "";

	private FacesContext context = FacesContext.getCurrentInstance();
	private ResourceBundle bundle = context.getApplication().getResourceBundle(
			context, "msgs");

	@PostConstruct
	public void init() {
		try {
			InitialContext ic = new InitialContext();
			ldapUrl = (String) ic.lookup("java:comp/env/ldapUrl");
			ldapDnSearch = (String) ic.lookup("java:comp/env/ldapDnSearch");
			ldapDnSearchSecret = (String) ic
					.lookup("java:comp/env/ldapDnSearchSecret");
		} catch (NamingException e) {
			System.err.println("AuthBean.init: " + e.getMessage());
		}
	}

	public void login() {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, "msgs");

		// Set up the environment for creating the initial context
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldapUrl);

		// Authenticate as S. User and password "mysecret"
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, ldapDnSearch);
		env.put(Context.SECURITY_CREDENTIALS, ldapDnSearchSecret);
		
		try {
			// Create initial context
			DirContext ctx = new InitialDirContext(env);

			// Create the default search controlsl
			SearchControls ctls = new SearchControls();

			// Specify the search filter to match
			String filter = "(uid=" + username + ")";

			// Search for objects using the filter
			try {
				SearchResult sr = ctx.search("ou=People,dc=uni-sofia,dc=bg",
						filter, ctls).nextElement();
				getLocalUser(username, password, sr);
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, bundle
								.getString("loginWellcome"), null));
			} catch (Exception e) {
				@SuppressWarnings("unused")
				LogBean log = new LogBean(
						"ERROR Auth.login: can't authenticate ,e: "
								+ e.getMessage());
				System.out.println("ldap error: " + e.getMessage());
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle
								.getString("loginWrongUsernamePassword"), null));
			}

			// Close the context when we're done
			ctx.close();
		} catch (NamingException e) {
			@SuppressWarnings("unused")
			LogBean log = new LogBean(
					"ERROR Auth.login: can't connect to LDAP ,e: "
							+ e.getMessage());
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle
							.getString("sorrySystemError"), null));
		}
	}

	private void getLocalUser(String username, String password, SearchResult sr) {
		String givenName = "";
		String sn = "";
		try {
			givenName = sr.getAttributes().get("givenName").get().toString();
			sn = sr.getAttributes().get("sn").get().toString();
		} catch (NamingException e1) {
			@SuppressWarnings("unused")
			LogBean log = new LogBean("ERROR Auth.getLocalUser: can't cast attribute");
		}
		try {
			// Try to find local user
			user = userFacade.findByUsername(username);
			if (user.getId() != null) {
				user.setProfilePassword(password);
				user.setPersonalNameFirst(givenName);
				user.setPersonalNameThird(sn);
				userFacade.update(user);
				systemLogin(user);
			}
		} catch (Exception e) {
			// Create local user
			@SuppressWarnings("unused")
			LogBean log = new LogBean("ERROR Auth.getLocalUser: no local user found");
			User user = new User();
			user.setIsActive(true);
			user.setPersonalNameFirst(givenName);
			user.setPersonalNameThird(sn);
			user.setProfileUsername(username);
			user.setProfilePassword(password);
			userFacade.save(user);
			@SuppressWarnings("unused")
			LogBean log1 = new LogBean(
					"INFO Auth.getLocalUser: create local user, username: " + username);
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle
							.getString("newAccountCreated")
							+ " "
							+ bundle.getString("UserName")
							+ ": "
							+ username
							+ ". " + bundle.getString("identifyToLogIn"), null));
		}
	}

	private void systemLogin(User user) {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		@SuppressWarnings("unused")
		LogBean log = new LogBean("INFO Auth.login: OK, user.id: "
				+ user.getId());
		externalContext.getSessionMap().put("user", user);
		try {
			externalContext.redirect(externalContext.getRequestContextPath()
					+ "/pages/protected/selectRole.xhtml");
		} catch (IOException e) {
			@SuppressWarnings("unused")
			LogBean log1 = new LogBean(
					"ERROR Auth.systemLogin: can't redirect to selectRole, e: "
							+ e.getMessage());
		}
	}

	public void logout() {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			externalContext.invalidateSession();
			externalContext.redirect(externalContext.getRequestContextPath());
		} catch (Exception e) {
			@SuppressWarnings("unused")
			LogBean lBean = new LogBean("AuthBean.init: " + e.getMessage());
		}
	}

	public Boolean isLoggedIn() {
		return user != null;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}