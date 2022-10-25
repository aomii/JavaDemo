package com.aoming.fkh.entity.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
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
@ApiModel(value = "物流司机轨迹", description = "物流司机轨迹")
public class BaseTrack<T> {


    /** 中心点坐标纬度 */
    private Double centerLat;
    /** 中心点坐标经度 */
    private Double centerLng;

    @ApiModelProperty(name = "当前点发生时间")
    private Long uploadTime;


    @TableField(exist = false)
    private int sort;


}
