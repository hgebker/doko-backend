package com.hgebk.dokobackend.mapper;

import com.hgebk.dokobackend.dto.EveningDTO;
import com.hgebk.dokobackend.dto.ResultDTO;
import com.hgebk.dokobackend.model.Evening;
import com.hgebk.dokobackend.model.Player;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EveningMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public EveningMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EveningDTO toDTO(Evening domainEvening) {
        EveningDTO dto = modelMapper.map(domainEvening, EveningDTO.class);

        dto.setResults(new ResultDTO[]{
                new ResultDTO(Player.JAN, domainEvening.getResultJan()),
                new ResultDTO(Player.TIM, domainEvening.getResultTim()),
                new ResultDTO(Player.OLE, domainEvening.getResultOle()),
                new ResultDTO(Player.LOUISA, domainEvening.getResultLouisa()),
                new ResultDTO(Player.HANNES, domainEvening.getResultHannes())
        });

        return dto;
    }
}
