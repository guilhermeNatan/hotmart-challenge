# Hotmart-challenge
Autor: Guilherme Natan Barbosa Alecrim

## Como executar o projeto 

### Pré-requisitos 
 - maven 3.6 ou superior configurado 
 - jdk 1.8.0_191 
 
1. Faça download do repositório 
 ```git clone https://github.com/guilhermeNatan/hotmart-challenge.git``` 
1. Dentro da pasta hotmart-challenge execute o comando  ```java -jar executable.jar```


OBS:  para atender o requisito de no mínimo 100 linhas por tabela foi criado um DataLoader 
para inserir os dados iniciais portanto na primeira vez que a aplicacao for executada pode ser
que demore alguns segundos. 


### Acessando a  base de dados 
Para facilitar a execução da apliação pelos avaliadores escolhi utilizar o H2 que é uma base
de dados relacional que pode ser embarcada junto com a  aplicação: 

Para acessar os dados basta executar a aplicação e acessar a seguinte url http://localhost:8080/h2-console 
Save Settings: Generic H2
Setting Name: Generic H2
JDBC URL : jdbc:h2:mem:desafiodb;DB_CLOSE_DELAY=-1
username: sa
password: sa


### Executando os testes de unidade 
Para executar a bateria de testes execute o seguinte comando dentro da raiz do projeto
```mvn test```


Foram criados testes de persistencia para todas as entidades a fim de validar os relacionamentos entre as entidades estavam 
corretos, mas também principalmente testes para validar o cálculo de score. 
Todos os testes econtram-se dentro de diretorio ```test.java.com.desafio.hotmart.entity```



### Métodos da API 
A documentação da api pode ser acessada no seguinte link
https://documenter.getpostman.com/view/2425100/TVKD2cr4#intro 

Foram criados os métodos  na classe ProductController
* product/list 
* product/insert 
* product/update/{id}
* product/find
* product/delete/{id}


### Estratégia de consumo da api de notícias e atualização dos scores
O método  NewsService:insertNewsIfNotExist garante que apenas notícias que ainda não exitem na base de dados
afim de evitar duplicidade e também é verificado se a data de publicação da notícia corresponde ao dia corrente, pois 
não faz sentido persistir noticias que não serão consideradas no calculo do score . 

Sempre que noticia é inserida na base de dados é atualizado o score de todos os produtos que tem a mesma categoria da   notícia 


Foi criado o ScheduledTasks:consumingNewsAPIScheduler que irá consumir a api de noticia 4 vezes ao dia
também a fim de limpar a base de dados foi criado o job ScheduledTasks:removeOldNews que irá executar uma vez por semana 
para excluir as noticias mais antigas.
 
 
### Tratamento de exeção 
Dentro do pacote exception foi criado o ExceptionHandler  para tratar as validações de requisição 
dentro do arquivo messages.propeties estão registrados todas a mensagens de exceção. 



### Arquitetura usada
Incluir imagem diagrama 

 - como os dados são auditados  
  - Factory explicar uso do save
  - Falar sobre os testes 
  
  
### Das tecnologias utilizadas no processo 
- Banco de dados: H2 
- IDE:  Intellij IDEA
- Teste da API rest: Postman 
- Gerenciador de depencias: Maven 


 ### Melhorias que podem ser implementadas
 - A api de noticias trabalha com um conjunto específico de categorias pode ser utilizado 
 uma Enum em vez de uma entidade  para representar as categorias
