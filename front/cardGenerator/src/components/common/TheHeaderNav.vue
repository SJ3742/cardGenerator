<template>
  <header>
    <div class="wrapper">
      <nav>
        <img class="icon" src="@/assets/icon.png" @click="goTo('home')" />
        <router-link :to="{ name: 'form' }">form</router-link>
      </nav>
    </div>
  </header>
</template>

<script setup>
import { useRouter } from 'vue-router';
const router = useRouter();
const goTo = (routeName) => {
  router.push({ name: routeName });
};
</script>

<style scoped>
.wrapper {
  display: flex;
  width: 100%;
}

header {
  display: flex;
  justify-content: flex-start;
  width: 100%; /* header를 전체 너비로 설정 */
}

nav {
  display: flex;
  align-items: center;
  width: 100%; /* nav가 전체 너비를 차지하도록 설정 */
  background-color: black;
  padding: 10px;
}

.icon {
  width: 2em;
  height: 2em;
  margin-right: 8px;
  cursor: pointer;
}

nav a {
  font-weight: bold;
  text-decoration: none;
  color: #01FFA3;
}
</style>
