package com.aoming.fkh.entity.zj;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

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
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@Document(collection = WaybillHistoryTrackCompressDTO.WAYBILL_HISTORY_TRACK_COMPRESS)
public class WaybillHistoryTrackCompressDTO {

    public static final String WAYBILL_HISTORY_TRACK_COMPRESS = "waybill_history_track_compress";

    public static final String DRIVER_PHONE = "driver_phone";
    public static final String WAYBILL_ID = "waybill_id";
    public static final String VEHICLE_PLATE_NO = "vehicle_plate_no";
    public static final String COMPRESS_CONTENT = "compress_content";
    public static final String COMPRESS_BEGIN_TIME = "compress_begin_Time";
    public static final String COMPRESS_END_TIME = "compress_end_Time";
    public static final String CREATED_TIME = "created_time";

    private static final long serialVersionUID = 1L;

    @Id
    @Field("_id")
    @JsonIgnore
    private String id;

    /** 司机id */
    @Field(DRIVER_PHONE)
    private String driverPhone;
    /** 运单id */
    @Field(WAYBILL_ID)
    @Indexed
    private String waybillId;

    /** 车牌号 */
    @Field(VEHICLE_PLATE_NO)
    private String vehiclePlateNo;

    /** 压缩轨迹内容 */
    @Field(COMPRESS_CONTENT)
    private String compressContent;

    /** load时间 */
    @Field(COMPRESS_BEGIN_TIME)
    private Date compressBeginTime;

    /** unloa时间 */
    @Field(COMPRESS_END_TIME)
    private Date compressEndTime;

    /** 创建时间 (公共字段) */
    @Field(CREATED_TIME)
    private Date createdTime;

}
