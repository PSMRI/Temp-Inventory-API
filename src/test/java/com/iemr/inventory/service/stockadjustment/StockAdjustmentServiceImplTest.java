package com.iemr.inventory.service.stockadjustment;

import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.stockadjustment.StockAdjustment;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentDraft;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItem;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraft;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraftEdit;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.mapper.stockadjustment.StockAdjustmentItemDraftMapper;
import com.iemr.inventory.repo.stockEntry.ItemStockEntryRepo;
import com.iemr.inventory.repo.stockadjustment.StockAdjustmentDraftRepo;
import com.iemr.inventory.repo.stockadjustment.StockAdjustmentItemDraftRepo;
import com.iemr.inventory.repo.stockadjustment.StockAdjustmentItemRepo;
import com.iemr.inventory.repo.stockadjustment.StockAdjustmentRepo;
import com.iemr.inventory.utils.exception.InventoryException;

@ExtendWith(MockitoExtension.class)
class StockAdjustmentServiceImplTest {

	@InjectMocks
	StockAdjustmentServiceImpl stockAdjustmentServiceImpl;

	@Mock
	StockAdjustmentDraftRepo stockAdjustmentDraftRepo;

	@Mock
	StockAdjustmentItemDraftRepo stockAdjustmentItemDraftRepo;

	@Mock
	StockAdjustmentRepo stockAdjustmentRepo;

	@Mock
	StockAdjustmentItemRepo stockAdjustmentItemRepo;

	@Mock
	ItemStockEntryRepo itemStockEntryRepo;

	@Mock
	StockAdjustmentItemDraftMapper stockAdjustmentItemDraftMapper;

	@Test
	void saveDraftTest() {

		Date date = new Date();

		StockAdjustmentDraft stockAdjustmentDraft = new StockAdjustmentDraft();

		stockAdjustmentDraft.setIsCompleted(false);

		StockAdjustmentItemDraft stockAdjustmentItemDraft = new StockAdjustmentItemDraft();

		stockAdjustmentItemDraft.setSADraftItemMapID(1L);
		stockAdjustmentItemDraft.setItemStockEntryID(2L);
		stockAdjustmentItemDraft.setIsAdded(true);
		stockAdjustmentItemDraft.setAdjustedQuantity(100);
		stockAdjustmentItemDraft.setReason("For Draft Making");
		stockAdjustmentItemDraft.setProviderServiceMapID(6);
		stockAdjustmentItemDraft.setCreatedBy("AK Hossain");
		stockAdjustmentItemDraft.setStatus("Ready");
		stockAdjustmentItemDraft.setStockAdjustmentDraftID(3L);
		stockAdjustmentItemDraft.setDeleted(false);
		stockAdjustmentItemDraft.setProcessed('Y');
		stockAdjustmentItemDraft.setCreatedDate(date);
		stockAdjustmentItemDraft.setModifiedBy("AB Hossain");
		stockAdjustmentItemDraft.setLastModDate(date);
		stockAdjustmentItemDraft.setStockAdjustmentDraftID(12L);

		stockAdjustmentDraft.toString();

		List<StockAdjustmentItemDraft> itemdraft = new ArrayList<StockAdjustmentItemDraft>();

		itemdraft.add(stockAdjustmentItemDraft);

		stockAdjustmentDraft.setFacilityID(12);
		stockAdjustmentDraft.setDraftDesc("store draft");
		stockAdjustmentDraft.setDraftName("store");
		stockAdjustmentDraft.setRefNo("R87er89E");
		stockAdjustmentDraft.setIsCompleted(true);
		stockAdjustmentDraft.setProviderServiceMapID(6);
		stockAdjustmentDraft.setDeleted(false);
		stockAdjustmentDraft.setVanID(1L);
		stockAdjustmentDraft.setProcessed('Y');
		stockAdjustmentDraft.setCreatedBy("AK Kundu");
		stockAdjustmentDraft.setModifiedBy("AK Kundu");
		stockAdjustmentDraft.setStockAdjustmentDraftID(12L);

		stockAdjustmentItemDraft.toString();

		StockAdjustmentDraft stockdraft = stockAdjustmentDraft;

		when(stockAdjustmentDraftRepo.findById(stockAdjustmentDraft.getStockAdjustmentDraftID()))
				.thenReturn(Optional.of(stockdraft));

		stockAdjustmentItemDraftRepo.updateDeleted(stockAdjustmentDraft.getStockAdjustmentDraftID());

		when(stockAdjustmentItemDraftRepo.saveAll(itemdraft)).thenReturn(itemdraft);

		when(stockAdjustmentItemDraftRepo.findById(stockAdjustmentItemDraft.getSADraftItemMapID()))
				.thenReturn(Optional.of(stockAdjustmentItemDraft));

		stockdraft.setStockAdjustmentItemDraft(itemdraft);

		when(stockAdjustmentDraftRepo.save(stockAdjustmentDraft)).thenReturn(stockdraft);

		// Assertions
		Assertions.assertEquals(stockdraft, stockAdjustmentServiceImpl.saveDraft(stockAdjustmentDraft));

	}

	@Test
	void getStockAjustmentDraftTransactionTest() {

		ItemStockEntryinput itemStockEntryinput = new ItemStockEntryinput();

		itemStockEntryinput.setStockAdjustmentDraftID(12L);
		itemStockEntryinput.setStockAdjustmentID(2L);
		itemStockEntryinput.setConsumptionID(3L);
		itemStockEntryinput.setPatientIssueID(4L);
		itemStockEntryinput.setStockTransferID(5L);
		itemStockEntryinput.setPhyEntryID(6L);
		itemStockEntryinput.setFacilityID(7);
		itemStockEntryinput.setFromDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		itemStockEntryinput.setToDate(Timestamp.valueOf("2018-09-01 09:01:16"));

		itemStockEntryinput.toString();

		StockAdjustmentDraft stockAdjustmentDraft = new StockAdjustmentDraft();
		stockAdjustmentDraft.setFacilityID(12);
		stockAdjustmentDraft.setDraftDesc("store draft");
		stockAdjustmentDraft.setDraftName("store");
		stockAdjustmentDraft.setRefNo("R87er89E");
		stockAdjustmentDraft.setIsCompleted(false);
		stockAdjustmentDraft.setProviderServiceMapID(6);
		stockAdjustmentDraft.setDeleted(false);
		stockAdjustmentDraft.setVanID(1L);
		stockAdjustmentDraft.setProcessed('Y');
		stockAdjustmentDraft.setCreatedBy("AK Kundu");
		stockAdjustmentDraft.setModifiedBy("AK Kundu");
		stockAdjustmentDraft.setStockAdjustmentDraftID(12L);

		stockAdjustmentDraft.toString();

		List<StockAdjustmentDraft> data = new ArrayList<StockAdjustmentDraft>();

		data.add(stockAdjustmentDraft);

		when(stockAdjustmentDraftRepo.findByIsCompletedAndFacilityIDAndCreatedDateBetweenOrderByCreatedDateDesc(false,
				itemStockEntryinput.getFacilityID(), itemStockEntryinput.getFromDate(),
				itemStockEntryinput.getToDate())).thenReturn(data);

		// Assertions
		Assertions.assertNotNull(itemStockEntryinput.getFacilityID());
		Assertions.assertNotNull(itemStockEntryinput.getFromDate());
		Assertions.assertNotNull(itemStockEntryinput.getToDate());

		Assertions.assertEquals(data,
				stockAdjustmentServiceImpl.getStockAjustmentDraftTransaction(itemStockEntryinput));

	}

	@Test
	void getforeditStockAjustmentDraftTransactionTest() {

		Long stockAdjustmentDraftID = 12L;

		StockAdjustmentItemDraft stockAdjustmentItemDraft = new StockAdjustmentItemDraft();

		stockAdjustmentItemDraft.setSADraftItemMapID(1L);
		stockAdjustmentItemDraft.setItemStockEntryID(2L);
		stockAdjustmentItemDraft.setIsAdded(true);
		stockAdjustmentItemDraft.setAdjustedQuantity(100);
		stockAdjustmentItemDraft.setReason("For Draft Making");
		stockAdjustmentItemDraft.setProviderServiceMapID(6);
		stockAdjustmentItemDraft.setCreatedBy("AK Hossain");
		stockAdjustmentItemDraft.setStatus("Ready");
		stockAdjustmentItemDraft.setStockAdjustmentDraftID(3L);
		stockAdjustmentItemDraft.setDeleted(false);
		stockAdjustmentItemDraft.setProcessed('Y');
		stockAdjustmentItemDraft.setModifiedBy("AB Hossain");
		stockAdjustmentItemDraft.setStockAdjustmentDraftID(12L);

		stockAdjustmentItemDraft.toString();

		List<StockAdjustmentItemDraft> s = new ArrayList<StockAdjustmentItemDraft>();

		s.add(stockAdjustmentItemDraft);

		StockAdjustmentDraft stock = new StockAdjustmentDraft();
		stock.setFacilityID(12);
		stock.setDraftDesc("store draft");
		stock.setDraftName("store");
		stock.setRefNo("R87er89E");
		stock.setIsCompleted(false);
		stock.setProviderServiceMapID(6);
		stock.setDeleted(false);
		stock.setVanID(1L);
		stock.setProcessed('Y');
		stock.setCreatedBy("AK Kundu");
		stock.setModifiedBy("AK Kundu");
		stock.setStockAdjustmentDraftID(12L);

		stock.toString();

		when(stockAdjustmentDraftRepo.getforedit(stockAdjustmentDraftID)).thenReturn(stock);

		// Assertions
		Assertions.assertEquals(stock,
				stockAdjustmentServiceImpl.getforeditStockAjustmentDraftTransaction(stockAdjustmentDraftID));

	}

	@Test
	void savetransactionTest() throws InventoryException {

		Date date = new Date();

		StockAdjustmentItem stockAdjustmentItem = new StockAdjustmentItem();

		stockAdjustmentItem.setFacilityID(12);
		stockAdjustmentItem.setProviderServiceMapID(6);
		stockAdjustmentItem.setDeleted(false);
		stockAdjustmentItem.setVanID(1L);
		stockAdjustmentItem.setProcessed('Y');
		stockAdjustmentItem.setCreatedBy("AK Kundu");
		stockAdjustmentItem.setCreatedDate(date);
		stockAdjustmentItem.setModifiedBy("AK Kundu");
		stockAdjustmentItem.setLastModDate(date);
		stockAdjustmentItem.setIsAdded(true);

		stockAdjustmentItem.toString();

		List<StockAdjustmentItem> sd = new ArrayList<StockAdjustmentItem>();
		sd.add(stockAdjustmentItem);

		StockAdjustment stockAdjustment = new StockAdjustment();

		stockAdjustment.setFacilityID(12);
		stockAdjustment.setRefNo("R87er89E");
		stockAdjustment.setProviderServiceMapID(6);
		stockAdjustment.setDeleted(false);
		stockAdjustment.setVanID(1L);
		stockAdjustment.setProcessed('Y');
		stockAdjustment.setCreatedBy("AK Kundu");
		stockAdjustment.setCreatedDate(date);
		stockAdjustment.setModifiedBy("AK Kundu");
		stockAdjustment.setLastModDate(date);
		stockAdjustment.setStockAdjustmentDraftID(12L);
		stockAdjustment.setStockAdjustmentID(13L);
		stockAdjustment.setStockAdjustmentItem(sd);

		stockAdjustment.toString();

		List<Long> comapreid = new ArrayList<>();
		comapreid.add(1L);

		final Integer facID = stockAdjustment.getFacilityID();
		sd.parallelStream().forEach(action -> {
			comapreid.add(action.getItemStockEntryID());
			action.setFacilityID(facID);
		});

		ItemStockEntry stock = new ItemStockEntry();

		stock.setEntryType("physicalStockEntry");
		stock.setQuantityInHand(100);
		stock.setVanID(3L);
		stock.setParkingPlaceID(6L);
		stock.setCreatedBy("ytyty");
		stock.setSyncFacilityID(8);
		stock.setItemStockEntryID(12L);

		stock.toString();

		List<ItemStockEntry> compareobject = new ArrayList<ItemStockEntry>();

		compareobject.add(stock);

		when(itemStockEntryRepo.findByItemStockEntryIDIn(comapreid)).thenReturn(compareobject);

		Map<Long, ItemStockEntry> result = compareobject.stream()
				.collect(Collectors.toMap(ItemStockEntry::getItemStockEntryID, Function.identity()));

		for (StockAdjustmentItem action : sd) {
			action.setSyncFacilityID(facID);
			ItemStockEntry itemStockEntry = result.get(action.getItemStockEntryID());

			if (!action.getIsAdded()) {
				if (action.getAdjustedQuantity() > itemStockEntry.getQuantityInHand()) {
					throw new InventoryException(
							"Adjustment Quantity for issue should be more than available quantity");
				}
			}
		}

		stockAdjustment.setSyncFacilityID(3);

		Long saID = stockAdjustment.getStockAdjustmentID();

		when(stockAdjustmentRepo.save(stockAdjustment)).thenReturn(stockAdjustment);

		when(stockAdjustmentDraftRepo.updatecompleted(stockAdjustment.getStockAdjustmentDraftID(), true))
				.thenReturn(facID);

		// Assertions
		Assertions.assertNotNull(stockAdjustment.getStockAdjustmentDraftID());
		Assertions.assertEquals(stockAdjustment, stockAdjustmentServiceImpl.savetransaction(stockAdjustment));

	}

	@Test
	void getforeditStockAjustmentTransactionTest() {

		Long stockAdjustmentID = 13L;

		StockAdjustment stock = new StockAdjustment();

		stock.setFacilityID(12);
		stock.setRefNo("R87er89E");
		stock.setProviderServiceMapID(6);
		stock.setDeleted(false);
		stock.setVanID(1L);
		stock.setProcessed('Y');
		stock.setCreatedBy("AK Kundu");
		stock.setModifiedBy("AK Kundu");
		stock.setStockAdjustmentDraftID(12L);
		stock.setStockAdjustmentID(13L);
		stock.setVanSerialNo(3L);
		stock.setSyncFacilityID(3);

		stock.toString();

		when(stockAdjustmentRepo.findById(stockAdjustmentID)).thenReturn(Optional.of(stock));

		StockAdjustmentItem stockAdjustmentItem = new StockAdjustmentItem();

		stockAdjustmentItem.setFacilityID(12);
		stockAdjustmentItem.setProviderServiceMapID(6);
		stockAdjustmentItem.setDeleted(false);
		stockAdjustmentItem.setVanID(1L);
		stockAdjustmentItem.setProcessed('Y');
		stockAdjustmentItem.setCreatedBy("AK Kundu");
		stockAdjustmentItem.setModifiedBy("AK Kundu");
		stockAdjustmentItem.setIsAdded(true);

		stockAdjustmentItem.toString();

		List<StockAdjustmentItem> sd = new ArrayList<StockAdjustmentItem>();
		sd.add(stockAdjustmentItem);

		when(stockAdjustmentItemRepo.findByStockAdjustmentIDAndSyncFacilityID(stock.getVanSerialNo(),
				stock.getSyncFacilityID())).thenReturn(sd);

		stock.setStockAdjustmentItem(sd);

		StockAdjustmentItemDraftEdit stockAdjustmentItemDraftEdit = new StockAdjustmentItemDraftEdit();

		stockAdjustmentItemDraftEdit.setSADraftItemMapID(1L);
		stockAdjustmentItemDraftEdit.setItemStockEntryID(2L);
		stockAdjustmentItemDraftEdit.setIsAdded(true);
		stockAdjustmentItemDraftEdit.setAdjustedQuantity(100);
		stockAdjustmentItemDraftEdit.setReason("For Draft Making");
		stockAdjustmentItemDraftEdit.setProviderServiceMapID(6);
		stockAdjustmentItemDraftEdit.setCreatedBy("AK Hossain");
		stockAdjustmentItemDraftEdit.setStatus("Ready");
		stockAdjustmentItemDraftEdit.setDeleted(false);
		stockAdjustmentItemDraftEdit.setModifiedBy("AB Hossain");

		stockAdjustmentItemDraftEdit.toString();

		List<StockAdjustmentItemDraftEdit> s = new ArrayList<StockAdjustmentItemDraftEdit>();
		s.add(stockAdjustmentItemDraftEdit);

		stock.setStockAdjustmentItemDraftEdit(s);

		when(stockAdjustmentItemDraftMapper.getStockAdjustmentItemEditList(stock.getStockAdjustmentItem()))
				.thenReturn(s);

		Assertions.assertEquals(stock,
				stockAdjustmentServiceImpl.getforeditStockAjustmentTransaction(stockAdjustmentID));

	}

	@Test
	void getStockAjustmentTransactionTest() {

		StockAdjustment stock = new StockAdjustment();

		stock.setFacilityID(12);
		stock.setRefNo("R87er89E");
		stock.setProviderServiceMapID(6);
		stock.setDeleted(false);
		stock.setVanID(1L);
		stock.setProcessed('Y');
		stock.setCreatedBy("AK Kundu");
		stock.setModifiedBy("AK Kundu");
		stock.setStockAdjustmentDraftID(12L);
		stock.setStockAdjustmentID(13L);
		stock.setVanSerialNo(3L);
		stock.setSyncFacilityID(3);

		stock.toString();

		List<StockAdjustment> data = new ArrayList<StockAdjustment>();
		data.add(stock);

		ItemStockEntryinput itemStockEntryinput = new ItemStockEntryinput();

		itemStockEntryinput.setStockAdjustmentDraftID(12L);
		itemStockEntryinput.setStockAdjustmentID(2L);
		itemStockEntryinput.setConsumptionID(3L);
		itemStockEntryinput.setPatientIssueID(4L);
		itemStockEntryinput.setStockTransferID(5L);
		itemStockEntryinput.setPhyEntryID(6L);
		itemStockEntryinput.setFacilityID(7);
		itemStockEntryinput.setFromDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		itemStockEntryinput.setToDate(Timestamp.valueOf("2018-09-01 09:01:16"));

		itemStockEntryinput.toString();

		when(stockAdjustmentRepo.findByFacilityIDAndCreatedDateBetweenOrderByCreatedDateDesc(
				itemStockEntryinput.getFacilityID(), itemStockEntryinput.getFromDate(),
				itemStockEntryinput.getToDate())).thenReturn(data);

		Assertions.assertEquals(data, stockAdjustmentServiceImpl.getStockAjustmentTransaction(itemStockEntryinput));

	}

}
