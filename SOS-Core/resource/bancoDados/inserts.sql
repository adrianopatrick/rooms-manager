INSERT INTO perfis_admins VALUES (1, 'master');

INSERT INTO usuarios VALUES ('administradores', 1, 'admin', 'Administrador', 'admin', 1);
INSERT INTO usuarios VALUES ('professores', 2, 'prof', 'Professor', 'prof');

INSERT INTO permissoes VALUES (1, 2, true,1);
INSERT INTO permissoes VALUES (2, 3, true,1);
INSERT INTO permissoes VALUES (3, 1, true,1);


<-- INSERT INTO perfis_admins_permissoes VALUES (1, 1);
<-- INSERT INTO perfis_admins_permissoes VALUES (1, 2);
<-- INSERT INTO perfis_admins_permissoes VALUES (1, 3);
<-- INSERT INTO perfis_admins_permissoes VALUES (1, 4);
<-- INSERT INTO perfis_admins_permissoes VALUES (2, 1);
<-- INSERT INTO perfis_admins_permissoes VALUES (2, 2);
<-- INSERT INTO perfis_admins_permissoes VALUES (3, 1);
<-- INSERT INTO perfis_admins_permissoes VALUES (3, 2);
<-- INSERT INTO perfis_admins_permissoes VALUES (3, 3);