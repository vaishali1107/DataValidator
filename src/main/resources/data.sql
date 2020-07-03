INSERT INTO hospitalization_status VALUES (11,'Admitted'), (12,'Discharge'), (13,'Death') on conflict do nothing ;

INSERT INTO ward_list VALUES (1,'Aundh - Baner'), (2,'Bhawani Peth'), (3,'Bibwewadi'), (4,'Dhankawadi  -  Sahakarnagar'), (5,'Dhole Patil Road'), (6,'Hadapsar  -  Mundhwa'), (7,'Kasba  -  Vishrambaugwada'), (8,'Kondhwa  -  Yewalewadi'), (9,'Kothrud  -  Bawdhan'), (10,'Nagar Road  -  Vadgaonsheri'), (11,'Shivajinagar  -  Gholeroad'), (12,'Sinhgad Road'), (13,'Wanawadi  -  Ramtekadi'), (14,'Warje  -  Karvenagar'), (15,'Yerawada - Kalas - Dhanori'), (16,'Incorrect/Incomplete Address'), (17,'Address Not Available'), (18,'Out of PMC') on conflict do nothing ;   

INSERT INTO sample_result VALUES (1,'Positive'), (2,'Negative'), (3,'Awaiting') on conflict do nothing ;

INSERT INTO testing_labs VALUES (1,'AG Diagnostics'), (2,'BJ GMC'), (3,'Genapath'), (4,'JYOT'), (5,'Metropolis'), (6,'NIV Pune'), (7,'Outside Lab'), (8,'Private Lab'), (9,'Religare Lab'), (10,'Sahyadri Lab'), (11,'Sasoon'), (12,'SRL'), (13,'Suburban Diagnostics'), (14,'ThyroCare') on conflict do nothing ;

INSERT INTO status VALUES (1,'Stable'), (2,'Critical'), (3,'On Ventilator'), (4,'Death') on conflict do nothing ;

INSERT INTO traceable VALUES (1,'Yes'), (2,'No') on conflict do nothing ;

INSERT INTO sample_collected VALUES (1,'Yes'), (2,'No') on conflict do nothing ;

INSERT INTO surveillance_status VALUES (1,'Under Surveillance'), (2,'Migrated'), (3,'Surveillance - Completed') on conflict do nothing ;

INSERT INTO migration  VALUES (1,'Out Of India'), (2,'Other State') on conflict do nothing ;

INSERT INTO state VALUES (1,'Andhra Pradesh'), (2,'Arunachal Pradesh'), (3,'Assam'), (4,'Bihar'), (5,'Chhattisgarh'), (6,'Goa'), (7,'Gujarat'), (8,'Haryana'), (9,'Himachal Pradesh'), (10,'Jharkhand'), (11,'Karnataka'), (12,'Kerala'), (13,'Madhya Pradesh'), (14,'Maharashtra'), (15,'Manipur'), (16,'Meghalaya'), (17,'Mizoram'), (18,'Nagaland'), (19,'Odisha'), (20,'Punjab'), (21,'Rajasthan'), (22,'Sikkim'), (23,'Tamil Nadu'), (24,'Telangana'), (25,'Tripura'), (26,'Uttar Pradesh'), (27,'Uttarakhand'), (28,'West Bengal') on conflict do nothing ; 

INSERT INTO source_info  VALUES (1,'Airport:APHO'), (2,'Airport:BOI'), (3,'Airport:MHA'), (4,'SELF REPORTING'), (5,'HOSPITAL'), (6,'CONTACT TRACING'), (7,'OTHERS'), (8,'APHO'), (9,'BOI'), (10,'MHA') on conflict do nothing ;

INSERT INTO country VALUES (1,'Andorra') ,(2,'United Arab Emirates') ,(3,'Afghanistan') ,(4,'Antigua and Barbuda') ,(5,'Anguilla') ,(6,'Albania') ,(7,'Armenia') ,(9,'Netherlands Antilles') ,(10,'Angola') ,(11,'Antarctica') ,(12,'Argentina') ,(13,'American Samoa') ,(14,'Austria') ,(15,'Australia') ,(16,'Aruba') ,(17,'Åland Islands') ,(18,'Azerbaijan') ,(19,'Bosnia and Herzegovina') ,(20,'Barbados') ,(21,'Bangladesh') ,(22,'Belgium') ,(23,'Burkina Faso') ,(24,'Bulgaria') ,(25,'Bahrain') ,(26,'Burundi') ,(27,'Benin') ,(28,'Saint Barthélemy') ,(29,'Bermuda') ,(30,'Brunei') ,(31,'Bolivia') ,(32,'Bonaire, Sint Eustatius and Saba') ,(33,'Brazil') ,(34,'Bahamas') ,(35,'Bhutan') ,(36,'Bouvet Island') ,(37,'Botswana') ,(38,'Belarus') ,(39,'Belize') ,(40,'Canada') ,(41,'Cocos Islands') ,(42,'The Democratic Republic Of Congo') ,(43,'Central African Republic') ,(44,'Congo') ,(45,'Switzerland') ,(46,'Cook Islands') ,(47,'Chile') ,(48,'Cameroon') ,(49,'China') ,(50,'Colombia') ,(51,'Costa Rica') ,(52,'Cuba') ,(53,'Cape Verde') ,(54,'Curaçao') ,(55,'Christmas Island') ,(56,'Cyprus') ,(57,'Czech Republic') ,(58,'Germany') ,(59,'Djibouti') ,(60,'Denmark') ,(61,'Dominica') ,(62,'Dominican Republic') ,(63,'Algeria') ,(64,'Ecuador') ,(65,'Estonia') ,(66,'Egypt') ,(67,'Western Sahara') ,(68,'Eritrea') ,(69,'Spain') ,(70,'Ethiopia') ,(71,'Finland') ,(72,'Fiji') ,(73,'Falkland Islands') ,(74,'Micronesia') ,(75,'Faroe Islands') ,(76,'France') ,(77,'Gabon') ,(78,'United Kingdom') ,(79,'Grenada') ,(80,'Georgia') ,(81,'French Guiana') ,(82,'Guernsey') ,(83,'Ghana') ,(84,'Gibraltar') ,(85,'Greenland') ,(86,'Gambia') ,(87,'Guinea') ,(88,'Guadeloupe') ,(89,'Equatorial Guinea') ,(90,'Greece') ,(91,'South Georgia And The South Sandwich Islands') ,(92,'Guatemala') ,(93,'Guam') ,(94,'Guinea - Bissau') ,(95,'Guyana') ,(96,'Hong Kong') ,(97,'Heard Island And McDonald Islands') ,(98,'Honduras') ,(100,'Croatia') ,(101,'Haiti') ,(102,'Hungary') ,(103,'Indonesia') ,(104,'Ireland') ,(105,'Israel') ,(106,'Isle Of Man') ,(107,'India') ,(108,'British Indian Ocean Territory') ,(109,'Iraq') ,(110,'Iran') ,(111,'Iceland') ,(112,'Italy') ,(113,'Jersey') ,(114,'Jamaica') ,(115,'Jordan') ,(116,'Japan') ,(117,'Kenya') ,(118,'Kyrgyzstan') ,(119,'Cambodia') ,(120,'Kiribati') ,(121,'Comoros') ,(122,'Saint Kitts And Nevis') ,(123,'North Korea') ,(124,'South Korea') ,(125,'Kuwait') ,(126,'Cayman Islands') ,(127,'Kazakhstan') ,(128,'Laos') ,(129,'Lebanon') ,(130,'Saint Lucia') ,(131,'Liechtenstein') ,(132,'Sri Lanka') ,(133,'Liberia') ,(134,'Lesotho') ,(135,'Lithuania') ,(136,'Luxembourg') ,(137,'Latvia') ,(138,'Libya') ,(139,'Morocco') ,(140,'Monaco') ,(141,'Moldova') ,(142,'Montenegro') ,(143,'Saint Martin') ,(144,'Madagascar') ,(145,'Marshall Islands') ,(146,'Macedonia') ,(147,'Mali') ,(148,'Myanmar') ,(149,'Mongolia') ,(150,'Macao') ,(151,'Northern Mariana Islands') ,(152,'Martinique') ,(153,'Mauritania') ,(154,'Montserrat') ,(155,'Malta') ,(156,'Mauritius') ,(157,'Maldives') ,(158,'Malawi') ,(159,'Mexico') ,(160,'Malaysia') ,(161,'Mozambique') ,(162,'Namibia') ,(163,'New Caledonia') ,(164,'Niger') ,(165,'Norfolk Island') ,(166,'Nigeria') ,(167,'Nicaragua') ,(168,'Netherlands') ,(169,'Norway') ,(170,'Nepal') ,(171,'Nauru') ,(172,'Niue') ,(173,'New Zealand') ,(174,'Oman') ,(175,'Panama') ,(176,'Peru') ,(177,'French Polynesia') ,(178,'Papua New Guinea') ,(179,'Philippines') ,(180,'Pakistan') ,(181,'Poland') ,(182,'Saint Pierre And Miquelon') ,(183,'Pitcairn') ,(184,'Puerto Rico') ,(185,'Palestine') ,(186,'Portugal') ,(187,'Palau') ,(188,'Paraguay') ,(189,'Qatar') ,(190,'Reunion') ,(191,'Romania') ,(192,'Serbia') ,(193,'Russia') ,(194,'Rwanda') ,(195,'Saudi Arabia') ,(196,'Solomon Islands') ,(197,'Seychelles') ,(198,'Sudan') ,(199,'Sweden') ,(200,'Singapore') ,(201,'Saint Helena') ,(202,'Slovenia') ,(203,'Svalbard And Jan Mayen') ,(204,'Slovakia') ,(205,'Sierra Leone') ,(206,'San Marino') ,(207,'Senegal') ,(208,'Somalia') ,(209,'Suriname') ,(210,'South Sudan') ,(211,'Sao Tome And Principe') ,(212,'El Salvador') ,(213,'Sint Maarten (Dutch part)') ,(214,'Syria') ,(215,'Swaziland') ,(216,'Turks And Caicos Islands') ,(217,'Chad') ,(218,'French Southern Territories') ,(219,'Togo') ,(220,'Thailand') ,(221,'Tajikistan') ,(222,'Tokelau') ,(223,'Timor - Leste') ,(224,'Turkmenistan') ,(225,'Tunisia') ,(226,'Tonga') ,(227,'Turkey') ,(228,'Trinidad and Tobago') ,(229,'Tuvalu') ,(230,'Taiwan') ,(231,'Tanzania') ,(232,'Ukraine') ,(233,'Uganda') ,(234,'United States Minor Outlying Islands') ,(235,'United States') ,(236,'Uruguay') ,(237,'Uzbekistan') ,(238,'Vatican') ,(239,'Saint Vincent And The Grenadines') ,(240,'Venezuela') ,(241,'British Virgin Islands') ,(242,'U.S. Virgin Islands') ,(243,'Vietnam') ,(244,'Vanuatu') ,(245,'Wallis And Futuna') ,(246,'Samoa') ,(247,'Yemen') ,(248,'Mayotte') ,(249,'South Africa') ,(250,'Zambia') ,(251,'Zimbabwe'),(252,'AF'),(253,'AR'),(254,'AU'),(255,'AT'),(256,'BE'),(257,'BR'),(258,'CA'),(259,'CN'),(260,'FR'),(261,'DE'),(262,'HK'),(263,'IN'),(264,'ID'),(265,'IE'),(266,'IT'),(267,'JP'),(268,'MY'),(269,'NP'),(270,'NZ'),(271,'SG'),(272,'ZA'),(273,'USA'),(274,'UK'),(275,'AE'),(276,'US'),(277,'ZW') on conflict do nothing ;


INSERT INTO gender VALUES (1,'M'), (2,'F'), (3,'Male'), (4,'Female'), (5,'FtM Male'), (6,'MtF Female') on conflict do nothing ;

INSERT INTO district  VALUES (1,'Pune') on conflict do nothing ;

INSERT INTO date_format  VALUES (1,'[0-3]?[0-9]-[0-3]?[0-9]-[0-9]{4}','dd-MM-yyyy'),(2,'[0-3]?[0-9]/[0-3]?[0-9]/[0-9]{4}','dd/MM/yyyy'),(3,'[0-3]?[0-9][.][0-3]?[0-9][.][0-9]{4}','dd.MM.yyyy') on conflict do nothing ;

INSERT INTO public.user_data(user_id,owner, contact, email, first_name,  last_name, password) VALUES ( 1,'admin', '1234567890', 'admin@gmail.com',  'admin', 'admin','$2a$10$Db6wdigv41OXMYOL3PkWyOjn5x3f.iepa85I6XUc6Nsu0gpinHJie' ) on conflict do nothing ;