package org.mydomain.armsdksample;

import java.net.URI;

import com.microsoft.azure.utility.AuthHelper;
import com.microsoft.windowsazure.Configuration;
import com.microsoft.windowsazure.management.configuration.ManagementConfiguration;

public class ConfigFactory {
	// Add-AzureAccount 後に Get-AzureSubscription で確認
	final static String subscriptionId = "<subscriptionId>";

	// 以下のコマンドで確認
	// $creds = Get-Credential # ApplicationId/Password でログイン
	// Add-AzureAccount -Credential $creds -ServicePrincipal -Tenant
	// $subscription.TenantId
	final static String armTenant = "<tenant id>";

	// 以下のコマンドで確認
	// Get-AzureADServicePrincipal -ObjectId <objectId> -Debug
	final static String armClientId = "<client id>";

	// パスワード
	final static String armClientkey = "<password>";

	// 固定文字列
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
