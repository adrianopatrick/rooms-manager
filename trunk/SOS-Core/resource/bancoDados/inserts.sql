CREATE DATABASE sosdb;

INSERT INTO perfis_admins (id,nome) VALUES (nextval('seq_perfis_admins'), 'Administrador');
INSERT INTO perfis_admins (id,nome) VALUES (nextval('seq_perfis_admins'), 'Funcionario');

INSERT INTO usuarios (dtype, id, email, nome, senha, perfil_id) VALUES ('administradores', nextval('seq_usuarios'), 'admin@.', 'Administrador', 'admin', 1);
INSERT INTO usuarios (dtype, id, email, nome, senha, perfil_id) VALUES ('administradores', nextval('seq_usuarios'), 'func@.', 'Funcionario', 'func', 2);
INSERT INTO usuarios (dtype, id, email, nome, senha) VALUES ('professores', nextval('seq_usuarios'), 'prof@.', 'Professor', 'prof');

INSERT INTO permissoes (id, tipo, "value", perfiladmin_id) VALUES (nextval('seq_permissoes'), 2, true,1);
INSERT INTO permissoes (id, tipo, "value", perfiladmin_id) VALUES (nextval('seq_permissoes'), 3, true,1);
INSERT INTO permissoes (id, tipo, "value", perfiladmin_id) VALUES (nextval('seq_permissoes'), 1, true,1);

INSERT INTO permissoes (id, tipo, "value", perfiladmin_id) VALUES (nextval('seq_permissoes'), 2, false,2);
INSERT INTO permissoes (id, tipo, "value", perfiladmin_id) VALUES (nextval('seq_permissoes'), 3, false,2);
INSERT INTO permissoes (id, tipo, "value", perfiladmin_id) VALUES (nextval('seq_permissoes'), 1, false,2);

INSERT INTO acessorios (id, descricao, nome) VALUES (nextval('seq_acessorios'), 'Projeta imagens', 'Projetor');
INSERT INTO acessorios (id, descricao, nome) VALUES (nextval('seq_acessorios'), 'Pinceis para quadro branco', 'Pinceis');
INSERT INTO acessorios (id, descricao, nome) VALUES (nextval('seq_acessorios'), 'Quadro para maior interação', 'Quadro Digital');

INSERT INTO salas (id, capacidade, nome, tiposala, observacao) VALUES (nextval('seq_salas'), 40, '01', '2', '');
INSERT INTO salas (id, capacidade, nome, tiposala, observacao) VALUES (nextval('seq_salas'), 30, 'Lab-01', '2', '');
INSERT INTO salas (id, capacidade, nome, tiposala, observacao) VALUES (nextval('seq_salas'), 150, '101', '2', '');
