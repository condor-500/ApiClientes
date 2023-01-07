package com.minegocio.apiclientesmn.repositories;

import com.minegocio.apiclientesmn.dto.DireccionDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

public interface DireccionRepository extends CrudRepository<DireccionDTO, Long> {

    @Query(value = "select * from clientes c inner join clientes_directiondto cd on cd.clientedto_id_cliente = c.id_cliente\n" +
            "      inner join direcciones d on cd.directiondto_id_direccion=d.id_direccion\n" +
            "where c.identification_number  = ?1 ", nativeQuery = true )
    List<DireccionDTO> direccionesCliente(@PathVariable String cedula);

    @Modifying
    @Query(value = "insert into clientes_directiondto (clientedto_id_cliente, directiondto_id_direccion) values (:idCliente, :id_direccion)", nativeQuery = true)
    @Transactional
    void guardarDireccion(@Param("idCliente") Long idCliente , @Param("id_direccion") Long idDireccion );
}
