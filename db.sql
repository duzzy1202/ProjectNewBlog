# 캐릭터 set 설정
SET NAMES utf8mb4;

# DB생성
DROP DATABASE IF EXISTS site34;
CREATE DATABASE site34;
USE site34;

# 카테고리
DROP TABLE IF EXISTS cateItem;
CREATE TABLE cateItem (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    `name` CHAR(100) NOT NULL UNIQUE
);

# 카테고리 아이템
INSERT INTO cateItem SET regDate = NOW(), `name` = "잡담";
INSERT INTO cateItem SET regDate = NOW(), `name` = "공부/IT기타";
INSERT INTO cateItem SET regDate = NOW(), `name` = "공부/프론트엔드";
INSERT INTO cateItem SET regDate = NOW(), `name` = "공부/백엔드";
INSERT INTO cateItem SET regDate = NOW(), `name` = "공부/알고리즘";
INSERT INTO cateItem SET regDate = NOW(), `name` = "취미/게임";
INSERT INTO cateItem SET regDate = NOW(), `name` = "취미/축구";
INSERT INTO cateItem SET regDate = NOW(), `name` = "취미/유튜브";

# 게시물 테이블 생성
DROP TABLE IF EXISTS article;
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    cateItemId INT(10) UNSIGNED NOT NULL,
    displayStatus TINYINT(1) UNSIGNED NOT NULL,
    `title` CHAR(200) NOT NULL,
    `body` TEXT NOT NULL,
    hits INT NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL
);

INSERT INTO article SET
regDate = NOW(),
updateDate = NOW(),
cateItemId = 4,
displayStatus = 1,
title = '페이징 기능 테스트를 위한 게시판',
`body` = '페이징 테스트를 합니다.';

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `loginId` CHAR(100) NOT NULL UNIQUE,
    `loginPw` CHAR(255) NOT NULL,
    `name` CHAR(100) NOT NULL,
    email CHAR(200) NOT NULL,
    `nickname` CHAR(200) NOT NULL,
    `level` INT(10) UNSIGNED NOT NULL,
    mailAuthCode CHAR(255) NOT NULL,
    mailAuthStatus TINYINT(1) UNSIGNED,
    delStatus TINYINT(1) UNSIGNED
);

/* articleReply 로 이름 변경 예정 */
DROP TABLE IF EXISTS articleReply;
CREATE TABLE reply (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    articleId INT(10) UNSIGNED NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    `body` TEXT NOT NULL
);

DROP TABLE IF EXISTS chatting;
CREATE TABLE chatting (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    `body` TEXT NOT NULL
);

DROP TABLE IF EXISTS message;
CREATE TABLE message (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    fromMemberId INT(10) UNSIGNED NOT NULL,
    toMemberId INT(10) UNSIGNED NOT NULL,
    `title` CHAR(200) NOT NULL,
    `body` TEXT NOT NULL,
    readStatus TINYINT(1) UNSIGNED,
    delStatus TINYINT(1) UNSIGNED
);


SELECT *
FROM article
WHERE id = 60;



SELECT * FROM article
SELECT * FROM `member`
SELECT * FROM cateItem
SELECT * FROM articleReply
SELECT * FROM chatting
SELECT * FROM attr
SELECT * FROM message

ALTER TABLE message ADD delStatus TINYINT(1) UNSIGNED
ALTER TABLE `member` CHANGE mailAuthCod mailAuthCode CHAR(255) NOT NULL
ALTER TABLE reply MODIFY memberId INT(10) UNSIGNED NOT NULL
ALTER TABLE reply RENAME articleReply

DROP TABLE IF EXISTS attr;
CREATE TABLE attr (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `name` CHAR(100) NOT NULL UNIQUE,
    `value` TEXT NOT NULL
);

# updateDate 칼럼 추가
ALTER TABLE `cateItem` ADD COLUMN `updateDate` DATETIME NOT NULL AFTER `regDate`; 

# attr 테이블에서 name 을 4가지 칼럼으로 나누기
ALTER TABLE `attr` DROP COLUMN `name`,
ADD COLUMN `relTypeCode` CHAR(20) NOT NULL AFTER `updateDate`,
ADD COLUMN `relId` INT(10) UNSIGNED NOT NULL AFTER `relTypeCode`,
ADD COLUMN `typeCode` CHAR(30) NOT NULL AFTER `relId`,
ADD COLUMN `type2Code` CHAR(30) NOT NULL AFTER `typeCode`,
CHANGE `value` `value` TEXT CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL AFTER `type2Code`,
DROP INDEX `name`; 

# attr 유니크 인덱스 걸기
## 중복변수 생성금지
## 변수찾는 속도 최적화
ALTER TABLE `attr` ADD UNIQUE INDEX (`relTypeCode`, `relId`, `typeCode`, `type2Code`); 

## 특정 조건을 만족하는 회원 또는 게시물(기타 데이터)를 빠르게 찾기 위해서
ALTER TABLE `attr` ADD INDEX (`relTypeCode`, `typeCode`, `type2Code`); 