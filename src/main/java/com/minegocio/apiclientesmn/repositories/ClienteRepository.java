package com.minegocio.apiclientesmn.repositories;

import com.minegocio.apiclientesmn.dto.ClienteDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.*;
import java.util.Optional;

public interface ClienteRepository extends CrudRepository<ClienteDTO,Long> {

    @Query("SELECT c FROM ClienteDTO c WHERE c.identificationType=?1")
    Optional<ClienteDTO> findByIdentificationNumberContaining(String identification);


    List<ClienteDTO> findByNamesContaining(String nombre);




}
