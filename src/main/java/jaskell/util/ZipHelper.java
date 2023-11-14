package jaskell.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ZipHelper {
    static public byte[] decompressByteArray(byte[] bytes, int buffer_size) throws DataFormatException, IOException {
        ByteArrayOutputStream baos = null;
        Inflater iflr = new Inflater();
        iflr.setInput(bytes);
        baos = new ByteArrayOutputStream();
        byte[] tmp = new byte[buffer_size];
        try{
            while(!iflr.finished()){
                int size = iflr.inflate(tmp);
                baos.write(tmp, 0, size);
            }
        } finally {
            baos.close();
        }
        return baos.toByteArray();
    }

    static public byte[] decompressByteArray(byte[] bytes)  throws DataFormatException, IOException {
        return decompressByteArray(bytes, 64);
    }

    static public byte[] compressByteArray(byte[] bytes, int buffer_size) throws DataFormatException, IOException {
        ByteArrayOutputStream baos = null;
        Deflater dflr = new Deflater();
        dflr.setInput(bytes);
        dflr.finish();
        baos = new ByteArrayOutputStream();

        byte[] tmp = new byte[buffer_size];
        try{
            while(!dflr.finished()){
                int size = dflr.deflate(tmp);
                baos.write(tmp, 0, size);
            }
        } finally {
            dflr.end();
            baos.close();
        }
        return baos.toByteArray();
    }

    static public byte[] compressByteArray(byte[] bytes) throws DataFormatException, IOException {
        return compressByteArray(bytes, 64);
    }
}
