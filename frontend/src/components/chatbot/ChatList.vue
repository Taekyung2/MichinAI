<template>
  <div class="container">
    <h3>채팅 목록</h3>
    <v-divider class="mt-1 mb-3"/>
    <v-row class="chat-list">
        <ChatListItem 
        :chat="eachChat"
        v-for="eachChat in chatList" :key="eachChat.date"/>
    </v-row>
  </div>
</template>

<script>
import ChatListItem from '@/components/chatbot/ChatListItem.vue';
import SERVER from '@/api/spring.js'
import axios from 'axios';

export default {
    name: 'ChatList',
    
    components:{
        ChatListItem
    },
    
    data(){
        return{
          chatList: [],
        }
    },
   
    mounted() {
      this.getChatList();
    },

    methods: {
      getChatList() {
        // var wow = this.$store.getters["Kakao/getAccount"];
        var botKey = this.$store.state.Kakao.account.userBotKey;
        axios.get(SERVER.URL + "/chat/", {
          params: {
            userBotKey: botKey
          }
        }).then(({data}) => {
          // console.log(data);
          this.chatList = data.chatList})
      }

    }
}
</script>

<style>
.chat-list{
  display: flex;
  justify-content: space-around;
}
</style>