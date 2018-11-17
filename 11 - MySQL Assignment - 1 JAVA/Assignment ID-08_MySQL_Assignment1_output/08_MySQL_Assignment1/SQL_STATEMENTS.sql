--Get All Subjects From DB
SELECT * FROM SUBJECT_TABLE S, BOOK_TABLE B where B.subjectId = S.subjectId order by S.subjectId asc;

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------

-- Get All Books From DB
SELECT * FROM BOOK_TABLE;

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------

-- Add Book To DB
-- INSERT into BOOK_TABLE (bookId, title, price, volume, publishDate) values (?, ?, ?, ?, ?);
INSERT INTO BOOK_TABLE (BOOKID, TITLE, PRICE, VOLUME, PUBLISHDATE, SUBJECTID) VALUES(201, 'Hindi Prose-I', 77.0, 2, '1987-07-01', 1001);
INSERT INTO BOOK_TABLE (BOOKID, TITLE, PRICE, VOLUME, PUBLISHDATE, SUBJECTID) VALUES(205, 'Hindi Prose-II', 88.0, 1, '1999-05-04', 1001);
INSERT INTO BOOK_TABLE (BOOKID, TITLE, PRICE, VOLUME, PUBLISHDATE, SUBJECTID) VALUES(111, 'Geography for Newbies Vol.I', 112.88, 1, '1998-09-01', 1002);
INSERT INTO BOOK_TABLE (BOOKID, TITLE, PRICE, VOLUME, PUBLISHDATE, SUBJECTID) VALUES(112, 'Geography for Beginers vol2', 78.88, 2, '1999-07-01', 1002);
INSERT INTO BOOK_TABLE (BOOKID, TITLE, PRICE, VOLUME, PUBLISHDATE, SUBJECTID) VALUES(221, 'Hindi Vyakaran-I', 44.0, 1, '2012-02-02', 1004);
INSERT INTO BOOK_TABLE (BOOKID, TITLE, PRICE, VOLUME, PUBLISHDATE, SUBJECTID) VALUES(220, 'Hindi Vyakaran-II', 99.0, 7, '2013-05-06', 1004);
INSERT INTO BOOK_TABLE (BOOKID, TITLE, PRICE, VOLUME, PUBLISHDATE, SUBJECTID) VALUES(113, 'Advanced Geography', 198.78, 3, '2001-09-02', 1005);
INSERT INTO BOOK_TABLE (BOOKID, TITLE, PRICE, VOLUME, PUBLISHDATE, SUBJECTID) VALUES(301, 'Wren & Martin', 89.88, 1, '1987-02-09', NULL);
INSERT INTO BOOK_TABLE (BOOKID, TITLE, PRICE, VOLUME, PUBLISHDATE, SUBJECTID) VALUES(401, 'Physics for Newbies', 189.88, 1, '1987-02-09', 1003);
INSERT INTO BOOK_TABLE (BOOKID, TITLE, PRICE, VOLUME, PUBLISHDATE, SUBJECTID) VALUES(402, 'Physics for Professionals', 300.88, 2, '1987-02-09', 1006);
-- Commit the insert query changes
COMMIT;

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------

-- Add Subject To DB
-- Insert into SUBJECT_TABLE regarding subject details
-- INSERT into SUBJECT_TABLE (subjectId, subtitle, durationInHours) values (?, ?, ?);
INSERT INTO SUBJECT_TABLE (SUBJECTID, SUBTITLE, DURATIONINHOURS) VALUES(1001, 'Hindi-Literature', 100);
INSERT INTO SUBJECT_TABLE (SUBJECTID, SUBTITLE, DURATIONINHOURS) VALUES(1002, 'Geography-Primary', 50);
INSERT INTO SUBJECT_TABLE (SUBJECTID, SUBTITLE, DURATIONINHOURS) VALUES(1003, 'Physics(beginers)', 45);
INSERT INTO SUBJECT_TABLE (SUBJECTID, SUBTITLE, DURATIONINHOURS) VALUES(1006, 'Physics(advanced)', 150);
INSERT INTO SUBJECT_TABLE (SUBJECTID, SUBTITLE, DURATIONINHOURS) VALUES(1004, 'Hindi-Grammar', 95);
INSERT INTO SUBJECT_TABLE (SUBJECTID, SUBTITLE, DURATIONINHOURS) VALUES(1005, 'Geography-Advanced', 44);
-- Commit the insert query changes
COMMIT;
--Update into BOOK_TABLE for the Books that are going to be referred by the corresponding Subjects
-- UPDATE BOOK_TABLE SET subjectId=? WHERE bookId=?;
UPDATE BOOK_TABLE SET subjectId=1001 WHERE bookId=201;
UPDATE BOOK_TABLE SET subjectId=1001 WHERE bookId=205;
UPDATE BOOK_TABLE SET subjectId=NULL WHERE bookId=301;
COMMIT;

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------

-- Delete Subject From DB
-- DELETE FROM SUBJECT_TABLE regarding subject details
-- DELETE from SUBJECT_TABLE where subjectId=?;
DELETE from SUBJECT_TABLE where subjectId=1006;
-- UPDATE BOOK_TABLE regarding with subject id as NULL
-- UPDATE BOOK_TABLE set subjectId=NULL where bookId = ?;
UPDATE BOOK_TABLE set subjectId=NULL where bookId = 402;
-- Commit the insert query changes
COMMIT;

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------

-- Delete Book From DB
-- DELETE from BOOK_TABLE where bookId=?;
DELETE from BOOK_TABLE where bookId=402;
-- Commit the insert query changes
COMMIT;


