# 🛒 Projeto Supermercado

Este projeto é um sistema de **Supermercado** desenvolvido em **Java** utilizando o padrão de arquitetura **MVC (Model-View-Controller)**.  
O objetivo é gerenciar produtos, clientes e operações de venda de forma organizada e modular.

---

## 📌 Funcionalidades
- Cadastro, edição e exclusão de produtos.
- Consulta de produtos disponíveis.
- Registro de clientes.
- Controle básico de vendas.
- Interface construída com **Swing** (Java Desktop).
- Estrutura organizada em camadas:
  - **Model** → classes de domínio e regras de negócio.
  - **View** → telas e interação com o usuário.
  - **Controller** → comunicação entre a view e o model.

---

## 🏗️ Estrutura do Projeto
```
ProjetoSupermercado/
├── src/
│ ├── main/ # Package principal do projeto
│ │ └── Main.java # Classe principal que executa o programa
│ ├── model/ # Classes de domínio e regras de negócio
│ ├── view/ # JPanel's e componentes gráficos
│ └── controller/ # Lógica de controle e integração
├── .gitignore
├── README.md
└── ...
```

---

## 🚀 Tecnologias Utilizadas
- **Java SE 17+** (compatível com versões anteriores)
- **Swing** (interfaces gráficas)
- **Eclipse IDE** (desenvolvimento)
- **Git/GitHub** (controle de versão)

---

## ▶️ Como Executar
1. Clone este repositório:
   ```bash
   git clone https://github.com/GustavoK1510/ProjetoSupermercado.git
   ```
2. Abra o projeto no **Eclipse** (ou outra IDE Java).
3. Compile e execute a classe principal (geralmente `Main.java`).

---

## 📚 Padrão de Arquitetura (MVC)
- **Model** → contém os objetos principais do sistema, como `Produto`, `Cliente` e `Carrinho de Compras`.  
- **View** → responsável pela interface gráfica, com múltiplos `JPanels` organizados em um único `JFrame`.  
- **Controller** → controla o fluxo entre as Views e os Models, aplicando as regras de negócio.  

---

## ✨ Melhorias Futuras
- Integração com banco de dados (ex: MySQL).
- Geração de relatórios de vendas.
- Autenticação de usuários (login/senha).
- Interface gráfica mais moderna.

---

## 👨‍💻 Autor
- Gustavo Kautzmann  
- [GitHub](https://github.com/GustavoK1510)
