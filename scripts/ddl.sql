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