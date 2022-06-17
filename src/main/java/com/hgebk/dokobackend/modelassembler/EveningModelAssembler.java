package com.hgebk.dokobackend.modelassembler;

import com.hgebk.dokobackend.controller.EveningController;
import com.hgebk.dokobackend.domain.EveningResults;
import com.hgebk.dokobackend.dto.EveningDTO;
import com.hgebk.dokobackend.dto.EveningResultDTO;
import com.hgebk.dokobackend.entity.Evening;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@Relation(collectionRelation = "evenings", itemRelation = "evening")
public class EveningModelAssembler implements
                                   RepresentationModelAssembler<Evening, EveningDTO> {
    private final ModelMapper modelMapper;

    @Autowired
    public EveningModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public EveningDTO toModel(Evening evening) {
        List<EveningResultDTO> results = evening.getResults();
        EveningResults resultsDomain = new EveningResults(results);

        EveningDTO dto = modelMapper.map(evening, EveningDTO.class);
        dto.setResults(results);
        dto.setSum(resultsDomain.getSum());
        dto.setAvg(resultsDomain.getAvg());
        dto.setMin(resultsDomain.getMinResult().orElse(null));
        dto.setMax(resultsDomain.getMaxResult().orElse(null));

        return dto.add(linkTo(methodOn(EveningController.class).getEvening(evening.getDate())).withSelfRel());
    }
}
