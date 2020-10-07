<template>
  <div class="container">
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