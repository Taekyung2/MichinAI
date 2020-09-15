<template>
  <div>
      <h3>챗봇 채팅 리스트입니다</h3>
      
    <ChatListItem 
        :chat="chat"
        v-for="chat in chatList" :key="chat.id"/>
  </div>
</template>

<script>
import ChatListItem from '@/components/chatbot/ChatListItem.vue'
export default {
    name: 'ChatList',
    
    data(){
        return{
            chatList: [
                {id:'1'},
                {id:'2'}
            ]
        }
    },
    components:{
        ChatListItem
    }
}
</script>

<style>

</style>