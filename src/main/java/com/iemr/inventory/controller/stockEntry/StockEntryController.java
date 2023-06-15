package com.iemr.inventory.controller.stockEntry;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.stockExit.ItemStockExit;
import com.iemr.inventory.data.stockentry.AllocateItemMap;
import com.iemr.inventory.data.stockentry.ItemMasterWithQuantityMap;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.data.stockentry.PhysicalStockEntry;
import com.iemr.inventory.service.stockEntry.StockEntryServiceImpl;
import com.iemr.inventory.utils.response.OutputResponse;

import io.swagger.annotations.ApiOperation;

@RestController
public class StockEntryController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	private StockEntryServiceImpl stockEntryService;
	
	@CrossOrigin()
	@ApiOperation(
			value = "Physical Stock Entry",
			consumes = "application/json",
			produces = "application/json")
	@RequestMapping(value =  "/physicalStockEntry" ,headers = "Authorization", method = { RequestMethod.POST })
	public String physicalStockEntry(@RequestBody PhysicalStockEntry physicalStockEntry)  {
		
			OutputResponse output = new OutputResponse();

					
			
			try {
				physicalStockEntry=stockEntryService.savePhysicalStockEntry(physicalStockEntry);
				
				output.setResponse(physicalStockEntry.toString());
			} catch (Exception e) {
				logger.error(e.getMessage());
				output.setError(e);
			}
			return output.toString();
	}
	
	@CrossOrigin()
	@RequestMapping(value = "/getItemBatchForStoreID", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getItemBatchForStoreID(@RequestBody ItemStockEntry itemStockEntry) {

		OutputResponse response = new OutputResponse();

		try {

			List<ItemStockEntry> getData = stockEntryService.getItemBatchForStoreID(itemStockEntry);

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
	@RequestMapping(value = "/allocateStockFromItemID/{facilityID}", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String allocateStockFromItemID(@PathVariable("facilityID") Integer facilityID,@RequestBody List<ItemStockExit> itemStockExitList) {

		OutputResponse response = new OutputResponse();

		try {

			List<AllocateItemMap> getData = stockEntryService.getItemStockFromItemID(facilityID, itemStockExitList);

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
	@RequestMapping(value = "/getPhysicalStockEntry", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getPhysicalStockEntry(@RequestBody ItemStockEntryinput itemStockinput) {

		OutputResponse response = new OutputResponse();

		try {

			List<PhysicalStockEntry> getData = stockEntryService.getPhysicalStockEntry(itemStockinput);

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
	
	// this method is fetch all the mapped items and availabe Batch the store can trade with with partial itemname search
	@CrossOrigin()
	@RequestMapping(value = "/itemBatchPartialSearch", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String itemPartialSearch(@RequestBody ItemMaster getItem) {

		OutputResponse response = new OutputResponse();

		try {

			List<ItemStockEntry> getData = stockEntryService.getItemMastersPartialSearch(getItem.getItemName(),getItem.getFacilityID());

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
	@RequestMapping(value = "/itemBatchWithZeroPartialSearch", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String itemBatchWithZeroPartialSearch(@RequestBody ItemMaster getItem) {

		OutputResponse response = new OutputResponse();

		try {

			List<ItemStockEntry> getData = stockEntryService.getItemMastersPartialSearchWithZero(getItem.getItemName(),getItem.getFacilityID());

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
	@RequestMapping(value = "/getPhysicalStockEntryItems", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getPhysicalStockEntryItems(@RequestBody ItemStockEntryinput getItem) {

		OutputResponse response = new OutputResponse();

		try {

			List<ItemStockEntry> getData = stockEntryService.getPhysicalStockEntryItems(getItem.getPhyEntryID());

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
	@RequestMapping(value = "/getItemwithQuantityPartialSearch", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getItemwithQuantityPartialSearch(@RequestBody ItemMaster getItem) {

		OutputResponse response = new OutputResponse();

		try {

			List<ItemMasterWithQuantityMap> getData = stockEntryService.getItemwithQuantityPartialSearch(getItem.getItemName(),getItem.getFacilityID());

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
	
}
