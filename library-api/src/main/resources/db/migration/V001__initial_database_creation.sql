CREATE TABLE IF NOT EXISTS categorias
(

    id   INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nome VARCHAR(300) NOT NULL

);

CREATE TABLE IF NOT EXISTS livros
(

    id              INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    titulo          VARCHAR(300) NOT NULL,
    autor           VARCHAR(300) NOT NULL,
    isbn            VARCHAR(13)  NOT NULL,
    data_publicacao DATE         NOT NULL,
    categoria_id    INTEGER      NOT NULL REFERENCES categorias (id)

);

CREATE TABLE IF NOT EXISTS usuarios
(

    id            INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nome          VARCHAR(200) NOT NULL,
    email         VARCHAR(250) NOT NULL,
    data_cadastro DATE         NOT NULL,
    telefone      VARCHAR(11)  NOT NULL

);

CREATE TABLE IF NOT EXISTS emprestimos
(

    id              INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    usuario_id      INTEGER   NOT NULL REFERENCES usuarios (id),
    livro_id        INTEGER   NOT NULL REFERENCES livros (id),
    data_emprestimo TIMESTAMP NOT NULL,
    data_devolucao  TIMESTAMP NULL,
    status          INTEGER   NOT NULL

);
