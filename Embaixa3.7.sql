create database embaixada5;
use embaixada5;
show tables;

describe passaporte;
select * from passaporte;
describe cidadao;
select * from cidadao;
describe casamento;
select * from casamento;
describe autorizacao;
select * from autorizacao;
describe cpf;
select * from cpf;
describe nascimento;
select * from nascimento;
describe divorcio;
select * from divorcio;
describe obito;
select * from obito;
describe nacionalidade;
select * from nacionalidade;
describe taxas;
select * from taxas;
drop database embaxada;
 
create table passaporte(
idpassaporte int unsigned not null auto_increment,
idcidadao int unsigned not null,
tipoPassaporte char(1) not null,
paisEmissor char(3),
numeroPassaporte varchar (8),
dataExpedicao datetime not null,
validade datetime not null,
orgaoExpeditor char(20) not null,
condicao boolean,
primary key(idpassaporte),
foreign key(idcidadao) references cidadao(idcidadao)
);
select cidadao.nomePessoa, passaporte.tipoPassaporte from cidadao join passaporte on cidadao.idcidadao = 1;
alter table cidadao add rg varchar(40);

create table cidadao(
idcidadao int unsigned not null auto_increment,
date date,
idpassaporte int unsigned not null,
nomePessoa varchar(100),
nancionalidade varchar (25),
dataNascimento datetime not null,
localNascimento varchar(50),
sexo char(1),
naturalidade varchar(50),
profissao varchar (50),
telefone varchar(30),
email varchar (50),
enderco varchar(50),
filiacao1 varchar(50),
filiacao2 varchar(50),
primary key(idcidadao)
);

create table login(
idlogin int unsigned not null auto_increment,
idcidadao int unsigned not null,
login varchar(45),
senha varchar(45),
primary key(idlogin),
foreign key(idcidadao) references cidadao(idcidadao)
);

create table casamento(
idcasamento int unsigned not null auto_increment,
date date,
idpassaporte int unsigned not null,
idcidadao int unsigned not null,
declarante varchar (30),
diaCasamento datetime not null,
localidadePais varchar (20),
numeroRgistroCivil varchar (20),
conjuge varchar(30),
estadoCivilAnterior varchar(10),
nomeConjugeCasamentoAnterior varchar(10),
dataNascimentoConjugeAnteror datetime not null,
dataObtoConjugeAnteror datetime not null,
nomeMaeConjugeAnterior varchar(30),
nacionalidadeConjugeAnterior varchar(10),
domicilioPaisConjugeAnterior varchar(40),
primary key(idcasamento),
foreign key(idpassaporte) references passaporte(idpassaporte)
);

create table autorizacao(
idautorizacao int unsigned not null auto_increment,
date date,
idpassaporte int unsigned not null,
idcidadao int unsigned not null,
nomeMenor varchar (30) not null,
dataExpedicao date not null,
residenciaAtual varchar(30),
nomePai varchar (30),
nomeMae varchar(30),
nomePessoaAutorizador varchar(30),
primary key (idautorizacao),
foreign key(idpassaporte) references passaporte(idpassaporte),
foreign key(idcidadao) references cidadao(idcidadao)
);

ALTER TABLE cpf MODIFY cpf varchar(30);
create table cpf(
idcpf int unsigned not null auto_increment,
idpassaporte int unsigned not null,
idcidadao int unsigned not null,
motivo varchar(10),
dataSaidaPais datetime not null,
tituloEleitor int (10),
cpf int (11),
primary key(idcpf),
foreign key(idpassaporte) references passaporte(idpassaporte),
foreign key(idcidadao) references cidadao(idcidadao)
);

create table nascimento(
idnascimento int unsigned not null auto_increment,
idcidadao int unsigned not null,
idpassaporte int unsigned not null,
idcpf int unsigned not null,
dataNascimento datetime not null,
paisDeNascimento char(3),
enderecoResidencial varchar(30),
nomePai varchar (30),
nomeMae varchar (30),
primary key(idnascimento),
foreign key(idcidadao) references cidadao(idcidadao),
foreign key(idpassaporte) references passaporte(idpassaporte),
foreign key(idcpf) references cpf(idcpf)
);

create table divorcio(
iddivorcio int unsigned not null auto_increment,
idcidadao int unsigned not null,
idpassaporte int unsigned not null,
nomeRequerente varchar(30) not null,
nomeExConjuge varchar(30) not null,
paisAtual char(3) not null,
dataSolitacao datetime not null,
primary key(iddivorcio),
foreign key(idcidadao) references cidadao(idcidadao),
foreign key(idpassaporte) references passaporte(idpassaporte)
);

create table obito(
idobito int unsigned not null auto_increment,
idpassaporte int unsigned not null,
idcidadao int unsigned not null,
dataObito datetime not null,
endereco varchar(30) not null,
paisFalecido char(3) not null,
primary key(idobito),
foreign key(idpassaporte) references passaporte(idpassaporte),
foreign key(idcidadao) references cidadao(idcidadao)
);

create table nacionalidade(
idnacionalidade int unsigned not null auto_increment,
date date,
idcidadao int unsigned not null,
idpassaporte int unsigned not null,
dataSolicitacao datetime not null,
primary key(idnacionalidade),
foreign key(idcidadao) references cidadao(idcidadao),
foreign key(idpassaporte) references passaporte(idpassaporte)
);

create table taxas(
idtaxas int unsigned not null auto_increment,
idpassaporte int unsigned not null,
idcasamento int unsigned not null,
idautorizacao int unsigned not null,
idcpf int unsigned not null,
idnascimento int unsigned not null,
iddivorcio int unsigned not null,
idobito int unsigned not null,
idnacionalidade int unsigned not null,
taxaPassaporte float,
taxaCasamento float,
taxaAutorizacaoe float,
taxaCpf float,
taxaNascimento float,
taxaDivorcio float,
taxaObito float,
taxaNacionalidade float,
primary key(idtaxas),
foreign key(idpassaporte) references passaporte(idpassaporte),
foreign key(idcasamento) references casamento(idcasamento),
foreign key(idautorizacao) references autorizacao(idautorizacao),
foreign key(idcpf) references cpf(idcpf),
foreign key(idnascimento) references nascimento(idnascimento),
foreign key(iddivorcio) references divorcio(iddivorcio),
foreign key(idobito) references obito(idobito),
foreign key(idnacionalidade) references nacionalidade(idnacionalidade)
);

create table servicos(
idservicos int unsigned not null auto_increment,
idcidadao int unsigned not null,
idtaxas int unsigned not null,
idpassaporte int unsigned not null,
idcasamento int unsigned not null,
idautorizacao int unsigned not null,
idcpf int unsigned not null,
idnascimento int unsigned not null,
iddivorcio int unsigned not null,
idobito int unsigned not null,
idnacionalidade int unsigned not null,
tipoServico varchar(40) not null,
primary key(idservicos),
foreign key(idcidadao) references cidadao(idcidadao),
foreign key(idtaxas) references taxas(idtaxas),
foreign key(idpassaporte) references passaporte(idpassaporte),
foreign key(idcasamento) references casamento(idcasamento),
foreign key(idautorizacao) references autorizacao(idautorizacao),
foreign key(idcpf) references cpf(idcpf),
foreign key(idnascimento) references nascimento(idnascimento),
foreign key(iddivorcio) references divorcio(iddivorcio),
foreign key(idobito) references obito(idobito),
foreign key(idnacionalidade) references nacionalidade(idnacionalidade)
);

-- inserindo dados na tabela cidadao
INSERT INTO cidadao (idpassaporte, nomePessoa, nancionalidade, dataNascimento, localNascimento, sexo, naturalidade, profissao, telefone, email, enderco, filiacao1, filiacao2) VALUES
( 1, 'Ana Silva', 'BR', '1990-05-20', 'São Paulo', 'F', 'São Paulo', 'Engenheira', '11987654321', 'ana@example.com', 'Rua A, 1', 'João Silva', 'Maria Silva'),
( 2, 'Carlos Souza', 'BR', '1985-07-15', 'Rio de Janeiro', 'M', 'Rio de Janeiro', 'Médico', '21987654321', 'carlos@example.com', 'Rua B, 2', 'José Souza', 'Ana Souza'),
( 3, 'Fernanda Lima', 'BR', '1988-03-10', 'Curitiba', 'F', 'Curitiba', 'Arquiteta', '41987654321', 'fernanda@example.com', 'Rua C, 3', 'Pedro Lima', 'Lucia Lima'),
( 4, 'Gabriel Santos', 'BR', '1995-08-22', 'Salvador', 'M', 'Salvador', 'Estudante', '51987654321', 'gabriel@example.com', 'Rua D, 4', 'Carlos Santos', 'Ana Santos'),
( 5, 'Juliana Costa', 'BR', '1992-12-30', 'Fortaleza', 'F', 'Fortaleza', 'Professora', '61987654321', 'juliana@example.com', 'Rua E, 5', 'José Costa', 'Maria Costa'),
( 6, 'Marcos Pereira', 'BR', '1983-01-14', 'Recife', 'M', 'Recife', 'Engenheiro', '81987654321', 'marcos@example.com', 'Rua F, 6', 'João Pereira', 'Ana Pereira'),
( 7, 'Tatiane Almeida', 'BR', '1991-09-05', 'Belo Horizonte', 'F', 'Belo Horizonte', 'Designer', '31987654321', 'tatiane@example.com', 'Rua G, 7', 'Roberto Almeida', 'Luiza Almeida'),
( 8, 'Rafael Gomes', 'BR', '1987-11-11', 'Manaus', 'M', 'Manaus', 'Advogado', '92987654321', 'rafael@example.com', 'Rua H, 8', 'Carlos Gomes', 'Sandra Gomes'),
( 9, 'Patrícia Martins', 'BR', '1994-04-20', 'Porto Alegre', 'F', 'Porto Alegre', 'Jornalista', '51987654322', 'patricia@example.com', 'Rua I, 9', 'André Martins', 'Bia Martins'),
( 10, 'Leonardo Rocha', 'BR', '1980-06-18', 'São Luís', 'M', 'São Luís', 'Músico', '98987654321', 'leonardo@example.com', 'Rua J, 10', 'Ricardo Rocha', 'Fernanda Rocha'),
( 11, 'Ana Beatriz', 'BR', '1989-05-15', 'João Pessoa', 'F', 'João Pessoa', 'Fotógrafa', '83987654321', 'anabeatriz@example.com', 'Rua K, 11', 'Renato Beatriz', 'Clara Beatriz'),
( 12, 'Eduardo Silva', 'BR', '1996-02-28', 'Natal', 'M', 'Natal', 'Estudante', '84987654321', 'eduardo@example.com', 'Rua L, 12', 'Carlos Silva', 'Sofia Silva'),
( 13, 'Mariana Cruz', 'BR', '1993-03-22', 'Vitória', 'F', 'Vitória', 'Nutricionista', '27987654321', 'mariana@example.com', 'Rua M, 13', 'Pedro Cruz', 'Maria Cruz'),
( 14, 'Sérgio Almeida', 'BR', '1981-07-10', 'Florianópolis', 'M', 'Florianópolis', 'Cozinheiro', '48987654321', 'sergio@example.com', 'Rua N, 14', 'Joaquim Almeida', 'Ana Almeida'),
( 15, 'Laura Pereira', 'BR', '1992-10-30', 'Belo Horizonte', 'F', 'Belo Horizonte', 'Professora', '31987654321', 'laura@example.com', 'Rua O, 15', 'Pedro Pereira', 'Lucia Pereira');

INSERT INTO cidadao (idpassaporte, nomePessoa, nancionalidade, dataNascimento, localNascimento, sexo, naturalidade, profissao, telefone, email, enderco, filiacao1, filiacao2, rg) VALUES
( 16, 'Lileia Albuquerque', 'BR', '1990-05-20', 'São Paulo', 'F', 'São Paulo', 'Engenheira', '11987654321', 'ana@example.com', 'Rua A, 1', 'João Silva', 'Maria Silva','298.715 SSP-DF'),
( 17, 'Lucas Gabriel Rodrigues da Silva', 'BR', '1985-07-15', 'Rio de Janeiro', 'M', 'Rio de Janeiro', 'Médico', '21987654321', 'carlos@example.com', 'Rua B, 2', 'José Souza', 'Ana Souza','369.789 SSP-DF'),
( 18, 'Rubens Golçalves de Oliveira', 'BR', '1988-03-10', 'Curitiba', 'F', 'Curitiba', 'Arquiteta', '41987654321', 'fernanda@example.com', 'Rua C, 3', 'Pedro Lima', 'Lucia Lima','125.965 SSP-MA');

-- inserindo dados na tabela passaporte
INSERT INTO passaporte (idcidadao, tipoPassaporte, paisEmissor, numeroPassaporte, dataExpedicao, validade, orgaoExpeditor) VALUES
(1, 'A', 'BRA', '12345678', '2024-01-01', '2034-01-01', 'Polícia Federal'),
(2, 'A', 'BRA', '23456789', '2024-01-01', '2034-01-01', 'Polícia Federal'),
(3, 'A', 'BRA', '34567890', '2024-01-01', '2034-01-01', 'Polícia Federal'),
(4, 'A', 'BRA', '45678901', '2024-01-01', '2034-01-01', 'Polícia Federal'),
(5, 'A', 'BRA', '56789012', '2024-01-01', '2034-01-01', 'Polícia Federal'),
(6, 'A', 'BRA', '67890123', '2024-01-01', '2034-01-01', 'Polícia Federal'),
(7, 'A', 'BRA', '78901234', '2024-01-01', '2034-01-01', 'Polícia Federal'),
(8, 'A', 'BRA', '89012345', '2024-01-01', '2034-01-01', 'Polícia Federal'),
(9, 'A', 'BRA', '90123456', '2024-01-01', '2034-01-01', 'Polícia Federal'),
(10, 'A', 'BRA', '01234567', '2024-01-01', '2034-01-01', 'Polícia Federal'),
(11, 'A', 'BRA', '12345679', '2024-01-01', '2034-01-01', 'Polícia Federal'),
(12, 'A', 'BRA', '23456780', '2024-01-01', '2034-01-01', 'Polícia Federal'),
(13, 'A', 'BRA', '34567891', '2024-01-01', '2034-01-01', 'Polícia Federal'),
(14, 'A', 'BRA', '45678902', '2024-01-01', '2034-01-01', 'Polícia Federal'),
(15, 'A', 'BRA', '56789013', '2024-01-01', '2034-01-01', 'Polícia Federal');

-- inserindo dados na tabela casamento
INSERT INTO casamento (idpassaporte, idcidadao, declarante, diaCasamento, localidadePais, numeroRgistroCivil, conjuge, estadoCivilAnterior, nomeConjugeCasamentoAnterior, dataNascimentoConjugeAnteror, dataObtoConjugeAnteror, nomeMaeConjugeAnterior, nacionalidadeConjugeAnterior, domicilioPaisConjugeAnterior) VALUES
(1, 1, 'Ana Silva', '2024-01-01', 'Brasil', '12345', 'Bruno Silva', 'Solteiro', '', '1990-05-20', '2000-04-07', 'Maria Silva', 'BR', 'Brasil'),
(2, 2, 'Carlos Souza', '2024-01-02', 'Brasil', '12346', 'Juliana Souza', 'Solteiro', '', '1985-07-15', '1990-06-01', 'Ana Souza', 'BR', 'Brasil'),
(3, 3, 'Fernanda Lima', '2024-01-03', 'Brasil', '12347', 'Ricardo Lima', 'Solteiro', '', '1988-03-10', '1993-05-04', 'Lucia Lima', 'BR', 'Brasil'),
(4, 4, 'Gabriel Santos', '2024-01-04', 'Brasil', '12348', 'Mariana Santos', 'Solteiro', '', '1995-08-22', '2021-03-15', 'Ana Santos', 'BR', 'Brasil'),
(5, 5, 'Juliana Costa', '2024-01-05', 'Brasil', '12349', 'André Costa', 'Solteiro', '', '1992-12-30', '2018-07-22', 'Maria Costa', 'BR', 'Brasil'),
(6, 6, 'Marcos Pereira', '2024-01-06', 'Brasil', '12350', 'Luana Pereira', 'Solteiro', '', '1983-01-14', '2020-11-05', 'Ana Pereira', 'BR', 'Brasil'),
(7, 7, 'Tatiane Almeida', '2024-01-07', 'Brasil', '12351', 'Rafael Almeida', 'Solteiro', '', '1991-09-05', '2019-01-30', 'Luiza Almeida', 'BR', 'Brasil'),
(8, 8, 'Rafael Gomes', '2024-01-08', 'Brasil', '12352', 'Marcia Gomes', 'Solteiro', '', '1987-11-11', '2022-09-14', 'Sandra Gomes', 'BR', 'Brasil'),
(9, 9, 'Patrícia Martins', '2024-01-09', 'Brasil', '12353', 'Leonardo Martins', 'Solteiro', '', '1994-04-20', '2017-05-09', 'Bia Martins', 'BR', 'Brasil'),
(10, 10, 'Leonardo Rocha', '2024-01-10', 'Brasil', '12354', 'Sofia Rocha', 'Solteiro', '', '1980-06-18', '2023-04-27', 'Fernanda Rocha', 'BR', 'Brasil'),
(11, 11, 'Ana Beatriz', '2024-01-11', 'Brasil', '12355', 'Marcio Beatriz', 'Solteiro', '', '1989-05-15', '2016-12-01', 'Clara Beatriz', 'BR', 'Brasil'),
(12, 12, 'Eduardo Silva', '2024-01-12', 'Brasil', '12356', 'Talita Silva', 'Solteiro', '', '1996-02-28', '2024-02-18', 'Sofia Silva', 'BR', 'Brasil'),
(13, 13, 'Mariana Cruz', '2024-01-13', 'Brasil', '12357', 'Júnior Cruz', 'Solteiro', '', '1993-03-22', '2015-06-11', 'Maria Cruz', 'BR', 'Brasil'),
(14, 14, 'Sérgio Almeida', '2024-01-14', 'Brasil', '12358', 'Claudia Almeida', 'Solteiro', '', '1981-07-10', '2014-10-23', 'Ana Almeida', 'BR', 'Brasil'),
(15, 15, 'Laura Pereira', '2024-01-15', 'Brasil', '12359', 'Ricardo Pereira', 'Solteiro', '', '1992-10-30', '2025-08-04', 'Lucia Pereira', 'BR', 'Brasil');

-- inserindo dados na tabela de autorização 
INSERT INTO autorizacao (idpassaporte, idcidadao, nomeMenor, dataExpedicao, residenciaAtual, nomePai, nomeMae, nomePessoaAutorizador) VALUES
(1, 1, 'Felipe Silva', '2024-01-01', 'São Paulo', 'Ana Silva', 'Carlos Silva', 'José Silva'),
(2, 2, 'Mariana Souza', '2024-01-01', 'Rio de Janeiro', 'Carlos Souza', 'Maria Souza', 'Ana Souza'),
(3, 3, 'Lucas Lima', '2024-01-01', 'Curitiba', 'Fernanda Lima', 'Ricardo Lima', 'Lucia Lima'),
(4, 4, 'Gabriel Santos', '2024-01-01', 'Salvador', 'Gabriel Santos', 'Ana Santos', 'João Santos'),
(5, 5, 'Larissa Costa', '2024-01-01', 'Fortaleza', 'Juliana Costa', 'José Costa', 'Maria Costa'),
(6, 6, 'Eduardo Pereira', '2024-01-01', 'Recife', 'Marcos Pereira', 'Ana Pereira', 'Roberto Pereira'),
(7, 7, 'Ana Almeida', '2024-01-01', 'Belo Horizonte', 'Tatiane Almeida', 'Luiza Almeida', 'Marcio Almeida'),
(8, 8, 'Mateus Gomes', '2024-01-01', 'Manaus', 'Rafael Gomes', 'Sandra Gomes', 'Carlos Gomes'),
(9, 9, 'Lívia Martins', '2024-01-01', 'Porto Alegre', 'Patrícia Martins', 'Bia Martins', 'André Martins'),
(10, 10, 'João Rocha', '2024-01-01', 'São Luís', 'Leonardo Rocha', 'Fernanda Rocha', 'Ricardo Rocha'),
(11, 11, 'Clara Beatriz', '2024-01-01', 'João Pessoa', 'Ana Beatriz', 'Clara Beatriz', 'Renato Beatriz'),
(12, 12, 'Daniel Silva', '2024-01-01', 'Natal', 'Eduardo Silva', 'Sofia Silva', 'Carlos Silva'),
(13, 13, 'Isabela Cruz', '2024-01-01', 'Vitória', 'Mariana Cruz', 'Maria Cruz', 'Pedro Cruz'),
(14, 14, 'Lucas Almeida', '2024-01-01', 'Florianópolis', 'Sérgio Almeida', 'Ana Almeida', 'Joaquim Almeida'),
(15, 15, 'Ana Pereira', '2024-01-01', 'Belo Horizonte', 'Laura Pereira', 'Lucia Pereira', 'Pedro Pereira');

-- inserindo dados na tabela CPF 
INSERT INTO cpf (idpassaporte, idcidadao, motivo, dataSaidaPais, tituloEleitor, cpf) VALUES
(1, 1, 'Nacional', '2024-01-01', 1234567890, 12345678901),
(2, 2, 'Nacional', '2024-01-01', 1234567891, 12345678902),
(3, 3, 'Nacional', '2024-01-01', 1234567892, 12345678903),
(4, 4, 'Nacional', '2024-01-01', 1234567893, 12345678904),
(5, 5, 'Nacional', '2024-01-01', 1234567894, 12345678905),
(6, 6, 'Nacional', '2024-01-01', 1234567895, 12345678906),
(7, 7, 'Nacional', '2024-01-01', 1234567896, 12345678907),
(8, 8, 'Nacional', '2024-01-01', 1234567897, 12345678908),
(9, 9, 'Nacional', '2024-01-01', 1234567898, 12345678909),
(10, 10, 'Nacional', '2024-01-01', 1234567899, 12345678910),
(11, 11, 'Nacional', '2024-01-01', 1234567800, 12345678011),
(12, 12, 'Nacional', '2024-01-01', 1234567811, 12345678122),
(13, 13, 'Nacional', '2024-01-01', 1234567822, 12345678233),
(14, 14, 'Nacional', '2024-01-01', 1234567833, 12345678344),
(15, 15, 'Nacional', '2024-01-01', 1234567844, 12345678455);
 
-- insaerindo dados na tabela nascimento
INSERT INTO nascimento (idcidadao, idpassaporte, idcpf, dataNascimento, paisDeNascimento, enderecoResidencial, nomePai, nomeMae) VALUES
(1, 1, 1, '1990-05-20', 'BRA', 'Rua A, 1', 'João Silva', 'Maria Silva'),
(2, 2, 2, '1985-07-15', 'BRA', 'Rua B, 2', 'José Souza', 'Ana Souza'),
(3, 3, 3, '1988-03-10', 'BRA', 'Rua C, 3', 'Pedro Lima', 'Lucia Lima'),
(4, 4, 4, '1995-08-22', 'BRA', 'Rua D, 4', 'Carlos Santos', 'Ana Santos'),
(5, 5, 5, '1992-12-30', 'BRA', 'Rua E, 5', 'José Costa', 'Maria Costa'),
(6, 6, 6, '1983-01-14', 'BRA', 'Rua F, 6', 'João Pereira', 'Ana Pereira'),
(7, 7, 7, '1991-09-05', 'BRA', 'Rua G, 7', 'Roberto Almeida', 'Luiza Almeida'),
(8, 8, 8, '1987-11-11', 'BRA', 'Rua H, 8', 'Carlos Gomes', 'Sandra Gomes'),
(9, 9, 9, '1994-04-20', 'BRA', 'Rua I, 9', 'André Martins', 'Bia Martins'),
(10, 10, 10, '1980-06-18', 'BRA', 'Rua J, 10', 'Ricardo Rocha', 'Fernanda Rocha'),
(11, 11, 11, '1989-05-15', 'BRA', 'Rua K, 11', 'Renato Beatriz', 'Clara Beatriz'),
(12, 12, 12, '1996-02-28', 'BRA', 'Rua L, 12', 'Carlos Silva', 'Sofia Silva'),
(13, 13, 13, '1993-03-22', 'BRA', 'Rua M, 13', 'Pedro Cruz', 'Maria Cruz'),
(14, 14, 14, '1981-07-10', 'BRA', 'Rua N, 14', 'Joaquim Almeida', 'Ana Almeida'),
(15, 15, 15, '1992-10-30', 'BRA', 'Rua O, 15', 'Pedro Pereira', 'Lucia Pereira');

-- inserindo dados na tabela divorcio 
INSERT INTO divorcio (idcidadao, idpassaporte, nomeRequerente, nomeExConjuge, paisAtual, dataSolitacao) VALUES
(1, 1, 'Ana Silva', 'Bruno Silva', 'BRA', '2024-01-01'),
(2, 2, 'Carlos Souza', 'Juliana Souza', 'BRA', '2024-01-02'),
(3, 3, 'Fernanda Lima', 'Ricardo Lima', 'BRA', '2024-01-03'),
(4, 4, 'Gabriel Santos', 'Mariana Santos', 'BRA', '2024-01-04'),
(5, 5, 'Juliana Costa', 'André Costa', 'BRA', '2024-01-05'),
(6, 6, 'Marcos Pereira', 'Luana Pereira', 'BRA', '2024-01-06'),
(7, 7, 'Tatiane Almeida', 'Rafael Almeida', 'BRA', '2024-01-07'),
(8, 8, 'Rafael Gomes', 'Marcia Gomes', 'BRA', '2024-01-08'),
(9, 9, 'Patrícia Martins', 'Leonardo Martins', 'BRA', '2024-01-09'),
(10, 10, 'Leonardo Rocha', 'Sofia Rocha', 'BRA', '2024-01-10'),
(11, 11, 'Ana Beatriz', 'Marcio Beatriz', 'BRA', '2024-01-11'),
(12, 12, 'Eduardo Silva', 'Talita Silva', 'BRA', '2024-01-12'),
(13, 13, 'Mariana Cruz', 'Júnior Cruz', 'BRA', '2024-01-13'),
(14, 14, 'Sérgio Almeida', 'Claudia Almeida', 'BRA', '2024-01-14'),
(15, 15, 'Laura Pereira', 'Ricardo Pereira', 'BRA', '2024-01-15');

-- inserindo dados na tabela obitos 
INSERT INTO obito (idpassaporte, idcidadao, dataObito, endereco, paisFalecido) VALUES
(1, 1, '2024-01-01', 'Rua A, 1', 'BRA'),
(2, 2, '2024-01-01', 'Rua B, 2', 'BRA'),
(3, 3, '2024-01-01', 'Rua C, 3', 'BRA'),
(4, 4, '2024-01-01', 'Rua D, 4', 'BRA'),
(5, 5, '2024-01-01', 'Rua E, 5', 'BRA'),
(6, 6, '2024-01-01', 'Rua F, 6', 'BRA'),
(7, 7, '2024-01-01', 'Rua G, 7', 'BRA'),
(8, 8, '2024-01-01', 'Rua H, 8', 'BRA'),
(9, 9, '2024-01-01', 'Rua I, 9', 'BRA'),
(10, 10, '2024-01-01', 'Rua J, 10', 'BRA'),
(11, 11, '2024-01-01', 'Rua K, 11', 'BRA'),
(12, 12, '2024-01-01', 'Rua L, 12', 'BRA'),
(13, 13, '2024-01-01', 'Rua M, 13', 'BRA'),
(14, 14, '2024-01-01', 'Rua N, 14', 'BRA'),
(15, 15, '2024-01-01', 'Rua O, 15', 'BRA');

-- inserindo dados na tabela Nacionalidade
insert into nacionalidade (idcidadao, idpassaporte, dataSolicitacao) values 
(1,2, '2019-10-06 12:08:10'),
(1,2, '2019-08-11 10:52:13'),
(1,2, '2019-06-09 08:38:45'),
(1,2, '2019-02-10 11:20:13'),
(1,2, '2019-07-03 13:29:25'),
(1,2, '2019-08-02 14:52:23'),
(1,2, '2019-09-14 17:36:10'),
(1,2, '2019-03-09 10:15:03'),
(1,2, '2019-12-25 10:22:06'),
(1,2, '2017-08-10 17:52:39'),
(1,2, '2017-08-15 10:02:13'),
(1,2, '2024-08-09 10:09:09'),
(1,2, '2024-05-12 12:56:13'),
(1,2, '2024-03-09 07:39:22'),
(1,2, '2019-08-09 10:02:13'); 


-- inserindo dados na tabela Taxas
INSERT INTO taxas (idpassaporte,idcasamento,idautorizacao,idcpf,idnascimento,iddivorcio,idobito,idnacionalidade,taxaPassaporte,taxaCasamento,taxaAutorizacaoe,taxaCpf,taxaNascimento,taxaDivorcio,taxaObito,taxaNacionalidade) VALUES
(1, 2, 3, 4, 5, 6, 7, 8,100.00, 800.00, 50.00, 35.00, 48.00, 120.00, 98.00, 160.00);

-- INSERINDO SENHAS E LOGIN PREDEFINIDOS
insert into login (idcidadao, login, senha) values (16, 'Lileia@123', 'df-2024'),(17, 'Lucas@123', 'df-2024'),(18, 'Rubens@123', 'df-2024');







 























