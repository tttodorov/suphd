package com.user.phd;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;


import com.facade.CountryService;
import com.facade.UserFacade;
import com.model.Country;
import com.model.User;

@RequestScoped
@ManagedBean(name = "userPhdCitizenship", eager = true)
public class UserPhdCitizenshipBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserFacade userFacade;

	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) context
			.getExternalContext().getRequest();

	private ResourceBundle bundle = context.getApplication().getResourceBundle(
			context, "msgs");

	private User user;


	// Country Autocomplete

	private List<Country> codeCountries;

	public List<String> completeCountry(String query) {
		System.out.println("completeCountry: " + query);
		List<Country> allCountries = getCodeCountries();

		List<String> filteredCountries = new ArrayList<String>();

		for (Country countriesItem : allCountries) {
			try {
				if (countriesItem.getMesName().toLowerCase()
						.startsWith(query.toLowerCase())) {
					filteredCountries.add(countriesItem.getMesName());
				}
			} catch (Exception e) {
				System.out.println("Look for country: " + e.getMessage());
			}
		}

		return filteredCountries;
	}

	public List<Country> getCodeCountries() {
		try {
			URL url = new URL(
					"http://localhost:8080/PhdEJB/CodeCountryFacade/CodeCountryFacadeImp?wsdl");
			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName("http://facade.com/", "CodeCountryFacade");
			Service service = Service.create(url, qname);
			CountryService codeCountry = service
					.getPort(CountryService.class);
			codeCountries = codeCountry.findAllMes();
		} catch (Exception e) {
			System.out.println("UserPhdPersonalDataBean.getCodeCountries(): "
					+ e.getMessage());
		}
		return codeCountries;
	}

	public void setCodeCountries(List<Country> codeCountries) {
		this.codeCountries = codeCountries;
	}

	// get the remote user
	@ManagedProperty("#{request.remoteUser}")
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		if (user == null) {
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			String username = context.getUserPrincipal().getName();
			user = userFacade.findByUsername(username);
		}
		return user;
	}

	public void updateUser() {
		try {
			userFacade.update(user);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, bundle
							.getString("savedChanges"), null));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}