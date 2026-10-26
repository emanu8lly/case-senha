# Validador de Senhas API 🔒

API REST desenvolvida em **Java 17** e **Spring Boot 3** com o objetivo de validar senhas conforme regras definidas. O projeto foi construído priorizando a simplicidade arquitetural, baixo acoplamento e aderência estrita às normas do protocolo HTTP.

---

## 🚀 Tecnologias Utilizadas
* **Java 17**
* **Spring Boot 3**
* **Gradle**
* **JUnit 5 & AssertJ**
* **Spring MockMvc**

---

## 📋 Regras de Negócio Implementadas
A senha deve atender obrigatoriamente a todos os seguintes critérios:
1. Mínimo de **9 caracteres**.
2. Pelo menos **1 dígito** (0-9).
3. Pelo menos **1 letra minúscula** (a-z).
4. Pelo menos **1 letra maiúscula** (A-Z).
5. Pelo menos **1 caractere especial** (`!`,`@`,`#`,`$`,`%`,`^`,`&`,`*`,`(`,`)`,`-`,`+`).
6. **Não possuir caracteres repetidos** em nenhuma posição da senha. (ex. `Aabc123!`)
7. **Não conter espaços em branco**, tabulações (`\t`) ou quebras de linha (`\n`).

---

## 🏗️ Decisões de Arquitetura e Premissas

* **Padrão Strategy:** A validação é desacoplada através da interface `ValidacaoSenha`. Cada regra é um componente isolado (`@Component`), injetado automaticamente como uma lista no `ValidadorSenhaService`. Isso garante **alta coesão** e facilidade para adicionar novas regras sem alterar o serviço principal.
* **Uso de `text/plain` e Ausência de DTO:** Optou-se por receber a senha diretamente como texto puro (`text/plain`) na assinatura do controller (`@RequestBody String senha`). Como a aplicação não possui persistência (base de dados) e o payload é um valor escalar único, introduzir um DTO (`Data Transfer Object`) constituiria *over-engineering*.
* **Tratamento Semântico de Erros HTTP:**
  * **`400 Bad Request`**: Retornado quando o corpo da requisição está totalmente ausente ou ilegível (`HttpMessageNotReadableException`).
  * **`415 Unsupported Media Type`**: Retornado quando o cliente envia um formato de dados incorreto no cabeçalho (ex: `application/json` em vez de `text/plain`) (`HttpMediaTypeNotSupportedException`).
  * **`200 OK`**: Retornado quando a requisição é processada com sucesso, entregando o resultado booleano (`true` ou `false`) independentemente de a senha ser válida ou reprovada pelas regras de negócio.

---

## ⚙️ Como Executar o Projeto

1. É necessário ter **Java 17** instalado. O projeto utiliza o Gradle Wrapper.
2. Clone o repositório e abra o projeto no seu editor de preferência (ex: IntelliJ IDEA).
3. Execute a aplicação através da classe principal `ValidadorApplication.java` ou utilizando o terminal na raiz do projeto:
   ```bash
   ./gradlew bootRun
   ```
A API estará disponível em: http://localhost:8080

## 🧪 Como Executar os Testes
Para rodar toda a suíte de testes unitários e de integração, execute o seguinte comando no terminal:
   ```bash
   ./gradlew test
   ```
## 📥 Utilização

### 1. Sucesso na Validação
* Método: `POST`
* URL: `http://localhost:8080/senhas/validacao`
* Headers:
  * `Content-Type: text/plain`
* Body (Text):
```plaintext
AbTp9!fok
```
* Resposta (`200 OK`):
```json
true
```
### 2. Formato Não Suportado
* Método: `POST`
* URL: `http://localhost:8080/senhas/validacao`
* Headers:
  * `Content-Type: text/plain`
* Body (JSON):
```json
{ "senha": "AbTp9!fok" }
```
* Resposta (`415 Unsupported Media Type`):
```json
{
  "mensagem": "A senha deve ser enviada como um texto comum e direto (sem formatações ou chaves de JSON). Certifique-se de configurar o formato de envio (Content-Type) como 'text/plain'.",
  "timestamp": "2026-10-25T19:40:45",
  "status": 415
}
```
### 3. Corpo da Requisição Ausente
* Método: `POST`
* URL: `http://localhost:8080/senhas/validacao`
* Headers:
  * `Content-Type: text/plain`
* Body (Text):
(vazio)
* Resposta (`400 Bad Request`):
```json
{
  "mensagem": "O corpo da requisição é obrigatório e está ausente ou inválido.",
  "timestamp": "2026-10-25T19:40:45",
  "status": 400
}
```