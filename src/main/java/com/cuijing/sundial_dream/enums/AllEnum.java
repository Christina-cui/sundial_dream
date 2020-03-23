package com.cuijing.sundial_dream.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

public interface AllEnum {
    enum Status implements IEnum {
        /**
         *
         */
        在职,

        /**
         * 禁用
         */
        禁用,

        /**
         * 离职
         */
        离职;

        @Override
        public Integer getValue() {
            return this.ordinal();
        }
    }
    enum ShenHeEnum implements IEnum{
        审核中,

        审核通过,

        审核失败;


        @Override
        public Integer getValue() {
            return this.ordinal();
        }

    }

}
