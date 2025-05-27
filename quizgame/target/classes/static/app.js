const BASE_URL = 'http://localhost:8080/api';
let token = localStorage.getItem('token');
let currentMatchId = null;

function register() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    fetch(`${BASE_URL}/users/register`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ nickname: email, email, password })
    }).then(res => res.text()).then(alert);
}

function login() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    fetch(`${BASE_URL}/auth/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password })
    }).then(res => res.json())
      .then(data => {
          token = data.token;
          localStorage.setItem('token', token);
          document.getElementById('auth-form').style.display = 'none';
          document.getElementById('game-menu').style.display = 'block';
      });
}

function startMatch() {
    // Предположим, мы играем с пользователем ID 2
    fetch(`${BASE_URL}/matches/start?player1=1&player2=2`, {
        headers: { Authorization: `Bearer ${token}` }
    }).then(res => res.json())
      .then(match => {
          currentMatchId = match.id;
          enterMatch(currentMatchId);
      });
}

function enterMatch(matchId) {
    document.getElementById('game-menu').style.display = 'none';
    document.getElementById('match-container').style.display = 'block';

    const ws = new WebSocket("ws://localhost:8080/ws");

    ws.onopen = () => {
        console.log("WebSocket соединение установлено");
    };

    ws.onmessage = event => {
        const data = JSON.parse(event.data);
        if (data.type === "match_result") {
            showResults(data.match);
        } else if (data.type === "question") {
            showQuestion(data.question);
        }
    };
}

function showQuestion(question) {
    const container = document.getElementById('match-container');
    container.innerHTML = `
        <h2>${question.text}</h2>
        ${question.answers.map(a => `
            <button onclick="sendAnswer(${question.id}, ${a.id})">${a.text}</button>
        `).join('<br>')}
    `;
}

function sendAnswer(questionId, answerId) {
    const request = {
        userId: 1,
        matchId: currentMatchId,
        questionId: questionId,
        selectedAnswerId: answerId,
        timeTaken: 5.5
    };

    fetch(`${BASE_URL}/matches/answer`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
        },
        body: JSON.stringify(request)
    });
}

function showResults(match) {
    const container = document.getElementById('match-container');
    container.innerHTML = `
        <h2>Игра окончена!</h2>
        <p>Результаты: ${JSON.stringify(match)}</p>
    `;
}