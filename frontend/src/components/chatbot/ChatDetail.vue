<template>
  <div>
      <h3>{{ chat.id }}번 챗봇 채팅 디테일입니다.</h3>
       <p @click="back">뒤로가기</p>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  computed:{
    ...mapGetters([
      'chat'
    ])
  },
  methods: {
    back(){
      this.$router.push({name:'ChatList'})
    }
  }
}
</script>

<style>

</style>