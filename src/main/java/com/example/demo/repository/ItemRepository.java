package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Item;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item,Integer>{
    Item findByName(String name);

    Item findItemById(Integer id);

    boolean existsByName(String name);

    @Query(value = "SELECT * FROM item i WHERE i.item_list_id LIKE %:id%",
            nativeQuery = true)
    List<Item> findItemsByListId(@Param("id") Integer id);

}