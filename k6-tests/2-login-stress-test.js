import http from 'k6/http';
import { check, sleep } from 'k6';

// Stress тест: різке зростання навантаження
export const options = {
    stages: [
        { duration: '5s', target: 20 }, // розгін до 20 користувачів
        { duration: '15s', target: 20 }, // тримаємо навантаження
        { duration: '5s', target: 0 },  // спад
    ],
};

export default function () {
    // 1. Отримуємо сторінку логіну для захоплення CSRF токена
    const loginPage = http.get('http://localhost:8080/login');
    
    // Шукаємо CSRF токен у прихованому полі форми (стандарт Spring Security)
    const csrfMatch = loginPage.body.match(/name="_csrf" value="(.*?)"/);
    const csrfToken = csrfMatch ? csrfMatch[1] : '';

    // 2. Відправляємо POST запит з логіном і паролем адміністратора
    const res = http.post('http://localhost:8080/login', {
        username: 'admin',
        password: 'admin',
        _csrf: csrfToken,
    });

    // Перевіряємо, що після логіну нас перенаправило (статус 200 або 302) і ми авторизовані
    check(res, {
        'login successful': (r) => r.status === 200 || r.status === 302,
    });

    sleep(1);
}