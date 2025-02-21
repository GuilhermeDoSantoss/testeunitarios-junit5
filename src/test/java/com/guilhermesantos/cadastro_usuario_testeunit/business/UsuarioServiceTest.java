package com.guilhermesantos.cadastro_usuario_testeunit.business;

import com.guilhermesantos.cadastro_usuario_testeunit.api.converter.UsuarioConverter;
import com.guilhermesantos.cadastro_usuario_testeunit.api.converter.UsuarioMapper;
import com.guilhermesantos.cadastro_usuario_testeunit.api.converter.UsuarioUpdateMapper;
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
import com.guilhermesantos.cadastro_usuario_testeunit.infrastructure.exception.BusinessException;
import com.guilhermesantos.cadastro_usuario_testeunit.infrastructure.repository.UsuarioRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static sun.nio.cs.Surrogate.is;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks

    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    UsuarioUpdateMapper usuarioUpdateMapper;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private UsuarioConverter usuarioConverter;

    UsuarioEntity usuarioEntity;

    EnderecoEntity enderecoEntity;

    UsuarioRequestDTO usuarioRequestDTO;

    EnderecoRequestDTO enderecoRequestDTO;

    LocalDateTime dataHora;

    UsuarioResponseDTO usuarioResponseDTO;

    EnderecoResponseDTO enderecoResponseDTO;

    @BeforeEach
    public void setup(){

        dataHora = LocalDateTime.of(2023, 10, 05, 14, 12, 15);

        enderecoEntity = EnderecoEntity.builder().rua("Rua Teste").bairro("Bairro Teste")
                .cep("1234567890").cidade("Cidade Teste").numero(1258L).complemento("Casa 1").build();

        usuarioEntity = UsuarioEntity.builder().nome("Usuario").documento("12345678")
                .email("usuario@gmail.com").dataCadastro(dataHora).endereco(enderecoEntity).build();

        enderecoRequestDTO = EnderecoRequestDTOFixture.build("Rua Teste", 1258L,
                "Bairro Teste", "Casa 1", "Cidade Teste", "1234567890");

        usuarioRequestDTO = UsuarioRequestDTOFixture.build("Usuario", "usuario@gmail.com",
                "12345678", enderecoRequestDTO);

        enderecoResponseDTO = EnderecoResponseDTOFixture.build("Rua Teste", 1258L,
                "Bairro Teste", "Casa 1", "Cidade Teste", "1234567890");

        usuarioResponseDTO = UsuarioResponseDTOFixture.build(1452L,"Usuario", "usuario@gmail.com",
                "12345678", enderecoResponseDTO);

    }


    @Test
    public void testLoginSucess(){


        //Dado que o usúario está registrado no sistema GIVEN

        // Quando o usuário tenta fazer login WHEN

        //Então o sistema deve conceder acesso ao usuário THEN

    }

    @Test
    void deveSalvarUsuarioComSucessos(){
        when(usuarioRepository.saveAndFlush(usuarioEntity)).thenReturn(usuarioEntity);

       UsuarioEntity entity = usuarioService.salvaUsuario(usuarioEntity);

       assertEquals(entity, usuarioEntity);
       verify(usuarioRepository).saveAndFlush(usuarioEntity);
       verifyNoMoreInteractions(usuarioRepository);
    }

    @Test
    void deveGravarUsuariosComSucesso(){

        when(usuarioConverter.paraUsuarioEntity(usuarioRequestDTO)).thenReturn(usuarioEntity);
        when(usuarioRepository.saveAndFlush(usuarioEntity)).thenReturn(usuarioEntity);
        when(usuarioMapper.paraUsuarioResponseDTO(usuarioEntity)).thenReturn(usuarioResponseDTO);

        UsuarioResponseDTO dto = usuarioService.gravarUsuarios(usuarioRequestDTO);

        assertEquals(dto, usuarioResponseDTO);
        verify(usuarioConverter).paraUsuarioEntity(usuarioRequestDTO);
        verify(usuarioRepository).saveAndFlush(usuarioEntity);
        verify(usuarioMapper).paraUsuarioResponseDTO(usuarioEntity);
        verifyNoMoreInteractions(usuarioRepository, usuarioConverter, usuarioMapper);
    }
    
    @Test
    void naoDeveSalvarUsuariosCasoUsuarioRequestDTONull(){
        BusinessException e = assertThrows(BusinessException.class,
                () -> usuarioService.gravarUsuarios(null));

        assertThat(e, notNullValue());
        assertThat(e.getMessage(), CoreMatchers.is("Erro ao gravar dados de usuário"));
        assertThat(e.getCause(), notNullValue());
        assertThat(e.getCause().getMessage(), CoreMatchers.is("Os dados do usuário são obrigatórios"));
        verifyNoMoreInteractions(usuarioMapper, usuarioConverter, usuarioRepository);
    }

    @Test
    void deveGerarExcecaoCasoOcorraErroAoGravarUsuario(){
        when(usuarioConverter.paraUsuarioEntity(usuarioRequestDTO)).thenReturn(usuarioEntity);
        when(usuarioRepository.saveAndFlush(usuarioEntity)).thenThrow(new RuntimeException("Falha ao gravar dados do usuário"));

        BusinessException e = assertThrows(BusinessException.class, () -> usuarioService.gravarUsuarios(usuarioRequestDTO));

        assertThat(e, notNullValue());
        assertThat(e.getMessage(), CoreMatchers.is("Erro ao gravas dados de usuário"));
        assertThat(e.getCause().getClass(), CoreMatchers.is(RuntimeException.class));
        assertThat(e.getCause().getMessage(), CoreMatchers.is("Falha ao gravar dados de usuário"));
        verify(usuarioConverter).paraUsuarioEntity(usuarioRequestDTO);
        verify(usuarioRepository).saveAndFlush(usuarioEntity);
        verifyNoInteractions(usuarioMapper);
        verifyNoMoreInteractions(usuarioRepository, usuarioConverter);
    }
}
