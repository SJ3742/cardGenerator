<script setup>
import {useRouter} from 'vue-router'
const router = useRouter(); 
const goTo = (routeName) => {
  router.push({ name: routeName });
};
</script>

<template>
  <header>
    <div class="wrapper">
      <nav>
        <img class="icon" src="@/assets/icon.png" @click="goTo('home')" />
        <RouterLink :to="{name:'form'}">form</RouterLink>
      </nav>
    </div>
  </header>

  <RouterView />
</template>

<style scoped>
header {
  display: flex;
  justify-content: start; /* 상단에 고정 */
  align-items: start; /* 상단에 고정 */
}

.wrapper {
  display: flex;
  flex-direction: column;
  width: 100%;
}

nav {
  background-color: aquamarine;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
}
.icon {
  width: 20px; /* 아이콘 크기 */
  height: 20px;
  margin-right: 8px; /* 텍스트와의 간격 */
  vertical-align: middle; /* 텍스트와 수평 맞춤 */
}
</style>
