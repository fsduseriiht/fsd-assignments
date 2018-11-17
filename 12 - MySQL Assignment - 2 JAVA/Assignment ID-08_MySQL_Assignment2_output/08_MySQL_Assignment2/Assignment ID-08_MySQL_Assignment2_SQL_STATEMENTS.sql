------------------------------------------------------------------------------------------------------------------
-- 1. Write a query to retrieve the book and its subject details using inner join.
------------------------------------------------------------------------------------------------------------------
--Query to Get All Subjects From DB
SELECT * FROM SUBJECT_TABLE S, BOOK_TABLE B where B.subjectId = S.subjectId order by S.subjectId asc;
--	Output for [SELECT * FROM SUBJECT_TABLE S, BOOK_TABLE B where B.subjectId = S.subjectId order by S.subjectId asc;]
--	SUBJECTID	SUBTITLE			DURATIONINHOURS	BOOKID	TITLE							PRICE	VOLUME	PUBLISHDATE	SUBJECTID
--	1001		Hindi-Literature	100				205		"Hindi Prose-II"				88		1		1999-05-04	1001
--	1001		Hindi-Literature	100				201		"Hindi Prose-I"					77		2		1987-07-01	1001
--	1002		Geography-Primary	50				111		"Geography for Newbies Vol.I"	113		1		1998-09-01	1002
--	1002		Geography-Primary	50				112		"Geography for Beginers vol2"	79		2		1999-07-01	1002
--	1003		Physics(beginers)	45				401		"Physics for Newbies"			190		1		1987-02-09	1003
--	1004		Hindi-Grammar		95				221		"Hindi Vyakaran-I"				44		1		2012-02-02	1004
--	1004		Hindi-Grammar		95				220		"Hindi Vyakaran-II"				99		7		2013-05-06	1004
--	1005		Geography-Advanced	44				113		"Advanced Geography"			199		3		2001-09-02	1005
--	1006		Physics(advanced)	150				402		"Physics for Professionals"		301		2		1987-02-09	1006




------------------------------------------------------------------------------------------------------------------
-- 2. Write a query to retrieve the books list published in a given specific date interval
------------------------------------------------------------------------------------------------------------------
-- Query To Get all values form BOOK Table --
SELECT * FROM BOOK_TABLE ORDER BY PUBLISHDATE ASC;
--	Output for [SELECT * FROM BOOK_TABLE ORDER BY PUBLISHDATE ASC;]
-- 	BOOKID		TITLE							PRICE		VOLUME		PUBLISHDATE		SUBJECTID
-- 	402			"Physics for Professionals"		301			2			1987-02-09		1006
-- 	401			"Physics for Newbies"			190			1			1987-02-09		1003
-- 	301			"Wren & Martin"					90			1			1987-02-09		NULL
-- 	201			"Hindi Prose-I"					77			2			1987-07-01		1001
-- 	111			"Geography for Newbies Vol.I"	113			1			1998-09-01		1002
-- 	205			"Hindi Prose-II"				88			1			1999-05-04		1001
-- 	112			"Geography for Beginers vol2"	79			2			1999-07-01		1002
-- 	113			"Advanced Geography"			199			3			2001-09-02		1005
-- 	221			"Hindi Vyakaran-I"				44			1			2012-02-02		1004
-- 	220			"Hindi Vyakaran-II"				99			7			2013-05-06		1004
-- To retrieve the books list published in a given specific date interval -- 1998-09-01 to 2012-02-02
SELECT * FROM BOOK_TABLE WHERE DATE(PUBLISHDATE) BETWEEN STR_TO_DATE('1998-09-01','%Y-%m-%d') AND STR_TO_DATE('2012-02-02','%Y-%m-%d');
--	Output for [
--			SELECT * FROM BOOK_TABLE WHERE DATE(PUBLISHDATE)
--			BETWEEN STR_TO_DATE('1998-09-01','%Y-%m-%d') AND STR_TO_DATE('2012-02-02','%Y-%m-%d');
-- 				]
--	BOOKID		TITLE							PRICE	VOLUME	PUBLISHDATE	SUBJECTID
--	205			"Hindi Prose-II"				88		1		1999-05-04	1001
--	111			"Geography for Newbies Vol.I"	113		1		1998-09-01	1002
--	112			"Geography for Beginers vol2"	79		2		1999-07-01	1002
--	221			"Hindi Vyakaran-I"				44		1		2012-02-02	1004
--	113			"Advanced Geography"			199		3		2001-09-02	1005




------------------------------------------------------------------------------------------------------------------
-- 3. Write a query to retrieve the number of books referred by each subject 
-- where the subject title starts with a specific character (say ‘j’ for instance).
------------------------------------------------------------------------------------------------------------------
-- Query to retrieve the number of books referred by each subject 
-- where the subject title starts with a specific character
SELECT S.SUBJECTID, S.SUBTITLE, COUNT(*) AS "NUMBER_OF_BOOKS"
FROM SUBJECT_TABLE S, BOOK_TABLE B
where B.SUBJECTID = S.SUBJECTID
GROUP BY S.SUBJECTID
HAVING S.SUBTITLE LIKE 'H%' OR S.SUBTITLE LIKE 'G%';
--	Output for [
--			SELECT S.SUBJECTID, S.SUBTITLE, COUNT(*) AS "NUMBER_OF_BOOKS"
--			FROM SUBJECT_TABLE S, BOOK_TABLE B
--			where B.SUBJECTID = S.SUBJECTID
--			GROUP BY S.SUBJECTID
--			HAVING S.SUBTITLE LIKE 'H%' OR S.SUBTITLE LIKE 'G%';
--				]
--	SUBJECTID	SUBTITLE			NUMBER_OF_BOOKS
--	1001		Hindi-Literature		2
--	1002		Geography-Primary		2
--	1004		Hindi-Grammar			2
--	1005		Geography-Advanced		1
