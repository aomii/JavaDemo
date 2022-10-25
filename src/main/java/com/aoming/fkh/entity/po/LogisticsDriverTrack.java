package com.aoming.fkh.entity.po;

import com.aoming.fkh.entity.base.BaseTrack;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 围栏告警 实体类  </p>
 *
 * @author wudq
 * @version 1.0.0
 * @email "mailto:wudq@fkhwl.com"
 * @date 2021.08.24 13:48
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("logistics_driver_track")
@ApiModel(value = "物流司机轨迹", description = "物流司机轨迹")
public class LogisticsDriverTrack extends BaseTrack<LogisticsDriverTrack> {

    public static final String DRIVER_ID = "driver_id";
    public static final String WAYBILL_ID = "waybill_id";
    public static final String VEHICLE_PLATE_NO = "vehicle_plate_no";
    public static final String CENTER_LAT = "centerLat";
    public static final String CENTER_LNG = "centerLng";
    public static final String CENTER_ADDR = "center_addr";
    public static final String UPLOAD_TIME = "upload_time";

    private static final long serialVersionUID = 1L;

    // @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /** 司机id */
    private String driverId;
    /** 运单id */
    private String waybillId;

    /** 车牌号 */
    private String vehiclePlateNo;

    /** 中心点地址 */
    private String centerAddr;




    private int deleteStatus;
    /** 创建时间 (公共字段) */
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;


}
