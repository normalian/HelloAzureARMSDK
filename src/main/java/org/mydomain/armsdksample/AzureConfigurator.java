package org.mydomain.armsdksample;

import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.credentials.AzureEnvironment;

public class AzureConfigurator {
	final static String SUBSCRIPTIONID = "your subscription id";
	final static String CLIENTID = "your client id";
	final static String TENANTID = "your tenant id";
	final static String SECRET = "your password";

	static public ApplicationTokenCredentials createApplicationTokenCredentials() {
		return new ApplicationTokenCredentials(AzureConfigurator.CLIENTID, AzureConfigurator.TENANTID,
				AzureConfigurator.SECRET, AzureEnvironment.AZURE);
	}
}
