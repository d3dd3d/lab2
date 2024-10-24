CREATE TABLE IF NOT EXISTS public.history_calculator
(
    "historyID" uuid NOT NULL,
    "firstOperand" character varying COLLATE pg_catalog."default",
    "firstDigit" character varying COLLATE pg_catalog."default",
    "secondOperand" character varying COLLATE pg_catalog."default",
    "secondDigit" character varying COLLATE pg_catalog."default",
    "dateHistory" timestamp without time zone,
    "operationName" character varying COLLATE pg_catalog."default",
    CONSTRAINT history_calculator_pkey PRIMARY KEY ("historyID")
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.history_calculator
    OWNER to postgres;