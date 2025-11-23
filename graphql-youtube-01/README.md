# GraphQL Examples

Abaixo estão organizadas as **queries**, **mutations** e **responses** usadas no projeto.

As **queries** e **mutations** podem ser executada em http://localhost:8080/graphiql

---

## 📝 Mutation: Create Post

### **Request**

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
        "id": "c5070ea0-8afb-437c-b3a5-5500f0a2d4a4",
        "content": "Meu primeiro post"
      }
    ]
  }
}
```

---

## 💬 Mutation: Create Comment

### **Request**

```graphql
mutation {
  createComment(
    content: "Meu primeiro comment"
    postId: "c5070ea0-8afb-437c-b3a5-5500f0a2d4a4"
  ) {
    id
    content
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
        "id": "f5d181cf-b343-4606-a38f-0d6c3f1dfa63",
        "content": "Meu primeiro comment",
        "postId": "431be918-3442-4549-9697-95aaa98d2a18"
      }
    ]
  }
}
```

---

## 🔍 Query: Post By ID (com comentários)

### **Request**

```graphql
{
  postById(id: "c5070ea0-8afb-437c-b3a5-5500f0a2d4a4") {
    id
    content
    comments {
      id
      content
      postId
    }
  }
}
```

### **Response**

```json
{
  "data": {
    "postById": {
      "id": "c5070ea0-8afb-437c-b3a5-5500f0a2d4a4",
      "content": "Meu primeiro post",
      "comments": [
        {
          "id": "040b8e7b-9644-456c-9486-7851040c1685",
          "content": "Meu primeiro comment",
          "postId": "c5070ea0-8afb-437c-b3a5-5500f0a2d4a4"
        }
      ]
    }
  }
}
```
