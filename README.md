# VACATION-MANAGER-projeto-final
Projeto final do programa catalisa

# Objetivo desta aplicação (Conceitual)

A Vacation Manager é um projeto que consiste em desenvolver uma aplicação, 
que permite a gestão de solictações de gozo à férias dentro de uma organização, objetivando um fluxo mais 
intuitivo, fácil e fluído, possibilitanto ao gestor e ao colaborador, de forma global, através de acessos seus especificos,
fazer o procedimento.

# Regra de Negócio

Regras Globais 
 Através da aplicação, deve ser possível:

*-Cadastrar um novo usuário.*
-Um ADMIN buscar os dados de qualquer usuário.
-Um EMPLOYEE buscar os seus dados.
-Um ADMIN atualizar os dados de qualquer usuário.
-Um EMPLOYEE atualizar seus próprios dados (mas não de outros usuários).
-Um ADMIN inativar os dados de um usuário.
-Um usuário criar um pedido de férias.
-Um usuário buscar seus pedidos de férias.
-Um usuário atualizar seu pedido de férias.
-Um usuário cancelar seu pedido de férias.
-O recurso de busca de usuários não deverá trazer usuários INACTIVE.
-Deverá ter telas de Front-End básicas para usar a aplicação.
-A aplicação deverá retornar mensagens amigáveis para as eventuais exceções retornadas pela aplicação.
-Deverão ser usados Http Status adequados para cada resposta.
-Um usuário com estado INACTIVE ou ON_VACATION não poderá editar seus dados, nem seus pedidos de férias.
-Ao cancelar um pedido de férias, o status do pedido deverá ser atualizado para CANCELED.
-Um pedido com estado CANCELED não deverá ser listado nos recursos de busca (de pedidos ou de usuários).

**Especificas para as Classes**

**Classe User**

Atributos da Entidade User:
-id: identificador do usuário (Long/String?) - Único
-name: nome do usuário (String)
-email: email do usuário (String) - Único
-birthDate: data de nascimento do usuário (LocalDate)
-hiringDate: data de contratação do usuário (LocalDate)
-daysBalance: saldo de dias de férias disponíveis (Integer)
-profile: perfil do usuário (Enum)
-status: estado no qual se encontra o usuário (Enum)
-vacationRequests: lista dos pedidos de férias do usuário (List<VacationRequest>)

Especificações

-Existirá (inicialmente) dois perfis de usuários: ADMIN e EMPLOYEE, determinados no cadastro dos usuários.
-O saldo de dias de férias não poderá ser informado no cadastro.
-Não deve ser possível cadastrar um usuário com uma data de contratação futura.
-Não deve ser possível cadastrar (inicialmente) um usuário com menos de 18 anos.
-O campo nome deve conter no mínimo 5 caracteres.
-O formato do email deve ser validado no cadastro.
-Não pode ter dois usuários cadastrados com o mesmo email.
-Um usuário não pode ter mais de 60 dias de saldo de férias.
-Existirá (inicialmente) três tipos de usuários: ACTIVE, ON_VACATION e INACTIVE.

**Classe Vacation Request**

Atributos da Entidade Vacation Request:
-id: identificador do pedido (Long/String?) - Único
-userId: identificador do usuário pedindo férias (Long)
-vacationDays: quantidade de dias de férias usados no pedido (Integer)
-startAt: dia de início das férias (LocalDate)
-endAt: dia de volta ao escritório (LocalDate)
-status: estado no qual se encontra o pedido de férias (Enum)

Especificações

-O usuário não poderá pedir menos de 5 dias de férias.
-O usuário não poderá pedir férias caso não tenha um saldo de dias suficiente.
-O usuário não poderá pedir férias com menos de 45 dias de antecedência.
-Só um usuário com estado ACTIVE pode gerenciar seus pedidos de férias.
-A data de início das férias (startAt) não poderá coincidir com um fim de semana.
-A data de volta ao escritório (endAt) não poderá coincidir com um fim de semana (Exemplo: caso as férias acabem um sábado, a volta ao escritório deveria ser na segunda-feira seguinte).
-Existirá (inicialmente) três tipos de estado de pedido de férias: CREATED, ONGOING, CONCLUDED e CANCELED, atualizados automaticamente à medida que o calendário avance.
-Um pedido só poderá ser alterado ou cancelado até 7 dias antes do início do período de férias.
-Um pedido cancelado deverá retornar o saldo de dias para o usuário.

# Versão da Aplicação

Versao 1 ...

# Explicação Técnica

A aplicação é composta por duas entidades: USER e VACATIONREQUEST.

Os atributos de cada entidade estão listadas no campo acima, "Regra de Negócio".

Na entidade User temos a possibilidade de CRUD's que permitem o cadastro de um novo usuário, a atualização de um usuário já cadastrado,
e a busca por registros de usuários cadastrados ou apenas um especifico, apenas com perfil ativo ou  de férias e, utilizando o atributo email, 
para requsitar as buscas. Também usaremos o e-mail para solicitar a requisição de cancelamento (inativação) do usuário.

Todo o fluxo parte de uma requisição via Front-End, na qual um formulário front-end recebe os dados que serão comunicados para o 
Back-end onde a classe Controller recebe a requisição (Get/Post/Put/Delete) em uma imagem DTO, passando para camada service/repository/model, onde se dá 
o processamento dos dados de entrada serão analisados e persisitidos ou não, devolvendo as respostas adequadas a cada tipo de requisição recebida.

Para o cadastro de usuario, serão validados dados obrigatorios conforme a regra de negocio, como a idade, a data de admissão, o email,
que, em caso de inadequações, conforme regras inseridas em cada metodo, serão recusadas e nao cadastrará um novo usuario, sempre repostando uma mensagem orientativa.


O mesmo fluxo, ocorre para a entidade Vacation Request com o devido processo e fluxo especifico para esta. 

Todo o fluxo para pedido de férias (Vacation Request), parte de uma requisição via Front-End, na qual um formulário front-end 
recebe os dados que serão comunicados para o Back-end, onde a classe Controller recebe a requisição (Get/Post/Put/Delete) em uma
imagem DTO, passando para camada service/repository/model, em que se dará o processamento dos dados de entrada, uma analise conforme
as regras de negocio e,em seguida, persisitidos ou não, devolvendo as respostas adequadas a cada tipo de requisição recebida.

Para o cadastro de Pedido de Férias, serão validados dados obrigatórios conforme a regra de negócio, como a quantidade minima de dias 
de férias, prazo minimo de dias antecedendo ao dia do inicio de férias, e que em caso de inadequações, conforme regras inseridas em 
cada metodo, serão recusadas e nao cadastrará um novo pedido de férias, sempre repostando uma mensagem orientativa.

Essas entidades, User e Vacation Request se relacionam entre si por anotações próprias que permitem que ao requisitar um pedido 
de férias ficam relacionados o pedido de férias ao seu respectivo usuario, e esse dado é salvo na entidade Vacatiopn Request, porem 
podem ser relacionados com o metodo de busca adequado.

# Conclusão









