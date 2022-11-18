package com.Otto.task.lib.mapper;

import com.Otto.task.lib.dtos.ItemDTO;
import com.Otto.task.lib.entity.Item;
import com.Otto.task.lib.entity.Pizza;
import com.Otto.task.lib.entity.Toppings;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemDtoToItemMapper implements ItemDtoToItemMapperInterface {

    @Override
    public List<Item> ToItem(List<ItemDTO> itemDTOs) {

        List<Toppings> toppings = new ArrayList<>();
       return itemDTOs.stream()
               .map(itemDTO -> new Item(Pizza.toPizza(itemDTO.getPizza()),
                      itemDTO.getToppings() != null ? itemDTO
                               .getToppings()
                               .stream()
                               .map(Toppings::toTopping)
                               .collect(Collectors.toList())
                               : toppings
                       ))
               .collect(Collectors.toList());

    }
}
