package com.cuijing.sundial_dream.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuijing.sundial_dream.condition.DonationCondition;
import com.cuijing.sundial_dream.entity.Admin;
import com.cuijing.sundial_dream.entity.Donation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface DonationService extends IService<Donation> {
    /**
     * 增加捐款记录
     */
    @Transactional
    void saveDonation(Donation donation);

    /**
     * 按照查询条件分页查询捐款记录
     */
    IPage<Donation> selectDonationByPage(Page page, DonationCondition condition);

    /**
     * 根据id获得捐款记录
     */
    Donation selectById(Long id);

    /**
     * 更新捐款记录
     */
    @Transactional
    boolean updateDonation(Donation donation);

    /**
     * 删除一条捐款记录
     */
    @Transactional
    boolean deleteDonation(Long donationId);


    /**
     * 根据条件删除捐款记录
     */
    @Transactional
    void deleteByCondition(DonationCondition condition);

}
