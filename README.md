# Hotmart-challenge
Autor: Guilherme Natan Barbosa Alecrim

## Como executar o projeto 

### Pré-requisitos 
 - maven 3.6 ou superior configurado 
 - jdk 1.8.0_191 

### Executando  
1. Faça download do repositório 
 ```git clone https://github.com/guilhermeNatan/hotmart-challenge.git``` 
1. Dentro da pasta hotmart-challenge execute o comando  ```java -jar executable.jar```


OBS:  para atender o requisito de no mínimo 100 linhas por tabela foi criado um DataLoader 
para inserir os dados iniciais portanto na primeira vez que a aplicação for executada pode ser
que demore alguns segundos. 


## Acessando a  base de dados 
Para facilitar a execução da aplicação pelos avaliadores escolhi utilizar o H2 que é uma base
de dados relacional que pode ser embarcada junto com a  aplicação: 

Para acessar os dados basta executar a aplicação e acessar a seguinte url http://localhost:8080/h2-console 

Save Settings: Generic H2

Setting Name: Generic H2

JDBC URL : jdbc:h2:mem:desafiodb;DB_CLOSE_DELAY=-1

username: sa

password: sa


## Executando os testes de unidade 
Para executar a bateria de testes execute o seguinte comando dentro da raiz do projeto
```mvn test```


Foram criados testes de persistência para todas as entidades a fim de validar os relacionamentos entre as entidades estavam 
corretos, mas também principalmente testes para validar o cálculo de score. 
Todos os testes encontram-se dentro de diretório ```test.java.com.desafio.hotmart.entity```

Os testes de unidades utilizam as factories criadas dentro do pacote factory, 
Toda factory implementa um método create que recebe um boolean indicando se uma entidade deve ou não ser persistida no banco
esse boolean pode ser passado como false para testes que nao dependem de persistir alguma informação no banco de dados 
dessa forma a execução dos testes pode ser otimizada. 

## Métodos da API 
A documentação completa da api pode ser acessada no seguinte link
https://documenter.getpostman.com/view/2425100/TVKD2cr4#intro 

Foram criados os métodos  na classe ProductController
* product/list : lista todos os produtos com paginação
* product/insert : adiciona um novo produto
* product/update/{id} : atualiza os dados de um determinado produto
* product/find : busca um produto na base de dados pelo nome, a lista retonada contem os produtos ordenados pelo score, nome e categoria , o retorno também tem suporte a paginação
* product/delete/{id}: remove um produto 


## Arquitetura
![Alt text](/desafio.png "Diagrama de entidades ") 

Toda entidade extends da classe BaseEntity, que possui os atribuitos id, version , createAt , lastUpdate, 
sendo os dois  últimos utilizados para auditar os dados, futuramente pode ser incluindo 
um atributo createdBy para relacionar o usuário que criou determinado registro. A configuração de auditoria 
foi feita na classe AuditingConfig 

#### Pacotes 

**Entity:** Contêm todas as entidades utilizadas na modelagem do problema. Toda entidade 
herda da classe BaseEntity esta que contêm atributos de auditoria, 
além de id e versão de um registro, o atributo versao pode ser utilizado para fazer 
tratramentos de acesso concorrente. 

**Reuse.Factory**: Dentro do pacote factory foram implementados fábricas para criar instâncias de cada 
entidade essas fábricas podem ser usadas tanto no contexto de teste de unidade 
quando no contexto da aplicação. 

**Reuse.Util:** Classes utilitárias 

**Repository:** Contêm todos os repositórios estes que implementam as regras de acesso ao banco de dados 

**Service:** Contem todos os services  e implementam regras de negócio como por exemplo validações que envolvem
mais de uma entidade.  
 
**Controllers:** estão no pacote controller e implementa os endpoints de acesso, a lógica de regras 
de negócio devem ser delegadas para algum service ou repository. 

**Exception:** Contêm as exceções customizadas.   

**Schedulers:** Contêm os jobs que serão executados de tempos em tempos como por exemplo o consumo da api de notícias. 

**Configuration:** Configurações do projeto. 


## Tratamento de exceção 
Dentro do pacote exception foi criado o ExceptionHandler  para tratar as validações de requisição 
dentro do arquivo messages.propeties estão registrados todas a mensagens de exceção. 


## Estratégia de consumo da api de notícias e atualização dos scores
Foi criado o ScheduledTasks:consumingNewsAPIScheduler que irá consumir a api de noticia 4 vezes ao dia
também a fim de limpar a base de dados foi criado o job ScheduledTasks:removeOldNews que irá executar uma vez por semana 
para excluir as noticias mais antigas.
 
 
O método  NewsService:insertNewsIfNotExist garante que apenas notícias que ainda não existem na base de dados
afim de evitar duplicidade e também é verificado se a data de publicação da notícia corresponde ao dia corrente, pois 
não faz sentido persistir noticias que não serão consideradas no cálculo do score . 

Sempre que noticia é inserida na base de dados é atualizado o score de todos os produtos que tem a mesma categoria da   notícia 

Foi verificado que é possível fazer uma chamada para api de noticias passando a categoria como parametro dessa forma apenas 
as noticias de uma determinada categoria são retornadas, de acordo com a documentaçao da api existem as seguintes categorias 
business , entertainment, general ,health,science, sports,technology,  a cada execução do job são feitos 7 chamadas 
na api de noticias, uma para cada categoria.  

 
  
### Das tecnologias utilizadas
- Banco de dados: H2  (https://www.h2database.com/html/main.html)
- IDE:  Intellij IDEA (https://www.jetbrains.com/idea/promo/ultimate/?gclid=CjwKCAjwwab7BRBAEiwAapqpTDTCRbK2d8oCcHZuscQZAW9EayYBAXtT9n58ayiWwafcDp-9OvdxwhoCeD8QAvD_BwE)
- Teste da API rest: Postman  (https://www.postman.com/)
- Gerenciador de dependência: Maven 3.6  (https://maven.apache.org/)
- Lombok 
