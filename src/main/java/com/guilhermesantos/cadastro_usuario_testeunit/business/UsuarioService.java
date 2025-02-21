package com.guilhermesantos.cadastro_usuario_testeunit.business;


import com.guilhermesantos.cadastro_usuario_testeunit.api.converter.UsuarioConverter;
import com.guilhermesantos.cadastro_usuario_testeunit.api.converter.UsuarioMapper;
import com.guilhermesantos.cadastro_usuario_testeunit.api.converter.UsuarioUpdateMapper;
import com.guilhermesantos.cadastro_usuario_testeunit.api.request.UsuarioRequestDTO;
import com.guilhermesantos.cadastro_usuario_testeunit.api.response.UsuarioResponseDTO;
import com.guilhermesantos.cadastro_usuario_testeunit.infrastructure.entity.UsuarioEntity;
import com.guilhermesantos.cadastro_usuario_testeunit.infrastructure.exception.BusinessException;
import com.guilhermesantos.cadastro_usuario_testeunit.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import static org.springframework.util.Assert.notNull;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final UsuarioUpdateMapper usuarioUpdateMapper;
    private final UsuarioMapper usuarioMapper;


    public UsuarioEntity salvaUsuario(UsuarioEntity usuarioEntity) {
        return usuarioRepository.saveAndFlush(usuarioEntity);
    }

    public UsuarioResponseDTO gravarUsuarios(UsuarioRequestDTO usuarioRequestDTO) {
        try {
            notNull(usuarioRequestDTO, "Os dados do usuário são obrigatórios");
            UsuarioEntity usuarioEntity = salvaUsuario(usuarioConverter.paraUsuarioEntity(usuarioRequestDTO));
            return usuarioMapper.paraUsuarioResponseDTO(usuarioEntity);
        } catch (Exception e) {
            throw new BusinessException("Erro ao gravar dados de usuário");
        }
    }

    public UsuarioResponseDTO atualizaCadastro(UsuarioRequestDTO usuarioRequestDTO){
        try {
            notNull(usuarioRequestDTO, "Os dados do usuário são obrigatórios");
            UsuarioEntity usuario = usuarioRepository.findByEmail(usuarioRequestDTO.getEmail());
            UsuarioEntity entity = usuarioUpdateMapper.updateUsuarioFromDTO(usuarioRequestDTO, usuario);
            return usuarioMapper.paraUsuarioResponseDTO(salvaUsuario(entity));
        } catch (Exception e) {
            throw new BusinessException("Erro ao gravar dados de usuário", e);
        }
    }

    public UsuarioResponseDTO buscaDadosUsuario(String email) {
            UsuarioEntity entity = usuarioRepository.findByEmail(email);

            return entity != null ? usuarioMapper.paraUsuarioResponseDTO(usuarioRepository) : null;
    }

    public void deletaDadosUsuario(String email) {

        usuarioRepository.deleteByEmail(email);

    }


}
