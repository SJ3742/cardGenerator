<template>
    <div class="container mt-4">
      <form>
        <div class="form-group">
          <label for="name">이름</label>
          <input type="text" id="name" class="form-control" placeholder="이름을 입력하세요" />
  
          <label for="url">URL</label>
          <input type="url" id="url" class="form-control" placeholder="URL을 입력하세요" />
  
          <label for="type">타입</label>

          <!-- 선택된 옵션의 색상을 적용 -->
          <select 
            id="type" 
            name="type" 
            class="form-control" 
            v-model="selectedType"
            :style="{ color: selectedTypeColor }">
            <option 
              v-for="type in types" 
              :key="type.id" 
              :value="type.name" 
              :style="{ color: type.colorCode }">
              {{ type.name }}
            </option>
          </select>
          

          <label for="skill1">스킬1</label>
          <input type="text" id="skill1" class="form-control" placeholder="스킬을 입력하세요" />

          <label for="skill2">스킬2</label>
          <input type="text" id="skill2" class="form-control" placeholder="스킬을 입력하세요" />


        </div>
  
        <button type="submit" class="btn btn-primary mt-3">제출하기!!!</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref, computed } from 'vue';
  
  const types = ref([
    { id: 1, name: '풀', colorCode: '#78C850' },
    { id: 2, name: '불', colorCode: '#F08030' },
    { id: 3, name: '물', colorCode: '#6890F0' }, 
    { id: 4, name: '전기', colorCode: '#F8D030' },
    { id: 5, name: '초', colorCode: '#F85888' },
    { id: 6, name: '격투', colorCode: '#C03028' },
    { id: 7, name: '악', colorCode: '#705848' },
    { id: 8, name: '강철', colorCode: '#B8B8D0' },
    { id: 9, name: '드래곤', colorCode: '#7038F8' },
    { id: 10, name: '무색', colorCode: '#A8A878' }
  ]);
  
  const selectedType = ref(types.value[0].name); // 초기 선택값 설정
  
  // 선택된 타입에 맞는 색상 반환
  const selectedTypeColor = computed(() => {
    const selected = types.value.find(type => type.name === selectedType.value);
    return selected ? selected.colorCode : '#000000'; // 기본값: 검정색
  });
  </script>
  