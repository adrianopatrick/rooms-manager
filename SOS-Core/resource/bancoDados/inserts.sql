CREATE DATABASE sosdb;

INSERT INTO perfis_admins (id,nome) VALUES (nextval('seq_perfis_admins'), 'master');
INSERT INTO perfis_admins (id,nome) VALUES (nextval('seq_perfis_admins'), 'basic');

INSERT INTO usuarios (dtype, id, email, nome, senha, perfil_id) VALUES ('administradores', nextval('seq_usuarios'), 'admin', 'Administrador', 'admin', 1);
INSERT INTO usuarios (dtype, id, email, nome, senha, perfil_id) VALUES ('administradores', nextval('seq_usuarios'), 'func', 'Funcionario', 'func', 2);
INSERT INTO usuarios (dtype, id, email, nome, senha) VALUES ('professores', nextval('seq_usuarios'), 'prof', 'Professor', 'prof');

INSERT INTO permissoes (id, tipo, "value", perfiladmin_id) VALUES (nextval('seq_permissoes'), 2, true,1);
INSERT INTO permissoes (id, tipo, "value", perfiladmin_id) VALUES (nextval('seq_permissoes'), 3, true,1);
INSERT INTO permissoes (id, tipo, "value", perfiladmin_id) VALUES (nextval('seq_permissoes'), 1, true,1);

INSERT INTO permissoes (id, tipo, "value", perfiladmin_id) VALUES (nextval('seq_permissoes'), 2, false,2);
INSERT INTO permissoes (id, tipo, "value", perfiladmin_id) VALUES (nextval('seq_permissoes'), 3, false,2);
INSERT INTO permissoes (id, tipo, "value", perfiladmin_id) VALUES (nextval('seq_permissoes'), 1, false,2);
