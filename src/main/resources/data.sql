alter sequence estudiante_seq increment by 1;
alter sequence clase_seq increment by 1;
alter sequence inscripcion_seq increment by 1;

insert into estudiante (id, codigo, nombre, especialidad, grado, fecha_creacion, fecha_modificacion) values (nextval('estudiante_seq'), 100, 'JONES', 'HISTORIA', 'GR', current_date(), current_date());
insert into estudiante (id, codigo, nombre, especialidad, grado, fecha_creacion, fecha_modificacion) values (nextval('estudiante_seq'), 150, 'PARKS', 'CONTABILIDAD', 'SO', current_date(), current_date());
insert into estudiante (id, codigo, nombre, especialidad, grado, fecha_creacion, fecha_modificacion) values (nextval('estudiante_seq'), 200, 'BAKER', 'MATEMATICAS', 'GR', current_date(), current_date());
insert into estudiante (id, codigo, nombre, especialidad, grado, fecha_creacion, fecha_modificacion) values (nextval('estudiante_seq'), 250, 'GLASS', 'HISTORIA', 'SN', current_date(), current_date());
insert into estudiante (id, codigo, nombre, especialidad, grado, fecha_creacion, fecha_modificacion) values (nextval('estudiante_seq'), 300, 'BAKER', 'CONTABILIDAD', 'SN', current_date(), current_date());
insert into estudiante (id, codigo, nombre, especialidad, grado, fecha_creacion, fecha_modificacion) values (nextval('estudiante_seq'), 350, 'RUSSEL', 'MATEMATICAS', 'JR', current_date(), current_date());
insert into estudiante (id, codigo, nombre, especialidad, grado, fecha_creacion, fecha_modificacion) values (nextval('estudiante_seq'), 400, 'REY', 'CONTABILIDAD', 'FR', current_date(), current_date());
insert into estudiante (id, codigo, nombre, especialidad, grado, fecha_creacion, fecha_modificacion) values (nextval('estudiante_seq'), 450, 'JONES', 'HISTORIA', 'SN', current_date(), current_date());
insert into clase (id, nombre, horario, aula, fecha_creacion, fecha_modificacion) values (nextval('clase_seq'), 'BA200', 'M-F9', 'SC110', current_date(), current_date());
insert into clase (id, nombre, horario, aula, fecha_creacion, fecha_modificacion) values (nextval('clase_seq'), 'BD445', 'MWF3', 'SC213', current_date(), current_date());
insert into clase (id, nombre, horario, aula, fecha_creacion, fecha_modificacion) values (nextval('clase_seq'), 'BF410', 'MWF8', 'SC213', current_date(), current_date());
insert into clase (id, nombre, horario, aula, fecha_creacion, fecha_modificacion) values (nextval('clase_seq'), 'CS150', 'MWF3', 'EA304', current_date(), current_date());
insert into clase (id, nombre, horario, aula, fecha_creacion, fecha_modificacion) values (nextval('clase_seq'), 'CS250', '', '', current_date(), current_date());
insert into inscripcion (id, estudiante_id, clase_id, posicion, fecha_creacion, fecha_modificacion) values (nextval('inscripcion_seq'), 1, 2, 1, current_date(), current_date());
insert into inscripcion (id, estudiante_id, clase_id, posicion, fecha_creacion, fecha_modificacion) values (nextval('inscripcion_seq'), 2, 1, 1, current_date(), current_date());
insert into inscripcion (id, estudiante_id, clase_id, posicion, fecha_creacion, fecha_modificacion) values (nextval('inscripcion_seq'), 3, 2, 2, current_date(), current_date());
insert into inscripcion (id, estudiante_id, clase_id, posicion, fecha_creacion, fecha_modificacion) values (nextval('inscripcion_seq'), 3, 5, 1, current_date(), current_date());
insert into inscripcion (id, estudiante_id, clase_id, posicion, fecha_creacion, fecha_modificacion) values (nextval('inscripcion_seq'), 5, 4, 1, current_date(), current_date());
insert into inscripcion (id, estudiante_id, clase_id, posicion, fecha_creacion, fecha_modificacion) values (nextval('inscripcion_seq'), 7, 1, 2, current_date(), current_date());
insert into inscripcion (id, estudiante_id, clase_id, posicion, fecha_creacion, fecha_modificacion) values (nextval('inscripcion_seq'), 7, 3, 1, current_date(), current_date());
insert into inscripcion (id, estudiante_id, clase_id, posicion, fecha_creacion, fecha_modificacion) values (nextval('inscripcion_seq'), 7, 5, 2, current_date(), current_date());
insert into inscripcion (id, estudiante_id, clase_id, posicion, fecha_creacion, fecha_modificacion) values (nextval('inscripcion_seq'), 8, 1, 3, current_date(), current_date());

