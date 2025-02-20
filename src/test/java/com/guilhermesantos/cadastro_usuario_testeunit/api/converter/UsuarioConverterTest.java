package com.guilhermesantos.cadastro_usuario_testeunit.api.converter;

import com.guilhermesantos.cadastro_usuario_testeunit.api.request.EnderecoRequestDTO;
import com.guilhermesantos.cadastro_usuario_testeunit.api.request.EnderecoRequestDTOFixture;
import com.guilhermesantos.cadastro_usuario_testeunit.api.request.UsuarioRequestDTO;
import com.guilhermesantos.cadastro_usuario_testeunit.api.request.UsuarioRequestDTOFixture;
import com.guilhermesantos.cadastro_usuario_testeunit.infrastructure.entity.EnderecoEntity;
import com.guilhermesantos.cadastro_usuario_testeunit.infrastructure.entity.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UsuarioConverterTest {

    @InjectMocks
    UsuarioConverter usuarioConverter;

    UsuarioEntity usuarioEntity;

    EnderecoEntity enderecoEntity;

    UsuarioRequestDTO usuarioRequestDTO;

    EnderecoRequestDTO enderecoRequestDTO;

    @BeforeEach
    public void setup(){

        enderecoEntity = EnderecoEntity.builder().rua("Rua Teste").bairro("Bairro Teste")
                .cep("1234567890").cidade("Cidade Teste").numero(1258L).complemento("Casa 1").build();

        usuarioEntity = UsuarioEntity.builder().nome("Usuario").documento("12345678")
                .email("usuario@gmail.com").dataCadastro(LocalDateTime.now()).endereco(enderecoEntity).build();

        enderecoRequestDTO = EnderecoRequestDTOFixture.build("Rua Teste", 1258L,
                "Bairro Teste", "Casa 1", "Cidade Teste", "1234567890");

        usuarioRequestDTO = UsuarioRequestDTOFixture.build("Usuario", "usuario@gmail.com",
                "12345678", enderecoRequestDTO);
    }

    @Test
    void deveConverterParaUsuarioEntity(){

       UsuarioEntity entity = usuarioConverter.paraUsuarioEntity(usuarioRequestDTO);

       assertEquals(usuarioEntity, entity);
    }
}
