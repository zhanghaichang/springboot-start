package com.dwring.springboot.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 * @Title: SignatureUtils.java
 * @Package com.dwring.springboot.utils
 * @Description: 数字签名
 * @author haichangzhang
 * @date 2018年7月11日 下午5:17:45
 * @version V1.0
 */
public class SignatureUtils {

	/**
	 * 数字签名算法。JDK只提供了MD2withRSA, MD5withRSA, SHA1withRSA，其他的算法需要第三方包才能支持
	 **/
	public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

    /**
     * 签名，三步走
     * 1. 实例化，传入算法
     * 2. 初始化，传入私钥
     * 3. 签名
     * @param privateKey  私钥
     * @param plainText  原文
     * @return
     */
	public static byte[] sign(PrivateKey privateKey, byte[] plainText) {
		try {
			// 实例化
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			// 初始化，传入私钥
			signature.initSign(privateKey);
			// 更新
			signature.update(plainText);
			// 签名
			return signature.sign();
		} catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
			e.printStackTrace();
		}
		return null;
	}

    /**
     * 验签，三步走
     * 1. 实例化，传入算法
     * 2. 初始化，传入公钥
     * 3. 验签
     * @param publicKey  公钥
     * @param signatureVerify 原文签名后的内容
     * @param plainText  解密后的内容
     * @return
     */
	public static boolean verify(PublicKey publicKey, byte[] signatureVerify, byte[] plainText) {
		try {
			// 实例化
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			// 初始化
			signature.initVerify(publicKey);
			// 更新
			signature.update(plainText);
			// 验签
			return signature.verify(signatureVerify);
		} catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
			e.printStackTrace();
		}
		return false;
	}
}
