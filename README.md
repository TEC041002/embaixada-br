# Embaixada BR - Sistema ERP

Este projeto tem como objetivo desenvolver um sistema ERP simples em Java para gerenciar as operações da Embaixada do Brasil, promovendo a colaboração entre as equipes de desenvolvimento, testes e implementação.

## Objetivo

Desenvolver um sistema que atenda às seguintes funcionalidades:
- **Cadastro de Cidadãos:** Informações pessoais, documentos e status.
- **Agendamento de Serviços:** Marcação de consultas e serviços oferecidos pela embaixada.
- **Gerenciamento de Vistos:** Registro e acompanhamento de pedidos de visto.
- **Relatórios:** Geração de relatórios de atendimentos e serviços.

## Estrutura da Atividade

### 1. Divisão das Equipes
- **Desenvolvedores:** Responsáveis pela codificação do sistema e integração com o banco de dados.
- **Testers:** Garantir a qualidade do software através de testes.
- **Implementadores:** Integrar o sistema e prepará-lo para uso.

### 2. Tempo de Duração
- A atividade pode ser realizada em 2 a 3 aulas.

### 3. Tecnologias Sugeridas
- **Java SE:** Para a programação do backend.
- **JDBC:** Para a conexão com um banco de dados (SQLite ou MySQL).
- **MySQL Workbench:** Para visualização e administração do banco.
- **Git:** Para controle de versão.

## Atividades Específicas por Equipe

### Desenvolvedores
- Criar classes Java para cada funcionalidade (por exemplo, Cidadao, Servico, Visto).
- Implementar métodos para operações (cadastro, agendamento, geração de relatórios).
- Configurar o banco de dados:
  - Criar tabelas (cidadãos, serviços, vistos).
  - Utilizar JDBC para operações CRUD.

### Testers
- Elaborar casos de teste para cada funcionalidade.
- Criar um plano de teste e registrar os resultados.

### Implementadores
- Integrar o sistema em um ambiente de desenvolvimento.
- Preparar uma apresentação final demonstrando o funcionamento do sistema.

## Banco de Dados

### Modelo Relacional
- **Tabela cidadãos:** id, nome, documento, status.
- **Tabela serviços:** id, tipo_servico, data, cidadao_id.
- **Tabela vistos:** id, cidadao_id, status_visto, data_pedido.

## Apresentação Final

Cada equipe apresentará seu trabalho:
- **Desenvolvedores:** Demonstração do código e funcionalidades.
- **Testers:** Resultados dos testes realizados.
- **Implementadores:** Explicação sobre a integração e usabilidade do sistema.

## Avaliação

- Avaliação do código (manutenibilidade, clareza).
- Qualidade e abrangência dos testes.
- Integração com o banco de dados.
- Apresentação e clareza na explicação do projeto.

## Dicas para a Produção
- Forneça um modelo inicial do banco de dados.
- Documente as decisões tomadas durante o desenvolvimento.
- Esteja disponível para tirar dúvidas sobre JDBC e modelagem de banco de dados.

## Contato
Para mais informações, visite: [www.embaixabr.com](http://www.contoso.com)

---

