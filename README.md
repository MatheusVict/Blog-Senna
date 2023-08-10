<h1 align="center">
  Blog Senna API
</h1>

> API do blog Senna que fala sobre automobilismo, onde os usuários podem ver vários posts sobre varias categorias de esporte.
> Este projeto é uma reimaginação do primeiro projeto que fiz o blog senna, quando estava no inicio da carreira.
> Aprendi com os maiores desafios que foram Usar o TDD e mater um arquitetura limpa

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Webflux](https://docs.spring.io/spring-framework/reference/web/webflux.html)
- [Spring Data + PostreSQL](https://docs.spring.io/spring-framework/reference/data-access/jdbc.html)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [Spirng Security](https://spring.io/guides/gs/securing-web/)
- [JWT](https://jwt.io/)

## Práticas adotadas

- SOLID
- Clean architecture
- Testes automatizados
- Consultas com filtros dinâmicos usando o Query By Example
- Uso de DTOs para a API
- Injeção de Dependências
- Geração automática do Swagger com a OpenAPI 3
- Auditoria sobre criação e atualização da entidade

## Como Executar

### Localmente
- Clonar repositório git
- Construir o projeto:

```
./mvnw clean package
```
- Executar:
```
mvn spring-boot:run
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Usando Docker

- Pode usar a imagem oficial do docker hub usando o comando:

```
docker run matheusvict/blog-senna
```
- Pode usar o docker compose
```
docker compose up
```

ou

- Clonar repositório git
- Construir o projeto:
```
./mvnw clean package
```
- Construir a imagem:
```
./mvnw spring-boot:build-image
```
- Executar o container:
```
docker run --name blog-senna -p 8080:8080  -d blog-senna:0.0.1-SNAPSHOT
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Se torne um contribuidor


Para contribuir com <nome_do_projeto>, siga estas etapas:

1. Bifurque este repositório.
2. Crie um branch: `git checkout -b <nome_branch>`.
3. Faça suas alterações e confirme-as: `git commit -m '<mensagem_commit>'`
4. Envie para o branch original: `git push origin <nome_do_projeto> / <local>`
5. Crie a solicitação de pull.

Como alternativa, consulte a documentação do GitHub em [como criar uma solicitação pull](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request).


## API Public Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Insominia](https://insomnia.rest/):

### Auth

- POST /auth/login
```json
{
  "email": "user@email.com",
  "password": "123456"
}
```

### Users

- POST /auth/register
```json
{
  "name": "Matheus Victor",
  "email": "user@email.com",
  "password": "123456"
}
```

- GET /users/{id}
```json
{
  "id": 0,
  "name": "string",
  "email": "string",
  "role": "ADMIN",
  "enabled": true,
  "authorities": [
    {
      "authority": "string"
    }
  ],
  "username": "string",
  "accountNonExpired": true,
  "accountNonLocked": true,
  "credentialsNonExpired": true
}
```

### Posts

- GET /posts
```json
[
  {
    "id": 0,
    "title": "string",
    "slug": "string",
    "description": "string",
    "content": "string",
    "category": {
      "id": 0,
      "name": "string",
      "description": "string",
      "slug": "string"
    },
    "user": {
      "id": 0,
      "name": "string",
      "email": "string",
      "role": "ADMIN",
      "enabled": true,
      "authorities": [
        {
          "authority": "string"
        }
      ],
      "username": "string",
      "accountNonExpired": true,
      "accountNonLocked": true,
      "credentialsNonExpired": true
    }
  }
]
```

- GET /posts/byCategory?slug={categorySlug}
```json
[
  {
    "id": 0,
    "title": "string",
    "slug": "string",
    "description": "string",
    "content": "string",
    "category": {
      "id": 0,
      "name": "string",
      "description": "string",
      "slug": "string"
    },
    "user": {
      "id": 0,
      "name": "string",
      "email": "string",
      "role": "ADMIN",
      "enabled": true,
      "authorities": [
        {
          "authority": "string"
        }
      ],
      "username": "string",
      "accountNonExpired": true,
      "accountNonLocked": true,
      "credentialsNonExpired": true
    }
  }
]
```


- GET /posts/slug/{slug}
```json
{
    "id": 0,
    "title": "string",
    "slug": "string",
    "description": "string",
    "content": "string",
    "category": {
      "id": 0,
      "name": "string",
      "description": "string",
      "slug": "string"
    },
    "user": {
      "id": 0,
      "name": "string",
      "email": "string",
      "role": "ADMIN",
      "enabled": true,
      "authorities": [
        {
          "authority": "string"
        }
      ],
      "username": "string",
      "accountNonExpired": true,
      "accountNonLocked": true,
      "credentialsNonExpired": true
    }
  }
```
- GET /posts/id/{id}
```json
{
    "id": 0,
    "title": "string",
    "slug": "string",
    "description": "string",
    "content": "string",
    "category": {
      "id": 0,
      "name": "string",
      "description": "string",
      "slug": "string"
    },
    "user": {
      "id": 0,
      "name": "string",
      "email": "string",
      "role": "ADMIN",
      "enabled": true,
      "authorities": [
        {
          "authority": "string"
        }
      ],
      "username": "string",
      "accountNonExpired": true,
      "accountNonLocked": true,
      "credentialsNonExpired": true
    }
  }
```

GET /posts/pageable

```json
{
  "totalPages": 0,
  "totalElements": 0,
  "size": 0,
  "content": [
    {
      "id": 0,
      "title": "string",
      "slug": "string",
      "description": "string",
      "content": "string",
      "category": {
        "id": 0,
        "name": "string",
        "description": "string",
        "slug": "string"
      },
      "user": {
        "id": 0,
        "name": "string",
        "email": "string",
        "role": "ADMIN",
        "enabled": true,
        "authorities": [
          {
            "authority": "string"
          }
        ],
        "username": "string",
        "accountNonExpired": true,
        "accountNonLocked": true,
        "credentialsNonExpired": true
      }
    }
  ],
  "number": 0,
  "sort": {
    "empty": true,
    "unsorted": true,
    "sorted": true
  },
  "pageable": {
    "offset": 0,
    "sort": {
      "empty": true,
      "unsorted": true,
      "sorted": true
    },
    "paged": true,
    "unpaged": true,
    "pageNumber": 0,
    "pageSize": 0
  },
  "numberOfElements": 0,
  "first": true,
  "last": true,
  "empty": true
}
```

### Categories

GET /categories
```json

[
  {
    "id": 0,
    "name": "string",
    "description": "string",
    "slug": "string"
  }
]
```

GET /categories/{slug}
```json

[
  {
    "id": 0,
    "name": "string",
    "description": "string",
    "slug": "string"
  }
]
```

GET /categories/pageable
```json

{
  "totalPages": 0,
  "totalElements": 0,
  "size": 0,
  "content": [
    {
      "id": 0,
      "name": "string",
      "description": "string",
      "slug": "string"
    }
  ],
  "number": 0,
  "sort": {
    "empty": true,
    "unsorted": true,
    "sorted": true
  },
  "pageable": {
    "offset": 0,
    "sort": {
      "empty": true,
      "unsorted": true,
      "sorted": true
    },
    "paged": true,
    "unpaged": true,
    "pageNumber": 0,
    "pageSize": 0
  },
  "numberOfElements": 0,
  "first": true,
  "last": true,
  "empty": true
}
```
