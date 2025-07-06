<h1 align="center">Projeto CodeBurger 🍔</h1>

**Bem-vindo ao CodeBurger!** Este é um sistema de gerenciamento para uma hamburgueria, desenvolvido em Java com a interface gráfica Swing. O projeto foi criado como um exercício acadêmico para praticar e aprimorar habilidades em desenvolvimento de software, trabalho em equipe e controle de versão com Git.

---

# 🧠 Como o Sistema Funciona?

O sistema simula um ambiente real de uma hamburgueria, com as seguintes funcionalidades:

* **Tela de Login**: Autenticação de usuários para acesso ao sistema.
* **Menu Principal**: Navegação central para acessar as diferentes seções de gerenciamento.
* **Gestão de Estoque**: CRUD (Criação, Leitura, Atualização e Exclusão) para os insumos do estoque.
* **Gestão de Produtos**: CRUD para os produtos vendidos na hamburgueria, como sanduíches, bebidas e sobremesas.
* **Gestão de Pedidos**: Visualização e controle dos pedidos realizados.

---

# ✨ Tecnologias Utilizadas

* **Linguagem**: Java 17+
* **Interface Gráfica**: Java Swing
* **Banco de Dados**: MySQL
* **IDE**: Eclipse
* **Build/Dependências**: Módulos nativos do Java (`module-info.java`)
* **Controle de Versão**: Git e GitHub

---

# ✅ Pré-requisitos

Antes de começar, garanta que você tenha os seguintes softwares instalados em sua máquina:

* **Java JDK 17** ou superior.
* **Eclipse IDE for Java Developers**.
* **Git** (para controle de versão).
* Um cliente de banco de dados para MySQL (como DBeaver, HeidiSQL ou o próprio MySQL Workbench) para configurar a base de dados.

---

# 🚀 Configurando o Ambiente (Setup)

Para que o projeto funcione corretamente no seu Eclipse, siga estes passos com atenção:

1. **Configure o Banco de Dados**:

   * Abra seu cliente de banco de dados.
   * Execute o script `hamburgueria_database.sql` para criar o banco `hamburgueria_db` e todas as tabelas e dados necessários.
   * Verifique se as credenciais de acesso ao banco no arquivo `src/br/com/hamburgueria/dao/ConnectionFactory.java` correspondem às do seu ambiente local (usuário e senha). Por padrão, está configurado para `root` e sem senha.

2. **Clone o Repositório**:

   * Abra o terminal (ou Git Bash no Windows) e use o comando abaixo para clonar o projeto para uma pasta de sua preferência.

   ```bash
   git clone https://github.com/seu-usuario/codeBurguer.git
   ```

3. **Importe o Projeto no Eclipse**:

   * Abra o Eclipse.
   * Vá em **`File > Import...`**.
   * Na janela que abrir, selecione **`General > Existing Projects into Workspace`** e clique em **`Next`**.
   * Clique em **`Select root directory`**, navegue até a pasta `codeBurguer` que você clonou e selecione-a.
   * **IMPORTANTE**: Deixe a opção **"Copy projects into workspace" DESMARCADA**. Isso garante que o Eclipse utilize a pasta que o Git está monitorando.
   * Clique em **`Finish`**.

Pronto! O projeto deve aparecer no seu "Package Explorer" e já estar configurado para rodar.

---

# 🤝 Guia de Contribuição para Iniciantes

Esta é a parte mais importante! Para que eu e possíveis contribuidores possamos trabalhar juntos sem criar conflitos ou perder código, sigam **sempre** este fluxo de trabalho.

## **Passo a Passo para fazer alterações no código:**

### **1. Sincronize seu código com o repositório remoto (****`git pull`****)**

**SEMPRE**, antes de começar a programar, você deve baixar as últimas alterações que foram enviadas para o GitHub. Isso evita que você trabalhe em uma versão desatualizada do código e cause conflitos depois.

* **Pelo Terminal**:

  ```bash
  # Navegue até a pasta do projeto
  cd caminho/para/codeBurguer

  # Puxe as atualizações
  git pull
  ```

### **2. Faça suas alterações no código**

Agora que seu projeto está sincronizado, você pode criar novas classes, alterar interfaces, corrigir bugs, etc. Lembre-se de sempre testar para garantir que suas alterações não quebraram nada.

### **3. Adicione e "Comite" suas alterações (****`git commit`****)**

Quando terminar uma tarefa (por exemplo, "criei o botão de deletar produto" ou "corrigi a cor do menu"), você precisa "salvar" um pacote de alterações. Isso é um **commit**.

* **Pelo Eclipse**:

  1. Abra a visão **`Git Staging`**. Se não estiver visível, vá em **`Window > Show View > Other...`** e procure por `Git Staging`.
  2. Na seção **`Unstaged Changes`**, você verá todos os arquivos que modificou. Arraste-os para a seção **`Staged Changes`**.
  3. No campo **`Commit Message`**, escreva uma mensagem **clara e curta** sobre o que você fez. Por exemplo: `feat: Adiciona tela de cadastro de cliente` ou `fix: Corrige bug no login`.
  4. Clique em **`Commit`** (NÃO clique em "Commit and Push" ainda).

* **Pelo Terminal**:

  ```bash
  # Adiciona todos os arquivos modificados para a "área de stage"
  git add .

  # Cria o commit com uma mensagem descritiva
  git commit -m "feat: Adiciona o campo 'CPF' na tela de usuário"
  ```

### **4. Envie suas alterações para o GitHub (****`git push`****)**

Depois de "commitar", suas alterações estão salvas localmente. O último passo é enviá-las para o repositório na nuvem (GitHub), para que seu colega possa baixá-las.

* **Pelo Eclipse**:

  1. Clique com o botão direito no projeto.
  2. Vá em **`Team > Push to Upstream`**.

* **Pelo Terminal**:

  ```bash
  git push
  ```

**Resumo do Fluxo**: **PULL -> MODIFICA -> COMMIT -> PUSH.** Repita esse ciclo sempre!

---

# 📂 Estrutura do Projeto

A estrutura de pastas do projeto está organizada da seguinte forma para facilitar a manutenção:

```
codeBurguer/
├── src/                      # Pasta principal com todo o código-fonte Java.
│   ├── br/com/hamburgueria/
│   │   ├── app/              # Classe principal que inicia a aplicação.
│   │   ├── dao/              # Classes de acesso a dados (conexão com o BD).
│   │   ├── model/            # Classes que representam as entidades (Usuário, Produto, etc.).
│   │   └── view/             # Classes da interface gráfica (as telas do sistema).
│   └── module-info.java      # Arquivo de configuração de módulos do Java.
├── hamburgueria_database.sql # Script de criação do banco de dados.
├── .classpath                # Arquivo do Eclipse com as configurações do projeto.
├── .project                  # Arquivo que identifica o projeto como Java no Eclipse.
└── README.md                 # Este arquivo que você está lendo.
```

**Arquivos que NÃO DEVEM IR para o GitHub**: as pastas `bin/` e `.metadata/` são geradas automaticamente pelo Eclipse e nunca devem ser enviadas para o repositório. O arquivo `.gitignore` já está configurado para ignorá-las.

---

# 💡 Dica Final

Caso o Eclipse, por algum motivo, não reconheça o projeto como um Projeto Java, clique com o botão direito na pasta do projeto e vá em **`Configure > Convert to Java Project`**.

---

<h3 align="center">Obrigado pela atenção! ♨</h3>
