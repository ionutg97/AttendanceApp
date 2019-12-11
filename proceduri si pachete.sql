create or replace PACKAGE pack_attendance_lists IS
    PROCEDURE add_attendance_list (
        v_name   IN   VARCHAR,
        v_type   IN   VARCHAR,
        v_week   IN   INTEGER,
         v_out_id out INTEGER
    );
    
     PROCEDURE get_attendance_lists (
        v_week      IN    VARCHAR,
        v_type      IN    VARCHAR,
        out_lists   OUT   SYS_REFCURSOR
    );

END pack_attendance_lists;

create or replace PACKAGE BODY pack_attendance_lists IS

    PROCEDURE add_attendance_list (
        v_name     IN    VARCHAR,
        v_type     IN    VARCHAR,
        v_week     IN    INTEGER,
        v_out_id   OUT   INTEGER
    ) IS
    BEGIN
        INSERT INTO attendance_lists VALUES (
            NULL,
            v_name,
            v_type,
            v_week
        );
        
        SELECT
            MAX(id_attendance_list)
        INTO v_out_id
        FROM
            attendance_lists;
            
        pack_attendances.add_attendance(307,211,v_out_id);    

    END add_attendance_list;

    PROCEDURE get_attendance_lists (
        v_week      IN    VARCHAR,
        v_type      IN    VARCHAR,
        out_lists   OUT   SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN out_lists FOR SELECT
                               id_attendance_list,
                               name_attendance,
                               type,
                               week
                           FROM
                               attendance_lists
                           WHERE
                               type = v_type
                               AND week = v_week;

    END get_attendance_lists;

END pack_attendance_lists;

create or replace PACKAGE pack_attendances IS
    PROCEDURE add_attendance (
        v_id_class   IN   Integer,
        v_id_teacher   IN   Integer,
        v_id_attend   IN   Integer
    ); 
    
 PROCEDURE get_attendances_studentss(
      --  v_id_attendance_list   IN    Number,
     --  v_out                  OUT   Number,
     out_lists              OUT   SYS_REFCURSOR
    );

END pack_attendances;

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


    PROCEDURE get_attendances_studentss (
      out_lists OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN out_lists FOR SELECT
                            id,
                            id_attendace,
                             ID_STUDENT,
                              grade,
                              detail
                          FROM
                              attends_students;
                              
    END get_attendances_studentss;

END pack_attendances;

 create or replace PACKAGE pack_attendances_students IS
    PROCEDURE update_attendances_students (
        v_attendance_list_id   IN   INTEGER,
        v_identity_number      IN   VARCHAR,
        v_grade                IN   INTEGER,
        v_detail               IN   VARCHAR
    );

    PROCEDURE add_attendances_students (
        v_identity_number        IN   VARCHAR,
        v_attendance_list_name   IN   VARCHAR,
        v_grade                  IN   INTEGER,
        v_detail                 IN   VARCHAR
    );

    PROCEDURE get_attendances_studentss (
        v_id_attendance_list   IN    NUMBER,
        out_lists              OUT   SYS_REFCURSOR
    );

    PROCEDURE add_all_students_by_group (
        v_id_attedance_list   IN   INTEGER,
        v_group_name          IN   VARCHAR
    );

    PROCEDURE add_student_all_parameter_trig (
        v_group_name     IN   VARCHAR,
        v_id_attendance   IN   INTEGER
    );

END pack_attendances_students;

create or replace PACKAGE BODY pack_attendances_students IS

    PROCEDURE update_attendances_students (
        v_attendance_list_id   IN   INTEGER,
        v_identity_number      IN   VARCHAR,
        v_grade                IN   INTEGER,
        v_detail               IN   VARCHAR
    ) IS
    BEGIN
        UPDATE attends_students
        SET
            grade = v_grade,
            detail = v_detail
        WHERE
            id_attendace = (
                SELECT
                    id_attendace
                FROM
                    attendances
                WHERE
                    id_attendance_list = v_attendance_list_id
            )
            AND id_student = (
                SELECT
                    id_student
                FROM
                    students
                WHERE
                    identity_number = v_identity_number
            );

    END update_attendances_students;
-------------------------------------------------------------------------------------

    PROCEDURE add_attendances_students (
        v_identity_number        IN   VARCHAR,
        v_attendance_list_name   IN   VARCHAR,
        v_grade                  IN   INTEGER,
        v_detail                 IN   VARCHAR
    ) IS
    BEGIN
        INSERT INTO attends_students VALUES (
            NULL,
            (
                SELECT
                    id_attendace
                FROM
                    attendances
                WHERE
                    id_attendance_list = (
                        SELECT
                            id_attendance_list
                        FROM
                            attendance_lists
                        WHERE
                            name_attendance = v_attendance_list_name
                    )
            ),
            (
                SELECT
                    id_student
                FROM
                    students
                WHERE
                    identity_number = v_identity_number
            ),
            v_grade,
            v_detail
        );

    END add_attendances_students;
---------------------------------------------------------------------------------

    PROCEDURE get_attendances_studentss (
        v_id_attendance_list   IN    NUMBER,
        out_lists              OUT   SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN out_lists FOR SELECT
                               id,
                               id_attendace,
                               id_student,
                               grade,
                               detail
                           FROM
                               attends_students
                           WHERE
                               id_attendace = (
                                   SELECT
                                       id_attendace
                                   FROM
                                       attendances
                                   WHERE
                                       id_attendance_list = v_id_attendance_list
                               );

    END get_attendances_studentss;
    
    ---------------------------------------

        PROCEDURE add_all_students_by_group (
            v_id_attedance_list   IN   INTEGER,
            v_group_name          IN   VARCHAR
        ) IS
    
            v_id_attendance   INTEGER;
            v_id_group        INTEGER;
            v_id_student      INTEGER;
            v_exists          INTEGER;
            CURSOR c1 IS
            SELECT
                id_student
            FROM
                students
            WHERE
                id_group = v_id_group;
    
        BEGIN
            pack_groups.get_group(v_group_name, v_id_group);
            SELECT
                id_attendace
            INTO v_id_attendance
            FROM
                attendances
            WHERE
                id_attendance_list = v_id_attedance_list;
    
            OPEN c1;
            LOOP
                v_exists := 0;
                FETCH c1 INTO v_id_student;
                EXIT WHEN c1%notfound OR c1%notfound IS NULL;
           
             pack_students.exist_student_on_attendance(v_id_student, v_id_attendance, v_exists);
    
                IF v_exists = 0 THEN
                    INSERT INTO attends_students VALUES (
                        NULL,
                        v_id_attendance,
                        v_id_student,
                        0,
                        'p'
                    );
    
                END IF;
    
            END LOOP;
    
            CLOSE c1;
        END add_all_students_by_group;
    
--------------------------------------------------------------------

    PROCEDURE add_student_all_parameter_trig (
        v_group_name      IN   VARCHAR,
        v_id_attendance   IN   INTEGER
    ) IS
    BEGIN
        INSERT INTO aaaaa VALUES (
            NULL,
            v_group_name,
            v_id_attendance
        );

    END add_student_all_parameter_trig;

END pack_attendances_students;

create or replace PACKAGE pack_classrooms IS
    PROCEDURE add_classroom (
        v_name IN VARCHAR
    );

END pack_classrooms;

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

create or replace PACKAGE pack_groups IS
    PROCEDURE add_group (
        v_name IN VARCHAR
    );

    PROCEDURE get_group (
        v_name   IN    VARCHAR,
        out_id   OUT   INTEGER
    );

    PROCEDURE get_groupById (
        v_id             IN    INTEGER,
        out_name_group   OUT   VARCHAR
    );

    FUNCTION get_allgroups RETURN SYS_REFCURSOR;

    PROCEDURE get_groups (
        out_groups OUT SYS_REFCURSOR
    );

END pack_groups;

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
        v_name   IN    VARCHAR,
        out_id   OUT   INTEGER
    ) IS
    BEGIN
        SELECT
            id_group
        INTO out_id
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

    PROCEDURE get_groupById (
        v_id             IN    INTEGER,
        out_name_group   OUT   VARCHAR
    ) iS
    BEGIN
        SELECT
            name_group
        INTO out_name_group
        FROM
            groups
        WHERE
            id_group = v_id;

    END get_groupById;

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

create or replace PACKAGE pack_groups_attend_lists IS
    PROCEDURE add_groups_attend_list (
        v_id_group             IN   INTEGER,
        v_id_attendance_list   IN   INTEGER
    );

    PROCEDURE get_groups_attend_list_by_id (
        v_id        IN    INTEGER,
        out_lists   OUT   SYS_REFCURSOR
    );
    
--    PROCEDURE get_allGroups_byAttendList (
--        v_id_attendance_list in INTEGER,
--        out_array_id_groups OUT INTEGER
--        );
--    

END pack_groups_attend_lists;

create or replace PACKAGE BODY pack_groups_attend_lists IS

    PROCEDURE add_groups_attend_list (
        v_id_group             IN   INTEGER,
        v_id_attendance_list   IN   INTEGER
    ) IS
    BEGIN
        INSERT INTO groups_attend_lists VALUES (
            NULL,
            v_id_group,
            v_id_attendance_list
        );

    END add_groups_attend_list;

    PROCEDURE get_groups_attend_list_by_id (
        v_id        IN    INTEGER,
        out_lists   OUT   SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN out_lists FOR SELECT
                               id_group,
                               id_attendance_list
                           FROM
                               groups_attend_lists
                           WHERE
                               id_attendance_list = v_id;

    END get_groups_attend_list_by_id;

END pack_groups_attend_lists;

create or replace PACKAGE pack_students IS
    PROCEDURE add_student (
        v_name               IN   VARCHAR,
        v_indentity_number   IN   VARCHAR,
        v_group_name         IN   VARCHAR
    );

    PROCEDURE get_all_students_by_group (
        v_id_group   IN    VARCHAR,
        out_lists    OUT   SYS_REFCURSOR
    );

    PROCEDURE find_student_on_attendance (
        v_id_attendance     IN    INTEGER,
        v_identity_number   IN    VARCHAR,
        v_id                OUT   INTEGER
    );

    PROCEDURE get_student_by_id (
        v_id        IN    INTEGER,
        out_lists   OUT   SYS_REFCURSOR
    );

    PROCEDURE exist_student_on_attendance (
        v_id_student      IN    INTEGER,
        v_id_attendance   IN    INTEGER,
        v_out_id          OUT   INTEGER
    );

END pack_students;

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
            (
                SELECT
                    id_group
                FROM
                    groups
                WHERE
                    name_group = v_group_name
            )
        );

    END add_student;

    PROCEDURE get_all_students_by_group (
        v_id_group   IN    VARCHAR,
        out_lists    OUT   SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN out_lists FOR SELECT
                               id_student,
                               name_student,
                               identity_number
                           FROM
                               students
                           WHERE
                               id_group = v_id_group;

    END get_all_students_by_group;

    PROCEDURE get_student_by_id (
        v_id        IN    INTEGER,
        out_lists   OUT   SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN out_lists FOR SELECT
                               id_student,
                               name_student,
                               identity_number,
                               id_group
                           FROM
                               students
                           WHERE
                               id_student = v_id;

    END get_student_by_id;

    PROCEDURE find_student_on_attendance (
        v_id_attendance     IN    INTEGER,
        v_identity_number   IN    VARCHAR,
        v_id                OUT   INTEGER
    ) IS
    BEGIN
        SELECT
            id
        INTO v_id
        FROM
            attends_students
        WHERE
            id_student = (
                SELECT
                    id_student
                FROM
                    students
                WHERE
                    identity_number = v_identity_number
            )
            AND id_attendace = (
                SELECT
                    id_attendace
                FROM
                    attendances
                WHERE
                    id_attendance_list = v_id_attendance
            );

    EXCEPTION
        WHEN no_data_found THEN
            v_id := 0;
    END find_student_on_attendance;

    PROCEDURE exist_student_on_attendance (
        v_id_student      IN   INTEGER,
        v_id_attendance   IN   INTEGER,
        v_out_id          out   INTEGER
    ) IS
    BEGIN
        SELECT
            id
        INTO v_out_id
        FROM
            attends_students
        WHERE
            id_student = v_id_student
            AND id_attendace = v_id_attendance;
            
             EXCEPTION
        WHEN no_data_found THEN
            v_out_id := 0;

    END exist_student_on_attendance;

END pack_students;

create or replace PACKAGE pack_teachers IS
    PROCEDURE add_teacher (
        v_name IN VARCHAR
    );

END pack_teachers;

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