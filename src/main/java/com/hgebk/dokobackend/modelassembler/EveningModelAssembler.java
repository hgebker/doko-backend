package com.hgebk.dokobackend.modelassembler;

import com.hgebk.dokobackend.controller.EveningController;
import com.hgebk.dokobackend.controller.ExpenseController;
import com.hgebk.dokobackend.dto.EveningDTO;
import com.hgebk.dokobackend.model.Evening;
import com.hgebk.dokobackend.model.Expense;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@Relation(collectionRelation = "evenings", itemRelation = "evening")
public class EveningModelAssembler implements
                                   RepresentationModelAssembler<EveningDTO, EntityModel<EveningDTO>> {

    @Override
    public EntityModel<EveningDTO> toModel(EveningDTO evening) {
        return EntityModel.of(evening)
                          .add(linkTo(methodOn(EveningController.class).getEvening(evening.getDate())).withSelfRel());
    }
}
