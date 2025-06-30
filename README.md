# Projeto CodeBurguer 🍔

Este repositório contém o projeto Java desenvolvido no Eclipse. Siga os passos abaixo para clonar e configurar corretamente o ambiente de desenvolvimento **sem quebrar nenhuma funcionalidade**.

---

## ✅ Pré-requisitos

- Eclipse IDE instalado (de preferência a mesma versão)
- Git instalado
- Java JDK 17 ou superior

---

## 🚀 Clonando o projeto

1. Abra o terminal e clone este repositório:

```bash
git clone https://github.com/seu-usuario/codeBurguer.git
````

2. Abra o Eclipse

3. Vá em:
   **`File > Import > General > Existing Projects into Workspace`**

4. Selecione o diretório `codeBurguer` clonado

5. Certifique-se de que a opção **"Copy projects into workspace"** **NÃO** esteja marcada

6. Clique em **Finish**

---

## ⚠️ Regras para evitar bugs no projeto

* Nunca adicione ou envie a pasta `.metadata/` para o repositório
* Nunca envie a pasta `bin/`, pois ela é gerada automaticamente
* Sempre que criar novas classes ou pacotes, certifique-se de que estão dentro da pasta `src/`
* Antes de subir algo para o Git, execute o projeto para garantir que não há erros de compilação
* Use `git pull` antes de começar a trabalhar para garantir que você está com o código mais atualizado

---

## 📂 Estrutura esperada do projeto

```
codeBurguer/
├─ .classpath
├─ .project
├─ .settings/
├─ src/
│  └─ codeBurguer/
│     └─ MainClass.java
└─ bin/  (gerado automaticamente, não versionado)
```

---

## ✅ Configurações sincronizadas pelo repositório

* `.classpath` → configurações das bibliotecas
* `.project` → identifica o projeto como Java no Eclipse
* `.settings/` → configurações do compilador (Java version, encoding, etc)

Esses arquivos **devem ser mantidos no Git**, pois são necessários para o Eclipse importar o projeto corretamente.

---

## 🧠 Dica final

Caso o Eclipse não reconheça o projeto como Java, clique com o botão direito na pasta do projeto > **Configure > Convert to Java Project**.

---

Colabore com cuidado e bons commits! 😉
