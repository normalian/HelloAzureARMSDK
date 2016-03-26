package org.mydomain.armsdksample;

import com.microsoft.azure.management.compute.ComputeManagementClient;
import com.microsoft.azure.management.compute.ComputeManagementClientImpl;
import com.microsoft.azure.management.compute.VirtualMachinesOperations;
import com.microsoft.azure.management.compute.models.VirtualMachine;

public class ListAzureVM {

	public static void main(String[] args) throws Exception {
		ComputeManagementClient client = new ComputeManagementClientImpl(
				AzureConfigurator.createApplicationTokenCredentials());
		client.setSubscriptionId(AzureConfigurator.SUBSCRIPTIONID);

		String resourceGroupName = "java-armsdk-group";
		String vmName = "SDKCreatedVM";

		// Get-AzureLocation Ç≈ämîF
		String region = "EastAsia";

		System.out.println("Start create vm...");

		// VM çÏê¨
		try {
			VirtualMachinesOperations opr = client.getVirtualMachinesOperations();
			for (VirtualMachine vm : opr.listAll().getBody()) {
				System.out.println(vm.getId());
				System.out.println("\t" + vm.getHardwareProfile().getVmSize());
				System.out.println("\t" + vm.getLicenseType());
				System.out.println("\t" + vm.getInstanceView());
			}

			// TODO: create VM
			// VirtualMachine parameters = new VirtualMachine();
			// parameters.setLocation(region);
			// opr.createOrUpdate(resourceGroupName, vmName, parameters);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("end");
	}
}
