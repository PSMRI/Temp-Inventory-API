package com.iemr.inventory.controller.visit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.inventory.data.visit.BenVisitDetail;
import com.iemr.inventory.data.visit.BeneficiaryModel;
import com.iemr.inventory.service.visit.VisitService;
import com.iemr.inventory.utils.response.OutputResponse;

@RestController
public class VisitController {

	@Autowired
	VisitService visitService;

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@CrossOrigin()
	@RequestMapping(value = "/getVisitFromBenID", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getVisitFromBenRegID(@RequestBody BenVisitDetail benVisitDetail, HttpServletRequest httpRequest) {
		OutputResponse response = new OutputResponse();
		String auth = httpRequest.getHeader("authorization");

		try {
			logger.info("getVisitFromBenRegID recieved object " + benVisitDetail.toString());
			BenVisitDetail newbenVisitDetail = new BenVisitDetail();
			newbenVisitDetail.setBeneficiaryID(benVisitDetail.getBeneficiaryID());
			BeneficiaryModel saveData = visitService.getVisitDetail(newbenVisitDetail.toString(),
					benVisitDetail.getProviderServiceMapID(), auth);

			response.setResponse(saveData.toString());

		} catch (Exception e) {			
			response.setError(e);
			logger.error("Error in getVisitFromBenID", e.getMessage());

		}
		/**
		 * sending the response...
		 */
		return response.toString();

	}

	@CrossOrigin()
	@RequestMapping(value = "/getVisitFromAdvanceSearch", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getVisitFromAdvanceSearch(@RequestBody String model, HttpServletRequest httpRequest) {

		OutputResponse response = new OutputResponse();
		String auth = httpRequest.getHeader("authorization");

		try {

			List<BeneficiaryModel> saveData = visitService.getVisitFromAdvanceSearch(model, auth);
			response.setResponse(saveData.toString());

		} catch (Exception e) {
			response.setError(e);
			logger.error("Error in getVisitFromBenID",e.getMessage());

		}
		/**
		 * sending the response...
		 */
		return response.toString();

	}
}
