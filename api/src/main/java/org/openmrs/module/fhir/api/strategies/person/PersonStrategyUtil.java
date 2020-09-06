package org.openmrs.module.fhir.api.strategies.person;

import org.openmrs.api.context.Context;
import org.openmrs.module.fhir.api.util.FHIRUtils;

public class PersonStrategyUtil {

	public static GenericPersonStrategy getPersonStrategy() {
		String strategy = FHIRUtils.getPersonStrategy();

		return strategy == null ?
				new PersonStrategy() :
				Context.getRegisteredComponent(strategy, GenericPersonStrategy.class);
	}
}
