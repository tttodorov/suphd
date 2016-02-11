package com.user.adminUniversity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.facade.CodeSpecialityFacade;
import com.facade.CountryFacade;
import com.facade.EKATTEFacade;
import com.facade.EducationClassFacade;
import com.facade.EducationDurationFacade;
import com.facade.EducationFormFacade;
import com.facade.EducationPaymentFacade;
import com.facade.EducationReasonForAccFacade;
import com.facade.EducationStatusFacade;
import com.facade.FacultyFacade;
import com.facade.UniversityFacade;
import com.facade.UserFacade;
import com.facade.UserPhdStatusChangeFacade;
import com.model.EKATTE;
import com.model.University;
import com.model.User;

@SessionScoped
@ManagedBean(name = "adminUniversityReportMesExcel", eager = true)
public class AdminUniversityReportMesExcelBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserFacade userFacade;
	private List<User> allUserPhdReportMes;

	@EJB
	private EKATTEFacade ekatteFacade;

	@EJB
	private CountryFacade countryFacade;

	@EJB
	private FacultyFacade facultyFacade;

	@EJB
	private CodeSpecialityFacade codeSpecialityFacade;

	@EJB
	private EducationClassFacade educationClassFacade;

	@EJB
	private EducationFormFacade educationFormFacade;

	@EJB
	private EducationDurationFacade educationDurationFacade;

	@EJB
	private EducationPaymentFacade educationPaymentFacade;

	@EJB
	private EducationReasonForAccFacade reasonsForAccFacade;

	@EJB
	private EducationStatusFacade educationStatusFacade;

	@EJB
	private UserPhdStatusChangeFacade userPhdStatusChangeFacade;

	@EJB
	private UniversityFacade universityFacade;

	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) context
			.getExternalContext().getRequest();
	private ResourceBundle bundle = context.getApplication().getResourceBundle(
			context, "msgs");

	@PostConstruct
	public void init() {
		allUserPhdReportMes = userFacade.getAllUserPhdReportMes();
	}

	public String getIdNumberTypeName(Integer idNumberType) {
		if (idNumberType == null) {
			return bundle.getString("PIN");
		} else if (idNumberType == 0) {
			return bundle.getString("PIN");
		} else if (idNumberType == 1) {
			return bundle.getString("personalId");
		}
		return bundle.getString("service");
	}

	public String getGenderName(Integer gender) {
		if (gender == null) {
			return bundle.getString("man");
		} else if (gender == 0) {
			return bundle.getString("man");
		} else if (gender == 1) {
			return bundle.getString("woman");
		}
		return bundle.getString("service");
	}

	public String getLocationName(Integer birthCountryId, Integer birthEkatteId) {
		if (birthCountryId == null) {
			return "";
		}
		if (birthCountryId == 34) {
			if (birthEkatteId == null) {
				return "";
			}
			return ekatteFacade.getById(birthEkatteId).getMesName();
		}
		return countryFacade.getById(birthCountryId).getMesName();
	}

	public String getEkkateName(Integer ekatteId) {
		if (ekatteId == null) {
			return "";
		}
		EKATTE ekatte = new EKATTE();
		try {
			ekatte = ekatteFacade.getById(ekatteId);
		} catch (Exception e) {
			System.out.println(" ERROR " + e.getMessage());
		}
		return bundle.getString("municipalityShort") + " "
				+ ekatte.getMesName() + ", " + bundle.getString("cityShort")
				+ " " + ekatte.getCity() + ", "
				+ bundle.getString("regionShort") + " " + ekatte.getRegion();
	}

	public String getCountryName(Integer countryId) {
		if (countryId == null) {
			return "";
		}
		return countryFacade.getById(countryId).getMesName();
	}

	public String getDateString(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(date);
	}

	public String getDateYearString(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(date);
	}

	public String checkString(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}

	public String getIdNumberForeigner(Integer idNumberType, String idNumber) {
		if (idNumberType == null) {
			return "";
		} else if (idNumberType == 0) {
			return "";
		}
		return idNumber;
	}

	public Integer getFacultyMesId(Integer facultyId) {
		if (facultyId == null) {
			return 1;
		}
		return facultyFacade.getById(facultyId).getMesId();
	}

	public Integer getSpecialityMesId(Integer specialityId) {
		if (specialityId == null) {
			return 1;
		}
		return codeSpecialityFacade.getById(specialityId).getMesId();
	}

	public String getCourseName(Integer courseId) {
		if (courseId == null) {
			return "";
		}
		return educationClassFacade.getById(courseId).getMesName();
	}

	public Integer getEducationFormMesId(Integer edFormId) {
		if (edFormId == null) {
			return 1;
		}
		return educationFormFacade.getById(edFormId).getMesId();
	}
	
	public String getEducationDurationName(Integer edDurationId) {
		if (edDurationId == null) {
			return "";
		}
		return educationDurationFacade.getById(edDurationId).getMesName();
	}

	public String getEducationPaymentName(Integer edPaymentId) {
		if (edPaymentId == null) {
			return "";
		}
		return educationPaymentFacade.getById(edPaymentId).getMesName();
	}

	public String getReasonForAccName(Integer reasonForAccId) {
		if (reasonForAccId == null) {
			return "";
		}
		return reasonsForAccFacade.getById(reasonForAccId).getMesName();
	}

	public String getBoolean(Boolean b) {
		if (b == null) {
			return bundle.getString("btnNo");
		} else if (!b) {
			return bundle.getString("btnNo");
		}
		return bundle.getString("btnYes");
	}

	public String getStatusChangeDate(Integer userId) {
		return getDateString(userPhdStatusChangeFacade.getLastByUser(userId)
				.getCreateOn());
	}

	public String getStatusName(Integer statusId) {
		if (statusId == null) {
			return "";
		}
		return educationStatusFacade.getById(statusId).getMesName();
	}

	public String getUniversityName(Integer universityId, String universityName) {
		if (universityId == null) {
			if (universityName != null) {
				return universityName;
			}
			return "";
		}
		University u = new University();
		try {
			u = universityFacade.getById(universityId);
			if (u.getMesName() != null) {
				return u.getMesName();
			}
		} catch (Exception e) {
			System.out.println(" getUniversityName: ERROR " + e.getMessage());
		}
		return "";
	}

	public String getCountSpeciality(Boolean b) {
		if (b == null) {
			return "1";
		} else if (!b) {
			return "1";
		}
		return "2";
	}
	
	public List<User> getAllUserPhdReportMes() {
		return allUserPhdReportMes;
	}

	public void setAllUserPhdReportMes(List<User> listAllUserPhdReportMes) {
		this.allUserPhdReportMes = listAllUserPhdReportMes;
	}
}