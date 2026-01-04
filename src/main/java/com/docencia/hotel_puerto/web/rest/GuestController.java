package com.docencia.hotel_puerto.web.rest;

import com.docencia.hotel_puerto.domain.model.Guest;
import com.docencia.hotel_puerto.service.api.GuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/guests")
@Tag(name="Huespedes", description = "Operaciones REST de huespedes")
public class GuestController {
    private GuestService guestService;

    @Autowired
    public void setGuestService(GuestService guestService) {
        this.guestService = guestService;
    }

    @Operation(summary = "Obtener todos los huespedes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de huespedes obtenida"),
            @ApiResponse(responseCode = "500", description = "Error inesperado en el servidor o la base de datos")
    })
    @GetMapping
    public ResponseEntity<List<Guest>> findAll() {
        return ResponseEntity.ok(guestService.findAll());
    }

    @Operation(summary = "Obtener huesped por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Huesped encontrado"),
            @ApiResponse(responseCode = "404", description = "El id proporcionado no existe"),
            @ApiResponse(responseCode = "400", description = "El id proporcionado es invalido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Guest> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(guestService.findById(id));
    }

    @Operation(summary = "Crear un huesped")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Nuevo huesped creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos"),
    })
    @PostMapping
    public ResponseEntity<Guest> create(@Valid @RequestBody Guest guest) {
        return ResponseEntity.ok(guestService.save(guest));
    }

    @Operation(summary = "Crear un huesped")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Huesped actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Guest> update(@Valid @RequestBody Guest guest, @PathVariable("id") Long id) {
        return ResponseEntity.ok(guestService.save(guest));
    }

    @Operation(summary = "Eliminar un huesped")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Huesped eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "El id a eliminar no existe"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(guestService.delete(id));
    }
}


