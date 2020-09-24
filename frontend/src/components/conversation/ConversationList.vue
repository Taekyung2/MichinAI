<template>
  <div>
    <h3>오늘의 회화 리스트 입니다.</h3>

    <Calender />
    <ConversationListItem 
    :conversation="conversation" 
    v-for="(conversation, i) in conversationList" :key="i"
    />
  </div>
           
</template>

<script>
import ConversationListItem  from '@/components/conversation/ConversationListItem.vue'
import Calender from '@/components/conversation/Calendar.vue'
export default {
    name: 'ConversationList',
    data(){
        return{
            conversationList: [
                {id:'1'},
                {id:'2'},
                {id:'3'},
                {id:'4'},
                {id:'5'},
            ],
           
        }
    },
     
    components: {
        ConversationListItem,
        Calender
    },
    
}
</script>

<style>

</style>