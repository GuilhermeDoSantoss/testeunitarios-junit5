package com.guilhermesantos.cadastro_usuario_testeunit.api.request;

public class EnderecoRequestDTOFixture {

    public static EnderecoRequestDTO build(String rua, Long numero, String bairro,
                                           String complemento, String cidade, String cep){
        return new EnderecoRequestDTO(rua, numero, bairro, complemento, cidade, cep);
    }
}
