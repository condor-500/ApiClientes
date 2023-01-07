package com.minegocio.apiclientesmn.service;

import com.minegocio.apiclientesmn.dto.DireccionDTO;


import java.util.List;
import java.util.Optional;

public interface DireccionService {
    List<DireccionDTO> listarDireccion();
    Optional<DireccionDTO> buscarId(Long id);
    DireccionDTO guardar(DireccionDTO direccionDTO);
    void guardarDireciones(DireccionDTO direccionDTO , Long id);
    List<DireccionDTO> direccionesCliente( String cedula);
    void eliminar(Long id);
}
