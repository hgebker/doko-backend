package com.hgebk.dokobackend.mapper;

import com.hgebk.dokobackend.domain.EveningResults;
import com.hgebk.dokobackend.dto.EveningDTO;
import com.hgebk.dokobackend.dto.EveningResultDTO;
import com.hgebk.dokobackend.model.Evening;
import com.hgebk.dokobackend.model.Player;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EveningMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public EveningMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<EveningResultDTO> toResults(Evening evening) {
        return List.of(new EveningResultDTO(Player.JAN, evening.getResultJan()),
                           new EveningResultDTO(Player.TIM, evening.getResultTim()),
                           new EveningResultDTO(Player.OLE, evening.getResultOle()),
                           new EveningResultDTO(Player.LOUISA, evening.getResultLouisa()),
                           new EveningResultDTO(Player.HANNES, evening.getResultHannes()));
    }

    public EveningDTO toDTO(Evening evening) {
        List<EveningResultDTO> results = toResults(evening);
        EveningResults resultsDomain = new EveningResults(results);

        EveningDTO dto = modelMapper.map(evening, EveningDTO.class);
        dto.setResults(results);
        dto.setSum(resultsDomain.getSum());
        dto.setAvg(resultsDomain.getAvg());
        dto.setMin(resultsDomain.getMinResult().orElse(null));
        dto.setMax(resultsDomain.getMaxResult().orElse(null));
        return dto;
    }
}
