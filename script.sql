
CREATE SEQUENCE public.oferta_id_seq;

CREATE TABLE public.oferta (
                id INTEGER NOT NULL DEFAULT nextval('public.oferta_id_seq'),
                codigo_oferta CHAR(4) NOT NULL,
                descripcion VARCHAR(50) NOT NULL,
                estado BOOLEAN DEFAULT true NOT NULL,
                usuario_creacion VARCHAR(50),
                fecha_creacion TIMESTAMP DEFAULT now(),
                pc_creacion VARCHAR(50),
                ip_creacion VARCHAR(50),
                usuario_modificacion VARCHAR(50),
                fecha_modificacion TIMESTAMP DEFAULT now(),
                pc_modificacion VARCHAR(50),
                ip_modificacion VARCHAR(50),
                CONSTRAINT oferta_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.oferta_id_seq OWNED BY public.oferta.id;

CREATE SEQUENCE public.plan_id_seq;

CREATE TABLE public.plan (
                id INTEGER NOT NULL DEFAULT nextval('public.plan_id_seq'),
                descripcion VARCHAR(50) NOT NULL,
                estado BOOLEAN DEFAULT true NOT NULL,
                usuario_creacion VARCHAR(50),
                fecha_creacion TIMESTAMP DEFAULT now(),
                pc_creacion VARCHAR(50),
                ip_creacion VARCHAR(50),
                usuario_modificacion VARCHAR(50),
                fecha_modificacion TIMESTAMP DEFAULT now(),
                pc_modificacion VARCHAR(50),
                ip_modificacion VARCHAR(50),
                CONSTRAINT plan_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.plan_id_seq OWNED BY public.plan.id;

CREATE SEQUENCE public.tipo_linea_id_seq;

CREATE TABLE public.tipo_linea (
                id INTEGER NOT NULL DEFAULT nextval('public.tipo_linea_id_seq'),
                descripcion VARCHAR(50) NOT NULL,
                estado BOOLEAN DEFAULT true NOT NULL,
                usuario_creacion VARCHAR(50),
                fecha_creacion TIMESTAMP DEFAULT now(),
                pc_creacion VARCHAR(50),
                ip_creacion VARCHAR(50),
                usuario_modificacion VARCHAR(50),
                fecha_modificacion TIMESTAMP DEFAULT now(),
                pc_modificacion VARCHAR(50),
                ip_modificacion VARCHAR(50),
                CONSTRAINT tipo_linea_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.tipo_linea_id_seq OWNED BY public.tipo_linea.id;

CREATE SEQUENCE public.estado_linea_id_seq;

CREATE TABLE public.estado_linea (
                id INTEGER NOT NULL DEFAULT nextval('public.estado_linea_id_seq'),
                descripcion VARCHAR(50) NOT NULL,
                estado BOOLEAN DEFAULT true NOT NULL,
                usuario_creacion VARCHAR(50),
                fecha_creacion TIMESTAMP DEFAULT now(),
                pc_creacion VARCHAR(50),
                ip_creacion VARCHAR(50),
                usuario_modificacion VARCHAR(50),
                fecha_modificacion TIMESTAMP DEFAULT now(),
                pc_modificacion VARCHAR(50),
                ip_modificacion VARCHAR(50),
                CONSTRAINT estado_linea_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.estado_linea_id_seq OWNED BY public.estado_linea.id;

CREATE SEQUENCE public.tipo_documento_id_seq;

CREATE TABLE public.tipo_documento (
                id INTEGER NOT NULL DEFAULT nextval('public.tipo_documento_id_seq'),
                descripcion VARCHAR(50) NOT NULL,
                abreviatura VARCHAR(6) NOT NULL,
                estado BOOLEAN DEFAULT true NOT NULL,
                usuario_creacion VARCHAR(50),
                fecha_creacion TIMESTAMP DEFAULT now(),
                pc_creacion VARCHAR(50),
                ip_creacion VARCHAR(50),
                usuario_modificacion VARCHAR(50),
                fecha_modificacion TIMESTAMP DEFAULT now(),
                pc_modificacion VARCHAR(50),
                ip_modificacion VARCHAR(50),
                CONSTRAINT tipo_documento_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.tipo_documento_id_seq OWNED BY public.tipo_documento.id;

CREATE SEQUENCE public.cliente_id_seq;

CREATE TABLE public.cliente (
                id VARCHAR NOT NULL DEFAULT nextval('public.cliente_id_seq'),
                nombres VARCHAR(100) NOT NULL,
                apellido_paterno VARCHAR(100) NOT NULL,
                apellido_materno VARCHAR(100) NOT NULL,
                id_tipo_documento INTEGER NOT NULL,
                identificacion VARCHAR(12) NOT NULL,
                fecha_nacimiento DATE NOT NULL,
                estado BOOLEAN DEFAULT true NOT NULL,
                usuario_creacion VARCHAR(50),
                fecha_creacion TIMESTAMP DEFAULT now(),
                pc_creacion VARCHAR(50),
                ip_creacion VARCHAR(50),
                usuario_modificacion VARCHAR(50),
                fecha_modificacion TIMESTAMP DEFAULT now(),
                pc_modificacion VARCHAR(50),
                ip_modificacion VARCHAR(50),
                CONSTRAINT cliente_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN public.cliente.identificacion IS 'Número de documento de identidad';


ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;

CREATE SEQUENCE public.linea_movil_id_seq;

CREATE TABLE public.linea_movil (
                id INTEGER NOT NULL DEFAULT nextval('public.linea_movil_id_seq'),
                id_cliente VARCHAR NOT NULL,
                id_plan INTEGER NOT NULL,
                id_estado_linea INTEGER NOT NULL,
                id_tipo_linea INTEGER NOT NULL,
                numero_telefonico CHAR(9) NOT NULL,
                estado BOOLEAN DEFAULT true NOT NULL,
                usuario_creacion VARCHAR(50),
                fecha_creacion TIMESTAMP DEFAULT now(),
                pc_creacion VARCHAR(50),
                ip_creacion VARCHAR(50),
                usuario_modificacion VARCHAR(50),
                fecha_modificacion TIMESTAMP DEFAULT now(),
                pc_modificacion VARCHAR(50),
                ip_modificacion VARCHAR(50),
                CONSTRAINT linea_movil_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.linea_movil_id_seq OWNED BY public.linea_movil.id;

CREATE SEQUENCE public.oferta_linea_id_seq;

CREATE TABLE public.oferta_linea (
                id INTEGER NOT NULL DEFAULT nextval('public.oferta_linea_id_seq'),
                id_oferta INTEGER NOT NULL,
                id_linea_movil INTEGER NOT NULL,
                fecha_inicio TIMESTAMP NOT NULL,
                fecha_fin TIMESTAMP NOT NULL,
                estado BOOLEAN DEFAULT true NOT NULL,
                usuario_creacion VARCHAR(50),
                fecha_creacion TIMESTAMP DEFAULT now(),
                pc_creacion VARCHAR(50),
                ip_creacion VARCHAR(50),
                usuario_modificacion VARCHAR(50),
                fecha_modificacion TIMESTAMP DEFAULT now(),
                pc_modificacion VARCHAR(50),
                ip_modificacion VARCHAR(50),
                CONSTRAINT oferta_linea_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.oferta_linea_id_seq OWNED BY public.oferta_linea.id;

ALTER TABLE public.oferta_linea ADD CONSTRAINT oferta_linea_oferta_fk
FOREIGN KEY (id_oferta)
REFERENCES public.oferta (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.linea_movil ADD CONSTRAINT plan_linea_movil_fk
FOREIGN KEY (id_plan)
REFERENCES public.plan (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.linea_movil ADD CONSTRAINT tipo_linea_linea_movil_fk
FOREIGN KEY (id_tipo_linea)
REFERENCES public.tipo_linea (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.linea_movil ADD CONSTRAINT estado_linea_movil_fk
FOREIGN KEY (id_estado_linea)
REFERENCES public.estado_linea (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.cliente ADD CONSTRAINT tipo_documento_cliente_fk
FOREIGN KEY (id_tipo_documento)
REFERENCES public.tipo_documento (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.linea_movil ADD CONSTRAINT cliente_linea_movil_fk
FOREIGN KEY (id_cliente)
REFERENCES public.cliente (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.oferta_linea ADD CONSTRAINT linea_movil_oferta_fk
FOREIGN KEY (id_linea_movil)
REFERENCES public.linea_movil (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;