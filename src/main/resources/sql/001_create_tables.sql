CREATE TABLE IF NOT EXISTS quotes
(
    id VARCHAR(120) PRIMARY KEY,
    text TEXT NOT NULL,
    author VARCHAR(120),
    date DATE NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS mood_entries
(
    id VARCHAR(120) PRIMARY KEY,
    mood VARCHAR(120) NOT NULL
        CHECK (mood IN('CALM','HAPPY','TIRED','ANXIOUS','STRESSED','SAD','PEACEFUL','NEUTRAL')),
    note TEXT,
    time_stamp TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS journal_entries
(
    id VARCHAR(120) PRIMARY KEY,
    content TEXT NOT NULL,
    time_stamp TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS focus_sessions
(
    id         VARCHAR(120) PRIMARY KEY,
    duration INTERVAL NOT NULL CHECK (duration > INTERVAL '0 seconds'),
    started_at TIMESTAMP NOT NULL DEFAULT now(),
    completed BOOLEAN NOT NULL DEFAULT false
);

CREATE INDEX IF NOT EXISTS idx_mood_entry_time_stamp ON mood_entries(time_stamp);

CREATE INDEX IF NOT EXISTS idx_journal_entry_time_stamp ON journal_entries(time_stamp);