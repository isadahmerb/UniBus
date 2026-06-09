const API_URL = 'http://localhost:8080';

function getToken() {
    // Retorna o token correto dependendo da página atual
    const path = window.location.pathname;
    if (path.includes('/motorista/')) return localStorage.getItem('tokenMotorista');
    if (path.includes('/passageiro/')) return localStorage.getItem('tokenPassageiro');
    return localStorage.getItem('token');
}

function setToken(token) {
    localStorage.setItem('token', token);
}

function logout() {
    const path = window.location.pathname;
    if (path.includes('/motorista/')) {
        localStorage.removeItem('tokenMotorista');
        localStorage.removeItem('motorista');
        window.location.href = '/motorista/login.html';
    } else if (path.includes('/passageiro/')) {
        localStorage.removeItem('tokenPassageiro');
        localStorage.removeItem('passageiro');
        window.location.href = '/passageiro/login.html';
    } else {
        localStorage.removeItem('token');
        localStorage.removeItem('usuario');
        window.location.href = '/index.html';
    }
}

async function request(method, endpoint, body = null) {
    const headers = {
        'Content-Type': 'application/json',
    };

    const token = getToken();
    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }

    const options = { method, headers };
    if (body) options.body = JSON.stringify(body);

    const response = await fetch(`${API_URL}${endpoint}`, options);

    if (response.status === 401 || response.status === 403) {
        logout();
        return;
    }

    return response;
}

const api = {
    post: (endpoint, body) => request('POST', endpoint, body),
    get: (endpoint) => request('GET', endpoint),
    put: (endpoint, body) => request('PUT', endpoint, body),
    patch: (endpoint, body) => request('PATCH', endpoint, body),
    delete: (endpoint) => request('DELETE', endpoint),
};