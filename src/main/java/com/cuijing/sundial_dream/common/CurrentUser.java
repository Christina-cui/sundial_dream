package com.cuijing.sundial_dream.common;


import com.cuijing.sundial_dream.entity.SuperEntity;

import java.util.Optional;

public interface CurrentUser {
    Optional<SuperEntity> getCurrentUser();
}