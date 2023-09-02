package com.news.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @author 18786
 */
public class DigestUtil {
    public static String md5Hex(String str) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        return md5.digestHex(str);
    }
}
