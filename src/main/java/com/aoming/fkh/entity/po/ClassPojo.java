package com.aoming.fkh.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("class_table")
public class ClassPojo {
 
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
 
    @TableField(value = "class_name")
    private String className;
 
}