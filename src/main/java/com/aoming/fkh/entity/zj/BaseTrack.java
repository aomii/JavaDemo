package com.aoming.fkh.entity.zj;

import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  物流轨迹基础类 -用于抽稀排序</p>
 *
 * @author aoming
 * @version 1.0.0
 * @email "mailto:aoming@fkhwl.com"
 * @date 2023.01.17 13:48
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "物流轨迹基础类", description = "物流轨迹基础类")
public class BaseTrack<T> {

    /** 中心点坐标纬度 */
    @Compress
    private Double lat;
    /** 中心点坐标经度 */
    @Compress
    private Double lon;


    @ApiModelProperty(name = "当前点发生时间")
    @Compress
    private Long gtm;

    /**用于稀疏抽取后排序*/
    @TableField(exist = false)
    private int sort;
}
