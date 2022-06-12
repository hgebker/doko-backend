package com.hgebk.dokobackend.mapper;

import com.hgebk.dokobackend.dto.EveningDTO;
import com.hgebk.dokobackend.dto.PlayerResultDTO;
import com.hgebk.dokobackend.model.Evening;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EveningMapper {
    public EveningDTO toDTO(Evening domainEvening) {
        EveningDTO dto = new EveningDTO();
        dto.setDate(domainEvening.getDate());
        dto.setSemester(domainEvening.getSemester());
        dto.setAmountJan(domainEvening.getAmountJan());
        dto.setAmountTim(domainEvening.getAmountTim());
        dto.setAmountOle(domainEvening.getAmountOle());
        dto.setAmountLouisa(domainEvening.getAmountLouisa());
        dto.setAmountHannes(domainEvening.getAmountHannes());
        dto.setSum(domainEvening.getSum());
        dto.setAvg(domainEvening.getAvg());

        Optional<PlayerResultDTO> minResult = domainEvening.getMinResult();
        if (minResult.isPresent()) {
            dto.setMin(minResult.get().getValue());
            dto.setMinPlayer(minResult.get().getName());
        }

        Optional<PlayerResultDTO> maxResult = domainEvening.getMaxResult();
        if (maxResult.isPresent()) {
            dto.setMax(maxResult.get().getValue());
            dto.setMaxPlayer(maxResult.get().getName());
        }

        return dto;
    }
}
