package com.hgebk.dokobackend.dto;

import com.hgebk.dokobackend.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EveningResultDTO {
    private Player player;

    private Double value;
}
