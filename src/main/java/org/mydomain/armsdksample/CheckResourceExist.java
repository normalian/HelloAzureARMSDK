package org.mydomain.armsdksample;

import java.io.IOException;

import com.microsoft.azure.CloudException;
import com.microsoft.azure.management.resources.ResourceManagementClient;
import com.microsoft.azure.management.resources.ResourceManagementClientImpl;
import com.microsoft.azure.management.resources.ResourcesOperations;

public class CheckResourceExist {
	public static void main(String[] args) throws Exception {
		ResourceManagementClient client = new ResourceManagementClientImpl(
				AzureConfigurator.createApplicationTokenCredentials());
		client.setSubscriptionId(AzureConfigurator.SUBSCRIPTIONID);
		ResourcesOperations resourcesOperations = client.getResourcesOperations();

		// TODO: ë∂ç›ämîF
		dispResourceExist(resourcesOperations, "winapserver", "", "Microsoft.ClassicCompute", "domainNames",
				"winapserver");

		System.out.println("end");
	}

	public static void dispResourceExist(ResourcesOperations resourcesOperations, String resourceName,
			String parentResourcePath, String resourceProviderNamespace, String resourceType, String resourceGroupName)
			throws IOException, CloudException, IllegalArgumentException {
		String apiVersion = "2015-11-01";
		Boolean result = resourcesOperations.checkExistence(resourceGroupName, resourceProviderNamespace,
				parentResourcePath, resourceType, resourceName, apiVersion).getBody();
		System.out.println("result = " + result);
	}
}
