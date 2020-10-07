<template>
  <div class="container">
    <template v-if="this.$store.state.Kakao.account.userBotKey == null">
      <img class="no" :src="require(`@/assets/connectImg/noConnect.png`)" alt="not connected">
      <v-dialog
        v-model="dialog"
        scrollable
        max-width="300px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn 
            depressed
            small
            dark
            color="#FBC02D"
            v-bind="attrs"
            v-on="on"
            class="btn"
          >
            Q. 연동은 어떻게 하나요?
          </v-btn>
        </template>
        <v-card>
          <v-card-title class="headline">
            How To Connect?
          </v-card-title>
          <v-card-text style="height: 310px;">
            <img class="how" :src="require(`@/assets/connectImg/howTo1.png`)" alt="not connected">
            <br>
            <img class="how" :src="require(`@/assets/connectImg/howTo2.png`)" alt="not connected">
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="primary"
              text
              @click="dialog = false"
            >
              I GOT IT
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </template>
    <template v-else>
      <img v-if="noChat" class="noChats" :src="require(`@/assets/noChats.png`)" alt="no chats">
      <v-row v-else class="chat-list">
          <ChatListItem 
          :chat="eachChat"
          v-for="eachChat in chatList" :key="eachChat.date"/>
      </v-row>
    </template>
  </div>
</template>

<script>
import ChatListItem from '@/components/chatbot/ChatListItem.vue';
import SERVER from '@/api/spring.js'
import axios from 'axios';

import { mapGetters } from "vuex";

export default {
    name: 'ChatList',
    
    components:{
        ChatListItem,
    },
    
    computed: {
      ...mapGetters(["Kakao/isConnected"]),
    },

    data(){
        return{
          chatList: [],
          noChat: false,
          dialog: false,
        }
    },
   
    mounted() {
      this.getChatList();
    },

    methods: {
      getChatList() {
        var botKey = this.$store.state.Kakao.account.userBotKey;
        axios.get(SERVER.URL + "/chat/", {
          params: {
            userBotKey: botKey
          }
        }).then(({data}) => {
          console.log(data);
          this.chatList = data.chatList;
          if (this.chatList.length == 0)
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

.no {
  width: 100%;
  margin-top: 40%;
  margin-bottom: 20%;
}

.how {
  display: block;
  width: 70%;
  margin: 10px auto;
}

.btn {
  display: block;
  margin: 0px auto;
}
</style>