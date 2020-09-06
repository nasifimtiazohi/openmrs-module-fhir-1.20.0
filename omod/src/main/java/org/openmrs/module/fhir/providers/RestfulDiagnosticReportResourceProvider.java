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
package org.openmrs.module.fhir.providers;

import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.Delete;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Update;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.param.ReferenceParam;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import org.hl7.fhir.dstu3.model.DiagnosticReport;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Resource;
import org.openmrs.module.fhir.resources.FHIRDiagnosticReportResource;
import org.openmrs.module.fhir.util.MethodOutcomeBuilder;

import java.util.List;

public class RestfulDiagnosticReportResourceProvider implements IResourceProvider {

	private static final String EXCEPTION_MESSAGE =
			"No Diagnostic Report is associated with the given UUID to update. Please" +
					" make sure you have set at lease one non-delete Issued, Subject, Performer and " +
					"ServiceCategory to create a new Diagnostic Report with the given UUID.";

	private FHIRDiagnosticReportResource diagnosticReportResource;

	public RestfulDiagnosticReportResourceProvider() {
		diagnosticReportResource = new FHIRDiagnosticReportResource();
	}

	@Override
	public Class<? extends Resource> getResourceType() {
		return DiagnosticReport.class;
	}

	/**
	 * Create Diagnostic Report
	 *
	 * @param diagnosticReport FHIR Diagnostic Report object
	 * @return This method returns a list of Diagnostic Reports. This list may contain multiple
	 * matching resources, or it may also be empty.
	 */
	@Create
	public MethodOutcome createFHIRDiagnosticReport(@ResourceParam DiagnosticReport diagnosticReport) {
		return MethodOutcomeBuilder.buildCreate(diagnosticReportResource.createFHIRDiagnosticReport(diagnosticReport));
	}

	/**
	 * The "@Read" annotation indicates that this method supports the read operation. Read
	 * operations should return a single resource instance.
	 *
	 * @param theId The read operation takes one parameter, which must be of type IdDt and must be
	 *              annotated with the "@Read.IdParam" annotation.
	 * @return Returns a resource matching this identifier, or null if none exists.
	 */
	@Read
	public DiagnosticReport getResourceById(@IdParam IdType theId) {
		return diagnosticReportResource.getByUniqueId(theId);
	}

	@Update
	public MethodOutcome updateFHIRDiagnosticReport(@ResourceParam DiagnosticReport diagnosticReport,
			@IdParam IdType theId) {
		try {
			return MethodOutcomeBuilder
					.buildUpdate(diagnosticReportResource.updateFHIRDiagnosticReport(diagnosticReport, theId.getIdPart()));
		}
		catch (Exception e) {
			return MethodOutcomeBuilder.buildCustom(EXCEPTION_MESSAGE);
		}
	}

	/**
	 * Delete person by unique id
	 *
	 * @param theId object containing the id
	 */
	@Delete
	public void retireDiagnosticReport(@IdParam IdType theId) {
		diagnosticReportResource.retireDiagnosticReport(theId);
	}

	/**
	 * The "@Search" annotation indicates that this method supports the search operation. Search operations should return
	 * a bundle of resources.
	 *
	 * @param theSubject The read operation takes one parameter, which must be of type ReferenceParam.
	 * @return Returns a bundle of resources matching this subject's given name, or empty bundle if none exists.
	 */
	@Search
	public List<DiagnosticReport> findByPatientAndServiceCategory(
			@RequiredParam(name = DiagnosticReport.SP_SUBJECT, chainWhitelist = Patient.SP_GIVEN) ReferenceParam theSubject,
			@OptionalParam(name = DiagnosticReport.SP_CATEGORY) TokenParam theService) {
		return diagnosticReportResource.getDiagnosticReportByPatientNameAndServiceCategory(theSubject, theService);
	}
}
