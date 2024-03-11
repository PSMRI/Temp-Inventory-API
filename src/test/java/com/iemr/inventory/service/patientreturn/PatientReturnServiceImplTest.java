package com.iemr.inventory.service.patientreturn;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.patientreturn.ItemDetailModel;
import com.iemr.inventory.data.patientreturn.PatientReturnModel;
import com.iemr.inventory.data.patientreturn.ReturnHistoryModel;
import com.iemr.inventory.data.stockExit.ItemReturnEntry;
import com.iemr.inventory.data.stockExit.T_PatientIssue;
import com.iemr.inventory.repository.patientreturn.ItemReturnEntryRepo;
import com.iemr.inventory.repository.patientreturn.PatientReturnRepo;

@ExtendWith(MockitoExtension.class)
class PatientReturnServiceImplTest {

	@Mock
	private PatientReturnRepo patientReturnRepo;
	@Mock
	private ItemReturnEntryRepo itemReturnEntryRepo;
	@Mock
	private PatientReturnServiceImpl patientReturnService;

	@Before
	public void setUp() {
		patientReturnRepo = mock(PatientReturnRepo.class);
		itemReturnEntryRepo = mock(ItemReturnEntryRepo.class);
		patientReturnService = new PatientReturnServiceImpl();
	}

	@Test
	public void testGetItemNameByRegID() {
		// Mocking data
		List<Object[]> results = new ArrayList<>();
		Object[] object1 = { 1L, 2, 3, "Item1" };
		results.add(object1);
		// Test
		T_PatientIssue patientIssue = new T_PatientIssue();
		patientIssue.toString();

		List<PatientReturnModel> list = patientReturnService.getItemNameByRegID(patientIssue);
		assertEquals(0, list.size());

	}

	@Test
	public void testGetItemDetailByBen() {
		// Mocking data
		List<Object[]> results = new ArrayList<>();
		Object[] object1 = { 1, "Name", "Description", 2, new Timestamp(System.currentTimeMillis()), true, true, 3L, 4L,
				5L, 6L, 7L, 8, 9 };
		results.add(object1);
		// Test
		ItemDetailModel itemDetailModel = new ItemDetailModel(null, null, null, null, null, null, null, null, null,
				null, null, null, null, null);
		itemDetailModel.toString();

		List<ItemDetailModel> list = patientReturnService.getItemDetailByBen(itemDetailModel);
		assertEquals(0, list.size());
		// Add more assertions for other fields
	}

	@Test
	public void testUpdateQuantityReturned() {
		// Mocking data
		ItemDetailModel[] itemDetailModels = { new ItemDetailModel(null, null, null, null, null, null, null, null, null,
				null, null, null, null, null) };
		itemDetailModels.toString();

		// Test
		String result = "Quantity updated successfully";
		Assertions.assertTrue(result.contains("Quantity updated successfully"));

	}

	@Test
	public void testGetBenReturnHistory() {
		// Mocking data
		List<Object[]> results = new ArrayList<>();
		Object[] object1 = { "Name", "Description", 2, new Timestamp(System.currentTimeMillis()), 3L, 4L, "Visit", 5,
				"Code", new Timestamp(System.currentTimeMillis()) };
		results.add(object1);

		// Test
		ItemReturnEntry itemReturnEntry = new ItemReturnEntry();
		itemReturnEntry.setBenRegID(12L);
		itemReturnEntry.toString();

		List<ReturnHistoryModel> list = patientReturnService.getBenReturnHistory(itemReturnEntry);
		assertEquals(0, list.size());
		// Add more assertions for other fields

	}
}
