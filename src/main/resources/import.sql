\\Usuario já cadastrado
MERGE INTO Usuario (id, login, senha, email) KEY(login) VALUES (RANDOM_UUID(), 'admin', 'admin', 'admin@admin.com');

\\autores 
MERGE INTO Autor (id, nome, data_nascimento)
KEY(nome)
VALUES (RANDOM_UUID(), 'Machado de Assis', DATE '1839-06-21');

MERGE INTO Autor (id, nome, data_nascimento)
KEY(nome)
VALUES (RANDOM_UUID(), 'George Orwell', DATE '1903-06-25');

MERGE INTO Autor (id, nome, data_nascimento)
KEY(nome)
VALUES (RANDOM_UUID(), 'Clarice Lispector', DATE '1920-12-10');

MERGE INTO Autor (id, nome, data_nascimento)
KEY(nome)
VALUES (RANDOM_UUID(), 'José Saramago', DATE '1922-11-16');

MERGE INTO Autor (id, nome, data_nascimento)
KEY(nome)
VALUES (RANDOM_UUID(), 'J.K. Rowling', DATE '1965-07-31');

\\livros 


