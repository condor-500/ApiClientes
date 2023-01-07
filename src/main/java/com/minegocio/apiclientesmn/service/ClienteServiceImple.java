package com.minegocio.apiclientesmn.service;

import com.minegocio.apiclientesmn.dto.ClienteDTO;
import com.minegocio.apiclientesmn.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.*;
import java.util.Optional;

@Service
public class ClienteServiceImple implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteDTO> listarClientes() {
        return (List<ClienteDTO>) clienteRepository.findAll();
    }

    @Override
    public Optional<ClienteDTO> buscarId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Optional<ClienteDTO> buscarCedula(String identification) {
        return clienteRepository.findByIdentificationNumberContaining(identification);
    }

    @Override
    public List<ClienteDTO> buscarNombre(String nombre) {

        return clienteRepository.findByNamesContaining(nombre);
    }

    @Override
    @Transient
    public ClienteDTO guardar(ClienteDTO clienteDTO) {
        clienteDTO.setDateUpdate(new Date());
        return clienteRepository.save(clienteDTO);
    }

    @Override
    public void eliminar(Long id) {
     clienteRepository.deleteById(id);
    }
}
