<template>
    <div>
        <h2>챗봇 화면</h2>
        <ChatList/>
    </div>
</template>

<script>
import ChatList from '@/components/chatbot/ChatList.vue'

export default {
    name: 'Chatbot',
    components: {
        ChatList
    }

}
</script>

<style>

</style>