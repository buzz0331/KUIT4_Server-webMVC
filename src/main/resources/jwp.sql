DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS (
                       userId          varchar(12)		NOT NULL,
                       password		varchar(12)		NOT NULL,
                       name			varchar(20)		NOT NULL,
                       email			varchar(50),

                       PRIMARY KEY               (userId)
);

INSERT INTO USERS VALUES('admin', 'password', '이영선', 'admin@naver.com');

DROP TABLE IF EXISTS QUESTIONS;

CREATE TABLE QUESTIONS (
                           questionId 			bigint				auto_increment,
                           writer				varchar(30)			NOT NULL,
                           title				varchar(50)			NOT NULL,
                           contents			varchar(5000)		NOT NULL,
                           createdDate			timestamp			NOT NULL,
                           countOfAnswer int,
                           PRIMARY KEY               (questionId)
);

DROP TABLE IF EXISTS ANSWERS;

CREATE TABLE ANSWERS (
                         answerId 			bigint				auto_increment,
                         writer				varchar(30)			NOT NULL,
                         contents			varchar(5000)		NOT NULL,
                         createdDate			timestamp			NOT NULL,
                         questionId			bigint				NOT NULL,
                         PRIMARY KEY         (answerId)
);

INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES
    ('이영선',
     '쿠잇 4기 여러분 환영합니다!',
     '한 학기 동안 유익한 시간이 되셨으면 좋겠습니다!\n 모두 화이팅!!!',
     CURRENT_TIMESTAMP(), 1);

INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES
    ('함형주',
     '저는 회식이 너무 좋아요',
     '회식을 하면 저를 꼭 불러주세요',
     CURRENT_TIMESTAMP(), 1);

INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES
    ('이윤정',
     '저는 객체지향 마스터에요!',
     '오브젝트란 책이 저의 최애 책이랍니다~^^ \n 여러분의 최애 책은 무엇인가요?',
     CURRENT_TIMESTAMP(), 1);


INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES
    ('조하상',
     '저는 진행 마스터입니다!',
     '엠티 때 제가 진행할 예정이니 많이 기대해주세요! \n 엠티도 많관부~',
     CURRENT_TIMESTAMP(), 1);


INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES
    ('김지환',
     '저는 깃 마스터랍니다~ 하핫',
     CURRENT_TIMESTAMP(), 2);

INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES
    ('김민우',
     '바쁘다 바빠 현대사회',
     CURRENT_TIMESTAMP(), 3);

INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES
    ('지호준',
     '나는야 풀스택 개발자 *_*',
     CURRENT_TIMESTAMP(), 1);

INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES
    ('박성현',
     '저는 막내지만 팀장 맡는 것을 좋아한답니다. 팀장은 모두 잘생긴 제게 맡겨주세요^^~~!!',
     CURRENT_TIMESTAMP(), 4);