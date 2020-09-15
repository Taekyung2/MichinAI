<template>
<li>
  {{chat.id}}번째, 챗봇 채팅 아이템 입니다.
</li>
</template>

<script>
export default {
    name: 'ChatListItem',
    props: {
      chat: Object,
    }
}
</script>

<style>

</style>