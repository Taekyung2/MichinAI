<template>
  <v-card
  outlined
  @click="selectedChat(chat)"
  class="chat-list-item"
  >
    <v-list-item>
      <v-list-item-content class="chat"> 
        <p class="chat-date">
          <v-icon class="chat-icon mr-2">mdi-message</v-icon>
          {{chat.date.slice(2)}}
        </p>
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
      ]),
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