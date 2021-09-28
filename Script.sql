--
-- PostgreSQL database dump
--

-- Dumped from database version 12.8 (Ubuntu 12.8-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 13.4

-- Started on 2021-09-28 14:14:42

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 16409)
-- Name: estudiante; Type: TABLE; Schema: public; Owner: seth
--

CREATE TABLE public.estudiante (
    id integer NOT NULL,
    primer_ape text NOT NULL,
    segundo_ape text NOT NULL,
    primer_nom text NOT NULL,
    segundo_nom text,
    activo boolean
);


ALTER TABLE public.estudiante OWNER TO seth;

--
-- TOC entry 202 (class 1259 OID 16407)
-- Name: estudiante_idEstudiante_seq; Type: SEQUENCE; Schema: public; Owner: seth
--

CREATE SEQUENCE public."estudiante_idEstudiante_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."estudiante_idEstudiante_seq" OWNER TO seth;

--
-- TOC entry 2933 (class 0 OID 0)
-- Dependencies: 202
-- Name: estudiante_idEstudiante_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: seth
--

ALTER SEQUENCE public."estudiante_idEstudiante_seq" OWNED BY public.estudiante.id;


--
-- TOC entry 2797 (class 2604 OID 16412)
-- Name: estudiante id; Type: DEFAULT; Schema: public; Owner: seth
--

ALTER TABLE ONLY public.estudiante ALTER COLUMN id SET DEFAULT nextval('public."estudiante_idEstudiante_seq"'::regclass);


--
-- TOC entry 2927 (class 0 OID 16409)
-- Dependencies: 203
-- Data for Name: estudiante; Type: TABLE DATA; Schema: public; Owner: seth
--

COPY public.estudiante (id, primer_ape, segundo_ape, primer_nom, segundo_nom, activo) FROM stdin;
1	Callear	Kentish	Lowell	Zerk	f
2	Baffin	Becconsall	Therine	Elihu	f
4	Gerauld	O'Deegan	Frederich	Wheeler	t
5	Clohessy	Merlin	Ulrick	Neal	t
6	Handrik	Dominico	Gratia	Kristoforo	f
7	Rogan	Menhci	Isobel	Charmain	f
8	Grinsdale	Kingcote	Lolly	Hedvige	f
9	Santello	Gebbe	Lynn	Richardo	t
10	Abbay	Gorden	Clareta	Aretha	t
11	Condie	Dinse	Keefer	Merrielle	t
12	Vedstra	Graffin	Garth	Niles	t
13	Choldcroft	Moyne	Ariana	Arie	t
14	Wellwood	Edmund	Dotti	Reginauld	t
15	Rosel	Mynard	Stu	Keelia	t
16	Crain	Rubinov	Ondrea	Walther	t
17	Cleaveland	Simner	Nappy	Marcille	t
18	Usmar	Tripney	Heywood	Randal	f
19	Wymer	Hathorn	Candace	Andie	t
20	Shead	Battison	Hermine	Debee	f
21	Apted	Minget	Lani	Meridel	t
22	Tollfree	Cino	Pierre	Halsy	f
23	Michele	McQueen	Con	Shari	f
24	Cottel	Swapp	Hanni	Kelcy	f
25	Warstall	Ainsley	Lazar	Chicky	t
26	Kettlesting	O'Connor	Rois	Vanni	t
27	Iannuzzelli	Isaq	Friedrick	Ilaire	t
28	Nornasell	Nemchinov	Hunfredo	Ward	f
29	Santus	Jennaroy	Gardner	Ashbey	f
30	Harms	Fromont	Zed	Erick	f
3	Clixby	Farnes	Jermayne	Diane-marie	t
31	Diaz	Seth	Diaz	Noe	f
32	Diaz	Noe	Diaz	Seth	f
\.


--
-- TOC entry 2934 (class 0 OID 0)
-- Dependencies: 202
-- Name: estudiante_idEstudiante_seq; Type: SEQUENCE SET; Schema: public; Owner: seth
--

SELECT pg_catalog.setval('public."estudiante_idEstudiante_seq"', 32, true);


--
-- TOC entry 2799 (class 2606 OID 16417)
-- Name: estudiante estudiante_pkey; Type: CONSTRAINT; Schema: public; Owner: seth
--

ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_pkey PRIMARY KEY (id);


-- Completed on 2021-09-28 14:14:46

--
-- PostgreSQL database dump complete
--

