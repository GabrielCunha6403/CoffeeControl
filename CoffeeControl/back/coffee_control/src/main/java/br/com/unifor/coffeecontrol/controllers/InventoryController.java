package br.com.unifor.coffeecontrol.controllers;

import br.com.unifor.coffeecontrol.dtos.InventoryDto;
import br.com.unifor.coffeecontrol.forms.InventoryForm;
import br.com.unifor.coffeecontrol.forms.UpdatedInventoryForm;
import br.com.unifor.coffeecontrol.modelos.Inventory;
import br.com.unifor.coffeecontrol.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public Page<InventoryDto> listInventories(Pageable paginacao){
        return inventoryService.listInventories(paginacao);
    }

    @PostMapping
    public ResponseEntity<InventoryDto> signUpInventory(@RequestBody InventoryForm inventoryForm, UriComponentsBuilder uriBuilder){
        return inventoryService.signUpInventory(inventoryForm, uriBuilder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDto> updateSpecificInventoryById(@PathVariable int id, @RequestBody UpdatedInventoryForm form) {
        return inventoryService.updateSpecificInventoryById(id, form);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Inventory> deleteSpecificInventoryById(@PathVariable int id) {
        return inventoryService.deleteSpecificInventoryById(id);
    }
}
