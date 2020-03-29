package com.cuijing.sundial_dream.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuijing.sundial_dream.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {
    public BaseServiceImpl() {
    }

    @Transactional
    public boolean delete(Collection<? extends Serializable> idx) {
        return idx != null && !idx.isEmpty() ? this.removeByIds(idx) : false;
    }

    @Transactional
    public boolean deleteById(Serializable id) {
        if (Objects.nonNull(id)) {
            this.removeById(id);
            return true;
        } else {
            return false;
        }
    }
}

