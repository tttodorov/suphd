package com.user.supervisor;

import java.io.Serializable;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.facade.UserFacade;
import com.facade.UserPhdReportFacade;
import com.model.User;
import com.model.PhdReport;

@RequestScoped
@ManagedBean(name = "supervisorReviewsPhd", eager = true)
public class SupervisorReviewsPhd implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserFacade userFacade;
	private User user;
	private Integer userPhdId;
	private PhdReport phdReport;
	private String content;
	@EJB
	private UserPhdReportFacade userPhdReportFacade;
	private Integer month;
	private Integer year;
	private String currentPhdReportName;

	private Boolean next = true;
	private Boolean prev = true;

	public PhdReport previousUserPhdReport;
	public PhdReport nextUserPhdReport;

	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) context
			.getExternalContext().getRequest();

	private ResourceBundle bundle = context.getApplication().getResourceBundle(
			context, "msgs");

	public void saveReview() {
		try {
			if (phdReport != null) {
				userPhdReportFacade.update(phdReport);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, bundle
								.getString("reviewUpdated"), null));
			} else {
				userPhdReportFacade.save(phdReport);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, bundle
								.getString("reviewSaved"), null));
			}

		} catch (Exception e) {
			System.out.println("Error: UserPhdReportBean.saveReport - "
					+ e.getMessage());
		}
	}

	public void updateUser() {
		try {
			userFacade.update(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public User getUser() {
		if (user == null) {
			user = userFacade.getById(getUserPhdId());
		}
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getUserPhdId() {
		if (userPhdId == null) {
			HttpServletRequest req = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			userPhdId = Integer.parseInt(req.getParameter("userId"));
		}
		return userPhdId;
	}

	public void setUserPhdId(Integer userPhdId) {
		this.userPhdId = userPhdId;
	}

	public PhdReport getUserPhdReport() {
		PhdReport currentUserPhdReport = new PhdReport();
		try {
			currentUserPhdReport = userPhdReportFacade
					.findReportByYearMonthUser(getYear(), getMonth(),
							getUserPhdId());
			// set current Phd report ID
			FacesContext phdReportContext = FacesContext.getCurrentInstance();
			HttpServletRequest phdReportRequest = (HttpServletRequest) phdReportContext
					.getExternalContext().getRequest();
			HttpSession phdReportHttpSession = phdReportRequest
					.getSession(false);
			phdReportHttpSession.setAttribute("currentPhdReportId",
					currentUserPhdReport.getId());
		} catch (Exception e) {
			System.out.println("UserPhdReportBean.getUserPhdReport: "
					+ e.getMessage());
		}
		phdReport = currentUserPhdReport;

		if (getContent() == null) {
			String noReport = "";
			currentUserPhdReport = userPhdReportFacade
					.findReportByYearMonthUser(getYear(), getMonth(),
							getUserPhdId());
			setContent(noReport);
		}

		checkForNextReport();
		checkForPreviousReport();

		return phdReport;
	}

	public void setUserPhdReport(PhdReport phdReport) {
		this.phdReport = phdReport;
	}

	// Go to previous report

	public PhdReport getPreviousUserPhdReport() {
		Integer currentReportYear = getUserPhdReport().getYear();
		Integer currentReportMonth = getUserPhdReport().getMonth();
		Integer currentReportUser = getUserPhdId();

		currentReportMonth -= 3;
		if (currentReportMonth == 0 && getUserPhdId() == currentReportUser) {
			currentReportMonth = 12;
			currentReportYear -= 1;

		}

		PhdReport currentUserPhdReport = userPhdReportFacade
				.findReportByYearMonthUser(currentReportYear,
						currentReportMonth, currentReportUser);

		setContent(currentUserPhdReport.getContent());

		// set current Phd report ID
		FacesContext phdReportContext = FacesContext.getCurrentInstance();
		HttpServletRequest phdReportRequest = (HttpServletRequest) phdReportContext
				.getExternalContext().getRequest();
		HttpSession phdReportHttpSession = phdReportRequest.getSession(false);
		phdReportHttpSession.setAttribute("currentPhdReportId",
				currentUserPhdReport.getId());
		return previousUserPhdReport;
	}

	public void setPreviousUserPhdReport(PhdReport previousUserPhdReport) {
		this.previousUserPhdReport = previousUserPhdReport;
	}

	// Go to next report

	public PhdReport getNextUserPhdReport() {
		Integer currentReportYear = getUserPhdReport().getYear();
		Integer currentReportMonth = getUserPhdReport().getMonth();
		Integer currentReportUser = getUserPhdId();

		currentReportMonth += 3;
		if (currentReportMonth == 15 && getUserPhdId() == currentReportUser) {
			currentReportMonth = 3;
			currentReportYear += 1;
		}

		PhdReport currentUserPhdReport = userPhdReportFacade
				.findReportByYearMonthUser(currentReportYear,
						currentReportMonth, currentReportUser);

		setContent(currentUserPhdReport.getContent());

		// set current Phd report ID
		FacesContext phdReportContext = FacesContext.getCurrentInstance();
		HttpServletRequest phdReportRequest = (HttpServletRequest) phdReportContext
				.getExternalContext().getRequest();
		HttpSession phdReportHttpSession = phdReportRequest.getSession(false);
		phdReportHttpSession.setAttribute("currentPhdReportId",
				currentUserPhdReport.getId());
		return nextUserPhdReport;
	}

	public void setNextUserPhdReport(PhdReport nextUserPhdReport) {
		this.nextUserPhdReport = nextUserPhdReport;
	}

	// Check if there is any report for next Month

	public void checkForNextReport() {
		Integer testNextMonth = phdReport.getMonth();
		Integer testNextYear = phdReport.getYear();
		Integer testCurrentReportUser = getUserPhdId();

		testNextMonth += 3;
		if (testNextMonth == 15) {
			testNextMonth = 3;
			testNextYear += 1;
		}
		PhdReport testNextUserPhdReport = userPhdReportFacade
				.findReportByYearMonthUser(testNextYear, testNextMonth,
						testCurrentReportUser);
		if (testNextUserPhdReport.getId() == null) {
			next = false;
		} else {
			next = true;
		}
	}

	// Check if there is any report for previous Month

	public void checkForPreviousReport() {
		Integer testPreviousMonth = phdReport.getMonth();
		Integer testPreviousYear = phdReport.getYear();
		Integer testCurrentReportUser = getUserPhdId();

		testPreviousMonth -= 3;
		if (testPreviousMonth == 0) {
			testPreviousMonth = 12;
			testPreviousYear -= 1;
		}
		PhdReport testPreviousUserPhdReport = userPhdReportFacade
				.findReportByYearMonthUser(testPreviousYear, testPreviousMonth,
						testCurrentReportUser);
		if (testPreviousUserPhdReport.getId() == null) {
			prev = false;
		} else {
			prev = true;
		}

	}

	// Report name
	public String getCurrentPhdReportName() {
		String monthString = null;
		Integer monthInteger = getUserPhdReport().getMonth();
		switch (monthInteger) {
		case 3:
			monthString = bundle.getString("march");
			break;
		case 6:
			monthString = bundle.getString("june");
			break;
		case 9:
			monthString = bundle.getString("september");
			break;
		case 12:
			monthString = bundle.getString("december");
			break;
		}
		currentPhdReportName = String.format("%d, %s", getUserPhdReport()
				.getYear(), monthString);
		return currentPhdReportName;
	}

	public void setCurrentPhdReportName(String currentPhdReportName) {
		this.currentPhdReportName = currentPhdReportName;
	}

	public Integer getMonth() {
		if (month == null) {
			Integer currentMonth = Calendar.getInstance().get(Calendar.MONTH);
			if (currentMonth <= 2) {
				return 3;
			} else if (currentMonth <= 5) {
				return 6;
			} else if (currentMonth <= 8) {
				return 9;
			} else {
				return 12;
			}
		}
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		year = Calendar.getInstance().get(Calendar.YEAR);
		return year;
	}

	public String moveTosupervisorListAllPhd() {
		return "supervisorListAllPhd";
	}

	public UserPhdReportFacade getUserPhdReportFacade() {
		return userPhdReportFacade;
	}

	public void setUserPhdReportFacade(UserPhdReportFacade userPhdReportFacade) {
		this.userPhdReportFacade = userPhdReportFacade;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getNext() {
		return next;
	}

	public void setNext(Boolean next) {
		this.next = next;
	}

	public Boolean getPrev() {
		return prev;
	}

	public void setPrev(Boolean prev) {
		this.prev = prev;
	}
}