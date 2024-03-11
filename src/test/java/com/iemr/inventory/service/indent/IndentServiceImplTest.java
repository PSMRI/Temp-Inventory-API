package com.iemr.inventory.service.indent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.indent.Indent;
import com.iemr.inventory.data.indent.IndentIssue;
import com.iemr.inventory.data.indent.IndentOrder;
import com.iemr.inventory.data.indent.ItemfacilitymappingIndent;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.repo.indent.IndentIssueRepo;
import com.iemr.inventory.repo.indent.IndentOrderRepo;
import com.iemr.inventory.repo.indent.IndentRepo;
import com.iemr.inventory.repo.indent.ItemfacilitymappingIndentRepo;
import com.iemr.inventory.repo.stockEntry.ItemStockEntryRepo;
import com.iemr.inventory.repo.stockExit.ItemStockExitRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@ExtendWith(MockitoExtension.class)
class IndentServiceImplTest {

	@InjectMocks
	IndentServiceImpl indentServiceImpl;

	@Mock
	EntityManager entityManager;

	@Mock
	ItemfacilitymappingIndentRepo itemfacilitymappingIndentRepo;

	@Mock
	IndentOrderRepo indentOrderRepo;

	@Mock
	IndentRepo indentRepo;

	@Mock
	IndentIssueRepo indentIssueRepo;

	@Mock
	ItemStockExitRepo itemStockExitRepo;

	@Mock
	ItemStockEntryRepo itemStockEntryRepo;

	@Mock
	private CriteriaBuilder criteriaBuilder;

	@Mock
	private CriteriaQuery<Indent> criteriaQuery;

	@Mock
	private Root<Indent> root;

	@Captor
	private ArgumentCaptor<Predicate[]> predicateCaptor;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testFindItemIndent() {
		// Mock data
		List<Object[]> mockData = new ArrayList<>();
		mockData.add(new Object[] { 1, "Code1", "Item1", true, "Strength1", "UOM1", "Category1", "Form1",
				"PharmaCategory1", "Composition1", 123, new BigDecimal(10) });
		mockData.add(new Object[] { 2, "Code2", "Item2", true, "Strength2", "UOM2", "Category2", "Form2",
				"PharmaCategory2", "Composition2", 456, new BigDecimal(20) });

		when(itemfacilitymappingIndentRepo.findindentitem(anyInt(), anyString())).thenReturn(mockData);

		// Test method invocation
		List<ItemfacilitymappingIndent> result = indentServiceImpl.findItemIndent(1, "Item1");

		// Assertions
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("Code1", result.get(0).getItemCode());
		assertEquals("Item1", result.get(0).getItemName());
		assertEquals(true, result.get(0).getIsMedical());
		assertEquals("Strength1", result.get(0).getStrength());
		assertEquals("UOM1", result.get(0).getUomName());
		assertEquals("Category1", result.get(0).getItemCategory());
		assertEquals("Form1", result.get(0).getItemForm());
		assertEquals("PharmaCategory1", result.get(0).getPharmacologicalCategoryName());
		assertEquals("Composition1", result.get(0).getComposition());
		assertEquals(123, result.get(0).getFacilityID());
		assertEquals(new BigDecimal(10), result.get(0).getQoh());
	}

	@Test
	void testGetIndentWorklist() {
		// Mock data
		IndentOrder indentOrder = new IndentOrder();
		indentOrder.setStatus("Pending");
		indentOrder.setFacilityID(123);

		indentOrder.toString();

		List<Indent> results = Arrays.asList(new Indent(), new Indent());

		CriteriaBuilder criteriaBuilderMock = mock(CriteriaBuilder.class);
		CriteriaQuery<Indent> criteriaQueryMock = mock(CriteriaQuery.class);
		Root<Indent> rootMock = mock(Root.class);
		Predicate predicateMock1 = mock(Predicate.class);
		Predicate predicateMock2 = mock(Predicate.class);
		Join<Object, Object> joinMock = mock(Join.class);

		when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilderMock);
		when(criteriaBuilderMock.createQuery(Indent.class)).thenReturn(criteriaQueryMock);
		when(criteriaQueryMock.from(Indent.class)).thenReturn(rootMock);
		when(rootMock.join(anyString(), any(JoinType.class))).thenReturn(joinMock);
		when(criteriaBuilderMock.equal(null, indentOrder.getStatus())).thenReturn(predicateMock1, predicateMock2);
		when(criteriaQueryMock.select(rootMock)).thenReturn(criteriaQueryMock);
		when(criteriaQueryMock.where(any(Predicate[].class))).thenReturn(criteriaQueryMock);
		// when(criteriaQueryMock.orderBy(any())).thenReturn(criteriaQueryMock);
		when(entityManager.createQuery(criteriaQueryMock))
				.thenReturn((TypedQuery<Indent>) mock(jakarta.persistence.Query.class));
		when(criteriaBuilderMock.desc(any())).thenReturn(mock(jakarta.persistence.criteria.Order.class));
		when(joinMock.get(anyString())).thenReturn(mock(jakarta.persistence.criteria.Path.class));
		// when(criteriaBuilderMock.between(any(), any(),
		// any())).thenReturn(mock(Predicate.class));

		// Test method invocation
		String result = indentServiceImpl.getIndentWorklist(indentOrder);

		// Assertions
		assertNotNull(result);
		assertTrue(result.contains("Indent"));

	}

	@Test
	void getIndentHistory() {

		Indent indent = new Indent();

		indent.setSyncFacilityID(1);
		indent.setOrderDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setCreatedDate(Timestamp.valueOf("2018-10-02 09:01:16"));
		indent.setStatus("Pending");
		indent.setProcessed("N");
		indent.setIndentID(1L);
		indent.setFromFacilityID(3);

		indent.toString();

		List<Indent> indentList = new ArrayList<Indent>();

		when(indentOrderRepo.getIndentHistory(indent.getFromFacilityID())).thenReturn(indentList);

		// Assertions
		Assertions.assertEquals(indentList.toString(), indentServiceImpl.getIndentHistory(indent));

	}

	@Test
	void createIndentRequestTest() {

		Indent indent = new Indent();
		indent.setIndentID(5L);
		indent.setFromFacilityID(3);
		indent.setSyncFacilityID(3);
		indent.setFromFacilityName("Medicine");
		indent.setToFacilityID(1);
		indent.setRefNo("fehfgdh");
		indent.setOrderDate(new Timestamp(System.currentTimeMillis()));
		indent.setReason("hjdfgdjhg");
		indent.setUserID(10);
		indent.setProviderServiceMapID(6);
		indent.setStatus("Pending");
		indent.setStatusReason("dhghdhgkghfkjghj");
		indent.setDeleted(false);
		indent.setProcessed("N");
		indent.setCreatedBy("Ak Hossain");
		indent.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		indent.setModifiedBy("Ak Hossain");
		indent.setLastModDate(new Timestamp(System.currentTimeMillis()));
		indent.setVanID(9L);
		indent.setParkingPlaceID(7L);
		indent.setVanSerialNo(3L);

		indent.toString();

		IndentOrder indentOrder = new IndentOrder();

		indentOrder.setIndentID(5L);
		indentOrder.setFromFacilityID(3);
		indentOrder.setSyncFacilityID(3);
		indentOrder.setProviderServiceMapID(6);
		indentOrder.setStatus("Pending");
		indentOrder.setDeleted(false);
		indentOrder.setProcessed("N");
		indentOrder.setCreatedBy("Ak Hossain");
		indentOrder.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		indentOrder.setModifiedBy("Ak Hossain");
		indentOrder.setLastModDate(new Timestamp(System.currentTimeMillis()));
		indentOrder.setVanID(9L);
		indentOrder.setParkingPlaceID(7L);
		indentOrder.setVanSerialNo(3L);

		indentOrder.toString();

		List<IndentOrder> indentOrderList = new ArrayList<IndentOrder>();
		indentOrderList.add(indentOrder);

		indent.setIndentOrder(indentOrderList);

		Indent indentCreated = indent;

		indentCreated.toString();

		when(indentRepo.save(indent)).thenReturn(indentCreated);

		Integer vanSerialNo = 3;

		when(indentRepo.updateVanSerialNo(indentCreated.getIndentID(), indentCreated.getFromFacilityID()))
				.thenReturn(vanSerialNo);

		when((List<IndentOrder>) indentOrderRepo.saveAll(indent.getIndentOrder())).thenReturn(indentOrderList);

		indentCreated.setIndentOrder(indentOrderList);

		Assertions.assertEquals(indentCreated.toString(), indentServiceImpl.createIndentRequest(indent));

	}

	@Test
	void getOrdersByIndentID() {

		Indent indent = new Indent();

		indent.setSyncFacilityID(1);

		indent.toString();

		IndentOrder indentOrder = new IndentOrder();

		indentOrder.setSyncFacilityID(3);
		indentOrder.setIndentID(5L);
		indentOrder.setVanID(9L);
		indentOrder.setProviderServiceMapID(6);
		indentOrder.setCreatedBy("Ak Hossain");
		indentOrder.setCreatedDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indentOrder.setParkingPlaceID(7L);
		indentOrder.setStatus("Pending");
		indentOrder.setProcessed("N");
		indentOrder.setFromFacilityID(3);

		indentOrder.toString();

		List<IndentOrder> indentOrderList = new ArrayList<IndentOrder>();

		indentOrderList.add(indentOrder);

		when(indentRepo.findByIndentID(indentOrder.getIndentID())).thenReturn(indent);

		when(indentOrderRepo.getOrdersByIndentID(indent.getVanSerialNo(), indent.getSyncFacilityID()))
				.thenReturn(indentOrderList);

		// Assertions
		Assertions.assertEquals(indentOrderList.toString(), indentServiceImpl.getOrdersByIndentID(indentOrder));

	}

	@Test
	void getIndentOrderWorklistTest() {

		IndentOrder indentOrder = new IndentOrder();

		indentOrder.setSyncFacilityID(3);
		indentOrder.setIndentID(5L);
		indentOrder.setVanID(9L);
		indentOrder.setProviderServiceMapID(6);
		indentOrder.setCreatedBy("Ak Hossain");
		indentOrder.setCreatedDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indentOrder.setParkingPlaceID(7L);
		indentOrder.setStatus("Pending");
		indentOrder.setProcessed("N");
		indentOrder.setFromFacilityID(3);
		indentOrder.setVanSerialNo(5L);

		indentOrder.toString();

		Indent indent = new Indent();

		indent.setSyncFacilityID(3);
		indent.setOrderDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setCreatedDate(Timestamp.valueOf("2018-10-02 09:01:16"));
		indent.setStatus("Pending");
		indent.setProcessed("N");
		indent.setIndentID(1L);
		indent.setFromFacilityID(3);
		indent.setVanSerialNo(5L);

		indent.toString();

		List<IndentOrder> list1 = new ArrayList<IndentOrder>();

		list1.add(indentOrder);

		when(indentRepo.findByIndentID(indentOrder.getIndentID())).thenReturn(indent);

		when(indentOrderRepo.getOrdersByIndentID(indent.getVanSerialNo(), indent.getSyncFacilityID()))
				.thenReturn(list1);

		// Assertions
		Assertions.assertEquals(list1.toString(), indentServiceImpl.getIndentOrderWorklist(indentOrder));

	}

	@Test
	void issueIndentDispenseTest() {

		IndentIssue indentIssue = new IndentIssue();

		indentIssue.setAction("Issued");
		indentIssue.setIndentIssueID(3L);
		indentIssue.setIndentOrderID(5L);
		indentIssue.setIndentID(5L);
		indentIssue.setItemID(3);
		indentIssue.setItemName("ARBAAZ");
		indentIssue.setIssuedQty(100);
		indentIssue.setIssueDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indentIssue.setRemarks("Excellent");
		indentIssue.setVanID(9L);
		indentIssue.setProviderServiceMapID(6);
		indentIssue.setCreatedBy("Ak Hossain");
		indentIssue.setCreatedDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indentIssue.setParkingPlaceID(7L);
		indentIssue.setStatus("Pending");
		indentIssue.setDeleted(false);
		indentIssue.setProcessed("N");
		indentIssue.setFromFacilityID(3);
		indentIssue.setVanSerialNo(5L);
		indentIssue.setModifiedBy("Ak Hossain");
		indentIssue.setLastModDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		indentIssue.setItemStockEntryID(5L);
		indentIssue.setBatchNo("B73673Tach");
		indentIssue.setToFacilityID(6);
		indentIssue.setUnitCostPrice(Double.valueOf("10000"));
		indentIssue.setSyncFacilityID(3);
		indentIssue.setIsManual(false);
		indentIssue.setRejectedReason("No Reason");

		indentIssue.toString();

		List<IndentIssue> list = new ArrayList<IndentIssue>();

		list.add(indentIssue);

		IndentIssue[] array = new IndentIssue[list.size()];
		array = list.toArray(array);

		Indent indent = new Indent();

		indent.setSyncFacilityID(3);
		indent.setOrderDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setCreatedDate(Timestamp.valueOf("2018-10-02 09:01:16"));
		indent.setStatus("Pending");
		indent.setProcessed("N");
		indent.setIndentID(5L);
		indent.setFromFacilityID(3);
		indent.setVanSerialNo(5L);

		indent.toString();

		when(indentRepo.findByIndentID(list.get(0).getIndentID())).thenReturn(indent);

		Assertions.assertEquals("Dispensed successfully", indentServiceImpl.issueIndent(array));

	}

	@Test
	void issueIndentRejectedTest() {

		IndentIssue indentIssue = new IndentIssue();

		indentIssue.setAction("Rejected");
		indentIssue.setIndentIssueID(3L);
		indentIssue.setIndentOrderID(5L);
		indentIssue.setIndentID(5L);
		indentIssue.setItemID(3);
		indentIssue.setItemName("ARBAAZ");
		indentIssue.setIssuedQty(100);
		indentIssue.setIssueDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indentIssue.setRemarks("Excellent");
		indentIssue.setVanID(9L);
		indentIssue.setProviderServiceMapID(6);
		indentIssue.setCreatedBy("Ak Hossain");
		indentIssue.setCreatedDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indentIssue.setParkingPlaceID(7L);
		indentIssue.setStatus("Pending");
		indentIssue.setDeleted(false);
		indentIssue.setProcessed("N");
		indentIssue.setFromFacilityID(3);
		indentIssue.setVanSerialNo(5L);
		indentIssue.setModifiedBy("Ak Hossain");
		indentIssue.setLastModDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		indentIssue.setItemStockEntryID(5L);
		indentIssue.setBatchNo("B73673Tach");
		indentIssue.setToFacilityID(6);
		indentIssue.setUnitCostPrice(Double.valueOf("10000"));
		indentIssue.setSyncFacilityID(3);
		indentIssue.setIsManual(false);
		indentIssue.setRejectedReason("No Reason");

		indentIssue.toString();

		List<IndentIssue> list = new ArrayList<IndentIssue>();

		list.add(indentIssue);

		IndentIssue[] array = new IndentIssue[list.size()];
		array = list.toArray(array);

		Indent indent = new Indent();

		indent.setSyncFacilityID(3);
		indent.setOrderDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setCreatedDate(Timestamp.valueOf("2018-10-02 09:01:16"));
		indent.setStatus("Pending");
		indent.setProcessed("N");
		indent.setIndentID(5L);
		indent.setFromFacilityID(3);
		indent.setVanSerialNo(5L);

		indent.toString();

		when(indentRepo.findByIndentID(list.get(0).getIndentID())).thenReturn(indent);

		Assertions.assertEquals("Rejected successfully", indentServiceImpl.issueIndent(array));
		;

	}

	@Test
	void cancelIndentOrderTest() {

		Indent indent1 = new Indent();

		indent1.setSyncFacilityID(3);
		indent1.setOrderDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent1.setCreatedDate(Timestamp.valueOf("2018-10-02 09:01:16"));
		indent1.setStatus("Pending");
		indent1.setProcessed("N");
		indent1.setIndentID(5L);
		indent1.setFromFacilityID(3);
		indent1.setVanSerialNo(5L);

		indent1.toString();

		Indent indent = indent1;

		when(indentRepo.findByIndentID(indent1.getIndentID())).thenReturn(indent);

		indentOrderRepo.cancelIndent(indent.getIndentID());
		indentOrderRepo.cancelIndentOrder(indent.getVanSerialNo(), indent.getSyncFacilityID());

		// Assertions
		Assertions.assertEquals("Cancelled successfully", indentServiceImpl.cancelIndentOrder(indent1));

	}

	@Test
	void receiveIndentTest() {

		Indent indent = new Indent();

		indent.setSyncFacilityID(3);
		indent.setOrderDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setCreatedDate(Timestamp.valueOf("2018-10-02 09:01:16"));
		indent.setStatus("Pending");
		indent.setProcessed("N");
		indent.setIndentID(5L);
		indent.setFromFacilityID(3);
		indent.setVanSerialNo(5L);
		indent.setCreatedBy("AP Malhotra");

		indent.toString();

		Indent idn = indent;

		when(indentRepo.findByIndentID(indent.getIndentID())).thenReturn(idn);

		IndentIssue indentIssue = new IndentIssue();

		indentIssue.setAction("Issued");
		indentIssue.setIndentIssueID(3L);
		indentIssue.setIndentOrderID(5L);
		indentIssue.setIndentID(5L);
		indentIssue.setItemID(3);
		indentIssue.setItemName("ARBAAZ");
		indentIssue.setIssuedQty(100);
		indentIssue.setIssueDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indentIssue.setRemarks("Excellent");
		indentIssue.setVanID(9L);
		indentIssue.setProviderServiceMapID(6);
		indentIssue.setCreatedBy("Ak Hossain");
		indentIssue.setCreatedDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indentIssue.setParkingPlaceID(7L);
		indentIssue.setStatus("Pending");
		indentIssue.setDeleted(false);
		indentIssue.setProcessed("N");
		indentIssue.setFromFacilityID(3);
		indentIssue.setVanSerialNo(5L);
		indentIssue.setModifiedBy("Ak Hossain");
		indentIssue.setLastModDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		indentIssue.setItemStockEntryID(5L);
		indentIssue.setBatchNo("B73673Tach");
		indentIssue.setToFacilityID(6);
		indentIssue.setUnitCostPrice(Double.valueOf("10000"));
		indentIssue.setSyncFacilityID(3);
		indentIssue.setIsManual(false);
		indentIssue.setRejectedReason("No Reason");
		indentIssue.setExpiryDate(Timestamp.valueOf("2018-09-02 09:03:16"));

		indentIssue.toString();

		List<IndentIssue> issuedList = new ArrayList<IndentIssue>();

		issuedList.add(indentIssue);
		
		when(indentOrderRepo.acceptIndent(indent.getIndentID(), indent.getFromFacilityID())).thenReturn(5);
		when(indentOrderRepo.acceptIndentOrder(idn.getVanSerialNo(), idn.getSyncFacilityID())).thenReturn(5);

		when(indentOrderRepo.getIndentIssued(idn.getVanSerialNo(), idn.getToFacilityID())).thenReturn(issuedList);

		ItemStockEntry stockEntry = new ItemStockEntry();
		stockEntry.setFacilityID(indent.getFromFacilityID());
		stockEntry.setItemID(indentIssue.getItemID());
		stockEntry.setQuantity(indentIssue.getIssuedQty());
		stockEntry.setQuantityInHand(indentIssue.getIssuedQty());
		stockEntry.setTotalCostPrice(indentIssue.getUnitCostPrice());
		stockEntry.setBatchNo(indentIssue.getBatchNo());
		stockEntry.setExpiryDate(indentIssue.getExpiryDate());
		stockEntry.setEntryTypeID(indentIssue.getIndentID());
		stockEntry.setEntryType("Indent");
		stockEntry.setCreatedBy(indent.getCreatedBy());
		stockEntry.setCreatedDate(Timestamp.valueOf("2018-10-02 09:01:16"));
		stockEntry.setSyncFacilityID(idn.getFromFacilityID());

		stockEntry.toString();

		List<ItemStockEntry> itemStockList = new ArrayList<ItemStockEntry>();
		itemStockList.add(stockEntry);
		
		when(itemStockEntryRepo.saveAll(itemStockList)).thenReturn(itemStockList);

		when(itemStockEntryRepo.updateItemStockEntryVanSerialNo()).thenReturn(5);

		Assertions.assertEquals("Received successfully", indentServiceImpl.receiveIndent(indent));

	}

	@Test
	void updateIndentOrderTest() {

		Indent indent = new Indent();

		indent.setSyncFacilityID(3);
		indent.setOrderDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setCreatedDate(Timestamp.valueOf("2018-10-02 09:01:16"));
		indent.setStatus("Pending");
		indent.setProcessed("N");
		indent.setIndentID(5L);
		indent.setFromFacilityID(3);
		indent.setVanSerialNo(5L);
		indent.setCreatedBy("AP Malhotra");
		indent.setVanID(1L);
		indent.setProviderServiceMapID(2);
		indent.setCreatedBy("AP Malhotra");

		indent.toString();

		Indent indentCreated = indent;
		when(indentRepo.save(indent)).thenReturn(indentCreated);

		IndentOrder indentOrder = new IndentOrder();

		indentOrder.setIndentID(indentCreated.getIndentID());
		indentOrder.setVanID(indentCreated.getVanID());
		indentOrder.setProviderServiceMapID(indentCreated.getProviderServiceMapID());
		indentOrder.setCreatedBy(indentCreated.getCreatedBy());
		indentOrder.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		indentOrder.setParkingPlaceID(indentCreated.getParkingPlaceID());
		indentOrder.setStatus("Pending");
		indentOrder.setProcessed("N");
		indentOrder.setFromFacilityID(indentCreated.getFromFacilityID());
		indentOrder.setSyncFacilityID(indentCreated.getSyncFacilityID());

		indentOrder.toString();

		List<IndentOrder> indentOrderL = new ArrayList<IndentOrder>();

		indentOrderL.add(indentOrder);

		indentCreated.setIndentOrder(indentOrderL);

		Assertions.assertNotNull(indentOrder);
		Assertions.assertNotNull(indentCreated);
		Assertions.assertNotNull(indentCreated.getSyncFacilityID());
		Assertions.assertEquals("Updated successfully", indentServiceImpl.updateIndentOrder(indent));

	}

}
