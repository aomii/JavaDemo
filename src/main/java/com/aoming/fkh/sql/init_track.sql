drop table if EXISTS   `logistics_driver_track` ;
CREATE TABLE `logistics_driver_track` (
    `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `driver_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '司机id',
    `waybill_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '运单id',
    `vehicle_plate_no` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '车头车牌',
    `center_lat` double DEFAULT NULL COMMENT '中心点坐标纬度',
    `center_lng` double DEFAULT NULL COMMENT '中心点坐标经度',
    `center_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '中心点地址',
    `upload_time` bigint DEFAULT NULL COMMENT '上传时间',
    `created_time` datetime DEFAULT NULL COMMENT '创建时间',
    `delete_status` tinyint(1) DEFAULT '0' COMMENT '是否移除 0：正常 1：移除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `vehicle_no_index` (`vehicle_plate_no`) USING BTREE COMMENT '车牌号索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='物流司机轨迹';

drop table if EXISTS   `logistics_driver_track_history` ;
CREATE TABLE `logistics_driver_track_history` (
    `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `driver_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '司机id',
    `waybill_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '运单id',
    `vehicle_plate_no` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '车头车牌',
    `compress_content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '压缩数据',
    `compress_begin_time` datetime DEFAULT NULL COMMENT '压缩数据最小时间',
    `compress_end_time` datetime DEFAULT NULL COMMENT '压缩数据最大时间',
    `created_time` bigint DEFAULT NULL COMMENT '上传时间',
    `block_offset` TINYINT DEFAULT 0 COMMENT '块内偏移量',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `vehicle_no_index` (`vehicle_plate_no`) USING BTREE COMMENT '车牌号索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='物流司机轨迹历史';