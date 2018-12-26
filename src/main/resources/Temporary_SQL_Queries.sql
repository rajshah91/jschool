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
   batch_id INT   NOT NULL 
)
ENGINE=InnoDB;

ALTER TABLE course_fee ADD CONSTRAINT fk_cf_batch FOREIGN KEY (batch_id) REFERENCES batch (id) ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE course_fee ADD CONSTRAINT fk_cf_course  FOREIGN KEY (course_id ) REFERENCES course (id) ON UPDATE CASCADE ON DELETE NO ACTION;
-- ALTER TABLE course_fee ADD CONSTRAINT fk_cf_semester  FOREIGN KEY (semester_id ) REFERENCES semester (id) ON UPDATE CASCADE ON DELETE  NO ACTION;
ALTER TABLE course_fee ADD CONSTRAINT uk_cf_fee UNIQUE (course_id,batch_id);


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

------------------------------------------------------------------------------------------------------------------------------
DROP TABLE if exists student;

CREATE TABLE student 
(
  id                       INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  enrollment_number        VARCHAR(20) NOT NULL,
  course_id                INT NOT NULL,
  batch_id                 INT NOT NULL,
--   semester_id              INT NOT NULL,
  first_name               VARCHAR(50) NOT NULL,
  middle_name              VARCHAR(50),
  last_name                VARCHAR(50) NOT NULL,
  gender                   VARCHAR(20) NOT NULL,
  qualification            VARCHAR(50) NOT NULL,
  birth_date               DATE,
  enrollment_date          DATE DEFAULT NULL,
  address_line1            VARCHAR(1000),
  city                     VARCHAR(50),
  state                    VARCHAR(50),
  country                  VARCHAR(50),
  pincode                  VARCHAR(10),
  mobile_number            VARCHAR(15),
  email_id                 VARCHAR(50),
  guardian_full_name       VARCHAR(50),
  guardian_full_address    VARCHAR(1000),
  guardian_mobile_number   VARCHAR(15),
  blood_group              VARCHAR(20),
  disability               VARCHAR(10),
  disability_detail        VARCHAR(500),
  discount                 DOUBLE DEFAULT 0,
  data_create_time         DATETIME  DEFAULT now(),
  data_update_time         TIMESTAMP ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
)
ENGINE = InnoDB;


ALTER TABLE student ADD CONSTRAINT fk_stud_batch FOREIGN KEY (batch_id) REFERENCES batch (id) ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE student ADD CONSTRAINT fk_stud_course FOREIGN KEY (course_id) REFERENCES course (id) ON UPDATE CASCADE ON DELETE NO ACTION;
-- ALTER TABLE student ADD CONSTRAINT fk_stud_semester FOREIGN KEY (semester_id) REFERENCES semester (id) ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE student ADD CONSTRAINT uk_stud_mob UNIQUE (mobile_number);
ALTER TABLE student ADD CONSTRAINT uk_stud_email UNIQUE (email_id);


--------------------------------------------------------------------------------------------------------------------------

DROP TABLE if exists student_fee;

CREATE TABLE student_fee 
(
  id                       INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  student_id               INT NOT NULL,
  course_id                INT NOT NULL,
  batch_id                 INT NOT NULL,
--   semester_id              INT NOT NULL,
--   discount                 DOUBLE DEFAULT 0,
  amount_paid              DOUBLE,
  payment_mode             VARCHAR(10),
  cheque_number            VARCHAR(50),
  fee_collected_by         VARCHAR(50) DEFAULT NULL,
  data_create_time         DATETIME  DEFAULT now(),
  data_update_time         TIMESTAMP ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP  
)
ENGINE = InnoDB;


ALTER TABLE student_fee ADD CONSTRAINT fk_studfee_student FOREIGN KEY (student_id) REFERENCES student (id) ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE student_fee ADD CONSTRAINT fk_studfee_batch FOREIGN KEY (batch_id) REFERENCES batch (id) ON UPDATE CASCADE ON DELETE NO ACTION;
ALTER TABLE student_fee ADD CONSTRAINT fk_studfee_course FOREIGN KEY (course_id) REFERENCES course (id) ON UPDATE CASCADE ON DELETE NO ACTION;
-- ALTER TABLE student_fee ADD CONSTRAINT fk_studfee_semester FOREIGN KEY (semester_id) REFERENCES semester (id) ON UPDATE CASCADE ON DELETE NO ACTION;