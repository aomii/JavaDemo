/*
 *
 *  * ******************************************************************************
 *  *
 *  *                       Woodare PROPRIETARY INFORMATION
 *  *
 *  *           The information contained herein is proprietary to Woodare
 *  *            and shall not be reproduced or disclosed in whole or in part
 *  *                     or used for any design or manufacture
 *  *               without direct written authorization from FengDa.
 *  *
 *  *             Copyright (c) 2021 by Woodare.  All rights reserved.
 *  *
 *  * ******************************************************************************
 *
 */

package com.aoming.fkh.optimize.track_compress;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ZipUtil;

/**
 * ClassName ZlibUtils
 *
 * @author wang
 * Date 2021/11/10 20:08
 */
public class ZipHelper {

    /**
     * 压缩
     * @param data
     * @return
     */
    public static String compress(String data) {
        byte[] bytes = ZipUtil.gzip(data, "UTF8");
        // System.out.println("zip压缩后:"+new String(bytes));
        return Base64.encode(bytes);
    }
    /**
     *  解压缩
     * @param data
     * @return
     */
    public static String decompress(String data) {
        byte[] plainBytes = ZipUtil.unGzip(Base64.decode(data));
        return new String(plainBytes);
    }


    public static void main(String[] args) {

    }

}
