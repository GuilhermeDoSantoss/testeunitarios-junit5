package com.guilhermesantos.cadastro_usuario_testeunit.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "endereco")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rua")
    private String rua;

    @Column(name = "numero")
    private Long numero;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "cep")
    private String cep;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnderecoEntity that = (EnderecoEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(rua, that.rua) &&
                Objects.equals(numero, that.numero) &&
                Objects.equals(bairro, that.bairro) &&
                Objects.equals(complemento, that.complemento) &&
                Objects.equals(cidade, that.cidade) &&
                Objects.equals(cep, that.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rua, numero, bairro, complemento, cidade, cep);
    }
}
