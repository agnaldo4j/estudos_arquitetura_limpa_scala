package com.codesimples.algorithm.signature;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class ReferenceImplementation {
    public String generateSignature(String httpMethod, String serverEndpoint, String json, String plainSecret) throws Exception {
        String data = buildSignatureData(httpMethod, serverEndpoint, json, plainSecret);
        return signData(data, plainSecret, "HmacSHA256");
    }

    private String signData(String data, String plainSecret, String algorithm) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(plainSecret.getBytes("UTF-8"), algorithm);
        Mac mac = Mac.getInstance(algorithm);
        mac.init(secretKey);
        return Hex.encodeHexString(mac.doFinal(data.getBytes("UTF-8")));
    }

    private String buildSignatureData(String httpMethod, String serverEndpoint, String json, String plainSecret) {
        return httpMethod.toUpperCase()+"\n"+serverEndpoint+"\n"+json+"\n"+encodeBase64(plainSecret);
    }

    private String encodeBase64(String plainString) {
        return Base64.encodeBase64String(plainString.getBytes());
    }
}
