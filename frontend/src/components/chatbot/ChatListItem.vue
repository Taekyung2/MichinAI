<template>
  <v-card
  outlined
  @click="selectedChat(chat)"
  class="chat-list-item"
  >
    <v-list-item three-line>
      <v-list-item-content class="chat"> 
        <v-icon class="chat-icon">mdi-message</v-icon>
        <p class="chat-date">{{chat.date}}</p>
      </v-list-item-content>
    </v-list-item>

  </v-card>
</template>

<script>
import { mapActions } from 'vuex'
export default {
    name: 'ChatListItem',
    props: {
      chat: Object,
    },
    methods: {
      ...mapActions([
        'selectedChat'
      ])
    }
}
</script>

<style>
.chat-list-item{
  min-width: 30%;
  margin-bottom: 10px;
}
.chat{
  display: flex;
  justify-content: center;
  align-content: center;
}


.chat-date{
  font-size: 16px;
  text-align: center;
}
.v-application p {
    margin-bottom: 0px;
}

</style>