﻿insert into perfis_usuario values (1, 'administradores');
insert into perfis_usuario values (2, 'professores');
insert into perfis_usuario values (3, 'funcionarios');

insert into usuarios values ('administradores', 1, 'admin', 'Administrador', 'admin');
insert into usuarios values ('professores', 2, 'prof', 'Professor', 'prof');
insert into usuarios values ('funcionarios', 3, 'func', 'Funcionario', 'func');

insert into perfilusuario_usuario values (1, 1);
insert into perfilusuario_usuario values (2, 2);
insert into perfilusuario_usuario values (3, 3);