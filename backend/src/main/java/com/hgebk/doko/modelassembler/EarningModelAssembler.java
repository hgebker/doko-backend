package com.hgebk.doko.modelassembler;

import com.hgebk.doko.controller.EarningController;
import com.hgebk.doko.entity.Earning;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EarningModelAssembler implements RepresentationModelAssembler<Earning, EntityModel<Earning>> {
    @Override
    public EntityModel<Earning> toModel(Earning earning) {
        return EntityModel
                .of(earning)
                .add(linkTo(methodOn(EarningController.class).getEarning(earning.getDescription())).withSelfRel());
    }
}
