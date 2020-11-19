package ca.uhn.fhir.letsbuild.security;

import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.exceptions.AuthenticationException;
import ca.uhn.fhir.rest.server.interceptor.auth.AuthorizationInterceptor;
import ca.uhn.fhir.rest.server.interceptor.auth.IAuthRule;
import ca.uhn.fhir.rest.server.interceptor.auth.RuleBuilder;
import org.hl7.fhir.r4.model.IdType;

import java.util.List;

public class ServerAuthorizationInterceptor_Hint extends AuthorizationInterceptor {

    @Override
    public List<IAuthRule> buildRuleList(RequestDetails theRequestDetails) {
        RuleBuilder builder = new RuleBuilder();

        String authHeader = theRequestDetails.getHeader("Authorization");
        if (authHeader == null) {
            throw new AuthenticationException("No authorization header found");
        }

        if (authHeader.equals("Bearer 111")) {
            builder
                    .allow().write().allResources().withAnyId().andThen()
                    .allow().read().allResources().inCompartment("Patient", new IdType("Patient/PT00001")).andThen()
                    .denyAll();
        } else if (authHeader.equals("Bearer 222")) {
            builder
                    .allow().write().allResources().withAnyId().andThen()
                    .allow().read().allResources().inCompartment("Patient", new IdType("Patient/PT00002")).andThen()
                    .denyAll();
        } else {
            builder
                    .denyAll();
        }

        return builder.build();
    }
}
