--
-- PostgreSQL database dump
--

-- Dumped from database version 10.22
-- Dumped by pg_dump version 10.22

-- Started on 2023-04-24 20:06:52

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
-- TOC entry 2846 (class 0 OID 25422)
-- Dependencies: 199
-- Data for Name: image; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.image (id, file_name, product_id) FROM stdin;
20	dbc64f29-a420-42fa-81b1-84885dfaf8ff.ph1.jpg	8
21	ae553319-69e8-48c7-a3ae-162245516811.	8
22	4d302283-3c48-4c53-b3df-c5d41a8a6524.	8
23	b9d31a87-e6cc-4cf7-aa32-dfd201f257a2.ph2.jpg	9
24	20ed1c65-2bc1-4896-9e27-c4d143fe2459.	9
25	c2d34da5-b485-4fa8-9361-b04f175d4f2c.	9
26	bef3674b-88a3-4bf6-a3dd-d70769cf9be6.ph3.jpg	12
27	3aca3c4d-67a7-4df2-bd5f-580c98098845.	12
28	7267dc96-ec9f-4044-9eaf-17394fab821c.	12
38	5eb08262-f07f-4bdb-abae-6489b5b754d5.ph8.jpg	16
39	f01abfd9-8a0f-4842-bd75-3c300b1ebdc3.	16
40	2ff293de-ac95-471d-a7e4-7d1835e40d91.	16
41	2fb2c1b7-f507-4110-8b17-f9be8f645f6a.zxc1.jpg	17
42	fe96c77f-4961-43d5-85d5-c3723b2daa99.zxc2.jpg	17
43	cf71531c-47a2-4708-8211-bb83117fa49f.zxc3.jpg	17
\.


--
-- TOC entry 2852 (class 0 OID 25468)
-- Dependencies: 205
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (id, count, date_time, price, status, person_id, product_id) FROM stdin;
1	1	2023-04-23 10:54:43.344019	13	0	2	12
5	1	2023-04-23 11:07:43.141423	12	1	2	9
2	1	2023-04-23 10:54:43.42902	12	2	2	8
3	1	2023-04-23 11:01:31.536119	12	3	2	8
4	1	2023-04-23 11:07:43.065426	12	2	2	9
\.


--
-- TOC entry 2844 (class 0 OID 25397)
-- Dependencies: 197
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person (id, age, email, first_name, last_name, password, patronymic, phone, role) FROM stdin;
1	1	admin	admin	admin	$2a$10$Z42MaeEscpNIlLV85OEabOm8hs5lkIKAFWFl16.O04kTw8P0ov4Xa	admin	1	ADMIN
2	32	ahmetov26@gmail.com	Святослав	Ахметов	$2a$10$v8SIBJ.TcZTBC88UuTPbq.drOlU075hKMZjR64b.ua3Ezo8VWjMsW	Рафисович	89028517670	USER
3	36	ahmetovRR@gmail.com	Руслан	Ахметов	$2a$10$G.vBnQKCByLZdD7ca9H6cOTVNMMcfPSAwcXM.p7DVtIRnGTRCroiG	Рафисович	89028517679	USER
\.


--
-- TOC entry 2848 (class 0 OID 25430)
-- Dependencies: 201
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (id, author, country, descriptions, pages, price, publisher, title, year_of_issue) FROM stdin;
9	asd	asd	asd	12	12	asd	asd	12
12	qwe	qwe	qwe	13	13	qwe	qwe1	13
8	qweq	qweq	qweq	123	123	qweq	qweq	123
16	ert	ert	ert	123	3	ert	ert	123
17	zxczxc	zxczxc	xzczxc	123	123	zxczxc	zxczxc	123
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

SELECT pg_catalog.setval('public.image_id_seq', 43, true);


--
-- TOC entry 2867 (class 0 OID 0)
-- Dependencies: 204
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_seq', 5, true);


--
-- TOC entry 2868 (class 0 OID 0)
-- Dependencies: 196
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person_id_seq', 3, true);


--
-- TOC entry 2869 (class 0 OID 0)
-- Dependencies: 202
-- Name: product_cart_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_cart_id_seq', 35, true);


--
-- TOC entry 2870 (class 0 OID 0)
-- Dependencies: 200
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 17, true);


-- Completed on 2023-04-24 20:06:53

--
-- PostgreSQL database dump complete
--

