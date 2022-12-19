package br.com.unifor.coffeecontrol.services;

import br.com.unifor.coffeecontrol.dtos.InventoryDto;
import br.com.unifor.coffeecontrol.forms.InventoryForm;
import br.com.unifor.coffeecontrol.forms.UpdatedInventoryForm;
import br.com.unifor.coffeecontrol.modelos.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public interface InventoryService {
    Page<InventoryDto> listInventories(Pageable paginacao);

    ResponseEntity<InventoryDto> signUpInventory(InventoryForm inventoryForm, UriComponentsBuilder uriBuilder);

    ResponseEntity<InventoryDto> updateSpecificInventoryById(int id, UpdatedInventoryForm form);

    ResponseEntity<Inventory> deleteSpecificInventoryById(int id);
}
