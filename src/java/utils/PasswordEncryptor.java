/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Oksana_Moroz
 */
public class PasswordEncryptor {
    
    public static String getEncoded(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(password.getBytes("UTF-8"));
            byte[] raw = md.digest();
            String hash = (new BASE64Encoder()).encode(raw);
            return hash;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordEncryptor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PasswordEncryptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
