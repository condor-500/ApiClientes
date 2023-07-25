package com.minegocio.apiclientesmn.controllers;


import com.minegocio.apiclientesmn.dto.ClienteDTO;
import com.minegocio.apiclientesmn.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.minegocio.apiclientesmn.vi.SearchClientVI;

import java.util.*;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClientController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar(){
        List<ClienteDTO> clienteDTOList = clienteService.listarClientes();
        return new ResponseEntity<>(clienteDTOList , HttpStatus.OK);
    }


    @GetMapping( value = "/buscar",
            headers = "Accept=application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getCliente(@RequestBody SearchClientVI searchClient ){
            return new ResponseEntity<>(clienteService.searchClients(searchClient.parametro) , HttpStatus.OK );
    }

    @PostMapping("/")
    public ResponseEntity<?> crearCliente(@RequestBody ClienteDTO clienteDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.guardar(clienteDTO));
    }


    @PostMapping("/update")
    public ResponseEntity<?> editar(@RequestBody ClienteDTO clienteDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.guardar(clienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Map<String, String> resultado = new HashMap<>();
        resultado.put("fecha",String.valueOf(new Date()));
           boolean valor =  clienteService.eliminar(id);
            return ResponseEntity.badRequest().body(valor);
    }
}
