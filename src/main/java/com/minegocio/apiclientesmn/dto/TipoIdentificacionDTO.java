package com.minegocio.apiclientesmn.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tipo_identificacion")
@Getter
@Setter
public class TipoIdentificacionDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIdentificacion;

    @Column(name = "CODE_IDENTIFICACION")
    private Long codigo;

    @Column(name = "DETALLEIDENTIFICACION")
    private String detalleIdentificacion;

}
