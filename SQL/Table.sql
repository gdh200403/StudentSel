create database if not exists CourseSelSys character set utf8mb4 collate utf8mb4_bin;
USE `CourseSelSys`;
# TODO: 1. 当学生入学后，大学一般已经为学生准备了四年的学习计划，即为了满足学历、学位的要求而应完成的课程（Course）
# 2.	在每学期期末的选课（Class）时间，学生都能通过选课系统查看下学期所开设课程，并选择和注册自己应该或有兴趣学习的课程，学生也可按学分查询、按课程性质查询等多种方式查询课程信息。系统应提供选课帮助，如已经选择的总学分等。
# 3.	如果多个老师讲授这门课程，学生还可以选择自己喜欢的老师的课程
# 4.	如果有空余的选课名额，那么学生就选课成功，考试结束后可查看该课程的成绩
# 5.	选课成功则通过付费系统支付学费
# 6.	学生在正式上课前也可取消课程
# 7.	任课教师可以查看选课学生的名单，可以给学生成绩或查看学生的成绩
# 8.	管理员可以录入课程信息


DROP TABLE IF EXISTS `TC`;
DROP TABLE IF EXISTS `SC`;
DROP TABLE IF EXISTS `Course`;
DROP TABLE IF EXISTS `Admin`;
DROP TABLE IF EXISTS `Student`;
DROP TABLE IF EXISTS `Teacher`;
DROP TABLE IF EXISTS `CoursePlan`;

CREATE TABLE CoursePlan (
    major varchar(20) NOT NULL,
    plan_id varchar(20) NOT NULL,
    PRIMARY KEY (Major)
);

CREATE TABLE Admin (
  id varchar(20) NOT NULL,
  pwd varchar(20) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE Student (
    student_id varchar(20) NOT NULL,
    student_name varchar(20) NOT NULL,
    sex varchar(10) NOT NULL,
    age int(11) NOT NULL,
    major varchar(20) NOT NULL,
    pwd varchar(20) NOT NULL,
    PRIMARY KEY (student_id),
    FOREIGN KEY (major) REFERENCES CoursePlan(major)
);

CREATE TABLE Teacher (
    teacher_id varchar(20) NOT NULL,
    teacher_name varchar(20) NOT NULL,
    sex varchar(10) NOT NULL,
    age int(11) NOT NULL,
    pwd varchar(20) NOT NULL,
    PRIMARY KEY (teacher_id)
);



CREATE TABLE Course (
    course_id varchar(20) NOT NULL,
    course_name varchar(20) NOT NULL,
    type varchar(20) NOT NULL,
    credit int(11) default 0,
    total_hours int(11) default 0,
    teaching_hours int(11) default 0,
    experiment_hours int(11) default 0,
    PRIMARY KEY (course_id)
);

CREATE TABLE TC (
    teacher_id varchar(20) NOT NULL,
    course_id varchar(20) NOT NULL,
    current int default 0,
    Capacity int default 0,
    term varchar(20) NOT NULL,
    place varchar(20) NOT NULL,
    Comment varchar(100) default NULL,
    PRIMARY KEY (teacher_id, course_id, term),
    FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id),
    FOREIGN KEY (course_id) REFERENCES Course(course_id)
);

CREATE TABLE SC (
    student_id varchar(20) NOT NULL,
    course_id varchar(20) NOT NULL,
    teacher_id varchar(20) NOT NULL,
    Grade int(11) default NULL,
    term varchar(20) NOT NULL,
    PRIMARY KEY (student_id, course_id, teacher_id, term),
    FOREIGN KEY (student_id) REFERENCES Student(student_id),
    FOREIGN KEY (course_id) REFERENCES Course(course_id),
    FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id)
);

drop view if exists TCView;
drop view if exists SCView;

create view TCView as select teacher.teacher_id,teacher_name,course.course_id,course_name,type,credit,total_hours,teaching_hours,experiment_hours,term,place,current,Capacity,Comment
                      from TC inner join Course on TC.course_id=Course.course_id inner join Teacher on TC.teacher_id=Teacher.teacher_id;

create view SCView as select SC.student_id, student_name, SC.course_id, course_name, Teacher.teacher_id, teacher.teacher_name, type, credit, total_hours, teaching_hours, experiment_hours, tc.term, place, Grade, current, Capacity, Comment
                      from sc, tc, Student, Course, Teacher
                      where sc.course_id = tc.course_id and sc.student_id = Student.student_id and sc.course_id = Course.course_id and tc.teacher_id = Teacher.teacher_id and sc.term = tc.term;

# 删除课程记录事务
drop procedure if exists deleteCourse;
delimiter //
create procedure deleteCourse(in course_id varchar(20))
begin
    #define course_name
#     set @course_name=(select course_name from Course where Course.course_id=course_id);
#     call deleteCourseByName(@course_name);
    delete from TC where tc.course_id=course_id;
    delete from SC where sc.course_id=course_id;
    delete from Course where course.course_id=course_id;
end //
delimiter ;

# 删除所遇课程名的课程记录事务
drop procedure if exists deleteCourseByName;
delimiter //
create procedure deleteCourseByName(in course_name varchar(20))
begin
    delete from TC where tc.course_id in (select course_id from Course where course.course_name=course_name);
    delete from SC where sc.course_id in (select course_id from Course where course.course_name=course_name);
    delete from Course where course.course_name=course_name;
end //

# 删除教师记录事务
drop procedure if exists deleteTeacher;
delimiter //
create procedure deleteTeacher(in teacher_id varchar(20))
begin
    delete from TC where tc.teacher_id=teacher_id;
    delete from Teacher where teacher.teacher_id=teacher_id;
end //
delimiter ;

# 删除学生记录事务
drop procedure if exists deleteStudent;
delimiter //
create procedure deleteStudent(in student_id varchar(20))
begin
    delete from SC where sc.student_id=student_id;
    delete from Student where student.student_id=student_id;
end //
delimiter ;



insert into Admin values('admin','admin');

insert into CoursePlan values('计算机科学与技术','计算机科学与技术 2024-2027');
insert into CoursePlan values('软件工程','软件工程 2024-2027');
insert into CoursePlan values('网络工程','网络工程 2024-2027');


insert into Student values('PB42000001','张百嘉','男',20,'计算机科学与技术','123456');
insert into Student values('PB42000002','李玲','女',20,'软件工程','123456');
insert into Student values('PB42000003','王嘉','男',20,'计算机科学与技术','123456');
insert into Student values('PB42000004','郑永波','男',20,'网络工程','123456');
insert into Student values('PB42000005','朱丽','女',20,'软件工程','123456');
insert into Student values('PB42000006','周潞潞', '女',20,'软件工程','123456');

insert into Teacher values('42000007','李明','男',30,'123456');
insert into Teacher values('42000008','王丽','女',30,'123456');
insert into Teacher values('42000009','张丽','女',30,'123456');
insert into Teacher values('42000010','李丽','女',30,'123456');
insert into Teacher values('42000011','王明','男',30,'123456');
insert into Teacher values('42000012','张明','男',30,'123456');

# generate 10 courses data with random data and different course name
insert into Course values('cs1','计算机科学与技术','必修',4,64,32,32);
insert into Course values('phy2','物理','必修',5,64,32,32);
insert into Course values('math3','数学','必修',2,64,32,32);
insert into Course values('phi4','哲学','必修',2,64,32,32);
insert into Course values('bio5','生物','必修',4,64,32,32);
insert into Course values('En6','英语','必修',4,64,32,32);
insert into Course values('his7','历史学','必修',3,64,32,32);

insert into TC values('42000007','cs1','10','100','sp21','A-101', NULL);


insert into SC values('PB42000001','cs1', '42000007',69,'sp21');