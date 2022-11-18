package com.Otto.task.lib.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class ItemDTO {

    private String Pizza;
    private List<String> Toppings;
}
