package br.com.unifor.coffeecontrol.services.Impl;

import br.com.unifor.coffeecontrol.dtos.InventoryDto;
import br.com.unifor.coffeecontrol.forms.InventoryForm;
import br.com.unifor.coffeecontrol.forms.UpdatedInventoryForm;
import br.com.unifor.coffeecontrol.modelos.Inventory;
import br.com.unifor.coffeecontrol.repositories.InventoryRepository;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import br.com.unifor.coffeecontrol.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<InventoryDto> listInventories(Pageable paginacao) {
        Page<Inventory> inventories = inventoryRepository.findAll(paginacao);
        return InventoryDto.convert(inventories);
    }

    @Override
    public ResponseEntity<InventoryDto> signUpInventory(InventoryForm inventoryForm, UriComponentsBuilder uriBuilder) {
        Inventory inventory = inventoryForm.convert();
        inventoryRepository.save(inventory);

        URI uri = uriBuilder.path("/inventories/{id}").buildAndExpand(inventory.getId()).toUri();
        return ResponseEntity.created(uri).body(new InventoryDto(inventory));
    }

    @Override
    public ResponseEntity<InventoryDto> updateSpecificInventoryById(int id, UpdatedInventoryForm form) {
        Inventory inventory = form.update(id, inventoryRepository);
        return ResponseEntity.ok(new InventoryDto(inventory));
    }

    @Override
    public ResponseEntity<Inventory> deleteSpecificInventoryById(int id) {
        inventoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
