insert into JOB_OPENINGS(JOB_OPENING_ID, AVAILABLE_VACANCIES, CREATED_ON, EMPLOYMENT_TYPE, TITLE, LOCATION, MIN_EXPERIENCE_IN_YEARS, MAX_EXPERIENCE_IN_YEARS, MIN_OFFERINGCTC, MAX_OFFERINGCTC, JOB_DESCRIPTION, TOTAL_VACANCIES, VALID_FROM, VALID_TILL)
values(1, 2, sysdate-4, 'fulltime', 'Full Stack Java Tech Lead', 'Pune', '9', '15', '20', '25', 'Full Stack Java Tech Lead telecom domain....', 2, sysdate-1, sysdate+10);

insert into JOB_OPENINGS(JOB_OPENING_ID, AVAILABLE_VACANCIES, CREATED_ON, EMPLOYMENT_TYPE, TITLE, LOCATION, MIN_EXPERIENCE_IN_YEARS, MAX_EXPERIENCE_IN_YEARS, MIN_OFFERINGCTC, MAX_OFFERINGCTC, JOB_DESCRIPTION, TOTAL_VACANCIES, VALID_FROM, VALID_TILL)
values(2, 1, sysdate, 'fulltime', 'IT Manager', 'Pune', null, '15', null, 30, 'Operations Management at investment banking', null, sysdate+10, sysdate+20);

insert into JOB_OPENINGS_SKILLS(JOB_SKILL_ID, IS_MANDATORY, SKILL_DESCRIPTION, JOB_OPENING_ID)
values(1, true, 'Spring boot', 1);

insert into JOB_OPENINGS_SKILLS(JOB_SKILL_ID, IS_MANDATORY, SKILL_DESCRIPTION, JOB_OPENING_ID)
values(2, true, 'Jenkins CI, CD', 1);

insert into JOB_OPENINGS_SKILLS(JOB_SKILL_ID, IS_MANDATORY, SKILL_DESCRIPTION, JOB_OPENING_ID)
values(3, true, 'React or Angular', 2);

insert into JOB_OPENINGS_SKILLS(JOB_SKILL_ID, IS_MANDATORY, SKILL_DESCRIPTION, JOB_OPENING_ID)
values(4, true, 'Spring, Apache kafka', 2);

insert into JOB_OPENINGS_SKILLS(JOB_SKILL_ID, IS_MANDATORY, SKILL_DESCRIPTION, JOB_OPENING_ID)
values(5, true, 'Jenkins CI, CD, docker', 2);

insert into users(ID, EMAIL, EMAIL_VERIFIED, IMAGE_URL, NAME, PASSWORD, PROVIDER, PROVIDER_ID)
values (1, 'admin@gmail.com', FALSE, null, 'admin', '$2a$10$E.O/txwh6YHiZ.vuqaifBe6cnM7ecpeq59Y/inuiFgi4PhvFwieZm', 'local', null);
