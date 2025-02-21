package com.guilhermesantos.cadastro_usuario_testeunit.api.converter;

import com.guilhermesantos.cadastro_usuario_testeunit.api.request.EnderecoRequestDTO;
import com.guilhermesantos.cadastro_usuario_testeunit.api.request.EnderecoRequestDTOFixture;
import com.guilhermesantos.cadastro_usuario_testeunit.api.request.UsuarioRequestDTO;
import com.guilhermesantos.cadastro_usuario_testeunit.api.request.UsuarioRequestDTOFixture;
import com.guilhermesantos.cadastro_usuario_testeunit.api.response.EnderecoResponseDTO;
import com.guilhermesantos.cadastro_usuario_testeunit.api.response.EnderecoResponseDTOFixture;
import com.guilhermesantos.cadastro_usuario_testeunit.api.response.UsuarioResponseDTO;
import com.guilhermesantos.cadastro_usuario_testeunit.api.response.UsuarioResponseDTOFixture;
import com.guilhermesantos.cadastro_usuario_testeunit.infrastructure.entity.EnderecoEntity;
import com.guilhermesantos.cadastro_usuario_testeunit.infrastructure.entity.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UsuarioUpdateMapperTest {

    UsuarioUpdateMapper usuarioUpdateMapper;

    UsuarioEntity usuarioEntityEsperado;

    UsuarioEntity usuarioEntity;

    EnderecoEntity enderecoEntity;

    UsuarioRequestDTO usuarioRequestDTO;

    EnderecoRequestDTO enderecoRequestDTO;

    LocalDateTime dataHora;

    @BeforeEach
    public void setup(){

        usuarioUpdateMapper = Mappers.getMapper(UsuarioUpdateMapper.class);

        dataHora = LocalDateTime.of(2023, 10, 05, 14, 12, 15);

        enderecoEntity = EnderecoEntity.builder().rua("Rua Teste").bairro("Bairro Teste")
                .cep("1234567890").cidade("Cidade Teste").numero(1258L).complemento("Casa 1").build();

        usuarioEntity = UsuarioEntity.builder().id(124L).nome("Usuario").documento("12345678")
                .email("usuario@gmail.com").dataCadastro(dataHora).endereco(enderecoEntity).build();

        enderecoRequestDTO = EnderecoRequestDTOFixture.build("Rua Teste", 1258L,
                "Bairro Teste", "Casa 1", "Cidade Teste", "1234567890");

        usuarioRequestDTO = UsuarioRequestDTOFixture.build("Usuario Teste", null,
                "123456768", enderecoRequestDTO);

        usuarioEntityEsperado = UsuarioEntity.builder().id(124L).nome("Usuario Teste").email("usuario@gmail.com")
                .documento("123456768").dataCadastro(dataHora).endereco(enderecoEntity).build();

    }

    @Test
    void deveConverterParaUsuarioEntity(){

       UsuarioEntity entity = usuarioUpdateMapper.updateUsuarioFromDTO(usuarioRequestDTO, usuarioEntity);

       assertEquals(usuarioEntityEsperado, entity);
    }
}
