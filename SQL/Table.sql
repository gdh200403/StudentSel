create database if not exists CourseSelSys character set utf8mb4 collate utf8mb4_bin;

USE `CourseSelSys`;
# --TODO: 1.	当学生入学后，大学一般已经为学生准备了四年的学习计划，即为了满足学历、学位的要求而应完成的课程（Course）
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
    Major varchar(20) NOT NULL,
    PlanID varchar(20) NOT NULL,
    PRIMARY KEY (Major)
);

CREATE TABLE Admin (
  ID varchar(20) NOT NULL,
  Pwd varchar(20) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE Student (
    ID varchar(20) NOT NULL,
    Name varchar(20) NOT NULL,
    Sex varchar(10) NOT NULL,
    Age int(11) NOT NULL,
    Major varchar(20) NOT NULL,
    Pwd varchar(20) NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (Major) REFERENCES CoursePlan(Major)
);

CREATE TABLE Teacher (
    ID varchar(20) NOT NULL,
    Name varchar(20) NOT NULL,
    Sex varchar(10) NOT NULL,
    Age int(11) NOT NULL,
    Pwd varchar(20) NOT NULL,
    PRIMARY KEY (ID)
);



CREATE TABLE Course (
    ID varchar(20) NOT NULL,
    Name varchar(20) NOT NULL,
    Credit int(11) NOT NULL,
    Type varchar(20) NOT NULL,
    Teacher varchar(20) NOT NULL,
    Time varchar(20) NOT NULL,
    Place varchar(20) NOT NULL,
    Capacity int(11) NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (Teacher) REFERENCES Teacher(ID)
);

CREATE TABLE TC (
    ID varchar(20) NOT NULL,
    CourseID varchar(20) NOT NULL,
    Capacity int NOT NULL,
    PRIMARY KEY (ID,CourseID),
    FOREIGN KEY (ID) REFERENCES Teacher(ID),
    FOREIGN KEY (CourseID) REFERENCES Course(ID)
);

CREATE TABLE SC (
    ID varchar(20) NOT NULL,
    CourseID varchar(20) NOT NULL,
    Grade int(11) default NULL,
    PRIMARY KEY (ID,CourseID),
    FOREIGN KEY (ID) REFERENCES Student(ID),
    FOREIGN KEY (CourseID) REFERENCES Course(ID)
);

insert into Admin values('admin','admin');

insert into CoursePlan values('计算机科学与技术','计算机科学与技术2024-2027');
insert into CoursePlan values('软件工程','软件工程2024-2027');
insert into CoursePlan values('网络工程','网络工程2024-2027');
insert into CoursePlan values('信息安全','信息安全2024-2027');
insert into CoursePlan values('数学','数学2024-2027');
insert into CoursePlan values('物理','物理2024-2027');
insert into CoursePlan values('化学','化学2024-2027');
insert into CoursePlan values('生物','生物2024-2027');
insert into CoursePlan values('英语','英语2024-2027');
insert into CoursePlan values('法学','法学2024-2027');
insert into CoursePlan values('经济学','经济学2024-2027');
insert into CoursePlan values('管理学','管理学2024-2027');
insert into CoursePlan values('哲学','哲学2024-2027');
insert into CoursePlan values('历史学','历史学2024-2027');

insert into Student values('PB42000001','张百嘉','男',20,'计算机科学与技术','123456');
insert into Student values('PB42000002','李玲','女',20,'软件工程','123456');
insert into Student values('PB42000003','王嘉','男',20,'计算机科学与技术','123456');
insert into Student values('PB42000004','郑永波','男',20,'网络工程','123456');
insert into Student values('PB42000005','朱丽','女',20,'数学','123456');
insert into Student values('PB42000006','周潞潞', '女',20,'管理学','123456');

insert into Teacher values('PB42000007','李明','男',30,'123456');
insert into Teacher values('PB42000008','王丽','女',30,'123456');
insert into Teacher values('PB42000009','张丽','女',30,'123456');
insert into Teacher values('PB42000010','李丽','女',30,'123456');
insert into Teacher values('PB42000011','王明','男',30,'123456');
insert into Teacher values('PB42000012','张明','男',30,'123456');


insert into Course values('CS001','计算机网络',4,'必修','PB42000007','周一第1-2节','A101',100);
insert into Course values('CS002','计算机组成原理',4,'必修','PB42000008','周二第1-2节','A102',100);
insert into Course values('CS003','操作系统',4,'必修','PB42000009','周三第1-2节','A103',100);
insert into Course values('CS004','数据库原理',4,'必修','PB42000010','周四第1-2节','A104',100);
insert into Course values('CS005','数据结构',4,'必修','PB42000011','周五第1-2节','A105',100);