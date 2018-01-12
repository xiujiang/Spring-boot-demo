package com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {
    public static byte[] getMd5Bytes(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());
            byte b[] = md.digest();
            return b;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMd5Str(String source) {

        byte b[] = getMd5Bytes(source);
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    public static byte[] getSha1Bytes(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("sha-1");
            md.update(source.getBytes());
            byte b[] = md.digest();
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getSha1Str(String source) {

        byte[] b = getSha1Bytes(source);
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
}
