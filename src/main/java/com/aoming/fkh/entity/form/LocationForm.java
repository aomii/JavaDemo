package com.aoming.fkh.entity.form;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: </p>
 *
 * @author llmm
 * @version 1.0.0
 * @email llmm @fkhwl.com
 * @date 2022.06.02 11:47
 * @Version 1.0.0
 * @since 2022 -05-26 11:27
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@ApiModel(value = "车辆定位查询对象", description = "车辆")
public class LocationForm  {

    private static final long serialVersionUID = 1L;

    @ApiParam("运单号")
    private String waybillId;

    @ApiParam("车牌号")
    private String vehiclePlateNo;

    @ApiParam("开始时间")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private String startTime;

    @ApiParam("结束时间")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone ="GMT+8")
    private String endTime;

    @ApiParam("定位类型【1:自动，2：手动】")
    private Integer trackType;

}
