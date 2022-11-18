package com.Otto.task.lib.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class Item {

    private Pizza pizza;
    private List<Toppings> toppings;
}