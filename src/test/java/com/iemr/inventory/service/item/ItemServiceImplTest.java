package com.iemr.inventory.service.item;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.items.M_ItemCategory;
import com.iemr.inventory.data.items.M_ItemForm;
import com.iemr.inventory.data.items.M_Route;
import com.iemr.inventory.repository.item.ItemCategoryRepo;
import com.iemr.inventory.repository.item.ItemFormRepo;
import com.iemr.inventory.repository.item.ItemRepo;
import com.iemr.inventory.repository.item.RouteRepo;
import com.iemr.inventory.repository.itemfacilitymapping.M_itemfacilitymappingRepo;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

	@InjectMocks
	ItemServiceImpl itemServiceImpl;

	@Mock
	private ItemRepo itemRepo;

	@Mock
	private ItemCategoryRepo itemCategoryRepo;

	@Mock
	private RouteRepo routeRepo;

	@Mock
	private ItemFormRepo itemFormRepo;

	@Mock
	M_itemfacilitymappingRepo itemfacilitymappingRepo;

	@Test
	void createItemMasterTest() {

		ItemMaster itemMaster = new ItemMaster();

		itemMaster.setItemID(3);
		itemMaster.setItemName("Tynenol 500");
		itemMaster.setItemDesc("It is a medicine for headache");
		itemMaster.setItemCode("P01C78L");
		itemMaster.setItemCategoryID(345);
		itemMaster.setIsMedical(true);
		itemMaster.setItemFormID(789);
		itemMaster.setPharmacologyCategoryID(56);
		itemMaster.setManufacturerID(5);
		itemMaster.setStrength("Strong");
		itemMaster.setUomID(6);
		itemMaster.setIsScheduledDrug(true);
		itemMaster.setComposition("Paracitamol");
		itemMaster.setRouteID(7);
		itemMaster.setProviderServiceMapID(8);
		itemMaster.setStatus("Approved and Useable");
		itemMaster.setDiscontinued(false);
		itemMaster.setDeleted(false);
		itemMaster.setProcessed('a');
		itemMaster.setCreatedBy("P Medical Company");
		itemMaster.setCreatedDate(Date.valueOf("2000-08-08"));
		itemMaster.setModifiedBy("C Medical Company");
		itemMaster.setLastModDate(Date.valueOf("2001-09-09"));
		itemMaster.setQuantity(500);
		itemMaster.setFacilityID(23);

		itemMaster.toString();

		when(itemRepo.save(itemMaster)).thenReturn(itemMaster);

		// Assertions
		Assertions.assertEquals(itemMaster, itemServiceImpl.createItemMaster(itemMaster));

	}

	@Test
	void getItemCategoryAllTest() {

		Boolean all = true;
		Integer providerServiceMapID = Integer.valueOf(7);

		M_ItemCategory m_ItemCategory = new M_ItemCategory();

		m_ItemCategory.setItemCategoryID(5);
		m_ItemCategory.setAlertBeforeDays(15);
		m_ItemCategory.setIssueType("Expiry");
		m_ItemCategory.setItemCategoryName("Paracitamol");
		m_ItemCategory.setItemCategoryDesc("It is a medicine for headache");
		m_ItemCategory.setItemCategoryCode("P01C78L");
		m_ItemCategory.setStatus("Approved and Useable");
		m_ItemCategory.setProviderServiceMapID(8);
		m_ItemCategory.setDeleted(false);
		m_ItemCategory.setProcessed('a');
		m_ItemCategory.setCreatedBy("P Medical Company");
		m_ItemCategory.setCreatedDate(Date.valueOf("2000-08-08"));
		m_ItemCategory.setModifiedBy("C Medical Company");
		m_ItemCategory.setLastModDate(Date.valueOf("2001-09-09"));

		m_ItemCategory.toString();

		List<M_ItemCategory> itemCategory = new ArrayList<M_ItemCategory>();

		itemCategory.add(m_ItemCategory);

		when(itemCategoryRepo.findByProviderServiceMapID(providerServiceMapID)).thenReturn(itemCategory);

		// Assertions
		Assertions.assertNotNull(providerServiceMapID);
		Assertions.assertEquals(true, all);
		Assertions.assertEquals(itemCategory, itemServiceImpl.getItemCategory(all, providerServiceMapID));

	}

	@Test
	void getItemCategoryNotAllTest() {

		Boolean all = false;
		Integer providerServiceMapID = Integer.valueOf(7);

		M_ItemCategory m_ItemCategory = new M_ItemCategory();

		m_ItemCategory.setItemCategoryID(5);
		m_ItemCategory.setAlertBeforeDays(15);
		m_ItemCategory.setIssueType("Expiry");
		m_ItemCategory.setItemCategoryName("Paracitamol");
		m_ItemCategory.setItemCategoryDesc("It is a medicine for headache");
		m_ItemCategory.setItemCategoryCode("P01C78L");
		m_ItemCategory.setStatus("Approved and Useable");
		m_ItemCategory.setProviderServiceMapID(8);
		m_ItemCategory.setDeleted(true);
		m_ItemCategory.setProcessed('a');
		m_ItemCategory.setCreatedBy("P Medical Company");
		m_ItemCategory.setCreatedDate(Date.valueOf("2000-08-08"));
		m_ItemCategory.setModifiedBy("C Medical Company");
		m_ItemCategory.setLastModDate(Date.valueOf("2001-09-09"));

		m_ItemCategory.toString();

		List<M_ItemCategory> itemCategory = new ArrayList<M_ItemCategory>();

		itemCategory.add(m_ItemCategory);

		when(itemCategoryRepo.findByDeletedAndProviderServiceMapID(false, providerServiceMapID))
				.thenReturn(itemCategory);

		// Assertions
		Assertions.assertNotNull(providerServiceMapID);
		Assertions.assertEquals(false, all);
		Assertions.assertEquals(itemCategory, itemServiceImpl.getItemCategory(all, providerServiceMapID));

	}

	@Test
	void getItemRouteAllTest() {

		Boolean all = true;

		M_Route m_Route = new M_Route();

		m_Route.setRouteID(7);
		m_Route.setRouteName("Route of Medicine");
		m_Route.setRouteDesc("It is a route for Medicines");
		m_Route.setProviderServiceMapID(8);
		m_Route.setDeleted(false);
		m_Route.setProcessed('a');
		m_Route.setCreatedBy("P Medical Company");
		m_Route.setCreatedDate(Date.valueOf("2000-08-08"));
		m_Route.setModifiedBy("C Medical Company");
		m_Route.setLastModDate(Date.valueOf("2001-09-09"));

		m_Route.toString();

		List<M_Route> route = new ArrayList<M_Route>();

		route.add(m_Route);

		when(routeRepo.getAll()).thenReturn(route);

		// Assertions
		Assertions.assertEquals(true, all);
		Assertions.assertEquals(route, itemServiceImpl.getItemRoute(all));

	}

	@Test
	void getItemRouteNotAllTest() {

		Boolean all = false;

		M_Route m_Route = new M_Route();

		m_Route.setRouteID(7);
		m_Route.setRouteName("Route of Medicine");
		m_Route.setRouteDesc("It is a route for Medicines");
		m_Route.setProviderServiceMapID(8);
		m_Route.setDeleted(true);
		m_Route.setProcessed('a');
		m_Route.setCreatedBy("P Medical Company");
		m_Route.setCreatedDate(Date.valueOf("2000-08-08"));
		m_Route.setModifiedBy("C Medical Company");
		m_Route.setLastModDate(Date.valueOf("2001-09-09"));

		m_Route.toString();

		List<M_Route> route = new ArrayList<M_Route>();

		route.add(m_Route);

		when(routeRepo.findByDeleted(false)).thenReturn(route);

		// Assertions
		Assertions.assertEquals(false, all);
		Assertions.assertEquals(route, itemServiceImpl.getItemRoute(all));

	}

	@Test
	void getItemFormAllTest() {

		Boolean all = true;

		M_ItemForm m_ItemForm = new M_ItemForm();

		m_ItemForm.setItemFormID(12);
		m_ItemForm.setItemForm("Sustainable");
		m_ItemForm.setItemFormDesc("To make the medicines also environment friendly and sustainable");
		m_ItemForm.setProviderServiceMapID(8);
		m_ItemForm.setDeleted(false);
		m_ItemForm.setProcessed('a');
		m_ItemForm.setCreatedBy("P Medical Company");
		m_ItemForm.setCreatedDate(Date.valueOf("2000-08-08"));
		m_ItemForm.setModifiedBy("C Medical Company");
		m_ItemForm.setLastModDate(Date.valueOf("2001-09-09"));

		m_ItemForm.toString();

		List<M_ItemForm> route = new ArrayList<M_ItemForm>();

		route.add(m_ItemForm);

		when(itemFormRepo.getAll()).thenReturn(route);

		// Assertions
		Assertions.assertEquals(true, all);
		Assertions.assertEquals(route, itemServiceImpl.getItemForm(all));

	}

	@Test
	void getItemFormNotAllTest() {

		Boolean all = false;

		M_ItemForm m_ItemForm = new M_ItemForm();

		m_ItemForm.setItemFormID(12);
		m_ItemForm.setItemForm("Sustainable");
		m_ItemForm.setItemFormDesc("To make the medicines also environment friendly and sustainable");
		m_ItemForm.setProviderServiceMapID(8);
		m_ItemForm.setDeleted(true);
		m_ItemForm.setProcessed('a');
		m_ItemForm.setCreatedBy("P Medical Company");
		m_ItemForm.setCreatedDate(Date.valueOf("2000-08-08"));
		m_ItemForm.setModifiedBy("C Medical Company");
		m_ItemForm.setLastModDate(Date.valueOf("2001-09-09"));

		m_ItemForm.toString();

		List<M_ItemForm> route = new ArrayList<M_ItemForm>();

		route.add(m_ItemForm);

		when(itemFormRepo.findByDeleted(false)).thenReturn(route);

		// Assertions
		Assertions.assertEquals(false, all);
		Assertions.assertEquals(route, itemServiceImpl.getItemForm(all));

	}

	@Test
	void getItemMasterTest() {

		Integer providerServiceMapID = Integer.valueOf(8);

		ItemMaster itemMaster = new ItemMaster();

		itemMaster.setItemID(3);
		itemMaster.setItemName("Tynenol 500");
		itemMaster.setItemDesc("It is a medicine for headache");
		itemMaster.setItemCode("P01C78L");
		itemMaster.setItemCategoryID(345);
		itemMaster.setIsMedical(true);
		itemMaster.setItemFormID(789);
		itemMaster.setPharmacologyCategoryID(56);
		itemMaster.setManufacturerID(5);
		itemMaster.setStrength("Strong");
		itemMaster.setUomID(6);
		itemMaster.setIsScheduledDrug(true);
		itemMaster.setComposition("Paracitamol");
		itemMaster.setRouteID(7);
		itemMaster.setProviderServiceMapID(8);
		itemMaster.setStatus("Approved and Useable");
		itemMaster.setDiscontinued(false);
		itemMaster.setDeleted(false);
		itemMaster.setProcessed('a');
		itemMaster.setCreatedBy("P Medical Company");
		itemMaster.setCreatedDate(Date.valueOf("2000-08-08"));
		itemMaster.setModifiedBy("C Medical Company");
		itemMaster.setLastModDate(Date.valueOf("2001-09-09"));
		itemMaster.setQuantity(500);
		itemMaster.setFacilityID(23);

		itemMaster.toString();

		List<ItemMaster> itemMasterList = new ArrayList<ItemMaster>();

		itemMasterList.add(itemMaster);

		when(itemRepo.findByProviderServiceMapID(providerServiceMapID)).thenReturn(itemMasterList);

		// Assertions
		Assertions.assertEquals(itemMasterList, itemServiceImpl.getItemMaster(providerServiceMapID));

	}

	@Test
	void blockItemMasterTest() {

		Integer itemmasterID = Integer.valueOf(1);
		Boolean deleteflag = false;

		when(itemRepo.deleteItemMaster(itemmasterID, deleteflag)).thenReturn(itemmasterID);

		// Assertions
		Assertions.assertEquals(itemmasterID, itemServiceImpl.blockItemMaster(itemmasterID, deleteflag));
	}

	@Test
	void discontinueItemMasterTest() {

		Integer itemmasterID = Integer.valueOf(2);
		Boolean continueflag = false;

		when(itemRepo.discontinueItemMaster(itemmasterID, continueflag)).thenReturn(itemmasterID);

		// Assertions
		Assertions.assertEquals(itemmasterID, itemServiceImpl.discontinueItemMaster(itemmasterID, continueflag));
	}

	@Test
	void addAllItemMasterTest() {

		ItemMaster itemMaster2 = new ItemMaster();

		itemMaster2.setItemID(3);
		itemMaster2.setItemName("Tynenol 500");
		itemMaster2.setItemDesc("It is a medicine for headache");
		itemMaster2.setItemCode("P01C78L");
		itemMaster2.setItemCategoryID(345);
		itemMaster2.setIsMedical(true);
		itemMaster2.setItemFormID(789);
		itemMaster2.setPharmacologyCategoryID(56);
		itemMaster2.setManufacturerID(5);
		itemMaster2.setStrength("Strong");
		itemMaster2.setUomID(6);
		itemMaster2.setIsScheduledDrug(true);
		itemMaster2.setComposition("Paracitamol");
		itemMaster2.setRouteID(7);
		itemMaster2.setProviderServiceMapID(8);
		itemMaster2.setStatus("Approved and Useable");
		itemMaster2.setDiscontinued(false);
		itemMaster2.setDeleted(false);
		itemMaster2.setProcessed('a');
		itemMaster2.setCreatedBy("P Medical Company");
		itemMaster2.setCreatedDate(Date.valueOf("2000-08-08"));
		itemMaster2.setModifiedBy("C Medical Company");
		itemMaster2.setLastModDate(Date.valueOf("2001-09-09"));
		itemMaster2.setQuantity(500);
		itemMaster2.setFacilityID(23);

		List<ItemMaster> itemMaster = new ArrayList<ItemMaster>();

		itemMaster.add(itemMaster2);

		when(itemRepo.saveAll(itemMaster)).thenReturn(itemMaster);

		// Assertions
		Assertions.assertEquals(itemMaster, itemServiceImpl.addAllItemMaster(itemMaster));
	}

	@Test
	void getItemMasterByIDTest() {

		Integer itemMasterID = Integer.valueOf(1);

		ItemMaster itemMaster = new ItemMaster(itemMasterID, "Tynenol 500");

		itemMaster.setItemID(3);
		itemMaster.setItemName("Tynenol 500");
		itemMaster.setItemDesc("It is a medicine for headache");
		itemMaster.setItemCode("P01C78L");
		itemMaster.setItemCategoryID(345);
		itemMaster.setIsMedical(true);
		itemMaster.setItemFormID(789);
		itemMaster.setPharmacologyCategoryID(56);
		itemMaster.setManufacturerID(5);
		itemMaster.setStrength("Strong");
		itemMaster.setUomID(6);
		itemMaster.setIsScheduledDrug(true);
		itemMaster.setComposition("Paracitamol");
		itemMaster.setRouteID(7);
		itemMaster.setProviderServiceMapID(8);
		itemMaster.setStatus("Approved and Useable");
		itemMaster.setDiscontinued(false);
		itemMaster.setDeleted(false);
		itemMaster.setProcessed('a');
		itemMaster.setCreatedBy("P Medical Company");
		itemMaster.setCreatedDate(Date.valueOf("2000-08-08"));
		itemMaster.setModifiedBy("C Medical Company");
		itemMaster.setLastModDate(Date.valueOf("2001-09-09"));
		itemMaster.setQuantity(500);
		itemMaster.setFacilityID(23);

		itemMaster.toString();

		when(itemRepo.findById(itemMasterID)).thenReturn(Optional.of(itemMaster));

		// Assertions
		Assertions.assertEquals(itemMaster, itemServiceImpl.getItemMasterByID(itemMasterID));

	}

	@Test
	void getItemMasterCatByIDTest() {

		Integer itemMasterID = Integer.valueOf(1);

		ItemMaster itemMaster = new ItemMaster();

		itemMaster.setItemID(3);
		itemMaster.setItemName("Tynenol 500");
		itemMaster.setItemDesc("It is a medicine for headache");
		itemMaster.setItemCode("P01C78L");
		itemMaster.setItemCategoryID(345);
		itemMaster.setIsMedical(true);
		itemMaster.setItemFormID(789);
		itemMaster.setPharmacologyCategoryID(56);
		itemMaster.setManufacturerID(5);
		itemMaster.setStrength("Strong");
		itemMaster.setUomID(6);
		itemMaster.setIsScheduledDrug(true);
		itemMaster.setComposition("Paracitamol");
		itemMaster.setRouteID(7);
		itemMaster.setProviderServiceMapID(8);
		itemMaster.setStatus("Approved and Useable");
		itemMaster.setDiscontinued(false);
		itemMaster.setDeleted(false);
		itemMaster.setProcessed('a');
		itemMaster.setCreatedBy("P Medical Company");
		itemMaster.setCreatedDate(Date.valueOf("2000-08-08"));
		itemMaster.setModifiedBy("C Medical Company");
		itemMaster.setLastModDate(Date.valueOf("2001-09-09"));
		itemMaster.setQuantity(500);
		itemMaster.setFacilityID(23);

		itemMaster.toString();

		when(itemRepo.findDetailOne(itemMasterID)).thenReturn(itemMaster);

		// Assertions
		Assertions.assertEquals(itemMaster, itemServiceImpl.getItemMasterCatByID(itemMasterID));

	}

	@Test
	void updateItemIssueConfigTest() {

		M_ItemCategory m_ItemCategory = new M_ItemCategory();

		m_ItemCategory.setItemCategoryID(50);
		m_ItemCategory.setAlertBeforeDays(151);
		m_ItemCategory.setIssueType("Expiry");
		m_ItemCategory.setItemCategoryName("Paracitamol2");
		m_ItemCategory.setItemCategoryDesc("It is a medicine for headache");
		m_ItemCategory.setItemCategoryCode("P01C78L91112");
		m_ItemCategory.setStatus("Approved and Useable");
		m_ItemCategory.setProviderServiceMapID(89);
		m_ItemCategory.setDeleted(false);
		m_ItemCategory.setProcessed('a');
		m_ItemCategory.setCreatedBy("P Medical Company");
		m_ItemCategory.setCreatedDate(Date.valueOf("2000-08-08"));
		m_ItemCategory.setModifiedBy("C Medical Company");
		m_ItemCategory.setLastModDate(Date.valueOf("2001-09-09"));

		m_ItemCategory.toString();

		List<M_ItemCategory> itemCategory = new ArrayList<M_ItemCategory>();

		itemCategory.add(m_ItemCategory);

		int cnt = 0;

		cnt = cnt + m_ItemCategory.getItemCategoryID();

		when(itemCategoryRepo.updateIssueConfig(m_ItemCategory.getItemCategoryID(), m_ItemCategory.getIssueType()))
				.thenReturn(cnt);

		// Assertions
		Assertions.assertNotNull(m_ItemCategory.getItemCategoryID());
		Assertions.assertNotNull(m_ItemCategory.getIssueType());
		Assertions.assertEquals(cnt, itemServiceImpl.updateItemIssueConfig(itemCategory));

	}

	@Test
	void getItemRouteProviderServiceMapIDTest() {

		Integer providerServiceMapID = Integer.valueOf(8);

		M_Route m_Route = new M_Route();

		m_Route.setRouteID(7);
		m_Route.setRouteName("Route of Medicine");
		m_Route.setRouteDesc("It is a route for Medicines");
		m_Route.setProviderServiceMapID(8);
		m_Route.setDeleted(false);
		m_Route.setProcessed('a');
		m_Route.setCreatedBy("P Medical Company");
		m_Route.setCreatedDate(Date.valueOf("2000-08-08"));
		m_Route.setModifiedBy("C Medical Company");
		m_Route.setLastModDate(Date.valueOf("2001-09-09"));

		m_Route.toString();

		List<M_Route> route = new ArrayList<M_Route>();

		route.add(m_Route);

		when(routeRepo.findByProviderServiceMapID(providerServiceMapID)).thenReturn(route);

		// Assertions
		Assertions.assertEquals(route, itemServiceImpl.getItemRouteProviderServiceMapID(providerServiceMapID));

	}

	@Test
	void getItemFormProviderServiceMapIDTest() {

		Integer providerServiceMapID = Integer.valueOf(8);

		M_ItemForm m_ItemForm = new M_ItemForm();

		m_ItemForm.setItemFormID(12);
		m_ItemForm.setItemForm("Sustainable");
		m_ItemForm.setItemFormDesc("To make the medicines also environment friendly and sustainable");
		m_ItemForm.setProviderServiceMapID(8);
		m_ItemForm.setDeleted(false);
		m_ItemForm.setProcessed('a');
		m_ItemForm.setCreatedBy("P Medical Company");
		m_ItemForm.setCreatedDate(Date.valueOf("2000-08-08"));
		m_ItemForm.setModifiedBy("C Medical Company");
		m_ItemForm.setLastModDate(Date.valueOf("2001-09-09"));

		m_ItemForm.toString();

		List<M_ItemForm> route = new ArrayList<M_ItemForm>();

		route.add(m_ItemForm);

		when(itemFormRepo.findByProviderServiceMapID(providerServiceMapID)).thenReturn(route);

		// Assertions
		Assertions.assertEquals(route, itemServiceImpl.getItemFormProviderServiceMapID(providerServiceMapID));

	}

	@Test
	void getItemMastersTest() {

		Integer providerServiceMapID = Integer.valueOf(8);
		Integer itemCategoryID = Integer.valueOf(12);

		ItemMaster itemMaster = new ItemMaster();

		itemMaster.setItemID(3);
		itemMaster.setItemName("Tynenol 500");
		itemMaster.setItemDesc("It is a medicine for headache");
		itemMaster.setItemCode("P01C78L");
		itemMaster.setItemCategoryID(345);
		itemMaster.setIsMedical(true);
		itemMaster.setItemFormID(789);
		itemMaster.setPharmacologyCategoryID(56);
		itemMaster.setManufacturerID(5);
		itemMaster.setStrength("Strong");
		itemMaster.setUomID(6);
		itemMaster.setIsScheduledDrug(true);
		itemMaster.setComposition("Paracitamol");
		itemMaster.setRouteID(7);
		itemMaster.setProviderServiceMapID(8);
		itemMaster.setStatus("Approved and Useable");
		itemMaster.setDiscontinued(false);
		itemMaster.setDeleted(false);
		itemMaster.setProcessed('a');
		itemMaster.setCreatedBy("P Medical Company");
		itemMaster.setCreatedDate(Date.valueOf("2000-08-08"));
		itemMaster.setModifiedBy("C Medical Company");
		itemMaster.setLastModDate(Date.valueOf("2001-09-09"));
		itemMaster.setQuantity(500);
		itemMaster.setFacilityID(23);
		itemMaster.setItemCategoryID(12);

		itemMaster.toString();

		List<ItemMaster> data = new ArrayList<ItemMaster>();

		data.add(itemMaster);

		when(itemRepo.getItemMasters(providerServiceMapID, itemCategoryID)).thenReturn(data);

		// Assertions
		Assertions.assertEquals(data, itemServiceImpl.getItemMasters(providerServiceMapID, itemCategoryID));

	}

	@Test
	void getItemCategoryTest2() {

		Integer catID = Integer.valueOf(6);

		M_ItemCategory m_ItemCategory = new M_ItemCategory();

		m_ItemCategory.setItemCategoryID(50);
		m_ItemCategory.setAlertBeforeDays(151);
		m_ItemCategory.setIssueType("Expiry");
		m_ItemCategory.setItemCategoryName("Paracitamol2");
		m_ItemCategory.setItemCategoryDesc("It is a medicine for headache");
		m_ItemCategory.setItemCategoryCode("P01C78L91112");
		m_ItemCategory.setStatus("Approved and Useable");
		m_ItemCategory.setProviderServiceMapID(89);
		m_ItemCategory.setDeleted(false);
		m_ItemCategory.setProcessed('a');
		m_ItemCategory.setCreatedBy("P Medical Company");
		m_ItemCategory.setCreatedDate(Date.valueOf("2000-08-08"));
		m_ItemCategory.setModifiedBy("C Medical Company");
		m_ItemCategory.setLastModDate(Date.valueOf("2001-09-09"));

		m_ItemCategory.toString();

		when(itemCategoryRepo.findById(catID)).thenReturn(Optional.of(m_ItemCategory));

		// Assertions
		Assertions.assertEquals(m_ItemCategory, itemServiceImpl.getItemCategory(catID));

	}

	@Test
	void getActiveItemMaster() {

		ItemMaster itemMaster = new ItemMaster();

		itemMaster.setItemID(3);
		itemMaster.setItemName("Tynenol 500");
		itemMaster.setItemDesc("It is a medicine for headache");
		itemMaster.setItemCode("P01C78L");
		itemMaster.setItemCategoryID(345);
		itemMaster.setIsMedical(true);
		itemMaster.setItemFormID(789);
		itemMaster.setPharmacologyCategoryID(56);
		itemMaster.setManufacturerID(5);
		itemMaster.setStrength("Strong");
		itemMaster.setUomID(6);
		itemMaster.setIsScheduledDrug(true);
		itemMaster.setComposition("Paracitamol");
		itemMaster.setRouteID(7);
		itemMaster.setProviderServiceMapID(8);
		itemMaster.setStatus("Approved and Useable");
		itemMaster.setDiscontinued(false);
		itemMaster.setDeleted(false);
		itemMaster.setProcessed('a');
		itemMaster.setCreatedBy("P Medical Company");
		itemMaster.setCreatedDate(Date.valueOf("2000-08-08"));
		itemMaster.setModifiedBy("C Medical Company");
		itemMaster.setLastModDate(Date.valueOf("2001-09-09"));
		itemMaster.setQuantity(500);
		itemMaster.setFacilityID(23);
		itemMaster.setItemCategoryID(12);

		itemMaster.toString();

		List<ItemMaster> itemMasterList2 = new ArrayList<ItemMaster>();

		itemMasterList2.add(itemMaster);

		when(itemRepo.findByDeletedAndProviderServiceMapID(itemMaster.getDeleted(),
				itemMaster.getProviderServiceMapID())).thenReturn(itemMasterList2);

		// Assertions
		Assertions.assertEquals(itemMasterList2, itemServiceImpl.getActiveItemMaster(itemMaster));

	}
}
