--
-- PostgreSQL database dump
--

-- Dumped from database version 12.8 (Ubuntu 12.8-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 13.4

-- Started on 2021-09-30 15:42:07

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
-- TOC entry 207 (class 1259 OID 16440)
-- Name: colegio; Type: TABLE; Schema: public; Owner: guest
--

CREATE TABLE public.colegio (
    idcolegio integer NOT NULL,
    tipocolegio character varying(50) NOT NULL,
    ciudad character varying(150) NOT NULL,
    nombrecolegio character varying(100) NOT NULL,
    estado boolean NOT NULL
);


ALTER TABLE public.colegio OWNER TO guest;

--
-- TOC entry 206 (class 1259 OID 16438)
-- Name: Colegio_idcolegio_seq; Type: SEQUENCE; Schema: public; Owner: guest
--

CREATE SEQUENCE public."Colegio_idcolegio_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Colegio_idcolegio_seq" OWNER TO guest;

--
-- TOC entry 2983 (class 0 OID 0)
-- Dependencies: 206
-- Name: Colegio_idcolegio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: guest
--

ALTER SEQUENCE public."Colegio_idcolegio_seq" OWNED BY public.colegio.idcolegio;


--
-- TOC entry 205 (class 1259 OID 16409)
-- Name: Estudiante; Type: TABLE; Schema: public; Owner: guest
--

CREATE TABLE public."Estudiante" (
    id integer NOT NULL,
    primer_ape text NOT NULL,
    segundo_ape text NOT NULL,
    primer_nom text NOT NULL,
    segundo_nom text,
    activo boolean,
    origen text,
    id_colegio integer
);


ALTER TABLE public."Estudiante" OWNER TO guest;

--
-- TOC entry 209 (class 1259 OID 16448)
-- Name: Historia_Academica; Type: TABLE; Schema: public; Owner: guest
--

CREATE TABLE public."Historia_Academica" (
    "idHistoria_academica" integer NOT NULL,
    "idEstudiante" integer NOT NULL,
    colegio_anterior text NOT NULL,
    ano text NOT NULL,
    grado text NOT NULL,
    activo boolean
);


ALTER TABLE public."Historia_Academica" OWNER TO guest;

--
-- TOC entry 208 (class 1259 OID 16446)
-- Name: Historia_Academica_idHistoria_academica_seq; Type: SEQUENCE; Schema: public; Owner: guest
--

CREATE SEQUENCE public."Historia_Academica_idHistoria_academica_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Historia_Academica_idHistoria_academica_seq" OWNER TO guest;

--
-- TOC entry 2985 (class 0 OID 0)
-- Dependencies: 208
-- Name: Historia_Academica_idHistoria_academica_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: guest
--

ALTER SEQUENCE public."Historia_Academica_idHistoria_academica_seq" OWNED BY public."Historia_Academica"."idHistoria_academica";


--
-- TOC entry 211 (class 1259 OID 16459)
-- Name: Info_Salud; Type: TABLE; Schema: public; Owner: guest
--

CREATE TABLE public."Info_Salud" (
    numero_seguro_social integer NOT NULL,
    nombre_entidad text NOT NULL,
    grupo_sanguineo text NOT NULL,
    id_estudiante integer NOT NULL
);


ALTER TABLE public."Info_Salud" OWNER TO guest;

--
-- TOC entry 213 (class 1259 OID 16480)
-- Name: Tutor; Type: TABLE; Schema: public; Owner: guest
--

CREATE TABLE public."Tutor" (
    id integer NOT NULL,
    primernom text NOT NULL,
    segundonom text NOT NULL,
    apellidopat text NOT NULL,
    apellidomat text NOT NULL,
    correo_electronico text NOT NULL,
    id_estudiante integer NOT NULL,
    tel_casa text,
    tel_movil text,
    activo boolean NOT NULL
);


ALTER TABLE public."Tutor" OWNER TO guest;

--
-- TOC entry 212 (class 1259 OID 16478)
-- Name: Tutor_id_seq; Type: SEQUENCE; Schema: public; Owner: guest
--

CREATE SEQUENCE public."Tutor_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Tutor_id_seq" OWNER TO guest;

--
-- TOC entry 2986 (class 0 OID 0)
-- Dependencies: 212
-- Name: Tutor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: guest
--

ALTER SEQUENCE public."Tutor_id_seq" OWNED BY public."Tutor".id;


--
-- TOC entry 204 (class 1259 OID 16407)
-- Name: estudiante_idEstudiante_seq; Type: SEQUENCE; Schema: public; Owner: guest
--

CREATE SEQUENCE public."estudiante_idEstudiante_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."estudiante_idEstudiante_seq" OWNER TO guest;

--
-- TOC entry 2987 (class 0 OID 0)
-- Dependencies: 204
-- Name: estudiante_idEstudiante_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: guest
--

ALTER SEQUENCE public."estudiante_idEstudiante_seq" OWNED BY public."Estudiante".id;


--
-- TOC entry 210 (class 1259 OID 16457)
-- Name: info_salud_numero_seguro_social_seq; Type: SEQUENCE; Schema: public; Owner: guest
--

CREATE SEQUENCE public.info_salud_numero_seguro_social_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.info_salud_numero_seguro_social_seq OWNER TO guest;

--
-- TOC entry 2988 (class 0 OID 0)
-- Dependencies: 210
-- Name: info_salud_numero_seguro_social_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: guest
--

ALTER SEQUENCE public.info_salud_numero_seguro_social_seq OWNED BY public."Info_Salud".numero_seguro_social;


--
-- TOC entry 2826 (class 2604 OID 16412)
-- Name: Estudiante id; Type: DEFAULT; Schema: public; Owner: guest
--

ALTER TABLE ONLY public."Estudiante" ALTER COLUMN id SET DEFAULT nextval('public."estudiante_idEstudiante_seq"'::regclass);


--
-- TOC entry 2828 (class 2604 OID 16451)
-- Name: Historia_Academica idHistoria_academica; Type: DEFAULT; Schema: public; Owner: guest
--

ALTER TABLE ONLY public."Historia_Academica" ALTER COLUMN "idHistoria_academica" SET DEFAULT nextval('public."Historia_Academica_idHistoria_academica_seq"'::regclass);


--
-- TOC entry 2829 (class 2604 OID 16462)
-- Name: Info_Salud numero_seguro_social; Type: DEFAULT; Schema: public; Owner: guest
--

ALTER TABLE ONLY public."Info_Salud" ALTER COLUMN numero_seguro_social SET DEFAULT nextval('public.info_salud_numero_seguro_social_seq'::regclass);


--
-- TOC entry 2830 (class 2604 OID 16483)
-- Name: Tutor id; Type: DEFAULT; Schema: public; Owner: guest
--

ALTER TABLE ONLY public."Tutor" ALTER COLUMN id SET DEFAULT nextval('public."Tutor_id_seq"'::regclass);


--
-- TOC entry 2827 (class 2604 OID 16443)
-- Name: colegio idcolegio; Type: DEFAULT; Schema: public; Owner: guest
--

ALTER TABLE ONLY public.colegio ALTER COLUMN idcolegio SET DEFAULT nextval('public."Colegio_idcolegio_seq"'::regclass);


--
-- TOC entry 2968 (class 0 OID 16409)
-- Dependencies: 205
-- Data for Name: Estudiante; Type: TABLE DATA; Schema: public; Owner: guest
--

COPY public."Estudiante" (id, primer_ape, segundo_ape, primer_nom, segundo_nom, activo, origen, id_colegio) FROM stdin;
34	Fuggle	Halsall	Junina	Alicea	f	Yangjiafang	5
35	Connick	Beuscher	Prue	Faydra	f	Perth	2
36	Wharton	Hebdon	Celestyn	Martynne	t	Madette	6
37	Sussans	Furley	Odille	Ruggiero	f	Hägersten	9
38	Ottewell	Conner	Vikky	Jacob	f	Wanganui	5
39	Cork	Maytum	Collete	Adelaida	t	Baomin	2
40	Rollason	Rickaert	Pansie	Sarette	f	Mahdia	3
41	Montgomery	Mauchline	Llywellyn	Marjy	f	Rimus	7
42	Jolliff	Egerton	Bobina	Robers	t	Hengshui	6
43	O'Caherny	Ridpath	Brenda	Griswold	t	Jelsa	4
44	Acory	Southerns	Danika	Thornie	f	Przybyszówka	2
45	Yoxen	Phaup	Remington	Barbi	t	Kranjska Gora	7
46	Davidwitz	Luffman	Natividad	Roz	t	Dingli	7
47	Renon	Karpeev	Denis	Jed	t	Yufrus	10
48	Stampe	Shyram	Page	Carrissa	f	Sātkhira	9
49	Roderigo	Oxborough	Rosamond	Mickie	f	Lidong	10
50	Bluschke	Gussie	Andriette	Kayle	t	Yueyang	4
51	Peers	Eshelby	Chloe	Concettina	t	Pulupandan	8
52	Strettell	Rummings	Hermy	Deva	f	Cangshi	5
53	Fillary	Flintuff	Clem	Joby	t	Shangtuhai	8
54	Hardson	Cochet	Stanfield	Katerina	f	Örnsköldsvik	8
55	Becarra	Shefton	Drusi	Portia	f	Yeni Suraxanı	2
56	Speerman	Faircloth	Lurlene	Man	t	Gemblengmulyo	7
57	Ringe	Shepard	Bailie	Constancia	f	Tulay	6
58	Letcher	De Benedictis	Glynnis	Malchy	t	Jijia	7
59	Lockhart	Wickey	Royall	Trumaine	t	Três Lagoas	2
60	Britch	Balassa	Kacie	Vanda	f	Cabreúva	6
61	Cleevely	Boick	Jeannine	Germaine	f	Edinburgh	3
62	Tuffell	Kupker	Yankee	Drona	f	Situsari	6
63	Corradetti	Stittle	Karol	Adoree	t	Mae Charim	7
64	Sanchez	Angel	Morales	Jesus	f		\N
65	Díaz	Díaz	Seth	Noé	f	Xalapa	\N
\.


--
-- TOC entry 2972 (class 0 OID 16448)
-- Dependencies: 209
-- Data for Name: Historia_Academica; Type: TABLE DATA; Schema: public; Owner: guest
--

COPY public."Historia_Academica" ("idHistoria_academica", "idEstudiante", colegio_anterior, ano, grado, activo) FROM stdin;
5	1	United States Naval Academy	1993	Primero	f
6	36	Tecnologico de Monterrey	2019	Séptimo	f
7	53	Colegio Privado Xalapa	2019	5	f
8	47	fasdf	haddfg	gasdfg	f
9	45	Juarez	2020	6to	f
\.


--
-- TOC entry 2974 (class 0 OID 16459)
-- Dependencies: 211
-- Data for Name: Info_Salud; Type: TABLE DATA; Schema: public; Owner: guest
--

COPY public."Info_Salud" (numero_seguro_social, nombre_entidad, grupo_sanguineo, id_estudiante) FROM stdin;
12345678	IMSS	O negativo	36
7654321	IMSS	B positivo	39
98765432	IMSS	AB positivo	43
\.


--
-- TOC entry 2976 (class 0 OID 16480)
-- Dependencies: 213
-- Data for Name: Tutor; Type: TABLE DATA; Schema: public; Owner: guest
--

COPY public."Tutor" (id, primernom, segundonom, apellidopat, apellidomat, correo_electronico, id_estudiante, tel_casa, tel_movil, activo) FROM stdin;
1	Anders	Alexandre	Kubatsch	Leppington	aleppington0@cbsnews.com	34	540-782-2944	116-108-1681	f
2	Katherina	Frederigo	Casillis	Ropking	fropking1@utexas.edu	35	807-357-6213	717-877-7355	t
3	Harley	Brittan	Berkery	Ugo	bugo2@umn.edu	36	354-484-6827	859-548-6536	t
4	Yoshi	Dean	Lukas	Furmenger	dfurmenger3@reddit.com	37	511-168-0518	765-651-7633	t
5	Shaun	Clemens	Sommerling	Tupman	ctupman4@yahoo.co.jp	38	332-665-5471	576-390-5247	f
6	Holt	Rafi	Antalffy	Naseby	rnaseby5@artisteer.com	39	112-637-3925	449-919-4128	t
7	Brandie	Euphemia	Lammerts	Thormann	ethormann6@chron.com	40	416-516-6818	885-288-1446	t
8	Kim	Adams	Schaffel	Whittock	awhittock7@who.int	41	310-760-0325	814-601-8953	t
9	Kerk	Nicko	Kurten	Habbes	nhabbes8@addtoany.com	42	712-661-2847	807-827-4275	t
10	Georgiana	Lorri	Mardee	Rubery	lrubery9@php.net	43	289-655-2810	996-866-5919	f
11	Blisse	Stafani	McDougle	Blueman	sbluemana@netlog.com	44	143-422-1720	754-233-6693	f
12	Ayn	Horace	Dewes	Tinan	htinanb@huffingtonpost.com	45	422-334-0690	601-407-1629	t
13	Sly	Bent	Loch	Markushkin	bmarkushkinc@linkedin.com	46	748-540-4144	337-275-1558	f
14	Randolph	Dorella	Brekonridge	Slogrove	dslogroved@nasa.gov	47	692-531-1701	111-597-8848	t
15	Adena	De witt	Reilly	Marle	dmarlee@vistaprint.com	48	979-309-3705	962-184-2890	f
16	Milka	Sophi	Kytley	Ebourne	sebournef@indiegogo.com	49	744-399-0268	537-271-4261	f
17	Ebeneser	Daisi	Drysdale	Hegg	dheggg@statcounter.com	50	676-262-1374	854-758-8080	f
18	Ivan	Rubie	Orbell	Johnes	rjohnesh@csmonitor.com	51	956-468-3620	408-407-7163	t
19	Lorette	Jackqueline	Ellacombe	Hedge	jhedgei@lulu.com	52	839-745-0046	529-169-3855	f
20	Ag	Rob	Moodie	Chree	rchreej@yahoo.com	53	711-791-4368	902-494-9306	t
21	Jacintha	Tobye	Gladdish	Jude	tjudek@cargocollective.com	54	250-967-8731	692-938-5232	t
22	Kristal	Sherm	Rollings	Allain	sallainl@hostgator.com	55	193-204-3382	688-121-5797	f
23	Kelila	Dorine	Joburn	Cuppleditch	dcuppleditchm@wikia.com	56	967-574-3920	708-511-3402	f
24	Jarrad	Meade	Cancellario	Rheam	mrheamn@acquirethisname.com	57	443-931-1253	373-567-7167	f
25	Karol	Druci	Chaplyn	Ewebank	dewebanko@economist.com	58	503-253-6767	654-733-9680	f
26	Natale	Rory	Medeway	Goulden	rgouldenp@craigslist.org	59	816-197-2268	260-620-6162	t
27	Freddy	Percival	Ascraft	Schlag	pschlagq@irs.gov	60	736-820-2451	550-499-6295	f
28	Rory	Marie	Jonathon	Soaper	msoaperr@elegantthemes.com	61	641-756-9640	748-430-3293	f
29	Robbert	Naomi	Ogger	Ilyinykh	nilyinykhs@amazon.co.jp	62	451-598-7571	916-518-0621	f
30	Aubert	Marjie	Golborn	Duiged	mduigedt@answers.com	63	460-178-8976	986-213-5125	t
33	Angel	Jesus	Mendoza 	Cuervo	qwer@gmail.com	51	3698745521	12398745	f
32	Seth	Daniel	morales	tapia	tapiamorales@gmail.com	47	741258963	369852147	f
31	Juan	Juanito	Juanito	El Golondrina	pussyslayer@gmail.com	36	12365479	789461326	f
34	Martín	Jesus	García	Torres	asdfsda@asfa.com	59	5434342	014216534	f
\.


--
-- TOC entry 2970 (class 0 OID 16440)
-- Dependencies: 207
-- Data for Name: colegio; Type: TABLE DATA; Schema: public; Owner: guest
--

COPY public.colegio (idcolegio, tipocolegio, ciudad, nombrecolegio, estado) FROM stdin;
3	Privado	Orizaba	Universiad Autonoma	t
4	Privado	Xalapa	Universidad de Xalapa	t
5	Publico	Xalapa	Universidad Veracruzana	t
6	Publico	Orizaba	Universidad de Orizaba	t
9	Privado	Veracruz	Americas	t
\.


--
-- TOC entry 2989 (class 0 OID 0)
-- Dependencies: 206
-- Name: Colegio_idcolegio_seq; Type: SEQUENCE SET; Schema: public; Owner: guest
--

SELECT pg_catalog.setval('public."Colegio_idcolegio_seq"', 9, true);


--
-- TOC entry 2990 (class 0 OID 0)
-- Dependencies: 208
-- Name: Historia_Academica_idHistoria_academica_seq; Type: SEQUENCE SET; Schema: public; Owner: guest
--

SELECT pg_catalog.setval('public."Historia_Academica_idHistoria_academica_seq"', 9, true);


--
-- TOC entry 2991 (class 0 OID 0)
-- Dependencies: 212
-- Name: Tutor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: guest
--

SELECT pg_catalog.setval('public."Tutor_id_seq"', 34, true);


--
-- TOC entry 2992 (class 0 OID 0)
-- Dependencies: 204
-- Name: estudiante_idEstudiante_seq; Type: SEQUENCE SET; Schema: public; Owner: guest
--

SELECT pg_catalog.setval('public."estudiante_idEstudiante_seq"', 65, true);


--
-- TOC entry 2993 (class 0 OID 0)
-- Dependencies: 210
-- Name: info_salud_numero_seguro_social_seq; Type: SEQUENCE SET; Schema: public; Owner: guest
--

SELECT pg_catalog.setval('public.info_salud_numero_seguro_social_seq', 1, false);


--
-- TOC entry 2834 (class 2606 OID 16445)
-- Name: colegio Colegio_pkey; Type: CONSTRAINT; Schema: public; Owner: guest
--

ALTER TABLE ONLY public.colegio
    ADD CONSTRAINT "Colegio_pkey" PRIMARY KEY (idcolegio);


--
-- TOC entry 2836 (class 2606 OID 16456)
-- Name: Historia_Academica Historia_Academica_pkey; Type: CONSTRAINT; Schema: public; Owner: guest
--

ALTER TABLE ONLY public."Historia_Academica"
    ADD CONSTRAINT "Historia_Academica_pkey" PRIMARY KEY ("idHistoria_academica");


--
-- TOC entry 2840 (class 2606 OID 16488)
-- Name: Tutor Tutor_pkey; Type: CONSTRAINT; Schema: public; Owner: guest
--

ALTER TABLE ONLY public."Tutor"
    ADD CONSTRAINT "Tutor_pkey" PRIMARY KEY (id);


--
-- TOC entry 2832 (class 2606 OID 16417)
-- Name: Estudiante estudiante_pkey; Type: CONSTRAINT; Schema: public; Owner: guest
--

ALTER TABLE ONLY public."Estudiante"
    ADD CONSTRAINT estudiante_pkey PRIMARY KEY (id);


--
-- TOC entry 2838 (class 2606 OID 16467)
-- Name: Info_Salud info_salud_pkey; Type: CONSTRAINT; Schema: public; Owner: guest
--

ALTER TABLE ONLY public."Info_Salud"
    ADD CONSTRAINT info_salud_pkey PRIMARY KEY (numero_seguro_social);


--
-- TOC entry 2982 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

GRANT USAGE ON SCHEMA public TO guest;


--
-- TOC entry 2984 (class 0 OID 0)
-- Dependencies: 209
-- Name: TABLE "Historia_Academica"; Type: ACL; Schema: public; Owner: guest
--

GRANT SELECT ON TABLE public."Historia_Academica" TO PUBLIC;


-- Completed on 2021-09-30 15:42:13

--
-- PostgreSQL database dump complete
--

