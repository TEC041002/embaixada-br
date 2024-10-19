package com.shakalinux.repositorio;

import com.shakalinux.model.Cidadao;

import java.util.List;

public interface CidadaoDAO {
    void salvar(Cidadao cidadao);
    Cidadao buscarPorId(int cidadaoId);
    List<Cidadao> listarTodos();
    void atualizar(Cidadao cidadao);
    void deletar(int cidadaoId);
}
