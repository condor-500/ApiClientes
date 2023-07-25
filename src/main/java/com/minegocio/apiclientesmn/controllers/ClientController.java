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
    public ResponseEntity<?> getCliente(@RequestParam("parametro")  String parametro , @RequestParam("valor")  String valor){
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

    @PostMapping("/update")
    public ResponseEntity<?> editar(@RequestBody ClienteDTO clienteDTO){
        Optional<ClienteDTO> cliente = clienteService.buscarId(clienteDTO.getIdCliente());
        Map<String, String> resultado = new HashMap<>();
        resultado.put("fecha",String.valueOf(new Date()));
        if(cliente.isPresent()){
            ClienteDTO clienteEditado = cliente.get();
            clienteEditado.setEmail(clienteDTO.getEmail());
            clienteEditado.setNames(clienteDTO.getNames());
            clienteEditado.setCellphone(clienteDTO.getCellphone());
            clienteEditado.setIdentificationType(clienteDTO.getIdentificationType());
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.guardar(clienteEditado));
        }else {
            resultado.put("mensaje","No existe cliente para modificar");
            return ResponseEntity.badRequest().body(resultado);
        }



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Map<String, String> resultado = new HashMap<>();
        resultado.put("fecha",String.valueOf(new Date()));
           boolean valor =  clienteService.eliminar(id);
            return ResponseEntity.badRequest().body(valor);
    }
}
