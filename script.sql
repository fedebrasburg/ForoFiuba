
delete from materia_materia;
delete from carrera_materias;
delete from opinion;
delete from curso;
delete from catedra;
delete from materia;
delete from departamento;
delete from carrera;

Insert into departamento(id,version,nombre) values (1,0,'Fisica');
Insert into materia(id,version,nombre,departamento_id) values (5,0,'Fisica I',1);
Insert into catedra(id,version,nombre,materia_id) values (20,0,'Garea',5);
Insert into curso(id,version,nombre,catedra_id) values (50,0,'Garea',20);
Insert into catedra(id,version,nombre,materia_id) values (21,0,'Echarri',5);
Insert into curso(id,version,nombre,catedra_id) values (51,0,'Echarri',21);
Insert into catedra(id,version,nombre,materia_id) values (22,0,'Corsini',5);
Insert into curso(id,version,nombre,catedra_id) values (52,0,'Corsini',22);

Insert into materia(id,version,nombre,departamento_id) values (6,0,'Fisica II',1);
Insert into catedra(id,version,nombre,materia_id) values (23,0,'Leone',6);
Insert into curso(id,version,nombre,catedra_id) values (54,0,'Mazzone',23);
Insert into catedra(id,version,nombre,materia_id) values (24,0,'Abraham',6);
Insert into curso(id,version,nombre,catedra_id) values (53,0,'Abraham - Pogio',24);

Insert into materia_materia(materia_correlativas_id, materia_id) values (6,5);

Insert into materia(id,version,nombre,departamento_id) values (7,0,'Fisica III',1);
Insert into catedra(id,version,nombre,materia_id) values (25,0,'Arcondo',7);
Insert into curso(id,version,nombre,catedra_id) values (59,0,'Arcondo',25);
Insert into catedra(id,version,nombre,materia_id) values (26,0,'Aguirre',7);
Insert into curso(id,version,nombre,catedra_id) values (60,0,'Aguirre',26);

Insert into materia_materia(materia_correlativas_id, materia_id) values (7,6);



Insert into departamento(id,version,nombre) values (2,0,'Matematica');
Insert into materia(id,version,nombre,departamento_id) values (8,0,'Analisis II',2);
Insert into catedra(id,version,nombre,materia_id) values (27,0,'Sirne',8);
Insert into curso(id,version,nombre,catedra_id) values (55,0,'Machiunas',27);
Insert into curso(id,version,nombre,catedra_id) values (56,0,'Seminara',27);
Insert into catedra(id,version,nombre,materia_id) values (28,0,'Acero',8);
Insert into curso(id,version,nombre,catedra_id) values (57,0,'Lopez',28);
Insert into curso(id,version,nombre,catedra_id) values (58,0,'Pavon',28);

Insert into materia(id,version,nombre,departamento_id) values (9,0,'Algebra II',2);
Insert into catedra(id,version,nombre,materia_id) values (29,0,'Lopez',9);
Insert into curso(id,version,nombre,catedra_id) values (61,0,'Bennedeto',29);
Insert into curso(id,version,nombre,catedra_id) values (62,0,'Rossi',29);
Insert into catedra(id,version,nombre,materia_id) values (30,0,'Prelat',9);
Insert into curso(id,version,nombre,catedra_id) values (63,0,'Boggi',30);
Insert into curso(id,version,nombre,catedra_id) values (64,0,'Puebla',30);

Insert into materia(id,version,nombre,departamento_id) values (10,0,'Analisis III',2);
Insert into catedra(id,version,nombre,materia_id) values (31,0,'Cachile',10);
Insert into curso(id,version,nombre,catedra_id) values (65,0,'Mauldhart',31);
Insert into catedra(id,version,nombre,materia_id) values (32,0,'Acero',10);
Insert into curso(id,version,nombre,catedra_id) values (66,0,'Peruzzi',32);

Insert into materia(id,version,nombre,departamento_id) values (11,0,'Probabilidad y estadistica',2);
Insert into catedra(id,version,nombre,materia_id) values (33,0,'Gil',11);
Insert into curso(id,version,nombre,catedra_id) values (67,0,'Porta',33);
Insert into catedra(id,version,nombre,materia_id) values (34,0,'Grynberg',11);
Insert into curso(id,version,nombre,catedra_id) values (68,0,'Bello',34);

Insert into materia_materia(materia_correlativas_id, materia_id) values (10,9);
Insert into materia_materia(materia_correlativas_id, materia_id) values (10,8);


Insert into departamento(id,version,nombre) values (3,0,'Computacion');
Insert into materia(id,version,nombre,departamento_id) values (12,0,'Algoritmos y programacion I',3);
Insert into catedra(id,version,nombre,materia_id) values (35,0,'Wachenchauzer',12);
Insert into curso(id,version,nombre,catedra_id) values (69,0,'Barrios',35);
Insert into curso(id,version,nombre,catedra_id) values (70,0,'Diegol',35);
Insert into catedra(id,version,nombre,materia_id) values (36,0,'Azcurra',12);
Insert into curso(id,version,nombre,catedra_id) values (71,0,'Vergini',36);

Insert into materia(id,version,nombre,departamento_id) values (13,0,'Algoritmos y programacion II',3);
Insert into catedra(id,version,nombre,materia_id) values (37,0,'Wachenchauzer',13);
Insert into curso(id,version,nombre,catedra_id) values (72,0,'Barrios',37);
Insert into curso(id,version,nombre,catedra_id) values (73,0,'Simon',37);
Insert into catedra(id,version,nombre,materia_id) values (38,0,'Guarna',13);
Insert into curso(id,version,nombre,catedra_id) values (74,0,'Guarna',38);

Insert into materia_materia(materia_correlativas_id, materia_id) values (13,12);


Insert into materia(id,version,nombre,departamento_id) values (14,0,'Algoritmos y programacion III',3);
Insert into catedra(id,version,nombre,materia_id) values (39,0,'Fontela',14);
Insert into curso(id,version,nombre,catedra_id) values (75,0,'Paez',39);
Insert into curso(id,version,nombre,catedra_id) values (76,0,'Suarez',39);

Insert into materia_materia(materia_correlativas_id, materia_id) values (14,13);


Insert into materia(id,version,nombre,departamento_id) values (15,0,'Organizacion de Datos',3);
Insert into catedra(id,version,nombre,materia_id) values (40,0,'Argerich',15);
Insert into curso(id,version,nombre,catedra_id) values (77,0,'Argerich',40);
Insert into catedra(id,version,nombre,materia_id) values (41,0,'Servetto',15);
Insert into curso(id,version,nombre,catedra_id) values (78,0,'Servetto',41);

Insert into materia_materia(materia_correlativas_id, materia_id) values (15,13);


Insert into materia(id,version,nombre,departamento_id) values (16,0,'Bases de datos',3);
Insert into catedra(id,version,nombre,materia_id) values (42,0,'Ale',16);
Insert into curso(id,version,nombre,catedra_id) values (79,0,'Fasce - Roman',42);

Insert into materia_materia(materia_correlativas_id, materia_id) values (16,15);


Insert into materia(id,version,nombre,departamento_id) values (47,0,'Computacion',3);
Insert into catedra(id,version,nombre,materia_id) values (48,0,'Cabrera',47);
Insert into curso(id,version,nombre,catedra_id) values (80,0,'Cabrera',48);

Insert into departamento(id,version,nombre) values (4,0,'Electronica');
Insert into materia(id,version,nombre,departamento_id) values (18,0,'Estructura del computador',4);
Insert into catedra(id,version,nombre,materia_id) values (45,0,'Mazzeo',18);
Insert into curso(id,version,nombre,catedra_id) values (84,0,'Burin',45);
Insert into curso(id,version,nombre,catedra_id) values (85,0,'Zambrano',45);
Insert into curso(id,version,nombre,catedra_id) values (86,0,'Cabibo',45);

Insert into materia_materia(materia_correlativas_id, materia_id) values (18,13);


Insert into materia(id,version,nombre,departamento_id) values (19,0,'Laboratorio',4);
Insert into catedra(id,version,nombre,materia_id) values (46,0,'Rosa',19);
Insert into curso(id,version,nombre,catedra_id) values (81,0,'Marino',46);
Insert into curso(id,version,nombre,catedra_id) values (82,0,'Fiorentin',46);
Insert into curso(id,version,nombre,catedra_id) values (83,0,'Rosa',46);

Insert into materia_materia(materia_correlativas_id, materia_id) values (19,6);





Insert into carrera(id,version,nombre) values(1000,0,'Informatica');
Insert into carrera_materias(carrera_id,materia_id) values(1000,5);
Insert into carrera_materias(carrera_id,materia_id) values(1000,6);
Insert into carrera_materias(carrera_id,materia_id) values(1000,7);
Insert into carrera_materias(carrera_id,materia_id) values(1000,8);
Insert into carrera_materias(carrera_id,materia_id) values(1000,9);
Insert into carrera_materias(carrera_id,materia_id) values(1000,10);
Insert into carrera_materias(carrera_id,materia_id) values(1000,11);
Insert into carrera_materias(carrera_id,materia_id) values(1000,12);
Insert into carrera_materias(carrera_id,materia_id) values(1000,13);
Insert into carrera_materias(carrera_id,materia_id) values(1000,14);
Insert into carrera_materias(carrera_id,materia_id) values(1000,15);
Insert into carrera_materias(carrera_id,materia_id) values(1000,15);
Insert into carrera_materias(carrera_id,materia_id) values(1000,16);
Insert into carrera_materias(carrera_id,materia_id) values(1000,18);
Insert into carrera_materias(carrera_id,materia_id) values(1000,19);

Insert into carrera(id,version,nombre) values(1001,0,'Quimica');
Insert into carrera_materias(carrera_id,materia_id) values(1001,5);
Insert into carrera_materias(carrera_id,materia_id) values(1001,6);
Insert into carrera_materias(carrera_id,materia_id) values(1001,8);
Insert into carrera_materias(carrera_id,materia_id) values(1001,9);
Insert into carrera_materias(carrera_id,materia_id) values(1001,11);



Insert into carrera(id,version,nombre) values(1002,0,'Industrial');
Insert into carrera_materias(carrera_id,materia_id) values(1002,5);
Insert into carrera_materias(carrera_id,materia_id) values(1002,6);
Insert into carrera_materias(carrera_id,materia_id) values(1002,8);
Insert into carrera_materias(carrera_id,materia_id) values(1002,9);
Insert into carrera_materias(carrera_id,materia_id) values(1002,11);
Insert into carrera_materias(carrera_id,materia_id) values(1002,47);












