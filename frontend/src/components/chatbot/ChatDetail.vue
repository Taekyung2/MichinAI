<template>
  <div>
    <chat-detail-header :chat="chat"></chat-detail-header>
    
    <template v-for="(chat, i) in chat.chats">
      <receive-msg :chat="chat" :key="i" v-if="chat.sender == 'bot'"/>
      <send-msg :chat="chat" :key="i" v-if="chat.sender == 'user'"/>
    </template>
  </div>
</template>

<script>
import chatDetailHeader from "./components/chatDetailHeader"
import receiveMsg from "./components/receiveMsg";
import sendMsg from "./components/sendMsg.vue";

// import axios from "axios";
import { mapGetters } from "vuex";

export default {
  name: 'ChatDetail',

  components: {
    chatDetailHeader,
    receiveMsg,
    sendMsg,
  },

  data() {
    return {
      "chatList": [],
    }
  },

  computed:{
    ...mapGetters([
      'chat'
    ])
  },

  methods: {
    back(){
      this.$router.push({name:'ChatList'})
    },
  }
}
</script>

<style>

</style>