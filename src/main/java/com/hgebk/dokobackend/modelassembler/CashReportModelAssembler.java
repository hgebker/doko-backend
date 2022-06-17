package com.hgebk.dokobackend.modelassembler;

import com.hgebk.dokobackend.controller.ReportController;
import com.hgebk.dokobackend.controller.SemesterController;
import com.hgebk.dokobackend.dto.CashReportDTO;
import com.hgebk.dokobackend.dto.SemesterReportDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CashReportModelAssembler
        implements RepresentationModelAssembler<CashReportDTO, EntityModel<CashReportDTO>> {

    @Override
    public EntityModel<CashReportDTO> toModel(CashReportDTO cashReport) {
        return EntityModel.of(cashReport)
                          .add(linkTo(methodOn(ReportController.class).getCashReport()).withSelfRel());
    }
}
