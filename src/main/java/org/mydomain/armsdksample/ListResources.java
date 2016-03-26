package org.mydomain.armsdksample;

import java.util.List;

import com.microsoft.azure.management.resources.ResourceManagementClient;
import com.microsoft.azure.management.resources.ResourceManagementClientImpl;
import com.microsoft.azure.management.resources.models.GenericResource;

public class ListResources {

	public static void main(String[] args) throws Exception {

		ResourceManagementClient client = new ResourceManagementClientImpl(
				AzureConfigurator.createApplicationTokenCredentials());
		client.setSubscriptionId(AzureConfigurator.SUBSCRIPTIONID);

		List<GenericResource> grList = client.getResourcesOperations().list(null, null).getBody();
		for (GenericResource rg : grList) {
			System.out.println(rg.getId());
		}

		System.out.println("end");
	}
}
