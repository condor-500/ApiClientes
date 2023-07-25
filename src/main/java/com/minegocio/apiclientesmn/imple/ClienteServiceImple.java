package com.minegocio.apiclientesmn.imple;

import com.minegocio.apiclientesmn.dto.ClienteDTO;
import com.minegocio.apiclientesmn.excepciones.ExceptionMinegocio;
import com.minegocio.apiclientesmn.repositories.ClienteRepository;
import com.minegocio.apiclientesmn.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.*;
import java.util.Optional;

@Service
public class ClienteServiceImple implements ClienteService {

  Logger logger = LoggerFactory.getLogger(ClienteServiceImple.class);

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
    public List<ClienteDTO> searchClients(String valor) {
        List<ClienteDTO> list = new ArrayList<>();
        logger.info(String.valueOf(clienteRepository.findByIdentificationNumberContaining(valor).size()));
        if(clienteRepository.findByIdentificationNumberContaining(valor).size() != 0){
            list = clienteRepository.findByIdentificationNumberContaining(valor);
        }else {
            list =  clienteRepository.findByNamesContaining(valor);
        }
        return list;
    }

    @Override
    public List<ClienteDTO> buscarCedula(String identification) {
        return clienteRepository.findByIdentificationNumberContaining(identification);
    }

    @Override
    public List<ClienteDTO> buscarNombre(String nombre) {
          return clienteRepository.findByNamesContaining(nombre);
    }

    @Override
    @Transient
    public ClienteDTO guardar(ClienteDTO clienteDTO) {
        Optional<ClienteDTO> cliente = clienteRepository.findById(clienteDTO.getIdCliente());
        if(cliente.isPresent()){
            ClienteDTO clienteEditado = cliente.get();
            clienteEditado.setEmail(clienteDTO.getEmail());
            clienteEditado.setNames(clienteDTO.getNames());
            clienteEditado.setCellphone(clienteDTO.getCellphone());
            clienteEditado.setIdentificationType(clienteDTO.getIdentificationType());
            clienteEditado.setDateUpdate(new Date());
            clienteRepository.save(clienteEditado);
        }else{
            throw new ExceptionMinegocio("Error al encontrat ");
        }
        return clienteRepository.save(clienteDTO);
    }

    @Override
    public boolean eliminar(Long id) {
        Optional<ClienteDTO> clienteDTO = clienteRepository.findById(id);
        Map<String, String> resultado = new HashMap<>();
        resultado.put("fecha",String.valueOf(new Date()));
        if(clienteDTO.isPresent()){
            clienteRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
