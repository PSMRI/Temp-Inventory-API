/*
* AMRIT – Accessible Medical Records via Integrated Technology 
* Integrated EHR (Electronic Health Records) Solution 
*
* Copyright (C) "Piramal Swasthya Management and Research Institute" 
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.inventory.repo.dispenseagainst_rx;

import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.iemr.inventory.data.dispenseagainst_rx.PrescribedDrugDetails;

@Service
@Repository
@Configuration
@ComponentScan
public interface PrescribedDrugDetailsRepo extends CrudRepository<PrescribedDrugDetails, Long> {
	@Query(" SELECT Distinct pd.beneficiaryRegID, pd.visitCode, pd.prescriptionID, pd.drugID, pd.genericDrugName, pd.drugForm,"
			+ " pd.drugStrength, pd.dose, pd.route, pd.frequency, "
			+ " pd.duration, pd.duartionUnit, pd.relationToFood, pd.specialInstruction, pd.createdDate, pd.createdBy, "
			+ " pd.itemStockEntryID, pd.batchNo, pd.quantityInHand, pd.expiryDate, pd.qtyPrescribed, pd.isEDL  "
			+ " FROM PrescribedDrugDetails pd  WHERE pd.beneficiaryRegID =:benRegID "
			+ " AND pd.visitCode =:visitCode AND (pd.facilityID =:facilityID or facilityID is null) ORDER BY pd.drugID, pd.createdDate DESC ")
	ArrayList<Object[]> getPrescribedMedicinesWithDetails(@Param("benRegID") Long benRegID,
			@Param("visitCode") Long visitCode, @Param("facilityID") Integer facilityID);

}
