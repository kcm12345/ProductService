CREATE TABLE category
(
    id    BIGINT AUTO_INCREMENT NOT NULL,
    title VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE joinedtable_instructor
(
    id             BIGINT NOT NULL,
    specialisation VARCHAR(255) NULL,
    CONSTRAINT pk_joinedtable_instructor PRIMARY KEY (id)
);

CREATE TABLE joinedtable_mentor
(
    id      BIGINT NOT NULL,
    company VARCHAR(255) NULL,
    avg_rating DOUBLE NOT NULL,
    CONSTRAINT pk_joinedtable_mentor PRIMARY KEY (id)
);

CREATE TABLE joinedtable_student
(
    id     BIGINT NOT NULL,
    course VARCHAR(255) NULL,
    batch  VARCHAR(255) NULL,
    CONSTRAINT pk_joinedtable_student PRIMARY KEY (id)
);

CREATE TABLE joinedtable_user
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_joinedtable_user PRIMARY KEY (id)
);

CREATE TABLE mps_instructor
(
    id             BIGINT NOT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    specialisation VARCHAR(255) NULL,
    CONSTRAINT pk_mps_instructor PRIMARY KEY (id)
);

CREATE TABLE mps_mentor
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    company  VARCHAR(255) NULL,
    avg_rating DOUBLE NOT NULL,
    CONSTRAINT pk_mps_mentor PRIMARY KEY (id)
);

CREATE TABLE mps_user
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    course   VARCHAR(255) NULL,
    batch    VARCHAR(255) NULL,
    CONSTRAINT pk_mps_user PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    title         VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    price DOUBLE NULL,
    category_id   BIGINT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE singletable_user
(
    id             BIGINT NOT NULL,
    dtype          VARCHAR(31) NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    company        VARCHAR(255) NULL,
    avg_rating DOUBLE NOT NULL,
    specialisation VARCHAR(255) NULL,
    course         VARCHAR(255) NULL,
    batch          VARCHAR(255) NULL,
    CONSTRAINT pk_singletable_user PRIMARY KEY (id)
);

CREATE TABLE tpc_instructor
(
    id             BIGINT NOT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    specialisation VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_instructor PRIMARY KEY (id)
);

CREATE TABLE tpc_mentor
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    company  VARCHAR(255) NULL,
    avg_rating DOUBLE NOT NULL,
    CONSTRAINT pk_tpc_mentor PRIMARY KEY (id)
);

CREATE TABLE tpc_student
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    course   VARCHAR(255) NULL,
    batch    VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_student PRIMARY KEY (id)
);

CREATE TABLE tpc_user
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_user PRIMARY KEY (id)
);

ALTER TABLE joinedtable_instructor
    ADD CONSTRAINT FK_JOINEDTABLE_INSTRUCTOR_ON_ID FOREIGN KEY (id) REFERENCES joinedtable_user (id);

ALTER TABLE joinedtable_mentor
    ADD CONSTRAINT FK_JOINEDTABLE_MENTOR_ON_ID FOREIGN KEY (id) REFERENCES joinedtable_user (id);

ALTER TABLE joinedtable_student
    ADD CONSTRAINT FK_JOINEDTABLE_STUDENT_ON_ID FOREIGN KEY (id) REFERENCES joinedtable_user (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);