package com.hgebk.dokobackend.dto;

import com.hgebk.dokobackend.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {
    private Player player;

    private Double value;
}
