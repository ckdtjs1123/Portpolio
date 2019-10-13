-- 테이블 삭제
drop table useraccount;
drop table usermemo;
drop sequence memoseq;

--메모일련번호
create sequence memoseq;

--유저정보
create table useraccount (
	name varchar2(20) not null,			-- 회원이름 문자열 null존재불가
	id varchar2(20) primary key,		-- 아이디 null존재불가 
	password varchar2(20) not null		-- 비밀번호  null존재불가 
);

--메모정보
create table usermemo (
	memoseq varchar2(60) primary key,
    title varchar2(60) not null, 	--메모 제목
    content varchar2(2000) not null,--메모내용
    indate date default sysdate,    --메모작성일
    id varchar2(20),				--작성자
	constraint id_fk 
	foreign key (id) 
	references useraccount(id)	
);
-- 책 정보
create table book (
	bNum varchar2(60) primary key,
	bName varchar2(60) not null,
	writer varchar2(200),
	type varchar2(200),
	state varchar2(200)
);

drop table book;
-- 리뷰 정보
create table review (
	rid varchar2(20) ,
	bName varchar2(60),
	rNum varchar2(60),
	reviews varchar2(511),
	review_date date default sysdate,
	constraint rid_fk foreign key (rid) references useraccount(id),
	constraint rNum_fk foreign key (rNum) references book(bNum)
	);

drop table review;
select * from review;
-- 커밋
commit;
select * from USERACCOUNT;
select * from USERMEMO;
delete useraccount;
delete usermemo;
select * from USERACCOUNT;
--	private String bNum;
--	private String writer;
--	private String content;
--	private String indate;
select * from book;
update book set review(writer,rNum,reviews) = ('','1','') where bnum = '1';
select * from review;

select * from book b, REVIEW r,useraccount u where b.bNum = r.rNum and r.rid = u.id;

INSERT INTO BOOK(bnum,bname,writer,type,state) VALUES((SELECT NVL(MAX(BNUM),0)+1 FROM BOOK),'B','B','3','3');