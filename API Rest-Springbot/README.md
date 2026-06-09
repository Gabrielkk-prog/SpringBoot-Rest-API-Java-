# API REST Springbot

Projeto pequeno que serve como um proxy entre um frontend estático e o
Supabase (banco de dados). A ideia é manter a lógica de chamada ao
Supabase no backend Java (Spring Boot) e expor endpoints simples que o
cliente pode consumir.

Principais recursos:
- Endpoint de health-check: `GET /api/health`
- Endpoint para buscar dados de uma tabela: `GET /api/dados?tabela={name}`
- Frontend simples: `client.html` para testar a API no navegador

---

Pré-requisitos
- Java 17+
- Maven 3.8+

Configuração
1. Copie ou exporte as variáveis necessárias (recomendado via variáveis de ambiente):

```
SUPABASE_URL=https://seu-projeto.supabase.co
SUPABASE_ANON_KEY=chave_anonima
SUPABASE_SERVICE_ROLE_KEY=chave_service_role (opcional)
SERVER_PORT=8080 (opcional)
```

Ou defina diretamente em `src/main/resources/application.properties` usando as variáveis de ambiente.

Como rodar
```bash
mvn clean package
mvn spring-boot:run
```

Abra no navegador:

```
http://localhost:8080/client.html
```

Uso (exemplo curl)
```bash
curl "http://localhost:8080/api/dados?tabela=users"
```

Observações de segurança
- Não comite as chaves do Supabase no repositório.
- O endpoint atual expõe dados públicos usando a chave anônima.
  Para operações administrativas use a `service-role` key com cautela.

Estrutura do projeto
```
API Rest-Springbot/
├── pom.xml
├── client.html
├── share.html
├── src/main/java/com/example/
│   ├── Application.java
│   ├── controller/SupabaseController.java
│   └── service/SupabaseService.java
└── src/main/resources/application.properties
```

Contribuições
- Abra uma issue ou envie um pull request com melhorias.

Licença
- Use conforme sua necessidade. Não inclui chaves sensíveis.


