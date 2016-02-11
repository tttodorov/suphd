package com.converter;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.facade.CodeSpecialityService;
import com.facade.CountryService;
import com.facade.EKATTEService;
import com.facade.EducationDurationFacade;
import com.facade.EducationFormFacade;
import com.facade.EducationPaymentFacade;
import com.facade.EducationProfGroupService;
import com.facade.GroupFacade;
import com.facade.SpecialityNSIFacade;
import com.facade.UniversityService;
import com.model.CodeSpeciality;
import com.model.Country;
import com.model.EKATTE;
import com.model.EducationDuration;
import com.model.EducationForm;
import com.model.EducationPayment;
import com.model.EducationProfGroup;
import com.model.Group;
import com.model.SpecialityNSI;
import com.model.University;

public class MesObject {

	private Integer id;
	private Integer mesId;
	private String mesName;

	// nomenclature services
	private EKATTEService ekatteFacade;
	private CountryService countryService;
	
	public MesObject() {
		this.id = 0;
		this.mesId = 0;
		this.mesName = null;
	}
	
	public MesObject(Integer id, String nomenclatureName) {
		this.id = id;
		if(nomenclatureName == "Country") {
			Country country = new Country();
			country = countryService.getById(id);
			setMesId(country.getId());
			setMesName(country.getMesName());
		}
		if(nomenclatureName == "EducationDuration") {
			EducationDuration educationDuration = new EducationDuration();
			try {
				URL url = new URL(
						"http://localhost:8080/PhdEJB/EducationDurationFacade/EducationDurationFacadeImp?wsdl");
				// 1st argument service URI, refer to wsdl document above
				// 2nd argument is service name, refer to wsdl document above
				QName qname = new QName("http://facade.com/", "EducationDurationFacade");
				Service service = Service.create(url, qname);
				EducationDurationFacade educationDurationFacade = service.getPort(EducationDurationFacade.class);
				educationDuration = educationDurationFacade.getById(id);
			} catch (Exception e) {
				System.out
						.println("AdminUniversityEditPhdBean.getCodeCountries(): "
								+ e.getMessage());
			}
			setMesId(educationDuration.getId());
			setMesName(educationDuration.getMesName());
		}
		if(nomenclatureName == "EducationForm") {
			EducationForm educationForm = new EducationForm();
			try {
				URL url = new URL(
						"http://localhost:8080/PhdEJB/educationFormFacade/educationFormFacadeImp?wsdl");
				// 1st argument service URI, refer to wsdl document above
				// 2nd argument is service name, refer to wsdl document above
				QName qname = new QName("http://facade.com/", "educationFormFacade");
				Service service = Service.create(url, qname);
				EducationFormFacade educationFormFacade = service.getPort(EducationFormFacade.class);
				educationForm = educationFormFacade.getById(id);
			} catch (Exception e) {
				System.out
						.println("AdminUniversityEditPhdBean.getCodeCountries(): "
								+ e.getMessage());
			}
			setMesId(educationForm.getId());
			setMesName(educationForm.getMesName());
		}
		if(nomenclatureName == "EducationPayment") {
			EducationPayment educationPayment = new EducationPayment();
			try {
				URL url = new URL(
						"http://localhost:8080/PhdEJB/EducationPaymentFacade/EducationPaymentFacadeImp?wsdl");
				// 1st argument service URI, refer to wsdl document above
				// 2nd argument is service name, refer to wsdl document above
				QName qname = new QName("http://facade.com/", "EducationPaymentFacade");
				Service service = Service.create(url, qname);
				EducationPaymentFacade educationPaymentFacade = service.getPort(EducationPaymentFacade.class);
				educationPayment = educationPaymentFacade.getById(id);
			} catch (Exception e) {
				System.out
						.println("AdminUniversityEditPhdBean.getCodeCountries(): "
								+ e.getMessage());
			}
			setMesId(educationPayment.getId());
			setMesName(educationPayment.getMesName());
		}
		if(nomenclatureName == "EKATTE") {
			EKATTE ekatte = new EKATTE();
			ekatte = ekatteFacade.getById(id);
			setMesId(ekatte.getId());
			setMesName(ekatte.getCity());
		}
		if(nomenclatureName == "Group") {
			Group group = new Group();
			try {
				URL url = new URL(
						"http://localhost:8080/PhdEJB/GroupFacade/GroupFacadeImp?wsdl");
				// 1st argument service URI, refer to wsdl document above
				// 2nd argument is service name, refer to wsdl document above
				QName qname = new QName("http://facade.com/", "GroupFacade");
				Service service = Service.create(url, qname);
				GroupFacade groupFacade = service.getPort(GroupFacade.class);
				group = groupFacade.getById(id);
			} catch (Exception e) {
				System.out
						.println("AdminUniversityEditPhdBean.getCodeCountries(): "
								+ e.getMessage());
			}
			setMesId(group.getId());
			setMesName(group.getName());
		}
		if(nomenclatureName == "CodeSpeciality") {
			CodeSpeciality codeSpeciality = new CodeSpeciality();
			try {
				URL url = new URL(
						"http://localhost:8080/PhdEJB/CodeSpecialityService/CodeSpecialityFacadeImp?wsdl");
				// 1st argument service URI, refer to wsdl document above
				// 2nd argument is service name, refer to wsdl document above
				QName qname = new QName("http://facade.com/", "CodeSpecialityService");
				Service service = Service.create(url, qname);
				CodeSpecialityService codeSpecialityService = service.getPort(CodeSpecialityService.class);
				codeSpeciality = codeSpecialityService.getById(id);
			} catch (Exception e) {
				System.out
						.println("AdminUniversityEditPhdBean.getCodeSpecialities(): "
								+ e.getMessage());
			}
			setMesId(codeSpeciality.getId());
			setMesName(codeSpeciality.getMesName());
		}
		if(nomenclatureName == "EducationProfGroup") {
			EducationProfGroup educationProfGroup = new EducationProfGroup();
			try {
				URL url = new URL(
						"http://localhost:8080/PhdEJB/EducationProfGroupService/EducationProfGroupFacadeImp?wsdl");
				// 1st argument service URI, refer to wsdl document above
				// 2nd argument is service name, refer to wsdl document above
				QName qname = new QName("http://facade.com/", "EducationProfGroupService");
				Service service = Service.create(url, qname);
				EducationProfGroupService educationProfGroupService = service.getPort(EducationProfGroupService.class);
				educationProfGroup = educationProfGroupService.getById(id);
			} catch (Exception e) {
				System.out
						.println("AdminUniversityEditPhdBean.getEducationProfGroups(): "
								+ e.getMessage());
			}
			setMesId(educationProfGroup.getId());
			setMesName(educationProfGroup.getMesName());
		}
		if(nomenclatureName == "SpecialityNSI") {
			SpecialityNSI specialityNSI = new SpecialityNSI();
			try {
				URL url = new URL(
						"http://localhost:8080/PhdEJB/SpecialityNSIFacade/SpecialityNSIFacadeImp?wsdl");
				// 1st argument service URI, refer to wsdl document above
				// 2nd argument is service name, refer to wsdl document above
				QName qname = new QName("http://facade.com/", "SpecialityNSIFacade");
				Service service = Service.create(url, qname);
				SpecialityNSIFacade specialityNSIFacade = service.getPort(SpecialityNSIFacade.class);
				specialityNSI = specialityNSIFacade.getById(id);
			} catch (Exception e) {
				System.out
						.println("AdminUniversityEditPhdBean.getCodeCountries(): "
								+ e.getMessage());
			}
			setMesId(specialityNSI.getId());
			setMesName(specialityNSI.getMesName());
		}
		if(nomenclatureName == "University") {
			University university = new University();
			try {
				URL url = new URL(
						"http://localhost:8080/PhdEJB/UniversityService/UniversityServiceImp?wsdl");
				// 1st argument service URI, refer to wsdl document above
				// 2nd argument is service name, refer to wsdl document above
				QName qname = new QName("http://facade.com/", "UniversityService");
				Service service = Service.create(url, qname);
				UniversityService universityService = service.getPort(UniversityService.class);
				university = universityService.getById(id);
			} catch (Exception e) {
				System.out
						.println("AdminUniversityEditPhdBean.getCodeCountries(): "
								+ e.getMessage());
			}
			setMesId(university.getId());
			setMesName(university.getMesName());
		}
	}

	public MesObject(Integer id, Integer mesId, String mesName) {
		this.id = id;
		this.mesId = mesId;
		this.mesName = mesName;
	}

	@Override
	public String toString() {
		return String.format("id: %d, mesId: %d, mesName: %s", getId(), getMesId(), getMesName());
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMesId() {
		return mesId;
	}

	public void setMesId(Integer EKATTEId) {
		this.mesId = EKATTEId;
	}

	public String getMesName() {
		return mesName;
	}

	public void setMesName(String displayName) {
		this.mesName = displayName;
	}

	public EKATTEService getEkatteFacade() {
		return ekatteFacade;
	}

	public void setEkatteFacade(EKATTEService ekatteFacade) {
		this.ekatteFacade = ekatteFacade;
	}

	public CountryService getCountryFacade() {
		return countryService;
	}

	public void setCountryFacade(CountryService countryService) {
		this.countryService = countryService;
	}
}