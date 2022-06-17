package com.hgebk.dokobackend.modelassembler;

import com.hgebk.dokobackend.controller.EarningController;
import com.hgebk.dokobackend.model.Earning;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EarningModelAssembler implements RepresentationModelAssembler<Earning, EntityModel<Earning>> {
    @Override
    public EntityModel<Earning> toModel(Earning earning) {
        return EntityModel.of(earning)
                          .add(linkTo(methodOn(EarningController.class).getEarning(earning.getDescription())).withSelfRel());
    }
}
