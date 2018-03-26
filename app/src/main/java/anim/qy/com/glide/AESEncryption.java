package anim.qy.com.glide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by ubuntu on 3/22/16.
 */
public class AESEncryption {
    private static AESEncryption _instance;
    private  Cipher _cipher;
    private static String CRYPTO_METHOD = "AES";
    
    private AESEncryption(){

    }

    public static AESEncryption instance() {
        if (null == _instance) {
            synchronized (AESEncryption.class) {
                if (null == _instance) {
                    _instance = new AESEncryption();
                }
            }
        }
        return _instance;
    }

    public void init(String mod, String keyStr, String ivStr) {
        try {
            _cipher = Cipher.getInstance(mod);
            SecretKeySpec key = new SecretKeySpec(CryptionTools.getByte(keyStr, "utf-8"), CRYPTO_METHOD);
            IvParameterSpec iv = new IvParameterSpec(CryptionTools.getByte(ivStr, "utf-8"));
            _cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encode(String src) {
        try {
            init("AES/CBC/PKCS5Padding",
                    "@2#kl$8%1x2datv*",
                    "1234567890123456");
            byte[] srcByteArray = CryptionTools.getByte(src, "utf-8");
            byte[] dstByteArray = _cipher.doFinal(srcByteArray);
            return CryptionTools.byteArray2Str(dstByteArray);
        } catch (Exception e) {
        	e.printStackTrace();
            return "";
        }
    }

    public void doCrypto(File inputFile,
                         File outputFile) throws IOException {
        try {
            InputStream is = new FileInputStream(inputFile);
            OutputStream out = new FileOutputStream(outputFile);
            CipherInputStream cis = new CipherInputStream(is, _cipher);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = cis.read(buffer)) > 0) {
                out.write(buffer, 0, r);
            }
            cis.close();
            is.close();
            out.close();

        } catch (Exception e) {

        }
    }
}
