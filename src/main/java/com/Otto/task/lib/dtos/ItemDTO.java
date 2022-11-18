package com.Otto.task.lib.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class ItemDTO {

    @NotNull
    @NotEmpty
    private String pizza;
    private List<String> toppings;
}
