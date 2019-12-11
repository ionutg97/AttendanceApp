-- Generated by Oracle SQL Developer Data Modeler 19.2.0.182.1216
--   at:        2019-11-25 15:59:16 EET
--   site:      Oracle Database 12c
--   type:      Oracle Database 12c



DROP TABLE attendance_lists CASCADE CONSTRAINTS;

DROP TABLE attendances CASCADE CONSTRAINTS;

DROP TABLE attends_students CASCADE CONSTRAINTS;

DROP TABLE classrooms CASCADE CONSTRAINTS;

DROP TABLE groups CASCADE CONSTRAINTS;

DROP TABLE groups_attend_lists CASCADE CONSTRAINTS;

DROP TABLE students CASCADE CONSTRAINTS;

DROP TABLE teachers CASCADE CONSTRAINTS;

CREATE TABLE attendance_lists (
    id_attendance_list   INTEGER NOT NULL,
    name_attendance      VARCHAR2(250),
    type                 VARCHAR2(20)
);

ALTER TABLE attendance_lists ADD CONSTRAINT attendance_lists_pk PRIMARY KEY ( id_attendance_list );


CREATE TABLE attendances (
    id_attendace         INTEGER NOT NULL,
    grade                INTEGER,
    detail               VARCHAR2(250),
    id_classroom         INTEGER NOT NULL,
    id_teacher           INTEGER NOT NULL,
    id_attendance_list   INTEGER NOT NULL
);



ALTER TABLE attendances
    ADD CONSTRAINT grades_ck CHECK ( grade BETWEEN 2 AND 10 );

ALTER TABLE attendances ADD CONSTRAINT attendances_pk PRIMARY KEY ( id_attendace );

CREATE TABLE attends_students (
    id             INTEGER NOT NULL,
    id_attendace   INTEGER NOT NULL,
    id_student     INTEGER NOT NULL
);

ALTER TABLE attends_students add CONSTRAINT id_attend_student_unique UNIQUE (id_attendace, id_student);

ALTER TABLE attends_students ADD CONSTRAINT attends_students_pk PRIMARY KEY ( id );

CREATE TABLE classrooms (
    id_classroom     INTEGER NOT NULL,
    name_classroom   VARCHAR2(20)
);

ALTER TABLE classrooms ADD CONSTRAINT classrooms_pk PRIMARY KEY ( id_classroom );

CREATE TABLE groups (
    id_group     INTEGER NOT NULL,
    name_group   VARCHAR2(20)
);

ALTER TABLE groups ADD CONSTRAINT classrooms_pkv1 PRIMARY KEY ( id_group );
ALTER TABLE groups ADD CONSTRAINT unique_name_group UNIQUE (name_group);


CREATE TABLE groups_attend_lists (
    id                   INTEGER NOT NULL,
    id_group             INTEGER NOT NULL,
    id_attendance_list   INTEGER NOT NULL
);

ALTER TABLE groups_attend_lists ADD CONSTRAINT groups_attend_lists_pk PRIMARY KEY ( id );

CREATE TABLE students (
    id_student        INTEGER NOT NULL,
    name_student      VARCHAR2(250),
    identity_number   VARCHAR2(7),
    id_group          INTEGER NOT NULL
);

ALTER TABLE students ADD CONSTRAINT students_pk PRIMARY KEY ( id_student );

ALTER TABLE students ADD CONSTRAINT unique_identity_number UNIQUE ( identity_number );

CREATE TABLE teachers (
    id_teacher     INTEGER NOT NULL,
    name_teacher   VARCHAR2(250)
);

ALTER TABLE teachers ADD CONSTRAINT classrooms_pkv2 PRIMARY KEY ( id_teacher );

ALTER TABLE groups_attend_lists
    ADD CONSTRAINT attend_lists_groups FOREIGN KEY ( id_attendance_list )
        REFERENCES attendance_lists ( id_attendance_list );

ALTER TABLE attendances
    ADD CONSTRAINT attendance_list_to_attendances FOREIGN KEY ( id_attendance_list )
        REFERENCES attendance_lists ( id_attendance_list );

ALTER TABLE attends_students
    ADD CONSTRAINT attends_students FOREIGN KEY ( id_attendace )
        REFERENCES attendances ( id_attendace );

ALTER TABLE attendances
    ADD CONSTRAINT classrooms_attendances FOREIGN KEY ( id_classroom )
        REFERENCES classrooms ( id_classroom );

ALTER TABLE groups_attend_lists
    ADD CONSTRAINT groups_attend_lists FOREIGN KEY ( id_group )
        REFERENCES groups ( id_group );

ALTER TABLE students
    ADD CONSTRAINT groups_students FOREIGN KEY ( id_group )
        REFERENCES groups ( id_group )
            ON DELETE CASCADE;

ALTER TABLE attends_students
    ADD CONSTRAINT students_attends FOREIGN KEY ( id_student )
        REFERENCES students ( id_student );

ALTER TABLE attendances
    ADD CONSTRAINT teachers_attendances FOREIGN KEY ( id_teacher )
        REFERENCES teachers ( id_teacher );

CREATE SEQUENCE attendance_lists_id_attendance START WITH 30000 NOCACHE ORDER;

CREATE SEQUENCE attendances_id_attendace_seq START WITH 2000 NOCACHE ORDER;

CREATE SEQUENCE classrooms_id_classroom_seq START WITH 200 NOCACHE ORDER;

CREATE SEQUENCE groups_id_group_seq START WITH 100 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER groups_id_group_trg BEFORE
    INSERT ON groups
    FOR EACH ROW
    WHEN ( new.id_group IS NULL )
BEGIN
    :new.id_group := groups_id_group_seq.nextval;
END;
/

CREATE SEQUENCE students_id_student_seq START WITH 1000 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER students_id_student_trg BEFORE
    INSERT ON students
    FOR EACH ROW
    WHEN ( new.id_student IS NULL )
BEGIN
    :new.id_student := students_id_student_seq.nextval;
END;
/

CREATE SEQUENCE techers_id_teacher_seq START WITH 200 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER techers_id_teacher_trg BEFORE
    INSERT ON teachers
    FOR EACH ROW
    WHEN ( new.id_teacher IS NULL )
BEGIN
    :new.id_teacher := techers_id_teacher_seq.nextval;
END;
/

-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                             8
-- CREATE INDEX                             0
-- ALTER TABLE                             18
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           1
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          5
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- TSDP POLICY                              0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0