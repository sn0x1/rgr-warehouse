import http from 'k6/http';
import { check, sleep } from 'k6';

// Load тест: стабільне середнє навантаження
export const options = {
    vus: 10,
    duration: '20s',
};

export default function () {
    // Створюємо "сесію" (jar) для збереження кукі (JSESSIONID)
    const jar = http.cookieJar();

    // 1. Отримуємо CSRF токен
    const loginPage = http.get('http://localhost:8080/login');
    const csrfMatch = loginPage.body.match(/name="_csrf" value="(.*?)"/);
    const csrfToken = csrfMatch ? csrfMatch[1] : '';

    // 2. Логінимось
    http.post('http://localhost:8080/login', {
        username: 'admin',
        password: 'admin',
        _csrf: csrfToken,
    });

    // 3. Йдемо на захищену сторінку складу товарів
    const productsRes = http.get('http://localhost:8080/products');

    // Перевіряємо, що ми дійсно потрапили на сторінку продуктів, а не отримали 403 Forbidden
    check(productsRes, {
        'status is 200': (r) => r.status === 200,
        'is products page': (r) => r.body.includes('Каталог товарів'),
    });

    sleep(2);
}