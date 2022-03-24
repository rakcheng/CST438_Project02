package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.ItemList;

import java.util.List;

public interface ItemListRepository extends CrudRepository<ItemList,Integer> {

    ItemList findByName(String name);
    ItemList findItemListById(Integer id);

    boolean existsByName(String name);

    @Query(value = "SELECT * FROM ItemList l WHERE l.user_id LIKE %:id%",
            nativeQuery = true)
    List<ItemList> findListsByUserId(@Param("id") Integer id);


}
