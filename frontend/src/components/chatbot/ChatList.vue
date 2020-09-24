<template>
  <div>
    <h3>챗봇 채팅 리스트입니다</h3>
    <v-row class="chat-list">
        <ChatListItem 
        :chat="chat"
        v-for="chat in chatList" :key="chat.id"/>
    </v-row>
  </div>
</template>

<script>
import ChatListItem from '@/components/chatbot/ChatListItem.vue'
export default {
    name: 'ChatList',
    
    data(){
        return{
            chatList: [
                {id:'1', date: '09-20'},
                {id:'2', date: '09-21'},
                {id:'3', date: '09-22'},
                {id:'4', date: '09-23'},
                {id:'5', date: '09-24'},
                {id:'6', date: '09-25'},
            ]
        }
    },
    components:{
        ChatListItem
    }
}
</script>

<style>
.chat-list{
  display: flex;
  justify-content: space-around;
}
</style>