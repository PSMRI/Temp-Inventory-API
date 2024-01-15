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
package com.iemr.inventory.repo.stockadjustment;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraft;

@Repository
@RestResource(exported = false)
public interface StockAdjustmentItemDraftRepo extends CrudRepository<StockAdjustmentItemDraft, Long> {


	@Query(" SELECT sadi "
			  + " FROM StockAdjustmentItemDraft sadi  "
			  + " JOIN sadi.itemStockEntry ise "
			  + " WHERE sadi.sADraftItemMapID =:id")
	StockAdjustmentItemDraft getforedit(@Param("id")Long id);


	@Transactional
	@Modifying
	@Query("UPDATE StockAdjustmentItemDraft c SET c.deleted = true WHERE c.stockAdjustmentDraftID = :id")
	Integer updateDeleted(@Param("id")Long stockAdjustmentDraftID);


	StockAdjustmentItemDraft findOne(Object saDraftItemMapID);

}
