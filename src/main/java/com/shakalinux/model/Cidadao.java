package com.shakalinux.model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class Cidadao {

    private int cidadaoId;
    private String nome;
    private String documento;
    private boolean status;
    private String telefone;
    private String email;
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    public int getCidadaoId() {
        return this.cidadaoId;
    }

    public void setCidadaoId(int cidadaoId) {
        this.cidadaoId = cidadaoId;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public boolean isStatus() {
        return this.status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return this.uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }


    public Cidadao() {}

    public Cidadao(int cidadaoId, String nome, String documento, boolean status, String telefone, String email, 
                   String cep, String logradouro, String bairro, String localidade, String uf) {
        this.cidadaoId = cidadaoId;
        this.nome = nome;
        this.documento = documento;
        this.status = status;
        this.telefone = telefone;
        this.email = email;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

     public void buscarCep(String cep) throws IOException, InterruptedException {
        String newCep = cep.replace("-", "");
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://viacep.com.br/ws/" + newCep + "/json/")).build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            Cidadao endereco = objectMapper.readValue(response.body(), Cidadao.class);
            this.cep = endereco.cep;
            this.logradouro = endereco.logradouro;
            this.bairro = endereco.bairro;
            this.localidade = endereco.localidade;
            this.uf = endereco.uf;
        } else {
            throw new IOException("Falha ao buscar CEP: " + response.statusCode());
        }
    }
  
}
