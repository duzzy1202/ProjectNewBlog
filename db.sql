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

# 게시물 테이블 생성
DROP TABLE IF EXISTS article;
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    cateItemId INT(10) UNSIGNED NOT NULL,
    displayStatus TINYINT(1) UNSIGNED NOT NULL,
    `title` CHAR(200) NOT NULL,
    `body` TEXT NOT NULL
);
INSERT INTO article SET
regDate = NOW(),
updateDate = NOW(),
cateItemId = 1,
displayStatus = 1,
title = '블로그를 시작합니다.',
`body` = '';
