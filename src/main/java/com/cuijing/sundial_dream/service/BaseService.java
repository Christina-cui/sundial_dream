package com.cuijing.sundial_dream.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;

public interface BaseService<T> extends IService<T> {
    boolean delete(Collection<? extends Serializable> idx);

    boolean deleteById(Serializable id);
}
