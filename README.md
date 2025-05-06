
## Toomcat
Baixe o [Tomcat 10.1+](https://tomcat.apache.org/download-10.cgi)

Extraia em uma pasta, por exemplo: 
> C:\tomcat-10.1.20

Copie o .war gerado (MeuProjetoWeb.war) para a pasta:
> C:\tomcat-10.1.20\webapps
 
### Inicie o Toomcat
No terminal/cmd, vá até a pasta bin do Tomcat e execute:

No Windows:
``` bash
startup.bat
```

## Build
Na pasta raiz do projeto, para fazer a build do mesmo rode o comando:
``` bash
mvn clean package
```
Depois mova o arquivo war gerado para a pasta web app do toomcat utilizando o comando:
``` bash
copy .\target\GestaoDeGames.war \apache-tomcat-10.1.40\webapps\
```