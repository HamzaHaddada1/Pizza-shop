package com.Otto.task.lib.mapper;

import com.Otto.task.lib.dtos.ItemDTO;
import com.Otto.task.lib.entity.Item;
import com.Otto.task.lib.entity.Pizza;
import com.Otto.task.lib.entity.Toppings;

import java.util.List;
import java.util.stream.Collectors;

public class ItemDtoToItemMapper implements ItemDtoToItemMapperInterface {

    @Override
    public List<Item> ToItem(List<ItemDTO> itemDTOs) {
        List<Toppings> toppings = itemDTOs.stream()
                .map(itemDTO -> itemDTO
                        .getToppings()
                        .stream()
                        .map(Toppings::toTopping)
                        .collect(Collectors.toList())

                ).toList().get(0);
       return itemDTOs.stream()
               .map(itemDTO -> new Item(Pizza.toPizza(itemDTO.getPizza()), toppings))
               .collect(Collectors.toList());

    }
}
