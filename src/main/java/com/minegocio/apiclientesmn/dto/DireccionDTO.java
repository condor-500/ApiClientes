package com.minegocio.apiclientesmn.dto;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "direcciones")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DireccionDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDireccion;
    private String mainAddress;
    private String provincia;
    private String ciudad;
    private String tipoDireccion;

}
