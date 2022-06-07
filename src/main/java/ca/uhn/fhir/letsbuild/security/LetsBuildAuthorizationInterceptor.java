package ca.uhn.fhir.letsbuild.security;

import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.interceptor.auth.AuthorizationInterceptor;
import ca.uhn.fhir.rest.server.interceptor.auth.IAuthRule;
import ca.uhn.fhir.rest.server.interceptor.auth.RuleBuilder;

import java.util.List;

public class LetsBuildAuthorizationInterceptor extends AuthorizationInterceptor {

    @Override
    public List<IAuthRule> buildRuleList(RequestDetails theRequestDetails) {
        RuleBuilder builder = new RuleBuilder();

        // Precursor: remove this line
        builder.allowAll();

        // Step 1
        // Extract the Authorization header from theRequestDetails
        // For testing, we'll assume it will have a value of "Bearer 1" or "Bearer 2"

        // Step 2
        // If the header isn't present, throw an appropriate exception
        // See server exceptions docs to find appropriate exceptions to throw:
        //    https://hapifhir.io/hapi-fhir/apidocs/hapi-fhir-base/ca/uhn/fhir/rest/server/exceptions/package-summary.html

        // Step 3
        // If the header contains "Bearer 1", allow read access to the Patient compartment for patient Patient/PT00001
        // If the header contains "Bearer 2", allow read access to the Patient compartment for patient Patient/PT00002

        // Step 4
        // Try your server out with some sample queries:
        //    http://localhost:8080/fhir/Patient/PT0001
        //    http://localhost:8080/fhir/Observation?subject=Patient/PT0001
        //    http://localhost:8080/fhir/Patient/PT0002
        //    http://localhost:8080/fhir/Observation?subject=Patient/PT0002

        return builder.build();
    }
}
