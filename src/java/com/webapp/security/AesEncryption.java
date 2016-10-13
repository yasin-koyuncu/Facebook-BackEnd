/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.security;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author yasin93
 */

/**
 * This class have not implemented yet.
 * @author yasin93
 */
public class AesEncryption {
  private static String salt;
    private static int iterations = 65536  ;
    private static int keySize = 128;
    private static byte[] ivBytes;

    private static SecretKey secretKey;

    public static void main(String []args) throws Exception {

        salt = getSalt(); SecretKey tmp;

        char[] message = "PasswordToEncrypt".toCharArray();
        System.out.println("Message: " + String.valueOf(message));
        
        System.out.println("Encrypted: " + encrypt(message));
        System.out.println("Decrypted: " + decrypt(encrypt(message).toCharArray()));
        System.out.println("SecretKey ToString: " +secretKey.toString());
        
        
        byte[] data=secretKey.getEncoded();
        tmp=new SecretKeySpec(data,0,data.length,"AES");
        System.out.println(tmp);
    }

    public static String encrypt(char[] plaintext) throws Exception {   
        byte[] saltBytes = salt.getBytes();

        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        PBEKeySpec spec = new PBEKeySpec(plaintext, saltBytes, iterations, keySize);
        secretKey = skf.generateSecret(spec);
        
        SecretKeySpec secretSpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        String test=secretSpec.toString();
       
        
        System.out.println("Encrypt:   "+test);  //tmp o denna är samma
        
        /// dDET OVAN SKA VARA I EN SEPARAT FUNKTION FÖR ATT GENERERA EN NYCKEL(SECRETKEYsPEC), DEN GENERERAS ENDAST VID REGISTRERING 
        //vIA DENNA NYCKEL SKA VI SEDAN KUNNA KRYPTERA SAMT DEKRYPTERA DATA. FRAMFÖRALLT FÖRST KRYPTERA OTP-NYCKELN.SEDAN SPARA ALLTING I DB + SECRETKEYSPEC. 
        //sEDAN DEKRYPTERAS HELA TIDEN OTP VID INLOGGNING FÖR ATT DEN SKA FÅ FRAM TOTP-VÄRDET

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretSpec);
        AlgorithmParameters params = cipher.getParameters();
        ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
        byte[] encryptedTextBytes = cipher.doFinal(String.valueOf(plaintext).getBytes("UTF-8"));

        return DatatypeConverter.printBase64Binary(encryptedTextBytes);
    }
    public static String key(){
        return secretKey.toString();
    }

    public static String decrypt(char[] encryptedText) throws Exception {

        //System.out.println(encryptedText);

        byte[] encryptedTextBytes = DatatypeConverter.parseBase64Binary(new String(encryptedText));//
        SecretKeySpec secretSpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        //System.out.println("Dekrypt key: "+secretSpec);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretSpec, new IvParameterSpec(ivBytes));

        byte[] decryptedTextBytes = null;

        try {
            decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
        }   catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }   catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return new String(decryptedTextBytes);

    }
    
   

    public static String getSalt() throws Exception {

        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[20];
        sr.nextBytes(salt);
        return new String(salt);
    }
}
