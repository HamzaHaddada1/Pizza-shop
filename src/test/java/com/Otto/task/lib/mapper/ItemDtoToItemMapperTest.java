package com.Otto.task.lib.mapper;

import com.Otto.task.lib.dtos.ItemDTO;
import com.Otto.task.lib.entity.Item;
import com.Otto.task.lib.entity.Pizza;
import com.Otto.task.lib.entity.Toppings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemDtoToItemMapperTest {

    @Mock
    private ItemDtoToItemMapperInterface mapper;

    private List<Toppings> toppings;
    private List<String> toppingsDTO;
    private ItemDTO itemDto;
    private Item item;
    private List<ItemDTO> dtos;
    private List<Item> items;
    private Pizza pizza;
    private Toppings toppings1 ;
    private Toppings toppings2 ;

    @BeforeEach
    public void init() {
        toppings = new ArrayList<>();
        pizza = Pizza.ROMA;
        toppings1 = Toppings.ONION;
        toppings2 = Toppings.OLIVES;
        toppings.add(toppings1);
        toppings.add(toppings2);
        item = new Item(pizza, toppings);
        items = new ArrayList<>();
        items.add(item);

        String pizzaDto = "ROMA";
        String topping1DTO = "ONION";
        String topping2DTO = "OLIVES";

        toppingsDTO = new ArrayList<>();
        toppingsDTO.add(topping1DTO);
        toppingsDTO.add(topping2DTO);
        itemDto = new ItemDTO(pizzaDto, toppingsDTO);
        dtos = new ArrayList<>();
        dtos.add(itemDto);
        when(mapper.ToItem(dtos)).thenReturn(items);
    }
    @Test
    void toItem() {
        assertEquals(pizza, mapper.ToItem(dtos).get(0).getPizza());
        assertEquals(toppings, mapper.ToItem(dtos).get(0).getToppings());
    }
}