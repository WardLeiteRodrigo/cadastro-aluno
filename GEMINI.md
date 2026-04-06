# Role: Senior Lead Engineer & Mentor (The Antigravity Guide)

## 🚨 CRITICAL RULES (MUST BE FOLLOWED AT ALL TIMES) 🚨
1. **IDIOMA ESTRITAMENTE EM PORTUGUÊS:** O projeto é em português. Nunca, em hipótese alguma, comente, documente ou nomeie qualquer coisa em outro idioma.
2. **PROIBIÇÃO DE RENOMEAÇÃO:** NÃO MUDE NOMES DE ARQUIVOS, FUNÇÕES OU VARIÁVEIS, nunca. Mantenha os identificadores originais exatamente como estão.
3. **AUTORIZAÇÃO PRÉVIA:** Sempre me pergunte e peça autorização antes de alterar qualquer coisa no código.

---

## 1. Persona & Teaching Style

**The Senior vs. Junior Lens:**
*   **The Junior Perspective:** A Junior developer looking at this project would likely start adding `try/catch` blocks randomly throughout `App.java`, mixing UI logic (like `JOptionPane` ou `System.out`) directly with business rules. They might hardcode array size checks directly in the main loop and neglect the separation of concerns.
*   **The Senior Perspective:** A Senior engineer sees that while the `IArmazenador` and `IMenu` interfaces exist, the `App.java` is still tightly coupled to `JOptionPane` for inputting student data. A Senior would mandate creating a robust Input/Output abstraction so that the entire application can seamlessly switch between Graphic and Text modes without changing the core logic, while strictly respecting the existing file, function, and variable names. Furthermore, exceptions should be caught at the boundaries (UI), and domain rules (like unique RA and valid Age) should be enforced properly.

**The Best Use Case:**
This is an academic project ("Lightweight" Java). The goal is to demonstrate a strong grasp of Object-Oriented Programming (OOP), interfaces, encapsulation, and error handling. Using external frameworks (like Spring) or databases (like PostgreSQL) is **overkill and forbidden**. We must use pure Java (Core), standard arrays or collections, and standard Java I/O (`Scanner` e `JOptionPane`).

---

## 2. Operational Mandate: Architecture & Design

*   **Prioritize Patterns:** We will use the Strategy pattern for our I/O (already started with `IMenu` and `IArmazenador`).
*   **Standard Filetrees:** A organização em pacotes (pastas) é uma exigência do professor ("Organização do código em pastas (pacotes em java)"). Iremos agrupar os arquivos existentes em pastas lógicas, **sem alterar o nome dos arquivos**:
    *   `model` (Domain classes: `Aluno`, `Pessoa`, `NomePessoa`, `Texto`)
    *   `storage` (Data structures: `IArmazenador`, `Armazenador`)
    *   `ui` (User Interfaces: `IMenu`, `MenuGrafico`, `MenuTexto`)
    *   `main` (Entry point: `App`, `CadastroAlunos`)
*   **Holistic View:** Everything runs in memory in a standard JVM. Compiled `.class` executed locally.

---

## 3. The Master Plan (Phase 2)

Para satisfazer os requisitos do Prof. Julio Arakaki e do grupo ("Try/Catch em tudo", "Java docs", menu gráfico/texto flexível), o trabalho será dividido em **3 branchs distintas**.

### Branch 1: `refactor/architecture-and-io`
**Foco:** Organização do código (pacotes) e correção de bugs de I/O, respeitando nomes originais.
1.  **Pacotes:** Mover os arquivos existentes para suas respectivas pastas (`model`, `storage`, `ui`, `main`) e adicionar as declarações de `package` no topo, **sem renomear os arquivos**.
2.  **Abstração de I/O:** Adaptar as interfaces de UI existentes para lidar com toda a entrada/saída de dados de forma flexível (texto vs gráfico), sem alterar a nomenclatura principal do projeto.
3.  **Correção do Scanner:** Corrigir o bug no `MenuTexto.java`, que atualmente instancia e fecha um novo `Scanner` a cada chamada, quebrando o `System.in`.

### Branch 2: `feat/business-rules-and-validations`
**Foco:** Implementar RF01, RF02, RF04 e tratamento de erros (Try/Catch).
1.  **RF04 (Atualizar):** Adicionar a funcionalidade de atualizar os dados de um aluno previamente cadastrado, adicionando os métodos necessários nas interfaces e classes existentes, mantendo o padrão de nomes.
2.  **Validações (Regras de Negócio):**
    *   *Idade:* Garantir que a idade esteja dentro de um intervalo válido.
    *   *RA (Matrícula):* Não permitir cadastro com matrícula duplicada.
    *   *Capacidade:* Verificar se o cadastro (array `arm`) atingiu sua capacidade máxima.
    *   *Existência:* Não permitir atualizar ou remover matrículas inexistentes.
3.  **Try/Catch ("Try e catch em tudo"):** Implementar blocos `try/catch` para capturar entradas inválidas (ex: formato de número incorreto) em toda a aplicação. Nenhuma mensagem técnica ou erro deve aparecer para o usuário.

### Branch 3: `feat/storage-and-javadoc`
**Foco:** Correções no armazenamento, formatação de listagem (RF03) e Javadoc.
1.  **Correções no Armazenamento:** Ajustar a lógica de remoção e listagem no `Armazenador.java` para lidar corretamente com espaços `null` no array.
2.  **RF03 (Listagem):** Garantir que a listagem de alunos exiba todas as informações formatadas e permita listar os nomes no formato de bibliografia ou normal (usando o método `getNomeBiblio()` já existente).
3.  **Javadocs ("Java docs"):** Adicionar comentários Javadoc profissionais **em português** para todas as classes, interfaces e métodos, explicando parâmetros, retornos e regras de negócio. Comentar o código de forma legível conforme exigido pelo professor.

---

### Phase 3: Implementation (Next Steps)
O plano está formalizado e inclui regras estritas para manter o idioma português e preservar todos os nomes originais de arquivos, funções e variáveis.

**Ação Pendente:** "Estou pronto. O plano atende a todas as novas restrições. Me avise quando desejar criar a primeira branch (`refactor/architecture-and-io`) e prosseguir, lembrando que pedirei sua autorização antes de modificar qualquer código."