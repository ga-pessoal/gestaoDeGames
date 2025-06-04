## Trabalho de Beatriz Schmidt da Silva e Gabriel Costa de Mattos

# Descrição
Este projeto é uma aplicação web desenvolvida em Java utilizando JSP, Servlets e banco de dados MySQL. Segue o padrão MVC para organização do código.

## Pré-requisitos
- JDK 11 ou superior (recomendo JDK 17+)
- Apache Tomcat 9 ou superior (ou outro servidor servlet compatível)
- MySQL Server instalado e em execução
- IDE (IntelliJ)

## Configuração do Banco de Dados
1. Crie um banco de dados MySQL executando o script do arquivo:

```
base_de_dados.sql
``` 

2. Importe o script de criação das tabelas e dados (se disponível):

```bash
mysql -u seu_usuario -p nome_do_banco < caminho/para/script.sql
```

3. Atualize o arquivo de configuração do projeto (ex: context.xml, web.xml ou arquivo de propriedades) com os dados do seu banco:
```
db.url=jdbc:mysql://localhost:3306/nome_do_banco
db.user=seu_usuario
db.password=sua_senha
```

## Como rodar o projeto
### Passo 1: Configurar o servidor
- Copie o projeto para o diretório de deploy do Tomcat (webapps) ou configure sua IDE para executar no servidor configurado.

### Passo 2: Build e deploy
- Compile o projeto (se estiver usando Maven, rode mvn clean install).
- Faça o deploy do .war gerado para o Tomcat ou use a funcionalidade de deploy da sua IDE.

### Passo 3: Inicie o servidor
- Execute o Tomcat.
- Acesse a aplicação pelo navegador em:
```
http://localhost:8080/GestaoDeGames/
```