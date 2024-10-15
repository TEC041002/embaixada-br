create database embaixada;
use embaixada;
show tables;
describe passaporte;
describe cidadao;
describe casamento;
describe autorizacao;
describe cpf;
describe nascimento;
describe divorcio;
describe obito;
describe nacionalidade;
describe taxas;
drop database embaxada;

select * from passaporte;
describe passaporte;
create table passaporte(
idpassaporte int unsigned not null auto_increment,
idcidadao int unsigned not null,
tipoPassaporte char(1) not null,
paisEmissor char(3),
numeroPassaporte varchar (8),
dataExpedicao datetime not null,
validade datetime not null,
orgaoExpeditor char(9) not null,
primary key(idpassaporte),
foreign key(idcidadao) references cidadao(idcidadao)
);

create table cidadao(
idcidadao int unsigned not null auto_increment,
date date,
idpassaporte int unsigned not null,
nomePessoa varchar(100),
nancionalidade varchar (15),
dataNascimento datetime not null,
localNascimento varchar(30),
sexo char(1),
naturalidade varchar(15),
profissao varchar (10),
telefone varchar(10),
email varchar (20),
enderco varchar(30),
filiacao1 varchar(50),
filiacao2 varchar(50),
primary key(idcidadao)
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

delete from obito where idobito = 130;
delete from obito where idobito = 131;
select * from obito;
drop table obito;
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
taxaPassaporte float,
taxaCasamento float,
taxaAutorizacaoe float,
taxaCpf float,
taxaNascimento float,
taxaDivorcio float,
taxaObito float,
taxaNacionalidade float,
primary key(idtaxas)
);
