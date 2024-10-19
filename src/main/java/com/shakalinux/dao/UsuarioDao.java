package com.shakalinux.dao;

import com.shakalinux.model.Usuario;
import com.shakalinux.repositorio.UsuarioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao implements UsuarioDAO {

        @Override
        public void adicionarUsuario(Usuario usuario) {
            String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";

            try (Connection conn = BaseConexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getEmail());
                stmt.setString(3, usuario.getSenha());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Usuario buscarUsuarioPorId(int usuarioId) {
            String sql = "SELECT * FROM usuario WHERE usuarioId = ?";
            Usuario usuario = null;

            try (Connection conn = BaseConexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, usuarioId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setUsuarioId(rs.getInt("usuarioId"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return usuario;
        }

        @Override
        public Usuario buscarUsuarioPorEmail(String email) {
            String sql = "SELECT * FROM usuario WHERE email = ?";
            Usuario usuario = null;

            try (Connection conn = BaseConexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setUsuarioId(rs.getInt("usuarioId"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return usuario;
        }

        @Override
        public List<Usuario> listarUsuarios() {
            String sql = "SELECT * FROM usuario";
            List<Usuario> usuarios = new ArrayList<>();

            try (Connection conn = BaseConexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setUsuarioId(rs.getInt("usuarioId"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));


                    usuarios.add(usuario);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return usuarios;
        }

        @Override
        public void atualizarUsuario(Usuario usuario) {
            String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE usuarioId = ?";

            try (Connection conn = BaseConexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getEmail());
                stmt.setString(3, usuario.getSenha());
                stmt.setInt(6, usuario.getUsuarioId());

                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void deletarUsuario(int usuarioId) {
            String sql = "DELETE FROM usuario WHERE usuarioId = ?";

            try (Connection conn = BaseConexao.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, usuarioId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    @Override
    public Usuario buscarPorNomeESenha(String nome, String senha) {
        String sql = "SELECT * FROM usuario WHERE nome = ? AND senha = ?";

        try (Connection connection = BaseConexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setUsuarioId(rs.getInt("usuarioId"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEmail(rs.getString("email"));
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}


