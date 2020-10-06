<template>
  <div class="container">
      <h2>오늘의 회화</h2>
      <ConversationList/>
  </div>
</template>

<script>
import ConversationList from '@/components/conversation/ConversationList.vue'
export default {
    name: 'Conversation',
    components:{
      ConversationList,      
    }
}
</script>

<style>

</style>