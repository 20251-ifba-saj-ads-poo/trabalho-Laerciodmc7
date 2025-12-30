-- Limpar dados para evitar duplicação
DELETE FROM Livro_Categoria;

DELETE FROM Livro;

DELETE FROM Categoria;

-- Autores (usando MERGE para evitar duplicação, com RANDOM_UUID() só na primeira inserção)
MERGE INTO Autor (id, nome, dataNascimento) KEY(nome) VALUES (RANDOM_UUID(), 'Machado de Assis', '1839-06-21');
MERGE INTO Autor (id, nome, dataNascimento) KEY(nome) VALUES (RANDOM_UUID(), 'George Orwell', '1903-06-25');
MERGE INTO Autor (id, nome, dataNascimento) KEY(nome) VALUES (RANDOM_UUID(), 'Clarice Lispector', '1920-12-10');
MERGE INTO Autor (id, nome, dataNascimento) KEY(nome) VALUES (RANDOM_UUID(), 'Jose Saramago', '1922-11-16');
MERGE INTO Autor (id, nome, dataNascimento) KEY(nome) VALUES (RANDOM_UUID(), 'J.K. Rowling', '1965-07-31');

-- Categorias
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Ficção Científica');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Literatura Brasileira');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Realismo');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Distopia');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Fantasia');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Literatura Portuguesa');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Romance');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Drama');

-- Livros
INSERT INTO Livro (id, nome, dataLancamento, autor_id, qntPaginas) VALUES (
    RANDOM_UUID(),
    'Dom Casmurro',
    '1899-01-01',
    (SELECT id FROM Autor WHERE nome = 'Machado de Assis' LIMIT 1),
    256
);

INSERT INTO Livro (id, nome, dataLancamento, autor_id, qntPaginas) VALUES (
    RANDOM_UUID(),
    'Memórias Póstumas de Brás Cubas',
    '1881-01-01',
    (SELECT id FROM Autor WHERE nome = 'Machado de Assis' LIMIT 1),
    224
);

INSERT INTO Livro (id, nome, dataLancamento, autor_id, qntPaginas) VALUES (
    RANDOM_UUID(),
    '1984',
    '1949-06-08',
    (SELECT id FROM Autor WHERE nome = 'George Orwell' LIMIT 1),
    328
);

INSERT INTO Livro (id, nome, dataLancamento, autor_id, qntPaginas) VALUES (
    RANDOM_UUID(),
    'c',
    '1945-08-17',
    (SELECT id FROM Autor WHERE nome = 'George Orwell' LIMIT 1),
    112
);

INSERT INTO Livro (id, nome, dataLancamento, autor_id, qntPaginas) VALUES (
    RANDOM_UUID(),
    'A Hora da Estrela',
    '1977-01-01',
    (SELECT id FROM Autor WHERE nome = 'Clarice Lispector' LIMIT 1),
    96
);

INSERT INTO Livro (id, nome, dataLancamento, autor_id, qntPaginas) VALUES (
    RANDOM_UUID(),
    'Perto do Coração Selvagem',
    '1943-01-01',
    (SELECT id FROM Autor WHERE nome = 'Clarice Lispector' LIMIT 1),
    192
);

INSERT INTO Livro (id, nome, dataLancamento, autor_id, qntPaginas) VALUES (
    RANDOM_UUID(),
    'Ensaio sobre a Cegueira',
    '1995-01-01',
    (SELECT id FROM Autor WHERE nome = 'Jose Saramago' LIMIT 1),
    352
);

INSERT INTO Livro (id, nome, dataLancamento, autor_id, qntPaginas) VALUES (
    RANDOM_UUID(),
    'O Evangelho Segundo Jesus Cristo',
    '1991-01-01',
    (SELECT id FROM Autor WHERE nome = 'Jose Saramago' LIMIT 1),
    336
);

INSERT INTO Livro (id, nome, dataLancamento, autor_id, qntPaginas) VALUES (
    RANDOM_UUID(),
    'Harry Potter e a Pedra Filosofal',
    '1997-06-26',
    (SELECT id FROM Autor WHERE nome = 'J.K. Rowling' LIMIT 1),
    223
);

INSERT INTO Livro (id, nome, dataLancamento, autor_id, qntPaginas) VALUES (
    RANDOM_UUID(),
    'Harry Potter e a Câmara Secreta',
    '1998-07-02',
    (SELECT id FROM Autor WHERE nome = 'J.K. Rowling' LIMIT 1),
    251
);


-- Dom Casmurro - Literatura Brasileira, Romance, Realismo
INSERT INTO Livro_Categoria (livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c
WHERE l.nome = 'Dom Casmurro' AND c.nome IN ('Literatura Brasileira', 'Romance', 'Realismo');

-- Memórias Póstumas de Brás Cubas - Literatura Brasileira, Romance, Realismo
INSERT INTO Livro_Categoria (livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c
WHERE l.nome = 'Memórias Póstumas de Brás Cubas' AND c.nome IN ('Literatura Brasileira', 'Romance', 'Realismo');

-- 1984 - Ficção Científica, Distopia
INSERT INTO Livro_Categoria (livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c
WHERE l.nome = '1984' AND c.nome IN ('Ficção Científica', 'Distopia');

-- A Revolução dos Bichos - Ficção Científica, Distopia
INSERT INTO Livro_Categoria (livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c
WHERE l.nome = 'A Revolução dos Bichos' AND c.nome IN ('Ficção Científica', 'Distopia');

-- A Hora da Estrela - Literatura Brasileira, Romance, Drama
INSERT INTO Livro_Categoria (livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c
WHERE l.nome = 'A Hora da Estrela' AND c.nome IN ('Literatura Brasileira', 'Romance', 'Drama');

-- Perto do Coração Selvagem - Literatura Brasileira, Romance, Drama
INSERT INTO Livro_Categoria (livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c
WHERE l.nome = 'Perto do Coração Selvagem' AND c.nome IN ('Literatura Brasileira', 'Romance', 'Drama');

-- Ensaio sobre a Cegueira - Literatura Portuguesa, Ficção Científica, Drama
INSERT INTO Livro_Categoria (livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c
WHERE l.nome = 'Ensaio sobre a Cegueira' AND c.nome IN ('Literatura Portuguesa', 'Ficção Científica', 'Drama');

-- O Evangelho Segundo Jesus Cristo - Literatura Portuguesa, Romance, Drama
INSERT INTO Livro_Categoria (livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c
WHERE l.nome = 'O Evangelho Segundo Jesus Cristo' AND c.nome IN ('Literatura Portuguesa', 'Romance', 'Drama');

-- Harry Potter e a Pedra Filosofal - Fantasia, Romance
INSERT INTO Livro_Categoria (livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c
WHERE l.nome = 'Harry Potter e a Pedra Filosofal' AND c.nome IN ('Fantasia', 'Romance');

-- Harry Potter e a Câmara Secreta - Fantasia, Romance
INSERT INTO Livro_Categoria (livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c
WHERE l.nome = 'Harry Potter e a Câmara Secreta' AND c.nome IN ('Fantasia', 'Romance');
