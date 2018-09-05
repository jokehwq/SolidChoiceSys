package com.foreveross.webbase.common.utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * DES工具类
 * 
 * @author 
 * @createTime 
 * @history 1.修改时间,修改;修改内容：
 * 
 */
public class DESUtil {
	
	private final static String DES_KEY = "azxHw3Jx0QqpeomrVROrMmmN";
	
    public static String desCrypto(String datasource) throws Exception {            
    	try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(DES_KEY.getBytes());
			//创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			//Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			//用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			//现在，获取数据并加密
			//正式执行加密操作
			return new String(Hex.encodeHex(cipher.doFinal(datasource.getBytes())));
		} catch (Exception e) {
			throw new Exception("加密失败");
		} 
    }
    
	public static String decrypt(String src) throws Exception{
        try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom random = new SecureRandom();
			// 创建一个DESKeySpec对象
			DESKeySpec desKey = new DESKeySpec(DES_KEY.getBytes());
			// 创建一个密匙工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// 将DESKeySpec对象转换成SecretKey对象
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			// 真正开始解密操作
			String s = new String(cipher.doFinal(Hex.decodeHex(src.toCharArray())));
			if(doField(s)){
				return s;
			}
			else{
				throw new Exception("参数被篡改");
			}
		} catch (Exception e) {
//			e.printStackTrace();
			throw new Exception("解密失败"+ e.getMessage());
		}
    }
    
  //core.js版本链接路径更新
	public static int getV(){
		return   (int) Math.ceil((System.currentTimeMillis()/(60*60*24*1000))*1.00);
	}
    
    public static void main(String[] args) throws Exception{
    	String params = "22d838774b9c4013748cecd4ea43352845b84325b362aa5bae3f9a93831a3ba5a244bc20cfbadd10bda99f956c3c07a59f39ccab037b2fb9e10f07145441565bd35305a0ceed825e9af626952bde82e453e39e45e3f18d45c8ebe8f378be4f35ebab542905369926edc00a76eb4c7f3770060028415ad54337602141ab3f1e87d4efe6dbe04f1c745502094afc6bd39a687477c0a46065dfb2121f85811bbd484150ced039443bf1f8a6cf2f9a99387423e61e08df852eadb6fb6029492540fccea92066f625d236ab9a8ff59afbea7a76cc3998f0c0a3829189da7d6653bcad7aa6378d98a725a438f9a9c18eadff0439cbdcdbfc3f0e3e6aa6cc94d321860aff29d9fd1bf3c760caf8e725058473312adfc5377792056d05c19a383eaebe32ab9a8ff59afbea7ab2a53bb10d851f83d0807640d210d0791e1a4d5eaa0432851b37fb64616e4343bb33131eec9da3186aa6cc94d321860a1b7c1343e65a52a0caf8e72505847331bb121a3e1b905eaf2ce57c1b8d2c6890040f0f116b078db7a5f6e7ee0cdcdd9b18333ef78e101f1b88f85749325ec93055c28ad01edb19b91eed1b8cc9a123d802f190142227dcc7655e07f310aa22ca36de0a5fc12e83f4";
    	String str="22d838774b9c4013748cecd4ea43352845b84325b362aa5bae3f9a93831a3ba5a244bc20cfbadd10bda99f956c3c07a59f39ccab037b2fb9e10f07145441565b7003d544770535fb9af626952bde82e453e39e45e3f18d45c8ebe8f378be4f35ebab542905369926edc00a76eb4c7f3770060028415ad54337602141ab3f1e87a13d9d350e5b39cd51a029fd7e91621aeda02e6298cfae4877c4028899503b2764643195b89018c1b8dbf84b7080a2e41ed90746eea7c1a964a6cf296ddd973f3138cbb48de567d5406fe1515191c4c77eaa6747fc6e13adca78248083dd900a06b372753fb259483b07e05b957848b433396af3d4100d204c7eacf6f30b927ece635810ed5283e65cd6e11a1404564c4d66e1b70bcda8b1a50df6d28e29380a406fe1515191c4c7869c328ed301b07bf3f1cf61204e1c9d912da698b2a8466d769c243aa8eb68bc33396af3d4100d2059fd0003a49e22ff1c402163667e68915cd6e11a1404564c9cdf4336635c3a2e2e839addd5e7e5b573198d7a85b237949e05373198a4a9088e5f2cde2159f7755889c199cc855abcd29719b426d47cdf3667ff7f1f73aee8e7dad9932b84ba11ca10b538fbbdcd770fe6a9ff7eabfd90fa745a3f6eb7203a";
    	System.out.println(decrypt(str));
        //待加密内容
//		String params = "testchunyu";
//		System.out.println(DESUtil.desCrypto(params));
//		//String str = "a9f4e59602594ccb86e4ff3db84ea15c:oXg8EuBSxyKN5yaNhaGcFIm7kEi8:23";
//    	String str="a9f4e59602594ccb86e4ff3db84ea15k";
//        String result=desCrypto(str);
//        System.out.println(result);
//        //result=decrypt("bb59d5c6914fd2b70aff4047bd333b683cc246f042d20ba29dd453ca157d0a9015517bddba8292bc");//测试
//        result=decrypt("6bb51333621fe566cc2b09b304320f0b0ed6a4e8dc3c2328fd0f78dbbf618f1981d313730a9b17c4a9526b1bba5ad9c8fd706a7066a65435b2891012b842a5680efa659ea988e361c92f5b1ec35dd121");//开发
//        //生产result=decrypt("bb59d5c6914fd2b70aff4047bd333b683cc246f042d20ba2cafb7d465afcc30a15517bddba8292bc");
//        System.out.println(result);
    }
    
    public static String decrypt(String src,String key) throws Exception{
        try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom random = new SecureRandom();
			// 创建一个DESKeySpec对象
			DESKeySpec desKey = new DESKeySpec(key.getBytes());
			// 创建一个密匙工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// 将DESKeySpec对象转换成SecretKey对象
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			// 真正开始解密操作
			return new String(cipher.doFinal(Hex.decodeHex(src.toCharArray())));
		} catch (Exception e) {
			throw new Exception("解密失败");
		}
    }
    
    public static boolean doField(String str) {
		if (str.indexOf("<") == -1 && str.indexOf(">") == -1
				&& str.indexOf("script") == -1 && str.indexOf("style") == -1) {
			return true;
		}
		return false;
	}
}
