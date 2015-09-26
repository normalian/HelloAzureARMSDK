package org.mydomain.armsdksample;

import java.net.URI;

import com.microsoft.azure.utility.AuthHelper;
import com.microsoft.windowsazure.Configuration;
import com.microsoft.windowsazure.management.configuration.ManagementConfiguration;

public class ConfigFactory {
	// Add-AzureAccount ��� Get-AzureSubscription �Ŋm�F
	final static String subscriptionId = "<subscriptionId>";

	// �ȉ��̃R�}���h�Ŋm�F
	// $creds = Get-Credential # ApplicationId/Password �Ń��O�C��
	// Add-AzureAccount -Credential $creds -ServicePrincipal -Tenant
	// $subscription.TenantId
	final static String armTenant = "<tenant id>";

	// �ȉ��̃R�}���h�Ŋm�F
	// Get-AzureADServicePrincipal -ObjectId <objectId> -Debug
	final static String armClientId = "<client id>";

	// �p�X���[�h
	final static String armClientkey = "<password>";

	// �Œ蕶����
	final static String managementUri = "https://management.core.windows.net/";
	final static String armUrl = "https://management.azure.net/";
	final static String armAadUrl = "https://login.windows.net/";

	public static Configuration createConfiguration() throws Exception {
		return ManagementConfiguration.configure(
				null,
				new URI(armUrl),
				subscriptionId,
				AuthHelper.getAccessTokenFromServicePrincipalCredentials(
						managementUri, armAadUrl, armTenant, armClientId,
						armClientkey).getAccessToken());
	}

	public static String getSubscriptionId() {
		return subscriptionId;
	}
}
