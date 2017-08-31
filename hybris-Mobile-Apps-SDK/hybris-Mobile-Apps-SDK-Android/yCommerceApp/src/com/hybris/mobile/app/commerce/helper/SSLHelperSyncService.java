package com.hybris.mobile.app.commerce.helper;

import android.util.Log;

import com.hybris.mobile.lib.commerce.helper.SSLHelper;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLHelperSyncService implements SSLHelper {
    private static final String TAG = SSLHelperSyncService.class.getCanonicalName();

    @Override
    public SSLSocketFactory getSSLSocketFactory() {
        // Bypassing SSLHelperSyncService
        TrustManager[] trustManager = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                }
        };

        SSLContext sslContext = null;

        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustManager, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            Log.e(TAG, "Error with SSLHelperSyncService. Details: " + e.getLocalizedMessage());
        }

        if (sslContext == null) {
            throw new IllegalStateException("Unable to get an instance of SSLContext");
        }

        return sslContext.getSocketFactory();
    }

    @Override
    public HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }
}
