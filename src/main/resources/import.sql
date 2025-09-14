-- Usuario já cadastrado
MERGE INTO Usuario (id, userName, senha) KEY(login) VALUES (RANDOM_UUID(), 'admin', 'admin');

---
-- Autores já definidos
MERGE INTO Autor (id, nome, data_nascimento) KEY(nome)
VALUES (RANDOM_UUID(), 'Machado de Assis', DATE '1839-06-21');

MERGE INTO Autor (id, nome, data_nascimento) KEY(nome)
VALUES (RANDOM_UUID(), 'George Orwell', DATE '1903-06-25');

MERGE INTO Autor (id, nome, data_nascimento) KEY(nome)
VALUES (RANDOM_UUID(), 'Clarice Lispector', DATE '1920-12-10');

MERGE INTO Autor (id, nome, data_nascimento) KEY(nome)
VALUES (RANDOM_UUID(), 'José Saramago', DATE '1922-11-16');

MERGE INTO Autor (id, nome, data_nascimento) KEY(nome)
VALUES (RANDOM_UUID(), 'J.K. Rowling', DATE '1965-07-31');


---
-- Categorias
-- Adicionando categorias relevantes para os autores
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Ficção Científica');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Literatura Brasileira');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Realismo');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Distopia');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Fantasia');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Literatura Portuguesa');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Romance');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Drama');


---
-- Livros
-- Associando os livros aos autores e categorias
-- Livros de Machado de Assis (Literatura Brasileira, Realismo, Romance)
MERGE INTO Livro (id, titulo, autor_id) KEY(titulo)
SELECT RANDOM_UUID(), 'Memórias Póstumas de Brás Cubas', a.id FROM Autor a WHERE a.nome = 'Machado de Assis';

MERGE INTO Livro (id, titulo, autor_id) KEY(titulo)
SELECT RANDOM_UUID(), 'Dom Casmurro', a.id FROM Autor a WHERE a.nome = 'Machado de Assis';

-- Livros de George Orwell (Distopia, Ficção Científica)
MERGE INTO Livro (id, titulo, autor_id) KEY(titulo)
SELECT RANDOM_UUID(), '1984', a.id FROM Autor a WHERE a.nome = 'George Orwell';

MERGE INTO Livro (id, titulo, autor_id) KEY(titulo)
SELECT RANDOM_UUID(), 'A Revolução dos Bichos', a.id FROM Autor a WHERE a.nome = 'George Orwell';

-- Livros de Clarice Lispector (Literatura Brasileira, Drama)
MERGE INTO Livro (id, titulo, autor_id) KEY(titulo)
SELECT RANDOM_UUID(), 'A Hora da Estrela', a.id FROM Autor a WHERE a.nome = 'Clarice Lispector';

-- Livros de José Saramago (Literatura Portuguesa, Ficção Científica)
MERGE INTO Livro (id, titulo, autor_id) KEY(titulo)
SELECT RANDOM_UUID(), 'Ensaio sobre a Cegueira', a.id FROM Autor a WHERE a.nome = 'José Saramago';

-- Livros de J.K. Rowling (Fantasia)
MERGE INTO Livro (id, titulo, autor_id) KEY(titulo)
SELECT RANDOM_UUID(), 'Harry Potter e a Pedra Filosofal', a.id FROM Autor a WHERE a.nome = 'J.K. Rowling';

MERGE INTO Livro (id, titulo, autor_id) KEY(titulo)
SELECT RANDOM_UUID(), 'Harry Potter e a Câmara Secreta', a.id FROM Autor a WHERE a.nome = 'J.K. Rowling';

MERGE INTO Livro (id, titulo, autor_id) KEY(titulo)
SELECT RANDOM_UUID(), 'Harry Potter e o Prisioneiro de Azkaban', a.id FROM Autor a WHERE a.nome = 'J.K. Rowling';


---
-- Associação de Livros e Categorias (tabela de junção)
-- A classe Livro tem uma lista de Categorias, então usamos uma tabela de junção.
MERGE INTO Livro_Categoria (livro_id, categoria_id) KEY(livro_id, categoria_id)
-- Memórias Póstumas de Brás Cubas
SELECT l.id, c.id FROM Livro l, Categoria c WHERE l.titulo = 'Memórias Póstumas de Brás Cubas' AND c.nome = 'Literatura Brasileira';
MERGE INTO Livro_Categoria (livro_id, categoria_id) KEY(livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c WHERE l.titulo = 'Memórias Póstumas de Brás Cubas' AND c.nome = 'Realismo';

-- Dom Casmurro
MERGE INTO Livro_Categoria (livro_id, categoria_id) KEY(livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c WHERE l.titulo = 'Dom Casmurro' AND c.nome = 'Literatura Brasileira';
MERGE INTO Livro_Categoria (livro_id, categoria_id) KEY(livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c WHERE l.titulo = 'Dom Casmurro' AND c.nome = 'Romance';

-- 1984
MERGE INTO Livro_Categoria (livro_id, categoria_id) KEY(livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c WHERE l.titulo = '1984' AND c.nome = 'Distopia';
MERGE INTO Livro_Categoria (livro_id, categoria_id) KEY(livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c WHERE l.titulo = '1984' AND c.nome = 'Ficção Científica';

-- A Revolução dos Bichos
MERGE INTO Livro_Categoria (livro_id, categoria_id) KEY(livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c WHERE l.titulo = 'A Revolução dos Bichos' AND c.nome = 'Distopia';

-- A Hora da Estrela
MERGE INTO Livro_Categoria (livro_id, categoria_id) KEY(livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c WHERE l.titulo = 'A Hora da Estrela' AND c.nome = 'Literatura Brasileira';
MERGE INTO Livro_Categoria (livro_id, categoria_id) KEY(livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c WHERE l.titulo = 'A Hora da Estrela' AND c.nome = 'Drama';

-- Ensaio sobre a Cegueira
MERGE INTO Livro_Categoria (livro_id, categoria_id) KEY(livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c WHERE l.titulo = 'Ensaio sobre a Cegueira' AND c.nome = 'Literatura Portuguesa';
MERGE INTO Livro_Categoria (livro_id, categoria_id) KEY(livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c WHERE l.titulo = 'Ensaio sobre a Cegueira' AND c.nome = 'Ficção Científica';

-- Harry Potter e a Pedra Filosofal
MERGE INTO Livro_Categoria (livro_id, categoria_id) KEY(livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c WHERE l.titulo = 'Harry Potter e a Pedra Filosofal' AND c.nome = 'Fantasia';

-- Harry Potter e a Câmara Secreta
MERGE INTO Livro_Categoria (livro_id, categoria_id) KEY(livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c WHERE l.titulo = 'Harry Potter e a Câmara Secreta' AND c.nome = 'Fantasia';

-- Harry Potter e o Prisioneiro de Azkaban
MERGE INTO Livro_Categoria (livro_id, categoria_id) KEY(livro_id, categoria_id)
SELECT l.id, c.id FROM Livro l, Categoria c WHERE l.titulo = 'Harry Potter e o Prisioneiro de Azkaban' AND c.nome = 'Fantasia';