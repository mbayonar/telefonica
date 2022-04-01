-- TABLA PLAN
INSERT INTO public.plan(descripcion, usuario_creacion, fecha_creacion, pc_creacion, ip_creacion, usuario_modificacion, fecha_modificacion, pc_modificacion, ip_modificacion)
VALUES ('PLAN PREPAGO', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
	('PLAN S/. 29.90', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    ('PLAN S/. 39.90', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    ('PLAN S/. 49.90', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    ('PLAN S/. 65.90', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1');

-- TABLA ESTADO
INSERT INTO public.estado_linea(descripcion, usuario_creacion, fecha_creacion, pc_creacion, ip_creacion, usuario_modificacion, fecha_modificacion, pc_modificacion, ip_modificacion)
VALUES ('ACTIVO', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    ('CANCELADO', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1');

-- TABLA TIPO_LINEA
INSERT INTO public.tipo_linea(descripcion, usuario_creacion, fecha_creacion, pc_creacion, ip_creacion, usuario_modificacion, fecha_modificacion, pc_modificacion, ip_modificacion)
VALUES ('POSTPAGO', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    ('PREPAGO', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1');
	
-- TABLA TIPO_DOCUMENTO
INSERT INTO public.tipo_documento(descripcion, abreviatura, usuario_creacion, fecha_creacion, pc_creacion, ip_creacion, usuario_modificacion, fecha_modificacion, pc_modificacion, ip_modificacion)
VALUES ('DOCUMENTO NACIONAL DE IDENTIDAD', 'DNI', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    ('CARNÉ DE EXTRANJERÍA', 'CE', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1');
	
-- TABLA CLIENTE
INSERT INTO public.cliente(nombres, apellido_paterno, apellido_materno, id_tipo_documento, identificacion, fecha_nacimiento, usuario_creacion, fecha_creacion, pc_creacion, ip_creacion, usuario_modificacion, fecha_modificacion, pc_modificacion, ip_modificacion)
VALUES ('MARCOS MARIANO', 'BAYONA', 'RIJALBA', 1, '74125896', '28-07-1998', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    ('JEREMY ROLANDO', 'AGURTO', 'GUZMAN', 1, '7896325', '05-02-1998', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
	('FRANZ JUNIOR', 'MORENO', 'CRUZ', 2, '000035741268', '13-10-1997', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1');

-- TABLA LINEA_MOVIL
INSERT INTO public.linea_movil(id_cliente, id_plan, id_estado_linea, id_tipo_linea, numero_telefonico, usuario_creacion, fecha_creacion, pc_creacion, ip_creacion, usuario_modificacion, fecha_modificacion, pc_modificacion, ip_modificacion)
VALUES (1, 1, 1, 1, '951236478', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    (2, 2, 1, 2, '987412563', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    (3, 3, 1, 1, '928173256', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    (1, 4, 2, 2, '954614651', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    (2, 5, 1, 2, '918241543', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    (3, 1, 1, 1, '971364825', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    (1, 2, 1, 2, '900236586', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    (2, 3, 2, 1, '914545977' 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    (3, 4, 1, 2, '984451532', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1');
	
-- TABLA OFERTA
INSERT INTO public.oferta(codigo_oferta, descripcion, usuario_creacion, fecha_creacion, pc_creacion, ip_creacion, usuario_modificacion, fecha_modificacion, pc_modificacion, ip_modificacion)
VALUES ('0001', '10 GB GRATIS', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    ('0002', 'REDES SOCIALES ILIMITADAS', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
	('0003', 'LLAMADAS INTERNACIONALES GRATIS POR 7 DÍAS', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    ('0004', 'WHATSAPP GRATIS POR 1 MES', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    ('0005', 'SMS ILIMITADOS', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    ('0006', 'YOUTUBE Y SPOTIFY X3 DÍAS', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1');
	
-- TABLA OFERTA_LINEA
INSERT INTO public.oferta_linea(id_oferta, id_linea_movil, fecha_inicio, fecha_fin, usuario_creacion, fecha_creacion, pc_creacion, ip_creacion, usuario_modificacion, fecha_modificacion, pc_modificacion, ip_modificacion)
VALUES (1, 1, '30-03-2022 15:32:00', '30-04-2022 15:32:00', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    (2, 1, '29-03-2022 11:57:00', '15-04-2022 08:45:00', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
	(3, 2, '10-04-2022 03:32:00', '17-04-2022 05:19:00', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    (4, 2, '29-03-2022 20:08:00', '15-04-2022 16:12:00', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    (5, 3, '05-04-2022 19:20:00', '05-05-2022 19:20:00', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1'),
    (5, 4, '01-04-2022 14:44:00', '04-04-2022 14:44:00', 'ADMIN', now(), 'localhost', '127.0.0.1', 'ADMIN', now(), 'localhost', '127.0.0.1');