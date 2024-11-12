import axios from 'axios';

const api = axios.create({
  baseURL: 'https://your-server-endpoint', // 서버 기본 URL
  headers: {
    'Content-Type': 'application/json',
  },
});

export default api;
