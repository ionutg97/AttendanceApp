EXECUTE pack_groups.add_group('1408A');

select pack_groups.get_attractiontype() from dual;

EXECUTE pack_students.add_student('Galan Ionut', '6543847', 100);
EXECUTE pack_students.add_student('Arteni Denisia', '5432332', 100);
EXECUTE pack_students.add_student('Vrabie Vasile', '1243847', 100);
EXECUTE pack_students.add_student('Gorceag Dan', '323847', 100);
EXECUTE pack_students.add_student('Gabriel Matei', '1143847', 100);
EXECUTE pack_students.add_student('Porusniuc Iuliana', '977231', 100);

execute pack_teachers.add_teacher('Alexandrescu');
execute pack_teachers.add_teacher('Mironeanu');

execute pack_attendance_lists.add_attendance_list('prezenta saptamana 4 grupa 1410A','laborator');
execute pack_attendance_lists.add_attendance_list('prezenta saptamana 5 grupa 1410A','curs');

execute pack_classrooms.add_classroom('C03');
execute pack_classrooms.add_classroom('AC2-2');

execute pack_attendances.add_attendance(300,203,30000);
execute pack_attendances.add_attendance(300,203,30001);

EXECUTE  pack_attendances_students.add_attendances_student(2000,1001,7,'bonus 1,2');
EXECUTE  pack_attendances_students.add_attendances_student(2000,1002,10,'-----');
EXECUTE  pack_attendances_students.add_attendances_student(2000,1003,3,'bonus 1,2');
EXECUTE  pack_attendances_students.add_attendances_student(2000,1004,7,'bonus 1,2');

variable outvar varchar2;
SELECT
            name_group
        INTO &outvar
        FROM
            groups
        WHERE
            id_group = 109;
print outvar;

SELECT * FROM groups;
    
select * from students;

select * from teachers;

select * from attendance_lists;

select * from classrooms;

select * from attends_students;

select * from attendances;

select * from groups_attend_lists;

delete attends_students where id=10009;


