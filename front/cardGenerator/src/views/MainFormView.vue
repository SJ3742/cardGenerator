<template>
  <div class="container mt-4">
    <form @submit.prevent="handleSubmit">
      <div class="form-group">

        <!-- 이름 선택 방식 -->
        <div>
          <label>이름 선택 방식</label>
          <div class="form-check">
            <input type="radio" id="auto" value="auto" v-model="nameInputType" name="nameOption" class="form-check-input"/>
            <label for="auto" class="form-check-label">URL에서 자동 불러오기</label>
          </div>
          <div class="form-check">
            <input type="radio" id="manual" value="manual" v-model="nameInputType" name="nameOption" class="form-check-input"/>
            <label for="manual" class="form-check-label">직접 입력</label>
          </div>
        </div>

        <!-- 이름 입력 (직접 입력 선택 시에만 표시) -->
        <div v-if="nameInputType === 'manual'" class="mt-3">
          <label for="name">이름</label>
          <input type="text" id="name" class="form-control" placeholder="이름을 입력하세요" v-model="name" />
        </div>

        <!-- URL 입력 -->
        <div class="mt-3">
          <label for="url">URL(이름, 프로필 등의 정보를 읽어옵니다)</label>
          <input type="url" id="url" class="form-control" placeholder="URL을 입력하세요" v-model="url" required/>
        </div>
        <!-- 희귀도 선택 드롭다운 -->
        <div class="mt-3">
          <label for="rarity">희귀도</label>
          <select 
            id="rarity" 
            name="rarity" 
            class="form-control" 
            v-model="rarity" 
            required>
            <option v-for="rarity in rarities" :key="rarity.value" :value="rarity.value">
              {{ rarity.value }}
            </option>
          </select>
        </div>
        

        <!-- 타입 선택 -->
        <div class="mt-3">
          <label for="type">타입</label>
          <select 
            id="type" 
            name="type" 
            class="form-control" 
            v-model="selectedType"
            :style="{ color: selectedTypeColor }" required>
            <option 
              v-for="type in types" 
              :key="type.id" 
              :value="type.name" 
              :style="{ color: type.colorCode }">
              {{ type.name }}
            </option>
          </select>
        </div>

        <!-- 특성 -->
        <div class="mt-3">
          <label for="ability">특성</label>
          <input type="text" id="ability" class="form-control" placeholder="특성" v-model="ability" />
        </div>

        <!-- 스킬1 -->
        <div class="mt-3">
          <label for="skill1">스킬1</label>
          <!-- 스킬1 코스트 선택 -->
          <div class="mt-2">
            <label>스킬1 코스트</label>
            <div class="energy-icons">
              <img
                v-for="type in types"
                :key="type.id"
                :src="type.icon"
                :alt="type.name"
                class="energy-icon"
                @click="toggleSkillCost(type, 'skill1')"
              />
            </div>

            <div class="selected-skill-cost mt-1">
              <span v-for="(energy, index) in skill1.cost" :key="index" class="selected-energy">
                <img :src="energy.icon" :alt="energy.name" @click="removeCost(energy, 'skill1')" class="energy-icon" />
              </span>
            </div>
          </div>          
          <input type="text" id="skill1" class="form-control" placeholder="스킬1" v-model="skill1.name" required/>
          <input type="text" id="skill1damage" class="form-control mt-1" placeholder="위력" v-model="skill1.damage" required/>
          <input type="text" id="skill1desc" class="form-control mt-1" placeholder="스킬1 부가설명" v-model="skill1.desc"/>         
        </div>

        <!-- 스킬 2 -->
        <div class="mt-3">
          <label for="skill2">스킬2</label>
          <!-- 스킬1 코스트 선택 -->
          <div class="mt-2">
            <label>스킬2 코스트</label>
            <div class="energy-icons">
              <img
                v-for="type in types"
                :key="type.id"
                :src="type.icon"
                :alt="type.name"
                class="energy-icon"
                @click="toggleSkillCost(type, 'skill2')"
              />
            </div>
            <div class="selected-skill-cost mt-1">
              <span v-for="(energy, index) in skill2.cost" :key="index" class="selected-energy">
                <img :src="energy.icon" :alt="energy.name" @click="removeCost(energy, 'skill2')" class="energy-icon" />
              </span>
            </div>
          </div>           
          <input type="text" id="skill2" class="form-control" placeholder="스킬2" v-model="skill2.name" />
          <input type="text" id="skill2damage" class="form-control mt-1" placeholder="위력" v-model="skill2.damage"/>
          <input type="text" id="skill2desc" class="form-control mt-1" placeholder="스킬2 부가설명" v-model="skill2.desc"/>
         
        </div>

        <!-- 설명 -->
        <div class="mt-3">
          <label for="desc">설명</label>
          <input type="text" id="desc" class="form-control" placeholder="설명" v-model="desc" />
        </div>
        
      </div>

      <button type="submit" class="btn btn-primary mt-3">제출하기!!!</button>
    </form>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import api from '@/Axios/connect';
import router from '@/router';

const nameInputType = ref('auto');
const name = ref('');
const url = ref('');
const ability = ref('');
const selectedType = ref('');
const rarity = ref(''); // 희귀도 선택 변수
const desc = ref('');
const resultImg = ref('');
const skill1 = ref({ name: '', damage: '', desc: '', cost: [] });
const skill2 = ref({ name: '', damage: '', desc: '', cost: [] });

const rarities = ref([
  { value: 'C', level: 1 },
  { value: 'U', level: 2 },
  { value: 'R', level: 3 },
  { value: 'RR', level: 4 },
  { value: 'AR', level: 5 },
  { value: 'SR', level: 6 },
  { value: 'SAR', level: 7 },
  { value: 'IR', level: 8 },
  { value: 'UR', level: 9 }
]);

import grassIcon from '@/assets/grass.png';
import fireIcon from '@/assets/fire.png';
import waterIcon from '@/assets/water.png';
import lightningIcon from '@/assets/lightning.png';
import psychicIcon from '@/assets/psychic.png';
import fightIcon from '@/assets/fight.png';
import darkIcon from '@/assets/dark.png';
import steelIcon from '@/assets/steel.png';
import dragonIcon from '@/assets/dragon.png';
import normalIcon from '@/assets/normal.png';

const types = ref([
  { id: 1, name: '풀', colorCode: '#66a945', icon: grassIcon },
  { id: 2, name: '불', colorCode: '#e56c3e', icon: fireIcon },
  { id: 3, name: '물', colorCode: '#5185c5', icon: waterIcon },
  { id: 4, name: '전기', colorCode: '#fbb917', icon: lightningIcon },
  { id: 5, name: '초', colorCode: '#dd6b7b', icon: psychicIcon },
  { id: 6, name: '격투', colorCode: '#e09c40', icon: fightIcon },
  { id: 7, name: '악', colorCode: '#4c4948', icon: darkIcon },
  { id: 8, name: '강철', colorCode: '#69a9c7', icon: steelIcon },
  { id: 9, name: '드래곤', colorCode: '#535ca8', icon: dragonIcon },
  { id: 10, name: '무색', colorCode: '#949495', icon: normalIcon }
]);



const selectedTypeColor = computed(() => {
  const selected = types.value.find(type => type.name === selectedType.value);
  return selected ? selected.colorCode : '#000000';
});

// 스킬 코스트 추가 함수
function toggleSkillCost(type, skill) {
  // skill1과 skill2에 대해 각각 cost 배열을 선택
  const skillCost = skill === 'skill1' ? skill1.value.cost : skill2.value.cost;

  // 최대 4개의 아이콘만 추가 가능하도록 조건 설정
  if (skillCost.length < 4) {
    skillCost.push(type);
    skillCost.sort((a, b) => a.id - b.id);
  }
}

// 스킬 코스트 제거 함수
function removeCost(type, skill) {
  // skill1과 skill2의 cost 배열에서 선택
  const skillCost = skill === 'skill1' ? skill1.value.cost : skill2.value.cost;

  // 배열에서 타입 일치하는 요소 제거
  const index = skillCost.findIndex(energy => energy.id === type.id);
  if (index !== -1) {
    skillCost.splice(index, 1);
  }
}

function handleSubmit() {
  // 폼 데이터 준비
  const card = { 
    name: name.value, 
    url: url.value, 
    ability: ability.value, 
    type: selectedType.value.id, 
    rarity: rarity.value,
    skill1: {
      name: skill1.value.name,
      damage: skill1.value.damage,
      desc: skill1.value.desc,
      cost: skill1.value.cost.map(item => item.id) // 프록시 배열을 일반 배열로 전개
    },
    skill2: {
      name: skill2.value.name,
      damage: skill2.value.damage,
      desc: skill2.value.desc,
      cost: skill1.value.cost.map(item => item.id) // 프록시 배열을 일반 배열로 전개
    },
    desc: desc.value 
  };
  console.log(card);
  // Axios로 서버에 POST 요청
  api.post('http://localhost:8080/card', card) //아직 안적어놨음 나중에 적어야함!!!!!
    .then(response => {
      //응답 처리 로직 추가 (console에 로그 출력하고 result에 보내기)
      console.log('서버 응답:', response.data);
      router.push('result');
      
    })
    .catch(error => {
      console.error('요청 실패:', error);
      // 오류 처리 로직 추가 (예: 에러 메시지 표시)
    });
  
    

}

</script>

<style scoped>
.energy-icons {
  display: flex;
  gap: 5px;
}
.energy-icon {
  width: 30px;
  height: 30px;
  cursor: pointer;
  transition: transform 0.2s;
}
.energy-icon:hover {
  transform: scale(1.1);
}
.selected-skill-cost {
  display: flex;
  margin-top: 5px;
}
.selected-energy img {
  width: 25px;
  height: 25px;

}
</style>
