-- Script que añade una table usuario a la base de datos taller.
-- Lo vamos a llamar desde el programa java.
drop table if exists usuario;
create table usuario(
	usuario varchar(10) primary key not null,
    clave blob not null
)engine innodb;

-- Encriptamos la contraseña con aes_encrypt que es lo mejor que hay de momento.
insert into usuario values
('root',aes_encrypt('root',0)),
('vicente',aes_encrypt('vicente',0));

-- Funcion que comprueba el login de usuario
-- Devuelve 0 si el usuario y la clave son correctos y -1 si no lo son.


select validarUS('root','root');
