<template>
    <v-card
    class="mx-auto"
    max-width="344"
    outlined
   @click="selectedChat(chat)"
  >
    <v-list-item three-line>
      <v-list-item-content>
        <v-list-item-title class="headline mb-1">{{chat.id}}번째, 챗봇 채팅</v-list-item-title>
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

</style>