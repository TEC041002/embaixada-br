//  ---------------------------------Com integração a banco de dados---------------------------------  //
package org.example;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;


public class Servico {
   private int id;
   private String tipoServico;
   private Date data;
   private int cidadaoId;


   public Servico(int id, String tipoServico, Date data, int cidadaoId) {
       this.id = id;
       this.tipoServico = tipoServico;
       this.data = data;
       this.cidadaoId = cidadaoId;
   }


   public int getId() { return id; }
   public String getTipoServico() { return tipoServico; }
   public Date getData() { return data; }
   public int getCidadaoId() { return cidadaoId; }


   public static void agendarServico(Connection conn, Servico servico) throws SQLException {
       String sql = "INSERT INTO servicos (tipo_servico, data, cidadao_id) VALUES (?, ?, ?)";
       PreparedStatement stmt = conn.prepareStatement(sql);
       stmt.setString(1, servico.getTipoServico());
       stmt.setDate(2, new java.sql.Date(servico.getData().getTime()));
       stmt.setInt(3, servico.getCidadaoId());


       stmt.executeUpdate();
       System.out.println("Serviço agendado com sucesso!");
   }
}


//  ---------------------------------Sem integração a banco de dados---------------------------------  //

import java.util.Date;


public class Servico {
   private int id;
   private String tipoServico;
   private Date data;
   private int cidadaoId;



   public Servico(int id, String tipoServico, Date data, int cidadaoId) {
       this.id = id;
       this.tipoServico = tipoServico;
       this.data = data;
       this.cidadaoId = cidadaoId;
   }



   public int getId() {
       return id;
   }


   public String getTipoServico() {
       return tipoServico;
   }


   public Date getData() {
       return data;
   }


   public int getCidadaoId() {
       return cidadaoId;
   }


 
   public static void agendarServico(Servico servico) {
      
       System.out.println("Serviço agendado com sucesso!");
       System.out.println("ID do Serviço: " + servico.getId());
       System.out.println("Tipo de Serviço: " + servico.getTipoServico());
       System.out.println("Data do Serviço: " + servico.getData());
       System.out.println("ID do Cidadão: " + servico.getCidadaoId());
   }
}
