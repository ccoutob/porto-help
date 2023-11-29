<h1 align="center">
🆘<br>Porto Help
</h1>

> Repositório dedicado ao projeto Porto Help (Java) - desenvolvido para atender as demandas do Challenge com a Porto Seguro pelo primeiro ano em ADS na FIAP!

<h2>📝Desafio</h2>
> Melhorar a assertividade da escolha de modal (tipo de guincho) para atendimento de veículos pesados.
<br>
> Realizar a escolha de modal sem esforço operacional humano.

<h2 name="objetivo">🎯 Objetivos do Projeto</h2>
 ●  Automatizar seleção de modal sem necessidade de interação humana
 <br>
 ●  Criação do registro de cliente
 <br>
 ●  Criação do registro do veículo, carga e apólice
 <br>
 ●  Integração das informações em um banco de dados, por meio de requisições de API

<h2>📖 Sobre o Challenge</h2>

| _Challenge 3_ |                                       |
| ------------- | ------------------------------------- |
| _Curso_       | Análise e Desenvolvimento de Sistemas |
| _Disciplina_  | Domain Driven Design                  |
| _Professor_   | Thiago Toshiyuki Izumi Yamamoto       |
| _Turma_       | 1TDSS                                 |

<h2 name="endpoints">🌐 Endpoints</h2>

### ❗ PS: Lembre sempre do /api antes de cada endpoint

### 🚛 Veículo

| Método | Endpoint                     | Descrição                |
| ------ | ---------------------------- | ------------------------ |
| GET    | /api/veiculo                 | Listar todos os veiculos |
| GET    | /api/veiculo/&lt;id&gt;      | Buscar veiculo pelo id   |
| POST   | /api/veiculo                 | Cadastrar um veiculo     |
| PUT    | /api/veiculo/&lt;id&gt;      | Atualizar um veiculo     |
| DELETE | /api/veiculo/&lt;id&gt;      | Deletar um veiculo       |

### 📃 Apólice
| Método | Endpoint                     | Descrição                |
| ------ | ---------------------------- | ------------------------ |
| GET    | /api/apolice                 | Listar todas as apolices |
| GET    | /api/apolice/&lt;id&gt;      | Buscar apolice pelo id   |
| POST   | /api/apolice                 | Cadastrar uma apolice    |
| PUT    | /api/apolice/&lt;id&gt;      | Atualizar uma apolice    |
| DELETE | /api/apolice/&lt;id&gt;      | Deletar uma apolice      |

### 📦 Carga

| Método | Endpoint                     | Descrição            |
| ------ | ---------------------------- | -------------------- |
| GET    | /api/carga                   | Listar todas as carga|
| GET    | /api/carga/&lt;id&gt;        | Buscar carga pelo id |
| POST   | /api/carga                   | Cadastrar uma carga  |
| PUT    | /api/carga/&lt;id&gt;        | Atualizar uma carga  |
| DELETE | /api/carga/&lt;id&gt;        | Deletar uma carga    |

###❗️Dados adicionais para a funcionalidade do projeto
● O Porto Help foi feito com o servidor tomcat na versão 10.1
<br>
● Os dados devem ser passados ao POSTMAN para realizar a requisição na Web e Banco de dados

### 🧑🏻‍💻 Autor 
> Cauã Couto Basques - Turma 1TDSS

