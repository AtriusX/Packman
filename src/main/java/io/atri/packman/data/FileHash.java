package io.atri.packman.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHash {

    private final byte[] hash;

    @SuppressWarnings("StatementWithEmptyBody")
    public FileHash(File file) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            try (DigestInputStream in = new DigestInputStream(new FileInputStream(file), md)) {
                byte[] buffer = new byte[1024];
                while (in.read(buffer) > 0);
            }
            hash = md.digest();
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new IllegalStateException("Failed to generate SHA1 hash!");
        }
    }

    public byte[] getHash() {
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (byte b : hash)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
