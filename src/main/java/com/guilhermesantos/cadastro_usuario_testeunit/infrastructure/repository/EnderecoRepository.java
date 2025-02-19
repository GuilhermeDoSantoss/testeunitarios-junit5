package com.guilhermesantos.cadastro_usuario_testeunit.infrastructure.repository;


import com.guilhermesantos.cadastro_usuario_testeunit.infrastructure.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
}
