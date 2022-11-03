>># VACATION-MANAGER-projeto-final

---
Projeto final do programa Catalisa turma 4

Equipe do projeto: Squad 5
- Ana Clara dos Santos Maciel;
- Jonathan Santos Tavares;
- Kevin Richard da Silva Candido;
- Pedro Pereira Paiva.

---
## Sobre o Proejto - Objetivo desta aplicação (Conceitual)

---
<p Align="justify">A Vacation Manager é um projeto que consiste em desenvolver uma aplicação, que permite a gestão de solicitações 
para gozo à férias dentro de uma organização, objetivando um fluxo mais intuitivo, fácil e fluído, possibilitanto ao gestor e ao colaborador,
de forma global, de forma segura através de acessos seus especificos, o pedido, a atualização, a aprovação e o cancelamento.</p>

---
## Tecnologias Utilizadas

---
### Back-end

- Java, versão 11;
- Spring Boot;
- JPA / Hibernate;
- Maven

### Front-end

- HTML / CSS / Bootstrap / JS


### Implantação em produção

- Back end: IntelliJ
- Front end: VSCode
- Banco de dados: Postgresql


---
## Competências

---

- SOLID
- CRUD - camadas
- ENUM
- EXCEPTIONS


---
## Regra de Negócio

---
### Regras Globais 

***Através da aplicação, deve ser possível:***

- cadastrar um novo usuário.
- um ADMIN buscar os dados de qualquer usuário.
- um EMPLOYEE buscar os seus dados.
- um ADMIN atualizar os dados de qualquer usuário.
- um EMPLOYEE atualizar seus próprios dados (mas não de outros usuários).
- um ADMIN inativar os dados de um usuário.
- um usuário criar um pedido de férias.
- um usuário buscar seus pedidos de férias.
- um usuário atualizar seu pedido de férias.
- um usuário cancelar seu pedido de férias.
- o recurso de busca de usuários não deverá trazer usuários INACTIVE.
- deverá ter telas de Front-End básicas para usar a aplicação.
- a aplicação deverá retornar mensagens amigáveis para as eventuais exceções retornadas pela aplicação.
- deverão ser usados Http Status adequados para cada resposta.
- um usuário com estado INACTIVE ou ON_VACATION não poderá editar seus dados, nem seus pedidos de férias.
- ao cancelar um pedido de férias, o status do pedido deverá ser atualizado para CANCELED.
- um pedido com estado CANCELED não deverá ser listado nos recursos de busca (de pedidos ou de usuários).

--- 
### Especificas por Classes

---
**Classe User**

***Atributos da Entidade User:***

- id: identificador do usuário (Long/String?) - Único
- name: nome do usuário (String)
- email: email do usuário (String) - Único
- birthDate: data de nascimento do usuário (LocalDate)
- hiringDate: data de contratação do usuário (LocalDate)
- daysBalance: saldo de dias de férias disponíveis (Integer)
- profile: perfil do usuário (Enum)
- status: estado no qual se encontra o usuário (Enum)
- vacationRequests: lista dos pedidos de férias do usuário (List<VacationRequest>)

***Especificações para User***

- existirá (inicialmente) dois perfis de usuários: ADMIN e EMPLOYEE, determinados no cadastro dos usuários.
- o saldo de dias de férias não poderá ser informado no cadastro.
- não deve ser possível cadastrar um usuário com uma data de contratação futura.
- não deve ser possível cadastrar (inicialmente) um usuário com menos de 18 anos.
- o campo nome deve conter no mínimo 5 caracteres.
- o formato do email deve ser validado no cadastro.
- não pode ter dois usuários cadastrados com o mesmo email.
- um usuário não pode ter mais de 60 dias de saldo de férias.
- existirá (inicialmente) três tipos de usuários: ACTIVE, ON_VACATION e INACTIVE.
- 
---
**Classe Vacation Request**

***Atributos da Entidade Vacation Request:***

- id: identificador do pedido (Long/String?) - Único
- userId: identificador do usuário pedindo férias (Long)
- vacationDays: quantidade de dias de férias usados no pedido (Integer)
- startAt: dia de início das férias (LocalDate)
- endAt: dia de volta ao escritório (LocalDate)
- status: estado no qual se encontra o pedido de férias (Enum)

***Especificações para Vacation Request***

- o usuário não poderá pedir menos de 5 dias de férias.
- o usuário não poderá pedir férias caso não tenha um saldo de dias suficiente.
- o usuário não poderá pedir férias com menos de 45 dias de antecedência.
- só um usuário com estado ACTIVE pode gerenciar seus pedidos de férias.
- a data de início das férias (startAt) não poderá coincidir com um fim de semana.
- a data de volta ao escritório (endAt) não poderá coincidir com um fim de semana (Exemplo: caso as férias acabem um sábado, a volta ao escritório deveria ser na segunda-feira seguinte).
- existirá (inicialmente) três tipos de estado de pedido de férias: CREATED, ONGOING, CONCLUDED e CANCELED, atualizados automaticamente à medida que o calendário avance.
- um pedido só poderá ser alterado ou cancelado até 7 dias antes do início do período de férias.
- um pedido cancelado deverá retornar o saldo de dias para o usuário.

---
## Versão da Aplicação

---

Versao 1 ...

---
## Explicação Técnica

---
<div class="text-justify">
<p Align="justify"></p>

A aplicação é composta por duas entidades: USER e VACATIONREQUEST, que permitem o acesso de dois perfis padronizados via Enum:
- perfil usuário, nomenclatura EMPLOYEE; 
- perfil administrador, nomenclatura ADMIN.

Os atributos de cada entidade estão listadas no campo acima, "Regra de Negócio".

<p Align="justify">Para a entidade User temos a possibilidade de utilizar CRUD's que permitirão o cadastro de um novo usuário, a atualização de
um usuário já cadastrado, a busca por todos os usuários já cadastrados na API ou apenas um único usuário especificamente, e fazer a 
inativaçao de um usuário que já não pertença mais a organização.</p>

A busca de um usuário já cadastrado, deverá ser via email do usuário, e somente trará em tela, usuario com status Ativo ou em ferias 

Também usaremos o e-mail para solicitar a requisição de cancelamento (inativação) do usuário.

Os atributos de cada entidade estão listadas no campo acima, "Regra de Negócio".

Todo o fluxo partirá de uma requisição via Front-End, na qual um formulário Front-end recebe e envia os dados para o Back-end 
onde a classe Controller identifica o tipo de requisição (Get/Post/Put/Delete), passa em uma imagem DTO, passando para camada 
service/repository/model, onde se dará todo o processamento dos dados de entrada, a análise das regras de negócio e se serão 
persisitidos ou não, devolvendo as respostas adequadas a cada tipo de requisição recebida.

Para o cadastro de usuário, serão validados os dados obrigatórios conforme a regra de negócio, como a idade, a data de admissão, 
o email, e que em caso de inadequações, conforme regras de negócio inseridas em cada método, serão recusadas e nao permitirá o
cadastro de um novo usuario, sempre reportando uma mensagem orientativa do motivo. Estando os dados adequados, será cadastrado um
novo usuario com status enum ACTIVE.

O mesmo fluxo, ocorre para a entidade Vacation Request com o devido processo e fluxo especifico para esta. 

Para a entidade Vacations Request temos a possibilidade de utilizar CRUD's que permitirão o cadastro de um novo pedido de férias,
, a atualização de um pedido já cadastrado, a busca por todos os pedidos já cadastrados na API ou apenas um único pedido especificamente, 
e fazer o cancelamento de um pedido de férias via Id do pedido.

Todo o fluxo para pedido de férias (Vacation Request), partirá de uma requisição via Front-End, na qual um formulário Front-end 
recebe e envia os dados para o Back-end, onde a classe Controller identifica o tipo de requisição (Get/Post/Put/Delete), passa em uma
imagem DTO, passando para camada service/repository/model, onde se dará todo o processamento dos dados de entrada, a análise das
regras de negócio e se serão persistidos ou não, devolvendo as respostas adequadas a cada tipo de requisição recebida.

Para o cadastro de Pedido de Férias, serão validados dados obrigatórios conforme a regra de negócio, como a quantidade minima de dias 
de férias, o prazo minimo de dias antecedendo ao dia que se quer o inicio de férias e, que em caso de inadequações, conforme regras
inseridas em cada metodo, serão recusadas e nao permitirá o cadastro de um novo pedido de férias, sempre reportando uma mensagem 
orientativa do motivo. Estando os dados adequados, será cadastrado um novo pedido de férias com status CREATED.

Entre as entidades (User e Vacation Request), há um relacionamento OneToMany e ManyToMay entre elas, via anotações próprias e, que 
permitem que ao requisitar um pedido de férias, usuário e o pedido de férias gerado, ficam relacionados e esse dado é salvo na 
tabela de dados entidade Vacatiopn Request, e podem ser buscados via GET, relacionados conforme a necessidade.

A busca de um pedido de férias já cadastrado, deverá ser via Id do pedido, e somente trará em tela, os pedidos com status
CREATED, ONGOING, CONCLUDED.

Também usaremos o id do pedido de férias para solicitar a requisição de cancelamento (inativação) de um pedido de férias.

</div>

---
## Como executar o Projeto

---

Pré-requisitos: Java 11

```bash
1. clonar repositório
git clone https://github.com/anacmaciel/projeto-final-catalisa.git

2. entrar na pasta do projeto
cd src

3. executar o projeto

```

---
Operando a Aplicação

Para cadastrar um novo usuario User, exclusivamente para o acesso tipo ADMIN, os dados de entrada na requisição Post, 
seguindo o exemplo ficticio abaixo, deverá estar com a seguinte configuração:

```
path (localhost:8080/users)
```

```Json
{
"name": "teste1231",
"email": "teste@egmail.com",
"birthDate": "2002-10-31",
"hiringDate": "2022-10-24",
"profileEnum": "ADMIN"
}
```


Para cadastrar um pedido de férias Vacation Request, os dados de entrada na requisição POST, seguindo o exemplo
ficticio abaixo, deverá estar com a seguinte configuração:

```
path (localhost:8080/user/vacationsrequest)
```

```Json
{
"vacationDays": 10,
"startAt": "2022-12-23",
"email": "teste@gmail.com"
}
```


Para consultar um usuário específico já cadastrado via acesso EMPLOYEE ou ADMIN, os dados de entrada na requisição GET, seguindo 
o exemplo ficticio abaixo, deverá estar com a seguinte configuração:

```
path (localhost:8080/users/emailDoUsuario)
```

Para consultar um usuário já cadastrado pelo acesso ADMIN, os dados de entrada na requisição GET, seguindo o exemplo ficticio
abaixo, deverá estar com a seguinte configuração:

```
path (localhost:8080/users)
```

Para inativar um usuario USER já cadastrado pelo acesso ADMIN, os dados de entrada na requisição DELETE, seguindo o exemplo ficticio
abaixo, deverá estar com a seguinte configuração:

```
path (localhost:8080/users/inactive/emailDoUsuario)
```

Para inativar um pedido de férias já cadastrado pelo acesso ADMIN, os dados de entrada na requisição DELETE, utilizar o id do pedido
de ferias, seguindo o exemplo ficticio abaixo, deverá estar com a seguinte configuração:

```
path (localhost:8080/vacationsrequest/cancel/id)
```


---
## Lay-out da aplicação

---


---
## Modelo Conceitual (diagramas)

---


---
## Agradecimentos

---


- Programa Catalisa ZUP
- Giovana
- Professores, Carol, Joy e Crispim
- Mentores Guil, Raphael, Graziela, Thiago Barros











