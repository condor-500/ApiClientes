package com.minegocio.apiclientesmn.service;

import com.minegocio.apiclientesmn.dto.ClienteDTO;

import java.util.*;
import java.util.Optional;

public interface ClienteService {

    List<ClienteDTO> listarClientes();
    Optional<ClienteDTO> buscarId(Long id);
    Optional<ClienteDTO> buscarCedula(String identification);
    List<ClienteDTO> buscarNombre(String nombre);
    ClienteDTO guardar(ClienteDTO clienteDTO);
    void eliminar(Long id);


}
