-- Usuario já cadastrado
MERGE INTO Usuario (id, userName, senha) KEY(userName) VALUES (RANDOM_UUID(), 'admin', 'admin');

-- Autores
MERGE INTO Autor (id, nome, dataNascimento) KEY(nome)
VALUES (RANDOM_UUID(), 'Machado de Assis', DATE '1839-06-21');
MERGE INTO Autor (id, nome, dataNascimento) KEY(nome)
VALUES (RANDOM_UUID(), 'George Orwell', DATE '1903-06-25');
MERGE INTO Autor (id, nome, dataNascimento) KEY(nome)
VALUES (RANDOM_UUID(), 'Clarice Lispector', DATE '1920-12-10');
MERGE INTO Autor (id, nome, dataNascimento) KEY(nome)
VALUES (RANDOM_UUID(), 'José Saramago', DATE '1922-11-16');
MERGE INTO Autor (id, nome, dataNascimento) KEY(nome)
VALUES (RANDOM_UUID(), 'J.K. Rowling', DATE '1965-07-31');

-- Categorias
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Ficção Científica');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Literatura Brasileira');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Realismo');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Distopia');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Fantasia');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Literatura Portuguesa');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Romance');
MERGE INTO Categoria (id, nome) KEY(nome) VALUES (RANDOM_UUID(), 'Drama');
