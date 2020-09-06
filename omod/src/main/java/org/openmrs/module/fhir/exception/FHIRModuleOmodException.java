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
package org.openmrs.module.fhir.exception;

/**
 * Represents often fatal errors that occur within the fhir module layer.
 */
public class FHIRModuleOmodException extends Exception {

	private static final long serialVersionUID = -185144340435149253L;

	public FHIRModuleOmodException() {
	}

	public FHIRModuleOmodException(String message) {
		super(message);
	}

	public FHIRModuleOmodException(String message, Throwable cause) {
		super(message, cause);
	}

	public FHIRModuleOmodException(Throwable cause) {
		super(cause);
	}

}
