<template>
  <div class="container">
    <img v-if="noChat" class="noChats" src="./components/noChats.png" alt="no chats">
    <v-row v-else class="chat-list">
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
          noChat: false,
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
          console.log(data);
          this.chatList = data.chatList;
          if (data.chatList.length() == 0)
            this.noChat = true;
        })
      }

    }
}
</script>

<style>
.chat-list {
  display: flex;
  justify-content: space-around;
}

.noChats {
  display: block;
  width: 70%;
  margin: 80px auto;
}
</style>