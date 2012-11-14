CREATE DATABASE sosdb;

INSERT INTO perfis_admins (id,nome) VALUES (nextval('seq_perfis_admins'), 'master');
INSERT INTO perfis_admins (id,nome) VALUES (nextval('seq_perfis_admins'), 'basic');
INSERT INTO perfis_admins (id,nome) VALUES (nextval('seq_perfis_admins'), 'externo');

INSERT INTO usuarios (dtype, id, email, nome, senha, perfil) VALUES ('administradores', nextval('seq_usuarios'), 'admin', 'Administrador', 'admin', 1);
INSERT INTO usuarios (dtype, id, email, nome, senha, perfil) VALUES ('administradores', nextval('seq_usuarios'), 'func', 'Funcionario', 'func', 2);
INSERT INTO usuarios (dtype, id, email, nome, senha, perfil) VALUES ('professores', nextval('seq_usuarios'), 'prof', 'Professor', 'prof', 3);

INSERT INTO permissoes (id, tipo, "value", perfiladmin_id) VALUES (nextval('seq_permissoes'), 2, true,1);
INSERT INTO permissoes (id, tipo, "value", perfiladmin_id) VALUES (nextval('seq_permissoes'), 3, true,1);
INSERT INTO permissoes (id, tipo, "value", perfiladmin_id) VALUES (nextval('seq_permissoes'), 1, true,1);

INSERT INTO permissoes (id, tipo, "value", perfiladmin_id) VALUES (nextval('seq_permissoes'), 2, false,2);
INSERT INTO permissoes (id, tipo, "value", perfiladmin_id) VALUES (nextval('seq_permissoes'), 3, false,2);
INSERT INTO permissoes (id, tipo, "value", perfiladmin_id) VALUES (nextval('seq_permissoes'), 1, false,2);

INSERT INTO acessorios (id, descricao, nome) VALUES (nextval('seq_acessorios'), 'Projeta imagens', 'Projetor');
INSERT INTO acessorios (id, descricao, nome) VALUES (nextval('seq_acessorios'), 'Pinceis para quadro branco', 'Pinceis');
INSERT INTO acessorios (id, descricao, nome) VALUES (nextval('seq_acessorios'), 'Quadro para maior interação', 'Quadro Digital');

INSERT INTO salas (id, capacidade, estado, nome, tiposala, observacao) VALUES (nextval('seq_salas'), 40, 'LIVRE', '01', 'Sala de Aula', '');
INSERT INTO salas (id, capacidade, estado, nome, tiposala, observacao) VALUES (nextval('seq_salas'), 30, 'LIVRE', 'Lab-01', 'Laboratório', '');
INSERT INTO salas (id, capacidade, estado, nome, tiposala, observacao) VALUES (nextval('seq_salas'), 150, 'LIVRE', '101', 'Auditório', 'Primeiro Andar');
