<template>
  <div>
      <h3>오늘의 회화 리스트 입니다.</h3>
       <ConversationListItem 
        :conversation="conversation"
        v-for="conversation in conversationList" :key="conversation.id"/>
        
  </div>
</template>

<script>
import ConversationListItem  from '@/components/conversation/ConversationListItem.vue'
export default {
    name: 'ConversationList',
    data(){
        return{
            conversationList: [
                {id:'1'},
                {id:'2'}
            ]
        }
    },
    components: {
        ConversationListItem,
    },
    
}
</script>

<style>

</style>