------------------------------------------------------------------------
PROJETO: Cadastro de Alunos (LED - Laboratorio de Estruturas Dinamicas)
------------------------------------------------------------------------

OBJETIVO DO PROJETO:
    Aplicacao Java para cadastrar, listar, atualizar e remover alunos,
    desenvolvida como atividade da disciplina LED (Prof. Julio Arakaki),
    Ciencia da Computacao - PUC-SP, 1o. Semestre de 2026.

VERSAO / DATA:
    1.0 - 2026/04/07

AUTORES:
    Kaua Bezerra
    Liam Vedovato
    Raul Kolaric
    Rodrigo Ward

ORGANIZACAO (pacotes):
    main    -> App.java, CadastroAlunos.java
    model   -> Pessoa, Aluno, NomePessoa, Texto, IdadeInvalidaException
    storage -> IArmazenador, Armazenador e excecoes de dominio
               (RaDuplicadoException, RaInexistenteException,
                CadastroCheioException)
    ui      -> IMenu/MenuGrafico/MenuTexto e IIO/IOGrafico/IOTexto
    test    -> classes de teste manual

COMO INICIAR O PROJETO (BlueJ):
    1) Abra o arquivo package.bluej na raiz do projeto.
    2) Compile todos os pacotes (botao "Compile").
    3) Clique com o botao direito na classe main.App e escolha
       "void main(String[] args)".
    4) Escolha o modo de interacao: 1 - Grafico ou 2 - Texto.
    5) Informe a capacidade do cadastro e use o menu de opcoes.

INSTRUCOES PARA O USUARIO:
    - Em qualquer prompt grafico, clicar Cancelar aborta a operacao.
    - Em modo texto, digite "cancelar" para abortar uma entrada.
    - A idade deve estar entre 16 e 120 anos.
    - O RA e unico: nao e possivel cadastrar dois alunos com o mesmo RA.
    - Erros sao mostrados em mensagens amigaveis (sem stack trace).
