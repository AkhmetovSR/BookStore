--
-- PostgreSQL database dump
--

-- Dumped from database version 10.22
-- Dumped by pg_dump version 10.22

-- Started on 2023-04-24 22:22:52

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

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2860 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 199 (class 1259 OID 25422)
-- Name: image; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.image (
    id integer NOT NULL,
    file_name character varying(255),
    product_id integer NOT NULL
);


ALTER TABLE public.image OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 25420)
-- Name: image_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.image_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.image_id_seq OWNER TO postgres;

--
-- TOC entry 2861 (class 0 OID 0)
-- Dependencies: 198
-- Name: image_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.image_id_seq OWNED BY public.image.id;


--
-- TOC entry 205 (class 1259 OID 25468)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    count integer NOT NULL,
    date_time timestamp(6) without time zone,
    price real NOT NULL,
    status smallint,
    person_id integer NOT NULL,
    product_id integer NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 25466)
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_seq OWNER TO postgres;

--
-- TOC entry 2862 (class 0 OID 0)
-- Dependencies: 204
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


--
-- TOC entry 197 (class 1259 OID 25397)
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
    id integer NOT NULL,
    age integer NOT NULL,
    email text NOT NULL,
    first_name text NOT NULL,
    last_name text NOT NULL,
    password text NOT NULL,
    patronymic text,
    phone text NOT NULL,
    role character varying(255)
);


ALTER TABLE public.person OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 25395)
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.person_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person_id_seq OWNER TO postgres;

--
-- TOC entry 2863 (class 0 OID 0)
-- Dependencies: 196
-- Name: person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.person_id_seq OWNED BY public.person.id;


--
-- TOC entry 201 (class 1259 OID 25430)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    author text NOT NULL,
    country text NOT NULL,
    descriptions text NOT NULL,
    pages integer NOT NULL,
    price integer NOT NULL,
    publisher text NOT NULL,
    title text NOT NULL,
    year_of_issue text NOT NULL,
    CONSTRAINT product_pages_check CHECK ((pages >= 10)),
    CONSTRAINT product_price_check CHECK ((price >= 1))
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 25450)
-- Name: product_cart; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_cart (
    id integer NOT NULL,
    person_id integer,
    product_id integer
);


ALTER TABLE public.product_cart OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 25448)
-- Name: product_cart_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_cart_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_cart_id_seq OWNER TO postgres;

--
-- TOC entry 2864 (class 0 OID 0)
-- Dependencies: 202
-- Name: product_cart_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_cart_id_seq OWNED BY public.product_cart.id;


--
-- TOC entry 200 (class 1259 OID 25428)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO postgres;

--
-- TOC entry 2865 (class 0 OID 0)
-- Dependencies: 200
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- TOC entry 2697 (class 2604 OID 25425)
-- Name: image id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image ALTER COLUMN id SET DEFAULT nextval('public.image_id_seq'::regclass);


--
-- TOC entry 2702 (class 2604 OID 25471)
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- TOC entry 2696 (class 2604 OID 25400)
-- Name: person id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person ALTER COLUMN id SET DEFAULT nextval('public.person_id_seq'::regclass);


--
-- TOC entry 2698 (class 2604 OID 25433)
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- TOC entry 2701 (class 2604 OID 25453)
-- Name: product_cart id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_cart ALTER COLUMN id SET DEFAULT nextval('public.product_cart_id_seq'::regclass);


--
-- TOC entry 2846 (class 0 OID 25422)
-- Dependencies: 199
-- Data for Name: image; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.image (id, file_name, product_id) FROM stdin;
44	83ff42ea-48c1-4546-b85f-4878def185ce.19841.webp	18
45	109f3a17-3888-4a07-84ab-106d6c1ed630.19842.webp	18
46	7ee636ee-fe09-49c5-aa91-3daa75485bb9.19843.webp	18
47	cf9f8ca0-7d57-459c-804c-af4b0083e413.pan1.webp	19
48	72824161-20fb-48fc-b4e1-49539fdbfc05.pan2.webp	19
49	6fa41f33-10b2-4c1f-a4c9-5bd2254a3a78.pan3.webp	19
50	d7327108-9dc0-4a88-9f94-012e36d4c8bd.java1.webp	20
51	9e446284-9f6a-408d-9a90-2f325490880b.java2.webp	20
52	acfe1f54-1cdd-4701-8b93-efcc602157ae.java3.webp	20
53	f8dc5646-521b-4a8a-beaf-7c6868f096c5.gmc1.webp	21
54	02c6bde5-b821-43be-beab-bbfb04e576e9.gmc2.webp	21
55	4ad552a8-67f6-402f-96b3-5bdca8f8035f.gmc3.webp	21
56	e3993aa9-26ae-442f-b002-3549447d324f.md1.webp	22
57	495e48a5-b3fe-47ba-9b10-97d04c1763ba.md2.webp	22
58	7e069726-87c2-4ed9-99ce-58e803d11d35.md3.webp	22
59	b763d6cc-1d4f-40f8-9f90-ac6cdcf08a6b.javaс1.webp	25
60	848fb55c-bf97-4e07-a6f8-1aa50e32efe9.javaс2.webp	25
61	afe7dacc-b5e4-4f12-b114-a3458a587eb4.javaс3.webp	25
\.


--
-- TOC entry 2852 (class 0 OID 25468)
-- Dependencies: 205
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (id, count, date_time, price, status, person_id, product_id) FROM stdin;
7	1	2023-04-24 21:55:50.131337	2476	0	4	20
6	1	2023-04-24 21:55:50.075331	450	1	4	18
\.


--
-- TOC entry 2844 (class 0 OID 25397)
-- Dependencies: 197
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person (id, age, email, first_name, last_name, password, patronymic, phone, role) FROM stdin;
1	1	admin	admin	admin	$2a$10$Z42MaeEscpNIlLV85OEabOm8hs5lkIKAFWFl16.O04kTw8P0ov4Xa	admin	1	ADMIN
4	20	ivanov@ivanov.ru	Иванов	Иван	$2a$10$BV3n4tB2o0yZIvQhCVeQ9eXDzqM4oGMBr1xTO9HAOv7lyoMiIDdCy	Иванович	89998887654	USER
3	36	ahmetovRR@gmail.com	Руслан	Ахметов	$2a$10$G.vBnQKCByLZdD7ca9H6cOTVNMMcfPSAwcXM.p7DVtIRnGTRCroiG	Рафисович	89028517679	USER
2	32	ahmetov26@gmail.com	Святослав	Ахметов	$2a$10$v8SIBJ.TcZTBC88UuTPbq.drOlU075hKMZjR64b.ua3Ezo8VWjMsW	Рафисович	89028517670	ADMIN
\.


--
-- TOC entry 2848 (class 0 OID 25430)
-- Dependencies: 201
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (id, author, country, descriptions, pages, price, publisher, title, year_of_issue) FROM stdin;
18	Джордж Оруэлл	Англия	Культовый роман Джорджа Оруэлла в новом, полном, без цензуры и купюр, переводе Дарьи Целовальниковой.\r\n«1984» — одна из трех знаменитых антиутопий XX века. Жуткий и пугающий роман о том, что нет ничего страшнее тотальной несвободы.\r\nДействуя в интересах Партии, Уинстон Смит каждый день переписывает историю, вымарывая из неё данные, противоречащие сегодняшним заверениям Министерства Правды. С каждой новой ложью Уинстон всё больше ненавидит правительство, которое не интересуется ничем кроме абсолютной власти, и которое карает всех, кто осмеливается мыслить иначе. Но чем больше сам Уинстон стремится к свободе, тем труднее ему становится избежать ареста, ведь Большой Брат всегда следит за тобой…\r\nСвоеобразный антипод второй великой антиутопии XX века - "О дивный новый мир" Олдоса Хаксли. Что, в сущности, страшнее: доведенное до абсурда "общество потребления" - или доведенное до абсолюта "общество идеи"? По Оруэллу, нет и не может быть ничего ужаснее тотальной несвободы.	320	450	ACT	1984	2022
19	Достоевский Федор Михайлович	Россия	«Преступление и наказание» (1866) — одно из самых значительных произведений в истории мировой литературы. Это и глубокий философский роман, и тонкая психологическая драма, и захватывающий детектив, и величественная картина мрачного города, в недрах которого герои грешат и ищут прощения, жертвуют собой и отрекаются от себя ради ближних и находят успокоение в смирении, покаянии, вере. Главный герой романа Родион Раскольников решается на убийство, чтобы доказать себе и миру, что он не «тварь дрожащая», а «право имеет». Главным предметом исследования писателя становится процесс превращения добропорядочного, умного и доброго юноши в убийцу, а также то, как совершивший преступление Раскольников может искупить свою вину.\r\n«Преступление и наказание» неоднократно экранизировали, музыку к балету написал Чайковский, а недавно на одной из московских площадок по роману была поставлена рок-опера.	672	940	РосПечать	Преступление и наказание	2015
20	Лой М., Нимайер П., Лук Д.	Англия	Неважно, кто вы – разработчик ПО или пользователь, в любом случае вы слышали о языке Java. В этой книге вы на конкретных примерах изучите основы Java, API, библиотеки классов, приемы и идиомы программирования. Особое внимание авторы уделяют построению реальных приложений.\r\nВы освоите средства управления ресурсами и исключениями, а также познакомитесь с новыми возможностями языка, появившимися в последних версиях Java.\r\n• Программируйте на Java с использованием компилятора, интерпретатора и других инструментов.\r\n• Исследуйте средства управления потоками и параллельной обработки.\r\n• Изучайте обработку текста и мощные API.\r\n• Создавайте приложения и службы на базе современных сетевых коммуникаций или веб-технологий.	544	2476	Издательский Дом Питер	Программируем на Java	2023
21	Дюма Александр	Испания	Неоднократно экранизированную историю молодого моряка Эдмона Дантеса, заточенного по подлому навету в зловещий замок Иф, совершившего оттуда дерзкий побег, ставшего обладателем несметных богатств острова Монте-Кристо и положившего жизнь на то, чтобы изощренно отомстить обидчикам, знают все, – однако нам по-прежнему доставляет удовольствие возвращаться к ней, снова и снова переживая все ее увлекательные перепетии.	768	252	МосПЕЧАТЬ	Граф Монте-Кристо	2017
22	Гоголь Николай Васильевич	Россия	Поэма «Мертвые души» еще при жизни автора была переведена на множество других языков. Она имела невероятный успех. Никому до Гоголя и после него не удавалось так ярко и остро описать пороки и слабости русского человека, так живо и правдиво отразить важнейшие для России проблемы. Прошло 160 лет, и поэма звучит как только что написанная. Чичиковы, Коробочки, Ноздревы, Плюшкины, Собакевичи — их стремления, чувства, поступки не кажутся нам отголосками прошлого. Современное и острое звучание эти персонажи обретают, когда мы смотрим все новые и новые спектакли и фильмы по этой бессмертной поэме.	352	700	МосПЕЧАТЬ	Мертвые души	2020
25	Автор: Лой М., Нимайер П., Лук Д.	Англия	Описание: Неважно, кто вы – разработчик ПО или пользователь, в любом случае вы слышали о языке Java. В этой книге вы на конкретных примерах изучите основы Java, API, библиотеки классов, приемы и идиомы программирования. Особое внимание авторы уделяют построению реальных приложений. Вы освоите средства управления ресурсами и исключениями, а также познакомитесь с новыми возможностями языка, появившимися в последних версиях Java. • Программируйте на Java с использованием компилятора, интерпретатора и других инструментов. • Исследуйте средства управления потоками и параллельной обработки. • Изучайте обработку текста и мощные API. • Создавайте приложения и службы на базе современных сетевых коммуникаций или веб-технологий.	544	1900	МосПЕЧАТЬ	Программируем на JAVA	2019
\.


--
-- TOC entry 2850 (class 0 OID 25450)
-- Dependencies: 203
-- Data for Name: product_cart; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_cart (id, person_id, product_id) FROM stdin;
\.


--
-- TOC entry 2866 (class 0 OID 0)
-- Dependencies: 198
-- Name: image_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.image_id_seq', 61, true);


--
-- TOC entry 2867 (class 0 OID 0)
-- Dependencies: 204
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_seq', 7, true);


--
-- TOC entry 2868 (class 0 OID 0)
-- Dependencies: 196
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person_id_seq', 4, true);


--
-- TOC entry 2869 (class 0 OID 0)
-- Dependencies: 202
-- Name: product_cart_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_cart_id_seq', 38, true);


--
-- TOC entry 2870 (class 0 OID 0)
-- Dependencies: 200
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 25, true);


--
-- TOC entry 2708 (class 2606 OID 25427)
-- Name: image image_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);


--
-- TOC entry 2716 (class 2606 OID 25473)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- TOC entry 2704 (class 2606 OID 25405)
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- TOC entry 2714 (class 2606 OID 25455)
-- Name: product_cart product_cart_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT product_cart_pkey PRIMARY KEY (id);


--
-- TOC entry 2710 (class 2606 OID 25440)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 2706 (class 2606 OID 25409)
-- Name: person uk_fwmwi44u55bo4rvwsv0cln012; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT uk_fwmwi44u55bo4rvwsv0cln012 UNIQUE (email);


--
-- TOC entry 2712 (class 2606 OID 25442)
-- Name: product uk_qka6vxqdy1dprtqnx9trdd47c; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT uk_qka6vxqdy1dprtqnx9trdd47c UNIQUE (title);


--
-- TOC entry 2720 (class 2606 OID 25474)
-- Name: orders fk1b0m4muwx1t377w9if3w6wwqn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk1b0m4muwx1t377w9if3w6wwqn FOREIGN KEY (person_id) REFERENCES public.person(id);


--
-- TOC entry 2721 (class 2606 OID 25479)
-- Name: orders fk787ibr3guwp6xobrpbofnv7le; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk787ibr3guwp6xobrpbofnv7le FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 2717 (class 2606 OID 25443)
-- Name: image fkgpextbyee3uk9u6o2381m7ft1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT fkgpextbyee3uk9u6o2381m7ft1 FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 2719 (class 2606 OID 25461)
-- Name: product_cart fkhpnrxdy3jhujameyod08ilvvw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT fkhpnrxdy3jhujameyod08ilvvw FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 2718 (class 2606 OID 25456)
-- Name: product_cart fksgnkc1ko2i1o9yr2p63ysq3rn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT fksgnkc1ko2i1o9yr2p63ysq3rn FOREIGN KEY (person_id) REFERENCES public.person(id);


-- Completed on 2023-04-24 22:22:53

--
-- PostgreSQL database dump complete
--

