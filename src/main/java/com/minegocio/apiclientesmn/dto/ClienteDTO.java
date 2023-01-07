package com.minegocio.apiclientesmn.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@EqualsAndHashCode
public class ClienteDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(name = "IDENTIFICACION")
    private Long identificationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "IDENTIFICACION", referencedColumnName = "CODE_IDENTIFICACION", insertable = false, updatable = false) })
    private TipoIdentificacionDTO tipoIdentificacion;

    @Column(name = "identificationType",length = 13)
    private String identificationNumber;

    private String names;

    private String email;

    private String cellphone;

    private Date   dateUpdate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DireccionDTO> directionDTO = new ArrayList<>();

}
