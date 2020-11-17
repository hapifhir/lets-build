package ca.uhn.fhir.letsbuild.upload;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;

public class CsvDataUploader_Hint2_Create_A_Patient {

    public static void main(String[] args) {

        // The following code creates a FHIR client - You can create one client and reuse it
        // for all requests.
        FhirContext ctx = FhirContext.forR4();
        IGenericClient client = ctx.newRestfulGenericClient("http://localhost:8080/fhir");

        // This line adds a "logging interceptor" which causes the client to output some details
        // about its actions to the console
        client.registerInterceptor(new LoggingInterceptor(false));


    }

}
