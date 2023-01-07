package com.minegocio.apiclientesmn.controllers;


import com.minegocio.apiclientesmn.dto.ClienteDTO;
import com.minegocio.apiclientesmn.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar(){
        return ResponseEntity.ok(clienteService.listarClientes());
    }


    @GetMapping("/buscar")
    public ResponseEntity<?> getNobre(@RequestParam("parametro")  String parametro , @RequestParam("valor")  String valor){
        if(parametro.equals("1")){
            return ResponseEntity.ok(clienteService.buscarNombre(valor.toUpperCase()));
        }else {
            return ResponseEntity.ok(clienteService.buscarCedula(valor.toUpperCase()));
        }
    }


    @PostMapping("/")
    public ResponseEntity<?> crearCliente(@RequestBody ClienteDTO clienteDTO){

       if(! clienteDTO.getIdentificationNumber().isEmpty() && clienteService.buscarCedula(clienteDTO.getIdentificationNumber()).isPresent()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje","Ya existe cliente"));

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.guardar(clienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<ClienteDTO> clienteDTO = clienteService.buscarId(id);
        if(clienteDTO.isPresent()){
            clienteService.eliminar(id);
            return ResponseEntity.notFound().build();
        }else{
           return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje","No existe Cliente"));
        }


    }
}
