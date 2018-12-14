/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  raj.shah
 * Created: Dec 14, 2018
 */

DROP TABLE IF EXISTS course cascade;

CREATE TABLE course
(
   id              DOUBLE            NOT NULL AUTO_INCREMENT  primary key,
   course_name     VARCHAR(255),
   fees            DOUBLE,
   semester        INT
)
ENGINE=InnoDB;

DROP TABLE IF EXISTS subject cascade;

CREATE TABLE subject
(
   sub_id       DOUBLE            NOT NULL AUTO_INCREMENT   primary key,
   active       VARCHAR(5),
   sub_code     VARCHAR(10),
   sub_name     VARCHAR(100),
   sub_title    VARCHAR(100)
)
ENGINE=InnoDB;


DROP TABLE IF EXISTS course_subject_mapping cascade;

CREATE TABLE course_subject_mapping
(
   id       DOUBLE    NOT NULL AUTO_INCREMENT   primary key,
   course_id DOUBLE   NOT NULL ,
   subject_id DOUBLE  NOT NULL
)
ENGINE=InnoDB;

ALTER TABLE course_subject_mapping ADD CONSTRAINT fk_subject FOREIGN KEY (subject_id) REFERENCES subject (sub_id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE course_subject_mapping ADD CONSTRAINT fk_course  FOREIGN KEY (course_id ) REFERENCES course  (id) ON UPDATE CASCADE ON DELETE CASCADE;

-----------------------------------------------------------------------------------------------------------------------------------------------