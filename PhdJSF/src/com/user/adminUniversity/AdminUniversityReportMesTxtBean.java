package com.user.adminUniversity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import com.facade.UserFacade;
import com.model.User;

@SessionScoped
@ManagedBean(name = "adminUniversityReportMesTxt", eager = true)
public class AdminUniversityReportMesTxtBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserFacade userFacade;
	private List<User> listAllUserPhd = new ArrayList<User>();

	@PostConstruct
	public void init() {
		listAllUserPhd = userFacade.getAllUserPhd();
	}

	public static String getUdepartments() {
		return "0|'Софийски университет \"Св. Климент Охридски\"'|68134|'бул. \"Цар Освободител\" № 15'|'000670680'|'02 8623 552'|'02 8161 593'|'02 988 22 71'|'info@uni-sofia.bg'|0|'U'\n";
	}

	public static String getUprofGroup() {
		return "306|1|'01/14/2014'|'01/14/2020'|'U'\n"
				+ "401|1|'09/27/2007'|'07/15/2019'|'U'\n"
				+ "701|1|'10/03/2013'|'10/03/2019'|'U'\n"
				+ "1101|1|'09/04/2008'|'09/04/2014'|'U'\n"
				+ "1103|1|'10/18/2007'|'07/15/2019'|'U'\n"
				+ "1104|1|'06/03/2013'|'06/03/2019'|'U'\n"
				+ "1105|1|'07/05/2012'|'07/05/2018'|'U'\n"
				+ "1106|1|'11/25/2010'|'11/25/2016'|'U'\n"
				+ "1107|1|'07/26/2012'|'07/26/2018'|'U'\n"
				+ "1108|1|'07/26/2012'|'07/26/2018'|'U'\n"
				+ "1211|1|'11/25/2010'|'11/25/2016'|'U'\n"
				+ "1216|1|'07/26/2012'|'07/26/2018'|'U'\n"
				+ "1220|1|'05/16/2013'|'05/16/2019'|'U'\n"
				+ "1222|1|'11/25/2010'|'11/25/2016'|'U'\n"
				+ "1301|1|'10/12/2013'|'10/12/2019'|'U'\n"
				+ "1501|1|'05/19/2011'|'05/31/2017'|'U'\n"
				+ "1502|1|'11/09/2010'|'11/09/2016'|'U'\n"
				+ "1503|1|'12/09/2009'|'12/09/2015'|'U'\n"
				+ "1504|1|'07/30/2008'|'07/30/2014'|'U'\n"
				+ "1505|1|'10/20/2011'|'10/20/2017'|'U'\n"
				+ "1506|1|'06/02/2009'|'06/02/2015'|'U'\n"
				+ "1507|1|'11/23/2010'|'11/23/2016'|'U'\n"
				+ "1508|1|'04/13/2011'|'04/13/2017'|'U'\n"
				+ "1510|1|'04/13/2011'|'04/13/2017'|'U'\n"
				+ "1511|1|'03/11/2009'|'03/11/2015'|'U'\n";
	}

	public static String getPersonalIdNumber(String personalIdNumber) {
		if (personalIdNumber == null) {
			return "";
		}
		return personalIdNumber;
	}

	public static Integer getPersonalIdNumberType(Integer personalIdNumberType) {
		if (personalIdNumberType == null) {
			return 0;
		}
		return personalIdNumberType;
	}

	public static String getPersonalNameFirst(String personalNameFirst) {
		if (personalNameFirst == null) {
			return "";
		}
		return personalNameFirst;
	}

	public static String getPersonalNameSecond(String personalNameSecond) {
		if (personalNameSecond == null) {
			return "";
		}
		return personalNameSecond;
	}

	public static String getPersonalNameThird(String personalNameThird) {
		if (personalNameThird == null) {
			return "";
		}
		return personalNameThird;
	}

	public static Integer getPersonalGender(Integer personalGender) {
		if (personalGender == null) {
			return 0;
		} else if ((personalGender != 0) && (personalGender != 1)) {
			return 0;
		}
		return personalGender;
	}

	public static Integer getMesBirthLocation(Integer birthCountryMesId,
			Integer birthEkatteMesId) {
		if (birthCountryMesId == null) {
			return 0;
		}
		if (birthCountryMesId == 34) {
			if (birthEkatteMesId == null) {
				return 0;
			}
			return birthEkatteMesId;
		}
		return birthCountryMesId + 100000;
	}

	public static Integer getCitizenship(Integer personalBirthCountry,
			Integer personalIdNumberCountry) {
		if (personalIdNumberCountry == null) {
			return personalBirthCountry;
		}
		return personalIdNumberCountry;
	}

	public static Integer getContactCountry(Integer contactCountry) {
		if (contactCountry == null) {
			return 1;
		}
		return contactCountry;
	}

	public static Integer getContactCity(Integer contactCity) {
		if (contactCity == null) {
			return 1;
		}
		return contactCity;
	}

	public static String getMesDateFormat(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(date);
	}

	public static String getMesDateFormatYear(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(date);
	}

	public static String getMasterUniversityName(String masterUniversityName) {
		if (masterUniversityName == null) {
			return "";
		}
		return masterUniversityName;
	}

	public static Integer getMesUniversityLocation(
			Integer universityCountryMesId, Integer universityEkatteMesId) {
		if (universityCountryMesId == null) {
			if (universityEkatteMesId == null) {
				return 0;
			}
			return universityEkatteMesId;
		}
		if (universityCountryMesId == 34) {
			return universityEkatteMesId;
		}
		return universityCountryMesId + 100000;
	}

	public static String getMasterProfession(String masterProfession) {
		if (masterProfession == null) {
			return "";
		}
		return masterProfession;
	}

	public static Integer getMasterCountry(Integer masterCountry) {
		if (masterCountry == null) {
			return 1;
		}
		return masterCountry;
	}

	public static Integer getAcademicsFaculty(Integer academicsFaculty) {
		if (academicsFaculty == null) {
			return 1;
		}
		return academicsFaculty;
	}

	public static Integer getAcademicsSpecialty(Integer academicsSpecialty) {
		if (academicsSpecialty == null) {
			return 1;
		}
		return academicsSpecialty;
	}

	public static Integer getAcademicsCourse(Integer academicsCourse) {
		Integer defaultAcademicsCourseID = 38;
		if (academicsCourse == null) {
			return defaultAcademicsCourseID;
		}
		return academicsCourse;
	}

	public static String getAcademicsFacultyNumber(String academicsFacultyNumber) {
		if (academicsFacultyNumber == null) {
			return "";
		}
		return academicsFacultyNumber;
	}

	public static Integer getAcademicsForm(Integer academicsForm) {
		if (academicsForm == null) {
			return 1;
		}
		return academicsForm;
	}

	public static Integer getAcademicsPayment(Integer academicsPayment) {
		if (academicsPayment == null) {
			return 1;
		}
		return academicsPayment;
	}

	public static Integer getAcademicsReasonsForAcc(
			Integer academicsReasonsForAcc) {
		if (academicsReasonsForAcc == null) {
			return 1;
		}
		return academicsReasonsForAcc;
	}

	public static Integer getAcademicsStatus(Integer academicsStatus) {
		if (academicsStatus == null) {
			return 1;
		}
		return academicsStatus;
	}

	public static Integer getAcademicsChangeUniversity(
			Integer academicsChangeUniversity) {
		if (academicsChangeUniversity == null) {
			return 1;
		}
		return academicsChangeUniversity;
	}

	public static Integer getMasterUniversityMesId(Integer masterUniversityMesId) {
		if (masterUniversityMesId == null) {
			return 1;
		}
		return masterUniversityMesId;
	}

	public static String getMasterUniversityMesName(
			String masterUniversityMesName) {
		if (masterUniversityMesName == null) {
			return "";
		}
		return masterUniversityMesName;
	}

	public static Integer getAcademicsProfGroup(Integer academicsProfGroup) {
		if (academicsProfGroup == null) {
			return 2000;
		}
		return academicsProfGroup;
	}

	public static String getMasterDiplomaNumber(String masterDiplomaNumber) {
		if (masterDiplomaNumber == null) {
			return "";
		}
		return masterDiplomaNumber;
	}

	public static String getMesIdNumberForeigner(Integer idNumberType,
			String idNumber) {
		if (idNumberType == null) {
			return "";
		}
		if ((idNumberType == 0) || (idNumberType == 1)) {
			return "";
		}
		if (idNumber == null) {
			return "";
		}
		return idNumber;
	}

	public static Integer getMesCountSpeciality(Boolean newSpeciality) {
		int countSpeciality = 1;
		if (newSpeciality == null) {
			return 0;
		}
		countSpeciality += (newSpeciality ? 1 : 0);
		return countSpeciality;
	}

	public static String getMesEducationDuration(String educationDurationName) {
		if (educationDurationName == null) {
			return "";
		}
		if (educationDurationName.length() == 6) {
			return educationDurationName.substring(0, 1);
		}
		return educationDurationName.substring(0, 1) + "."
				+ educationDurationName.substring(2, 3);
	}

	public static String getMesStatusChangeDate(Date statusChangeDate) {
		if (statusChangeDate == null) {
			return "0";
		}
		return getMesDateFormat(statusChangeDate);
	}

	public static Integer getMesBoolean(Boolean scholarship) {
		if (scholarship == null) {
			return 0;
		} else if (scholarship) {
			return 1;
		}
		return 0;
	}

	public List<User> getListAllUserPhd() {
		return listAllUserPhd;
	}

	public void setListAllUserPhd(List<User> listAllUserPhd) {
		this.listAllUserPhd = listAllUserPhd;
	}
}