/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.fhir.api;

import org.hl7.fhir.dstu3.model.FamilyMemberHistory;
import org.openmrs.api.OpenmrsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface FamilyMemberHistoryService extends OpenmrsService {

	/**
	 * Search family history by person
	 *
	 * @param personId person id
	 * @return fhir family history resource
	 */
	List<FamilyMemberHistory> searchFamilyMemberHistoryByPersonId(String personId);

	/**
	 * Get relationship by id
	 *
	 * @param id the id of the relationship
	 * @return fhir family history resource
	 */
	FamilyMemberHistory getRelationshipById(String id);

	/**
	 * Search relationship by id
	 *
	 * @param id the id of the relationship
	 * @return fhir family history resource
	 */
	List<FamilyMemberHistory> searchRelationshipsById(String id);
}
