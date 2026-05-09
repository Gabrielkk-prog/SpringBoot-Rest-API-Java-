# 📚 API REST Springbot - Documentação Completa

## 🎯 O que esse projeto faz?

Este projeto é uma **API REST** que actua como intermediária entre um frontend (navegador) e o **Supabase**. 

**Fluxo:**
```
Frontend (client.html) → API Spring Boot (Backend) → Supabase (Banco de Dados)
     |                          |                         |
  Browser               Porta 8080                   Cloud Database
```

### Funcionalidades:
- ✅ Servir uma página HTML com interface para buscar dados
- ✅ Receber requisições do navegador para buscar dados
- ✅ Conectar ao Supabase com autenticação segura
- ✅ Retornar dados em formato JSON
- ✅ Tratamento de erros e validações

---

## 🔴 Principais Erros na Estrutura Original

### Erro 1: **Código Java dentro de um arquivo HTML**
```html
<!-- ❌ ERRADO -->
<script>
  import org.springframework.stereotype.Service;  // Isso é Java, não JavaScript!
  public class SupabaseService { ... }
</script>
```
**Problema:** JavaScript do navegador não consegue executar código Java. Java é backend, JavaScript é frontend.

---

### Erro 2: **Mistura de responsabilidades**
- `client.html` tinha código de backend (Java)
- Não havia separação clara entre frontend e backend
- **Solução:** Frontend em HTML/JS, Backend em Spring Boot

---

### Erro 3: **Falta de estrutura padrão de um projeto Spring**
- Sem `pom.xml` (gerenciador de dependências)
- Sem `application.properties` (configurações)
- Sem classe principal da aplicação
- Sem Controller para expor endpoints
- **Solução:** Estrutura completa de Maven + Spring Boot

---

### Erro 4: **Credenciais e configurações hardcoded**
```java
// ❌ ERRADO
.baseUrl("https://supabase.co")
.defaultHeader("apikey", "SUA_ANON_KEY")
```
**Problema:** Chaves secretas no código são inseguras.
**Solução:** Usar `application.properties` com variáveis de ambiente

---

### Erro 5: **Código duplicado e incompleto**
O arquivo `SupabaseService.java` tinha duas implementações diferentes misturadas.

---

## ✅ Como está organizado agora?

### Estrutura de Pastas:
```
API Rest-Springbot/
├── pom.xml                                    # Configuração do Maven
├── client.html                                # Frontend (interface do usuário)
├── share.html                                 # Documentação
├── src/
│   ├── main/
│   │   ├── java/com/example/
│   │   │   ├── Application.java              # Classe principal
│   │   │   ├── controller/
│   │   │   │   └── SupabaseController.java   # Endpoints REST
│   │   │   └── service/
│   │   │       └── SupabaseService.java      # Lógica de negócio
│   │   └── resources/
│   │       └── application.properties        # Configurações
```

---

## 🏗️ O que cada arquivo faz?

### `pom.xml`
- Define dependências do projeto (Maven)
- Configuração de versão do Java (17)
- Spring Boot WebFlux para requisições assíncronas

### `application.properties`
- Configurações centralizadas
- URL e chaves do Supabase
- Porta do servidor (8080)

### `Application.java`
- Classe principal que inicia o Spring Boot
- Registra o `WebClient` como Bean (componente reutilizável)

### `SupabaseController.java`
- Define os **endpoints REST** da API
- `GET /api/dados?tabela=users` - busca dados
- `GET /api/health` - verifica se servidor está online
- Permite CORS (requisições do navegador)

### `SupabaseService.java`
- Implementa a lógica de conexão com Supabase
- Usa `WebClient` do Spring WebFlux
- Retorna dados de forma **reativa** (Mono)

### `client.html`
- Interface visual no navegador
- Permite digitar o nome da tabela
- Exibe os dados retornados

---

## 🚀 Como executar?

### 1. Configurar credenciais
Edite `src/main/resources/application.properties`:
```properties
supabase.url=https://seu-projeto.supabase.co
supabase.anon-key=sua-chave-publica
```

### 2. Compilar e rodar
```bash
mvn clean install
mvn spring-boot:run
```

### 3. Acessar no navegador
```
http://localhost:8080/client.html
```

---

## � Configuração para GitHub

### ⚠️ Segurança - NÃO commitar credenciais

Este projeto usa variáveis de ambiente para proteger chaves sensíveis.

### Passo 1: Copiar arquivo de exemplo
```bash
cp .env.example .env
```

### Passo 2: Editar `.env` com suas credenciais
```env
SUPABASE_URL=https://seu-projeto.supabase.co
SUPABASE_ANON_KEY=sua_chave_anonima
SUPABASE_SERVICE_ROLE_KEY=sua_chave_service_role
SERVER_PORT=8080
```

### Passo 3: Verificar `.gitignore`
O arquivo `.gitignore` já exclui:
- ✅ `.env` (credenciais locais)
- ✅ `target/` (arquivos de build)
- ✅ `.idea/` e `.vscode/` (arquivos de IDE)

### Passo 4: Fazer commit seguro
```bash
git init
git add .
git commit -m "Initial commit: API REST Spring Boot com Supabase"
git remote add origin https://github.com/seu-usuario/seu-repositorio.git
git push -u origin main
```

### Para outros desenvolvedores
Ao clonar o repositório:
1. Clone o projeto
2. Copie `.env.example` para `.env`
3. Atualize com suas credenciais do Supabase
4. Execute `mvn spring-boot:run`

---

## �📊 Fluxo de Requisição

```
1. Usuário abre client.html no navegador
                    ↓
2. Clica em "Buscar Dados" com nome da tabela
                    ↓
3. JavaScript faz: fetch('/api/dados?tabela=users')
                    ↓
4. SupabaseController recebe a requisição
                    ↓
5. SupabaseService conecta ao Supabase via WebClient
                    ↓
6. Supabase retorna dados em JSON
                    ↓
7. Spring retorna para o navegador
                    ↓
8. JavaScript exibe dados no client.html
```

---

## 🔑 Conceitos Importantes

- **REST API:** Interface baseada em HTTP (GET, POST, PUT, DELETE)
- **Spring Boot:** Framework Java para criar aplicações rápido
- **WebFlux:** Programação reativa (não bloqueia durante requisições)
- **Supabase:** Banco de dados cloud com autenticação
- **Frontend vs Backend:** Cliente (navegador) vs Servidor (Java)

---

## ✨ Benefícios da nova estrutura

✅ Separação clara de responsabilidades  
✅ Código limpo e profissional  
✅ Fácil de manter e expandir  
✅ Segurança (sem hardcoding de chaves)  
✅ Segue padrões de industria  
✅ Pronto para produção  

