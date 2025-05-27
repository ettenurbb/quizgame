const BASE_URL = 'http://localhost:8080/api';
let ws = null;
let currentQuestionId = null;

// Регистрация
function register() {
    const nickname = document.getElementById('registerNickname').value;
    const email = document.getElementById('registerEmail').value;
    const password = document.getElementById('registerPassword').value;

    fetch(`${BASE_URL}/users/register`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ nickname, email, password })
    }).then(res => res.text())
      .then(msg => alert(msg))
      .catch(err => console.error(err));
}

// Вход
function login() {
    const email = document.getElementById('loginEmail').value;
    const password = document.getElementById('loginPassword').value;

    fetch(`${BASE_URL}/auth/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password })
    }).then(res => res.json())
      .then(data => {
          localStorage.setItem('token', data.token);
          window.location.href = 'match.html';
      })
      .catch(err => console.error(err));
}

// Подключение к WebSocket при загрузке страницы игры
window.onload = function () {
    if (window.location.pathname.endsWith('match.html')) {
        connectWebSocket();
        loadUserProfile();
    }
};

function connectWebSocket() {
    ws = new WebSocket('ws://localhost:8080/ws');

    ws.onopen = () => {
        console.log('WebSocket подключен');
        startMatch();
    };

    ws.onmessage = function (event) {
        const data = JSON.parse(event.data);
        if (data.type === 'question') {
            showQuestion(data.question);
        } else if (data.type === 'end') {
            showResults(data.result);
        }
    };
}

function startMatch() {
    // Можно отправить запрос на начало матча
    fetch(`${BASE_URL}/matches/start`, {
        method: 'POST',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    });
}

function showQuestion(question) {
    document.getElementById('question-text').innerText = question.text;
    const answersContainer = document.getElementById('answers');
    answersContainer.innerHTML = '';
    question.answers.forEach(answer => {
        const btn = document.createElement('button');
        btn.innerText = answer.text;
        btn.onclick = () => sendAnswer(answer.id, question.id);
        answersContainer.appendChild(btn);
    });

    startTimer(question.timeLimit || 10);
}

function sendAnswer(answerId, questionId) {
    const payload = {
        userId: getUserId(),
        matchId: 1, // можно динамически
        questionId,
        selectedAnswerId: answerId
    };
    fetch(`${BASE_URL}/matches/submit-answer`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        body: JSON.stringify(payload)
    });
}

function showResults(result) {
    document.getElementById('result-screen').classList.remove('hidden');
    document.getElementById('match-result').innerText = `Вы набрали ${result.correctAnswersCount} правильных ответов.`;
}

function getUserId() {
    // Здесь можно извлечь ID пользователя из токена или отдельного запроса
    return 1; // временно
}

function loadUserProfile() {
    fetch(`${BASE_URL}/users/profile`, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    }).then(res => res.json())
      .then(user => {
          document.getElementById('username').innerText = user.nickname;
          document.getElementById('elo').innerText = user.eloRating;
          document.getElementById('avatar').src = user.avatarUrl || 'default-avatar.png';
      });
}

// Таймер
let timerInterval = null;
function startTimer(seconds) {
    let timeLeft = seconds;
    document.getElementById('time-left').innerText = timeLeft;
    clearInterval(timerInterval);
    timerInterval = setInterval(() => {
        timeLeft--;
        document.getElementById('time-left').innerText = timeLeft;
        if (timeLeft <= 0) {
            clearInterval(timerInterval);
        }
    }, 1000);
}