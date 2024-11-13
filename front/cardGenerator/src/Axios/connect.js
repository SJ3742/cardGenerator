import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/', // 서버 기본 URL
  headers: {
    'Content-Type': 'application/json',
  },
});

export default api;
