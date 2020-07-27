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
    mailAuthStatus TINYINT(1) UNSIGNED
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


SELECT *
FROM article
WHERE id = 60;



SELECT * FROM article
SELECT * FROM `member`
SELECT * FROM cateItem
SELECT * FROM articleReply
SELECT * FROM chatting

ALTER TABLE `member` ADD mailAuthStatus TINYINT(1) UNSIGNED
ALTER TABLE `member` CHANGE mailAuthCod mailAuthCode CHAR(255) NOT NULL
ALTER TABLE reply MODIFY memberId INT(10) UNSIGNED NOT NULL
ALTER TABLE reply RENAME articleReply
