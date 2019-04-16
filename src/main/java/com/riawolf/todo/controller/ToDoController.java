package com.riawolf.todo.controller;

import com.riawolf.todo.model.ItemsRepository;
import com.riawolf.todo.model.ToDoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/to-do")
public class ToDoController {
    @Autowired
    private ItemsRepository itemsRepository;

    @PostMapping(path = "/create")
    public @ResponseBody
    ToDoItem create(@RequestParam String label) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        ToDoItem n = new ToDoItem();
        n.setLabel(label);
        n.setDone(false);
        itemsRepository.save(n);
        return n;
    }

    @GetMapping(path = "/read")
    public @ResponseBody
    Iterable<ToDoItem> read() {
        return itemsRepository.findAll();
    }

    @PutMapping(path = "/update")
    public @ResponseBody
    ToDoItem update(@RequestParam Integer id, @RequestParam String label, @RequestParam Boolean isDone) {
        Optional<ToDoItem> optional = itemsRepository.findById(id);
        ToDoItem result =null;
        if(optional.isPresent()){
            result = optional.get();
            result.setLabel(label);
            result.setDone(isDone);
            itemsRepository.save(result);
        }
        return result;
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody
    ToDoItem delete(@RequestParam Integer id) {
        Optional<ToDoItem> optional = itemsRepository.findById(id);
        ToDoItem result = null;
        if(optional.isPresent()){
            result = optional.get();
            itemsRepository.delete(result);
        }
        return result;
    }
}
