<template>
<li @click="selectedChat(chat)">
  {{chat.id}}번째, 챗봇 채팅 아이템 입니다.
</li>
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