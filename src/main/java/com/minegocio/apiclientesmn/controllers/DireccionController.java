package com.minegocio.apiclientesmn.controllers;

import com.minegocio.apiclientesmn.dto.ClienteDTO;
import com.minegocio.apiclientesmn.dto.DireccionDTO;
import com.minegocio.apiclientesmn.service.ClienteService;
import com.minegocio.apiclientesmn.service.DireccionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/direccion")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @Autowired
    private ClienteService clienteService;

    Logger logger = LoggerFactory.getLogger(DireccionController.class);

    @GetMapping
    public ResponseEntity<?> lista(){
        return ResponseEntity.ok(direccionService.listarDireccion());
    };

    @GetMapping("/buscar")
    public ResponseEntity<?> listaDireccion(@RequestParam("cedula")  String cedula ){
        return ResponseEntity.ok(direccionService.direccionesCliente(cedula));
    }

    @PostMapping("/{cedula}")
    public ResponseEntity<?> agregarDireccion(@RequestBody DireccionDTO direccionDTO, @PathVariable String cedula ){
        Optional<ClienteDTO> cliente = clienteService.buscarCedula(cedula);
        logger.info(String.valueOf(direccionDTO) +" - "+ cliente.get().getIdCliente() );
        direccionService.guardarDireciones(direccionDTO, cliente.get().getIdCliente()) ;
        return ResponseEntity.ok().build();
    }

}
