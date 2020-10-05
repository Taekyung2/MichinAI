<template>
  <div>
    <!-- <h3>{{ chat.id }}번 챗봇 채팅 디테일입니다.</h3> -->
    <v-list-item>
      <v-list-item-content>
        <v-list-item-title>{{chat.date}}</v-list-item-title>
      </v-list-item-content>
    </v-list-item>
    <p @click="back">뒤로가기</p>
    <template v-for="(chat, i) in chat.chats">
      <receive-msg :chat="chat" :key="i" v-if="chat.sender == 'bot'"/>
      <send-msg :chat="chat" :key="i" v-if="chat.sender == 'user'"/>
    </template>
  </div>
</template>

<script>
import receiveMsg from "./receiveMsg.vue";
import sendMsg from "./sendMsg.vue";

// import axios from "axios";
import { mapGetters } from "vuex";

export default {
  name: 'ChatDetail',

  components: {
    receiveMsg,
    sendMsg,
  },

  data() {
    return {
      "chatList": [],
    //   "chatList": [
    //     {
    //       "userBotKey": "d10dc43b9a6e7385221dcd247a80c4030deaa8337543d4da248f24b1a16ba22afa",
    //       "date": "2020-10-05",
    //       "chats": [
    //         {
    //           "msg": "BEGIN",
    //           "sender": "user",
    //           "time": "02:07:22.514",
    //           "check": []
    //         },
    //         {
    //           "msg": "hello dear , have you ever been to the united states ? it is a state in the united kingdom .",
    //           "sender": "bot",
    //           "time": "02:07:23.712",
    //           "check": []
    //         },
    //         {
    //           "msg": "nO",
    //           "sender": "user",
    //           "time": "02:07:52.208",
    //           "check": []
    //         },
    //         {
    //           "msg": "are you a fan of the beatles ? they are one of my favorite bands . do you like them ?",
    //           "sender": "bot",
    //           "time": "02:07:53.391",
    //           "check": []
    //         },
    //         {
    //           "msg": "i dont know about them!",
    //           "sender": "user",
    //           "time": "13:08:11.567",
    //           "check": [
    //             {
    //               "fromPos": 0,
    //               "toPos": 1,
    //               "recommends": [
    //                 "I"
    //               ],
    //               "category": ""
    //             },
    //             {
    //               "fromPos": 2,
    //               "toPos": 6,
    //               "recommends": [
    //                 "don't"
    //               ],
    //               "category": "Spelling mistake"
    //             }
    //           ]
    //         },
    //         {
    //           "msg": "me either . i don ' t know what i ' m going to do with all this money .",
    //           "sender": "bot",
    //           "time": "13:08:12.958",
    //           "check": []
    //         },
    //         {
    //           "msg": "i dont know about them! <br> HAHAHA",
    //           "sender": "user",
    //           "time": "02:08:11.567",
    //           "check": [
    //             {
    //               "fromPos": 0,
    //               "toPos": 1,
    //               "recommends": [
    //                 "I"
    //               ],
    //               "category": ""
    //             },
    //             {
    //               "fromPos": 7,
    //               "toPos": 11,
    //               "recommends": [
    //                 "knows"
    //               ],
    //               "category": "Need s"
    //             }
    //           ]
    //         },
    //       ]
    //     }
    //   ]
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