package anim.qy.com.glide;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
 *
 *
 *
 */
public class AESDecryption {
	private static AESDecryption _instance;
	private Cipher _cipher = null;
	private static String CRYPTO_METHOD = "AES";

	private AESDecryption() {
	}

	public static AESDecryption instance() {
		if (null == _instance) {
			synchronized (AESDecryption.class) {
				if (null == _instance) {
					_instance = new AESDecryption();
				}
			}
		}
		return _instance;
	}

	public void init(String mod, String keyStr, String ivStr) {
		try {
			_cipher = Cipher.getInstance(mod);
			SecretKeySpec key = new SecretKeySpec(CryptionTools.getByte(keyStr,
					"utf-8"), CRYPTO_METHOD);
			IvParameterSpec iv = new IvParameterSpec(CryptionTools.getByte(ivStr,
					"utf-8"));
			_cipher.init(Cipher.DECRYPT_MODE, key, iv);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String decode(String src) {
		try {
			init("AES/CBC/PKCS5Padding",
					"@2#kl$8%1x2datv*",
					"1234567890123456");
			byte[] srcByteArray = CryptionTools.byteArray(src);
			byte[] dstByteArray = _cipher.doFinal(srcByteArray);
			return new String(dstByteArray, "utf-8");
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

	public CipherInputStream getInputStream(File inputFile){
		InputStream is = null;
		try {
			is = new FileInputStream(inputFile);
			CipherInputStream cis = new CipherInputStream(is, _cipher);
			return cis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
