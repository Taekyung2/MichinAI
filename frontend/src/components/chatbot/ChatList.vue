<template>
  <img
    v-if="noChat"
    class="noChats"
    :src="require(`@/assets/noChats.png`)"
    alt="no chats"
  />
  <v-row v-else class="chat-list">
    <v-timeline align-top dense>
      <v-timeline-item
        :color="['#7db3d9', '#f48705', '#f2cc06'][idx % 3]"
        small
        v-for="(eachChat, idx) in chatList"
        :key="eachChat.date"
      >
        <v-row
        @click="selectedChat(eachChat)">
          <v-col cols="3">
            <v-icon class="chat-icon mr-2">mdi-message</v-icon>
          </v-col>
          <v-col>
            <strong>{{ eachChat.date }}</strong>
            <div class="caption">
              Last Chat :
              {{ eachChat.chats[eachChat.chats.length - 1].time | time }}
            </div>
          </v-col>
        </v-row>
      </v-timeline-item>

      <!-- <v-timeline-item color="teal lighten-3" small>
        <v-row class="pt-1">
          <v-col cols="3">
            <strong>3-4pm</strong>
          </v-col>
          <v-col>
            <strong>Design Stand Up</strong>
            <div class="caption mb-2">Hangouts</div>
           <v-avatar>
                <v-img
                  src="https://avataaars.io/?avatarStyle=Circle&topType=LongHairFrida&accessoriesType=Kurt&hairColor=Red&facialHairType=BeardLight&facialHairColor=BrownDark&clotheType=GraphicShirt&clotheColor=Gray01&graphicType=Skull&eyeType=Wink&eyebrowType=RaisedExcitedNatural&mouthType=Disbelief&skinColor=Brown"
                ></v-img>
              </v-avatar>
              <v-avatar>
                <v-img
                  src="https://avataaars.io/?avatarStyle=Circle&topType=ShortHairFrizzle&accessoriesType=Prescription02&hairColor=Black&facialHairType=MoustacheMagnum&facialHairColor=BrownDark&clotheType=BlazerSweater&clotheColor=Black&eyeType=Default&eyebrowType=FlatNatural&mouthType=Default&skinColor=Tanned"
                ></v-img>
              </v-avatar>
              <v-avatar>
                <v-img
                  src="https://avataaars.io/?avatarStyle=Circle&topType=LongHairMiaWallace&accessoriesType=Sunglasses&hairColor=BlondeGolden&facialHairType=Blank&clotheType=BlazerSweater&eyeType=Surprised&eyebrowType=RaisedExcited&mouthType=Smile&skinColor=Pale"
                ></v-img>
              </v-avatar>
          </v-col>
        </v-row>
      </v-timeline-item> -->
    </v-timeline>
    <!-- <ChatListItem
      :chat="eachChat"
      v-for="eachChat in chatList"
      :key="eachChat.date"
    /> -->
  </v-row>
</template>

<script>
// import ChatListItem from "@/components/chatbot/ChatListItem.vue";
import SERVER from "@/api/spring.js";
import { mapActions } from "vuex";

import axios from "axios";
export default {
  name: "ChatList",

  components: {
    // ChatListItem,
  },

  data() {
    return {
      chatList: [],
      noChat: false,
    };
  },
  filters: {
    time: (val) => {
      var moment = require("moment");
      console.log(val);
      return moment(val.slice(0, 8), ["HH:mm a"]).format("LT");
    },
  },

  mounted() {
    this.getChatList();
  },

  methods: {
    ...mapActions(["selectedChat"]),
    getChatList() {
      // var wow = this.$store.getters["Kakao/getAccount"];
      var botKey = this.$store.state.Kakao.account.userBotKey;
      axios
        .get(SERVER.URL + "/chat/", {
          params: {
            userBotKey: botKey,
          },
        })
        .then(({ data }) => {
          this.chatList = data.chatList;
          if (this.chatList.length == 0) this.noChat = true;
        });
    },
  },
};
</script>

<style>
/* .chat-list {
  display: flex;
  justify-content: space-around;
} */

.noChats {
  display: block;
  width: 70%;
  margin: 80px auto;
}
</style>