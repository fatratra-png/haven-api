CREATE TABLE IF NOT EXISTS quotes
(
    id VARCHAR(120) PRIMARY KEY,
    text TEXT NOT NULL,
    author VARCHAR(120),
    date DATE NOT NULL UNIQUE,
);

CREATE TABLE IF NOT EXISTS mood_entries
(
    id VARCHAR(120) PRIMARY KEY,
    mood VARCHAR(120) NOT NULL
        CHECK (mood IN('CALM','HAPPY','TIRED','ANXIOUS','STRESSED','SAD','PEACEFUL','NEUTRAL')),
    note TEXT,
    timestamp NOT NULL TIMESTAMP DEFAULT now()
);

CREATE TABLE IF NOT EXISTS journal_entries
(
    id VARCHAR(120) PRIMARY KEY,
    content TEXT NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE focus_sessions
(
    id         VARCHAR(120) PRIMARY KEY,
    duration INTERVAL NOT NULL CHECK (duration > 0 ),
    started_at TIMESTAMP NOT NULL DEFAULT now(),
    completed BOOLEAN NOT NULL DEFAULT false
);

CREATE INDEX idx_mood_entry_timestamp ON mood_entries(timestamp);

CREATE INDEX idx_journal_entry_timestamp ON journal_entries(timestamp);