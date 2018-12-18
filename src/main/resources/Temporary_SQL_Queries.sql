DROP TABLE IF EXISTS semester;

CREATE TABLE semester
(
   id        INT   NOT NULL AUTO_INCREMENT,
   semester  INT,
   PRIMARY KEY (id)
)
ENGINE=InnoDB;


DROP TABLE IF EXISTS batch;

CREATE TABLE batch
(
   id     INT    NOT NULL AUTO_INCREMENT,
   batch    VARCHAR(20)  ,
   PRIMARY KEY (id)
)
ENGINE=InnoDB;


DROP TABLE IF EXISTS subject;

CREATE TABLE subject
(
   sub_id     INT            NOT NULL AUTO_INCREMENT,
   active     VARCHAR(255),
   sub_code   VARCHAR(255),
   sub_name   VARCHAR(255),
   sub_title  VARCHAR(255),
   PRIMARY KEY (sub_id)
)
ENGINE=InnoDB;


DROP TABLE IF EXISTS course;

CREATE TABLE course
(
   id           INT            NOT NULL AUTO_INCREMENT,
   course_name  VARCHAR(255),
   total_semester     INT,
   PRIMARY KEY (id)
)
ENGINE=InnoDB;

DROP TABLE if exists course_fee;

CREATE TABLE course_fee
(
   id       INT    NOT NULL AUTO_INCREMENT   primary key,
   fee_amount DOUBLE default 0,
   course_id INT   NOT NULL ,
   batch_id INT   NOT NULL ,
   semester_id INT  NOT NULL
)
ENGINE=InnoDB;

ALTER TABLE course_fee ADD CONSTRAINT fk_cf_batch FOREIGN KEY (batch_id) REFERENCES batch (id) ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE course_fee ADD CONSTRAINT fk_cf_course  FOREIGN KEY (course_id ) REFERENCES course (id) ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE course_fee ADD CONSTRAINT fk_cf_semester  FOREIGN KEY (semester_id ) REFERENCES semester (id) ON UPDATE CASCADE ON DELETE  NO ACTION;
ALTER TABLE course_fee ADD CONSTRAINT uk_cf_fee UNIQUE (course_id,batch_id,semester_id);


DROP TABLE if exists course_subject;

CREATE TABLE course_subject
(
   id  INT    NOT NULL AUTO_INCREMENT   primary key,
   course_id INT   NOT NULL ,
   batch_id INT   NOT NULL ,
   semester_id INT  NOT NULL,
   subject_id INT NOT NULL
)
ENGINE=InnoDB;

ALTER TABLE course_subject ADD CONSTRAINT fk_cssm_batch FOREIGN KEY (batch_id) REFERENCES batch (id) ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE course_subject ADD CONSTRAINT fk_cssm_course  FOREIGN KEY (course_id ) REFERENCES course (id) ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE course_subject ADD CONSTRAINT fk_cssm_semester  FOREIGN KEY (semester_id ) REFERENCES semester (id) ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE course_subject ADD CONSTRAINT fk_cssm_subject  FOREIGN KEY (subject_id ) REFERENCES subject (sub_id) ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE course_subject ADD CONSTRAINT uk_cs_bcss UNIQUE (course_id,batch_id,semester_id,subject_id);

--TRUNCATE TABLE semester;
INSERT INTO semester(semester) VALUES(1);
INSERT INTO semester(semester) VALUES(2);
INSERT INTO semester(semester) VALUES(3);
INSERT INTO semester(semester) VALUES(4);
INSERT INTO semester(semester) VALUES(5);
INSERT INTO semester(semester) VALUES(6);
INSERT INTO semester(semester) VALUES(7);
INSERT INTO semester(semester) VALUES(8);