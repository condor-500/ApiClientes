package com.minegocio.apiclientesmn.imple;


import com.minegocio.apiclientesmn.dto.DireccionDTO;
import com.minegocio.apiclientesmn.repositories.DireccionRepository;
import com.minegocio.apiclientesmn.service.DireccionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionServiceImple implements DireccionService {

   @Autowired
   private DireccionRepository direccionRepository;

    Logger logger = LoggerFactory.getLogger(DireccionServiceImple.class);

    @Override
    public List<DireccionDTO> listarDireccion() {
        return (List<DireccionDTO>) direccionRepository.findAll();
    }

    @Override
    public Optional<DireccionDTO> buscarId(Long id) {
        return direccionRepository.findById(id);
    }

    @Override
    public DireccionDTO guardar(DireccionDTO direccionDTO) {
        return direccionRepository.save(direccionDTO);
    }

    @Override
    public void guardarDireciones(DireccionDTO direccionDTO, Long id) {

        direccionRepository.save(direccionDTO);
        logger.info(id + "");
        direccionRepository.guardarDireccion( id  , 7L );

    }

    @Override
    public List<DireccionDTO> direccionesCliente(String cedula) {
        return direccionRepository.direccionesCliente(cedula);
    }

    @Override
    public void eliminar(Long id) {
        direccionRepository.deleteById(id);

    }
}
