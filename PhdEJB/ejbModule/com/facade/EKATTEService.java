package com.facade;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.model.EKATTE;

@WebService
@SOAPBinding(style = Style.RPC)
public interface EKATTEService {
	
	@WebMethod(operationName = "getById")
	public EKATTE getById(Integer id);
	
	@WebMethod(operationName = "getEKATTEs")
	public List<EKATTE> findAllMes();

	@WebMethod(operationName = "getAllSortByMesName")
	public List<EKATTE> getAllSortByMesName();
	
	@WebMethod(operationName = "getAllSortByMunicipality")
	public List<EKATTE> getAllSortByMunicipality();

}
