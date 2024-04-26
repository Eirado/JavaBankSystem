# JavaBankSystem

## Descrição

O **JavaBankSystem** é um sistema bancário que utiliza concorrência para simular transações entre bancos, lojas, funcionários e clientes. Este sistema foi desenvolvido em Java e possui as seguintes entidades:

- **Banco**: Entidade responsável por intermediar as transações de forma síncrona e coordenada, garantindo a consistência dos saldos das contas.
- **Loja**: Representa uma loja que vende produtos para os clientes. Cada loja possui uma conta para receber os pagamentos dos clientes e pagar os funcionários.
- **Funcionário**: Representa os funcionários das lojas. Cada funcionário é uma thread que possui duas contas: uma para receber o salário da loja e outra para investimentos.
- **Cliente**: Cada cliente é uma thread que possui uma conta com um saldo inicial de R$ 1.000,00. Eles realizam compras alternando entre as lojas até que o saldo da conta esteja vazio.
- **Conta**: Representa uma conta bancária, contendo informações como saldo e histórico de transações.

## Funcionalidades

### Clientes

- Cada cliente é uma thread que realiza compras alternando entre as lojas.
- Eles possuem uma conta com um saldo inicial de R$ 1.000,00.
- Realizam compras de R$ 100,00 ou R$ 200,00 até que o saldo da conta esteja vazio.

### Lojas

- Cada loja possui uma conta para receber os pagamentos dos clientes.
- Deve pagar os funcionários assim que possuir o valor dos seus salários (R$ 1.400,00).

### Funcionários

- Cada funcionário é uma thread que possui duas contas: uma para receber o salário da loja e outra para investimentos.
- Investem 20% do salário na conta de investimentos logo após seu recebimento.

### Banco

- Intermedia as transações de forma síncrona e coordenada, garantindo a consistência dos saldos das contas.
- Exibe o valor das transferências e o saldo final de cada conta, garantindo que os saldos estejam consistentes ao fim da execução, independentemente da ordem em que as transações foram feitas.

## Estrutura do Projeto

A estrutura do projeto pode ser organizada em pacotes separados para cada entidade, cada um contendo suas respectivas classes e interfaces. Por exemplo:

- `banco`: Contém a classe `Banco` que implementa a lógica de coordenação entre as transações.
- `loja`: Contém a classe `Loja` que representa uma loja e suas funcionalidades.
- `funcionario`: Contém a classe `Funcionario` que representa os funcionários das lojas.
- `cliente`: Contém a classe `Cliente` que representa os clientes do banco.
- `conta`: Contém a classe `Conta` que define as características de uma conta bancária.

## Extras
- Os clientes pagam valores aleatórios para as lojas, o que pode alterar no resultado final.
- Foi utilizado um listener em `Loja` e `Conta` para pagar o funcionário corretamente.

## Considerações Finais

O **JavaBankSystem** proporciona uma simulação de um sistema bancário com concorrência, demonstrando a importância da sincronização de transações para garantir a consistência dos saldos das contas. É importante observar como cada entidade interage entre si e como o banco coordena essas transações para manter a integridade dos dados.
