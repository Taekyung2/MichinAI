<template>
  <div>
    <!-- <h3>오늘의 회화 리스트 입니다.</h3> -->
    <v-row
      ><v-col cols="5" sm="6" md="4">
        <Calendar />
      </v-col>
      <v-col cols="7" sm="6" md="4">
        <v-chip-group
          v-model="language"
          column
          multiple
          mandatory
          class="d-flex align-end flex-column"
        >
          <v-chip filter outlined>영어</v-chip>
          <v-chip filter outlined>번역</v-chip>
        </v-chip-group>
      </v-col>
    </v-row>
    <template v-for="(conv, i) in getConversationList">
      <receive-msg
        :chat="conv"
        :key="i"
        v-if="conv.speaker == speaker"
        :lang="language"
      />
      <send-msg
        :chat="conv"
        :key="i"
        v-if="conv.speaker != speaker"
        :lang="language"
      />
    </template>
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
      language: [0],
    };
  },
  computed: {
    ...mapGetters(["getConversationList"]),
    speaker() {
      return this.getConversationList[0].speaker;
    },
  },
  components: {
    //        ConversationListItem,
    Calendar,
    receiveMsg,
    sendMsg,
  },
  mounted() {},
  methods: {},
};
</script>

<style>
</style>