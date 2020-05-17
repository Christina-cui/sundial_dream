package com.cuijing.sundial_dream.config;


import com.cuijing.sundial_dream.converter.BaseConverter;
import com.cuijing.sundial_dream.converter.EntityConverter;
import org.mapstruct.MapperConfig;

import static org.mapstruct.CollectionMappingStrategy.ADDER_PREFERRED;

@MapperConfig(
        uses = {BaseConverter.class, EntityConverter.class},
        componentModel = "spring",
        collectionMappingStrategy = ADDER_PREFERRED
)
@SuppressWarnings("unused")
public interface CommonConverterConfig {}
