package com.onlinetutorialspoint.controller;
 
import com.onlinetutorialspoint.exception.ItemNotFoundException;
import com.onlinetutorialspoint.model.Item;
import com.onlinetutorialspoint.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

@RestController
public class ItemController {
	 @Autowired(required=true)
	 ItemRepository itemRepository;

    @RequestMapping("/getAllItems")
    @ResponseBody
    public ResponseEntity<List<Item>> getAllItems(){ 
    	 List<Item> items= itemRepository.findAll();
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }

    @GetMapping("/item/{itemId}")
    @ResponseBody
    public ResponseEntity<Item> getItem(@PathVariable int itemId){
        if(itemId <= 0){
            throw new ItemNotFoundException("Invalid ItemId");
        }
        Item item = itemRepository.findById(itemId);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @PostMapping(value = "/addItem",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> addItem(@RequestBody Item item){
    	itemRepository.save(item);
        return new ResponseEntity<Item>(item, HttpStatus.CREATED);
    }
 
    @RequestMapping(value = "/updateItem/{id}", method = RequestMethod.PUT) 
    @ResponseBody
    public ResponseEntity<Item> updateStudent(@PathVariable int id, @RequestBody @Valid Item itemData){
    Item updatedItem = null;
        if(id != 0 && id > 0){
        	Item itemDatas = itemRepository.findById(id);  
        	itemDatas.setName(itemData.getName());
        	itemDatas.setCategory(itemData.getCategory());
        	updatedItem = itemRepository.save(itemDatas);
        }
        return new ResponseEntity<Item>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteItem(@PathVariable long id){
    	itemRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    } 
}
