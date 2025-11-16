# Avaliação A2 - Design Patterns e SOLID

Aluno: William Felipe Coscodai
RGM: 30051291

## Estrutura do Projeto

### Questão 1 - Strategy Pattern
- **Pattern Usado**: Strategy
- **Princípios SOLID**: 
  - SRP: Cada algoritmo em sua própria classe
  - OCP: Aberto para extensão (novos algoritmos) fechado para modificação
  - DIP: Dependência de abstrações (interface RiskAlgorithm)
- **Justificativa**: Strategy permite trocar algoritmos em runtime sem acoplamento

### Questão 2 - Adapter Pattern
- **Pattern Usado**: Adapter (bidirecional)
- **Princípios SOLID**:
  - SRP: Adaptador responsável apenas pela conversão
  - ISP: Interfaces segregadas para cada direção
  - DIP: Depende de abstrações, não de implementações concretas
- **Justificativa**: Adapter permite integração com sistema legado sem modificá-lo

### Questão 3 - State Pattern
- **Pattern Usado**: State
- **Princípios SOLID**:
  - SRP: Cada estado gerencia seu próprio comportamento
  - OCP: Novos estados podem ser adicionados sem modificar existentes
  - LSP: Estados são substituíveis
- **Justificativa**: State encapsula transições complexas e valida condições

### Questão 4 - Chain of Responsibility Pattern
- **Pattern Usado**: Chain of Responsibility com Circuit Breaker
- **Princípios SOLID**:
  - SRP: Cada validador tem uma única responsabilidade
  - OCP: Novos validadores podem ser adicionados
  - DIP: Validadores dependem de abstração
- **Justificativa**: Chain permite validações sequenciais com lógica condicional
