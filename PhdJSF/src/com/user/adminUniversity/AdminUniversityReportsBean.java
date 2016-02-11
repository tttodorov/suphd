package com.user.adminUniversity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.io.output.FileWriterWithEncoding;

import com.LogBean;
import com.facade.CodeSpecialityFacade;
import com.facade.CountryFacade;
import com.facade.EKATTEFacade;
import com.facade.EducationClassFacade;
import com.facade.EducationDurationFacade;
import com.facade.EducationFormFacade;
import com.facade.EducationPaymentFacade;
import com.facade.EducationReasonForAccFacade;
import com.facade.EducationStatusFacade;
import com.facade.EducationTypeFacade;
import com.facade.FacultyFacade;
import com.facade.SpecialityFacade;
import com.facade.UniversityFacade;
import com.facade.UserFacade;
import com.facade.UserPhdStatusChangeFacade;
import com.model.Faculty;
import com.model.Speciality;
import com.model.User;

@SessionScoped
@ManagedBean(name = "adminUniversityReports", eager = true)
public class AdminUniversityReportsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String universityCode;
	private Integer affiliateCode = 0;
	private Integer year;
	private Integer term;
	private Calendar c = Calendar.getInstance();

	@EJB
	private UniversityFacade universityFacade;

	@EJB
	private UserFacade userFacade;
	private List<User> allUserPhdReportMes = new ArrayList<User>();
	private List<User> allUserPhdForeignReportMes = new ArrayList<User>();
	private List<Speciality> allSpecialityReportMes = new ArrayList<Speciality>();
	private List<Faculty> allFacultyReportMes = new ArrayList<Faculty>();

	@EJB
	private CountryFacade countryFacade;

	@EJB
	private EKATTEFacade ekatteFacade;

	@EJB
	private EducationTypeFacade educationTypeFacade;
	private Integer educationTypeMasterId = 6;

	@EJB
	private FacultyFacade facultyFacade;

	@EJB
	private EducationClassFacade educationClassFacade;

	@EJB
	private EducationFormFacade educationFormFacade;

	@EJB
	private EducationDurationFacade educationDurationFacade;

	@EJB
	private EducationPaymentFacade educationPaymentFacade;
	private Integer typeMaster = 0;

	@EJB
	private EducationReasonForAccFacade educationReasonForAccFacade;

	@EJB
	private UserPhdStatusChangeFacade userPhdStatusChangeFacade;

	@EJB
	private EducationStatusFacade educationStatusFacade;

	@EJB
	private CodeSpecialityFacade codeSpecialityFacade;

	@EJB
	private SpecialityFacade specialityFacade;

	@PostConstruct
	public void init() {
		allUserPhdReportMes = userFacade.getAllUserPhdReportMes();
		allUserPhdForeignReportMes = userFacade.getAllUserPhdForeignReportMes();
		allSpecialityReportMes = specialityFacade.getAllSpecialityReportMes();
		allFacultyReportMes = facultyFacade.getAllFacultiesReportMes();
		try {
			InitialContext ic = new InitialContext();
			universityCode = (String) ic.lookup("java:comp/env/universityCode");
		} catch (NamingException e) {
			@SuppressWarnings("unused")
			LogBean lBean = new LogBean("AdminUniversity.init: "
					+ e.getMessage());
		}
		year = c.get(Calendar.YEAR);
		term = 1;
		Date today = c.getTime();
		// check if before 16 April
		c.set(Calendar.MONTH, 3);
		c.set(Calendar.DATE, 16);
		if (today.before(c.getTime())) {
			year--;
			term++;
		}
		// check if after 15 November
		c.set(Calendar.MONTH, 10);
		c.set(Calendar.DATE, 15);
		if (c.before(today)) {
			term++;
		}
	}

	public void generateReportMes() {
		try {
			BufferedWriter writer = new BufferedWriter(
					new FileWriterWithEncoding("/phd/downloads/"
							+ universityCode + "_" + year + "_" + term
							+ "_PhD.txt", "cp1251"));
			writer.write(String.format("[UniversityDetails]%n"));
			writer.write(getUniversityDetails());
			writer.write(String.format("[Udepartments]%n"));
			writer.write(AdminUniversityReportMesTxtBean.getUdepartments());
			writer.write(String.format("[Uunits]%n"));
			writer.write(getUunits());
			writer.write(String.format("[UprofGroup]%n"));
			writer.write(AdminUniversityReportMesTxtBean.getUprofGroup());
			writer.write(String.format("[Uspec]%n"));
			writer.write(getUSpec());
			writer.write(String.format("[UStudAll]%n"));
			writer.write(getUStudAll());
			writer.write(String.format("[UStudentNationality]%n"));
			writer.write(getUStudentNationality());
			writer.close();
		} catch (SecurityException e) {
			@SuppressWarnings("unused")
			LogBean lBean = new LogBean(
					" ERROR AdminUniversityReportsBean.generateReportMes: "
							+ e.getMessage());
		} catch (IOException e) {
			@SuppressWarnings("unused")
			LogBean lBean = new LogBean(
					" ERROR AdminUniversityReportsBean.generateReportMes: "
							+ e.getMessage());
		}
	}

	public String getUniversityDetails() {
		return universityCode
				+ "|"
				+ year
				+ "|"
				+ term
				+ "|'Софийски университет \"Св. Климент Охридски\"'|'СУ'|11|1|1|68134|1504|'бул. \"Цар Освободител\" № 15'|'02 8623 552'|'02 8161 593'|'02 988 22 71'|'02 9308 500'|'02 987 34 88'|''|'info@uni-sofia.bg'|'www.uni-sofia.bg'|'000670680'|'1888'|'U'\n";
	}

	public String getUunits() {
		Integer isCanceled = 0;
		Integer ekatteId = 68134;
		String action = "U";
		String result = "";
		for (Faculty faculty : allFacultyReportMes) {
			result += String.format("%s|%s|'%s'|%s|%s|'%s'%n", affiliateCode, faculty.getMesId(), faculty.getName(), isCanceled, ekatteId, action);
		}
		return result;
	}
	
	private String getUSpec() {
		String result = "";
		for (Speciality speciality : allSpecialityReportMes) {
			Integer educationDuration = 3;
			if(speciality.getMesEdForm() == 3) {
				educationDuration = 4;
			}
			
			Integer codeNSI = 0;
			Integer isCanceled = 0;
			String status = "U";
			Integer isRegulatedProfession = 0;
			Integer isMilitary = 0;

			result += String.format(
					"%s|%s|%s|%s|%s|%s|'%s'|'%s'|%s|%s|'%s'|%s|%s|%s%n",
					affiliateCode, speciality.getFaculty(), speciality
							.getMesSpecialityId(), speciality.getMesEdForm(),
					educationDuration, AdminUniversityReportMesTxtBean
					.getMesBoolean(speciality.getIsAcredited()),
					AdminUniversityReportMesTxtBean.getMesDateFormat(speciality
							.getDateStart()), AdminUniversityReportMesTxtBean
							.getMesDateFormat(speciality.getDateEnd()),
					codeNSI, isCanceled, status, isRegulatedProfession,
					isMilitary, speciality.getMesProfGroup());
		}
		return result;
	}

	private String getUStudAll() {
		String result = "";
		for (User u : allUserPhdReportMes) {
			result += String
					.format("'%s'|%s|'%s'|'%s'|'%s'|%s|%s|%s|%s|%s|"
							+ "'%s'|%s|'%s'|'%s'|%s|'%s'|%s|'%s'|%s|%s|"
							+ "%s|%s|%s|'%s'|%s|%s|%s|%s|'%s'|%s|"
							+ "%s|'%s'|%s|%s|'%s'|%s|%s|%s|'%s'|'%s'|"
							+ "'%s'|%s|'%s'|%s|'%s'|%s|%s|'%s'|'%s'|%s%n",
							/* 1 */AdminUniversityReportMesTxtBean
									.getPersonalIdNumber(u
											.getPersonalIdNumber()),
							/* 2 */AdminUniversityReportMesTxtBean
									.getPersonalIdNumberType(u
											.getPersonalIdNumberType()),
							/* 3 */AdminUniversityReportMesTxtBean
									.getPersonalNameFirst(u
											.getPersonalNameFirst()),
							/* 4 */AdminUniversityReportMesTxtBean
									.getPersonalNameSecond(u
											.getPersonalNameSecond()),
							/* 5 */AdminUniversityReportMesTxtBean
									.getPersonalNameThird(u
											.getPersonalNameThird()),
							/* 6 */AdminUniversityReportMesTxtBean
									.getPersonalGender(u.getPersonalGender()),
							/* 7 */AdminUniversityReportMesTxtBean.getMesBirthLocation(
									countryFacade.getById(
											u.getPersonalBirthCountry())
											.getMesId(),
									ekatteFacade.getById(
											u.getPersonalBirthCity())
											.getMesId()),
							/* 8 */AdminUniversityReportMesTxtBean
									.getCitizenship(
											u.getPersonalBirthCountry(),
											u.getPersonalIdNumberCountry()),
							/* 9 */AdminUniversityReportMesTxtBean
									.getContactCountry(countryFacade.getById(
											u.getContactCountry()).getMesId()),
							/* 10 */AdminUniversityReportMesTxtBean
									.getContactCity(ekatteFacade.getById(
											u.getContactCity()).getMesId()),
							/* 11 */AdminUniversityReportMesTxtBean
									.getMesDateFormat(u.getPersonalBirthDate()),
							/* 12 */educationTypeFacade.getById(
									educationTypeMasterId).getMesId(),
							/* 13 */AdminUniversityReportMesTxtBean
									.getMesDateFormatYear(u
											.getMasterDiplomaDate()),
							/* 14 */AdminUniversityReportMesTxtBean
									.getMasterUniversityName(u
											.getMasterUniversityName()),
							/* 15 */AdminUniversityReportMesTxtBean
									.getMesUniversityLocation(
											countryFacade.getById(
													u.getMasterCountry())
													.getMesId(),
											ekatteFacade.getById(
													u.getMasterCity())
													.getMesId()),
							/* 16 */AdminUniversityReportMesTxtBean
									.getMasterProfession(u
											.getMasterProfession()),
							/* 17 */AdminUniversityReportMesTxtBean
									.getMasterCountry(u.getMasterCountry()),
							/* 18 */AdminUniversityReportMesTxtBean
									.getMesIdNumberForeigner(
											u.getPersonalIdNumberType(),
											u.getPersonalIdNumber()),
							/* 19 */AdminUniversityReportMesTxtBean
									.getMesCountSpeciality(u
											.getAcademicsNewSpecialty()),
							/* 20 */affiliateCode,
							/* 21 */AdminUniversityReportMesTxtBean
									.getAcademicsFaculty(facultyFacade.getById(
											u.getAcademicsFaculty()).getMesId()),
							/* 22 */AdminUniversityReportMesTxtBean.getAcademicsSpecialty(codeSpecialityFacade
									.getById(u.getAcademicsSpeciality())
									.getMesId()),
							/* 23 */AdminUniversityReportMesTxtBean
									.getAcademicsCourse(educationClassFacade
											.getById(u.getAcademicsCourse())
											.getMesId()),
							/* 24 */AdminUniversityReportMesTxtBean
									.getAcademicsFacultyNumber(u
											.getAcademicsFacultyNumber()),
							/* 25 */AdminUniversityReportMesTxtBean
									.getAcademicsForm(educationFormFacade
											.getById(u.getAcademicsForm())
											.getMesId()),
							/* 26 */AdminUniversityReportMesTxtBean
									.getMesEducationDuration(educationDurationFacade
											.getById(u.getAcademicsDuration())
											.getMesName()),
							/* 27 */AdminUniversityReportMesTxtBean
									.getAcademicsPayment(educationPaymentFacade
											.getById(u.getAcademicsPayment())
											.getMesId()),
							/* 28 */typeMaster,
							/* 29 */AdminUniversityReportMesTxtBean
									.getMesDateFormatYear(u
											.getAcademicsYearOfAdmission()),
							/* 30 */AdminUniversityReportMesTxtBean
									.getAcademicsReasonsForAcc(educationReasonForAccFacade
											.getById(
													u.getAcademicsReasonsForAcc())
											.getMesId()),
							/* 31 */AdminUniversityReportMesTxtBean
									.getMesBoolean(u.getAcademicsScholarship()),
							/* 32 */AdminUniversityReportMesTxtBean
									.getMesStatusChangeDate(userPhdStatusChangeFacade
											.getLastByUser(u.getId())
											.getCreateOn()),
							/* 33 */AdminUniversityReportMesTxtBean
									.getAcademicsStatus(educationStatusFacade
											.getById(u.getAcademicsStatus())
											.getMesId()),
							/* 34 */AdminUniversityReportMesTxtBean
									.getAcademicsChangeUniversity(universityFacade
											.getById(
													u.getAcademicsChangeUniversity())
											.getMesId()),
							/* 35 */userPhdStatusChangeFacade.getLastByUser(
									u.getId()).getNotes(),
							/* 36 */AdminUniversityReportMesTxtBean
									.getMesBoolean(u.getAcademicsHostel()),
							/* 37 */AdminUniversityReportMesTxtBean
									.getMesBoolean(u.getAcademicsHolidays()),
							/* 38 */AdminUniversityReportMesTxtBean.getMesBoolean(u
									.getAcademicsInternationalProgram()),
							/* 39 */AdminUniversityReportMesTxtBean.getMesDateFormat(u
									.getAcademicsCalendarYearOfAdmission()),
							/* 40 */AdminUniversityReportMesTxtBean
									.getMesDateFormat(u
											.getAcademicsYearOfGraduation()),
							/* 41 */userPhdStatusChangeFacade.getLastByUser(
									u.getId()).getNotes(),
							/* 42 */typeMaster,
							/* 43 */AdminUniversityReportMesTxtBean
									.getMesDateFormatYear(u
											.getMasterDiplomaDate()),
							/* 44 */AdminUniversityReportMesTxtBean
									.getMasterUniversityMesId(universityFacade
											.getById(u.getMasterUniversity())
											.getMesId()),
							/* 45 */AdminUniversityReportMesTxtBean
									.getMasterUniversityMesName(universityFacade
											.getById(u.getMasterUniversity())
											.getMesName()),
							/* 46 */AdminUniversityReportMesTxtBean
									.getMesUniversityLocation(
											countryFacade.getById(
													u.getMasterCountry())
													.getMesId(),
											ekatteFacade.getById(
													u.getMasterCity())
													.getMesId()),
							/* 47 */AdminUniversityReportMesTxtBean
									.getAcademicsProfGroup(u
											.getAcademicsProfGroup()),
							/* 48 */AdminUniversityReportMesTxtBean
									.getMasterDiplomaNumber(u
											.getMasterDiplomaNumber()),
							/* 49 */AdminUniversityReportMesTxtBean
									.getMesDateFormat(u.getMasterDiplomaDate()),
							/* 50 */AdminUniversityReportMesTxtBean
									.getMesBoolean(u.getAcademicsNewSpecialty()));
		}
		return result;
	}

	private String getUStudentNationality() {
		String result = "";
		for (User u : allUserPhdForeignReportMes) {
			result += String.format("'%s'|%s%n", u.getPersonalIdNumber(),
					u.getPersonalCitizenship1());
		}
		return result;
	}

	public String getUniversityCode() {
		return universityCode;
	}

	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public List<User> getAllUserPhdForeignReportMes() {
		return allUserPhdForeignReportMes;
	}

	public void setAllUserPhdForeignReportMes(List<User> allUserPhdForeign) {
		this.allUserPhdForeignReportMes = allUserPhdForeign;
	}
}