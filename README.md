## Tecnologias utilizadas
- Java (Servlets e JSP)
- Apache Tomcat (servidor web)
- JDBC (conexão com banco de dados)
- MySQL (banco relacional)
- IDE: IntelliJ IDEA
- Maven

## Toomcat
Baixe o [Tomcat 10.1+](https://tomcat.apache.org/download-10.cgi)

Extraia em uma pasta, por exemplo: 
> C:\tomcat-10.1.20
 
### Inicie o Toomcat
No terminal/cmd, vá até a pasta bin do Tomcat e execute:
No Windows:
``` bash
startup.bat
```

## Build
Na pasta raiz do projeto, instale as dependências do projeto rode o comando:
``` bash
mvn clean install
```
Depois de instalar as dependências, para fazer a build do mesmo rode o comando:
``` bash
mvn clean package
```
Depois mova o arquivo war gerado para a pasta webapp do toomcat utilizando o comando:
``` bash
copy .\target\GestaoDeGames.war \apache-tomcat-10.1.40\webapps\
```

## Acessar
Para acessar o sistema basta usar essa url no navegador
> http://localhost:8080/MeuProjetoWeb/usuarios