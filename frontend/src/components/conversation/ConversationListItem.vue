<template>
<div>
  <li>
    {{conversation.id}}번째, 회화 아이템 입니다.
  </li>
</div>
</template>

<script>
export default {
    name: 'ConversationListItem',
    props: {
      conversation: Object,
    }
}
</script>

<style>

</style>