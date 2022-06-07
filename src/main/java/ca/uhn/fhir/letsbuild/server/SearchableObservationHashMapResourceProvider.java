package ca.uhn.fhir.letsbuild.server;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.param.ReferenceParam;
import ca.uhn.fhir.rest.server.provider.HashMapResourceProvider;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Observation;

import java.util.List;
import java.util.stream.Collectors;

public class SearchableObservationHashMapResourceProvider extends HashMapResourceProvider<Observation> {

	public SearchableObservationHashMapResourceProvider(FhirContext theFhirContext) {
		super(theFhirContext, Observation.class);
	}


	@Search
	public List<IBaseResource> searchBySubject(@RequiredParam(name="subject") ReferenceParam theParam) {
		String subjectId = theParam.getIdPart();
		return super
			.getStoredResources()
			.stream()
			.filter(t-> subjectId.equals(t.getSubject().getReferenceElement().getIdPart()))
			.collect(Collectors.toList());
	}

}
