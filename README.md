<<<<<<< HEAD
# ProjetoAppVendas
 Este é um aplicativo mobile desenvolvido para uma confeitaria, com o objetivo de aumentar o número de vendas e oferecer uma experiência mais prática para os clientes. O app permite que os usuários naveguem pelo cardápio façam pedidos , enquanto a confeitaria pode gerenciar os pedidos recebidos.  
 
*Funcionalidades*
1. Catálogo de produtos: Exibição dos produtos disponíveis com imagens e descrição.
2. Carrinho de compras: Adição e remoção de produtos antes da finalização do pedido.
3. Login: Autenticação via Firebase com dois tipos de login.  
 3.1 Login Usuario cliente: Pode visualizar o cardápio, adicionar produtos ao carrinho e finalizar compras.   
 3.2 Login Usuario admin: Tem acesso à página de pedidos realizados pelos clientes.   
5. Navegação: Botões de navegação entre as telas.
      
*Tecnologias utilizadas*    
Linguagem: Java (Android).    
Backend e Autenticação: Firebase Authentication e Firestore.

Projeto em desenvolvimento para melhorias, mas já funcional.
=======
# 🍰 Doces do Dia

Aplicativo Android para gerenciamento de pedidos de uma confeitaria, desenvolvido em **Java** utilizando **Android Studio**, **XML** e serviços do **Firebase**.

O projeto permite que clientes criem uma conta, façam login, consultem o cardápio, selecionem produtos, gerenciem seu carrinho e realizem pedidos.
A aplicação também possui uma área destinada à loja, permitindo o acesso aos pedidos realizados e às informações associadas aos clientes.

> Este projeto foi originalmente desenvolvido como Trabalho de Conclusão de Curso (TCC) e posteriormente retomado como projeto pessoal, com o objetivo de aprimorar sua implementação,a atualizar e continuar expandindo suas funcionalidades.

---

## 📱 Sobre o projeto

O **Doces do Dia** foi desenvolvido com o objetivo de criar uma solução para digitalizar o processo de pedidos de uma confeitaria, mas pode ser usado como base para outros comercios.

A aplicação possui dois fluxos principais:

- **Cliente:** acesso ao cardápio, seleção de produtos, gerenciamento do carrinho e realização de pedidos.
- **Loja:** acesso administrativo para consulta dos pedidos realizados e das informações relacionadas aos clientes.

Os dados da aplicação são armazenados utilizando serviços do **Firebase**, permitindo que as informações sejam persistidas e acessadas entre as diferentes partes do sistema.

---

## 🚀 Funcionalidades

### 👤 Cliente

Atualmente, o aplicativo permite ao cliente:

- Criar uma conta;
- Realizar login;
- Encerrar a sessão;
- Acessar seu perfil;
- Visualizar o cardápio;
- Selecionar produtos;
- Adicionar produtos ao carrinho;
- Visualizar e gerenciar o carrinho;
- Criar pedidos;
- Consultar seus pedidos realizados.

### 🏪 Loja

A aplicação possui uma área específica para a loja, com:

- Login administrativo separado;
- Acesso à área administrativa;
- Visualização dos pedidos realizados;
- Consulta das informações relacionadas ao cliente;
- Consulta dos produtos presentes nos pedidos.

---

## 🛠️ Tecnologias utilizadas

O projeto foi desenvolvido utilizando:

- **Java** — linguagem principal da aplicação;
- **Android Studio** — ambiente de desenvolvimento;
- **XML** — construção das interfaces;
- **Android SDK**;
- **AndroidX AppCompat**;
- **Material Components**;
- **ConstraintLayout**;
- **RecyclerView**;
- **Firebase Authentication** — autenticação dos usuários;
- **Cloud Firestore** — armazenamento dos dados da aplicação;
- **JUnit** — testes unitários;
- **Espresso** — testes instrumentados;
- **Git** — controle de versão;
- **GitHub** — hospedagem do código-fonte.

### Configurações atuais do projeto

| Configuração | Versão |
|---|---|
| Linguagem | Java |
| Java Compatibility | Java 8 |
| Compile SDK | 33 |
| Target SDK | 33 |
| Minimum SDK | 21 |
| Version Code | 1 |
| Version Name | 1.0 |
| Firebase BOM | 29.0.0 |

---

## 🔥 Firebase

O projeto utiliza o Firebase como infraestrutura para autenticação e persistência dos dados.

### Firebase Authentication

O **Firebase Authentication** é utilizado para gerenciar a autenticação dos usuários.

Através dele, o aplicativo consegue:

- Criar contas;
- Autenticar usuários;
- Manter a sessão autenticada;
- Identificar os usuários dentro da aplicação.

O sistema possui fluxos distintos para acesso dos clientes e da loja.

### Cloud Firestore

O **Cloud Firestore** é utilizado para armazenar os dados estruturados da aplicação.

Atualmente, a estrutura utilizada possui coleções relacionadas a:

### Firestore
├── Pedidos
├── Usuarios
└── carrinho


### Novas atualizações estão por vir