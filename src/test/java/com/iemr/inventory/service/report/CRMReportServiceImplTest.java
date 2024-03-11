package com.iemr.inventory.service.report;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.report.ItemStockEntryReport;
import com.iemr.inventory.data.report.ItemStockExitReport;
import com.iemr.inventory.data.report.PatientIssueExitReport;
import com.iemr.inventory.mapper.report.InventoryReportMapper;
import com.iemr.inventory.model.report.BenDrugIssueReport;
import com.iemr.inventory.model.report.ExpiryReport;
import com.iemr.inventory.model.report.InwardStockReport;
import com.iemr.inventory.repo.report.ItemStockReportRepo;

@ExtendWith(MockitoExtension.class)
class CRMReportServiceImplTest {

	@InjectMocks
	CRMReportServiceImpl crmReportServiceImpl;

	@InjectMocks
	CRMReportServiceImpl service;

	@Mock
	ItemStockReportRepo itemStockReportRepo;

	@Mock
	ItemStockReportRepo mockRepo;

	@Mock
	InventoryReportMapper mapper;

	@Mock
	private InventoryReportMapper inventoryReportMapper;

	@BeforeEach
	public void setUp() {
		mockRepo = mock(ItemStockReportRepo.class);
		service = new CRMReportServiceImpl(mockRepo);
	}

	@Test
	void getInwardStockReportTest() {

		ItemStockEntryReport entryReport = new ItemStockEntryReport();

		entryReport.setFactItemStockEntryID(3L);
		entryReport.setItemStockEntryID(4L);
		entryReport.setFacilityID(5);
		entryReport.setFacilityName("Medicine");
		entryReport.setFacilityDesc("For cure diseases");
		entryReport.setFacilityCode("M63465E");
		entryReport.setFacilityTypeID(6L);
		entryReport.setIsMainFacility(true);
		entryReport.setMainFacilityID(7);
		entryReport.setItemID(8);
		entryReport.setItemName("Underscore");
		entryReport.setItemDesc("Under score healthcare");
		entryReport.setItemCategoryID(9);
		entryReport.setItemCategoryName("Health Care");
		entryReport.setAlertBeforeDays("30 Days");
		entryReport.setStrength("Strong");
		entryReport.setBatchNo("B64563bv");
		entryReport.setQuantity(100);
		entryReport.setQuantityInHand(80);
		entryReport.setUnitCostPrice(Double.valueOf("12"));
		entryReport.setExpiryDate(Date.valueOf("2026-09-09"));
		entryReport.setEntryTypeID(10L);
		entryReport.setProviderServiceMapID(11);
		entryReport.setCreatedBy("A K");
		entryReport.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setMonth(8);
		entryReport.setMonthName("August");
		entryReport.setYear(2022);

		entryReport.toString();

		List<ItemStockEntryReport> list = new ArrayList<ItemStockEntryReport>();

		list.add(entryReport);

		when(itemStockReportRepo.getItemStockEntryReportByFacilityID(entryReport.getStartDate(),
				entryReport.getEndDate(), entryReport.getFacilityID())).thenReturn(list);

		List<InwardStockReport> reportList = new ArrayList<InwardStockReport>();

		Long slNo = 1L;

		ItemStockEntryReport reportData = entryReport;

		InwardStockReport report = new InwardStockReport();

		report.setSlNo(slNo);

		report.toString();

		when(mapper.mapInwardStockReport(reportData)).thenReturn(report);

		reportList.add(report);

		// Assertions
		Assertions.assertNotNull(entryReport.getFacilityID());
		Assertions.assertEquals(reportList.toString(), crmReportServiceImpl.getInwardStockReport(entryReport));
		;

	}

	@Test
	void testGetInwardStockReport() {
		// Prepare
		ItemStockReportRepo mockRepo = mock(ItemStockReportRepo.class);
		CRMReportServiceImpl service = new CRMReportServiceImpl(mockRepo);

		ItemStockEntryReport entryReport = new ItemStockEntryReport();

		entryReport.setFactItemStockEntryID(3L);
		entryReport.setItemStockEntryID(4L);
		entryReport.setFacilityID(null);
		entryReport.setFacilityName("Medicine");
		entryReport.setFacilityDesc("For cure diseases");
		entryReport.setFacilityCode("M63465E");
		entryReport.setFacilityTypeID(6L);
		entryReport.setIsMainFacility(true);
		entryReport.setMainFacilityID(7);
		entryReport.setItemID(8);
		entryReport.setItemName("Underscore");
		entryReport.setItemDesc("Under score healthcare");
		entryReport.setItemCategoryID(9);
		entryReport.setItemCategoryName("Health Care");
		entryReport.setAlertBeforeDays("30 Days");
		entryReport.setStrength("Strong");
		entryReport.setBatchNo("B64563bv");
		entryReport.setQuantity(100);
		entryReport.setQuantityInHand(80);
		entryReport.setUnitCostPrice(Double.valueOf("12"));
		entryReport.setExpiryDate(Date.valueOf("2026-09-09"));
		entryReport.setEntryTypeID(10L);
		entryReport.setProviderServiceMapID(11);
		entryReport.setCreatedBy("A K");
		entryReport.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setMonth(8);
		entryReport.setMonthName("August");
		entryReport.setYear(2022);

		entryReport.toString();

		// Call the method
		String result = service.getInwardStockReport(entryReport);

		// Assertions
		assertNotNull(result);
		// Add more assertions based on your expected behavior

	}

	@Test
	void getInwardStockReportElseTest() {

		ItemStockEntryReport entryReport = new ItemStockEntryReport();

		entryReport.setFactItemStockEntryID(3L);
		entryReport.setItemStockEntryID(4L);
		entryReport.setFacilityID(null);
		entryReport.setFacilityName("Medicine");
		entryReport.setFacilityDesc("For cure diseases");
		entryReport.setFacilityCode("M63465E");
		entryReport.setFacilityTypeID(6L);
		entryReport.setIsMainFacility(true);
		entryReport.setMainFacilityID(7);
		entryReport.setItemID(8);
		entryReport.setItemName("Underscore");
		entryReport.setItemDesc("Under score healthcare");
		entryReport.setItemCategoryID(9);
		entryReport.setItemCategoryName("Health Care");
		entryReport.setAlertBeforeDays("30 Days");
		entryReport.setStrength("Strong");
		entryReport.setBatchNo("B64563bv");
		entryReport.setQuantity(100);
		entryReport.setQuantityInHand(80);
		entryReport.setUnitCostPrice(Double.valueOf("12"));
		entryReport.setExpiryDate(Date.valueOf("2026-09-09"));
		entryReport.setEntryTypeID(10L);
		entryReport.setProviderServiceMapID(11);
		entryReport.setCreatedBy("A K");
		entryReport.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setMonth(8);
		entryReport.setMonthName("August");
		entryReport.setYear(2022);

		entryReport.toString();

		List<ItemStockEntryReport> list = new ArrayList<ItemStockEntryReport>();

		list.add(entryReport);

		when(itemStockReportRepo.getItemStockEntryReport(entryReport.getStartDate(), entryReport.getEndDate()))
				.thenReturn(list);

		List<InwardStockReport> reportList = new ArrayList<InwardStockReport>();

		Long slNo = 1L;

		ItemStockEntryReport reportData = entryReport;

		InwardStockReport report = new InwardStockReport();

		report.setSlNo(slNo);

		report.toString();

		when(mapper.mapInwardStockReport(reportData)).thenReturn(report);

		reportList.add(report);

		// Assertions
		Assertions.assertNull(entryReport.getFacilityID());
		Assertions.assertEquals(reportList.toString(), crmReportServiceImpl.getInwardStockReport(entryReport));
		;

	}

	@Test
	void getExpiryReportTest() {

		ItemStockEntryReport entryReport = new ItemStockEntryReport();

		entryReport.setFactItemStockEntryID(3L);
		entryReport.setItemStockEntryID(4L);
		entryReport.setFacilityID(5);
		entryReport.setFacilityName("Medicine");
		entryReport.setFacilityDesc("For cure diseases");
		entryReport.setFacilityCode("M63465E");
		entryReport.setFacilityTypeID(6L);
		entryReport.setIsMainFacility(true);
		entryReport.setMainFacilityID(7);
		entryReport.setItemID(8);
		entryReport.setItemName("Underscore");
		entryReport.setItemDesc("Under score healthcare");
		entryReport.setItemCategoryID(9);
		entryReport.setItemCategoryName("Health Care");
		entryReport.setAlertBeforeDays("30 Days");
		entryReport.setStrength("Strong");
		entryReport.setBatchNo("B64563bv");
		entryReport.setQuantity(100);
		entryReport.setQuantityInHand(80);
		entryReport.setUnitCostPrice(Double.valueOf("12"));
		entryReport.setExpiryDate(Date.valueOf("2026-09-09"));
		entryReport.setEntryTypeID(10L);
		entryReport.setProviderServiceMapID(11);
		entryReport.setCreatedBy("A K");
		entryReport.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setMonth(8);
		entryReport.setMonthName("August");
		entryReport.setYear(2022);

		entryReport.toString();

		List<ItemStockEntryReport> itemStockEntryReportList = new ArrayList<ItemStockEntryReport>();
		itemStockEntryReportList.add(entryReport);

		Date startExpiry = new Date(entryReport.getStartDate().getTime());
		Date endExpiry = new Date(entryReport.getEndDate().getTime());

		List<ExpiryReport> reportList = new ArrayList<ExpiryReport>();

		Object[] obj = new Object[] { "Medicine", "For cure diseases", "Health Care", "B64563bv", Double.valueOf("12"),
				Date.valueOf("2026-09-09"), 8 };

		List<Object[]> list = new ArrayList<Object[]>();
		list.add(obj);

		when(itemStockReportRepo.getExpiryReportByFacilityID(startExpiry, endExpiry, entryReport.getFacilityID()))
				.thenReturn(list);

		Object[] object = obj;

		ExpiryReport report = new ExpiryReport();

		Long slNo = 1L;

		report.setSlNo(slNo);
		report.setFacilityName((String) object[0]);
		report.setItemName((String) object[1]);
		report.setItemCategory((String) object[2]);
		report.setBatchNo((String) object[3]);
		report.setUnitCostPrice((Double) object[4]);
		report.setExpiryDate((Date) object[5]);
		report.setQuantityInHand((Integer) object[6]);

		report.toString();

		reportList.add(report);

		// Assertions
		Assertions.assertNotNull(entryReport.getFacilityID());
		Assertions.assertNotNull(object);
		Assertions.assertEquals(reportList.toString(), crmReportServiceImpl.getExpiryReport(entryReport));

	}

	@Test
	void getExpiryReportElseTest() {

		ItemStockEntryReport entryReport = new ItemStockEntryReport();

		entryReport.setFactItemStockEntryID(3L);
		entryReport.setItemStockEntryID(4L);
		entryReport.setFacilityID(null);
		entryReport.setFacilityName("Medicine");
		entryReport.setFacilityDesc("For cure diseases");
		entryReport.setFacilityCode("M63465E");
		entryReport.setFacilityTypeID(6L);
		entryReport.setIsMainFacility(true);
		entryReport.setMainFacilityID(7);
		entryReport.setItemID(8);
		entryReport.setItemName("Underscore");
		entryReport.setItemDesc("Under score healthcare");
		entryReport.setItemCategoryID(9);
		entryReport.setItemCategoryName("Health Care");
		entryReport.setAlertBeforeDays("30 Days");
		entryReport.setStrength("Strong");
		entryReport.setBatchNo("B64563bv");
		entryReport.setQuantity(100);
		entryReport.setQuantityInHand(80);
		entryReport.setUnitCostPrice(Double.valueOf("12"));
		entryReport.setExpiryDate(Date.valueOf("2026-09-09"));
		entryReport.setEntryTypeID(10L);
		entryReport.setProviderServiceMapID(11);
		entryReport.setCreatedBy("A K");
		entryReport.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setMonth(8);
		entryReport.setMonthName("August");
		entryReport.setYear(2022);

		entryReport.toString();

		List<ItemStockEntryReport> itemStockEntryReportList = new ArrayList<ItemStockEntryReport>();
		itemStockEntryReportList.add(entryReport);

		Date startExpiry = new Date(entryReport.getStartDate().getTime());
		Date endExpiry = new Date(entryReport.getEndDate().getTime());

		List<ExpiryReport> reportList = new ArrayList<ExpiryReport>();

		Object[] obj = new Object[] { "Medicine", "For cure diseases", "Health Care", "B64563bv", Double.valueOf("12"),
				Date.valueOf("2026-09-09"), 8 };

		List<Object[]> list = new ArrayList<Object[]>();
		list.add(obj);

		when(itemStockReportRepo.getExpiryReport(startExpiry, endExpiry)).thenReturn(list);

		Object[] object = obj;

		ExpiryReport report = new ExpiryReport();

		Long slNo = 1L;

		report.setSlNo(slNo);
		report.setFacilityName((String) object[0]);
		report.setItemName((String) object[1]);
		report.setItemCategory((String) object[2]);
		report.setBatchNo((String) object[3]);
		report.setUnitCostPrice((Double) object[4]);
		report.setExpiryDate((Date) object[5]);
		report.setQuantityInHand((Integer) object[6]);

		report.toString();

		reportList.add(report);

		// Assertions
		Assertions.assertNull(entryReport.getFacilityID());
		Assertions.assertNotNull(object);
		Assertions.assertEquals(reportList.toString(), crmReportServiceImpl.getExpiryReport(entryReport));

	}

	@Test
	void testGetConsumptionReport() {
		// Implement your test for getConsumptionReport method
		ItemStockReportRepo mockRepo = mock(ItemStockReportRepo.class);
		CRMReportServiceImpl service = new CRMReportServiceImpl(mockRepo);

		ItemStockExitReport exitReport = new ItemStockExitReport();

		exitReport.setFactItemStockExitID(12L);
		exitReport.setItemStockExitID(13L);
		exitReport.setItemStockEntryID(11L);
		exitReport.setFacilityID(12);
		exitReport.setFacilityName("Medicine");
		exitReport.setItemID(2);
		exitReport.setItemName("Pharmalogical");
		exitReport.setItemCategoryName("Pharmalogical");
		exitReport.setBatchNo("M23764367B");
		exitReport.setQuantity(100);
		exitReport.setUnitCostPrice(Double.valueOf("123"));
		exitReport.setExpiryDate(Date.valueOf("2022-09-09"));
		exitReport.setExitType("Normal");
		exitReport.setProviderServiceMapID(3);
		exitReport.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		exitReport.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		exitReport.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));

		exitReport.toString();

		String result = service.getConsumptionReport(exitReport);

		Assertions.assertNotNull(exitReport.getFacilityID());
		assertNotNull(result);
		// Add more assertions based on your expected behavior

	}

	@Test
	void testGetConsumptionReportAsNull() {
		// Implement your test for getConsumptionReport method
		ItemStockReportRepo mockRepo = mock(ItemStockReportRepo.class);
		CRMReportServiceImpl service = new CRMReportServiceImpl(mockRepo);

		ItemStockExitReport exitReport = new ItemStockExitReport();

		exitReport.setFactItemStockExitID(12L);
		exitReport.setItemStockExitID(13L);
		exitReport.setItemStockEntryID(11L);
		exitReport.setFacilityID(null);
		exitReport.setFacilityName("Medicine");
		exitReport.setItemID(2);
		exitReport.setItemName("Pharmalogical");
		exitReport.setItemCategoryName("Pharmalogical");
		exitReport.setBatchNo("M23764367B");
		exitReport.setQuantity(100);
		exitReport.setUnitCostPrice(Double.valueOf("123"));
		exitReport.setExpiryDate(Date.valueOf("2022-09-09"));
		exitReport.setExitType("Normal");
		exitReport.setProviderServiceMapID(3);
		exitReport.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		exitReport.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		exitReport.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));

		exitReport.toString();

		String result = service.getConsumptionReport(exitReport);

		Assertions.assertNull(exitReport.getFacilityID());
		assertNotNull(result);

	}

	@Test
	void getBenDrugIssueReportTest() {

		PatientIssueExitReport exitReport = new PatientIssueExitReport();

		exitReport.setFactPatientIssueExitID(123L);
		exitReport.setItemStockEntryID(12L);
		exitReport.setItemStockExitID(13L);
		exitReport.setItemID(12);
		exitReport.setItemName("Medicine");
		exitReport.setItemCategoryName("Pharmalogical");
		exitReport.setBatchNo("M356B");
		exitReport.setQuantityGiven(100);
		exitReport.setStrength("Moderate");
		exitReport.setExpiryDate(Date.valueOf("2028-09-09"));
		exitReport.setFacilityID(3);
		exitReport.setPatientName("AK Hossain");
		exitReport.setAge(32);
		exitReport.setGender("Male");
		exitReport.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		exitReport.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		exitReport.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));

		exitReport.toString();

		List<PatientIssueExitReport> list = new ArrayList<PatientIssueExitReport>();
		list.add(exitReport);

		when(itemStockReportRepo.getPatientIssueExitReportByFacilityID(exitReport.getStartDate(),
				exitReport.getEndDate(), exitReport.getFacilityID())).thenReturn(list);

		BenDrugIssueReport benDrugIssueReport = new BenDrugIssueReport();

		benDrugIssueReport.setSlNo(1L);
		benDrugIssueReport.setDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		benDrugIssueReport.setBeneficiaryName("Sfj");
		benDrugIssueReport.setAge(32);
		benDrugIssueReport.setGender("Male");
		benDrugIssueReport.setItemName("Medicine");
		benDrugIssueReport.setBatchNo("M356B");
		benDrugIssueReport.setItemCategory("Pharmalogical");
		benDrugIssueReport.setExpiryDate(Date.valueOf("2028-09-09"));
		benDrugIssueReport.setDispensedQuantity(100);
		benDrugIssueReport.setStrength("Moderate");

		benDrugIssueReport.toString();

		List<BenDrugIssueReport> reportList = new ArrayList<BenDrugIssueReport>();

		Long slNo = 1L;

		BenDrugIssueReport report = benDrugIssueReport;

		PatientIssueExitReport reportData = exitReport;

		when(mapper.mapBenDrugIssueReport(reportData)).thenReturn(report);

		report.setSlNo(slNo);
		reportList.add(report);

		Assertions.assertNotNull(exitReport.getFacilityID());
		Assertions.assertEquals(reportList.toString(), crmReportServiceImpl.getBenDrugIssueReport(exitReport));

	}

	@Test
	void getBenDrugIssueReportFacilityIDAsNullTest() {

		PatientIssueExitReport exitReport = new PatientIssueExitReport();

		exitReport.setFactPatientIssueExitID(123L);
		exitReport.setItemStockEntryID(12L);
		exitReport.setItemStockExitID(13L);
		exitReport.setItemID(12);
		exitReport.setItemName("Medicine");
		exitReport.setItemCategoryName("Pharmalogical");
		exitReport.setBatchNo("M356B");
		exitReport.setQuantityGiven(100);
		exitReport.setStrength("Moderate");
		exitReport.setExpiryDate(Date.valueOf("2028-09-09"));
		exitReport.setFacilityID(null);
		exitReport.setPatientName("AK Hossain");
		exitReport.setAge(32);
		exitReport.setGender("Male");
		exitReport.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		exitReport.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		exitReport.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));

		exitReport.toString();

		List<PatientIssueExitReport> list = new ArrayList<PatientIssueExitReport>();
		list.add(exitReport);

		BenDrugIssueReport benDrugIssueReport = new BenDrugIssueReport();

		benDrugIssueReport.setSlNo(1L);
		benDrugIssueReport.setDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		benDrugIssueReport.setBeneficiaryName("Sfj");
		benDrugIssueReport.setAge(32);
		benDrugIssueReport.setGender("Male");
		benDrugIssueReport.setItemName("Medicine");
		benDrugIssueReport.setBatchNo("M356B");
		benDrugIssueReport.setItemCategory("Pharmalogical");
		benDrugIssueReport.setExpiryDate(Date.valueOf("2028-09-09"));
		benDrugIssueReport.setDispensedQuantity(100);
		benDrugIssueReport.setStrength("Moderate");

		benDrugIssueReport.toString();

		List<BenDrugIssueReport> reportList = new ArrayList<BenDrugIssueReport>();

		when(itemStockReportRepo.getPatientIssueExitReport(exitReport.getStartDate(), exitReport.getEndDate()))
				.thenReturn(list);

		Long slNo = 1L;

		BenDrugIssueReport report = benDrugIssueReport;

		PatientIssueExitReport reportData = exitReport;

		when(mapper.mapBenDrugIssueReport(reportData)).thenReturn(report);

		report.setSlNo(slNo);
		reportList.add(report);

		Assertions.assertNull(exitReport.getFacilityID());
		Assertions.assertEquals(reportList.toString(), crmReportServiceImpl.getBenDrugIssueReport(exitReport));

	}

	@Test
	void testGetInwardStockReportWithFacilityID() {
		// Setup
		ItemStockEntryReport entryReport = new ItemStockEntryReport();
		entryReport.setFacilityID(1);
		entryReport.setStartDate(Timestamp.valueOf("2023-01-01 00:00:00"));
		entryReport.setEndDate(Timestamp.valueOf("2023-01-31 23:59:59"));

		entryReport.toString();

		List<ItemStockEntryReport> mockedList = Arrays.asList(entryReport);

		InwardStockReport mockReport = new InwardStockReport();
		mockReport.setSlNo(1L);

	}

	@Test
	void testGetDailyStockDetailsReport() {
		// Implement your test for getDailyStockDetailsReport method
		ItemStockReportRepo mockRepo = mock(ItemStockReportRepo.class);
		CRMReportServiceImpl service = new CRMReportServiceImpl(mockRepo);
		ItemStockEntryReport entryReport = new ItemStockEntryReport();

		entryReport.setFactItemStockEntryID(3L);
		entryReport.setItemStockEntryID(4L);
		entryReport.setFacilityID(null);
		entryReport.setFacilityName("Medicine");
		entryReport.setFacilityDesc("For cure diseases");
		entryReport.setFacilityCode("M63465E");
		entryReport.setFacilityTypeID(6L);
		entryReport.setIsMainFacility(true);
		entryReport.setMainFacilityID(7);
		entryReport.setItemID(8);
		entryReport.setItemName("Underscore");
		entryReport.setItemDesc("Under score healthcare");
		entryReport.setItemCategoryID(9);
		entryReport.setItemCategoryName("Health Care");
		entryReport.setAlertBeforeDays("30 Days");
		entryReport.setStrength("Strong");
		entryReport.setBatchNo("B64563bv");
		entryReport.setQuantity(100);
		entryReport.setQuantityInHand(80);
		entryReport.setUnitCostPrice(Double.valueOf("12"));
		entryReport.setExpiryDate(Date.valueOf("2026-09-09"));
		entryReport.setEntryTypeID(10L);
		entryReport.setProviderServiceMapID(11);
		entryReport.setCreatedBy("A K");
		entryReport.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setMonth(8);
		entryReport.setMonthName("August");
		entryReport.setYear(2022);

		entryReport.toString();

		// Set up mock behavior for the repository
		when(mockRepo.getDailyStockDetailReportByFacilityID(any(), any(), any())).thenReturn(new ArrayList<>());

		String result = service.getDailyStockDetailsReport(entryReport);

		assertNotNull(result);

	}

	@Test
	void testGetDailyStockSummaryReport() {
		// Implement your test for getDailyStockSummaryReport method
		ItemStockReportRepo mockRepo = mock(ItemStockReportRepo.class);
		CRMReportServiceImpl service = new CRMReportServiceImpl(mockRepo);

		ItemStockEntryReport entryReport = new ItemStockEntryReport();

		entryReport.setFactItemStockEntryID(3L);
		entryReport.setItemStockEntryID(4L);
		entryReport.setFacilityID(null);
		entryReport.setFacilityName("Medicine");
		entryReport.setFacilityDesc("For cure diseases");
		entryReport.setFacilityCode("M63465E");
		entryReport.setFacilityTypeID(6L);
		entryReport.setIsMainFacility(true);
		entryReport.setMainFacilityID(7);
		entryReport.setItemID(8);
		entryReport.setItemName("Underscore");
		entryReport.setItemDesc("Under score healthcare");
		entryReport.setItemCategoryID(9);
		entryReport.setItemCategoryName("Health Care");
		entryReport.setAlertBeforeDays("30 Days");
		entryReport.setStrength("Strong");
		entryReport.setBatchNo("B64563bv");
		entryReport.setQuantity(100);
		entryReport.setQuantityInHand(80);
		entryReport.setUnitCostPrice(Double.valueOf("12"));
		entryReport.setExpiryDate(Date.valueOf("2026-09-09"));
		entryReport.setEntryTypeID(10L);
		entryReport.setProviderServiceMapID(11);
		entryReport.setCreatedBy("A K");
		entryReport.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setMonth(8);
		entryReport.setMonthName("August");
		entryReport.setYear(2022);

		entryReport.toString();

		// Set up mock behavior for the repository
		when(mockRepo.getDailyStockSummaryReportByFacilityID(any(), any(), any())).thenReturn(new ArrayList<>());

		String result = service.getDailyStockSummaryReport(entryReport);

		assertNotNull(result);
		// Add more assertions based on your expected behavior

	}

	@Test
	void testGetMonthlyReport() {
		// Implement your test for getMonthlyReport method
		ItemStockReportRepo mockRepo = mock(ItemStockReportRepo.class);
		CRMReportServiceImpl service = new CRMReportServiceImpl(mockRepo);

		ItemStockEntryReport entryReport = new ItemStockEntryReport();

		entryReport.setFactItemStockEntryID(3L);
		entryReport.setItemStockEntryID(4L);
		entryReport.setFacilityID(null);
		entryReport.setFacilityName("Medicine");
		entryReport.setFacilityDesc("For cure diseases");
		entryReport.setFacilityCode("M63465E");
		entryReport.setFacilityTypeID(6L);
		entryReport.setIsMainFacility(true);
		entryReport.setMainFacilityID(7);
		entryReport.setItemID(8);
		entryReport.setItemName("Underscore");
		entryReport.setItemDesc("Under score healthcare");
		entryReport.setItemCategoryID(9);
		entryReport.setItemCategoryName("Health Care");
		entryReport.setAlertBeforeDays("30 Days");
		entryReport.setStrength("Strong");
		entryReport.setBatchNo("B64563bv");
		entryReport.setQuantity(100);
		entryReport.setQuantityInHand(80);
		entryReport.setUnitCostPrice(Double.valueOf("12"));
		entryReport.setExpiryDate(Date.valueOf("2026-09-09"));
		entryReport.setEntryTypeID(10L);
		entryReport.setProviderServiceMapID(11);
		entryReport.setCreatedBy("A K");
		entryReport.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setMonth(8);
		entryReport.setMonthName("August");
		entryReport.setYear(2022);

		entryReport.toString();

		// Set up mock behavior for the repository
		when(mockRepo.getDailyStockDetailReportByFacilityID(any(), any(), any())).thenReturn(new ArrayList<>());

		String result = service.getMonthlyReport(entryReport);

		assertNotNull(result);
		// Add more assertions based on your expected behavior

	}

	@Test
	void testGetYearlyReport() {
		// Implement your test for getYearlyReport method
		ItemStockReportRepo mockRepo = mock(ItemStockReportRepo.class);
		CRMReportServiceImpl service = new CRMReportServiceImpl(mockRepo);

		ItemStockEntryReport entryReport = new ItemStockEntryReport();

		entryReport.setFactItemStockEntryID(3L);
		entryReport.setItemStockEntryID(4L);
		entryReport.setFacilityID(null);
		entryReport.setFacilityName("Medicine");
		entryReport.setFacilityDesc("For cure diseases");
		entryReport.setFacilityCode("M63465E");
		entryReport.setFacilityTypeID(6L);
		entryReport.setIsMainFacility(true);
		entryReport.setMainFacilityID(7);
		entryReport.setItemID(8);
		entryReport.setItemName("Underscore");
		entryReport.setItemDesc("Under score healthcare");
		entryReport.setItemCategoryID(9);
		entryReport.setItemCategoryName("Health Care");
		entryReport.setAlertBeforeDays("30 Days");
		entryReport.setStrength("Strong");
		entryReport.setBatchNo("B64563bv");
		entryReport.setQuantity(100);
		entryReport.setQuantityInHand(80);
		entryReport.setUnitCostPrice(Double.valueOf("12"));
		entryReport.setExpiryDate(Date.valueOf("2026-09-09"));
		entryReport.setEntryTypeID(10L);
		entryReport.setProviderServiceMapID(11);
		entryReport.setCreatedBy("A K");
		entryReport.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setMonth(8);
		entryReport.setMonthName("August");
		entryReport.setYear(2022);

		entryReport.toString();

		// Set up mock behavior for the repository
		when(mockRepo.getDailyStockDetailReportByFacilityID(any(), any(), any())).thenReturn(new ArrayList<>());

		String result = service.getYearlyReport(entryReport);

		assertNotNull(result);
		// Add more assertions based on your expected behavior

	}

	@Test
	void testGetShortExpiryReport() {
		// Implement your test for getShortExpiryReport method
		ItemStockReportRepo mockRepo = mock(ItemStockReportRepo.class);
		CRMReportServiceImpl service = new CRMReportServiceImpl(mockRepo);

		ItemStockEntryReport entryReport = new ItemStockEntryReport();

		entryReport.setFactItemStockEntryID(3L);
		entryReport.setItemStockEntryID(4L);
		entryReport.setFacilityID(null);
		entryReport.setFacilityName("Medicine");
		entryReport.setFacilityDesc("For cure diseases");
		entryReport.setFacilityCode("M63465E");
		entryReport.setFacilityTypeID(6L);
		entryReport.setIsMainFacility(true);
		entryReport.setMainFacilityID(7);
		entryReport.setItemID(8);
		entryReport.setItemName("Underscore");
		entryReport.setItemDesc("Under score healthcare");
		entryReport.setItemCategoryID(9);
		entryReport.setItemCategoryName("Health Care");
		entryReport.setAlertBeforeDays("30 Days");
		entryReport.setStrength("Strong");
		entryReport.setBatchNo("B64563bv");
		entryReport.setQuantity(100);
		entryReport.setQuantityInHand(80);
		entryReport.setUnitCostPrice(Double.valueOf("12"));
		entryReport.setExpiryDate(Date.valueOf("2026-09-09"));
		entryReport.setEntryTypeID(10L);
		entryReport.setProviderServiceMapID(11);
		entryReport.setCreatedBy("A K");
		entryReport.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setMonth(8);
		entryReport.setMonthName("August");
		entryReport.setYear(2022);

		entryReport.toString();

		String result = service.getShortExpiryReport(entryReport);

		assertNotNull(result);

		// Add more assertions based on your expected behavior
	}

	@Test
	void testGetTransitReport() {
		// Implement your test for getTransitReport method
		ItemStockReportRepo mockRepo = mock(ItemStockReportRepo.class);
		CRMReportServiceImpl service = new CRMReportServiceImpl(mockRepo);

		ItemStockEntryReport entryReport = new ItemStockEntryReport();

		entryReport.setFactItemStockEntryID(3L);
		entryReport.setItemStockEntryID(4L);
		entryReport.setFacilityID(null);
		entryReport.setFacilityName("Medicine");
		entryReport.setFacilityDesc("For cure diseases");
		entryReport.setFacilityCode("M63465E");
		entryReport.setFacilityTypeID(6L);
		entryReport.setIsMainFacility(true);
		entryReport.setMainFacilityID(7);
		entryReport.setItemID(8);
		entryReport.setItemName("Underscore");
		entryReport.setItemDesc("Under score healthcare");
		entryReport.setItemCategoryID(9);
		entryReport.setItemCategoryName("Health Care");
		entryReport.setAlertBeforeDays("30 Days");
		entryReport.setStrength("Strong");
		entryReport.setBatchNo("B64563bv");
		entryReport.setQuantity(100);
		entryReport.setQuantityInHand(80);
		entryReport.setUnitCostPrice(Double.valueOf("12"));
		entryReport.setExpiryDate(Date.valueOf("2026-09-09"));
		entryReport.setEntryTypeID(10L);
		entryReport.setProviderServiceMapID(11);
		entryReport.setCreatedBy("A K");
		entryReport.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		entryReport.setMonth(8);
		entryReport.setMonthName("August");
		entryReport.setYear(2022);

		entryReport.toString();

		String result = service.getTransitReport(entryReport);

		assertNotNull(result);

	}

}