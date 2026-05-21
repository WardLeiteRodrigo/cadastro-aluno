------------------------------------------------------------------------
PROJETO: Cadastro de Alunos (LED - Laboratorio de Estruturas Dinamicas)
------------------------------------------------------------------------

OBJETIVO DO PROJETO:
    Aplicacao Java para cadastrar, listar, atualizar e remover alunos,
    desenvolvida como atividade da disciplina LED (Prof. Julio Arakaki),
    Ciencia da Computacao - PUC-SP, 1o. Semestre de 2026.

VERSAO / DATA:
    3.0 - 2026/05/19

AUTORES:
    Kaua Bezerra
    Liam Vedovato
    Raul Kolaric
    Rodrigo Ward

ORGANIZACAO (pacotes):
    main    -> App.java, CadastroAlunos.java
    model   -> Pessoa, Aluno, NomePessoa, Texto, IdadeInvalidaException
    storage -> IArmazenador, ArmazenadorLista, Armazenador e excecoes de dominio
               (RaDuplicadoException, RaInexistenteException,
                CadastroCheioException)
    ui      -> IMenu/MenuGrafico/MenuTexto e IIO/IOGrafico/IOTexto
    test    -> classes de teste manual

ESTRUTURA E MODO PADRAO:
    - O aplicativo inicia diretamente em modo grafico.
    - O usuario nao precisa escolher entre modo grafico e modo texto.
    - O usuario nao precisa informar a quantidade de alunos.
    - A estrutura padrao de armazenamento e uma lista ligada manual
      (ArmazenadorLista), sem capacidade fixa.
    - As classes de modo texto e de vetor permanecem no projeto como
      alternativas didaticas, mas nao sao solicitadas na inicializacao.

COMO INICIAR O PROJETO (BlueJ):
    1) Abra o arquivo package.bluej na raiz do projeto.
    2) Compile todos os pacotes (botao "Compile").
    3) Clique com o botao direito na classe main.App e escolha
       "void main(String[] args)".
    4) Use o menu grafico exibido para inserir, remover, listar, atualizar,
       salvar, carregar ou sair.

INSTRUCOES PARA O USUARIO:
    - Em qualquer prompt grafico, clicar Cancelar aborta a operacao.
    - A idade deve estar entre 16 e 120 anos.
    - O semestre deve estar entre 1 e 12.
    - O RA e unico: nao e possivel cadastrar dois alunos com o mesmo RA.
    - Os arquivos de cadastro sao salvos em formato binario com extensao .dat.
    - Erros sao mostrados em mensagens amigaveis (sem stack trace).
