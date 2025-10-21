-- ######################################################################
-- # SCRIPT DE INICIALIZAÇÃO ESTÁVEL E IDEAL (import.sql)
-- # Objetivo: Resolver todos os erros de Column Not Found e garantir IDs válidos.
-- ######################################################################

-- ETAPA 1: LIMPEZA DAS TABELAS (Garante um estado limpo para as novas inserções)
-- **A TABELA USUARIO ESTÁ FORA DESTE DELETE PARA PRESERVAR DADOS DINÂMICOS**
DELETE FROM Emprestimo;
DELETE FROM Livro_Categoria;
DELETE FROM Livro;
DELETE FROM Categoria;
DELETE FROM Autor;

-- ######################################################################
-- ETAPA 2: INSERÇÃO DE DADOS MESTRES (Usando UUIDs VÁLIDOS e Nomes Corretos)
-- ######################################################################

-- 2.1 INSERÇÃO/MERGE DO USUÁRIO ADMIN
-- ID: 00000000-0000-0000-0000-000000000001 (UUID Fixo)
MERGE INTO Usuario (id, userName, senha, nomeCompleto, cpf)
KEY(userName)
VALUES ('00000000-0000-0000-0000-000000000001', 'admin', 'admin', 'Administrador Padrão', '000.000.000-00');


-- 2.2 INSERÇÃO DE AUTORES
INSERT INTO Autor (id, nome, dataNascimento) VALUES ('10000000-0000-0000-0000-000000000001', 'J.K. Rowling', '1965-07-31');
INSERT INTO Autor (id, nome, dataNascimento) VALUES ('10000000-0000-0000-0000-000000000002', 'George Orwell', '1903-06-25');
INSERT INTO Autor (id, nome, dataNascimento) VALUES ('10000000-0000-0000-0000-000000000003', 'Clarice Lispector', '1920-12-10');


-- 2.3 INSERÇÃO DE CATEGORIAS (CORRIGIDO: Usando 'nome' no lugar de 'nomeCategoria')
INSERT INTO Categoria (id, nome) VALUES ('20000000-0000-0000-0000-000000000001', 'Fantasia');
INSERT INTO Categoria (id, nome) VALUES ('20000000-0000-0000-0000-000000000002', 'Distopia');
INSERT INTO Categoria (id, nome) VALUES ('20000000-0000-0000-0000-000000000003', 'Conto');


-- 2.4 INSERÇÃO DE LIVROS (Usando IDs e Referências Fixas)
INSERT INTO Livro (id, nome, dataLancamento, autor_id, qntPaginas)
VALUES ('30000000-0000-0000-0000-000000000001', 'Harry Potter e a Pedra Filosofal', '1997-06-26', '10000000-0000-0000-0000-000000000001', 223);

INSERT INTO Livro (id, nome, dataLancamento, autor_id, qntPaginas)
VALUES ('30000000-0000-0000-0000-000000000002', '1984', '1949-06-08', '10000000-0000-0000-0000-000000000002', 328);


-- ######################################################################
-- ETAPA 3: RELAÇÕES (TABELAS N:M)
-- ######################################################################

-- CORRIGIDO: Usando 'categoria_id' (o nome que o Hibernate espera no DB)
-- 3.1 VINCULAÇÃO LIVRO -> CATEGORIA (Harry Potter é Fantasia)
INSERT INTO Livro_Categoria (Livro_id, categoria_id)
VALUES ('30000000-0000-0000-0000-000000000001', '20000000-0000-0000-0000-000000000001');

-- 3.2 VINCULAÇÃO LIVRO -> CATEGORIA (1984 é Distopia)
INSERT INTO Livro_Categoria (Livro_id, categoria_id)
VALUES ('30000000-0000-0000-0000-000000000002', '20000000-0000-0000-0000-000000000002');