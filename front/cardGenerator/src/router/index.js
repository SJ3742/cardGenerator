import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MainFormView from '@/views/MainFormView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue'),
    },
    {
      path: '/form',
      name: 'form',
      component: MainFormView,
    },
    {
      path:'/result',
      name:'result',
      component: () => import('../views/AboutView.vue'),
    }
  ],


});

router.beforeEach((to, from, next) => {
  // 홈 경로로 이동할 때 경고 창 띄우기
  if (to.path === '/' && from.path ==='/form') {
    const confirmed = confirm('입력을 중지하고 홈으로 이동하실건가요?');
    if (confirmed) {
      next(); // 사용자가 확인을 누르면 이동
    } else {
      next(false); // 취소를 누르면 이동 취소
    }
  } else {
    next(); // 홈이 아닌 다른 경로는 바로 이동
  }
});

export default router
