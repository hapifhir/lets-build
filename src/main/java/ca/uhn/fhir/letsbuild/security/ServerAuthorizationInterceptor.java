package ca.uhn.fhir.letsbuild.security;

import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.interceptor.auth.AuthorizationInterceptor;
import ca.uhn.fhir.rest.server.interceptor.auth.IAuthRule;
import ca.uhn.fhir.rest.server.interceptor.auth.RuleBuilder;

import java.util.List;

public class ServerAuthorizationInterceptor extends AuthorizationInterceptor {

    @Override
    public List<IAuthRule> buildRuleList(RequestDetails theRequestDetails) {
        RuleBuilder builder = new RuleBuilder();

        // We just have a single rule here allowing all operations to proceed
        // Replace this rule with some more nuanced ones, then restart your server
        // and try them out.
        builder.allowAll();

        return builder.build();
    }
}
