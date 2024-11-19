
### **Autenticação**
#### **Registrar um novo usuário**
- **Método:** `POST`
- **URL:** `http://localhost:8080/auth/register`
- **Descrição:** Cadastra um novo usuário no sistema.
- **Corpo da requisição (JSON):**
  ```json
  {
    "username": "novoUsuario",
    "password": "senhaForte"
  }
  ```

#### **Login (autenticação do usuário)**
- **Método:** `POST`
- **URL:** `http://localhost:8080/auth/login`
- **Descrição:** Faz o login do usuário e retorna um token JWT.
- **Corpo da requisição (JSON):**
  ```json
  {
    "username": "novoUsuario",
    "password": "senhaForte"
  }
  ```
- **Resposta:**
  ```json
  {
    "token": "token"
  }
  ```

---

### **CRUD de Tarefas**
Esses endpoints lidam com a criação, listagem, atualização e exclusão de tarefas.

#### **Criar uma nova tarefa**
- **Método:** `POST`
- **URL:** `http://localhost:8080/task`
  - `Authorization: Bearer {token}`
- **Corpo da requisição (JSON):**
  ```json
  {
    "titulo": "Tarefa 1",
    "descricao": "Descrição da tarefa"
  }
  ```

#### **Listar todas as tarefas em ordem**
- **Método:** `GET`
- **URL:** `http://localhost:8080/task`
- **Descrição:** Lista todas as tarefas.
- **Cabeçalho:** 
  - `Authorization: Bearer {token}`

#### **Atualizar uma tarefa**
- **Método:** `PUT`
- **URL:** `http://localhost:8080/task/{id}`
- Atualiza uma tarefa existente.
- `Authorization: Bearer {token}`


#### **Deletar uma tarefa**
- **Método:** `DELETE`
- **URL:** `http://localhost:8080/task/{id}`
- **Descrição:** Deleta uma tarefa com o ID especificado.
- `Authorization: Bearer {token}`

pasta endoints com demais rotas
---
