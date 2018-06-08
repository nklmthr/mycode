package com.nklmthr.system.utils;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.Signature;

import org.bouncycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class TestSig {

    public static void main(String[] args) throws NoSuchAlgorithmException,
            NoSuchProviderException, ClassNotFoundException {

        Security.addProvider(new BouncyCastleProvider());
        URL bcClassUrl = TestSig.class.getClassLoader()
                .getResource("org/bouncycastle/jce/provider/BouncyCastleProvider.class");
        System.out.println("BouncyCastleProvider class URL   = " + bcClassUrl);

        ClassLoader loader = DigestSignatureSpi.MD5.class.getClassLoader();
        URL md5ClassUrl = loader.getResource(
                "org/bouncycastle/jcajce/provider/asymmetric/rsa/DigestSignatureSpi$MD5.class");
        System.out.println("DigestSignatureSpi.MD5 class URL = " + md5ClassUrl);

        Signature sign = Signature.getInstance("MD5withRSA", "BC");
        System.out.println("algorithm = " + sign.getAlgorithm());
    }
}
