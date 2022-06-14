package com.hgebk.dokobackend.mapper;

import com.hgebk.dokobackend.dto.EveningDTO;
import com.hgebk.dokobackend.dto.PlayerResultDTO;
import com.hgebk.dokobackend.model.Evening;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EveningMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public EveningMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EveningDTO toDTO(Evening domainEvening) {
        EveningDTO dto = modelMapper.map(domainEvening, EveningDTO.class);

        Optional<PlayerResultDTO> minResult = dto.getMinResult();
        if (minResult.isPresent()) {
            dto.setMin(minResult.get().getValue());
            dto.setMinPlayer(minResult.get().getName());
        }

        Optional<PlayerResultDTO> maxResult = dto.getMaxResult();
        if (maxResult.isPresent()) {
            dto.setMax(maxResult.get().getValue());
            dto.setMaxPlayer(maxResult.get().getName());
        }

        return dto;
    }
}
