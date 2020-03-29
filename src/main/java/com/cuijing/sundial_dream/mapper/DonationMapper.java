package com.cuijing.sundial_dream.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuijing.sundial_dream.condition.DonationCondition;
import com.cuijing.sundial_dream.entity.Donation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DonationMapper extends BaseMapper<Donation> {

    IPage<Donation> selectAllDonation(Page page, DonationCondition condition);

    void deleteByCondition(DonationCondition condition);

}
