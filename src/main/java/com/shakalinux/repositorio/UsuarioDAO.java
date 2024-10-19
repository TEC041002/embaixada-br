package com.shakalinux.repositorio;

import com.shakalinux.model.Usuario;

import java.util.List;

public interface UsuarioDAO {
    void adicionarUsuario(Usuario usuario);
    Usuario buscarUsuarioPorId(int usuarioId);
    Usuario buscarUsuarioPorEmail(String email);
    List<Usuario> listarUsuarios();
    void atualizarUsuario(Usuario usuario);
    void deletarUsuario(int usuarioId);
    Usuario buscarPorNomeESenha(String nome, String senha);
}
