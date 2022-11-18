package com.Otto.task.lib.mapper;

import com.Otto.task.lib.dtos.ItemDTO;
import com.Otto.task.lib.entity.Item;

import java.util.List;

public interface ItemDtoToItemMapperInterface {

    List<Item> ToItem(List<ItemDTO> itemDTOs);
}
