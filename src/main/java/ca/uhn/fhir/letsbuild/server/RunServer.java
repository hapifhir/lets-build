package ca.uhn.fhir.letsbuild.server;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.interceptor.LoggingInterceptor;
import ca.uhn.fhir.rest.server.interceptor.ResponseHighlighterInterceptor;
import ca.uhn.fhir.rest.server.provider.HashMapResourceProvider;
import ca.uhn.fhir.test.utilities.JettyUtil;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.hl7.fhir.r4.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a simple FHIR server that runs on port 8000
 */
public class RunServer {
	private static final Logger ourLog = LoggerFactory.getLogger(RunServer.class);
	private static FhirContext ourFhirContext = FhirContext.forR4Cached();
	private static Server ourListenerServer = new Server(8000);

	public static void main(String[] args) throws Exception {

		// Create a HAPI FHIR RestfulServer with two in-memory resource providers
		RestfulServer server = new RestfulServer(ourFhirContext);
		server.registerProvider(new HashMapResourceProvider<>(ourFhirContext, Patient.class));
		server.registerProvider(new SearchableObservationHashMapResourceProvider(ourFhirContext));

		// Register some helpful interceptors
		server.registerInterceptor(new ResponseHighlighterInterceptor());
		server.registerInterceptor(new LoggingInterceptor());

		// On day 2 we will register an authorization interceptor here
//		server.registerInterceptor();

		/*
		 * The following is all boilerplate in order to start the HAPI FHIR
		 * RestfulServer on port 8000 using Jetty
		 */
		ServletContextHandler proxyHandler = new ServletContextHandler();
		proxyHandler.setContextPath("/");
		ServletHolder targetServletHolder = new ServletHolder();
		targetServletHolder.setServlet(server);
		proxyHandler.addServlet(targetServletHolder, "/*");
		ourListenerServer.setHandler(proxyHandler);
		ourListenerServer.start();

		ourLog.info("Server is now running Started! Base URL is http://localhost:" + JettyUtil.getPortForStartedServer(ourListenerServer));
	}
}
