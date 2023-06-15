package com.iemr.inventory.controller.facilitytype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.inventory.data.facilitytype.M_facilitytype;
import com.iemr.inventory.service.facilitytype.M_facilitytypeInter;

import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

@RestController
public class FacilitytypeController {
	
	@Autowired
	private M_facilitytypeInter m_facilitytypeInter;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@CrossOrigin()
	@RequestMapping(value ="/getFacility", headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String getFacility(@RequestBody String getFacility) {
	
	OutputResponse response = new OutputResponse();

	try {

		M_facilitytype facilityDetails = InputMapper.gson().fromJson(getFacility,
				M_facilitytype.class);
		
	  ArrayList<M_facilitytype> allFacilityData=m_facilitytypeInter.getAllFicilityData(facilityDetails.getProviderServiceMapID());
	
	  
	  response.setResponse(allFacilityData.toString());
	
	
	}catch (Exception e) {
		logger.error(e.getMessage());
			response.setError(e);

		}
		/**
		 * sending the response...
		 */
		return response.toString();
	}
	
	@CrossOrigin()
	@RequestMapping(value ="/addFacility", headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String addFacility(@RequestBody String addFacility) {
	
	OutputResponse response = new OutputResponse();

	try {

		M_facilitytype[] facilityDetails = InputMapper.gson().fromJson(addFacility,
				M_facilitytype[].class);
		List<M_facilitytype> addfacilityDetails = Arrays.asList(facilityDetails);
		
	  ArrayList<M_facilitytype> allFacilityData=m_facilitytypeInter.addAllFicilityData(addfacilityDetails);
	
	  response.setResponse(allFacilityData.toString());
	
	}catch (Exception e) {
		logger.error(e.getMessage());
			response.setError(e);

		}
		/**
		 * sending the response...
		 */
		return response.toString();
	}
	
	
	
	@CrossOrigin()
	@RequestMapping(value ="/editFacility", headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String editFacility(@RequestBody String editFacility) {
	
	OutputResponse response = new OutputResponse();

	try {

		M_facilitytype facilityDetails = InputMapper.gson().fromJson(editFacility,
				M_facilitytype.class);
		
	  M_facilitytype allFacilityData=m_facilitytypeInter.editAllFicilityData(facilityDetails.getFacilityTypeID());
	  
	  allFacilityData.setFacilityTypeName(facilityDetails.getFacilityTypeName());
	  allFacilityData.setFacilityTypeDesc(facilityDetails.getFacilityTypeDesc());
	  allFacilityData.setFacilityTypeCode(facilityDetails.getFacilityTypeCode());
	  allFacilityData.setModifiedBy(facilityDetails.getModifiedBy());
	  
	  
	  M_facilitytype saveFacilityData=m_facilitytypeInter.updateFacilityData(allFacilityData);
	  
	
	  response.setResponse(saveFacilityData.toString());
	
	}catch (Exception e) {
		logger.error(e.getMessage());
			response.setError(e);

		}
		/**
		 * sending the response...
		 */
		return response.toString();
	}
	
	
	
	@CrossOrigin()
	@RequestMapping(value ="/deleteFacility", headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String deleteFacility(@RequestBody String deleteFacility) {
	
	OutputResponse response = new OutputResponse();

	try {

		M_facilitytype facilityDetails = InputMapper.gson().fromJson(deleteFacility,
				M_facilitytype.class);
		
	  M_facilitytype allFacilityData=m_facilitytypeInter.editAllFicilityData(facilityDetails.getFacilityTypeID());
	  allFacilityData.setDeleted(facilityDetails.getDeleted());
	  
	  
	  M_facilitytype saveFacilityData=m_facilitytypeInter.updateFacilityData(allFacilityData);
	  
	  response.setResponse(saveFacilityData.toString());
	  
	}catch (Exception e) {
		logger.error(e.getMessage());
			response.setError(e);

		}
		/**
		 * sending the response...
		 */
		return response.toString();
	}
}
