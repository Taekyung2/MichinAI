<template>
  <div>
    <!-- <h3>오늘의 회화 리스트 입니다.</h3> -->

    <Calendar />
    <template v-for="(conv,i) in getConversationList">
      <receive-msg :chat="conv" :key="i" v-if="conv.speaker == speaker"/>
      <send-msg :chat="conv" :key="i" v-if="conv.speaker != speaker"/>
    </template>

    <!-- <ConversationListItem 
    :conversation="conversation" 
    /> -->
    <!-- <v-card
    class="conversation-item mx-auto"
    >
    <v-card-title style="font-size:16px; padding-bottom: 0px" v-model="conversationList"
    v-for="conv in getConversationList" :key="conv.id"
    >
    {{conv.speaker}}  : {{conv.eng}}
    
      
    </v-card-title>

    <v-card-actions>

      <v-spacer></v-spacer>
      해석 
      <v-btn
        icon
        @click="show = !show;"
      >
         <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
      </v-btn>
    </v-card-actions>

    <v-expand-transition>
      <div v-show="show">
        <v-divider></v-divider>

        <v-card-text
        v-for="conv in getConversationList" :key="conv.id">
          {{conv.speaker}}  : {{conv.kor}}
        </v-card-text>
      </div>
    </v-expand-transition>
  </v-card> -->
  </div>
</template>

<script>
// import ConversationListItem  from '@/components/conversation/ConversationListItem.vue'
import Calendar from "@/components/conversation/Calendar.vue";
import receiveMsg from "./receiveMsg";
import sendMsg from "./sendMsg.vue";

import { mapGetters } from "vuex";
//import axios from 'axios'
export default {
  data() {
    return {
      show: false,
      conversationList: [], 
    };
  },
  computed: {
    ...mapGetters(["getConversationList"]),
    speaker(){
      return this.getConversationList[0].speaker
    }
  },
  components: {
    //        ConversationListItem,
    Calendar,receiveMsg,sendMsg
  },
  mounted(){
  },
  methods: {},
};
</script>

<style>
</style>