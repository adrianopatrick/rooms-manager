﻿insert into perfis_usuario values (1, 'administradores');
insert into perfis_usuario values (2, 'professores');
insert into perfis_usuario values (3, 'funcionarios');

insert into usuarios values ('administradores', 1, 'h2milhome', 'Herbeth', '123');
insert into usuarios values ('professores', 2, 'joe', 'Joerlan', '123');
insert into usuarios values ('funcionarios', 3, 'tiago@', 'Tiago', '123');

insert into perfilusuario_usuario values (1, 1);
insert into perfilusuario_usuario values (2, 2);
insert into perfilusuario_usuario values (3, 3);