package com.aoming.fkh.entity.zj;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  </p>
 *
 * @author aoming
 * @version x.x.x
 * @email "mailto:aoming@fkhwl.com"
 * @date 2022.06.06 09:28
 * @since x.x.x
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class TrackDetailDTO extends BaseTrack<TrackDetailDTO>{


    // /** 中心点坐标纬度 */
    // @Compress
    // private Double lat;
    // /** 中心点坐标经度 */
    // @Compress
    // private Double lon;





    /*正北方向夹角*/
    @Compress
    private Long agl;

    // /*海拔(单位:米)*/
    @Compress
    private Long hgt;

    /*里程(mlg*0.1=公里)*/
    @Compress
    private Double mlg;

    /*GPS 速度(1/10.0km/h)*/
    @Compress
    private Double spd;

}
