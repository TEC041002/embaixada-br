//  --------------------------------Com integração a banco de dados----------------------------  //
package org.example;




import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


public class Relatorio {


   public static void gerarRelatorioServicos(Connection conn) throws SQLException {
       String sql = "SELECT s.id, s.tipo_servico, s.data, c.nome FROM servicos s JOIN cidadaos c ON s.cidadao_id = c.id";
       Statement stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery(sql);


       System.out.println("Relatório de Serviços Agendados:");
       while (rs.next()) {
           int id = rs.getInt("id");
           String tipoServico = rs.getString("tipo_servico");
           Date data = rs.getDate("data");
           String nomeCidadao = rs.getString("nome");


           System.out.println("ID: " + id + ", Serviço: " + tipoServico + ", Data: " + data + ", Cidadão: " + nomeCidadao);
       }
   }
}


//  --------------------------------Sem integração a banco de dados----------------------------  //

package org.example;


import java.util.Date;
import java.util.List;


public class Relatorio {

  

   public static void gerarRelatorioServicos(List<Servico> servicos, List<Cidadao> cidadaos) {
       System.out.println("Relatório de Serviços Agendados:");


      
       for (Servico servico : servicos) {
         
           Cidadao cidadaoAssociado = null;
           for (Cidadao cidadao : cidadaos) {
               if (cidadao.getId() == servico.getCidadaoId()) {
                   cidadaoAssociado = cidadao;
                   break;
               }
           }


         
           if (cidadaoAssociado != null) {
               System.out.printf("ID: %d, Serviço: %s, Data: %s, Cidadão: %s%n",
                       servico.getId(), servico.getTipoServico(), servico.getData(), cidadaoAssociado.getNome());
           } else {
               System.out.printf("Cidadão não encontrado para o serviço ID: %d%n", servico.getId());
           }
       }
   }
}
