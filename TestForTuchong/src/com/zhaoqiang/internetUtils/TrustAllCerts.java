package com.zhaoqiang.internetUtils;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class TrustAllCerts implements X509TrustManager{

	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		// TODO Auto-generated method stub
		return new X509Certificate[0];
	}

	public SSLSocketFactory createSSLSocketFactory() {  
        SSLSocketFactory ssfFactory = null;  

        try {  
            SSLContext sc = SSLContext.getInstance("TLS");  
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());  

            ssfFactory = sc.getSocketFactory();  
        } catch (Exception e) {  
        }  

        return ssfFactory;  
    }  
}
