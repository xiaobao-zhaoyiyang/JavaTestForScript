package com.zhaoqiang.internetUtils;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

public class OKHTTPClientBuilder {
	public static OkHttpClient.Builder builderOKHttpClientBuilder(){
		try {
			TrustManager[] trustAllerts = buildTrustManagers();
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllerts, new SecureRandom());
			
			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
			OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
			builder.sslSocketFactory(sslSocketFactory);
			builder.hostnameVerifier((hostnameVerifier, session) -> true);
			return builder;
		} catch (NoSuchAlgorithmException | KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new OkHttpClient.Builder();
		} 
		
	}
	private static TrustManager[] buildTrustManagers() {
		return new TrustManager[] {
				new X509TrustManager() {

					@Override
					public void checkClientTrusted(X509Certificate[] chain, String authType)
							throws CertificateException {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void checkServerTrusted(X509Certificate[] chain, String authType)
							throws CertificateException {
						// TODO Auto-generated method stub
						
					}

					@Override
					public X509Certificate[] getAcceptedIssuers() {
						// TODO Auto-generated method stub
						return new X509Certificate[] {};
					}
				}
		};
	}
}
