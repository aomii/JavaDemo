/*
 *
 *  *
 *  *  * ******************************************************************************
 *  *  *
 *  *  *                       Woodare PROPRIETARY INFORMATION
 *  *  *
 *  *  *           The information contained herein is proprietary to Woodare
 *  *  *            and shall not be reproduced or disclosed in whole or in part
 *  *  *                     or used for any design or manufacture
 *  *  *               without direct written authorization from FengDa.
 *  *  *
 *  *  *             Copyright (c) 2021 by Woodare.  All rights reserved.
 *  *  *
 *  *  * ******************************************************************************
 *  *
 *
 *
 */

package com.aoming.fkh.optimize.track_compress;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * ClassName TripDetailRequest
 *
 * @author wang
 * Date 2022/6/13 16:58
 */
@Data
@ApiModel("行程详情传输对象")
@Accessors(chain = true)
public class TripDetailData {

    @ApiModelProperty(name = "唯一标识")
    private String uid;

    @ApiModelProperty(name = "tripUid")
    private String tripUid;

    @ApiModelProperty(name = "index")
    private Long idx;

    @ApiModelProperty(name = "当前点纬度")
    private Double lat;

    @ApiModelProperty(name = "当前点经度")
    private Double lng;

    @ApiModelProperty(name = "当前点速度")
    private Long speed;

    @ApiModelProperty(name = "当前点角度")
    private Double bearing;

    @ApiModelProperty(name = "当前点发生时间")
    private Long timestamp;

    /**
     * speed		:long
     * rpm		:long
     * throttle  	:double
     * waterTemp	:double
     * cvtTemp	:double
     * betteryVolt	:double
     * gpsHeight	:double
     */

    @ApiModelProperty(name = "转速")
    private Long rpm;

    @ApiModelProperty(name = "加速度")
    private Long acceleration ;

    @ApiModelProperty(name = "油量")
    private Double throttle;

    @ApiModelProperty(name = "水温")
    private Double waterTemp;

    @ApiModelProperty(name = "变速箱温度")
    private Double cvtTemp;

    @ApiModelProperty(name = "电池电压")
    private Double betteryVolt;

    @ApiModelProperty(name = "海拔")
    private Double gpsHeight;


}
