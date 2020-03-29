package com.cuijing.sundial_dream.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuijing.sundial_dream.condition.DonationCondition;
import com.cuijing.sundial_dream.entity.Donation;
import com.cuijing.sundial_dream.mapper.DonationMapper;
import com.cuijing.sundial_dream.service.DonationService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DonationServiceImpl extends BaseServiceImpl<DonationMapper, Donation> implements DonationService {
    @Override
    public void saveDonation(Donation donation) {
        saveOrUpdate(donation);
    }

    @Override
    public IPage<Donation> selectDonationByPage(Page page, DonationCondition condition) {
        return baseMapper.selectAllDonation(page,condition);
    }

    @Override
    public Donation selectById(Long id) {
        return selectById(id);
    }

    @Override
    public boolean updateDonation(Donation donation) {
        return saveOrUpdate(donation);
    }

    @Override
    public boolean deleteDonation(Long donationId) {
        return deleteById(donationId);
    }

    @Override
    public void deleteByCondition(DonationCondition condition) {
        baseMapper.deleteByCondition(condition);
    }
}
