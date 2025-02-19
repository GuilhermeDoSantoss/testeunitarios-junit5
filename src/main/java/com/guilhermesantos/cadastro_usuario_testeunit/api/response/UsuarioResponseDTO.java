package com.guilhermesantos.cadastro_usuario_testeunit.api.response;

public record UsuarioResponseDTO(Long id,

                                 String nome,

                                 String email,

                                 String documento,

                                 EnderecoResponseDTO endereco) {


}
