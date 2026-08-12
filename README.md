 F.Custom — Sistema de E-commerce em Java

Sistema de simulação de loja virtual desenvolvido em Java, rodando em ambiente de terminal, com fluxo completo de compra, personalização de produtos e painel administrativo.

🎯 Objetivo do Projeto:

O projeto foi desenvolvido com o objetivo de simular, de forma simplificada, o funcionamento de um e-commerce real — desde a autenticação de usuários até a finalização de um pedido — aplicando na prática conceitos fundamentais de lógica de programação, estruturas de dados, validação de entradas e regras de negócio.

A proposta central foi resolver dois problemas simultâneos:
- Permitir que um **cliente** navegue por um catálogo de produtos, personalize itens (cor, tamanho, estampa) e finalize uma compra com diferentes formas de pagamento.
- Permitir que um **administrador** gerencie o negócio: controle de estoque, alteração de preços e acompanhamento do histórico de vendas.

🛠️ Tecnologias Utilizadas:

- Linguagem: Java (puro, sem frameworks)
- **Entrada de dados:** `Scanner`
- **Estruturas de dados:** `ArrayList` (carrinho de compras e histórico de vendas)
- **Validação:** Expressões regulares (`matches()`) e tratamento de exceções (`try/catch`)
- **Formatação de saída:** `String.format()` e `printf()`

Não há uso de banco de dados ou persistência em arquivo — todas as informações (estoque, preços, histórico) são mantidas em memória, através de variáveis e arrays, durante a execução do programa.

📚 O que foi aprendido:

Este projeto foi importante para consolidar minha base em programação, especialmente nos seguintes pontos:

- Validação robusta de entradas:** desenvolvi métodos reutilizáveis para tratar dados vazios, inválidos ou fora do intervalo esperado, evitando que o programa quebrasse com entradas inesperadas do usuário.
- Coleções dinâmicas:** pratiquei o uso de `ArrayList` para gerenciar dados que crescem durante a execução, como itens do carrinho e vendas realizadas.
- Regras de negócio aplicadas ao código:** implementar descontos por forma de pagamento, controle de estoque e bloqueio de compras com carrinho vazio me ajudou a pensar de forma mais próxima de um sistema real.
- Identificação de melhorias arquiteturais:** ao revisar o próprio código, percebi a repetição de lógica entre os diferentes produtos (representados por variáveis soltas, como `estoqueCamisa` e `precoCalca`). Isso reforçou, na prática, os benefícios da **Programação Orientada a Objetos** — uma classe única `Produto` reduziria drasticamente a duplicação e tornaria o sistema mais fácil de manter.
- **Depuração contínua:** diversas correções foram feitas ao longo do desenvolvimento a partir de falhas encontradas em testes manuais, o que aproximou o processo de um ciclo real (ainda que informal) de desenvolvimento iterativo.

🔮 Próximos passos:

- Refatorar o sistema aplicando Programação Orientada a Objetos (classes `Produto`, `ItemCarrinho`, `Cliente`)
- Implementar persistência de dados (arquivo ou banco de dados)
- Separar a lógica de negócio da interface de terminal, preparando o código para uma futura interface gráfica ou web
