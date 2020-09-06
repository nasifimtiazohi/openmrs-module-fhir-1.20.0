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
package org.openmrs.module.fhir.resources;

import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.dstu3.model.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.fhir.api.PatientService;

import java.util.List;

public class FHIRPatientResource extends Resource {

	public Patient getByUniqueId(IdType id) {
		org.openmrs.module.fhir.api.PatientService patientService = Context.getService(
				org.openmrs.module.fhir.api.PatientService.class);
		org.hl7.fhir.dstu3.model.Patient fhirPatient = patientService.getPatient(id.getIdPart());
		if (fhirPatient == null) {
			throw new ResourceNotFoundException("Patient is not found for the given Id " + id.getIdPart());
		}
		return fhirPatient;
	}

	public List<Patient> searchByUniqueId(TokenParam id) {
		org.openmrs.module.fhir.api.PatientService patientService = Context.getService(
				org.openmrs.module.fhir.api.PatientService.class);
		return patientService.searchPatientsById(id.getValue());
	}

	//search by patient identifier. ex: GET_DESCRIPTION [base-url]/Patient?identifier=http://acme.org/patient|2345
	//returns a bundle of patients
	public List<Patient> searchByIdentifier(TokenParam identifier) {
		org.openmrs.module.fhir.api.PatientService patientService = Context.getService(
				org.openmrs.module.fhir.api.PatientService.class);
		if (identifier.getSystem() != null && !identifier.getSystem().isEmpty()) {
			return patientService.searchPatientsByIdentifier(identifier.getValue(), identifier.getSystem());
		}
		return patientService.searchPatientsByIdentifier(identifier.getValue());
	}

	public Bundle searchByGivenName(StringParam givenName) {
		org.openmrs.module.fhir.api.PatientService patientService = Context.getService(
				org.openmrs.module.fhir.api.PatientService.class);
		return patientService.searchPatientsByGivenName(givenName.getValue());
	}

	public Bundle searchByFamilyName(StringParam theFamilyName) {
		org.openmrs.module.fhir.api.PatientService patientService = Context.getService(
				org.openmrs.module.fhir.api.PatientService.class);
		return patientService.searchPatientsByFamilyName(theFamilyName.getValue());
	}

	public Bundle searchByName(StringParam name) {
		org.openmrs.module.fhir.api.PatientService patientService = Context.getService(
				org.openmrs.module.fhir.api.PatientService.class);
		return patientService.searchPatientsByName(name.getValue());
	}

	public List<Patient> searchPatients(TokenParam active) {
		org.openmrs.module.fhir.api.PatientService patientService = Context.getService(
				org.openmrs.module.fhir.api.PatientService.class);
		if ("true".equalsIgnoreCase(active.getValue())) {
			return patientService.searchPatients(true);
		} else {
			return patientService.searchPatients(false);
		}
	}

	public Bundle getPatientOperationsById(IdType id) {
		return Context.getService(PatientService.class).getPatientOperationsById(id.getIdPart());
	}

	public void deletePatient(IdType id) {
		PatientService patientService = Context.getService(PatientService.class);
		patientService.deletePatient(id.getIdPart());
	}

	public Patient createFHIRPatient(Patient patient) {
		PatientService patientService = Context.getService(PatientService.class);
		return patientService.createFHIRPatient(patient);
	}

	public Patient updatePatient(Patient patient, String theId) {
		PatientService patientService = Context.getService(PatientService.class);
		return patientService.updatePatient(patient, theId);
	}
}
