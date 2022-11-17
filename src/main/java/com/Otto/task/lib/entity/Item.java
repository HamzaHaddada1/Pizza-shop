package com.Otto.task.lib.entity;

import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Getter
@ToString
public class Item {

    private Pizza pizza;
    private Set<Toppings> toppings;
}