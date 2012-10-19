CREATE DATABASE sosdb;

INSERT INTO perfis_admins VALUES (nextval('seq_perfis_admins'), 'master');
INSERT INTO perfis_admins VALUES (nextval('seq_perfis_admins'), 'basic');

INSERT INTO usuarios VALUES ('administradores', nextval('seq_usuarios'), 'admin', 'Administrador', 'admin', 1);
INSERT INTO usuarios VALUES ('administradores', nextval('seq_usuarios'), 'func', 'Funcionario', 'func', 2);
INSERT INTO usuarios VALUES ('professores', nextval('seq_usuarios'), 'prof', 'Professor', 'prof');

INSERT INTO permissoes VALUES (nextval('seq_permissoes'), 2, true,1);
INSERT INTO permissoes VALUES (nextval('seq_permissoes'), 3, true,1);
INSERT INTO permissoes VALUES (nextval('seq_permissoes'), 1, true,1);
