package org.mydomain.armsdksample;

import java.io.IOException;

import com.microsoft.azure.management.resources.ResourceManagementClient;
import com.microsoft.azure.management.resources.ResourceManagementService;
import com.microsoft.azure.management.resources.ResourceOperations;
import com.microsoft.azure.management.resources.models.ResourceExistsResult;
import com.microsoft.windowsazure.Configuration;
import com.microsoft.windowsazure.core.ResourceIdentity;
import com.microsoft.windowsazure.exception.ServiceException;

public class CheckResourceExist {
	public static void main(String[] args) throws Exception {
		Configuration config = ConfigFactory.createConfiguration();
		ResourceManagementClient resourceManagementClient = ResourceManagementService
				.create(config);
		ResourceOperations resourceOperations = resourceManagementClient
				.getResourcesOperations();

		// クラウドサービスの生存確認
		dispResourceExist(resourceOperations, "winapserver",
				"Microsoft.ClassicCompute", "domainNames", "winapserver");

		// WebApps の生存確認
		dispResourceExist(resourceOperations, "normalianwebsite",
				"Microsoft.Web", "sites", "WebSiteDeployDevelopment");
	}

	public static void dispResourceExist(ResourceOperations resourceOperations,
			String resourceName, String providerNamespace, String resourceType,
			String resourceGroupName) throws IOException, ServiceException {
		ResourceIdentity resourceIdentity = new ResourceIdentity();
		resourceIdentity.setResourceName(resourceName);
		resourceIdentity.setResourceType(resourceType);
		resourceIdentity.setResourceProviderNamespace(providerNamespace);

		// The supported api-versions are '2014-01-01, 2014-06-01, 2015-06-01'
		resourceIdentity.setResourceProviderApiVersion("2015-06-01");

		ResourceExistsResult resourceExistsResult = resourceOperations
				.checkExistence(resourceGroupName, resourceIdentity);
		StringBuilder result = new StringBuilder("@"
				+ resourceIdentity.getResourceName() + " at "
				+ resourceGroupName + "is ");
		if (resourceExistsResult.isExists() == false) {
			result.append("not ");
		}
		result.append("exists");
		System.out.println(result.toString());
	}
}
