
CREATE SEQUENCE IF NOT EXISTS users_id_seq;
CREATE TABLE IF NOT EXISTS users
(
    id       VARCHAR(120) PRIMARY KEY DEFAULT ('H' || LPAD(nextval('users_id_seq')::text, 5, '0')),
    user_name VARCHAR(120) NOT NULL,
    age      INT NOT NULL CHECK (age BETWEEN 15 AND 90),
    gender   VARCHAR(120) NOT NULL CHECK (gender IN ('MALE','FEMALE'))
    );

CREATE SEQUENCE IF NOT EXISTS quotes_id_seq;
CREATE TABLE IF NOT EXISTS quotes
(
    id     VARCHAR(120) PRIMARY KEY DEFAULT ('Q' || LPAD(nextval('quotes_id_seq')::text, 5, '0')),
    text   TEXT NOT NULL,
    author VARCHAR(120),
    date   DATE NOT NULL UNIQUE
    );

CREATE SEQUENCE IF NOT EXISTS mood_entries_id_seq;
CREATE TABLE IF NOT EXISTS mood_entries
(
    id         VARCHAR(120) PRIMARY KEY DEFAULT ('M' || LPAD(nextval('mood_entries_id_seq')::text, 5, '0')),
    user_id    VARCHAR(120) NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    mood       VARCHAR(120) NOT NULL
    CHECK (mood IN ('CALM','HAPPY','TIRED','ANXIOUS','STRESSED','SAD','PEACEFUL','NEUTRAL')),
    note       TEXT,
    time_stamp TIMESTAMP NOT NULL DEFAULT now()
    );

CREATE SEQUENCE IF NOT EXISTS journal_entries_id_seq;
CREATE TABLE IF NOT EXISTS journal_entries
(
    id         VARCHAR(120) PRIMARY KEY DEFAULT ('J' || LPAD(nextval('journal_entries_id_seq')::text, 5, '0')),
    user_id    VARCHAR(120) NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    content    TEXT NOT NULL,
    time_stamp TIMESTAMP NOT NULL DEFAULT now()
    );

CREATE SEQUENCE IF NOT EXISTS focus_sessions_id_seq;
CREATE TABLE IF NOT EXISTS focus_sessions
(
    id         VARCHAR(120) PRIMARY KEY DEFAULT ('F' || LPAD(nextval('focus_sessions_id_seq')::text, 5, '0')),
    user_id    VARCHAR(120) NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    duration   INTERVAL NOT NULL DEFAULT INTERVAL '10 minutes' CHECK (duration > INTERVAL '0 seconds'),
    started_at TIMESTAMP NOT NULL DEFAULT now(),
    completed  BOOLEAN NOT NULL DEFAULT false
    );

CREATE INDEX IF NOT EXISTS idx_mood_entry_time_stamp    ON mood_entries (time_stamp);
CREATE INDEX IF NOT EXISTS idx_journal_entry_time_stamp ON journal_entries (time_stamp);