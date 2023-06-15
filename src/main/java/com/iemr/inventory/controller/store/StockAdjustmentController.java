package com.iemr.inventory.controller.store;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.inventory.data.stockadjustment.StockAdjustment;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentDraft;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.service.stockadjustment.StockAdjustmentServiceImpl;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

@RestController
public class StockAdjustmentController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	StockAdjustmentServiceImpl stockAdjustmentServiceImpl;

	@CrossOrigin()
	@RequestMapping(value = "/stockadjustmentdraft", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String stockadjustmentdraft( @RequestBody String store) {

		OutputResponse response = new OutputResponse();
		try {

			String saveData = "Invalid Store Type";


			
			StockAdjustmentDraft stockAdjustmentDraft = InputMapper.gson().fromJson(store, StockAdjustmentDraft.class);
				
			saveData = stockAdjustmentServiceImpl.saveDraft(stockAdjustmentDraft).toString();
			

			response.setResponse(saveData);

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
	@RequestMapping(value = "/getstockadjustmentdraftTransaction", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getstockadjustmentdraftTransaction( @RequestBody ItemStockEntryinput itemStockinput) {

		OutputResponse response = new OutputResponse();
		try {

				
			List<StockAdjustmentDraft> saveData = stockAdjustmentServiceImpl.getStockAjustmentDraftTransaction(itemStockinput);

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
	@RequestMapping(value = "/getforEditsStockAdjustmentdraftTransaction", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getforEditsSockAdjustmentdraftTransaction( @RequestBody ItemStockEntryinput itemStockinput) {

		OutputResponse response = new OutputResponse();
		try {
			
			StockAdjustmentDraft saveData = stockAdjustmentServiceImpl.getforeditStockAjustmentDraftTransaction(itemStockinput.getStockAdjustmentDraftID());

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
	@RequestMapping(value = "/stockadjustment", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String stockadjustment( @RequestBody StockAdjustment StockAdjustment) {

		OutputResponse response = new OutputResponse();
		try {

			String saveData = "Invalid Store Type";

			saveData = stockAdjustmentServiceImpl.savetransaction(StockAdjustment).toString();
			

			response.setResponse(saveData);

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
	@RequestMapping(value = "/getStockAdjustmentTransaction", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getforeditStockAdjustmentTransaction( @RequestBody ItemStockEntryinput itemStockinput) {

		OutputResponse response = new OutputResponse();
		try {
		
			List<StockAdjustment> saveData = stockAdjustmentServiceImpl.getStockAjustmentTransaction(itemStockinput);

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
	@RequestMapping(value = "/getforEditsStockAdjustmentTransaction", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getforEditsStockAdjustmentTransaction( @RequestBody ItemStockEntryinput itemStockinput) {

		OutputResponse response = new OutputResponse();
		try {


			
			StockAdjustment saveData = stockAdjustmentServiceImpl.getforeditStockAjustmentTransaction(itemStockinput.getStockAdjustmentID());
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
