--DML script to create test data

INSERT INTO users values
('testuserid1', 'testusername1', 'testuserpw1'),
('testuserid2', 'testusername2', 'testuserpw2'),
('testuserid3', 'testusername3', 'testuserpw3');

--test data: user1 has 0 posts, user 2 has 1 post, user 3 has 3 posts
insert into threads values
('testthreadid1', 'testtitle1', 'testuserid2'),
('testthreadid2', 'testtitle2', 'testuserid3'),
('testthreadid3', 'testtitle3', 'testuserid3'),
('testthreadid4', 'testtitle4', 'testuserid3');

--test data: user 1 has 1 comment.  user 2 has 2 comments.  user 3 has 3 comments
--thread 1 has no comments, thread 2 has 1 comment. thread 3 has 2 comments, thread 4 has 3 comments
insert into comments values
('testcommentid1', 'testthreadid2', 'testuserid1'),
('testcommentid2', 'testthreadid3', 'testuserid2'),
('testcommentid3', 'testthreadid3', 'testuserid3'),
('testcommentid4', 'testthreadid4', 'testuserid3'),
('testcommentid5', 'testthreadid4', 'testuserid2'),
('testcommentid6', 'testthreadid4', 'testuserid3');


