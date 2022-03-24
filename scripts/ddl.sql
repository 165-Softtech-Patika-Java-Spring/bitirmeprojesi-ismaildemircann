-- Table: public.usr_user

-- DROP TABLE IF EXISTS public.usr_user;

CREATE TABLE IF NOT EXISTS public.usr_user
(
    id bigint NOT NULL,
    create_date timestamp without time zone,
    created_by bigint,
    update_date timestamp without time zone,
    updated_by bigint,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(100) COLLATE pg_catalog."default" NOT NULL,
    username character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT usr_user_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.usr_user
    OWNER to postgres;
	
-- Table: public.prd_product

-- DROP TABLE IF EXISTS public.prd_product;

CREATE TABLE IF NOT EXISTS public.prd_product
(
    id bigint NOT NULL,
    create_date timestamp without time zone,
    created_by bigint,
    update_date timestamp without time zone,
    updated_by bigint,
    last_price numeric(19,2),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    id_pct_product_category bigint NOT NULL,
    vat_price numeric(19,2),
    tax_free_price numeric(19,2) NOT NULL,
    CONSTRAINT prd_product_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.prd_product
    OWNER to postgres;
	
-- Table: public.pct_product_category

-- DROP TABLE IF EXISTS public.pct_product_category;

CREATE TABLE IF NOT EXISTS public.pct_product_category
(
    id bigint NOT NULL,
    create_date timestamp without time zone,
    created_by bigint,
    update_date timestamp without time zone,
    updated_by bigint,
    product_type character varying(30) COLLATE pg_catalog."default" NOT NULL,
    vat_rate integer NOT NULL,
    CONSTRAINT pct_product_category_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.pct_product_category
    OWNER to postgres;
	
-- Table: public.log_usr_user

-- DROP TABLE IF EXISTS public.log_usr_user;

CREATE TABLE IF NOT EXISTS public.log_usr_user
(
    id bigint NOT NULL,
    create_date timestamp without time zone,
    created_by bigint,
    update_date timestamp without time zone,
    updated_by bigint,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(100) COLLATE pg_catalog."default" NOT NULL,
    username character varying(100) COLLATE pg_catalog."default" NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.log_usr_user
    OWNER to postgres;
	
-- Table: public.log_prd_product

-- DROP TABLE IF EXISTS public.log_prd_product;

CREATE TABLE IF NOT EXISTS public.log_prd_product
(
    id bigint NOT NULL,
    create_date timestamp without time zone,
    created_by bigint,
    update_date timestamp without time zone,
    updated_by bigint,
    last_price numeric(19,2),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    id_pct_product_category bigint NOT NULL,
    vat_price numeric(19,2),
    tax_free_price numeric(19,2) NOT NULL
)

TABLESPACE pg_default;
	
ALTER TABLE IF EXISTS public.log_prd_product
    OWNER to postgres;
	
-- Table: public.log_pct_product_category

-- DROP TABLE IF EXISTS public.log_pct_product_category;

CREATE TABLE IF NOT EXISTS public.log_pct_product_category
(
    id bigint NOT NULL,
    create_date timestamp without time zone,
    created_by bigint,
    update_date timestamp without time zone,
    updated_by bigint,
    product_type character varying(30) COLLATE pg_catalog."default" NOT NULL,
    vat_rate integer NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.log_pct_product_category
    OWNER to postgres;
	
CREATE OR REPLACE FUNCTION after_update_or_delete_usr_user_set_log_usr_user() RETURNS TRIGGER AS $log_usr_user$
    	BEGIN
            INSERT INTO log_usr_user (id, create_date, created_by, update_date, updated_by, name, password, surname, username)
    VALUES  (OLD.id, OLD.create_date, OLD.created_by, OLD.update_date, OLD.updated_by, OLD.name, OLD.password, OLD.surname, OLD.username);
		RETURN NULL;
    END;
$log_usr_user$ LANGUAGE plpgsql;

CREATE TRIGGER set_log_usr_user
AFTER INSERT OR UPDATE OR DELETE ON usr_user
    FOR EACH ROW EXECUTE FUNCTION after_update_or_delete_usr_user_set_log_usr_user();
	
CREATE OR REPLACE FUNCTION after_update_or_delete_prd_product_set_log_prd_product() RETURNS TRIGGER AS $log_prd_product$
    	BEGIN
            INSERT INTO log_prd_product (id, create_date, created_by, update_date, updated_by, last_price, name, id_pct_product_category, vat_price, tax_free_price)
    VALUES  (OLD.id, OLD.create_date, OLD.created_by, OLD.update_date, OLD.updated_by, OLD.last_price, OLD.name, OLD.id_pct_product_category, OLD.vat_price, old.tax_free_price);
		RETURN NULL;
    END;
$log_prd_product$ LANGUAGE plpgsql;

CREATE TRIGGER set_log_prd_product
AFTER INSERT OR UPDATE OR DELETE ON prd_product
    FOR EACH ROW EXECUTE FUNCTION after_update_or_delete_prd_product_set_log_prd_product();
	
CREATE OR REPLACE FUNCTION after_update_or_delete_pct_category_set_log_pct_category() RETURNS TRIGGER AS $log_pct_product_category$
    	BEGIN
            INSERT INTO log_pct_product_category (id, create_date, created_by, update_date, updated_by, product_type, vat_rate)
    VALUES  (OLD.id, OLD.create_date, OLD.created_by, OLD.update_date, OLD.updated_by, OLD.product_type, OLD.vat_rate);
		RETURN NULL;
    END;
$log_pct_product_category$ LANGUAGE plpgsql;

CREATE TRIGGER set_log_pct_product_category
AFTER INSERT OR UPDATE OR DELETE ON pct_product_category
    FOR EACH ROW EXECUTE FUNCTION after_update_or_delete_pct_category_set_log_pct_category();
	
-- SEQUENCE: public.usr_user_id_seq

-- DROP SEQUENCE IF EXISTS public.usr_user_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.usr_user_id_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.usr_user_id_seq
    OWNER TO postgres;
	
-- SEQUENCE: public.pct_product_category_id_seq

-- DROP SEQUENCE IF EXISTS public.pct_product_category_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.pct_product_category_id_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.pct_product_category_id_seq
    OWNER TO postgres;
	
-- SEQUENCE: public.prd_product_id_seq

-- DROP SEQUENCE IF EXISTS public.prd_product_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.prd_product_id_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.prd_product_id_seq
    OWNER TO postgres;