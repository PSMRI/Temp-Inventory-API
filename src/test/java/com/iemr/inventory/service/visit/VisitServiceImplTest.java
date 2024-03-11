package com.iemr.inventory.service.visit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.ResourceAccessException;

import com.iemr.inventory.data.visit.BenVisitDetail;
import com.iemr.inventory.data.visit.BeneficiaryFlowStatus;
import com.iemr.inventory.data.visit.BeneficiaryModel;
import com.iemr.inventory.repo.visit.BeneficiaryFlowStatusRepo;
import com.iemr.inventory.repo.visit.VisitRepo;
import com.iemr.inventory.utils.config.ConfigProperties;
import com.iemr.inventory.utils.exception.IEMRException;
import com.iemr.inventory.utils.http.HttpUtils;

class VisitServiceImplTest {

	protected String identityBaseURL = ConfigProperties.getPropertyByName("common-api-url");

	static String auth = "token";

	@Mock
	HttpUtils httpUtils;

	@Mock
	VisitRepo visitRepo;

	@Mock
	BeneficiaryFlowStatusRepo beneficiaryFlowStatusRepo;

	@InjectMocks
	VisitServiceImpl visitService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetVisitDetail_Success() throws Exception {
		// Arrange
		String benrID = "123";
		Integer providerservicemapID = 456;
		String auth = "token";

		BeneficiaryFlowStatus beneficiaryFlowStatus = new BeneficiaryFlowStatus();
		beneficiaryFlowStatus.setProviderServiceMapId(3);
		beneficiaryFlowStatus.setBeneficiaryRegID(2L);

		beneficiaryFlowStatus.toString();

		List<BeneficiaryFlowStatus> beneficiaryFlowStatusList = new ArrayList<BeneficiaryFlowStatus>();
		beneficiaryFlowStatusList.add(beneficiaryFlowStatus);

		BenVisitDetail benVisitDetail = new BenVisitDetail();

		benVisitDetail.setBenVisitID(1L);
		benVisitDetail.setBeneficiaryRegID(2L);
		benVisitDetail.setProviderServiceMapID(3);
		benVisitDetail.setServiceProviderName("MDC");
		benVisitDetail.setVisitDateTime(Timestamp.valueOf("2000-08-08 09:01:16"));
		benVisitDetail.setVisitCode("Visit1");
		benVisitDetail.setVisitNo(Short.valueOf("3"));
		benVisitDetail.setVisitReasonID(Short.valueOf("6"));
		benVisitDetail.setVisitReason("OPD");
		benVisitDetail.setVisitCategoryID(7);
		benVisitDetail.setVisitCategory("Monthly");
		benVisitDetail.setPregnancyStatus("Three months");
		benVisitDetail.setRCHID("P1N");
		benVisitDetail.setHealthFacilityType("Pill");
		benVisitDetail.setHealthFacilityLocation("Kolkata");
		benVisitDetail.setReportFilePath("PC Directory");
		benVisitDetail.setDeleted(false);
		benVisitDetail.setProcessed("Y");
		benVisitDetail.setCreatedBy("P Medical Company");
		benVisitDetail.setCreatedDate(Timestamp.valueOf("2000-08-08 09:01:16"));
		benVisitDetail.setModifiedBy("C Medical Company");
		benVisitDetail.setLastModDate(Timestamp.valueOf("2001-09-09 09:01:16"));
		benVisitDetail.setVisitFlowStatusFlag("ON");
		benVisitDetail.setVanSerialNo(3L);
		benVisitDetail.setVehicalNo("WB1232467");
		benVisitDetail.setParkingPlaceID(12);
		benVisitDetail.setSyncedBy("Sudama");
		benVisitDetail.setSyncedDate(Timestamp.valueOf("2001-09-09 09:01:16"));
		benVisitDetail.setReservedForChange("No");

		benVisitDetail.toString();

		List<BenVisitDetail> benVisitDetailList = new ArrayList<BenVisitDetail>();
		benVisitDetailList.add(benVisitDetail);

		BeneficiaryModel beneficiaryModel = new BeneficiaryModel();
		beneficiaryModel.setBenVisitDetail(benVisitDetailList);
		beneficiaryModel.setBeneficiaryFlowStatus(beneficiaryFlowStatusList);

		beneficiaryModel.toString();

		when(visitRepo.findBybeneficiaryRegIDAndProviderServiceMapID(beneficiaryModel.getBeneficiaryRegID(),
				providerservicemapID)).thenReturn(benVisitDetailList);

		when(beneficiaryFlowStatusRepo.findByBeneficiaryRegIDAndProviderServiceMapIdAndDoctorFlagInAndBenVisitIDNotNull(
				beneficiaryModel.getBeneficiaryRegID(), providerservicemapID,
				new Short[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 })).thenReturn(beneficiaryFlowStatusList);

		// When
		BeneficiaryModel result = visitService.getVisitDetail(benrID, providerservicemapID, auth);

		// Then
		assertNotNull(result);
		assertEquals(beneficiaryModel, result);
		verify(visitRepo, times(1)).findBybeneficiaryRegIDAndProviderServiceMapID(
				beneficiaryModel.getBeneficiaryRegID(), providerservicemapID);
		verify(beneficiaryFlowStatusRepo, times(1))
				.findByBeneficiaryRegIDAndProviderServiceMapIdAndDoctorFlagInAndBenVisitIDNotNull(
						beneficiaryModel.getBeneficiaryRegID(), providerservicemapID,
						new Short[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 });
	}

	@Test
	void testGetVisitDetail_InvalidBeneficiaryID() {
		// Arrange
		String benrID = "invalidID";
		Integer providerservicemapID = 456;
		String auth = "token";
		when(visitRepo.findBybeneficiaryRegIDAndProviderServiceMapID(any(), any()))
				.thenThrow(ResourceAccessException.class);

		// Act & Assert
		assertThrows(ResourceAccessException.class,
				() -> visitService.getVisitDetail(benrID, providerservicemapID, auth));
	}

	@Test
	public void testGetBeneficiaryListByIDs() throws Exception {
		// Given
		String benrID = "123";
		String auth = "token";
		String resultJson = "{\"data\":[{\"id\":1,\"name\":\"John\"}]}";

		// When
		List<BeneficiaryModel> result = visitService.getBeneficiaryListByIDs(benrID, auth);

		// Then
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	void testGetBeneficiaryListByIDs_InvalidBeneficiaryID() {
		// Arrange
		String benrID = "invalidID";
		String auth = "token";

		// Act & Assert
		assertThrows(ResourceAccessException.class, () -> visitService.getBeneficiaryListByIDs(benrID, auth));
	}

	@Test
	public void testGetBeneficiaryListBySearch() throws Exception {
		// Given
		String benrID = "123";
		String auth = "token";
		String resultJson = "{\"data\":[{\"id\":1,\"name\":\"John\"}]}";

		// When & Then
		assertThrows(IEMRException.class, () -> {
			visitService.getBeneficiaryListBySearch(benrID, auth);
		});
	}

	@Test
	void testGetBeneficiaryListBySearch_InvalidUserID() {
		// Arrange
		String benrID = "invalidID";
		String auth = "token";

		// Act & Assert
		assertThrows(ResourceAccessException.class, () -> visitService.getBeneficiaryListBySearch(benrID, auth));
	}

	@Test
	public void testGetVisitFromAdvanceSearch() throws Exception {
		// Given
		String benrID = "123";
		String auth = "token";
		String resultJson = "{\"data\":[{\"id\":1,\"name\":\"John\"}]}";

		// When
		List<BeneficiaryModel> result = visitService.getVisitFromAdvanceSearch(benrID, auth);

		// Then
		assertNotNull(result);
		assertEquals(1, result.size());
	}
}
