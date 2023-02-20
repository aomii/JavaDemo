package com.aoming.fkh.entity.zj;


import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;


/**
* 运单表
*
* @author admin 
* @since 1.0 2022-12-21
*/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class WaybillDTO {
	private static final long serialVersionUID = 1L;
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("运单状态：10待装货 20已装货 30已收货")
    private Integer waybillStatus;

    @ApiModelProperty("审核状态：10待审核 20审核通过")
    private Integer checkStatus;

    @ApiModelProperty("运单编号")
    private String waybillNo;

    @ApiModelProperty("关联项目id")
    private Long projectId;

    @ApiModelProperty("关联项目名称")
    private String projectName;

    @ApiModelProperty("车辆ID")
    private Long vehicleId;

    @ApiModelProperty("车牌号")
    private String plateNo;

    @ApiModelProperty("司机姓名")
    private String driverName;

    @ApiModelProperty("司机电话")
    private String driverPhone;

    @ApiModelProperty("货物名称")
    private String materialName;

    @ApiModelProperty("发货地点")
    private String sendAddress;

    @ApiModelProperty("发货详细地点")
    private String sendAddressDetail;

    @ApiModelProperty("收货地点")
    private String receiveAddress;

    @ApiModelProperty("收货详细地点")
    private String receiveAddressDetail;

    @ApiModelProperty("装货时间")
    private Date loadTime;

    @ApiModelProperty("卸货时间")
    private Date unloadTime;

    @ApiModelProperty("审核时间")
    private Date checkTime;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("创建人ID")
    private Long createUserId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改人")
    private String updateUser;

    @ApiModelProperty("修改人ID")
    private Long updateUserId;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("是否已删除：0=未删除，1=已删除")
    private Integer deleted;


}