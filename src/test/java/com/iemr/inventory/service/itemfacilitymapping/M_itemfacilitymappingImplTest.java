package com.iemr.inventory.service.itemfacilitymapping;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.itemfacilitymapping.M_itemfacilitymapping;
import com.iemr.inventory.data.itemfacilitymapping.V_fetchItemFacilityMap;
import com.iemr.inventory.data.items.ItemInStore;
import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.items.M_Route;
import com.iemr.inventory.data.manufacturer.M_Manufacturer;
import com.iemr.inventory.data.pharmacologicalcategory.M_Pharmacologicalcategory;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.data.uom.M_Uom;
import com.iemr.inventory.repo.stockEntry.ItemStockEntryRepo;
import com.iemr.inventory.repository.item.ItemRepo;
import com.iemr.inventory.repository.itemfacilitymapping.M_itemfacilitymappingRepo;
import com.iemr.inventory.repository.itemfacilitymapping.V_fetchItemFacilityMapRepo;

@ExtendWith(MockitoExtension.class)
class M_itemfacilitymappingImplTest {

	@InjectMocks
	M_itemfacilitymappingImpl m_itemfacilitymappingImpl;

	@Mock
	private V_fetchItemFacilityMapRepo v_fetchItemFacilityMapRepo;

	@Mock
	private M_itemfacilitymappingRepo m_itemfacilitymappingRepo;

	@Mock
	ItemStockEntryRepo itemStockEntryRepo;

	@Mock
	ItemRepo itemRepo;

	@Test
	void mapItemtoStoreTest() {

		M_itemfacilitymapping m_itemfacilitymapping = new M_itemfacilitymapping();

		m_itemfacilitymapping.setItemID(12);
		m_itemfacilitymapping.setItemStoreMapID(13);
		m_itemfacilitymapping.setFacilityID(12);
		m_itemfacilitymapping.setMappingType("Many to One");
		m_itemfacilitymapping.setStatus("Ready");
		m_itemfacilitymapping.setProviderServiceMapID(19);
		m_itemfacilitymapping.setDeleted(false);
		m_itemfacilitymapping.setCreatedBy("Kanika Mondal");
		m_itemfacilitymapping.setCreatedDate(null);
		m_itemfacilitymapping.setModifiedBy("Mamata Sarkar");
		m_itemfacilitymapping.setLastModDate(null);

		m_itemfacilitymapping.toString();

		ArrayList<M_itemfacilitymapping> data = new ArrayList<M_itemfacilitymapping>();

		data.add(m_itemfacilitymapping);

		when(m_itemfacilitymappingRepo.saveAll(data)).thenReturn(data);

		// Assertions
		Assertions.assertEquals(data, m_itemfacilitymappingImpl.mapItemtoStore(data));

	}

	@Test
	void editdataTest() {

		int itemStoreMapID = 12;

		M_itemfacilitymapping data = new M_itemfacilitymapping();

		data.setItemStoreMapID(itemStoreMapID);

		data.toString();

		when(m_itemfacilitymappingRepo.findById(itemStoreMapID)).thenReturn(Optional.of(data));

		// Assertions
		Assertions.assertEquals(data, m_itemfacilitymappingImpl.editdata(itemStoreMapID));

	}

	@Test
	void saveEditedItemTest() {

		M_itemfacilitymapping m_itemfacilitymapping = new M_itemfacilitymapping();

		m_itemfacilitymapping.setItemID(12);
		m_itemfacilitymapping.setItemStoreMapID(13);
		m_itemfacilitymapping.setFacilityID(12);
		m_itemfacilitymapping.setMappingType("Many to One");
		m_itemfacilitymapping.setStatus("Ready");
		m_itemfacilitymapping.setProviderServiceMapID(19);
		m_itemfacilitymapping.setDeleted(false);
		m_itemfacilitymapping.setCreatedBy("Kanika Mondal");
		m_itemfacilitymapping.setCreatedDate(null);
		m_itemfacilitymapping.setModifiedBy("Mamata Sarkar");
		m_itemfacilitymapping.setLastModDate(null);

		m_itemfacilitymapping.toString();

		M_itemfacilitymapping getdataforedit = m_itemfacilitymapping;

		M_itemfacilitymapping data = new M_itemfacilitymapping();

		data = getdataforedit;

		when(m_itemfacilitymappingRepo.save(getdataforedit)).thenReturn(data);

		// Assertions
		Assertions.assertEquals(data, m_itemfacilitymappingImpl.saveEditedItem(getdataforedit));

	}

	@Test
	void getsubitemforsubStoteTest() {

		Integer providerServiceMapID = 4;
		Integer facilityID = 5;

		Object[] obj = new Object[] { 5, "Many to One", false, 4 };

		ArrayList<Object[]> resultSet = new ArrayList<Object[]>();

		resultSet.add(obj);

		when(m_itemfacilitymappingRepo.getItemforSubstore(providerServiceMapID, facilityID)).thenReturn(resultSet);

		Object[] objects = new Object[] { 5, "Many to One", false, 4 };

		ArrayList<M_itemfacilitymapping> itemForsubStore = new ArrayList<M_itemfacilitymapping>();

		itemForsubStore.add(new M_itemfacilitymapping((Integer) objects[0], (String) objects[1], (Boolean) objects[2],
				(Integer) objects[3]));

		// Assertions
		Assertions.assertNotNull(resultSet);
		Assertions.assertNotNull(objects);
		Assertions.assertNotEquals(3, objects.length);
		Assertions.assertEquals(itemForsubStore,
				m_itemfacilitymappingImpl.getsubitemforsubStote(providerServiceMapID, facilityID));
	}

	@Test
	void getAllFacilityMappedDataTest() {

		Integer providerServiceMapID = 10;

		V_fetchItemFacilityMap itemDetails = new V_fetchItemFacilityMap();

		itemDetails.setItemFacilityMapID(3);
		itemDetails.setProviderServiceMapID(19);
		itemDetails.setStatus("Ready");
		itemDetails.setItemFacilityMappingDeleted(false);
		itemDetails.setFacilityID(12);
		itemDetails.setFacilityName("V");
		itemDetails.setFacilityDesc("For V");
		itemDetails.setFacilityCode("V1E");
		itemDetails.setFacilityTypeID(21);
		itemDetails.setFacilityTypeDesc("VWType");
		itemDetails.setFacilityTypeName("For V and W");
		itemDetails.setFacilityTypeCode("E1VW");
		itemDetails.setLocation("Kolkata");
		itemDetails.setPhysicalLocation("Kolkata");
		itemDetails.setStoreType("Local");
		itemDetails.setIsMainFacility(true);
		itemDetails.setIsMedical(true);
		itemDetails.setIsScheduledDrug(true);
		itemDetails.setItemCategoryID(12);
		itemDetails.setItemCategoryName("V");
		itemDetails.setItemCategoryDesc("V");
		itemDetails.setItemCategoryCode("V");
		itemDetails.setItemFormID(2);
		itemDetails.setItemFormName("W");
		itemDetails.setItemFormDesc("W");
		itemDetails.setItemFormCode("W");
		itemDetails.setPharmacologyCategoryID(346);
		itemDetails.setPharmCategoryName("dfgd");
		itemDetails.setPharmCategoryDesc("gdshjsjhdg");
		itemDetails.setPharmCategoryCode("bfhhd");
		itemDetails.setManufacturerID(3);
		itemDetails.setStrength("Strong");
		itemDetails.setRouteID(3);

		itemDetails.toString();

		ArrayList<V_fetchItemFacilityMap> data = new ArrayList<V_fetchItemFacilityMap>();

		data.add(itemDetails);

		when(v_fetchItemFacilityMapRepo.getAllFacilityMappedData(providerServiceMapID)).thenReturn(data);

		Assertions.assertEquals(data, m_itemfacilitymappingImpl.getAllFacilityMappedData(providerServiceMapID));

	}

	@Test
	void getItemMastersFromStoreIDTest() {

		Integer storeID = 3;

		ItemInStore itemInStore = new ItemInStore(12, 13, "Acer", 5L);

		itemInStore.toString();

		ArrayList<ItemInStore> itemForsubStore = new ArrayList<ItemInStore>();

		itemForsubStore.add(itemInStore);

		Object[] objArray = new Object[] { 12, 13, "Acer", 5L };

		ArrayList<Object[]> resultSet = new ArrayList<Object[]>();

		resultSet.add(objArray);

		when(m_itemfacilitymappingRepo.getItemforStore(storeID)).thenReturn(resultSet);

		Integer[] itemID = new Integer[] { 12 };

		Object[] objects = new Object[] { 12, 13, "Acer", 5L };

		ArrayList<Object[]> quant = resultSet;

		when(itemStockEntryRepo.getQuantity(itemID, storeID)).thenReturn(quant);

		Object[] objects1 = new Object[] { 12, 13, "Acer", 5L };

		// Assertions
		Assertions.assertNotEquals(0, resultSet.size());
		Assertions.assertNotNull(objects);
		Assertions.assertEquals(4, objects.length);
		Assertions.assertNotNull(objects1);
		Assertions.assertEquals(4, objects1.length);
		Assertions.assertEquals(itemForsubStore, m_itemfacilitymappingImpl.getItemMastersFromStoreID(storeID));

	}

	@Test
	void getItemMastersPartialSearchTest() {

		String item = "Medicine";
		Integer storeID = 8;

		M_Pharmacologicalcategory m_Pharmacologicalcategory = new M_Pharmacologicalcategory();

		m_Pharmacologicalcategory.setPharmCategoryID(6);
		m_Pharmacologicalcategory.setPharmCategoryName("Cancer Type");
		m_Pharmacologicalcategory
				.setPharmCategoryDesc("For cancer patients all the required medicines can be found here");
		m_Pharmacologicalcategory.setPharmCategoryCode("CAN123");
		m_Pharmacologicalcategory.setStatus("Approved and Ready");
		m_Pharmacologicalcategory.setProviderServiceMapID(67);
		m_Pharmacologicalcategory.setDeleted(false);
		m_Pharmacologicalcategory.setCreatedBy("TATA CANC MUMBAI");
		m_Pharmacologicalcategory.setModifiedBy("TATA CNC CHENNAI");

		m_Pharmacologicalcategory.toString();

		M_Manufacturer m_Manufacturer = new M_Manufacturer();

		m_Manufacturer.setManufacturerID(12);
		m_Manufacturer.setManufacturerName("Piramal PSMRI");
		m_Manufacturer.setManufacturerDesc("PSMRI Desc");
		m_Manufacturer.setStatus("Copyright Company CMMI 3");
		m_Manufacturer.setContactPerson("Mithun");
		m_Manufacturer.setcST_GST_No("143467374dfgsfhdghsgf876978");
		m_Manufacturer.setProviderServiceMapID(13);
		m_Manufacturer.setDeleted(false);
		m_Manufacturer.setCreatedBy("Piramal Sen");
		m_Manufacturer.setModifiedBy("Piramal Sen");

		m_Manufacturer.toString();

		M_Uom m_Uom = new M_Uom();

		m_Uom.setuOMID(13);
		m_Uom.setuOMName("UOMN");
		m_Uom.setuOMDesc("Used for umon purpose");
		m_Uom.setuOMCode("U888M999N000N");
		m_Uom.setStatus("Ready");
		m_Uom.setProviderServiceMapID(3);
		m_Uom.setCreatedBy(null);
		m_Uom.setCreatedBy("P Medical Company");
		m_Uom.setModifiedBy("C Medical Company");

		m_Uom.toString();

		M_Route m_Route = new M_Route();

		m_Route.setRouteID(7);
		m_Route.setRouteName("Route of Medicine");
		m_Route.setRouteDesc("It is a route for Medicines");
		m_Route.setProviderServiceMapID(8);
		m_Route.setDeleted(false);
		m_Route.setProcessed('a');
		m_Route.setCreatedBy("P Medical Company");
		m_Route.setModifiedBy("C Medical Company");

		m_Route.toString();

		ItemMaster item1 = new ItemMaster();
		item1.setItemCategoryID(12);
		item1.setQuantity(100);
		item1.setModifiedBy("A Kundu");
		item1.setCreatedBy("A Kundu");
		item1.setItemID(1);
		item1.setItemName("gxbb");
		item1.setItemDesc("ggkdvjxdhgkjfh");
		item1.setItemCode("dfjhghdj657");
		item1.setItemCategoryID(5);
		item1.setIsMedical(true);
		item1.setPharmacologyCategoryID(6);
		item1.setPharmacologyCategory(m_Pharmacologicalcategory);
		item1.setManufacturer(m_Manufacturer);
		item1.setManufacturerID(12);
		item1.setStrength("Very Strong");
		item1.setUom(m_Uom);
		item1.setUomID(13);
		item1.setRoute(m_Route);
		item1.setRouteID(7);
		item1.setIsScheduledDrug(true);
		item1.setComposition("Paracetamole");
		item1.setProviderServiceMapID(14);
		item1.setProviderServiceMap(null);
		item1.setStatus("Ready");
		item1.setDiscontinued(false);
		item1.setDeleted(false);
		item1.setProcessed('P');

		item1.toString();

		List<ItemMaster> itemForsubStore = new ArrayList<ItemMaster>();

		itemForsubStore.add(item1);

		Object[] objArr = new Object[itemForsubStore.size()];
		objArr = itemForsubStore.toArray(objArr);

		Object[] objArray2 = new Object[itemForsubStore.size()];
		objArray2 = itemForsubStore.toArray(objArray2);

		ArrayList<Object[]> resultSet = new ArrayList<Object[]>();

		resultSet.add(objArr);
		resultSet.add(objArray2);

		Integer[] itemID = new Integer[resultSet.size()];
		// itemID=resultSet.toArray(itemID);

		Object[] objects;

		objects = resultSet.get(0);
		// itemID[0] = (Integer) objects[0];

		objects = resultSet.get(1);
		// itemID[1] = (Integer) objects[1];

		when(itemRepo.findByItemIDIn(itemID)).thenReturn(itemForsubStore);

		// Assertions
		Assertions.assertNotEquals(0, resultSet.size());
		Assertions.assertNotNull(objects);
		Assertions.assertEquals(1, objects.length);
		Assertions.assertEquals(itemForsubStore, m_itemfacilitymappingImpl.getItemMastersPartialSearch(item, storeID));

	}

	@Test
	void getItemBatchForStoreTransferTest() {

		Integer facFrom = 5;
		Integer facTo = 3;
		String item = "Medicine";

		Date d1 = new Date();

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setFacilityID(5);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setExpiryDate(d1);

		itemStockEntry.toString();

		List<ItemStockEntry> itemForsubStore = new ArrayList<ItemStockEntry>();
		itemForsubStore.add(itemStockEntry);

		Object[] objArr = { 5, 3, 0, false, d1 };

		ArrayList<Object[]> resultSet = new ArrayList<Object[]>();

		resultSet.add(objArr);

		when(m_itemfacilitymappingRepo.getItemforStoreLikeItemName(facFrom, item)).thenReturn(resultSet);

		Integer[] itemID = { 5 };

		Object[] objects = { 5, 3, 0 };

		when(m_itemfacilitymappingRepo.getItemforStoreAndItemID(facTo, itemID)).thenReturn(resultSet);

		when(itemStockEntryRepo.findByFacilityIDAndItemIDInAndQuantityInHandGreaterThanAndExpiryDateAfter(facFrom,
				itemID, 0, new Date())).thenReturn(itemForsubStore);

		// Assertions
		Assertions.assertNotEquals(0, resultSet.size());
		Assertions.assertNotNull(objects);
		Assertions.assertEquals(3, objects.length);
		Assertions.assertEquals(itemForsubStore,
				m_itemfacilitymappingImpl.getItemBatchForStoreTransfer(facFrom, facTo, item));

	}

}
