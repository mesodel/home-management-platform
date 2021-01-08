CREATE TABLE family (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE user (
    email VARCHAR(30) NOT NULL,
    name VARCHAR(30) NOT NULL,
    password VARCHAR(20) NOT NULL,
    family_id BIGINT NOT NULL,

    PRIMARY KEY(email),
    FOREIGN KEY(family_id) REFERENCES family(id)
);

CREATE TABLE status (
    id BIGINT NOT NULL,
    value VARCHAR(30),

    PRIMARY KEY(id)
);

CREATE TABLE task (
    id BIGINT NOT NULL,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(100),
    status_id BIGINT NOT NULL,
    due_by datetime,

    PRIMARY KEY(id),
    FOREIGN KEY(status_id) REFERENCES status(id)
);

CREATE TABLE user_task (
    created_on datetime NOT NULL,
    user_email VARCHAR(30) NOT NULL,
    task_id BIGINT NOT NULL,

    PRIMARY KEY(created_on),
    FOREIGN KEY(user_email) REFERENCES user(email),
    FOREIGN KEY(task_id) REFERENCES task(id)
);

CREATE TABLE log_message (
    id BIGINT NOT NULL,
    timestamp datetime,
    message VARCHAR(30)
);