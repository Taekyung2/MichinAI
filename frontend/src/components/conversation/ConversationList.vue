<template>
  <div>
    <div class="conversation-option">
      <div class="calendar">
        <Calendar />
      </div>
      <div>
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
      </div>
    </div>
    <div
      class="text-center"
      style="margin-top: 150px"
      v-if="!getLoadingConversation"
    >
      <v-progress-circular
        :size="70"
        :width="7"
        color="#7db3d9"
        indeterminate
      ></v-progress-circular>
    </div>
    <template v-else>
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
    ...mapGetters(["getConversationList", "getLoadingConversation"]),
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
.conversation-option {
  display: flex;
  align-items: center;
  margin: 0px 15px;
  justify-content: space-between;
}
.calendar {
  width: 130px;
}
</style>