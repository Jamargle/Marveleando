package jmlb0003.com.marveleando.data.network.authentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashHelper {

    private HashHelper() {
    }

    public static String generate(
            final String timeStamp,
            final String privateKey,
            final String publicKey) {

        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            final String chainToDigest = timeStamp + privateKey + publicKey;
            final byte[] digest = messageDigest.digest(chainToDigest.getBytes());
            final StringBuilder md5 = new StringBuilder();
            for (final byte value : digest) {
                md5.append(Integer.toHexString((value & 0xFF) | 0x100).substring(1, 3));
            }
            return md5.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

}