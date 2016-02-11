package com.converter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.facade.CodeSpecialityFacade;
import com.facade.CodeSpecialityService;
import com.facade.CountryFacade;
import com.facade.CountryService;
import com.facade.EKATTEFacade;
import com.facade.EKATTEService;
import com.facade.EducationDurationFacade;
import com.facade.EducationDurationService;
import com.facade.EducationFormFacade;
import com.facade.EducationFormService;
import com.facade.EducationPaymentFacade;
import com.facade.EducationPaymentService;
import com.facade.EducationProfGroupFacade;
import com.facade.EducationProfGroupService;
import com.facade.EducationReasonForAccFacade;
import com.facade.EducationReasonForAccService;
import com.facade.EducationStatusFacade;
import com.facade.EducationStatusService;
import com.facade.FacultyFacade;
import com.facade.FacultyService;
import com.facade.GroupFacade;
import com.facade.GroupService;
import com.facade.SpecialityNSIFacade;
import com.facade.SpecialityNSIService;
import com.facade.SuClassFacade;
import com.facade.SuClassService;
import com.facade.UniversityFacade;
import com.facade.UniversityService;
import com.model.CodeSpeciality;
import com.model.Country;
import com.model.EKATTE;
import com.model.EducationDuration;
import com.model.EducationForm;
import com.model.EducationPayment;
import com.model.EducationProfGroup;
import com.model.EducationReasonForAcc;
import com.model.EducationStatus;
import com.model.Faculty;
import com.model.Group;
import com.model.SpecialityNSI;
import com.model.SuClass;
import com.model.University;

@ManagedBean(name = "mesService", eager = true)
@ApplicationScoped
public class MesService {

	private String siteUri;

	// nomenclatures
	private List<Country> countries;
	private List<EKATTE> ekattes;
	private List<University> universities;
	private List<EducationForm> educationForms;
	private List<EducationPayment> educationPayments;
	private List<EducationDuration> educationDurations;
	private List<EducationStatus> educationStatuses;
	private List<EducationProfGroup> educationProfGroups;
	private List<EducationReasonForAcc> educationReasonsForAcc;
	private List<Faculty> groupsPhDFaculties;
	private List<Group> groupsPhDDepartments;
	private List<CodeSpeciality> codeSpecialities;
	private List<SpecialityNSI> specialitiesNSI;
	private List<SuClass> suClasses;

	// converted nomenclatures
	private List<MesObject> mesCountries;
	private List<MesObject> mesEkattes;
	private List<MesObject> mesUniversities;
	private List<MesObject> mesEducationForms;
	private List<MesObject> mesEducationPayments;
	private List<MesObject> mesEducationStatuses;
	private List<MesObject> mesEducationProfGroups;
	private List<MesObject> mesEducationDurations;
	private List<MesObject> mesGroupsPhDFaculties;
	private List<MesObject> mesGroupsPhDDepartments;
	private List<MesObject> mesSpecialities;
	private List<MesObject> mesSpecialitiesNSI;
	private List<MesObject> suClassesConverted;
	private List<MesObject> mesEducationReasonsForAcc;

	@EJB
	private CountryFacade countryFacade;
	private Country country;

	@EJB
	private EKATTEFacade ekatteFacade;
	private EKATTE ekatte;

	@EJB
	private UniversityFacade universityFacade;
	private University university;

	@EJB
	private CodeSpecialityFacade codeSpecialityFacade;
	private CodeSpeciality codeSpeciality;

	@EJB
	private SpecialityNSIFacade specialityNSIFacade;
	private SpecialityNSI specialityNSI;

	@EJB
	private FacultyFacade facultyFacade;
	private Faculty faculty;

	@EJB
	private GroupFacade groupFacade;
	private Group group;

	@EJB
	private EducationDurationFacade educationDurationFacade;
	private EducationDuration educationDuration;

	@EJB
	private EducationFormFacade educationFormFacade;
	private EducationForm educationForm;

	@EJB
	private EducationPaymentFacade educationPaymentFacade;
	private EducationPayment educationPayment;

	@EJB
	private EducationStatusFacade educationStatusFacade;
	private EducationStatus educationStatus;

	@EJB
	private EducationProfGroupFacade educationProfGroupFacade;
	private EducationProfGroup educationProfGroup;

	@EJB
	private SuClassFacade suClassFacade;
	private SuClass suClass;

	@EJB
	private EducationReasonForAccFacade educationReasonForAccFacade;
	private EducationReasonForAcc educationReasonForAcc;

	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) context
			.getExternalContext().getRequest();
	private ResourceBundle bundle = context.getApplication().getResourceBundle(
			context, "msgs");

	public String getCountryNameById(Integer id) {
		try {
			country = countryFacade.getById(id);
			return country.getMesName();
		} catch (Exception e) {
			System.out.println("MesService.getCountryNameById(): "
					+ e.getMessage());
		}
		return "";
	}

	public String getEkatteNameById(Integer id) {
		try {
			ekatte = ekatteFacade.getById(id);
			return bundle.getString("municipalityShort") + " "
					+ ekatte.getMesName() + ", "
					+ bundle.getString("cityShort") + " " + ekatte.getCity()
					+ ", " + bundle.getString("regionShort") + " "
					+ ekatte.getRegion();
		} catch (Exception e) {
			System.out.println("MesService.getCountryNameById(): "
					+ e.getMessage());
		}
		return "";
	}

	public String getUniversityNameById(Integer id) {
		try {
			university = universityFacade.getById(id);
			return university.getMesName();
		} catch (Exception e) {
			System.out.println("getUniversityNameById(): " + e.getMessage());
		}
		return "";
	}

	public String getCodeSpecialityNameById(Integer id) {
		try {
			codeSpeciality = codeSpecialityFacade.getById(id);
			return codeSpeciality.getMesName();
		} catch (Exception e) {
			System.out.println("getSpecialityNameById(): " + e.getMessage());
		}
		return "";
	}

	public String getSpecialityNSINameById(Integer id) {
		try {
			specialityNSI = specialityNSIFacade.getById(id);
			return specialityNSI.getMesName();
		} catch (Exception e) {
			System.out.println("getSpecialityNSINameById(): " + e.getMessage());
		}
		return "";
	}

	public String getFacultyNameById(Integer id) {
		try {
			faculty = facultyFacade.getById(id);
			return faculty.getName();
		} catch (Exception e) {
			System.out.println("getFacultyNameById(): " + e.getMessage());
		}
		return "";
	}

	public String getGroupNameById(Integer id) {
		try {
			group = groupFacade.getById(id);
			return group.getName();
		} catch (Exception e) {
			System.out.println("getGroupNameById(): " + e.getMessage());
		}
		return "";
	}

	public String getEducationDurationNameById(Integer id) {
		try {
			educationDuration = educationDurationFacade.getById(id);
			return educationDuration.getMesName();
		} catch (Exception e) {
			System.out.println("getEducationDurationNameById(): "
					+ e.getMessage());
		}
		return "";
	}

	public String getEducationFormNameById(Integer id) {
		try {
			educationForm = educationFormFacade.getById(id);
			return educationForm.getMesName();
		} catch (Exception e) {
			System.out.println("getEducationFormNameById(): " + e.getMessage());
		}
		return "";
	}

	public String getEducationPaymentNameById(Integer id) {
		try {
			educationPayment = educationPaymentFacade.getById(id);
			return educationPayment.getMesName();
		} catch (Exception e) {
			System.out.println("getEducationPaymentNameById(): "
					+ e.getMessage());
		}
		return "";
	}

	public String getEducationStatusNameById(Integer id) {
		try {
			educationStatus = educationStatusFacade.getById(id);
			return educationStatus.getMesName();
		} catch (Exception e) {
			System.out.println("getEducationStatusNameById(): "
					+ e.getMessage());
		}
		return "";
	}

	public String getEducationProfGroupNameById(Integer id) {
		try {
			educationProfGroup = educationProfGroupFacade.getById(id);
			return educationProfGroup.getMesName();
		} catch (Exception e) {
			System.out.println("getEducationProfGroupNameById(): "
					+ e.getMessage());
		}
		return "";
	}

	public String getSuClassNameById(Integer id) {
		try {
			suClass = suClassFacade.getById(id);
			return suClass.getMesName();
		} catch (Exception e) {
			System.out.println("MesService.getSuClassNameById(" + id + "): "
					+ e.getMessage());
		}
		return "";
	}

	public String getEducationReasonForAccNameById(Integer id) {
		try {
			educationReasonForAcc = educationReasonForAccFacade.getById(id);
			return educationReasonForAcc.getMesName();
		} catch (Exception e) {
			System.out.println("MesService.getEducationReasonForAccNameById(" + id + "): "
					+ e.getMessage());
		}
		return "";
	}

	public List<Country> getCountries() {
		try {
			URL url = new URL(getSiteUri()
					+ "/PhdEJB/CountryService/CountryServiceImp?wsdl");
			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName("http://facade.com/", "CountryService");
			Service service = Service.create(url, qname);
			CountryService country = service.getPort(CountryService.class);
			countries = country.findAllMes();
		} catch (Exception e) {
			System.out
					.println("AdminUniversityEditPhdBean.getCodeCountries(): "
							+ e.getMessage());
		}
		return countries;
	}

	public List<EKATTE> getEkattes() {
		try {
			URL url = new URL(getSiteUri()
					+ "/PhdEJB/EKATTEService/EKATTEServiceImp?wsdl");
			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName("http://facade.com/", "EKATTEService");
			Service service = Service.create(url, qname);
			EKATTEService ekatte = service.getPort(EKATTEService.class);
			ekattes = ekatte.findAllMes();
		} catch (Exception e) {
			System.out.println("AdminUniversityEditPhdBean.getEkattes(): "
					+ e.getMessage());
		}
		return ekattes;
	}

	public List<University> getUniversities() {
		try {
			URL url = new URL(getSiteUri()
					+ "/PhdEJB/UniversityService/UniversityServiceImp?wsdl");
			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName("http://facade.com/", "UniversityService");
			Service service = Service.create(url, qname);
			UniversityService university = service
					.getPort(UniversityService.class);
			universities = university.findAllMes();
		} catch (Exception e) {
			System.out.println("AdminUniversityEditPhdBean.getUniversities(): "
					+ e.getMessage());
		}
		return universities;
	}

	public List<EducationForm> getEducationForms() {
		try {
			URL url = new URL(
					getSiteUri()
							+ "/PhdEJB/EducationFormService/EducationFormServiceImp?wsdl");
			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName("http://facade.com/",
					"EducationFormService");
			Service service = Service.create(url, qname);
			EducationFormService educationForm = service
					.getPort(EducationFormService.class);
			educationForms = educationForm.getAll();
		} catch (Exception e) {
			System.out
					.println("AdminUniversityEditPhdBean.getEducationForms(): "
							+ e.getMessage());
		}
		return educationForms;
	}

	public List<EducationDuration> getEducationDurations() {
		try {
			URL url = new URL(
					getSiteUri()
							+ "/PhdEJB/EducationDurationService/EducationDurationServiceImp?wsdl");
			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName("http://facade.com/",
					"EducationDurationService");
			Service service = Service.create(url, qname);
			EducationDurationService educationDuration = service
					.getPort(EducationDurationService.class);
			educationDurations = educationDuration.getAll();
		} catch (Exception e) {
			System.out
					.println("AdminUniversityEditPhdBean.getEducationDurations(): "
							+ e.getMessage());
		}
		return educationDurations;
	}

	public List<EducationPayment> getEducationPayments() {
		try {
			URL url = new URL(
					getSiteUri()
							+ "/PhdEJB/EducationPaymentService/EducationPaymentServiceImp?wsdl");
			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName("http://facade.com/",
					"EducationPaymentService");
			Service service = Service.create(url, qname);
			EducationPaymentService educationPayment = service
					.getPort(EducationPaymentService.class);
			educationPayments = educationPayment.getAll();
		} catch (Exception e) {
			System.out
					.println("AdminUniversityEditPhdBean.getEducationPayments(): "
							+ e.getMessage());
		}
		return educationPayments;
	}

	public List<EducationStatus> getEducationStatuses() {
		try {
			URL url = new URL(
					getSiteUri()
							+ "/PhdEJB/EducationStatusService/EducationStatusServiceImp?wsdl");
			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName("http://facade.com/",
					"EducationStatusService");
			Service service = Service.create(url, qname);
			EducationStatusService educationStatus = service
					.getPort(EducationStatusService.class);
			educationStatuses = educationStatus.getAll();
		} catch (Exception e) {
			System.out
					.println("AdminUniversityEditPhdBean.getEducationStatuses(): "
							+ e.getMessage());
		}
		return educationStatuses;
	}

	public List<EducationProfGroup> getEducationProfGroups() {
		try {
			URL url = new URL(
					getSiteUri()
							+ "/PhdEJB/EducationProfGroupService/EducationProfGroupServiceImp?wsdl");
			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName("http://facade.com/",
					"EducationProfGroupService");
			Service service = Service.create(url, qname);
			EducationProfGroupService educationProfGroup = service
					.getPort(EducationProfGroupService.class);
			educationProfGroups = educationProfGroup.getAll();
		} catch (Exception e) {
			System.out
					.println("AdminUniversityEditPhdBean.getEducationProfGroups(): "
							+ e.getMessage());
		}
		return educationProfGroups;
	}

	public List<Faculty> getGroupsPhDFaculties() {
		try {
			URL url = new URL(getSiteUri()
					+ "/PhdEJB/FacultyService/FacultyServiceImp?wsdl");
			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName("http://facade.com/", "FacultyService");
			Service service = Service.create(url, qname);
			FacultyService faculty = service.getPort(FacultyService.class);
			groupsPhDFaculties = faculty.getAll();
		} catch (Exception e) {
			System.out
					.println("AdminUniversityEditPhdBean.getEducationPayments(): "
							+ e.getMessage());
		}
		return groupsPhDFaculties;
	}

	public List<Group> getGroupsPhDDepartments() {
		try {
			URL url = new URL(getSiteUri()
					+ "/PhdEJB/GroupService/GroupServiceImp?wsdl");
			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName("http://facade.com/", "GroupService");
			Service service = Service.create(url, qname);
			GroupService group = service.getPort(GroupService.class);
			groupsPhDDepartments = group.getAll();
		} catch (Exception e) {
			System.out
					.println("AdminUniversityEditPhdBean.getGroupsPhDDepartments(): "
							+ e.getMessage());
		}
		return groupsPhDDepartments;
	}

	public List<CodeSpeciality> getCodeSpecialities() {
		try {
			URL url = new URL(getSiteUri()
					+ "/PhdEJB/CodeSpecialityService/CodeSpecialityServiceImp?wsdl");
			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName("http://facade.com/", "CodeSpecialityService");
			Service service = Service.create(url, qname);
			CodeSpecialityService speciality = service
					.getPort(CodeSpecialityService.class);
			codeSpecialities = speciality.getAll();
		} catch (Exception e) {
			System.out.println("AdminUniversityEditPhdBean.getCodeSpecialities(): "
					+ e.getMessage());
		}
		return codeSpecialities;
	}

	public List<SpecialityNSI> getSpecialitiesNSI() {
		try {
			URL url = new URL(
					getSiteUri()
							+ "/PhdEJB/SpecialityNSIService/SpecialityNSIServiceImp?wsdl");
			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName("http://facade.com/",
					"SpecialityNSIService");
			Service service = Service.create(url, qname);
			SpecialityNSIService specialityNSI = service
					.getPort(SpecialityNSIService.class);
			specialitiesNSI = specialityNSI.getAll();
		} catch (Exception e) {
			System.out
					.println("AdminUniversityEditPhdBean.getSpecialitiesNSI(): "
							+ e.getMessage());
		}
		return specialitiesNSI;
	}

	public List<SuClass> getSuClasses() {
		try {
			URL url = new URL(
					getSiteUri()
							+ "/PhdEJB/SuClassService/SuClassServiceImp?wsdl");
			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName("http://facade.com/",
					"SuClassService");
			Service service = Service.create(url, qname);
			SuClassService suClass = service
					.getPort(SuClassService.class);
			suClasses = suClass.getAll();
		} catch (Exception e) {
			System.out
					.println("MesService.getSuClasses(): "
							+ e.getMessage());
		}
		return suClasses;
	}

	public List<EducationReasonForAcc> getEducationReasonsForAcc() {
		try {
			URL url = new URL(
					getSiteUri()
							+ "/PhdEJB/EducationReasonForAccService/EducationReasonForAccServiceImp?wsdl");
			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName("http://facade.com/",
					"EducationReasonForAccService");
			Service service = Service.create(url, qname);
			EducationReasonForAccService reasonsForAcc = service
					.getPort(EducationReasonForAccService.class);
			educationReasonsForAcc = reasonsForAcc.getAll();
		} catch (Exception e) {
			System.out
					.println("MesService.getEducationReasonsForAcc(): "
							+ e.getMessage());
		}
		return educationReasonsForAcc;
	}

	public List<MesObject> getMesCountries() {
		mesCountries = new ArrayList<MesObject>();
		for (Country country : getCountries()) {
			mesCountries.add(new MesObject(country.getId(), country.getMesId(),
					country.getMesName()));
		}
		return mesCountries;
	}

	public List<MesObject> getMesEkattes() {
		mesEkattes = new ArrayList<MesObject>();
		for (EKATTE ekatte : getEkattes()) {
			try {
				mesEkattes.add(new MesObject(ekatte.getId(), ekatte.getMesId(),
						ekatte.getMesName() + ", гр. " + ekatte.getCity()
								+ ", обл. " + ekatte.getRegion()));
			} catch (Exception e) {
				System.out.println("MesService.init.mesEkattes: "
						+ e.getMessage());
			}
		}
		return mesEkattes;
	}

	public List<MesObject> getMesUniversities() {
		mesUniversities = new ArrayList<MesObject>();
		for (University university : getUniversities()) {
			try {
				mesUniversities.add(new MesObject(university.getId(),
						university.getMesId(), university.getMesName()));
			} catch (Exception e) {
				System.out.println("MesService.init.mesUniversities: "
						+ e.getMessage());
			}
		}
		return mesUniversities;
	}

	public List<MesObject> getMesEducationForms() {
		mesEducationForms = new ArrayList<MesObject>();
		for (EducationForm educationForm : getEducationForms()) {
			try {
				mesEducationForms.add(new MesObject(educationForm.getId(),
						educationForm.getMesId(), educationForm.getMesName()));
			} catch (Exception e) {
				System.out.println("MesService.init.mesEducationForms: "
						+ e.getMessage());
			}
		}
		return mesEducationForms;
	}

	public List<MesObject> getMesEducationDurations() {
		mesEducationDurations = new ArrayList<MesObject>();
		for (EducationDuration educationDuration : getEducationDurations()) {
			try {
				mesEducationDurations.add(new MesObject(educationDuration
						.getId(), educationDuration.getMesId(),
						educationDuration.getMesName()));
			} catch (Exception e) {
				System.out.println("MesService.init.mesEducationDurations: "
						+ e.getMessage());
			}
		}
		return mesEducationDurations;
	}

	public List<MesObject> getMesEducationPayments() {
		mesEducationPayments = new ArrayList<MesObject>();
		for (EducationPayment educationPayment : getEducationPayments()) {
			try {
				mesEducationPayments.add(new MesObject(
						educationPayment.getId(), educationPayment.getMesId(),
						educationPayment.getMesName()));
			} catch (Exception e) {
				System.out.println("MesService.init.mesEducationPayments: "
						+ e.getMessage());
			}
		}
		return mesEducationPayments;
	}

	public List<MesObject> getMesGroupsPhDFaculties() {
		mesGroupsPhDFaculties = new ArrayList<MesObject>();
		for (Faculty faculty : getGroupsPhDFaculties()) {
			try {
				mesGroupsPhDFaculties.add(new MesObject(faculty.getId(),
						faculty.getId(), faculty.getName()));
			} catch (Exception e) {
				System.out.println("MesService.init.mesGroupsPhDFaculties: "
						+ e.getMessage());
			}
		}
		return mesGroupsPhDFaculties;
	}

	public List<MesObject> getMesGroupsPhDDepartments() {
		mesGroupsPhDDepartments = new ArrayList<MesObject>();
		for (Group group : getGroupsPhDDepartments()) {
			try {
				mesGroupsPhDDepartments.add(new MesObject(group.getId(), group
						.getId(), group.getName()));
			} catch (Exception e) {
				System.out.println("MesService.init.mesGroupsPhDDepartments: "
						+ e.getMessage());
			}
		}
		return mesGroupsPhDDepartments;
	}

	public List<MesObject> getMesCodeSpecialities() {
		mesSpecialities = new ArrayList<MesObject>();
		for (CodeSpeciality codeSpeciality : getCodeSpecialities()) {
			try {
				mesSpecialities.add(new MesObject(codeSpeciality.getId(),
						codeSpeciality.getMesId(), codeSpeciality.getMesName()));
			} catch (Exception e) {
				System.out.println("MesService.init.mesSpecialities: "
						+ e.getMessage());
			}
		}
		return mesSpecialities;
	}

	public List<MesObject> getMesSpecialitiesNSI() {
		mesSpecialitiesNSI = new ArrayList<MesObject>();
		for (SpecialityNSI specialityNSI : getSpecialitiesNSI()) {
			try {
				mesSpecialitiesNSI.add(new MesObject(specialityNSI.getId(),
						specialityNSI.getMesId(), specialityNSI.getMesName()));
			} catch (Exception e) {
				System.out.println("MesService.init.mesSpecialitiesNSI: "
						+ e.getMessage());
			}
		}
		return mesSpecialitiesNSI;
	}

	public List<MesObject> getMesEducationStatuses() {
		mesEducationStatuses = new ArrayList<MesObject>();
		for (EducationStatus educationStatus : getEducationStatuses()) {
			try {
				mesEducationStatuses.add(new MesObject(educationStatus.getId(),
						educationStatus.getMesId(), educationStatus
								.getMesName()));
			} catch (Exception e) {
				System.out.println("MesService.getMesEducationStatuses: "
						+ e.getMessage());
			}
		}
		return mesEducationStatuses;
	}

	public List<MesObject> getMesEducationProfGroups() {
		mesEducationProfGroups = new ArrayList<MesObject>();
		for (EducationProfGroup educationProfGroup : getEducationProfGroups()) {
			try {
				mesEducationProfGroups.add(new MesObject(educationProfGroup
						.getId(), educationProfGroup.getMesId(),
						educationProfGroup.getMesName()));
			} catch (Exception e) {
				System.out.println("MesService.getMesEducationProfGroups: "
						+ e.getMessage());
			}
		}
		return mesEducationProfGroups;
	}

	public List<MesObject> getSuClassesConverted() {
		suClassesConverted = new ArrayList<MesObject>();
		for (SuClass suClass : getSuClasses()) {
			try {
				suClassesConverted.add(new MesObject(suClass
						.getId(), suClass.getMesId(),
						suClass.getMesName()));
			} catch (Exception e) {
				System.out.println("MesService.getSuClassesConverted: "
						+ e.getMessage());
			}
		}
		return suClassesConverted;
	}

	public List<MesObject> getMesEducationReasonsForAcc() {
		mesEducationReasonsForAcc = new ArrayList<MesObject>();
		for (EducationReasonForAcc educationReasnosForAcc : getEducationReasonsForAcc()) {
			try {
				mesEducationReasonsForAcc.add(new MesObject(educationReasnosForAcc
						.getId(), educationReasnosForAcc.getMesId(),
						educationReasnosForAcc.getMesName()));
			} catch (Exception e) {
				System.out.println("MesService.getMesEducationReasnosForAcc: "
						+ e.getMessage());
			}
		}
		return mesEducationReasonsForAcc;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public void setEkattes(List<EKATTE> ekattes) {
		this.ekattes = ekattes;
	}

	public void setUniversities(List<University> universities) {
		this.universities = universities;
	}

	public void setEducationForms(List<EducationForm> educationForms) {
		this.educationForms = educationForms;
	}

	public void setEducationDurations(List<EducationDuration> educationDurations) {
		this.educationDurations = educationDurations;
	}

	public void setEducationPayments(List<EducationPayment> educationPayments) {
		this.educationPayments = educationPayments;
	}

	public void setGroupsPhDFaculties(List<Faculty> groupsPhDFaculties) {
		this.groupsPhDFaculties = groupsPhDFaculties;
	}

	public void setGroupsPhDDepartments(List<Group> groupsPhDDepartments) {
		this.groupsPhDDepartments = groupsPhDDepartments;
	}

	public void setCodeSpecialities(List<CodeSpeciality> codeSpecialities) {
		this.codeSpecialities = codeSpecialities;
	}

	public void setSpecialitiesNSI(List<SpecialityNSI> specialitiesNSI) {
		this.specialitiesNSI = specialitiesNSI;
	}

	public void setMesCountries(List<MesObject> mesCountries) {
		this.mesCountries = mesCountries;
	}

	public void setMesEkattes(List<MesObject> mesEkattes) {
		this.mesEkattes = mesEkattes;
	}

	public void setMesUniversities(List<MesObject> mesUniversities) {
		this.mesUniversities = mesUniversities;
	}

	public void setMesEducationForms(List<MesObject> mesEducationForms) {
		this.mesEducationForms = mesEducationForms;
	}

	public void setMesEducationDurations(List<MesObject> mesEducationDurations) {
		this.mesEducationDurations = mesEducationDurations;
	}

	public void setMesEducationPayments(List<MesObject> mesEducationPayments) {
		this.mesEducationPayments = mesEducationPayments;
	}

	public void setMesGroupsPhDFaculties(List<MesObject> mesGroupsPhDFaculties) {
		this.mesGroupsPhDFaculties = mesGroupsPhDFaculties;
	}

	public void setMesGroupsPhDDepartments(
			List<MesObject> mesGroupsPhDDepartments) {
		this.mesGroupsPhDDepartments = mesGroupsPhDDepartments;
	}

	public void setMesSpecialities(List<MesObject> mesSpecialities) {
		this.mesSpecialities = mesSpecialities;
	}

	public void setMesSpecialitiesNSI(List<MesObject> mesSpecialitiesNSI) {
		this.mesSpecialitiesNSI = mesSpecialitiesNSI;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public EKATTE getEkatte() {
		return ekatte;
	}

	public void setEkatte(EKATTE ekatte) {
		this.ekatte = ekatte;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public CodeSpeciality getCodeSpeciality() {
		return codeSpeciality;
	}

	public void setSpeciality(CodeSpeciality speciality) {
		this.codeSpeciality = speciality;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public SpecialityNSI getSpecialityNSI() {
		return specialityNSI;
	}

	public void setSpecialityNSI(SpecialityNSI specialityNSI) {
		this.specialityNSI = specialityNSI;
	}

	public EducationDuration getEducationDuration() {
		return educationDuration;
	}

	public void setEducationDuration(EducationDuration duration) {
		this.educationDuration = duration;
	}

	public String getSiteUri() {
		try {
			InitialContext ic = new InitialContext();
			siteUri = (String) ic.lookup("java:comp/env/siteUri");
		} catch (NamingException e) {
			System.err.println("MesService.getSiteUri: " + e.getMessage());
		}
		return siteUri;
	}

	public void setSiteUri(String siteUri) {
		this.siteUri = siteUri;
	}

	public void setSuClasses(List<SuClass> suClasses) {
		this.suClasses = suClasses;
	}

	public void setEducationReasonsForAcc(
			List<EducationReasonForAcc> educationReasonsForAcc) {
		this.educationReasonsForAcc = educationReasonsForAcc;
	}
}