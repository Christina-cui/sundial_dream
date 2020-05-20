package com.cuijing.sundial_dream;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuijing.sundial_dream.condition.ActivityInfoCondition;
import com.cuijing.sundial_dream.entity.ActivityInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SundialDreamApplicationTests {



    @Test
    void contextLoads() {
        ActivityInfoCondition condition = new ActivityInfoCondition();
        condition.setTypeId(1);
        Page<ActivityInfo> page = new Page<>(1, 2);


    }


}
