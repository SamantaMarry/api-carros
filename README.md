
<h1 align="center">Carros - API :octocat: </h1>
<p>
  <img alt="Version" src="https://img.shields.io/badge/version-1.0.0-green.svg?cacheSeconds=2592000" />
</p>

>Desenvolvimento de API REST 

<div align-itens="center">
  
>Framework utilizado:  ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
  
</div>

## Descrição
<p>
Criação de método POST para importação de planilha contendo informações de montadoras e veículos.
Criação de duas tabelas no banco de dados H2, sendo uma para montadoras e outra para veículos.
</p>


## Como rodar

 1. Clonar o repositório;
 2. Abrir IDE na pasta do projeto e rodar;
 3. Fazer requisição:

#### Exemplo utilizando Postman
 1. POST para obter token: http://localhost:8080/oauth/token
 2. Em Authorization, selecione o type Basic Auth e insira o Username **"admin"** e Password **"123"**;
 3.  Em Body utilizar "*form-data*" com os valores abaixo:
>   **Key**: grant_type  | **Value**: password
>   **Key**: username   |  **Value**: admin
>   **Key**: password   |  **Value**: 123
 
> **Retorno**

```
{

"access_token":  "BhzQDG/x/FxxXPUbHnS0y7EacEw=",
"token_type":  "bearer",
"refresh_token":  "aOTOJ4Rg4KJUD6BA899+dXAajKs=",
"expires_in":  43199,
"scope":  "password"

}
```

 4. Copiar "*access_token*" e colar em  Authorization, type Bearer Token;
 5. Em Body utilizar "*form-data*" com os valores abaixo:
 >   **Key**: file   | **Value**: enviar arquivo
 6. enviar a requisição na url: http://localhost:8080/cars
 > **Retorno**: Status 200(ok)

---
## cURL
**Request token**:

    curl --location --request POST 'http://localhost:8080/oauth/token' \ --header 'Authorization: Basic YWRtaW46MTIz' \ --form 'grant_type="password"' \ --form 'username="samanta"' \ --form 'password="123"'

**Envio de arquivo**:

    curl --location --request POST 'http://localhost:8080/cars' \ --header 'Authorization: Bearer k4UC7Lf14TeIF1RyeNRw1G3eBBs=' \ --form 'file=@"/path/to/file"'
---

 

## Author

:raising_hand_woman: **Samanta Marry**

* Github: [@samantamarry](https://github.com/samantamarry)

