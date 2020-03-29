package com.cuijing.sundial_dream.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class SuperEntity<T extends Model<?>> extends Model<T> {
    @TableId("id")
    private Long id;
    @TableField(
            value = "create_time",
            fill = FieldFill.INSERT
    )
    private LocalDateTime createTime;
    @TableField(
            value = "update_time",
            fill = FieldFill.INSERT_UPDATE
    )
    private LocalDateTime updateTime;

    public SuperEntity() {
    }

    protected Serializable pkVal() {
        return this.id;
    }

    public SuperEntity<T> setId(final Long id) {
        this.id = id;
        return this;
    }

    public SuperEntity<T> setCreateTime(final LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public SuperEntity<T> setUpdateTime(final LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Long getId() {
        return this.id;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public LocalDateTime getUpdateTime() {
        return this.updateTime;
    }
}
