-- Таблица пользователей
CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL PRIMARY KEY,
    nickname VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    avatar_url VARCHAR(255),
    elo_rating INTEGER DEFAULT 1200,
    total_quizzes INTEGER DEFAULT 0,
    email_verified BOOLEAN DEFAULT FALSE,
    verification_token VARCHAR(255),
    token_expiration TIMESTAMP,
    is_blocked BOOLEAN DEFAULT FALSE
);

-- Таблица вопросов
CREATE TABLE IF NOT EXISTS questions (
    id BIGINT NOT NULL PRIMARY KEY,
    text TEXT NOT NULL,
    category VARCHAR(50),
    difficulty INTEGER
);

-- Таблица ответов
CREATE TABLE IF NOT EXISTS answers (
    id BIGINT NOT NULL PRIMARY KEY,
    question_id BIGINT NOT NULL,
    text TEXT NOT NULL,
    is_correct BOOLEAN NOT NULL,
    FOREIGN KEY (question_id) REFERENCES questions(id)
);

-- Таблица связей матча
CREATE TABLE IF NOT EXISTS matches (
    id BIGINT NOT NULL PRIMARY KEY,
    player1_id BIGINT,
    player2_id BIGINT,
    status VARCHAR(20),
    start_time TIMESTAMP,
    FOREIGN KEY (player1_id) REFERENCES users(id),
    FOREIGN KEY (player2_id) REFERENCES users(id)
);

-- Ответы во время матча
CREATE TABLE IF NOT EXISTS match_answers (
    id BIGINT NOT NULL PRIMARY KEY,
    match_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    selected_answer_id BIGINT,
    is_correct BOOLEAN NOT NULL,
    time_spent DOUBLE PRECISION,
    is_answered BOOLEAN NOT NULL DEFAULT FALSE, 
    FOREIGN KEY (match_id) REFERENCES matches(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (question_id) REFERENCES questions(id),
    FOREIGN KEY (selected_answer_id) REFERENCES answers(id)
);

-- История рейтинга
CREATE TABLE IF NOT EXISTS elo_history (
    id BIGINT NOT NULL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    old_rating INTEGER NOT NULL,
    new_rating INTEGER NOT NULL,
    change_reason VARCHAR(255),
    changed_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Таблица результата матчей
CREATE TABLE IF NOT EXISTS match_results (
    id BIGINT NOT NULL PRIMARY KEY,
    match_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    correct_answers_count INTEGER,
    time_left INTEGER,
    FOREIGN KEY (match_id) REFERENCES matches(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);


-- Таблица связи матчей и вопросов
CREATE TABLE IF NOT EXISTS match_questions (
    match_id BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    PRIMARY KEY (match_id, question_id),
    FOREIGN KEY (match_id) REFERENCES matches(id),
    FOREIGN KEY (question_id) REFERENCES questions(id)
);