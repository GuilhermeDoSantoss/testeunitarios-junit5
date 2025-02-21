package com.guilhermesantos.cadastro_usuario_testeunit.api.converter;


import com.guilhermesantos.cadastro_usuario_testeunit.api.request.UsuarioRequestDTO;
import com.guilhermesantos.cadastro_usuario_testeunit.infrastructure.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UsuarioUpdateMapper {

    UsuarioUpdateMapper INSTANCE = Mappers.getMapper(UsuarioUpdateMapper.class);

    UsuarioEntity updateUsuarioFromDTO(UsuarioRequestDTO usuarioDTO, @MappingTarget UsuarioEntity usuarioEntity);
}