package com.shakalinux.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shakalinux.model.Cidadao;
import com.shakalinux.repositorio.CidadaoDAO;

public class CidadaoDao implements CidadaoDAO {

    @Override
public void salvar(Cidadao cidadao) {
    String sql = "INSERT INTO cidadao (nome, documento, status, telefone, email, cep, logradouro, bairro, localidade, uf) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection connection = BaseConexao.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, cidadao.getNome());
        statement.setString(2, cidadao.getDocumento());
        statement.setBoolean(3, cidadao.isStatus());
        statement.setString(4, cidadao.getTelefone());
        statement.setString(5, cidadao.getEmail());
        statement.setString(6, cidadao.getCep());
        statement.setString(7, cidadao.getLogradouro());
        statement.setString(8, cidadao.getBairro());
        statement.setString(9, cidadao.getLocalidade());
        statement.setString(10, cidadao.getUf());

        statement.executeUpdate();
        System.out.println("Usuário cadastro com sucesso!");

    } catch (SQLException e) {
        if (e.getErrorCode() == 1062) { 
            System.out.println("Erro: Já existe um cidadão cadastrado com o documento " + cidadao.getDocumento() + ".");
        } else {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar cidadão: " + e.getMessage());
        }
    }
}


    @Override
    public Cidadao buscarPorId(int cidadaoId) {
        String sql = "SELECT * FROM cidadao WHERE cidadaoId = ?";
        Cidadao cidadao = null;

        try (Connection connection = BaseConexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, cidadaoId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cidadao = new Cidadao();
                cidadao.setCidadaoId(resultSet.getInt("cidadaoId"));
                cidadao.setNome(resultSet.getString("nome"));
                cidadao.setDocumento(resultSet.getString("documento"));
                cidadao.setStatus(resultSet.getBoolean("status"));
                cidadao.setTelefone(resultSet.getString("telefone"));
                cidadao.setEmail(resultSet.getString("email"));
                cidadao.setCep(resultSet.getString("cep"));
                cidadao.setLogradouro(resultSet.getString("logradouro"));
                cidadao.setBairro(resultSet.getString("bairro"));
                cidadao.setLocalidade(resultSet.getString("localidade"));
                cidadao.setUf(resultSet.getString("uf"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar cidadão: " + e.getMessage());
        }
        return cidadao;
    }

    @Override
    public List<Cidadao> listarTodos() {
        String sql = "SELECT * FROM cidadao";
        List<Cidadao> cidadaos = new ArrayList<>();

        try (Connection connection = BaseConexao.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Cidadao cidadao = new Cidadao();
                cidadao.setCidadaoId(resultSet.getInt("cidadaoId"));
                cidadao.setNome(resultSet.getString("nome"));
                cidadao.setDocumento(resultSet.getString("documento"));
                cidadao.setStatus(resultSet.getBoolean("status"));
                cidadao.setTelefone(resultSet.getString("telefone"));
                cidadao.setEmail(resultSet.getString("email"));
                cidadao.setCep(resultSet.getString("cep"));
                cidadao.setLogradouro(resultSet.getString("logradouro"));
                cidadao.setBairro(resultSet.getString("bairro"));
                cidadao.setLocalidade(resultSet.getString("localidade"));
                cidadao.setUf(resultSet.getString("uf"));
                
                cidadaos.add(cidadao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar cidadãos: " + e.getMessage());
        }
        return cidadaos;
    }

    @Override
    public void atualizar(Cidadao cidadao) {
        String sql = "UPDATE cidadao SET nome = ?, documento = ?, status = ?, telefone = ?, email = ?, cep = ?, logradouro = ?, bairro = ?, localidade = ?, uf = ? WHERE cidadaoId = ?";

        try (Connection connection = BaseConexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cidadao.getNome());
            statement.setString(2, cidadao.getDocumento());
            statement.setBoolean(3, cidadao.isStatus());
            statement.setString(4, cidadao.getTelefone());
            statement.setString(5, cidadao.getEmail());
            statement.setString(6, cidadao.getCep());
            statement.setString(7, cidadao.getLogradouro());
            statement.setString(8, cidadao.getBairro());
            statement.setString(9, cidadao.getLocalidade());
            statement.setString(10, cidadao.getUf());
            statement.setInt(11, cidadao.getCidadaoId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar cidadão: " + e.getMessage());
        }
    }

    @Override
    public void deletar(int cidadaoId) {
        String sql = "DELETE FROM cidadao WHERE cidadaoId = ?";

        try (Connection connection = BaseConexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, cidadaoId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar cidadão: " + e.getMessage());
        }
    }
    
}
