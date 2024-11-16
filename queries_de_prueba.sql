#Listar partidos con sus detalles incluidos 

SELECT p.idPartido, p.numeroJornada, p.fecha, sl.nombre as "local", sv.nombre as "visitante", e.nombre as "estadio", a.nombre as "arbitro" FROM lab9.partido p 
JOIN lab9.seleccion sl on sl.idSeleccion = p.seleccionLocal
JOIN lab9.estadio e on sl.estadio_idEstadio = e.idEstadio
JOIN lab9.seleccion sv on sv.idSeleccion = p.seleccionVisitante
JOIN lab9.arbitro a on a.idArbitro = p.arbitro; 

delete from lab9.partido where idPartido=5; 

#Crear partidos 

INSERT INTO lab9.partido (numeroJornada, fecha, seleccionLocal, seleccionVisitante, arbitro) 
VALUES (5, '2020-11-14', 2, 3, 3); 

#Listar selecciones 

SELECT s.idSeleccion, s.nombre FROM lab9.seleccion s;

#Listar arbitros 

SELECT a.idArbitro, a.nombre, a.pais FROM lab9.arbitro a; 

SELECT * FROM lab9.arbitro; 

#Crear arbitro

INSERT INTO lab9.arbitro (nombre, pais) VALUES ("Tony Flores", "Peru");  

#Borrar arbitro

DELETE FROM lab9.arbitro WHERE idArbitro = 5; 