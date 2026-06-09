# 🚀 Setup Guide

## Step 1: Get Supabase Credentials

1. Go to [supabase.com](https://supabase.com) and create a project (or use an existing one)
2. From the **Settings** → **API**, copy:
   - **Project URL** (your Supabase URL)
   - **Public API Key** (your anon key)
   - **Service Role Key** (optional, for admin operations)

## Step 2: Configure Environment Variables

### Option A: Using `.env` file (Recommended for local development)

1. In the project root, copy `.env.example` to `.env`:
```bash
cp .env.example .env
```

2. Edit `.env` and fill in your credentials:
```env
SUPABASE_URL=https://your-project.supabase.co
SUPABASE_ANON_KEY=your_public_anon_key_here
SUPABASE_SERVICE_ROLE_KEY=your_service_role_key_here
SERVER_PORT=8080
```

3. Spring Boot will automatically read these variables when running.

### Option B: Using System Environment Variables

Alternatively, set them in your terminal before running:

**On Windows (PowerShell):**
```powershell
$env:SUPABASE_URL="https://your-project.supabase.co"
$env:SUPABASE_ANON_KEY="your_key_here"
$env:SERVER_PORT="8080"
mvn spring-boot:run
```

**On macOS/Linux:**
```bash
export SUPABASE_URL="https://your-project.supabase.co"
export SUPABASE_ANON_KEY="your_key_here"
export SERVER_PORT="8080"
mvn spring-boot:run
```

## Step 3: Run the Application

```bash
cd API\ Rest-Springbot
mvn clean package
mvn spring-boot:run
```

You should see:
```
Application started with Spring Boot
...
Application is running on: http://localhost:8080
```

## Step 4: Test the API

### In the Browser:
- Open: http://localhost:8080/client.html
- Enter a table name (e.g., `users`)
- Click "Buscar Dados"

### Via Terminal (curl):
```bash
curl "http://localhost:8080/api/dados?tabela=users"
```

### Health Check:
```bash
curl "http://localhost:8080/api/health"
```

## ⚠️ Security Notes

- **Never commit `.env`** — It's already in `.gitignore`
- The `.env.example` file shows required variables (you can commit this)
- For production, use a secrets manager (AWS Secrets Manager, HashiCorp Vault, etc.)

## 🐛 Debugging

1. Install **Extension Pack for Java** in VS Code
2. Press `F5` to start debugging
3. Set breakpoints by clicking on line numbers
4. Check the Debug Console for logs

Check `src/main/resources/application.properties` for logging configuration.
