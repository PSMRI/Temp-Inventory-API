package com.iemr.inventory.controller.pharmacologicalcategory;

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

import com.iemr.inventory.data.pharmacologicalcategory.M_Pharmacologicalcategory;
import com.iemr.inventory.service.pharmacologicalcategory.PharmacologicalcategoryInter;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

@RestController
public class PharmacologicalcategoryController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	private PharmacologicalcategoryInter pharmacologicalcategoryInter;
	
	
	
	@CrossOrigin()
	@RequestMapping(value =  "/createPharmacologicalcategory" ,headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String createPharmacologicalcategory(@RequestBody String createPharmacologicalcategory) {
		
		OutputResponse response = new OutputResponse();

		try {

			M_Pharmacologicalcategory[] Pharmacologicalcategory = InputMapper.gson().fromJson(createPharmacologicalcategory,
					M_Pharmacologicalcategory[].class);
			List<M_Pharmacologicalcategory> createPharmacologicaldata = Arrays.asList(Pharmacologicalcategory);
			
			ArrayList<M_Pharmacologicalcategory> saveData=pharmacologicalcategoryInter.createPharmacologicalcategory(createPharmacologicaldata);
			
			response.setResponse(saveData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		/**
		 * sending the response...
		 */
		return response.toString();

	}
	
	
	
	@CrossOrigin()
	@RequestMapping(value =  "/getPharmacologicalcategory" ,headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String getPharmacologicalcategory(@RequestBody String createPharmacologicalcategory) {
		
		OutputResponse response = new OutputResponse();

		try {

			M_Pharmacologicalcategory Pharmacologicalcategory = InputMapper.gson().fromJson(createPharmacologicalcategory,
					M_Pharmacologicalcategory.class);
			
			ArrayList<M_Pharmacologicalcategory> saveData=pharmacologicalcategoryInter.getPharmacologicalcategory(Pharmacologicalcategory.getProviderServiceMapID());
			
			
			response.setResponse(saveData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		/**
		 * sending the response...
		 */
		return response.toString();

	}
	
	
	
	@CrossOrigin()
	@RequestMapping(value =  "/editPharmacologicalcategory" ,headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String editPharmacologicalcategory(@RequestBody String editPharmacologicalcategory) {
		
		
		OutputResponse response = new OutputResponse();

		try {

			M_Pharmacologicalcategory Pharmacologicalcategory = InputMapper.gson().fromJson(editPharmacologicalcategory,
					M_Pharmacologicalcategory.class);
			
			
			M_Pharmacologicalcategory saveData=pharmacologicalcategoryInter.editPharmacologicalcategory(Pharmacologicalcategory.getPharmCategoryID());
			

			saveData.setPharmCategoryDesc(Pharmacologicalcategory.getPharmCategoryDesc());

			saveData.setModifiedBy(Pharmacologicalcategory.getModifiedBy());
			M_Pharmacologicalcategory saveEditedData=pharmacologicalcategoryInter.saveEditedPharData(saveData);
			
			
			response.setResponse(saveData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		/**
		 * sending the response...
		 */
		return response.toString();

	}
	
	
	
	@CrossOrigin()
	@RequestMapping(value =  "/deletePharmacologicalcategory" ,headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String deletePharmacologicalcategory(@RequestBody String deletePharmacologicalcategory) {
		
		OutputResponse response = new OutputResponse();

		try {

			M_Pharmacologicalcategory Pharmacologicalcategory = InputMapper.gson().fromJson(deletePharmacologicalcategory,
					M_Pharmacologicalcategory.class);
			
			M_Pharmacologicalcategory saveData=pharmacologicalcategoryInter.editPharmacologicalcategory(Pharmacologicalcategory.getPharmCategoryID());
			
			saveData.setDeleted(Pharmacologicalcategory.getDeleted());
		
			M_Pharmacologicalcategory saveEditedData=pharmacologicalcategoryInter.saveEditedPharData(saveData);
			
			
			response.setResponse(saveData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		/**
		 * sending the response...
		 */
		return response.toString();

	}
	
	

}
