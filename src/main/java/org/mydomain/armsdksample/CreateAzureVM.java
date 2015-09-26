package org.mydomain.armsdksample;

import com.microsoft.azure.management.compute.ComputeManagementClient;
import com.microsoft.azure.management.compute.ComputeManagementService;
import com.microsoft.azure.management.compute.models.VirtualMachine;
import com.microsoft.azure.management.network.NetworkResourceProviderClient;
import com.microsoft.azure.management.network.NetworkResourceProviderService;
import com.microsoft.azure.management.resources.ResourceManagementClient;
import com.microsoft.azure.management.resources.ResourceManagementService;
import com.microsoft.azure.management.storage.StorageManagementClient;
import com.microsoft.azure.management.storage.StorageManagementService;
import com.microsoft.azure.utility.ComputeHelper;
import com.microsoft.azure.utility.ResourceContext;
import com.microsoft.windowsazure.Configuration;

public class CreateAzureVM {

	public static void main(String[] args) throws Exception {
		Configuration config = ConfigFactory.createConfiguration();
		ResourceManagementClient resourceManagementClient = ResourceManagementService
				.create(config);
		StorageManagementClient storageManagementClient = StorageManagementService
				.create(config);
		ComputeManagementClient computeManagementClient = ComputeManagementService
				.create(config);
		NetworkResourceProviderClient networkResourceProviderClient = NetworkResourceProviderService
				.create(config);

		String resourceGroupName = "java-armsdk-group";

		// Get-AzureLocation で確認
		String region = "EastAsia";

		ResourceContext context = new ResourceContext(region,
				resourceGroupName, ConfigFactory.getSubscriptionId(), false);

		System.out.println("Start create vm...");

		// VM 作成
		try {
			VirtualMachine vm = ComputeHelper.createVM(
					resourceManagementClient, computeManagementClient,
					networkResourceProviderClient, storageManagementClient,
					context, "javaSampleVM", "normalian", "P@ssw0rd2015")
					.getVirtualMachine();

			System.out.println(vm.getName() + " is created");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// リソースグループごと仮想マシンを削除、残す場合はコメントアウトする
		resourceManagementClient.getResourceGroupsOperations().beginDeleting(
				context.getResourceGroupName());
		System.out.println("end");
	}
}
