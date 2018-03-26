package anim.qy.com.glide;

/**
 * Created by ubuntu on 3/22/16.
 */
public class CryptionTools {
	private static final char[] encodes = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };

	public static byte[] getByte(String src, String charsetName) {
		try {
			return src.getBytes(charsetName);
		} catch (Exception e) {
			return new byte[0];
		}
	}

	private static byte covert2Byte(char paramChar) {
		if((paramChar >= '0') && (paramChar <= '9')) {
			return (byte) (paramChar - '0');
		}
		if((paramChar >= 'a') && (paramChar <= 'f')) {
			return (byte) (paramChar - 'a' + 10);
		}
		if((paramChar >= 'A') && (paramChar <= 'F')) {
			return (byte) (paramChar - 'A' + 10);
		}
		return 0;
	}

	public static byte[] byteArray(String paramString) {
		if ((paramString == null) || (paramString.equals(""))) {
			return new byte[0];
		}
		byte[] arrayOfByte = new byte[paramString.length() / 2];
		int i = 0;
		while (i < arrayOfByte.length) {
			char c1 = paramString.charAt(i * 2);
			char c2 = paramString.charAt(i * 2 + 1);
			arrayOfByte[i] = ((byte) (covert2Byte(c1) * 16 + covert2Byte(c2)));
			i += 1;
		}
		return arrayOfByte;
	}

	public static String byteArray2Str(byte[] srcArray) {
		if (srcArray == null || srcArray.length == 0) {
			return "";
		}

		char[] charArray = new char[srcArray.length * 2];
		int idx = 0;
		while (idx < srcArray.length) {
			int j = srcArray[idx];
			charArray[idx * 2 + 1] = encodes[j & 0xf];
			j = (byte) (j >>> 4);
			charArray[idx * 2] = encodes[j & 0xf];
			idx += 1;
		}
		return new String(charArray);
    }
}