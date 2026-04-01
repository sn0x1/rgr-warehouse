import http from 'k6/http';
import { check, sleep } from 'k6';

// Smoke тест: мінімальне навантаження, щоб перевірити, що система працює
export const options = {
    vus: 5,           // 5 віртуальних користувачів
    duration: '10s',  // протягом 10 секунд
};

export default function () {
    // Звертаємось до головної сторінки (доступна без логіну)
    const res = http.get('http://localhost:8080/');
    
    // Перевіряємо, що статус 200 (OK)
    check(res, {
        'status is 200': (r) => r.status === 200,
        'page contains welcome text': (r) => r.body.includes('Вітаємо'),
    });
    
    // Пауза 1 секунда між запитами користувача
    sleep(1);
}