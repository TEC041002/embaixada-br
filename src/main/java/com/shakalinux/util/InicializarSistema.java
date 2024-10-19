package com.shakalinux.util;

import com.shakalinux.dao.UsuarioDao;
import com.shakalinux.dao.CidadaoDao;
import com.shakalinux.model.Cidadao;
import com.shakalinux.model.Usuario;

import java.util.List;
import java.util.Scanner;

public class InicializarSistema {

    private CidadaoDao cidadaoDAO = new CidadaoDao();
    private Scanner scanner = new Scanner(System.in);
    private UsuarioDao usuarioDAO = new UsuarioDao();

    public void init() {
        if (realizarLogin()) {
            while (true) {
                mostrarMenuPrincipal();
                int opcao = obterOpcao();

                switch (opcao) {
                    case 1: cadastrarCidadao(); break;
                    case 2: atualizarCidadao(); break;
                    case 3: buscarCidadaoPorId(); break;
                    case 4: listarCidadaos(); break;
                    case 5: deletarCidadao(); break;
                    case 6: gerenciarUsuarios(); break;
                    case 0: System.exit(0);
                    default: System.out.println("Opção inválida.");
                }
            }
        } else {
            System.out.println("Login falhou. Programa encerrado.");
        }
    }

    private boolean realizarLogin() {
        System.out.println("Bem-vindo! Por favor, faça login.");
        System.out.print("Digite o e-mail: ");
        String email = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        // Tenta buscar o usuário pelo e-mail e senha
        Usuario usuario = usuarioDAO.buscarPorNomeESenha(email, senha);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            System.out.println("Login realizado com sucesso! Bem-vindo, " + usuario.getNome());
            return true;
        } else {
            System.out.println("E-mail ou senha incorretos.");

            // Opção de cadastro
            System.out.print("Deseja cadastrar-se? (s/n): ");
            String opcaoCadastro = scanner.nextLine();

            if (opcaoCadastro.equalsIgnoreCase("s")) {
                cadastrar();
            }

            return false;
        }
    }

    private void cadastrar() {
        Usuario usuario = new Usuario();

        System.out.print("Nome do Usuário: ");
        usuario.setNome(scanner.nextLine());

        System.out.print("Senha: ");
        usuario.setSenha(scanner.nextLine());

        System.out.print("E-mail: ");
        usuario.setEmail(scanner.nextLine());

        usuarioDAO.adicionarUsuario(usuario);
        System.out.println("Usuário cadastrado com sucesso. Você já pode fazer login.");
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1. Cadastrar Cidadão");
        System.out.println("2. Atualizar Cidadão");
        System.out.println("3. Buscar Cidadão por ID");
        System.out.println("4. Listar Todos os Cidadãos");
        System.out.println("5. Deletar Cidadão");
        System.out.println("6. Gerenciar Usuários");
        System.out.println("0. Sair");
    }

    private int obterOpcao() {
        while (true) {
            System.out.print("Opção: ");
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next();
            }
        }
    }

    private void cadastrarCidadao() {
        Cidadao cidadao = new Cidadao();

        System.out.print("Nome: ");
        cidadao.setNome(scanner.nextLine());

        System.out.print("Documento: ");
        cidadao.setDocumento(scanner.nextLine());

        System.out.print("Status (true/false): ");
        cidadao.setStatus(scanner.nextBoolean());
        scanner.nextLine();

        System.out.print("Telefone: ");
        cidadao.setTelefone(scanner.nextLine());

        System.out.print("Email: ");
        cidadao.setEmail(scanner.nextLine());

        System.out.print("CEP: ");
        cidadao.setCep(scanner.nextLine());

        System.out.print("Logradouro: ");
        cidadao.setLogradouro(scanner.nextLine());

        System.out.print("Bairro: ");
        cidadao.setBairro(scanner.nextLine());

        System.out.print("Localidade: ");
        cidadao.setLocalidade(scanner.nextLine());

        System.out.print("UF: ");
        cidadao.setUf(scanner.nextLine());

        cidadaoDAO.salvar(cidadao);
        System.out.println("Cidadão cadastrado com sucesso.");
    }

    private void atualizarCidadao() {
        System.out.print("ID do Cidadão a ser atualizado: ");
        int cidadaoId = obterId();

        Cidadao cidadaoExistente = cidadaoDAO.buscarPorId(cidadaoId);
        if (cidadaoExistente == null) {
            System.out.println("Cidadão não encontrado.");
            return;
        }

        System.out.println("Atualizando dados do cidadão (atual: " + cidadaoExistente.getNome() + ")");
        atualizarCamposCidadao(cidadaoExistente);

        cidadaoDAO.atualizar(cidadaoExistente);
        System.out.println("Cidadão atualizado com sucesso.");
    }

    private void atualizarCamposCidadao(Cidadao cidadao) {
        System.out.print("Nome (atual: " + cidadao.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) {
            cidadao.setNome(nome);
        }

        System.out.print("Documento (atual: " + cidadao.getDocumento() + "): ");
        String documento = scanner.nextLine();
        if (!documento.isEmpty()) {
            cidadao.setDocumento(documento);
        }

        System.out.print("Status (atual: " + cidadao.isStatus() + ") (true/false): ");
        String statusInput = scanner.nextLine();
        if (!statusInput.isEmpty()) {
            cidadao.setStatus(Boolean.parseBoolean(statusInput));
        }

        System.out.print("Telefone (atual: " + cidadao.getTelefone() + "): ");
        String telefone = scanner.nextLine();
        if (!telefone.isEmpty()) {
            cidadao.setTelefone(telefone);
        }
    }

    private void buscarCidadaoPorId() {
        System.out.print("ID do Cidadão: ");
        int cidadaoId = obterId();
        Cidadao cidadao = cidadaoDAO.buscarPorId(cidadaoId);
        if (cidadao != null) {
            System.out.println("Cidadão encontrado: " + cidadao.getNome());
        } else {
            System.out.println("Cidadão não encontrado.");
        }
    }

    private void listarCidadaos() {
        List<Cidadao> cidadaos = cidadaoDAO.listarTodos();
        if (cidadaos.isEmpty()) {
            System.out.println("Nenhum cidadão encontrado.");
            return;
        }
        cidadaos.forEach(cidadao -> System.out.println("ID: " + cidadao.getCidadaoId() + ", Nome: " + cidadao.getNome()));
    }

    private void deletarCidadao() {
        System.out.print("ID do Cidadão a ser deletado: ");
        int cidadaoId = obterId();
        cidadaoDAO.deletar(cidadaoId);
        System.out.println("Cidadão deletado com sucesso.");
    }

    private int obterId() {
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next();
            }
        }
    }

    private void gerenciarUsuarios() {
        while (true) {
            System.out.println("\nEscolha uma opção para Usuários:");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Atualizar Usuário");
            System.out.println("3. Buscar Usuário por ID");
            System.out.println("4. Listar Todos os Usuários");
            System.out.println("5. Deletar Usuário");
            System.out.println("0. Voltar");

            int opcaoUsuario = obterOpcao();

            switch (opcaoUsuario) {
                case 1: cadastrarUsuario(); break;
                case 2: atualizarUsuario(); break;
                case 3: buscarUsuarioPorId(); break;
                case 4: listarUsuarios(); break;
                case 5: deletarUsuario(); break;
                case 0: return;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarUsuario() {
        Usuario usuario = new Usuario();

        System.out.print("Nome do Usuário: ");
        usuario.setNome(scanner.nextLine());

        System.out.print("Senha: ");
        usuario.setSenha(scanner.nextLine());

        System.out.print("E-mail: ");
        usuario.setEmail(scanner.nextLine());

        usuarioDAO.adicionarUsuario(usuario);
        System.out.println("Usuário cadastrado com sucesso.");
    }

    private void atualizarUsuario() {
        System.out.print("ID do Usuário a ser atualizado: ");
        int usuarioId = obterId();

        Usuario usuarioExistente = usuarioDAO.buscarUsuarioPorId(usuarioId);
        if (usuarioExistente == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.println("Atualizando dados do usuário (atual: " + usuarioExistente.getNome() + ")");
        atualizarCamposUsuario(usuarioExistente);

        usuarioDAO.atualizarUsuario(usuarioExistente);
        System.out.println("Usuário atualizado com sucesso.");
    }

    private void atualizarCamposUsuario(Usuario usuario) {
        System.out.print("Nome (atual: " + usuario.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) {
            usuario.setNome(nome);
        }

        System.out.print("Senha (atual: " + usuario.getSenha() + "): ");
        String senha = scanner.nextLine();
        if (!senha.isEmpty()) {
            usuario.setSenha(senha);
        }

        System.out.print("E-mail (atual: " + usuario.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            usuario.setEmail(email);
        }
    }

    private void buscarUsuarioPorId() {
        System.out.print("ID do Usuário: ");
        int usuarioId = obterId();
        Usuario usuario = usuarioDAO.buscarUsuarioPorId(usuarioId);
        if (usuario != null) {
            System.out.println("Usuário encontrado: " + usuario.getNome());
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
            return;
        }
        usuarios.forEach(usuario -> System.out.println("ID: " + usuario.getUsuarioId() + ", Nome: " + usuario.getNome()));
    }

    private void deletarUsuario() {
        System.out.print("ID do Usuário a ser deletado: ");
        int usuarioId = obterId();
        usuarioDAO.deletarUsuario(usuarioId);
        System.out.println("Usuário deletado com sucesso.");
    }

}
