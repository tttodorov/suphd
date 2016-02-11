package com.user.phd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.facade.UserPhdReportFacade;
import com.model.User;
import com.model.PhdReport;

@RequestScoped
@ManagedBean(name = "userPhdReportBean", eager = true)
public class UserPhdReportBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserPhdReportFacade userPhdReportFacade;
	private PhdReport phdReport;
	private String content;
	private Integer userId;
	private Integer month;
	private Integer year;
	private TreeNode root;
	private List<Integer> allUserPhdReportYears;
	private TreeNode selectedNode;
	private String currentPhdReportName;
	private User id;

	private Boolean next = true;
	private Boolean prev = true;

	private Boolean editReport = true;

	public PhdReport previousUserPhdReport;
	public PhdReport nextUserPhdReport;

	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) context
			.getExternalContext().getRequest();

	private ResourceBundle bundle = context.getApplication().getResourceBundle(
			context, "msgs");

	public void saveReport() {
		try {
			// set other report properties
			if (getUserPhdReport().getYear() == null) {
				phdReport.setYear(getYear());
			}
			if (getUserPhdReport().getMonth() == null) {
				phdReport.setMonth(getMonth());
			}
			if (getUserPhdReport().getUserId() == null) {
				phdReport.setUserId(getUserId());
			}

			phdReport.setContent(content);

			if (phdReport != null) {
				userPhdReportFacade.update(phdReport);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, bundle
								.getString("reportUpdated"), null));
			} else {
				userPhdReportFacade.save(phdReport);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, bundle
								.getString("reportSaved"), null));
			}

		} catch (Exception e) {
			System.out.println("Error: UserPhdReportBean.saveReport - "
					+ e.getMessage());
		}
	}

	@PostConstruct
	public void init() {

		setContent(getUserPhdReport().getContent());

		// Tree
		root = new DefaultTreeNode("Root", null);
		try {
			TreeNode[] years = new TreeNode[getAllUserPhdReportYears(
					getUserId()).size()];
			int index = 0;
			for (Integer year : getAllUserPhdReportYears(getUserId())) {
				years[index] = new DefaultTreeNode(String.valueOf(year), root);
				ArrayList<PhdReport> allUserPhdReportByYear = userPhdReportFacade
						.getAllUserPhdReportByUserYear(getUserId(), year);
				for (PhdReport phdReport : allUserPhdReportByYear) {
					// System.out.println(phdReport.toString());
					String monthString = null;
					Integer monthText = Integer.valueOf(phdReport
							.getMonth());
					switch (monthText) {
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
					years[index].getChildren().add(
							new DefaultTreeNode(monthString));

				}

				index++;

			}
		} catch (Exception e) {
			System.out.println("TreeNode: " + e.getMessage());
		}
	}

	// Go to previous report

	public PhdReport getPreviousUserPhdReport() {
		Integer currentReportYear = getUserPhdReport().getYear();
		Integer currentReportMonth = getUserPhdReport().getMonth();
		Integer currentReportUser = getUserId();

		currentReportMonth -= 3;
		if (currentReportMonth == 0 && getUserId() == currentReportUser) {
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
		Integer currentReportUser = getUserId();

		currentReportMonth += 3;
		if (currentReportMonth == 15 && currentReportUser == getUserId()) {
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

	public String moveToUserPhdReportEdit() {
		return "userPhdReportEdit";
	}

	public TreeNode getRoot() {
		return root;
	}

	public Integer getUserId() {
		// Get session userID from Session
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		HttpSession httpSession = request.getSession(false);
		Integer currentUserId = (Integer) Integer.valueOf(httpSession
				.getAttribute("currentUserId").toString());
		userId = currentUserId;

		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public PhdReport getUserPhdReport() {

		// Get session userID from Session
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		HttpSession httpSession = request.getSession(false);
		Integer userPhdReportId = null;

		try {
			userPhdReportId = (Integer) Integer.valueOf(httpSession
					.getAttribute("currentPhdReportId").toString());

			System.out.println("userPhdReportId: " + userPhdReportId);

		} catch (Exception e) {
			System.out
					.println("Cant find currentPhdReportId:" + e.getMessage());
		}

		if (userPhdReportId == null) {
			PhdReport currentUserPhdReport = new PhdReport();

			try {
				currentUserPhdReport = userPhdReportFacade
						.findReportByYearMonthUser(getYear(), getMonth(),
								getUserId());

				// set current Phd report ID
				FacesContext phdReportContext = FacesContext
						.getCurrentInstance();
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
		} else {
			phdReport = userPhdReportFacade.findReportById(userPhdReportId);
		}

		checkForEditReport();

		checkForNextReport();
		checkForPreviousReport(); // If there isn`t next or previous report In
									// console: No record found in db
		return phdReport;
	}

	public void setUserPhdReport(PhdReport phdReport) {
		this.phdReport = phdReport;
	}

	// Check if there is any report for next Month

	public void checkForNextReport() {
		Integer testNextMonth = phdReport.getMonth();
		Integer testNextYear = phdReport.getYear();
		Integer testCurrentReportUser = getUserId();

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
		Integer testCurrentReportUser = getUserId();

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

	public void checkForEditReport() {
		Integer editMonth = Calendar.getInstance().get(Calendar.MONTH);
		if (editMonth <= 2) {
			editMonth = 3;
		} else if (editMonth <= 5) {
			editMonth = 6;
		} else if (editMonth <= 8) {
			editMonth = 9;
		} else {
			editMonth = 12;
		}
		if ((editMonth == phdReport.getMonth())
				&& (phdReport.getYear() == Calendar.getInstance().get(
						Calendar.YEAR))) {
			editReport = true;
		} else {
			editReport = false;
		}
	}

	public List<PhdReport> getAllReports() {

		return userPhdReportFacade.findAll();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Integer> getAllUserPhdReportYears(Integer userId) {
		allUserPhdReportYears = userPhdReportFacade
				.getAllUserPhdReportYears(userId);
		return allUserPhdReportYears;
	}

	public void setAllUserPhdReportYears(List<Integer> getAllUserPhdReportYears) {
		this.allUserPhdReportYears = getAllUserPhdReportYears;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
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

	public void setYear(Integer year) {
		this.year = year;
	}

	public String moveToPersonalData() {
		return "userPhdPersonalData";
	}

	public FacesContext getContext() {
		return context;
	}

	public void setContext(FacesContext context) {
		this.context = context;
	}

	public User getId() {
		return id;
	}

	public void setId(User id) {
		this.id = id;
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

	public Boolean getEditReport() {
		return editReport;
	}

	public void setEditReport(Boolean editReport) {
		this.editReport = editReport;
	}

}