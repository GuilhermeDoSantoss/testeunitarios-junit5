package com.guilhermesantos.cadastro_usuario_testeunit.api.response;

public class UsuarioResponseDTOFixture {

    public static UsuarioResponseDTO build(Long id, String nome, String email,
                                           String documento, EnderecoResponseDTO endereco){
        return new UsuarioResponseDTO(id, nome, email, documento, endereco);
    }
}
