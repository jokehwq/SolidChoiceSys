package com.foreveross.webbase.weixin.sdk.util;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

/**
 *
 * @author ngh
 * AES128 算法
 *
 * CBC 模式
 *
 * PKCS7Padding 填充模式
 *
 * CBC模式需要添加一个参数iv
 *
 * 介于java 不支持PKCS7Padding，只支持PKCS5Padding 但是PKCS7Padding 和 PKCS5Padding 没有什么区别
 * 要实现在java端用PKCS7Padding填充，需要用到bouncycastle组件来实现
 */
public class AES {
 // 算法名称
 final String KEY_ALGORITHM = "AES";
 // 加解密算法/模式/填充方式
 final String algorithmStr = "AES/CBC/PKCS7Padding";
 //
 private Key key;
 private Cipher cipher;
 boolean isInited = false;

 public void init(byte[] keyBytes) {

  // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
  int base = 16;
  if (keyBytes.length % base != 0) {
   int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
   byte[] temp = new byte[groups * base];
   Arrays.fill(temp, (byte) 0);
   System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
   keyBytes = temp;
  }
  // 初始化
  Security.addProvider(new BouncyCastleProvider());
  // 转化成JAVA的密钥格式
  key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
  try {
   // 初始化cipher
   cipher = Cipher.getInstance(algorithmStr, "BC");
  } catch (NoSuchAlgorithmException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (NoSuchPaddingException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (NoSuchProviderException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }
 /**
  * 加密方法
  *
  * @param content
  *            要加密的字符串
  * @param keyBytes
  *            加密密钥
  * @return
  */
/* public byte[] encrypt(byte[] content, byte[] keyBytes) {
  byte[] encryptedText = null;
  init(keyBytes);
  System.out.println("IV：" + new String(iv));
  try {
   cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
   encryptedText = cipher.doFinal(content);
  } catch (Exception e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  return encryptedText;
 }*/
 /**
  * 解密方法
  *
  * @param encryptedStr
  *            要解密的字符串
  * @param keyStr
  *            解密密钥
  * @param ivStr
  *            偏移量
  * @return
  */
 public String decrypt(String encryptedStr, String keyStr, String ivStr) {

  //被加密的数据
  byte[] encryptedDataByte = Base64.decodeBase64(encryptedStr);
  //加密秘钥
  byte[] keyByte = Base64.decodeBase64(keyStr);
  //偏移量
  byte[] ivByte = Base64.decodeBase64(ivStr);

  String deCryptedText = "";
  init(keyByte);
  try {
   cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivByte));
   deCryptedText = new String(cipher.doFinal(encryptedDataByte),"utf8");
  } catch (Exception e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  return deCryptedText;
 }

 public static void main(String[] args) {

  // 解密方法
  String data = "QIPUKxfL9EiKBNSqHQwkZmw/8DPNUuklVyDG5KwQjivaj5CT9egVc2Ya3UqZF1diKrsOtEMidWjlI4H+ck8b+ky3x3qC9id6Kmuj3GmSMhz09AN/ICLG9TsGJy1RE/n/j/Osi3HoYe74kMhv6j0efCFjYJoT8Vy2wc9VOS/yeyj5iupjIltp1P+HtAUy58QMKWudaK12uyHC0DOMxMuYmnNMRJpvimIALdfnH9X4hr5AHo2bwgyR3PpDnozxPPzVQtM/T2fJPmorPQWxJEAOAKihHh16YrIKjIvyDkq0SCbrbzUHvEsveetklf4s1zjFNjuosxdd/CnpuT/1f/Ik07P8ASaMRNB0Cbfo7p5YA1KMBv5rGAzOx3gjQH9PzpjcQp0DAJCb8mxw3eAde95LKX5eujg6Cr960E+eWrFWrIVc2/0eA4X3zv2o75Cy7ddu";
  String key = "JcD5GzL51PK+lL4Ql+jo+Q==";
  String iv = "JVtFSgMnABdqGiDyJDKlow==";

  try {
   System.out.println("解密后的内容：" + new AES().decrypt(data, key, iv));
  } catch (Exception e) {
   e.printStackTrace();
  }
 }
}