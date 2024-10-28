package com.dogognon.sohliou.kone.security.rsa;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

public class GenerateKeyPair {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
        var keyPair=keyPairGenerator.generateKeyPair();
        // public and private key 
        byte[] pub = keyPair.getPublic().getEncoded();
        byte[] pri = keyPair.getPrivate().getEncoded();
        PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream("public.pem")));
        PemObject pemObject=new PemObject("PUBLIC KEY",pub);
        pemWriter.writeObject(pemObject);
        pemWriter.close();
        PemWriter pemWriter2 = new PemWriter(new OutputStreamWriter(new FileOutputStream("private.pem")));
        PemObject pemObject2=new PemObject("PRIVATE KEY",pri);
        pemWriter2.writeObject(pemObject2);
        pemWriter2.close();
        // --- create the self signed cert
        try {
			Certificate cert = createSelfSigned(keyPair);
			 // --- create a new pkcs12 key store in memory
	        KeyStore pkcs12 = KeyStore.getInstance("PKCS12");
	        pkcs12.load(null, null);

	        // --- create entry in PKCS12
	        // pkcs12.setKeyEntry("privatekeyalias", keyPair.getPrivate(), "entrypassphrase".toCharArray(), new Certificate[] {cert});
	        pkcs12.setKeyEntry("tomcat", keyPair.getPrivate(), "spring".toCharArray(), new Certificate[] {cert});

	        // --- store PKCS#12 as file
	        try (FileOutputStream p12 = new FileOutputStream("keystore.p12")) {
	        	// pkcs12.store(p12, "p12passphrase".toCharArray());
	            pkcs12.store(p12, "spring".toCharArray());
	        }
		} catch (OperatorCreationException | CertIOException | CertificateException |KeyStoreException e) {
			e.printStackTrace();
		} 
        
        System.out.println("Vos clés ont été bien généréé elle sont à la racine du projet.");
    }
    
    private static X509Certificate createSelfSigned(KeyPair pair) throws OperatorCreationException, CertIOException, CertificateException {
        X500Name dnName = new X500Name("CN=publickeystorageonly");
        BigInteger certSerialNumber = BigInteger.ONE;

        Date startDate = new Date(); // now

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.YEAR, 1);
        Date endDate = calendar.getTime();

        ContentSigner contentSigner = new JcaContentSignerBuilder("SHA256WithRSA").build(pair.getPrivate());
        JcaX509v3CertificateBuilder certBuilder = new JcaX509v3CertificateBuilder(dnName, certSerialNumber, startDate, endDate, dnName, pair.getPublic());

        return new JcaX509CertificateConverter().getCertificate(certBuilder.build(contentSigner));
    }
}