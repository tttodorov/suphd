package com.facade;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import com.model.Country;

@WebService
@SOAPBinding(style = Style.RPC, use=Use.LITERAL)
public interface CountryService {

	@WebMethod(operationName = "getById")
	public Country getById(Integer id);

	@WebMethod(operationName = "getByCountryName")
	public Country getByCountryName(String countryName);
	
	@WebMethod(operationName = "getCountries")
	public List<Country> findAllMes();

	@WebMethod(operationName = "getAllSortByMesName")
	public List<Country> getAllSortByMesName();

}