-- Usuario já cadastrado
MERGE INTO Usuario (id, userName, senha) KEY(userName) VALUES (RANDOM_UUID(), 'admin', 'admin');

