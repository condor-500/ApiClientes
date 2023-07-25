package com.minegocio.apiclientesmn.repositories;

import com.minegocio.apiclientesmn.dto.ClienteDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.*;


public interface ClienteRepository extends CrudRepository<ClienteDTO,Long> {

    @Query("SELECT c FROM ClienteDTO c WHERE c.identificationNumber=?1")
    List<ClienteDTO> findByIdentificationNumberContaining(String identification);


    List<ClienteDTO> findByNamesContaining(String nombre);




}
