# GraphQL Queries e Responses

Este documento organiza e explica todas as queries e mutations utilizadas durante os testes de criação de **Post**, **Comment**, e os comportamentos relacionados ao polimorfismo entre `TextComment` e `ImageComment`.

---

## 📝 **1. Mutation: Criar Post**

### **Query**

```graphql
mutation {
  createPost(content: "Meu primeiro post") {
    id
    content
  }
}
```

### **Response**

```json
{
  "data": {
    "createPost": [
      {
        "id": "042e0889-da0f-4ca4-8524-ddefc2b04e41",
        "content": "Meu primeiro post"
      }
    ]
  }
}
```

### **Explicação**

Cria um novo Post contendo apenas `id` e `content`. O retorno é uma **lista de Posts**, conforme definido no schema (`[Post]`).

---

## 📝 **2. Mutation: Criar Comment (sem imagem)**

### **Query**

```graphql
mutation {
  createComment(
    content: "Meu primeiro comment sem imagem"
    postId: "042e0889-da0f-4ca4-8524-ddefc2b04e41"
  ) {
    id,
    content,
    postId
  }
}
```

### **Response**

```json
{
  "data": {
    "createComment": [
      {
        "id": "314661f4-ca43-4b63-b8ea-a4144b0935ed",
        "content": "Meu primeiro comment sem imagem",
        "postId": "042e0889-da0f-4ca4-8524-ddefc2b04e41"
      }
    ]
  }
}
```

### **Explicação**

Como não foi enviado `imageUrl`, o backend cria um **TextComment**. Campos opcionais ficam como `null` e não aparecem na resposta. O retorno também é uma **lista de Comment**.

---

## 📝 **3. Mutation: Criar Comment (com imagem)**

### **Query**

```graphql
mutation {
  createComment(
    content: "Meu primeiro comment com imagem"
    postId: "042e0889-da0f-4ca4-8524-ddefc2b04e41"
    imageUrl: "http://localhost/img.png"
  ) {
    id,
    content,
    postId
    ... on ImageComment {
      imageUrl
    }
  }
}
```

### **Response**

```json
{
  "data": {
    "createComment": [
      {
        "id": "314661f4-ca43-4b63-b8ea-a4144b0935ed",
        "content": "Meu primeiro comment sem imagem",
        "postId": "042e0889-da0f-4ca4-8524-ddefc2b04e41"
      },
      {
        "id": "ad3ce86f-c565-4568-a1ea-67cf377eaca6",
        "content": "Meu primeiro comment com imagem",
        "postId": "042e0889-da0f-4ca4-8524-ddefc2b04e41",
        "imageUrl": "http://localhost/img.png"
      }
    ]
  }
}
```

### **Explicação**

Aqui o backend identifica que `imageUrl` foi informado e cria um **ImageComment**.
O uso do fragmento:

```graphql
... on ImageComment { imageUrl }
```

é obrigatório, já que `imageUrl` não existe na interface `Comment`.

O retorno inclui **todos os comments já criados**, pois o resolver devolve `comments.values()`.

---

## 📝 **4. Query: Buscar Post por ID com Comments polimórficos**

### **Query**

```graphql
query {
  postById(id: "042e0889-da0f-4ca4-8524-ddefc2b04e41") {
    id
    content
    comments {
      ... on TextComment {
        id
        content
        postId
      }
      ... on ImageComment {
        id
        content
        imageUrl
        postId
      }
    }
  }
}
```

### **Response**

```json
{
  "data": {
    "postById": {
      "id": "042e0889-da0f-4ca4-8524-ddefc2b04e41",
      "content": "Meu primeiro post",
      "comments": [
        {
          "id": "314661f4-ca43-4b63-b8ea-a4144b0935ed",
          "content": "Meu primeiro comment sem imagem",
          "postId": "042e0889-da0f-4ca4-8524-ddefc2b04e41"
        },
        {
          "id": "ad3ce86f-c565-4568-a1ea-67cf377eaca6",
          "content": "Meu primeiro comment com imagem",
          "imageUrl": "http://localhost/img.png",
          "postId": "042e0889-da0f-4ca4-8524-ddefc2b04e41"
        }
      ]
    }
  }
}
```

### **Explicação**

Esta query demonstra polimorfismo real em GraphQL:

* `TextComment` retorna somente campos da interface.
* `ImageComment` retorna `imageUrl` porque foi solicitado com fragmento.

Isso confirma que sua interface GraphQL está funcionando corretamente com tipos concretos.

---

# 📌 Resumo Geral

* `createPost` cria posts simples retornando uma lista.
* `createComment` decide automaticamente entre `TextComment` e `ImageComment`.
* `imageUrl` só pode ser acessado usando fragmentos porque não pertence à interface `Comment`.
* `postById` demonstra o uso correto de fragmentos para lidar com tipos polimórficos.

---
