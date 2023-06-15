package com.iemr.inventory.controller.uom;

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

import com.iemr.inventory.data.uom.M_Uom;
import com.iemr.inventory.service.uom.UomInter;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

@RestController
public class UomController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private UomInter uomInter;
	
	
	@CrossOrigin()
	@RequestMapping(value =  "/createUom" ,headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String createUom(@RequestBody String createUom) {
		
		OutputResponse response = new OutputResponse();

		try {

			M_Uom[] UomData = InputMapper.gson().fromJson(createUom,
					M_Uom[].class);
		      List<M_Uom> saveUomData = Arrays.asList(UomData);
			
			ArrayList<M_Uom> saveData=uomInter.createDrugtypeData(saveUomData);
			
			
			
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
	@RequestMapping(value =  "/getUom" ,headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String getUom(@RequestBody String getUom) {
		
		OutputResponse response = new OutputResponse();

		try {

			M_Uom UomData = InputMapper.gson().fromJson(getUom,
					M_Uom.class);
			
			ArrayList<M_Uom> getData=uomInter.createDrugtypeData(UomData.getProviderServiceMapID());
			
			
			
			response.setResponse(getData.toString());

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
	@RequestMapping(value =  "/editUom" ,headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String editUom(@RequestBody String editUom) {
		
		OutputResponse response = new OutputResponse();

		try {

			M_Uom UomData = InputMapper.gson().fromJson(editUom,
					M_Uom.class);
			
			M_Uom geteditedData=uomInter.editDrugtypeData(UomData.getuOMID());
			geteditedData.setuOMName(UomData.getuOMName());
			geteditedData.setuOMDesc(UomData.getuOMDesc());
			geteditedData.setuOMCode(UomData.getuOMCode());
			geteditedData.setStatus(UomData.getStatus());
			geteditedData.setModifiedBy(UomData.getModifiedBy());
			
			M_Uom editedData=uomInter.saveeditedData(geteditedData);
			
			
			
			
			response.setResponse(editedData.toString());

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
	@RequestMapping(value =  "/deleteUom" ,headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String deleteUom(@RequestBody String editUom) {
		
		OutputResponse response = new OutputResponse();

		try {

			M_Uom UomData = InputMapper.gson().fromJson(editUom,
					M_Uom.class);
			
			M_Uom geteditedData=uomInter.editDrugtypeData(UomData.getuOMID());
			geteditedData.setDeleted(UomData.getDeleted());
			
			M_Uom deletedData=uomInter.saveeditedData(geteditedData);
			
			
			
			
			response.setResponse(deletedData.toString());

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
