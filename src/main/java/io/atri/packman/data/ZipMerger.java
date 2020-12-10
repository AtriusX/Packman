package io.atri.packman.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public final class ZipMerger {

    private final File dest;

    public ZipMerger(File dest) {
        this.dest = dest;
    }

    public boolean merge(ZipInputStream in) {
        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(dest))) {
            ZipEntry e;
            while ((e = in.getNextEntry()) != null)
                writeEntry(e, in, out);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void writeEntry(ZipEntry entry, ZipInputStream in, ZipOutputStream out) throws IOException {
        ZipEntry newEntry = new ZipEntry(entry.getName());
        out.putNextEntry(newEntry);
        writeBuffer(in, out);
        out.closeEntry();
    }

    private void writeBuffer(ZipInputStream in, ZipOutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = (in.read(buffer))) > 0) {
            out.write(buffer, 0, length);
        }
    }
}
