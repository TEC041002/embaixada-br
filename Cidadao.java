  // -------------------------------  Código com integração a Banco de dados:  ----------------------------------//

package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cidadao {
    private int id;  
    private String nome;
    private String documento;
    private String cpf;
    private String cep;
    private String endereco;
    private String email;
    private String telefone;
    private String uf;
    private String localidade;
    private String status;

   
    public Cidadao(int id, String nome, String documento, String cpf, String cep, String endereco,
                   String email, String telefone, String uf, String localidade, String status) {
        this.id = id; 
        this.nome = nome;
        this.documento = documento;
        this.cpf = cpf;
        this.cep = cep;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.uf = uf;
        this.localidade = localidade;
        this.status = status;
    }


    public Cidadao(int id, String nome, String documento, String status) {
        this.id = id;  
        this.nome = nome;
        this.documento = documento;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCep() {
        return cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getUf() {
        return uf;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getStatus() {
        return status;
    }

    public static void cadastrarCidadao(Connection conn, Cidadao cidadao) throws SQLException {
        String sql = "INSERT INTO cidadao (id, nome, documento, cpf, cep, endereco, email, telefone, uf, localidade, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, cidadao.getId());  // Definindo o id
        stmt.setString(2, cidadao.getNome());
        stmt.setString(3, cidadao.getDocumento());
        stmt.setString(4, cidadao.getCpf());
        stmt.setString(5, cidadao.getCep());
        stmt.setString(6, cidadao.getEndereco());
        stmt.setString(7, cidadao.getEmail());
        stmt.setString(8, cidadao.getTelefone());
        stmt.setString(9, cidadao.getUf());
        stmt.setString(10, cidadao.getLocalidade());
        stmt.setString(11, cidadao.getStatus());

        stmt.executeUpdate();
        System.out.println("Cidadão cadastrado com sucesso! ID: " + cidadao.getId());
    }

    
    public static void atualizarCidadao(Connection conn, Cidadao cidadao) throws SQLException {
        String sql = "UPDATE cidadao SET nome = ?, cpf = ?, cep = ?, endereco = ?, email = ?, telefone = ?, uf = ?, localidade = ?, status = ? " +
                "WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cidadao.getNome());
        stmt.setString(2, cidadao.getCpf());
        stmt.setString(3, cidadao.getCep());
        stmt.setString(4, cidadao.getEndereco());
        stmt.setString(5, cidadao.getEmail());
        stmt.setString(6, cidadao.getTelefone());
        stmt.setString(7, cidadao.getUf());
        stmt.setString(8, cidadao.getLocalidade());
        stmt.setString(9, cidadao.getStatus());
        stmt.setInt(10, cidadao.getId());  

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Cidadão atualizado com sucesso! ID: " + cidadao.getId());
        } else {
            System.out.println("Cidadão não encontrado. ID: " + cidadao.getId());
        }
    }

  
    public static void deletarCidadao(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM cidadao WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);  

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Cidadão deletado com sucesso! ID: " + id);
        } else {
            System.out.println("Cidadão não encontrado. ID: " + id);
        }
    }

  

  // -------------------------------  Código sem integração a Banco de dados:  ----------------------------------//

  package org.example;


import java.util.ArrayList;
import java.util.List;


public class Cidadao {
   private int id;
   private String nome;
   private String documento;
   private String cpf;
   private String cep;
   private String endereco;
   private String email;
   private String telefone;
   private String uf;
   private String localidade;
   private String status;



   private static List<Cidadao> cidadaos = new ArrayList<>();
   private static int idCounter = 1; 


   // Construtor completo
   public Cidadao(String nome, String documento, String cpf, String cep, String endereco,
                  String email, String telefone, String uf, String localidade, String status) {
       this.id = idCounter++;
       this.nome = nome;
       this.documento = documento;
       this.cpf = cpf;
       this.cep = cep;
       this.endereco = endereco;
       this.email = email;
       this.telefone = telefone;
       this.uf = uf;
       this.localidade = localidade;
       this.status = status;
   }



   public int getId() {
       return id;
   }


   public void setId(int id) {
       this.id = id;
   }


   public String getNome() {
       return nome;
   }


   public String getDocumento() {
       return documento;
   }


   public String getCpf() {
       return cpf;
   }


   public String getCep() {
       return cep;
   }


   public String getEndereco() {
       return endereco;
   }


   public String getEmail() {
       return email;
   }


   public String getTelefone() {
       return telefone;
   }


   public String getUf() {
       return uf;
   }


   public String getLocalidade() {
       return localidade;
   }


   public String getStatus() {
       return status;
   }



   public static void cadastrarCidadao(Cidadao cidadao) {
       cidadaos.add(cidadao);
       System.out.println("Cidadão cadastrado com sucesso! ID: " + cidadao.getId());
   }



   public static void atualizarCidadao(int id, String novoNome, String novoStatus) {
       for (Cidadao cidadao : cidadaos) {
           if (cidadao.getId() == id) {
               cidadao.nome = novoNome;
               cidadao.status = novoStatus;
               System.out.println("Cidadão atualizado com sucesso! ID: " + id);
               return;
           }
       }
       System.out.println("Cidadão não encontrado. ID: " + id);
   }



   public static void deletarCidadao(int id) {
       Cidadao cidadaoRemover = null;
       for (Cidadao cidadao : cidadaos) {
           if (cidadao.getId() == id) {
               cidadaoRemover = cidadao;
               break;
           }
       }
       if (cidadaoRemover != null) {
           cidadaos.remove(cidadaoRemover);
           System.out.println("Cidadão deletado com sucesso! ID: " + id);
       } else {
           System.out.println("Cidadão não encontrado. ID: " + id);
       }
   }



   public static void listarCidadaos() {
       if (cidadaos.isEmpty()) {
           System.out.println("Nenhum cidadão cadastrado.");
       } else {
           for (Cidadao cidadao : cidadaos) {
               System.out.println("ID: " + cidadao.getId() + ", Nome: " + cidadao.getNome() + ", Documento: " + cidadao.getDocumento() + ", Status: " + cidadao.getStatus());
           }
       }
   }
}

