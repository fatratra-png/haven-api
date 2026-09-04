
INSERT INTO users (user_name, age, gender) VALUES
                                               ('Fitia', 22, 'FEMALE'),
                                               ('Rado', 24, 'MALE'),
                                               ('Nomena', 19, 'FEMALE'),
                                               ('Tiana', 31, 'MALE'),
                                               ('Voahangy', 45, 'FEMALE');
-- ---------- mood_entries ----------
INSERT INTO mood_entries (user_id, mood, note, time_stamp) VALUES
                                                               ('H00001', 'CALM',     NULL,                            CURRENT_DATE + TIME '08:30'),
                                                               ('H00001', 'TIRED',    'réunion trop longue',           CURRENT_DATE + TIME '13:15'),
                                                               ('H00001', 'PEACEFUL', NULL,                            CURRENT_DATE + TIME '19:00'),
                                                               ('H00002', 'STRESSED', 'deadline projet',               CURRENT_DATE + TIME '10:00'),
                                                               ('H00002', 'ANXIOUS',  NULL,                            CURRENT_DATE + TIME '15:45'),
                                                               ('H00002', 'CALM',     'séance de respiration faite',   CURRENT_DATE + TIME '21:00'),
                                                               ('H00003', 'HAPPY',    'bon petit-déjeuner',            CURRENT_DATE + TIME '07:45'),
                                                               ('H00003', 'NEUTRAL',  NULL,                            CURRENT_DATE + TIME '14:20'),
                                                               ('H00004', 'SAD',      'nouvelle décevante au travail', CURRENT_DATE + TIME '11:10'),
                                                               ('H00004', 'CALM',     NULL,                            CURRENT_DATE + TIME '20:00'),
                                                               ('H00005', 'PEACEFUL', 'journée tranquille',            CURRENT_DATE + TIME '18:30');


-- ---------- journal_entries ----------
INSERT INTO journal_entries (user_id, content, time_stamp) VALUES
                                                               ('H00001', 'Journée assez chargée, mais la pause déjeuner dehors a fait du bien.', CURRENT_DATE + TIME '12:45'),
                                                               ('H00001', 'Envie de me coucher tôt ce soir, la semaine a été longue.',            CURRENT_DATE + TIME '20:30'),
                                                               ('H00002', 'Bonne avancée sur le projet malgré le stress du matin.',              CURRENT_DATE + TIME '18:10'),
                                                               ('H00003', 'Petite victoire aujourd''hui, rien de spécial mais ça va.',           CURRENT_DATE + TIME '21:00'),
                                                               ('H00004', 'Pas la meilleure journée, mais demain est un autre jour.',            CURRENT_DATE + TIME '22:15');


-- ---------- focus_sessions ----------
INSERT INTO focus_sessions (user_id, duration, started_at, completed) VALUES
                                                                          ('H00001', INTERVAL '20 minutes', CURRENT_DATE + TIME '19:05', true),
                                                                          ('H00001', INTERVAL '10 minutes', CURRENT_DATE + TIME '21:00', false),
                                                                          ('H00002', INTERVAL '30 minutes', CURRENT_DATE + TIME '21:15', true),
                                                                          ('H00003', INTERVAL '15 minutes', CURRENT_DATE + TIME '22:00', true),
                                                                          ('H00005', INTERVAL '10 minutes', CURRENT_DATE + TIME '19:00', false);