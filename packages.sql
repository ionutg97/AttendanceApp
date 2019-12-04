create or replace PACKAGE BODY pack_attendance_lists IS

    PROCEDURE add_attendance_list (
        v_name   IN   VARCHAR,
        v_type   IN   VARCHAR,
        v_week   IN   INTEGER,
        v_out_id out INTEGER
    ) IS
    BEGIN
        INSERT INTO attendance_lists VALUES (
            NULL,
            v_name,
            v_type,
            v_week
        );
        
        SELECT MAX(id_attendance_list) into v_out_id  FROM attendance_lists;

    END add_attendance_list;

END pack_attendance_lists;



create or replace PACKAGE BODY pack_attendances_students IS

   PROCEDURE add_attendances_student (
        v_id_attendance   IN   INTEGER,
        v_id_student   IN   INTEGER, 
        v_grade   IN   Integer,
        v_detail  IN   VARCHAR
        
    ) IS
    BEGIN
        INSERT INTO attends_students VALUES (
            NULL,
            (select id_attendace from attendances where id_attendace=v_id_attendance),
            (select id_student from students where id_student=v_id_student),
            v_grade,
            v_detail
        );

    END add_attendances_student;

END pack_attendances_students;


create or replace PACKAGE BODY pack_classrooms IS

    PROCEDURE add_classroom (
        v_name IN VARCHAR
    ) IS
    BEGIN
        INSERT INTO classrooms VALUES (
            NULL,
            v_name
        );

    END add_classroom;

END pack_classrooms;

create or replace PACKAGE BODY pack_attendances IS

     PROCEDURE add_attendance (
        v_id_class   IN   Integer,
        v_id_teacher   IN   Integer,
        v_id_attend   IN   Integer
    ) IS
    BEGIN
        INSERT INTO attendances VALUES (
            NULL,
            (select id_classroom from classrooms where id_classroom = v_id_class),
            (select id_teacher from teachers where id_teacher = v_id_teacher),
            (select id_attendance_list from attendance_lists where id_attendance_list = v_id_attend)
        );

    END add_attendance;

END pack_attendances;



create or replace PACKAGE BODY pack_groups IS

    PROCEDURE add_group (
        v_name IN VARCHAR
    ) IS
    BEGIN
        INSERT INTO groups VALUES (
            NULL,
            v_name
        );

    END add_group;

    PROCEDURE get_group (
        v_name     IN    VARCHAR,
        out_id     OUT   INTEGER,
        out_name   OUT   VARCHAR
    ) IS
    BEGIN
        SELECT
            id_group,
            name_group
        INTO
            out_id,
            out_name
        FROM
            groups
        WHERE
            name_group = v_name;

    END get_group;

    FUNCTION get_allgroups RETURN SYS_REFCURSOR AS
        l_rc SYS_REFCURSOR;
    BEGIN
        OPEN l_rc FOR SELECT
                          id_group,
                          name_group
                      FROM
                          groups;

        RETURN l_rc;
    END get_allgroups;

    PROCEDURE get_group2 (
        p_recordset OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_recordset FOR SELECT
                                 id_group,
                                 name_group
                             FROM
                                 groups;

    END get_group2;

    PROCEDURE get_groups (
        out_groups OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN out_groups FOR SELECT
                                id_group,
                                name_group 
                            FROM
                                groups;
    
    END get_groups;

END pack_groups;

create or replace PACKAGE BODY pack_groups_attend_lists IS
    PROCEDURE add_groups_attend_list (
        v_id_group             IN   INTEGER,
        v_id_attendance_list   IN   INTEGER
    ) IS
    BEGIN
        INSERT INTO groups_attend_lists VALUES (
            NULL,
            (
                SELECT
                    id_group
                FROM
                    groups
                WHERE
                    id_group = v_id_group
            ),
            (
                SELECT
                    id_attendance_list
                FROM
                    attendance_lists
                WHERE
                    id_attendance_list = v_id_attendance_list
            )
        );

    END add_groups_attend_list;
//------------ CURSOR -------------------------------------------  

    CURSOR cursorGroups RETURN GroupRecTyp is
      SELECT id_group , id_attendance_list FROM groups;
      
//----------------- procedure that use cursor -------------------------  

     PROCEDURE get_allGroups_byAttendList (
        v_id_attendance_list in INTEGER,
        out_array_id_groups OUT array_int
        ) AS
        begin
            v_count   NUMBER;
            v_count := 0;
            
            id_groups Integer;
            id_attendance_list Integer;
            
            out_array_id_groups := NEW array_int ();
            out_array_id_groups.EXTEND (10);
            
            OPEN cursorGroups;
            LOOP
                Fetch cursorGroups into id_groups,id_attendance_list;
                Exit when cursorGroups%NOTFOUND OR cursorGroups%NOTFOUND IS NULL;
                    IF id_attendance = v_id_attendence_list THEN
                        out_array_id_groups(v_count); v_count = v_count + 1;
                        END IF END
    loop;
    CLOSE cursorgroups;
    RETURN out_array_id_groups;
END get_allgroups_byattendlist;

END pack_groups_attend_lists;


create or replace PACKAGE BODY pack_students IS

    PROCEDURE add_student (
        v_name               IN   VARCHAR,
        v_indentity_number   IN   VARCHAR,
        v_group_name         IN   VARCHAR
    ) IS
    BEGIN
        INSERT INTO students VALUES (
            NULL,
            v_name,
            v_indentity_number,
            (Select id_group from groups where  name_group = v_group_name)
        );

    END add_student;

END pack_students;


create or replace PACKAGE BODY pack_teachers IS

    PROCEDURE add_teacher (
        v_name IN VARCHAR
    ) IS
    BEGIN
        INSERT INTO teachers VALUES (
            NULL,
            v_name
        );

    END add_teacher;

END pack_teachers;